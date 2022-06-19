#include "geralHeader.h"

//File that contains crucial functions to the system

SSL *sslConn;

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
		printf("\nPlease turn on the AGV Manager Server before the client!!\n\n");
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
		printf("Resenting Value...\n");
		
		//Default values	
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
		
		int nCols;
		int nRows;
			
		//A variavel global eleSize contém o numero de elementos que guarda na memoria partilhada.
		shm2->numAgvs = eleSize;
		
		printf("\nReceving the plant warehouse...\n");
		
		printf("\nReceiving the number of cols....\n");
		recv(sock,&protocolMessage,sizeof(protocolMessage),0);
		
		//IN CASE THERE IS TRASH IN THE SOCKET BUFFER, WE MUST HARD CODE FIX THIS INFORMATION
		if(protocolMessage[3] != 19) nCols = 19;
		else nCols = protocolMessage[3];
		
		printf("Received number of columns: %d !\n",nCols);
		
		printf("\nReceiving the number of rows....\n");
		recv(sock,&protocolMessage,sizeof(protocolMessage),0);
		
		//IN CASE THERE IS TRASH IN THE SOCKET BUFFER, WE MUST HARD CODE FIX THIS INFORMATION
		if(protocolMessage[3] != 21) nRows = 21;
		else nRows = protocolMessage[3];
			
		printf("Received number of rows: %d !\n",nRows);
			
		//### MATRIX CREATION ###
		int matrix [nRows][nCols];
		
		for(int i = 0; i < nRows; i++){
			for(int j = 0; j < nCols ; j++){
				//Recebe os numeros da matriz 0 (não existe dock nessa coordenada) ou 2 (existe dock nessa coordenada) ou 1 (se existir um obstáculo)
				recv(sock,&protocolMessage,sizeof(protocolMessage),0);
				matrix[i][j] = protocolMessage[3];		
					
			}				
				
		}
		
		//Na comunicação do servidor para o cliente, o cliente recebe valores que deviam ser 0, devido á falha de comunicação estas posições têm de estar obrigatoriamente 0.	
		matrix[0][0] = 0;
		matrix[0][1] = 0;	
		
		//IN CASE THE MATRIX DOESN'T UPDATE
		if(matrix[0][1] != 0) {
			printf("\nError : Matrix mismatching the original plant!\n\n");
		}
			
		//Dynamically Allocate a matrix in the heap in order to have a geral pointer valid for all threads in the system	
		printf("\nWARN: Fixing the plant....\n\n");
		int **matrixZ = (int **) malloc(sizeof (int *) * nRows);
		
		for (int i = 0; i < nRows; ++i) {
			matrixZ[i] = (int *) malloc(sizeof (int) * nCols);
		}
		
		//Assign the pointer to a dynamically created matrix
		geralPlant = matrixZ[0];
							
		//Fix the dynamically matrix created (Since the communication with the server provided some trash in the buffer)					
		fixMatrix();
		
		printf("\nWARN: Matrix after the fixing:\n\n");
		for (int i=0 ; i<nRows; i++){
			for (int j=0 ; j<nCols ; j++){
				printf("%d ",geralPlant[i * nCols + j]);
			
			}	
			printf("\n");
		
		}
				
		//ASSIGN DOCKS TO THE AGVS (initial Position and the AGVS Docks)
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
					//Since the route planner function only recognizes values between 0 and 1, we will assign the value 1 to a Dock (that before had a value 2)
					geralPlant[i * nCols + j] = 1;	
											
				}
 					
			}
				
				
		}
		
	}
	
	printf("\nINFO : Number of AGVs in the system : %d\n",shm2->numAgvs);
	
	printf("\nSTART_API : ");
	closeConnection(&sock);
	
	return;
}
			
//FIND ALL AGV IDS (SERVER WILL SEND ALL AGVS AWATING ORDERS)
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
	printf("Processing all AGVs...\n");
	
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

//CLOSE THE SOCKET CONNECTION

void closeConnection(int* sock) {
	
	char byte[5] = {0,0,0,0,0};	

	byte[1] = 1;
	
	//SEND REQUEST TO CLOSE CONNECTION
	send(*sock,&byte,sizeof(byte),0);
	recv(*sock,&byte,sizeof(byte),0);
	
	close(*sock);
	
	printf("\nConnection closed...\n");
	
	return;
	
}

//FUNCTION THAT OPENS A SSL CONNECTION WITH THE SERVER

void openSocketTLS(int* sock) {
	
	//### Connection ###
	int err;
	struct addrinfo  req, *list;
	
	char line[BUF_SIZE];
	
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
	
	const SSL_METHOD *method;
	SSL_CTX *ctx;
	
	method = SSLv23_client_method();
	ctx = SSL_CTX_new(method);
	
	
	// Load client's certificate and key
	
	char client[] = "client1";
	
	strcpy(line,client);
	strcat(line,".pem");
		
	SSL_CTX_use_certificate_file(ctx, line, SSL_FILETYPE_PEM);
		
	strcpy(line,client);
	strcat(line,".key");
		
	SSL_CTX_use_PrivateKey_file(ctx, line, SSL_FILETYPE_PEM);
		
	if (!SSL_CTX_check_private_key(ctx)) {
		puts("Error loading client's certificate/key");
		close(*sock);
		exit(1);
	}
		
	SSL_CTX_set_verify(ctx, SSL_VERIFY_PEER,NULL);
	
	// THE SERVER'S CERTIFICATE IS TRUSTED
	SSL_CTX_load_verify_locations(ctx,SERVER_SSL_CERT_FILE,NULL);
	
	// Restrict TLS version and cypher suites
	SSL_CTX_set_min_proto_version(ctx,TLS1_2_VERSION);
	SSL_CTX_set_cipher_list(ctx, "HIGH:!aNULL:!kRSA:!PSK:!SRP:!MD5:!RC4");
	
	sslConn = SSL_new(ctx);
	
	SSL_set_fd(sslConn, *sock);
	
	if(SSL_connect(sslConn)!=1) {
		puts("TLS handshake error");
		SSL_free(sslConn);
		close(*sock);
		exit(1);
	}
	
	printf("TLS version: %s\nCypher suite: %s\n",
	SSL_get_cipher_version(sslConn),SSL_get_cipher(sslConn));
	
	if(SSL_get_verify_result(sslConn)!= X509_V_OK) {
		puts("Sorry: invalid server certificate");
		SSL_free(sslConn);
		close(*sock);
		exit(1);
	}
	
	X509* cert=SSL_get_peer_certificate(sslConn);
	X509_free(cert);
	
	if(cert==NULL) {
		puts("Sorry: no certificate provided by the server");
		SSL_free(sslConn);
		close(*sock);
		exit(1);
	}
	
}
