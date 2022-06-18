#include "geralHeader.h"

void* position_thread(void *arg) {
	
	info* st = (info*) arg;
	
    st->currentPosition = st->route[0];
    st->nextPosition = st->route[1];
    int i = 0;

    while (i < (st->routeLength)) {
        // Update velocity
        st->vInfo.x = st->route[i].x - st->currentPosition.x;
        st->vInfo.y = st->route[i].y - st->currentPosition.y;

        sleep(1);

        // Update position
        printf("AGV with an ID of: %d: Moving to (%d, %d)\n", st->agvId, st->nextPosition.x, st->nextPosition.y);
        printf("AGV with an ID of: %d: Velocity direction -> (xDirection: %d, yDirection: %d)\n", st->agvId, st->vInfo.x, st->vInfo.y);
        
        //MUTEX AQUI!
        
        if((st->currentPosition.x == st->agvDock.x) && (st->currentPosition.y == st->agvDock.y)) geralPlant[st->currentPosition.y * 19 + st->currentPosition.x] = 0;
        
        i++;
        st->currentPosition = st->nextPosition;
        st->nextPosition = st->route[i];
        
        //MUTEX AQUI!
        geralPlant[st->currentPosition.y * 19 + st->currentPosition.x] = 1;
    
	}
	    
    pthread_exit(NULL);
	
}
