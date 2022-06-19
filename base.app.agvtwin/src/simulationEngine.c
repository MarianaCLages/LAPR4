#include "geralHeader.h"

// SIMULATION ENGINE MODULE

// VERIFICAR DIREÇÕES
// VERIFICAR RECONHECIMENTO DA PROXIMA POSIÇÃO

pthread_mutex_t mux;
pthread_mutex_t muxPlat = PTHREAD_MUTEX_INITIALIZER;

int main(void)
{

	// START THE SYSTEM

	// START_API
	receiveInformationsFromServer();

	//### Shared Memory ###
	data *shm;
	int size = sizeof(data);
	int fd;

	open_shared_memory(&fd, (void **)&shm, size);

	// Geral information
	int i;
	int nAgvs = 4;

	// threads information
	pthread_t threads[nAgvs];

	// SIMULATION_API
	printf("\nSimulation_API Start...\n");
	// CREATE ALL AGVS (in case there is an AGV with an ID of 0, we must assign other one with a valid ID, in order to
	pthread_mutex_init(&mux, NULL);

	printf("\nCMD_API Start...\n");

	for (i = 0; i < nAgvs; i++)
	{

		// While Receiving informations from the server, there is an AGV ID that is 0, and that doesn't exist in our database, its supose to be an AGV with an ID of : 23
		if (shm->ids[i] == 0)
			shm->infoAgvs[i].agvId = 23;
		else
			shm->infoAgvs[i].agvId = shm->ids[i];

		pthread_create(&threads[i], NULL, agv_thread, (void *)&(shm->infoAgvs[i]));
	}

	pthread_mutex_destroy(&mux);

	// WAIT FOR ALL THREADS TO END
	for (int i = 0; i < nAgvs; i++)
		pthread_join(threads[i], NULL);

	printf("\nCMD_API ended!\n\n");
	printf("\nSimulation_API ended!\n\n");

	printf("\n\nSimulation ended!\n\n");
	free(geralPlant);

	// DEVE SER LANÇADO X THREADS CONSOANTE O NÚMERO DE AGVS A TRABALHAR! DONE
	// FAZER ALGO PARA TERMINAR AS THREADS QUANDO O AGV ACABA A ROUTE! DONE
	// PODEMOS FAZER AQUI UM HANDLER DE SINAL QUE ACABA TODAS AS THREADS EM EXECUÇÃO, QUE ACHAM? NO NEED
}

// CMD_API
void *agv_thread(void *arg)
{

	info *st = (info *)arg;

	// AGV MONITOR THREAD
	pthread_t agvMonitorStatus_thread;

	// BATTERY MONITOR THREAD
	pthread_t battery_monitorThread;

	// POSITION THREAD
	pthread_t positioningThread[2];

	// SENSOR THREAD
	pthread_t sensorThread;

	// Socket
	int sock;

	// MENSAGEM A ENVIAR AO SERVIDOR
	char byte[5] = {0, 0, 0, 0, 0};

	// OPEN THE SOCKET
	openSocket(&sock);

	// ENVIAR SINAL AO SERVIDOR
	send(sock, &byte, sizeof(byte), 0);

	// RECEBER SINAL DO SERVIDOR
	recv(sock, &byte, sizeof(byte), 0);

	st->sockt = &sock;

	// READY TO WORK
	if (byte[1] == 2)
	{

		byte[1] = 30;

		// ENVIA A MENSAGEM
		send(sock, &byte, sizeof(byte), 0);

		// ESPERA PELA POSIÇÃO DO X
		recv(sock, &byte, sizeof(byte), 0);

		int xPos = byte[4];

		// ESPERA PELA POSIÇÃO DO Y
		recv(sock, &byte, sizeof(byte), 0);

		int yPos = byte[4];

		// ASSIGN DESTINY THE ORDER ASSIGNED
		st->destiny.x = xPos;
		st->destiny.y = yPos;

		// MONITOR STATUS MODULE
		pthread_create(&agvMonitorStatus_thread, NULL, monitorStatus_thread, (void *)st);

		// BATTERY MANAGEMENT MODULE
		pthread_create(&battery_monitorThread, NULL, batteryMonitor_thread, (void *)st);

		// SENSOR MODULE
		pthread_create(&sensorThread, NULL, simulation_engine_thread, (void *)st);

		//### ROUTE UNTIL ORDER LOCATION

		pthread_mutex_lock(&mux);
		printf("\n\nThread number %ld, Order Location: (Xpos : %d and Ypos : %d)\n\n", pthread_self(), xPos, yPos);

		// ROUTE PLANNER MODULE - DESTINO ORDER
		calculateRoute(st);

		pthread_mutex_unlock(&mux);

		// A VALID MOCK FOR THIS AGV IN SPECIFIC
		// mockRoute(st);

		// POSITIONING MODULE - ROUTE ATÉ A ORDER
		pthread_create(&positioningThread[0], NULL, position_thread, (void *)st);

		// WAITS FOR THE POSITION MODULE TO END IN ORDER TO CALCULATE THE ROUTE TO THE DOCK
		pthread_join(positioningThread[0], NULL);

		printf("AGV Arrived the Order Location!\n\n");
		printf("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\nn\n\n\n\n\n\n\n\n\n\\nn\n\n\n\n\n\n");

		//### ROUTE UNTIL DOCK LOCATION

		// ASSIGN HIS FINAL LOCATION TO HIS DOCK AGAIN
		st->destiny.x = st->agvDock.x;
		st->destiny.y = st->agvDock.y;

		pthread_mutex_lock(&mux);

		// ROUTE PLANNER MODULE - DESTINO DOCK
		calculateRoute(st);

		pthread_mutex_unlock(&mux);

		// MOCK ROUTE TO DOCK
		// mockRouteToDock(st);

		// POSITIONING MODULE - ROUTE ATÉ A DOCK
		pthread_create(&positioningThread[1], NULL, position_thread, (void *)st);

		// WAITS FOR THE POSITION MODULE TO END, SINCE THE AGV TASK IS NOW OVER!
		pthread_join(positioningThread[1], NULL);

		printf("\nAGV Arrived the Dock Location!\nEnding Task now...\n\n");

		// sleep(10);

		pthread_cancel(agvMonitorStatus_thread);
		pthread_cancel(battery_monitorThread);
		pthread_cancel(sensorThread);
	}

	close(sock);

	pthread_exit(NULL);
}

