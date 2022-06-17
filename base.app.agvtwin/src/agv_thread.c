#include "geralHeader.h"

/*
void* agv_thread (void *arg) {
	
	info st = *((info*) arg);
	
	//AGV MONITOR THREAD
	pthread_t agvMonitorStatus_thread;
	
	//BATTERY MONITOR THREAD
	pthread_t battery_monitorThread;
	
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
	
	st.sockt = &sock;
	
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
		
		st.destiny.x = xPos;
		st.destiny.y = yPos;
		
		printf("\nThread number %ld, Order Location: (Xpos : %d and Ypos : %d)\n",pthread_self(),xPos,yPos);
		
		pthread_create(&agvMonitorStatus_thread, NULL, monitorStatus_thread, (void*) &(st));
		pthread_create(&battery_monitorThread, NULL, batteryMonitor_thread, (void*) &(st));
		
		//COMEÇAR O ROUTE PLANNER!
		//Talvez lançar aqui a thread do battery_management (algoritmo simples que vai reduzir a bateria 
		
		//int row,int column,int ** matrix,int startX,int startY, int endX, int endY
		
		pthread_mutex_lock(&mux);
		calculateRoute(&st);
		pthread_mutex_unlock(&mux);
		
		//COMEÇAR O MOVIMENTO DEPOIS TBM
		//QUANDO ACABA A ROTA VOLTAR A SUA DOCK
		
		//routeplanner(estrutura de dados com a posição desejada)
		
		//Quando volta a sua DOCK fechar connection com o server e mandar abaixo as threads
		
		sleep(10);
		
		pthread_cancel(agvMonitorStatus_thread);
		pthread_cancel(battery_monitorThread);
	
	}
	
	//closeConnection(&sock);
	
	close(sock);

	pthread_exit(NULL);

}
*/

