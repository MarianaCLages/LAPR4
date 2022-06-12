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
#include <pthread.h>
#define BUF_SIZE sizeof(info)
// read a string from stdin protecting buffer overflow
#define GETS(B,S) {fgets(B,S-2,stdin);B[strlen(B)-1]=0;}
#define MEMORIE_NAME "/shm"

#define MAX 80
#define PORT "10639"
#define SA struct sockaddr
int size;


//CONTROL UNIT MODULE

pthread_mutex_t mux;


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
	
int length(char protocolMessage[]){
	
	int aux1 = 0;
	int aux2 = 0;
	
	if (protocolMessage[2] < 0) {
        aux1 = protocolMessage[2] + 256;
    } else {
        aux1 = protocolMessage[2];
        }

    if (protocolMessage[3] < 0) {
       aux2 = protocolMessage[3] + 256;
    } 
    else {
       aux2 = protocolMessage[3];
     }

     int strLenght = (aux1 + (aux2 * 256));
     return strLenght;

}

int * findIDS(int sock){
	
	
	char serverMessage[4];
	char protocolMessage[4];
	
	serverMessage[1] = 1;
	
	write(sock,serverMessage,1);
	
	read(sock,serverMessage,1);
	int elementSize = serverMessage[1];
	
	int * array;
	
	for(int i = 0; i < elementSize; i++){
		
		read(sock,protocolMessage,1);
		int id = protocolMessage[3] & 0xFF;
		
		*array = id;
		array++;
	}
	
	for(int i = 0; i < elementSize; i++){
		array--;
	}
	size = elementSize;
	return array;
	
}	


int main(int argc, char **argv) {
	//### Shared Memory ###
	data * shm2;


    //size of data
    int size = sizeof(info);
    int size2 = sizeof(data);
	int fd;


	create_shared_memory(&fd, (void **)&shm2, size2);

	
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


	
	//Se o argumento for 1 coloca todos os agvs nos seus default values..
	if(strcmp(argv[2],"1") == 0){
		
		printf("Resenting Value...");
		//default values	
		for(int i = 0; i < 256; i++){
			shm2->infoAgvs[i].sInfo.back = 0;
			shm2->infoAgvs[i].sInfo.backLeft = 0;
			shm2->infoAgvs[i].sInfo.backRight = 0;
			shm2->infoAgvs[i].sInfo.front = 0;
			shm2->infoAgvs[i].sInfo.frontLeft = 0;
			shm2->infoAgvs[i].sInfo.frontRight = 0;
			shm2->infoAgvs[i].sInfo.left = 0;
			shm2->infoAgvs[i].sInfo.right = 0;
			shm2->infoAgvs[i].vInfo.x = 0;
			shm2->infoAgvs[i].vInfo.y = 0;
			shm2->infoAgvs[i].battery = 0;
			
			//Buscar todos os IDS existentes e adicioná-los á memoria partilhada
			shm2->ids = findIDS(sock);
			shm2->numAgvs = size;
		}
	}
	
	//Se o segundo argumento for 2, envia o status do AGV para o servidor
	else if(strcmp(argv[2],"2") == 0){
        
		char byte[4] = {0,0,0,0};
    
		write(sock,&byte,1);
		read(sock,&byte,1);
    
		//Se o servidor mandar sinal 2 significa que está disposto a receber o status dos agvs
		if(byte[1] == 2){
			
			write(sock,&byte,1);
			char * memoryInfo = NULL;
		
			//Dependendo do numero do argumento, a informação do agv será enviada
			int agvID = atoi(argv[3]);

			printf("Sending the AGV information...");
			sprintf(memoryInfo, "%d,%d,%d,%d,%d,%d,%d,%d,%d,%d,%d,%d,%d,%d,%d",shm2->infoAgvs[agvID].vInfo.x,shm2->infoAgvs[agvID].vInfo.y,shm2->infoAgvs[agvID].sInfo.left,shm2->infoAgvs[agvID].sInfo.right,shm2->infoAgvs[agvID].sInfo.front,shm2->infoAgvs[agvID].sInfo.back,shm2->infoAgvs[agvID].sInfo.frontLeft,shm2->infoAgvs[agvID].sInfo.frontRight,shm2->infoAgvs[agvID].sInfo.backRight,shm2->infoAgvs[agvID].sInfo.backLeft,shm2->infoAgvs[agvID].currentPosition.x,shm2->infoAgvs[agvID].currentPosition.y,shm2->infoAgvs[agvID].nextPosition.x,shm2->infoAgvs[agvID].nextPosition.y,shm2->infoAgvs[agvID].battery);
	
		
			byte[2] = sizeof(memoryInfo) + 4;
			write(sock,byte,1);
		
			int size = sizeof(memoryInfo);
			char protocolMessage[4 + size]; 
		
			char * messageToBeSent = createProtocolMessageWithAString(memoryInfo,protocolMessage);
		
			write(sock,messageToBeSent,1);
		

		}
		close(sock);
	}

	
	exit(0);
	}
	

