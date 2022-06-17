#include "geralHeader.h"

void* batteryMonitor_thread (void *arg) {

	info st = *((info*) arg);
	
	for(;;){
		sleep(4);
		st.battery--;
		printf("REDUZI A BATERIA XD\n");
	
	}

	pthread_exit(NULL);

}
