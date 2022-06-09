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
#define BUF_SIZE sizeof(data)
// read a string from stdin protecting buffer overflow
#define GETS(B,S) {fgets(B,S-2,stdin);B[strlen(B)-1]=0;}
#define MEMORIE_NAME "/shm"

#define MAX 80
#define PORT "9999"
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

int main(int argc, char **argv) {
	
	
	//### Shared Memory ###
    data * shm;

    //size of data
    int size = sizeof(data);
	int fd;

	create_shared_memory(&fd, (void **)&shm, size);
	
	
	//### Connection ###
	int err, sock;
	unsigned long f, i, n, num;
	unsigned char bt;
	char line[BUF_SIZE];
	struct addrinfo  req, *list;
   
	if(argc!=2) {
        puts("Server's IPv4/IPv6 address or DNS name is required as argument");
        exit(1);
    }

	bzero((char *)&req,sizeof(req));
	// let getaddrinfo set the family depending on the supplied server address
	req.ai_family = AF_UNSPEC;
	req.ai_socktype = SOCK_DGRAM;
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

	if(connect(sock,(struct sockaddr *)list->ai_addr, list->ai_addrlen)==-1) {
        perror("Failed connect"); 
        freeaddrinfo(list); 
        close(sock); 
        exit(1);}
	
	do {
        	do {
			printf("Enter a positive integer to SUM (zero to terminate): ");
			GETS(line,BUF_SIZE);
			while(sscanf(line,"%li",&num)!=1 || num<0) {
				puts("Invalid number");
				GETS(line,BUF_SIZE);
				}
			n=num;
			for(i=0;i<4;i++) {
				bt=n%256; write(sock,&bt,1); n=n/256; }
        		}
        	while(num);
		num=0; f=1; for(i=0;i<4;i++) {read(sock,&bt,1); num=num+bt*f; f=f*256;}
        	printf("SUM RESULT=%lu\n",num);
        	}
	while(num != 0);
	close(sock);
	exit(0);
	}
