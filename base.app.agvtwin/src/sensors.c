#include "geralHeader.h"

//SENSORS MODULE

void* sensor_threadFunction (void *arg) {
	
	sensorAlert* var = (sensorAlert*) arg;
	
	if(var->sensorValue == 1) {
		//Se a direção do AGV for 1, significa que ele esta neste momento em direção do obstáculo, se for 0, quer dizer que não está na direção desse obstáculo
		
		if(var->directionValue == 1) {
			//Stop the AGV
			var->st->secsSleep = 2;
		}
	
	} else if(var->sensorValue == 2) {
		if(var->directionValue == 1) {
			//Reduce Velocity to half
			var->st->secsSleep = 2;
		}
	
	}
	
	pthread_exit(NULL);
}
