#include "geralHeader.h"

void openSocket(int* sock) {
	//### Connection ###
	int err;
	struct addrinfo  req, *list;
	
	bzero((char *)&req,sizeof(req));
	// let getaddrinfo set the family depending on the supplied server address
	req.ai_family = AF_UNSPEC;
	req.ai_socktype = SOCK_STREAM;
	err=getaddrinfo(IPSERVER, PORT , &req, &list);

	if(err) {
		printf("Failed to get server address, error: %s\n",gai_strerror(err)); 
		exit(EXIT_FAILURE); 
		
	}

	*sock = socket(list->ai_family,list->ai_socktype,list->ai_protocol);
	
	if(*sock == -1) {
		perror("Failed to open socket"); 
		freeaddrinfo(list); 
		exit(EXIT_FAILURE);
		
	}

	//Connection
	if(connect(*sock,(struct sockaddr *)list->ai_addr, list->ai_addrlen) != 0) {
		perror("Failed connect"); 
		freeaddrinfo(list); 
		close(*sock); 
		exit(EXIT_FAILURE);
		
	}
	
}

// Função que cria a memoria partilhada e o apontador para a mesma
void create_shared_memory(int *fd, void **p, int size) {
    // Cria memoria partilhada e trata do erro
    if ((*fd = shm_open(SHM_NAME, O_RDWR | O_CREAT, 0666)) == -1) {
        perror("erro ao criar memoria partilhada");
        exit(EXIT_FAILURE);
        
    }

    // Atribui o tamanho da memoria partilhada
    if (ftruncate(*fd, size) == -1) {
        perror("erro ao atribuir tamanho da memoria partilhada");
        exit(EXIT_FAILURE);
        
    }

    // Mapeia a memoria partilhada e trata o erro
    *p = mmap(NULL, size, PROT_READ | PROT_WRITE, MAP_SHARED, *fd, 0);
    
    if (*p == MAP_FAILED) {
        perror("erro ao mapear memoria partilhada");
        exit(EXIT_FAILURE);
        
    }
    
}


// Função que abre a memoria partilhada e o apontador para a mesma
void open_shared_memory(int *fd, void **p, int size) {
    // Abre memoria partilhada e trata do erro
    if ((*fd = shm_open(SHM_NAME, O_RDWR, 0666)) == -1) {
        perror("erro ao criar memoria partilhada");
        exit(EXIT_FAILURE);
        
    }

    // Atribui o tamanho da memoria partilhada
    if (ftruncate(*fd, size) == -1) {
        perror("erro ao atribuir tamanho da memoria partilhada");
        exit(EXIT_FAILURE);
        
    }

    // Mapeia a memoria partilhada e trata o erro
    *p = mmap(NULL, size, PROT_READ | PROT_WRITE, MAP_SHARED, *fd, 0);
    
    if (*p == MAP_FAILED) {
        perror("erro ao mapear memoria partilhada");
        exit(EXIT_FAILURE);
        
    }
    
}

