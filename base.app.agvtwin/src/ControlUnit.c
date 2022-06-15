#include "geralHeader.h"

void findIDS(int sock);

//Tamanho de elemnetos dos IDs
int eleSize;

//Array que vai guardar os IDs do AGV
int array[256];

//CONTROL UNIT MODULE

pthread_mutex_t mux;

int main(int argc, char **argv) {
	
	//### Shared Memory ###
	data * shm2;
    int size2 = sizeof(data);
	int fd;
	
	//Socket
	int sock;
	
	//MENSAGEM A ENVIAR AO SERVIDOR
	char byte[5] = {0,0,0,0,0};	

	//CREATE THE SHARED MEMORY
	create_shared_memory(&fd, (void **)&shm2, size2);

	//OPEN THE SOCKET
	sock = openSocket();
	
	//ENVIAR SINAL AO SERVIDOR
	send(sock,&byte,sizeof(byte),0);
	
	//RECEBER SINAL DO SERVIDOR
	recv(sock,&byte,sizeof(byte),0);

	//Server envia sinal 2, avisando que está pronto para receber a informação do cliente.
	if(byte[1] == 2){
	
		printf("Connected to the server!\n");
	
		//Se o argumento for 1 coloca todos os agvs nos seus default values... (RECOMENDADO SÓ SE A MEMORIA PARTILHADA ESTIVER VAZIA E PRECISAR DE SER PREENCHIDA)
		if(strcmp(argv[1],"1") == 0){
		
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
				shm2->infoAgvs[i].battery = 0;
				
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
				shm2->ids[i] = array[i];}
				
			}
			
			//A variavel global eleSize contém o numero de elementos que guarda na memoria partilhada.
			shm2->numAgvs = eleSize;		
		
			printf("Recebendo o tamanho da matriz...\n");	
			//Mensagem que vai ser enviada ao server
			char protocolMessage[4] = {0,0,0,0};
		
			//### MATRIX CREATION ###
			int matrix [19][19];
		
			for(int i = 0; i < 19; i++){
				for(int j = 0; j < 19 ; j++){
					//Recebe os numeros da matriz 0 (não existe dock nessa coordenada) ou 2 (existe dock nessa coordenada)
					recv(sock,&protocolMessage,sizeof(protocolMessage),0);
					matrix[i][j] = protocolMessage[3];	
				
				}				
			}
		
			//Na comunicação do servidor para o cliente, o cliente recebe valores que deviam ser 0, devido á falha de comunicação estas posições têm de estar obrigatoriamente 0.	
			matrix[0][0] = 0;
			matrix[0][1] = 0;	
		
			for(int i = 0; i < 19; i++){
				for(int j = 0; j < 19; j++){
				
				//Guardar a matriz na memória partilhada. ATENÇÃO NA PRIMEIRAS EXECUÇÃO HÁ CHANCES DA MATRIZ QUE FOR GERADA TER SÓ 0s, SE TAL ACONTECER INICIE O CONTROL UNIT DE NOVO.	
				shm2->plant[i][j] = matrix[i][j];
					
				}
				
			}
		
			//Escrever a memória partilhada
			for(int i = 0; i < 19; i++){
				for(int j = 0; j < 19 ; j++){
					printf("%d",shm2->plant[i][j]);
				}				
				printf("\n");
			}
		
		}
	
		//Se o segundo argumento for 2, envia o status dos AGVs para os servidores
		else if(strcmp(argv[1],"2") == 0){
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
			
	}
	
	byte[1] = 1;
	
	send(sock,&byte,sizeof(byte),0);
	
	recv(sock,&byte,sizeof(byte),0);
	
	close(sock);
	
	printf("\nConnection closed...\n");
	exit(0);
}

//FIND ALL AGV IDS
void findIDS(int sock){
	
	char serverMessage[4];
	char protocolMessage[4];
	
	//COLOCA A MENSAGEM = 1, PARA PEDIR AO SERVIDOR QUE O CLIENTE PRECISA DOS IDS DOS AGVS
	serverMessage[1] = 1;
	
	//ENVIA A MENSAGEM
	send(sock,&serverMessage,sizeof(serverMessage),0);
	
	printf("Recebendo os IDs...\n");
	//Receive Teste for some unknown reason, the VM requires another receive from the Server, without this receive the program won't receive all IDs
	recv(sock,&serverMessage,sizeof(serverMessage),0);
	
	int elementSize = serverMessage[1];
	
	printf("Tamanho dos IDs dos AGVs recebidos.\n");

	//Recebe o numero de IDs existentes
	recv(sock,&protocolMessage,sizeof(protocolMessage),0);
	printf("Processando os AGVs que existe no total %d\n",elementSize);
	
	for(int i = 0; i < elementSize; i++){

		printf("Processando o AGV numero: %d \n",i);
		
		//Recebe o ID de um AGV um a um
		recv(sock,&protocolMessage,sizeof(protocolMessage),0);
		
		//Cria-se uma mascara para obter o ID correto
		int id = protocolMessage[3] & 0xFF;
		
		//Adiciona-se ao vetor (variável global)
		array[i] = id;
		printf("AGV COM O ID: %d . RECEBIDO COM SUCESSO!\n",id);
		
	}
	
	//Guadar o tamanho dos elementos na variavel global
	eleSize = elementSize;
	printf("\nTODOS OS PROCESSOS FORAM RECEBIDOS COM SUCESSO!\n");

}	
