#include "geralHeader.h"

//SIMULATION ENGINE MODULE

//VERIFICAR DIREÇÕES
//VERIFICAR RECONHECIMENTO DA PROXIMA POSIÇÃO

pthread_mutex_t mux;
pthread_mutex_t muxPlat = PTHREAD_MUTEX_INITIALIZER;

//Test variables
int opt = 0;
int opt2 = 0;

int main(void) {
	
	//START THE SYSTEM
	
	//START_API
	receiveInformationsFromServer();
	
	//### Shared Memory ###
    data * shm;
    int size = sizeof(data);
	int fd;
	
	//CREATE THE SHARED MEMORY
	open_shared_memory(&fd, (void **)&shm, size);
	
	//Geral information
	int i;
	
	int nAgvs = 4;

	//threads information
	pthread_t threads[nAgvs];
	
	//SIMULATION_API
	printf("\nSimulation_API Start...\n");			
	//CREATE ALL AGVS (in case there is an AGV with an ID of 0, we must assign other one with a valid ID, in order to
	pthread_mutex_init(&mux, NULL);
	
	printf("\nCMD_API Start...\n");		
	
	for(i = 0 ; i < nAgvs ; i++) {

		//While Receiving informations from the server, there is an AGV ID that is 0, and that doesn't exist in our database, its supose to be an AGV with an ID of : 23
		if(shm->ids[i] == 0) shm->infoAgvs[i].agvId = 23;
		else shm->infoAgvs[i].agvId = shm->ids[i];

		pthread_create(&threads[i], NULL, agv_thread, (void*) &(shm->infoAgvs[i]));
		
	}
	
	pthread_mutex_destroy(&mux);
	
	//WAIT FOR ALL THREADS TO END
	for (int i = 0; i < nAgvs; i++) pthread_join(threads[i], NULL);
	
	//Since there is no more tasks to assign to any AGV the CMD_API will end!
	printf("\nCMD_API ended!\n\n");
	
	//Since the CMD_API has ended the SIMULATION_API will end aswell
	printf("\nSimulation_API ended!\n\n");
	
	//The system will now end and free the dynamically allocated plant
	printf("\n\nSimulation ended!\n\n");
	free(geralPlant);
	
	exit(EXIT_SUCCESS);
	
}

