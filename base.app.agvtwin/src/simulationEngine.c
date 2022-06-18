#include "geralHeader.h"

//SIMULATION ENGINE MODULE

//VERIFICAR DIREÇÕES
//VERIFICAR RECONHECIMENTO DA PROXIMA POSIÇÃO

pthread_mutex_t mux;
pthread_mutex_t muxPlat = PTHREAD_MUTEX_INITIALIZER;

int main(void) {
	
	//START THE SYSTEM
	
	//START_API
	receiveInformationsFromServer();
	
	//### Shared Memory ###
    data * shm;
    int size = sizeof(data);
	int fd;
	
	open_shared_memory(&fd, (void **)&shm, size);
	
	//Geral information
	int i;
	int nAgvs = 1;

	//threads information
	pthread_t threads[nAgvs];
	
	//SIMULATION_API
	printf("\nSimulation_API Start...\n");			
	//CREATE ALL AGVS (in case there is an AGV with an ID of 0, we must assign other one with a valid ID, in order to
	pthread_mutex_init(&mux, NULL);
	
	printf("\nCMD_API Start...\n");		
	
	for(i = 0 ; i < nAgvs ; i++) {
		shm->infoAgvs[i].agvId = shm->ids[i];
		pthread_create(&threads[i], NULL, agv_thread, (void*) &(shm->infoAgvs[i]));
		
	}
	
	pthread_mutex_destroy(&mux);
	
	//WAIT FOR ALL THREADS TO END
	for (int i = 0; i < nAgvs; i++) pthread_join(threads[i], NULL);
	
	printf("\nCMD_API ended!\n\n");
	printf("\nSimulation_API ended!\n\n");
	
	printf("\n\nSimulation ended!\n\n");
	free(geralPlant);
	
	//DEVE SER LANÇADO X THREADS CONSOANTE O NÚMERO DE AGVS A TRABALHAR!
	//FAZER ALGO PARA TERMINAR AS THREADS QUANDO O AGV ACABA A ROUTE!
	//PODEMOS FAZER AQUI UM HANDLER DE SINAL QUE ACABA TODAS AS THREADS EM EXECUÇÃO, QUE ACHAM?
	
}

void* agv_thread (void *arg) {
	
	info* st = (info*) arg;
	
	//AGV MONITOR THREAD
	pthread_t agvMonitorStatus_thread;
	
	//BATTERY MONITOR THREAD
	pthread_t battery_monitorThread;
	
	//POSITION THREAD
	pthread_t positioningThread[2];
	
	//Socket
	int sock;
	
	//MENSAGEM A ENVIAR AO SERVIDOR
	char byte[5] = {0,0,0,0,0};	
	
	//OPEN THE SOCKET
	openSocket(&sock);
	
	//ENVIAR SINAL AO SERVIDOR
	send(sock,&byte,sizeof(byte),0);
	
	//RECEBER SINAL DO SERVIDOR
	recv(sock,&byte,sizeof(byte),0);
	
	st->sockt = &sock;
	
	//READY TO WORK
	if(byte[1] == 2){
		
		byte[1] = 30;
	
		//ENVIA A MENSAGEM
		send(sock,&byte,sizeof(byte),0);
			
		//ESPERA PELA POSIÇÃO DO X
		recv(sock,&byte,sizeof(byte),0);
		
		int xPos = byte[4];
		
		//ESPERA PELA POSIÇÃO DO Y
		recv(sock,&byte,sizeof(byte),0);
		
		int yPos = byte[4];
		
		st->destiny.x = xPos;
		st->destiny.y = yPos;
		
		
		//MONITOR STATUS MODULE
		pthread_create(&agvMonitorStatus_thread, NULL, monitorStatus_thread, (void*) st);
		
		//BATTERY MANAGEMENT MODULE
		pthread_create(&battery_monitorThread, NULL, batteryMonitor_thread, (void*) st);
		
		//### ROUTE UNTIL ORDER LOCATION
		
		pthread_mutex_lock(&mux);	
		printf("\n\nThread number %ld, Order Location: (Xpos : %d and Ypos : %d)\n\n",pthread_self(),xPos,yPos);
		
		//ROUTE PLANNER MODULE - DESTINO ORDER 
		//calculateRoute(st);
		
		pthread_mutex_unlock(&mux);
		
		mockRoute(st);
		
		//POSITIONING MODULE - ROUTE ATÉ A ORDER
		pthread_create(&positioningThread[0], NULL, position_thread, (void*) st);
		
		//WAITS FOR THE POSITION MODULE TO END IN ORDER TO CALCULATE THE ROUTE TO THE DOCK
		pthread_join(positioningThread[0], NULL);
		
		//### ROUTE UNTIL DOCK LOCATION
		
		pthread_mutex_lock(&mux);
		
		//ROUTE PLANNER MODULE - DESTINO DOCK
		//calculateRoute(st);
		
		pthread_mutex_unlock(&mux);
		
		//POSITIONING MODULE - ROUTE ATÉ A DOCK
		//pthread_create(&positioningThread[1], NULL, position_thread, (void*) st);
		
		//WAITS FOR THE POSITION MODULE TO END, SINCE THE AGV TASK IS NOW OVER!
		//pthread_join(positioningThread[1], NULL);
		
		//QUANDO ACABA A ROTA VOLTAR A SUA DOCK
		
		sleep(10);
		
		pthread_cancel(agvMonitorStatus_thread);
		pthread_cancel(battery_monitorThread);
	
	}
	
	close(sock);

	pthread_exit(NULL);

}

