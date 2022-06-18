#include "geralHeader.h"

void* batteryMonitor_thread (void *arg) {

	info* st = (info*) arg;
	
	int battBefore = 0;
	int battAfter = 0;
	
	for(;;){
		
		sleep(4);
		
		if(st->currentPosition.x == st->agvDock.x && st->currentPosition.y == st->agvDock.y){
			if(battBefore != 100) {
				battBefore = st->battery;
		
				battAfter = battBefore + 1;
		
				st->battery = battAfter;
				printf("\nAGV Recharging!\nThe Battery of the AGV ID: %d has been updated!\nBattery -> (Before: %d, After: %d)\n\n",st->agvId,battBefore,battAfter);
			
			} else {
				printf("\nAGV Battery Full!\n\n");
			}
					
		} else {
			battBefore = st->battery;
		
			battAfter = battBefore - 1;
		
			st->battery = battAfter;
			printf("\nAGV Moving!\nThe Battery of the AGV ID: %d has been updated!\nBattery -> (Before: %d, After: %d)\n\n",st->agvId,battBefore,battAfter);
	
			
		}
			
	}

	pthread_exit(NULL);

}
