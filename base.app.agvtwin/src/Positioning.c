#include "geralHeader.h"

//POSITIONING MODULE

void* position_thread(void *arg) {
	
	info* st = (info*) arg;
	
	//GETS AGV INITIAL INFORMATION
    st->currentPosition = st->route[0];
    st->nextPosition = st->route[1];
    int i = 0;
    
    //Starts the value by default
    st->secsSleep = 1;

    while (i < (st->routeLength)) {
        // Update velocity
        st->vInfo.x = st->route[i].x - st->currentPosition.x;
        st->vInfo.y = st->route[i].y - st->currentPosition.y;

        sleep(st->secsSleep);

        // Update position (THIS PRINTS SERVE AS A LOG)
        printf("AGV with an ID of: %d: Moving to (%d, %d)\n", st->agvId, st->nextPosition.x, st->nextPosition.y);
        printf("AGV with an ID of: %d: Velocity direction -> (xDirection: %d, yDirection: %d)\n", st->agvId, st->vInfo.x, st->vInfo.y);
        
        // Update the current position to 0
        geralPlant[st->currentPosition.y * 19 + st->currentPosition.x] = 0;
        
        i++;
        st->currentPosition = st->nextPosition;
        st->nextPosition = st->route[i];
        
		//UPDATES THE PLANT, IN ORDER TO ALERT OTHER THREADS THAT THE CURRENT POSITION OF THE AGV IS IN THIS INDEX
        geralPlant[st->currentPosition.y * 19 + st->currentPosition.x] = 1;
    
	}
	
    pthread_exit(NULL);
	
}
