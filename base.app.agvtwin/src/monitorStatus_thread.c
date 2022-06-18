#include "geralHeader.h"

void* monitorStatus_thread(void *arg) {
	
	info* st = (info*) arg;
	
	char clientMessage[5] = {0,0,0,0,0};
	
	for(;;){
	
		sleep(1);
		
		//SEND AGV STATUS
			
		//X POSITION
		clientMessage[4] = st->currentPosition.x;
		send(*(st->sockt),&clientMessage,sizeof(clientMessage),0);
		
		//Y POSITION
		clientMessage[4] = st->currentPosition.y;
		send(*(st->sockt),&clientMessage,sizeof(clientMessage),0);
		
		//Velocity X
		clientMessage[4] = st->vInfo.x;
		send(*(st->sockt),&clientMessage,sizeof(clientMessage),0);
		
		//Velocity Y
		clientMessage[4] = st->vInfo.y;
		send(*(st->sockt),&clientMessage,sizeof(clientMessage),0);
		
		//Battery
		clientMessage[4] = st->battery;
		send(*(st->sockt),&clientMessage,sizeof(clientMessage),0);
		
		//Agv ID
		clientMessage[4] = st->agvId;
		send(*(st->sockt),&clientMessage,sizeof(clientMessage),0);
		
		printf("\nThe AGV with an ID of: %d, sent to the Server his Information!\nX position: %d,Y position: %d,Velocity X: %d,Velocity Y: %d,Battery: %d\n\n",st->agvId,st->currentPosition.x,st->currentPosition.y,st->vInfo.x,st->vInfo.y,st->battery);
	
	}
	
	pthread_exit(NULL);
	
}