//Função que pertence ao START_API e vai iniciar a memória partilhada
void receiveInformationsFromServer() {
	
	//### Shared Memory ###
	data * shm2;
    int size2 = sizeof(data);
	int fd;
	
	//Socket
	int sock;
	
	//MENSAGEM A ENVIAR AO SERVIDOR
	char byte[5] = {0,0,0,0,0};	
	
	//Mensagem que vai ser enviada ao server
	char protocolMessage[4] = {0,0,0,0};

	//CREATE THE SHARED MEMORY
	create_shared_memory(&fd, (void **)&shm2, size2);

	//OPEN THE SOCKET
	openSocket(&sock);
	
	//ENVIAR SINAL AO SERVIDOR
	send(sock,&byte,sizeof(byte),0);
	
	//RECEBER SINAL DO SERVIDOR
	recv(sock,&byte,sizeof(byte),0);

	//Server envia sinal 2, avisando que está pronto para receber a informação do cliente.
	if(byte[1] == 2){
		
		
	
		printf("Connected to the server!\n");
	
		//Se o argumento for 1 coloca todos os agvs nos seus default values... (RECOMENDADO SÓ SE A MEMORIA PARTILHADA ESTIVER VAZIA E PRECISAR DE SER PREENCHIDA)
		
		
		printf("Resenting Value...\n");
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
			shm2->infoAgvs[i].battery = 100;
				
		}
		
		printf("Reset Values completed!\nSearch all AGVs IDs.\n");
		
		//### AGV ID ###
		
		//Buscar todos os IDS existentes e adicioná-los á memoria partilhada
		findIDS(sock);
		
		for(int i = 0; i < eleSize; i++){
			/*Na comunicação de Java (host) -> C (client), o Cliente recebe um ID com valor 0, após muitos testes em o objetivo de resolver este problema, 
			concluiu-se que é possivel que haja falha na comunicação da VM para o Java por isso colocou-se este if para exclui-lo.*/
			if(i != 0){
			printf("ID: %d\n",array[i]);
			shm2->ids[i] = array[i];
			
			}
				
		}
			
		//A variavel global eleSize contém o numero de elementos que guarda na memoria partilhada.
		shm2->numAgvs = eleSize;
		
		
		printf("\nReceving the plant warehouse...\n");
		
		printf("\nReceiving the number of cols....\n");
		recv(sock,&protocolMessage,sizeof(protocolMessage),0);
		
		int nCols = 19;
		
		printf("Received number of columns: %d !\n",nCols);
		
		printf("\nReceiving the number of rows....\n");
		recv(sock,&protocolMessage,sizeof(protocolMessage),0);
		
		int nRows = 21;
		
		printf("Received number of rows: %d !\n",nRows);
			
		//### MATRIX CREATION ###
		int matrix [nRows][nCols];
		
		for(int i = 0; i < nRows; i++){
			for(int j = 0; j < nCols ; j++){
				//Recebe os numeros da matriz 0 (não existe dock nessa coordenada) ou 2 (existe dock nessa coordenada)
				recv(sock,&protocolMessage,sizeof(protocolMessage),0);
				matrix[i][j] = protocolMessage[3];		
					
			}				
				
		}
			
		//Na comunicação do servidor para o cliente, o cliente recebe valores que deviam ser 0, devido á falha de comunicação estas posições têm de estar obrigatoriamente 0.	
		matrix[0][0] = 0;
		matrix[0][1] = 0;	
			
		printf("\nWARN: Fixing the plant....\n\n");
		
		int **matrixZ = (int **) malloc(sizeof (int *) * nRows);
		
		for (int i = 0; i < nRows; ++i) {
			matrixZ[i] = (int *) malloc(sizeof (int) * nCols);
		}
		
		geralPlant = matrixZ[0];
					
		fixMatrix();
		
		printf("\nWARN: Matrix after the fixing:\n\n");
		for (int i=0 ; i<nRows; i++){
			for (int j=0 ; j<nCols ; j++){
				printf("%d ",geralPlant[i * nCols + j]);
			
			}	
			printf("\n");
		
		}
			
		/*			
		for(int i = 0; i < 19; i++){
			for(int j = 0; j < 19; j++){
				
			//Guardar a matriz na memória partilhada. ATENÇÃO NA PRIMEIRAS EXECUÇÃO HÁ CHANCES DA MATRIZ QUE FOR GERADA TER SÓ 0s, SE TAL ACONTECER INICIE O CONTROL UNIT DE NOVO.	
			shm2->plant[i][j] = matrix2[i][j];
					
			}
				
		}
		
		for(int i = 0; i < 19; i++){
			for(int j = 0; j < 19; j++){
				
				printf("%d ",shm2->plant[i][j]);
					
			}
				
			printf("\n");
				
		}
		* */
		
		//ASSIGN DOCKS TO THE AGVS (initial Position)
		
		int index = 0;
	
		printf("\n");
		
		for(int i = 0; i < nRows; i++){
			for(int j = 0; j < nCols; j++){
				if(geralPlant[i * nCols + j] == 2) {
					shm2->infoAgvs[index].currentPosition.x = j;
					shm2->infoAgvs[index].currentPosition.y = i;
					shm2->infoAgvs[index].agvDock.x = j;
					shm2->infoAgvs[index].agvDock.y = i;
					index++;
					printf("Assigned AGV Dock and first position! AGV index: %d, Position/dock : (xPos: %d and yPos: %d)\n",index,shm2->infoAgvs[index].agvDock.x,shm2->infoAgvs[index].agvDock.y);
					geralPlant[i * nCols + j] = 1;	
											
				}
 					
			}
				
				
		}
		
	}
	
	printf("\nINFO : Number of AGVs in the system : %d\n",shm2->numAgvs);
	
	closeConnection(&socket);
	
	printf("\nSTART_API : Connection closed...\n");
	return;
}