// SIMULATION ENGINE THREAD (ANALYZING THE SENSORS)
void *simulation_engine_thread(void *arg)
{

	info *st = (info *)arg;

	for (;;)
	{

		// NEXT POSITION
		int nx = st->nextPosition.x;
		int ny = st->nextPosition.y;

		// CURRENT POSITION
		int cx = st->currentPosition.x;
		int cy = st->currentPosition.y;

		// CURRENT VELOCITY
		int vx = st->vInfo.x;
		int vy = st->vInfo.y;

		// Next position in the plant
		int nexPos = (nx * 19) + ny;

		// 2 Positions ahead in X coords
		int nnXPos = ((nx + 1) * 19) + ny;

		// 2 Positions ahead in Y coords
		int nnYPos = (nx * 19) + (ny + 1);

		// 1 Position behind in X coords
		int nbXPos = ((nx - 1) * 19) + ny;

		// 1 Position behind in Y coords
		int nbYPos = (nx * 19) + (ny - 1);

		if (geralPlant[nexPos] == 1)
		{

			// MUDAR VALOR SENSORES
			// TALVEZ SEMPRE QUE É ALTERADO UM VALOR DO SENSOR LANÇAR 1 THREAD, DESSE MESMO SENSOR, UMA THREAD QUE EXECUTE O SENSOR MODULE E QUE VERIFIQUE O SENTIDO/VELOCIDADE DE UM AGV? E SE FOR NECESSÁRIO LANÇAR UM SINAL PARA FAZER UMA NOVA ROUTE?

			if (cx == nx)
			{ // CIMA OU BAIXO
				if (cy < ny)
				{ // BAIXO
					st->sInfo.back = 1;

					//	printf("\nUpdated the back sensor!\n\n");

					if ((vx == 0) && (vy > 0))
					{
						// DIREÇÃO -> 1
					}
					else
					{
						// DIREÇÃO -> 0
					}

					// CRIAR THREAD SENSOR
					// TALVEZ APENAS CRIAR A THREAD CASO A DIREÇÃO SEJA 1!
				}

				else if (cy > ny)
				{ // CIMA
					st->sInfo.front = 1;

					//	printf("\nUpdated the front sensor!\n\n");

					if ((vx == 0) && (vy < 0))
					{
						// DIREÇÃO -> 1
					}
					else
					{
						// DIREÇÃO -> 0
					}

					// CRIAR THREAD SENSOR
					// TALVEZ APENAS CRIAR A THREAD CASO A DIREÇÃO SEJA 1!
				}

				// ESQUERDA DIREIRA
				st->sInfo.right = 0;
				st->sInfo.left = 0;

				// OBLIQUO
				st->sInfo.frontRight = 0;
				st->sInfo.backRight = 0;
				st->sInfo.frontLeft = 0;
				st->sInfo.backLeft = 0;

				/*

				//VERIFICAR QUE A POSIÇÃO CONTINUA OCUPADA!

				pthread_mutex_lock(&muxPlant);
				shm.plant[cx][cy] = 1;
				pthread_mutex_unlock(&muxPlant);
				*
				*/
			}
			else if (cy == ny)
			{ // ESQUERDA OU DIREITA

				if (cx < nx)
				{ // DIREITA
					st->sInfo.right = 1;

					//	printf("\nUpdated the right sensor!\n\n");

					if ((vx > 0) && (vy == 0))
					{
						// DIREÇÃO -> 1
					}
					else
					{
						// DIREÇÃO -> 0
					}

					// CRIAR THREAD SENSOR
					// TALVEZ APENAS CRIAR A THREAD CASO A DIREÇÃO SEJA 1!
				}

				else if (cx > nx)
				{ // ESQUERDA
					st->sInfo.left = 1;

					if ((vx < 0) && (vy == 0))
					{
						// DIREÇÃO -> 1
					}
					else
					{
						// DIREÇÃO -> 0
					}

					// CRIAR THREAD SENSOR
					// TALVEZ APENAS CRIAR A THREAD CASO A DIREÇÃO SEJA 1!
				}

				// CIMA OU BAIXO
				st->sInfo.back = 0;
				st->sInfo.front = 0;

				// OBLIQUO
				st->sInfo.frontRight = 0;
				st->sInfo.backRight = 0;
				st->sInfo.frontLeft = 0;
				st->sInfo.backLeft = 0;

				/*

				//VERIFICAR QUE A POSIÇÃO CONTINUA OCUPADA!
				pthread_mutex_lock(&muxPlant);
				shm.plant[cx][cy] = 1;
				pthread_mutex_unlock(&muxPlant);
				*
				*
				*/
			}
			else if (cy != ny && cx != nx)
			{ // OBLIQUO

				if (cx < nx && cy > ny)
				{ // CIMA DIREITA
					st->sInfo.frontRight = 1;

					//		 printf("\nnUpdated the top right sensor!\n\n");

					if ((vx > 0) && (vy < 0))
					{
						// DIREÇÃO -> 1
					}
					else
					{
						// DIREÇÃO -> 0
					}

					// CRIAR THREAD SENSOR
					// TALVEZ APENAS CRIAR A THREAD CASO A DIREÇÃO SEJA 1!
				}

				else if (cx < nx && cy < ny)
				{ // BAIXO DIREITA

					st->sInfo.backRight = 1;

					//		 printf("\nUpdated the back right sensor!\n\n");

					if ((vx > 0) && (vy > 0))
					{
						// DIREÇÃO -> 1
					}
					else
					{
						// DIREÇÃO -> 0
					}

					// CRIAR THREAD SENSOR
					// TALVEZ APENAS CRIAR A THREAD CASO A DIREÇÃO SEJA 1!
				}

				else if (cx > nx && cy > ny)
				{ // CIMA ESQUERDA

					st->sInfo.frontLeft = 1;

					//			 printf("\nUpdated the top left sensor!\n\n");

					if ((vx < 0) && (vy < 0))
					{
						// DIREÇÃO -> 1
					}
					else
					{
						// DIREÇÃO -> 0
					}

					// CRIAR THREAD SENSOR
					// TALVEZ APENAS CRIAR A THREAD CASO A DIREÇÃO SEJA 1!
				}
				else if (cx > nx && cy < ny)
				{ // BAIXO ESQUERDA

					st->sInfo.backLeft = 1;

					//		 printf("\nUpdated the back left sensor!\n\n");

					if ((vx < 0) && (vy > 0))
					{
						// DIREÇÃO -> 1
					}
					else
					{
						// DIREÇÃO -> 0
					}

					// CRIAR THREAD SENSOR
					// TALVEZ APENAS CRIAR A THREAD CASO A DIREÇÃO SEJA 1!
				}

				// ESQUERDA DIREIRA
				st->sInfo.right = 0;
				st->sInfo.left = 0;

				// CIMA OU BAIXO
				st->sInfo.back = 0;
				st->sInfo.front = 0;

				/*

				//VERIFICAR QUE A POSIÇÃO CONTINUA OCUPADA!
				pthread_mutex_lock(&muxPlant);
				shm.plant[cx][cy] = 1;
				pthread_mutex_unlock(&muxPlant);
				*
				*/
			}
		}
		else if (geralPlant[nnXPos] == 1 && ((nx + 1) != cx))
		{

			// LANÇAR ALGO PARA REDUZIR A VELOCIDADE CASO A DIREÇÃO DO AGV SEJA NO SENTIDO DESTE OBSTÁCULO

			if (ny == cy)
			{
				st->sInfo.right = 2; // DIREITA

				//	printf("\nUpdated the right sensor -> value 2!\n\n");

				if ((vx > 0) && (vy == 0))
				{
					// DIREÇÃO -> 1
				}
				else
				{
					// DIREÇÃO -> 0
				}
			}

			else if (ny < cy)
			{ // CIMA DIREITA

				st->sInfo.frontRight = 2;

				//			 printf("\nUpdated the top right sensor -> value 2!\n\n");

				if ((vx > 0) && (vy < 0))
				{
					// DIREÇÃO -> 1
				}
				else
				{
					// DIREÇÃO -> 0
				}
			}

			else if (ny > cy)
			{ // BAIXO DIREITA

				st->sInfo.backRight = 2;

				//			 printf("\nUpdated the back right sensor -> value 2!\n\n");

				if ((vx > 0) && (vy > 0))
				{
					// DIREÇÃO -> 1
				}
				else
				{
					// DIREÇÃO -> 0
				}
			}

			// ALTERAR VALOR DA PLANTA PARA INFORMAR QUE AQUELA POSIÇÃO ESTA OCUPADA!

			/*

			pthread_mutex_lock(&muxPlant);
			shm.plant[nx][ny] = 1;
			shm.plant[cx][cy] = 0;
			pthread_mutex_unlock(&muxPlant);
			*
			* */
		}
		else if (geralPlant[nbXPos] == 1 && ((nx - 1) != cx))
		{

			// LANÇAR ALGO PARA REDUZIR A VELOCIDADE CASO A DIREÇÃO DO AGV SEJA NO SENTIDO DESTE OBSTÁCULO

			if (ny == cy)
			{ // ESQUERDA
				st->sInfo.left = 2;

				//		printf("\nUpdated the left sensor -> value 2!\n\n");

				if ((vx < 0) && (vy == 0))
				{
					// DIREÇÃO -> 1
				}
				else
				{
					// DIREÇÃO -> 0
				}
			}

			else if (ny < cy)
			{ // CIMA ESQUERDA
				st->sInfo.frontLeft = 2;

				//		printf("\nUpdated the top left sensor -> value 2!\n\n");

				if ((vx < 0) && (vy < 0))
				{
					// DIREÇÃO -> 1
				}
				else
				{
					// DIREÇÃO -> 0
				}
			}

			else if (ny > cy)
			{ // BAIXO ESQUERDA
				st->sInfo.backLeft = 2;

				//		 printf("\nUpdated the back left sensor -> value 2!\n\n");

				if ((vx < 0) && (vy > 0))
				{
					// DIREÇÃO -> 1
				}
				else
				{
					// DIREÇÃO -> 0
				}
			}

			// ALTERAR VALOR DA PLANTA PARA INFORMAR QUE AQUELA POSIÇÃO ESTA OCUPADA!

			/*

			pthread_mutex_lock(&muxPlant);
			shm.plant[nx][ny] = 1;
			shm.plant[cx][cy] = 0;
			pthread_mutex_unlock(&muxPlant);
			*
			*/
		}
		else if (geralPlant[nbYPos] == 1 && ((ny - 1) != cy))
		{

			// LANÇAR ALGO PARA REDUZIR A VELOCIDADE CASO A DIREÇÃO DO AGV SEJA NO SENTIDO DESTE OBSTÁCULO

			if (nx == cx)
			{ // CIMA
				st->sInfo.front = 2;

				//		 printf("\nUpdated the top sensor -> value 2!\n\n");

				if ((vx == 0) && (vy < 0))
				{
					// DIREÇÃO -> 1
				}
				else
				{
					// DIREÇÃO -> 0
				}
			}

			// ALTERAR VALOR DA PLANTA PARA INFORMAR QUE AQUELA POSIÇÃO ESTA OCUPADA!

			/*

			pthread_mutex_lock(&muxPlant);
			shm.plant[nx][ny] = 1;
			shm.plant[cx][cy] = 0;
			pthread_mutex_unlock(&muxPlant);
			*
			* */
		}
		else if (geralPlant[nnYPos] == 1 && ((ny + 1) != cy))
		{

			// LANÇAR ALGO PARA REDUZIR A VELOCIDADE CASO A DIREÇÃO DO AGV SEJA NO SENTIDO DESTE OBSTÁCULO

			if (nx == cx)
			{ // BAIXO
				st->sInfo.back = 2;

				//		 printf("\nUpdated the back sensor -> value 2!\n\n");

				if ((vx == 0) && (vy > 0))
				{
					// DIREÇÃO -> 1
				}
				else
				{
					// DIREÇÃO -> 0
				}
			}

			// ALTERAR VALOR DA PLANTA PARA INFORMAR QUE AQUELA POSIÇÃO ESTA OCUPADA!

			/*

			/*

			pthread_mutex_lock(&muxPlant);
			shm.plant[nx][ny] = 1;
			shm.plant[cx][cy] = 0;
			pthread_mutex_unlock(&muxPlant);
			*
			* */
		}

		// DORMIR 100MS
		usleep(100);
	}
}
