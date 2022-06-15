#include "geralHeader.h"

// função que cria a memoria partilhada e o apontador para a mesma

void create_shared_memory(int *fd, void **p, int size)
{
    // cria memoria partilhada e trata o erro
    if ((*fd = shm_open(MEMORIE_NAME, O_RDWR, 0666)) == -1)
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

int main(int argc, char **argv)
{
	//### Shared Memory ###
	data * shm2;
    int size2 = sizeof(data);
	int fd;

	create_shared_memory(&fd, (void **)&shm2, size2);

	//### Connection ###
	int err, sock;
	struct addrinfo  req, *list;
   
	if(argc!=2) {
		puts("Server's IPv4/IPv6 address or DNS name is required as argument");
		exit(1);
	}
	
	bzero((char *)&req,sizeof(req));
	// let getaddrinfo set the family depending on the supplied server address
	req.ai_family = AF_UNSPEC;
	req.ai_socktype = SOCK_STREAM;
	err=getaddrinfo(IPSERVER, PORT , &req, &list);

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

	//connection
	if(connect(sock,(struct sockaddr *)list->ai_addr, list->ai_addrlen) != 0) {
		perror("Failed connect"); 
		freeaddrinfo(list); 
		close(sock); 
		exit(1);}
		
	//MENSAGEM A ENVIAR AO SERVIDOR
	char byte[5] = {0,0,0,0,0};	
	
	//ENVIAR SINAL AO SERVIDOR
	send(sock,&byte,sizeof(byte),0);
	
	//RECEBER SINAL DO SERVIDOR
	recv(sock,&byte,sizeof(byte),0);
	
	//Server envia sinal 2, avisando que está pronto para receber a informação do cliente.
	if(byte[1] == 2){
	
	        
        //### SENDING AGV STATUS TO THE SERVER ###
        			
		int agvID ;
		byte[1] = 2;
		//Envia ao servidor que está pronto para enviar a informação		
		send(sock,&byte,sizeof(byte),0);
		char memoryInfo[70];	
		printf("Vou mandar a informação para o server! %d\n",shm2->numAgvs);
		//Mensagem que vai ser enviada			
		char protocolMessage[4] = {0,0,0,0};
		
		for(int i = 0; i < shm2->numAgvs;i++){	
		
			//Devido ao problema de existir um ID = 0
			if(shm2->ids[i] != 0 ){
				//ID da memoria partilhada.
				agvID = shm2->ids[i];
				printf("\nSending the %d AGV information...\n",agvID);
					
				//STATUS DESSE AGV ORDENADO ENTRE VIRGULAS QUE VAI GUARDAR NA MEMÓRIA
				sprintf(memoryInfo, "%d,%d,%d,%d,%d,%d,%d,%d,%d,%d,%d,%d,%d,%d,%d",shm2->infoAgvs[agvID].vInfo.x,shm2->infoAgvs[agvID].vInfo.y,shm2->infoAgvs[agvID].sInfo.left,shm2->infoAgvs[agvID].sInfo.right,shm2->infoAgvs[agvID].sInfo.front,shm2->infoAgvs[agvID].sInfo.back,shm2->infoAgvs[agvID].sInfo.frontLeft,shm2->infoAgvs[agvID].sInfo.frontRight,shm2->infoAgvs[agvID].sInfo.backRight,shm2->infoAgvs[agvID].sInfo.backLeft,shm2->infoAgvs[agvID].currentPosition.x,shm2->infoAgvs[agvID].currentPosition.y,shm2->infoAgvs[agvID].nextPosition.x,shm2->infoAgvs[agvID].nextPosition.y,shm2->infoAgvs[agvID].battery);
	
				//vai dividir a informação entre virgulas
				char *p = strtok (memoryInfo, ",");	
				
				//Há 15 informações do AGV Status
				for(int j = 0; j < 15; j++){
						
					//O protocol message vai adquirir a informação uma a uma.
					protocolMessage[3] = strtol(p,NULL,10);
					p = strtok(NULL,",");
					printf("%d\n",protocolMessage[3]);
					send(sock,&byte,sizeof(protocolMessage),0);				
					}
			
			}
		}		
				

	}
	byte[1] = 1;
	close(sock);
	send(sock,&byte,sizeof(byte),0);
	recv(sock,&byte,sizeof(byte),0);
	printf("\nConnection closed...\n");
	
	exit(0);
}