//CMD_API
void* agv_thread (void *arg) {
	
	info* st = (info*) arg;
	
	//AGV MONITOR THREAD
	pthread_t agvMonitorStatus_thread;
	
	//BATTERY MONITOR THREAD
	pthread_t battery_monitorThread;
	
	//POSITION THREAD
	pthread_t positioningThread[2];
	
	//SIMULATION ENGINE THREAD
	pthread_t sensorSimulation;
	
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
	
	//ASSIGN THE POINTER TO THE SOCKET
	st->sockt = &sock;
	
	//READY TO WORK
	if(byte[1] == 2){
		
		byte[1] = 30;
	
		//ENVIA A MENSAGEM
		send(sock,&byte,sizeof(byte),0);
		
		//ORDER LOCATION:
			
		//ESPERA PELA POSIÇÃO DO X
		recv(sock,&byte,sizeof(byte),0);
		
		int xPos = byte[4];
		
		//ESPERA PELA POSIÇÃO DO Y
		recv(sock,&byte,sizeof(byte),0);
		
		int yPos = byte[4];
		
		//ASSIGN DESTINY THE ORDER ASSIGNED
		st->destiny.x = xPos;
		st->destiny.y = yPos;

		//MONITOR STATUS MODULE
		pthread_create(&agvMonitorStatus_thread, NULL, monitorStatus_thread, (void*) st);
		
		//BATTERY MANAGEMENT MODULE
		pthread_create(&battery_monitorThread, NULL, batteryMonitor_thread, (void*) st);
		
		//SENSOR MODULE
		pthread_create(&sensorSimulation, NULL, simulation_engine_thread, (void*) st);

		//### ROUTE UNTIL ORDER LOCATION
		
		pthread_mutex_lock(&mux);	
		printf("\n\nThread number %ld, Order Location: (Xpos : %d and Ypos : %d)\n\n",pthread_self(),xPos,yPos);
		
		//ROUTE PLANNER MODULE - DESTINO ORDER 
		calculateRoute(st);
		
		pthread_mutex_unlock(&mux);

		//A VALID MOCK FOR THIS AGV IN SPECIFIC - TEST METHOD
		//SIMULATION TEST METHOD
		
		/*
		
		pthread_mutex_lock(&muxPlat);
		if(opt == 0) {
			mockRoute(st);
		} else if(opt == 1) {
			mockRoute_2(st);
		}
		
		opt++;
		
		pthread_mutex_unlock(&muxPlat);
		* 
		*/
 		
		//POSITIONING MODULE - ROUTE ATÉ A ORDER
		pthread_create(&positioningThread[0], NULL, position_thread, (void*) st);
		
		//WAITS FOR THE POSITION MODULE TO END IN ORDER TO CALCULATE THE ROUTE TO THE DOCK
		pthread_join(positioningThread[0], NULL);
		
		printf("AGV Arrived the Order Location!\n\n");

		//### ROUTE UNTIL DOCK LOCATION
		
		//ASSIGN HIS FINAL LOCATION TO HIS DOCK AGAIN
		st->destiny.x = st->agvDock.x;
		st->destiny.y = st->agvDock.y;

		pthread_mutex_lock(&mux);

		// ROUTE PLANNER MODULE - DESTINO DOCK
		calculateRoute(st);

		pthread_mutex_unlock(&mux);

		// MOCK ROUTE TO DOCK - TEST METHOD
		//SIMULATION TEST METHOD
		
		/*
		
		pthread_mutex_lock(&muxPlat);
		if(opt2 == 0) {
			mockRouteToDock(st);
		} else if(opt2 == 1) {
			mockRouteToDock_2(st);
		}
		
		opt2++;
		
		pthread_mutex_unlock(&muxPlat);
		* 
		*/
		
		//POSITIONING MODULE - ROUTE ATÉ A DOCK
		pthread_create(&positioningThread[1], NULL, position_thread, (void*) st);
		
		//WAITS FOR THE POSITION MODULE TO END, SINCE THE AGV TASK IS NOW OVER!
		pthread_join(positioningThread[1], NULL);
		
		printf("\nAGV Arrived the Dock Location!\nEnding Task now...\n\n");
		
		pthread_cancel(agvMonitorStatus_thread);
		pthread_cancel(battery_monitorThread);
		pthread_cancel(sensorSimulation);

	}
	
	close(sock);

	pthread_exit(NULL);

}

