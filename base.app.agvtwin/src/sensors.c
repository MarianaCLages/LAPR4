#include <errno.h>
#include <fcntl.h>
#include <semaphore.h>
#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <sys/mman.h>
#include <sys/stat.h>
#include <sys/types.h>
#include <sys/wait.h>
#include <time.h>
#include <unistd.h>
#include "data.h"
#include <strings.h>
#include <string.h>
#include <stdlib.h>
#include <unistd.h>
#include <stdio.h>
#include <sys/types.h>
#include <sys/socket.h>
#include <pthread.h>

//SENSORS MODULE

void* sensor_thread (void *arg) {
	
	sensorAgvInfo st = *((sensorAgvInfo*) arg);
	
	if(st.sensorValue == 1) {
		
		//Se a direção do AGV for 1, significa que ele esta neste momento em direção do obstáculo, se for 0, quer dizer que não está na direção desse obstáculo
		
		//Duvidas a tratar
		//Se mandarmos um sinal para o módulo de POSITIONING, como é que sabemos o PID desse processo que está a correr esse módulo?
		//E como é que sabemos o AGV que se trata? O mesmo está a ser passado para este módulo através da estrutura sensorAgvInfo e tem um PID específico
		
		if(st.direction == 1) {
			//PARAR O AGV!
			//mandar o USR1 para o modulo do POSITIONING
		}
	
	} else if(st.sensorValue == 2) {
		if(st.direction == 1) {
			//Reduzir velocidade para metade!
			//mandar o USR2 para o modulo do POSITIONING
		}
	
	}
	
	pthread_exit(NULL);
}
