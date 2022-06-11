#include <errno.h>
#include <fcntl.h>
#include <semaphore.h>
#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <sys/mman.h>
#include <sys/stat.h>
#include <sys/types.h>
#include <sys/wait.h>
#include <time.h>
#include <unistd.h>
#include "data.h"
#include <strings.h>
#include <string.h>
#include <stdlib.h>
#include <unistd.h>
#include <stdio.h>
#include <sys/types.h>
#include <sys/socket.h>
#include <netinet/in.h>
#include <arpa/inet.h>
#include <netdb.h>
#define BUF_SIZE sizeof(info)
// read a string from stdin protecting buffer overflow
#define GETS(B,S) {fgets(B,S-2,stdin);B[strlen(B)-1]=0;}
#define MEMORIE_NAME "/shm"

#define MAX 80
#define PORT "10639"
#define SA struct sockaddr




// função que cria a memoria partilhada e o apontador para a mesma

void create_shared_memory(int *fd, void **p, int size)
{
    // cria memoria partilhada e trata o erro
    if ((*fd = shm_open(MEMORIE_NAME, O_RDWR | O_CREAT, 0666)) == -1)
    {
        perror("erro ao criar memoria partilhada");
        exit(1);
    }

    // atribui o tamanho da memoria partilhada
    if (ftruncate(*fd, size) == -1)
    {
        perror("erro ao atribuir tamanho da memoria partilhada");
        exit(1);
    }

    // mapeia a memoria partilhada e trata o erro
    *p = mmap(NULL, size, PROT_READ | PROT_WRITE, MAP_SHARED, *fd, 0);
    if (*p == MAP_FAILED)
    {
        perror("erro ao mapear memoria partilhada");
        exit(1);
    }
}

char * createProtocolMessageWithAString(char * memoryInfo, char protocolMessage[]){
	
	int size = sizeof(memoryInfo);
	char * byteMemory = memoryInfo;
	
	
	int dl1 = size % 256;
	int dl2 = size / 256;
	
	char dl1B = (char) dl1;
	char dl2B = (char) dl2;
	
	protocolMessage[0] = 0;
	protocolMessage[1] = 0;
	
	//Data Size
	protocolMessage[2] = dl1B;
	protocolMessage[3] = dl2B;
	
	for(int i = 0; i < size; i++){
		protocolMessage[i + 4] = byteMemory[i];
	}
	
	return protocolMessage;
	}

int main(int argc, char **argv) {
	
	
	//### Shared Memory ###
    info * shm;

    //size of data
    int size = sizeof(info);
	int fd;

	create_shared_memory(&fd, (void **)&shm, size);
	

	
	//### Connection ###
	int err, sock;
	//unsigned long f, i, n, num;
	//unsigned char bt;
	//char line[BUF_SIZE];
	struct addrinfo  req, *list;
	

   
	if(argc!=2) {
        puts("Server's IPv4/IPv6 address or DNS name is required as argument");
        exit(1);
    }

	bzero((char *)&req,sizeof(req));
	// let getaddrinfo set the family depending on the supplied server address
	req.ai_family = AF_UNSPEC;
	req.ai_socktype = SOCK_STREAM;
	err=getaddrinfo(argv[1], PORT , &req, &list);
	
	if(err) {
        printf("Failed to get server address, error: %s\n",gai_strerror(err)); 
        exit(1); 
     }

	sock=socket(list->ai_family,list->ai_socktype,list->ai_protocol);
	
	if(sock==-1) {
        perror("Failed to open socket"); 
        freeaddrinfo(list); 
        exit(1);
     }


	if(connect(sock,(struct sockaddr *)list->ai_addr, list->ai_addrlen) != 0) {
        perror("Failed connect"); 
        freeaddrinfo(list); 
        close(sock); 
        exit(1);}
        
    char byte[4] = {0,0,0,0};
    
    write(sock,&byte,1);
    read(sock,&byte,1);
    
    if(byte[1] == 2){
	
		write(sock,&byte,1);
		
		char * memoryInfo = NULL;
		
		

		printf("Sending the AGV information...");

		//sprintf(memoryInfo, "\nAGV Global Status\nVelocity:\n x: %d y: %d\nAll Sensors:\nLeft: %d\nRight: %d\nFront: %d\nBack: %d\nFront Left: %d\nFront Right: %d",shm->velo.x,shm->velo.y,shm->sensor.left,shm->sensor.right,shm->sensor.front,shm->sensor.back,shm->sensor.frontLeft,shm->sensor.frontRight);
		
		sprintf(memoryInfo, "%d,%d,%d,%d,%d,%d,%d,%d,%d,%d,%d,%d,%d,%d,%d",shm->vInfo.x,shm->vInfo.y,shm->sInfo.left,shm->sInfo.right,shm->sInfo.front,shm->sInfo.back,shm->sInfo.frontLeft,shm->sInfo.frontRight,shm->sInfo.backRight,shm->sInfo.backLeft,shm->currentPosition.x,shm->currentPosition.y,shm->nextPosition.x,shm->nextPosition.y,shm->battery);
	
		
		byte[2] = sizeof(memoryInfo) + 4;
		write(sock,byte,1);
		
		int size = sizeof(memoryInfo);
		char protocolMessage[4 + size]; 
		
		char * messageToBeSent = createProtocolMessageWithAString(memoryInfo,protocolMessage);
		
		write(sock,messageToBeSent,1);
		

	}
    
	

	close(sock);
	exit(0);
	}