//SIMULATION ENGINE THREAD (ANALYZING THE SENSORS)
void* simulation_engine_thread (void *arg) {

	info* st = (info*) arg;
	sensorAlert sAlert;
	
	pthread_t sensorAlertThread;
	
	for(;;) {
	
		//NEXT POSITION
		int nx = st->nextPosition.x;
		int ny = st->nextPosition.y;
	
		//CURRENT POSITION
		int cx = st->currentPosition.x;
		int cy = st->currentPosition.y;
		
		//CURRENT VELOCITY
		int vx = st->vInfo.x;
		int vy = st->vInfo.y;

		//Next position in the plant
		int nexPos = (nx * 19) + ny;
		
		//2 Positions ahead in X coords
		int nnXPos = ((nx + 1) * 19) + ny;

		//2 Positions ahead in Y coords
		int nnYPos = (nx * 19) + (ny + 1);

		//1 Position behind in X coords
		int nbXPos = ((nx - 1) * 19) + ny;

		//1 Position behind in Y coords
		int nbYPos = (nx * 19) + (ny - 1);

		if(geralPlant[nexPos] == 1) {

			//MUDAR VALOR SENSORES
			
			if(cx == nx) { //CIMA OU BAIXO
				if(cy < ny) {//BAIXO
					st->sInfo.back = 1;

					if((vx == 0) && (vy > 0)) {
						 sAlert.directionValue = 1;
						 sAlert.sensorValue = 1;
						 sAlert.st = st;
						 
						 pthread_create(&sensorAlertThread, NULL, sensor_threadFunction, (void*) &sAlert);
						 
					} else {
						//DIREÇÃO -> 0
					}
					
				}
				
				else if(cy > ny) { //CIMA
					st->sInfo.front = 1;

					if((vx == 0) && (vy < 0)) {
						 sAlert.directionValue = 1;
						 sAlert.sensorValue = 1;
						 sAlert.st = st;
						 
						 pthread_create(&sensorAlertThread, NULL, sensor_threadFunction, (void*) &sAlert);
					} else {
						 //DIREÇÃO -> 0
					}
					
				} 
				
				//ESQUERDA DIREIRA
				st->sInfo.right = 0;
				st->sInfo.left = 0;
				
				//OBLIQUO
				st->sInfo.frontRight = 0;
				st->sInfo.backRight = 0;
				st->sInfo.frontLeft = 0;
				st->sInfo.backLeft = 0;

			} else if(cy == ny) { //ESQUERDA OU DIREITA

				if(cx < nx) { //DIREITA
					st->sInfo.right = 1;

					if((vx > 0) && (vy == 0)) {
						 sAlert.directionValue = 1;
						 sAlert.sensorValue = 1;
						 sAlert.st = st;
						 
						 pthread_create(&sensorAlertThread, NULL, sensor_threadFunction, (void*) &sAlert);
					} else {
						//DIREÇÃO -> 0
					}

				}
				
				else if(cx > nx) { //ESQUERDA
					st->sInfo.left = 1;

					if((vx < 0) && (vy == 0)) {
						 sAlert.directionValue = 1;
						 sAlert.sensorValue = 1;
						 sAlert.st = st;
						 
						 pthread_create(&sensorAlertThread, NULL, sensor_threadFunction, (void*) &sAlert);
					} else {
						 //DIREÇÃO -> 0
					}

				}
					 
				//CIMA OU BAIXO
				st->sInfo.back = 0;
				st->sInfo.front = 0;
				
				//OBLIQUO
				st->sInfo.frontRight = 0;
				st->sInfo.backRight = 0;
				st->sInfo.frontLeft = 0;
				st->sInfo.backLeft = 0;

			} else if(cy != ny && cx != nx){ //OBLIQUO

				if(cx < nx && cy > ny){  //CIMA DIREITA
					 st->sInfo.frontRight = 1;
					 
					 if((vx > 0) && (vy < 0)) {
						 sAlert.directionValue = 1;
						 sAlert.sensorValue = 1;
						 sAlert.st = st;
						 
						 pthread_create(&sensorAlertThread, NULL, sensor_threadFunction, (void*) &sAlert);
					 } else {
						  //DIREÇÃO -> 0
					 }

				}
				
				else if(cx < nx && cy < ny) { //BAIXO DIREITA

					 st->sInfo.backRight = 1;

					 if((vx > 0) && (vy > 0)) {
						 sAlert.directionValue = 1;
						 sAlert.sensorValue = 1;
						 sAlert.st = st;
						 
						 pthread_create(&sensorAlertThread, NULL, sensor_threadFunction, (void*) &sAlert);
					 } else {
						  //DIREÇÃO -> 0
				     }
					 
				}
				
				else if(cx > nx && cy > ny) { //CIMA ESQUERDA

					 st->sInfo.frontLeft = 1;
					 
					 if((vx < 0) && (vy < 0)) {
						 sAlert.directionValue = 1;
						 sAlert.sensorValue = 1;
						 sAlert.st = st;
						 
						 pthread_create(&sensorAlertThread, NULL, sensor_threadFunction, (void*) &sAlert);
					 } else {
						  //DIREÇÃO -> 0
					 }

				}
				else if(cx > nx && cy < ny) { //BAIXO ESQUERDA

					 st->sInfo.backLeft = 1;
					 
					 if((vx < 0) && (vy > 0)) {
						 sAlert.directionValue = 1;
						 sAlert.sensorValue = 1;
						 sAlert.st = st;
						 
						 pthread_create(&sensorAlertThread, NULL, sensor_threadFunction, (void*) &sAlert);
					 } else {
						  //DIREÇÃO -> 0
					 }
					 
				} 
		
				//ESQUERDA DIREIRA
				st->sInfo.right = 0;
				st->sInfo.left = 0;
				
				//CIMA OU BAIXO
				st->sInfo.back = 0;
				st->sInfo.front = 0;

			}
		
		}else if(geralPlant[nnXPos] == 1 && ((nx + 1) != cx)) {

			//LANÇAR ALGO PARA REDUZIR A VELOCIDADE CASO A DIREÇÃO DO AGV SEJA NO SENTIDO DESTE OBSTÁCULO
				
			if(ny == cy) {
				st->sInfo.right = 2; //DIREITA

				if((vx > 0) && (vy == 0)) {
					sAlert.directionValue = 1;
					sAlert.sensorValue = 2;
					sAlert.st = st;
						 
					pthread_create(&sensorAlertThread, NULL, sensor_threadFunction, (void*) &sAlert);
				} else {
					//DIREÇÃO -> 0
				}
				
			}
			
			else if(ny < cy) { //CIMA DIREITA

				 st->sInfo.frontRight = 2;
				 
				 if((vx > 0) && (vy < 0)) {
					  sAlert.directionValue = 1;
					  sAlert.sensorValue = 2;
					  sAlert.st = st;
						 
					  pthread_create(&sensorAlertThread, NULL, sensor_threadFunction, (void*) &sAlert);
				 } else {
					  //DIREÇÃO -> 0
				 }
				 
			}
			 
			else if(ny > cy) { //BAIXO DIREITA

				 st->sInfo.backRight = 2;
 
				 if((vx > 0) && (vy > 0)) {
					sAlert.directionValue = 1;
					sAlert.sensorValue = 2;
					sAlert.st = st;
						 
					pthread_create(&sensorAlertThread, NULL, sensor_threadFunction, (void*) &sAlert);
				 } else {
					  //DIREÇÃO -> 0
				 }
				 
			}
			
		} else if(geralPlant[nbXPos] == 1 && ((nx - 1) != cx)) {
				
			if(ny == cy) { //ESQUERDA
				st->sInfo.left = 2;
				
				if((vx < 0) && (vy == 0)) {
					sAlert.directionValue = 1;
					sAlert.sensorValue = 2;
					sAlert.st = st;
						 
					pthread_create(&sensorAlertThread, NULL, sensor_threadFunction, (void*) &sAlert);
				} else {
					 //DIREÇÃO -> 0
				}
				
			}

			else if (ny < cy) { // CIMA ESQUERDA
				st->sInfo.frontLeft = 2;

				if ((vx < 0) && (vy < 0)) {
					sAlert.directionValue = 1;
					sAlert.sensorValue = 2;
					sAlert.st = st;
						 
					pthread_create(&sensorAlertThread, NULL, sensor_threadFunction, (void*) &sAlert);
				}
				
				else {
					// DIREÇÃO -> 0
				}
				
			}
			
			else if(ny > cy) { //BAIXO ESQUERDA
				 st->sInfo.backLeft = 2;

				if ((vx < 0) && (vy > 0)) {
					sAlert.directionValue = 1;
					sAlert.sensorValue = 2;
					sAlert.st = st;
						 
					pthread_create(&sensorAlertThread, NULL, sensor_threadFunction, (void*) &sAlert);
				}
				else {
					// DIREÇÃO -> 0
				}
				
			}

		} else if(geralPlant[nbYPos] == 1 && ((ny - 1) != cy)) {

			if(nx == cx) { //CIMA
				 st->sInfo.front = 2;

				 if((vx == 0) && (vy < 0)){
					sAlert.directionValue = 1;
					sAlert.sensorValue = 2;
					sAlert.st = st;
						 
					pthread_create(&sensorAlertThread, NULL, sensor_threadFunction, (void*) &sAlert);
				 }else {
					  //DIREÇÃO -> 0
				 }
				 
				 
			}

		} else if(geralPlant[nnYPos] == 1 && ((ny + 1) != cy)) {

			if(nx == cx) { //BAIXO
				 st->sInfo.back = 2;

				if ((vx == 0) && (vy > 0)) {
					sAlert.directionValue = 1;
					sAlert.sensorValue = 2;
					sAlert.st = st;
						 
					pthread_create(&sensorAlertThread, NULL, sensor_threadFunction, (void*) &sAlert);
				}
				
				else{
					// DIREÇÃO -> 0
				}
				
			}

		} else {
			//Reset the velocity variable
			st->secsSleep = 1;
		}

		// DORMIR 100MS
		usleep(100);
	}
}