void sendStatusToServer(){
	
	//### Shared Memory ###
	data * shm2;
    int size2 = sizeof(data);
	int fd;
	
	//Socket
	int sock;
	
	//MENSAGEM A ENVIAR AO SERVIDOR
	char byte[5] = {0,0,0,0,0};	
	
	//Mensagem que vai ser enviada ao server
	char protocolMessage[4] = {0,0,0,0};

	//CREATE THE SHARED MEMORY
	create_shared_memory(&fd, (void **)&shm2, size2);

	//OPEN THE SOCKET
	openSocket(&sock);
	
	//ENVIAR SINAL AO SERVIDOR
	send(sock,&byte,sizeof(byte),0);
	
	//RECEBER SINAL DO SERVIDOR
	recv(sock,&byte,sizeof(byte),0);
	
	//Se o segundo argumento for 2, envia o status dos AGVs para os servidores
	//### SENDING AGV STATUS TO THE SERVER ###
        			
	int agvID ;
	byte[1] = 2;
	//Envia ao servidor que está pronto para enviar a informação		
	send(sock,&byte,sizeof(byte),0);
	char memoryInfo[70];	
	printf("Sending information to the server...\n");
	
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
		
	closeConnection(&socket);
	return;
		
}
			
//FIND ALL AGV IDS
void findIDS(int sock){
	
	char serverMessage[4];
	char protocolMessage[4];
	
	//COLOCA A MENSAGEM = 1, PARA PEDIR AO SERVIDOR QUE O CLIENTE PRECISA DOS IDS DOS AGVS
	serverMessage[1] = 1;
	
	//ENVIA A MENSAGEM
	send(sock,&serverMessage,sizeof(serverMessage),0);
	
	printf("Receiving the AGVs IDs...\n");
	//Receive Teste for some unknown reason, the VM requires another receive from the Server, without this receive the program won't receive all IDs
	recv(sock,&serverMessage,sizeof(serverMessage),0);
	
	int elementSize = serverMessage[1];
	
	printf("All AGV IDs received!\n");

	//Recebe o numero de IDs existentes
	recv(sock,&protocolMessage,sizeof(protocolMessage),0);
	printf("Processing all AGVs...\n",elementSize);
	
	if(elementSize > 6) {
		 printf("\nWARN: Since the warehouse only has 6 docks, the maximum number of AGVs we can have is 6!\n\n");
		 elementSize = 6;
	}
	
	for(int i = 0; i < elementSize; i++){

		printf("Processing AGV number: %d \n",i);
		
		//Recebe o ID de um AGV um a um
		recv(sock,&protocolMessage,sizeof(protocolMessage),0);
		
		//Cria-se uma mascara para obter o ID correto
		int id = protocolMessage[3] & 0xFF;
		
		//Adiciona-se ao vetor (variável global)
		array[i] = id;
		printf("AGV WITH AN ID OF: %d . RECEVIED WITH SUCESS!\n",id);
		
	}
	
		
	//Guadar o tamanho dos elementos na variavel global
	eleSize = elementSize;
	printf("\nALL AGV INFORMATION RECEIVED!\n");

}	


void deleteShareMemory(int *fd, void **p){
	// Desfaz o mapeamento
	if (munmap(p, sizeof(info)) < 0) {
		perror("No munmap()");
		exit(EXIT_FAILURE);
	}
	
	// Fecha o descritor
	if (close(*fd) < 0) {
		perror("No close()");
		exit(EXIT_FAILURE);
	}
	
	//Apaga a memória partilhada do sistema 
    if(shm_unlink(SHM_NAME) < 0){ 
        perror("No unlink()");
        exit(EXIT_FAILURE);
    }

}

void closeConnection(int* sock) {
	
	char byte[5] = {0,0,0,0,0};	

	byte[1] = 1;
	
	send(*sock,&byte,sizeof(byte),0);
	recv(*sock,&byte,sizeof(byte),0);
	
	close(*sock);
	
	printf("\nConnection closed...\n");
	return;
	
	
}
