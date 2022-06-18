#include "geralHeader.h"

void* batteryMonitor_thread (void *arg) {

	info* st = (info*) arg;
	
	int battBefore = 0;
	int battAfter = 0;
	
	for(;;){
		sleep(4);
		
		battBefore = st->battery;
		
		battAfter = battBefore - 1;
		
		st->battery = battAfter;
		printf("\nThe Battery of the AGV ID: %d has been updated!\nBattery -> (Before: %d, After: %d)\n\n",st->agvId,battBefore,battAfter);
	
	}

	pthread_exit(NULL);

}