/*

//SIMULATION ENGINE THREAD

void* simulation_engine_thread (void *arg) {

	data shm = *((data*) arg);
	
	pthread_mutex_lock(&mux);
	int currIndex = shm.index;
	shm.index++;
	pthread_mutex_unlock(&mux);
	
	for(;;) {
	
		//NEXT POSITION
		int nx = shm.infoAgvs[currIndex].nextPosition.x;
		int ny = shm.infoAgvs[currIndex].nextPosition.y;
	
		//CURRENT POSITION
		int cx = shm.infoAgvs[currIndex].currentPosition.x;
		int cy = shm.infoAgvs[currIndex].currentPosition.y;
		
		//CURRENT VELOCITY
		int vx = shm.infoAgvs[currIndex].vInfo.x;
		int vy = shm.infoAgvs[currIndex].vInfo.y;
		
		if(shm.plant[nx][ny] == 1) {
	
			//MUDAR VALOR SENSORES
			//TALVEZ SEMPRE QUE É ALTERADO UM VALOR DO SENSOR LANÇAR 1 THREAD, DESSE MESMO SENSOR, UMA THREAD QUE EXECUTE O SENSOR MODULE E QUE VERIFIQUE O SENTIDO/VELOCIDADE DE UM AGV? E SE FOR NECESSÁRIO LANÇAR UM SINAL PARA FAZER UMA NOVA ROUTE?
		
			if(cx == nx) { //CIMA OU BAIXO
				if(cy < ny) {//BAIXO
					shm.infoAgvs[currIndex].sInfo.back = 1; 
					
					if((vx == 0) && (vy > 0)) {
						 //DIREÇÃO -> 1
					} else {
						//DIREÇÃO -> 0
					}
					
					//CRIAR THREAD SENSOR
					//TALVEZ APENAS CRIAR A THREAD CASO A DIREÇÃO SEJA 1!
				
				}
				
				else if(cy > ny) { //CIMA
					shm.infoAgvs[currIndex].sInfo.front = 1;
					
					if((vx == 0) && (vy < 0)) {
						 //DIREÇÃO -> 1
					} else {
						 //DIREÇÃO -> 0
					}
					
					//CRIAR THREAD SENSOR
					//TALVEZ APENAS CRIAR A THREAD CASO A DIREÇÃO SEJA 1!
					
				} 
				
				//ESQUERDA DIREIRA
				shm.infoAgvs[currIndex].sInfo.right = 0;
				shm.infoAgvs[currIndex].sInfo.left = 0;
				
				//OBLIQUO
				shm.infoAgvs[currIndex].sInfo.frontRight = 0;
				shm.infoAgvs[currIndex].sInfo.backRight = 0;
				shm.infoAgvs[currIndex].sInfo.frontLeft = 0;
				shm.infoAgvs[currIndex].sInfo.backLeft = 0;
				
				//VERIFICAR QUE A POSIÇÃO CONTINUA OCUPADA!
				
				pthread_mutex_lock(&muxPlant);
				shm.plant[cx][cy] = 1;
				pthread_mutex_unlock(&muxPlant);
			
			} else if(cy == ny) { //ESQUERDA OU DIREITA
				if(cx < nx) { //DIREITA
					shm.infoAgvs[currIndex].sInfo.right = 1; 
					
					if((vx > 0) && (vy == 0)) {
						 //DIREÇÃO -> 1
					} else {
						//DIREÇÃO -> 0
					}
					
					//CRIAR THREAD SENSOR
					//TALVEZ APENAS CRIAR A THREAD CASO A DIREÇÃO SEJA 1!
					
				}
				
				else if(cx > nx) { //ESQUERDA
					shm.infoAgvs[currIndex].sInfo.left = 1;
					
					if((vx < 0) && (vy == 0)) {
						 //DIREÇÃO -> 1
					} else {
						 //DIREÇÃO -> 0
					}
					
					//CRIAR THREAD SENSOR
					//TALVEZ APENAS CRIAR A THREAD CASO A DIREÇÃO SEJA 1!
					
				}
					 
				//CIMA OU BAIXO
				shm.infoAgvs[currIndex].sInfo.back = 0;
				shm.infoAgvs[currIndex].sInfo.front = 0;
				
				//OBLIQUO
				shm.infoAgvs[currIndex].sInfo.frontRight = 0;
				shm.infoAgvs[currIndex].sInfo.backRight = 0;
				shm.infoAgvs[currIndex].sInfo.frontLeft = 0;
				shm.infoAgvs[currIndex].sInfo.backLeft = 0;
				
				//VERIFICAR QUE A POSIÇÃO CONTINUA OCUPADA!
				pthread_mutex_lock(&muxPlant);
				shm.plant[cx][cy] = 1;
				pthread_mutex_unlock(&muxPlant);
			
			} else if(cy != ny && cx != nx){ //OBLIQUO
			
				if(cx < nx && cy > ny){  //CIMA DIREITA
					 shm.infoAgvs[currIndex].sInfo.frontRight = 1;
					 
					 if((vx > 0) && (vy < 0)) {
						  //DIREÇÃO -> 1
					 } else {
						  //DIREÇÃO -> 0
					 }
						  
					//CRIAR THREAD SENSOR
					//TALVEZ APENAS CRIAR A THREAD CASO A DIREÇÃO SEJA 1!
					 
				}
				
				else if(cx < nx && cy < ny) { //BAIXO DIREITA
					 shm.infoAgvs[currIndex].sInfo.backRight = 1;
					 
					 if((vx > 0) && (vy > 0)) {
						  //DIREÇÃO -> 1
					 } else {
						  //DIREÇÃO -> 0
				     }
					
					//CRIAR THREAD SENSOR
					//TALVEZ APENAS CRIAR A THREAD CASO A DIREÇÃO SEJA 1!
					 
				}
				
				else if(cx > nx && cy > ny) { //CIMA ESQUERDA
					 shm.infoAgvs[currIndex].sInfo.frontLeft = 1;
					 
					 if((vx < 0) && (vy < 0)) {
						  //DIREÇÃO -> 1
					 } else {
						  //DIREÇÃO -> 0
					 }
					
					//CRIAR THREAD SENSOR
					//TALVEZ APENAS CRIAR A THREAD CASO A DIREÇÃO SEJA 1!
					 
				}
				else if(cx > nx && cy < ny) { //BAIXO ESQUERDA
					 shm.infoAgvs[currIndex].sInfo.backLeft = 1;
					 
					 if((vx < 0) && (vy > 0)) {
						  //DIREÇÃO -> 1
					 } else {
						  //DIREÇÃO -> 0
					 }
					
					//CRIAR THREAD SENSOR
					//TALVEZ APENAS CRIAR A THREAD CASO A DIREÇÃO SEJA 1!
					 
				} 
		
				//ESQUERDA DIREIRA
				shm.infoAgvs[currIndex].sInfo.right = 0;
				shm.infoAgvs[currIndex].sInfo.left = 0;
				
				//CIMA OU BAIXO
				shm.infoAgvs[currIndex].sInfo.back = 0;
				shm.infoAgvs[currIndex].sInfo.front = 0;
				
				//VERIFICAR QUE A POSIÇÃO CONTINUA OCUPADA!
				pthread_mutex_lock(&muxPlant);
				shm.plant[cx][cy] = 1;
				pthread_mutex_unlock(&muxPlant);
				
			}
		
		}else if(shm.plant[nx + 1][ny] == 1 && ((nx + 1) != cx)) { 
				
			//LANÇAR ALGO PARA REDUZIR A VELOCIDADE CASO A DIREÇÃO DO AGV SEJA NO SENTIDO DESTE OBSTÁCULO
				
			if(ny == cy) {
				shm.infoAgvs[currIndex].sInfo.right = 2; //DIREITA
				
				if((vx > 0) && (vy == 0)) {
					 //DIREÇÃO -> 1
				} else {
					//DIREÇÃO -> 0
				}
				
			}
			
			else if(ny < cy) { //CIMA DIREITA
				 shm.infoAgvs[currIndex].sInfo.frontRight = 2; 
				 
				 if((vx > 0) && (vy < 0)) {
					  //DIREÇÃO -> 1
				 } else {
					  //DIREÇÃO -> 0
				 }
				 
			}
			 
			else if(ny > cy) { //BAIXO DIREITA
				 shm.infoAgvs[currIndex].sInfo.backRight = 2; 
				 
				 if((vx > 0) && (vy > 0)) {
					  //DIREÇÃO -> 1
				 } else {
					  //DIREÇÃO -> 0
				 }
				 
			}
			
			//ALTERAR VALOR DA PLANTA PARA INFORMAR QUE AQUELA POSIÇÃO ESTA OCUPADA!
			
			pthread_mutex_lock(&muxPlant);
			shm.plant[nx][ny] = 1;
			shm.plant[cx][cy] = 0;
			pthread_mutex_unlock(&muxPlant);
				
		} else if(shm.plant[nx - 1][ny] == 1 && ((nx - 1) != cx)) { 
				
			//LANÇAR ALGO PARA REDUZIR A VELOCIDADE CASO A DIREÇÃO DO AGV SEJA NO SENTIDO DESTE OBSTÁCULO
				
			if(ny == cy) { //ESQUERDA
				shm.infoAgvs[currIndex].sInfo.left = 2;
				
				if((vx < 0) && (vy == 0)) {
					 //DIREÇÃO -> 1
				} else {
					 //DIREÇÃO -> 0
				}
				
			}
			
			else if(ny < cy) { //CIMA ESQUERDA
				shm.infoAgvs[currIndex].sInfo.frontLeft = 2;
				
				if((vx < 0) && (vy < 0)) {
					 //DIREÇÃO -> 1
				} else {
					//DIREÇÃO -> 0
				}
				
			}
			
			else if(ny > cy) { //BAIXO ESQUERDA
				 shm.infoAgvs[currIndex].sInfo.backLeft = 2;
				 
				 if((vx < 0) && (vy > 0)) {
					  //DIREÇÃO -> 1
				 } else {
					  //DIREÇÃO -> 0
				 }
				 
			}
			
			//ALTERAR VALOR DA PLANTA PARA INFORMAR QUE AQUELA POSIÇÃO ESTA OCUPADA!
			
			pthread_mutex_lock(&muxPlant);
			shm.plant[nx][ny] = 1;
			shm.plant[cx][cy] = 0;
			pthread_mutex_unlock(&muxPlant);
				
		} else if(shm.plant[nx][ny - 1] == 1 && ((ny - 1) != cy)) { 
				
			//LANÇAR ALGO PARA REDUZIR A VELOCIDADE CASO A DIREÇÃO DO AGV SEJA NO SENTIDO DESTE OBSTÁCULO
				
			if(nx == cx) { //CIMA
				 shm.infoAgvs[currIndex].sInfo.front = 2;
				 
				 if((vx == 0) && (vy < 0)){
					  //DIREÇÃO -> 1
				 }else {
					  //DIREÇÃO -> 0
				 }
				 
				 
			}
			
			//ALTERAR VALOR DA PLANTA PARA INFORMAR QUE AQUELA POSIÇÃO ESTA OCUPADA!
			
			pthread_mutex_lock(&muxPlant);
			shm.plant[nx][ny] = 1;
			shm.plant[cx][cy] = 0;
			pthread_mutex_unlock(&muxPlant);
							
		} else if(shm.plant[nx][ny + 1] == 1 && ((ny + 1) != cy)) { 
				
			//LANÇAR ALGO PARA REDUZIR A VELOCIDADE CASO A DIREÇÃO DO AGV SEJA NO SENTIDO DESTE OBSTÁCULO
				
			if(nx == cx) { //BAIXO
				 shm.infoAgvs[currIndex].sInfo.back = 2;
				 
				 if((vx == 0) && (vy > 0)) {
					  //DIREÇÃO -> 1
				 } else {
					  //DIREÇÃO -> 0
				 }
				 
			 }
			
			//ALTERAR VALOR DA PLANTA PARA INFORMAR QUE AQUELA POSIÇÃO ESTA OCUPADA!
			
			pthread_mutex_lock(&muxPlant);
			shm.plant[nx][ny] = 1;
			shm.plant[cx][cy] = 0;
			pthread_mutex_unlock(&muxPlant);
								
		} 
		//DORMIR 100MS 
		usleep(100);
	}
}
* 
*/

