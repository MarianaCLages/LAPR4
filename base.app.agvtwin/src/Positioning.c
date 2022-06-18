#include "geralHeader.h"

void* position_thread(void *arg) {
	
	info* st = (info*) arg;
	
    st->currentPosition = st->route[0];
    st->nextPosition = st->route[1];
    int i = 0;

    while (i < (st->routeLength)) {
        // update velocity
        st->vInfo.x = st->route[i].x - st->currentPosition.x;
        st->vInfo.y = st->route[i].y - st->currentPosition.y;

        sleep(1);

        // update position
        printf("AGV with an %d: Moving to (%d, %d)\n", st->agvId, st->nextPosition.x, st->nextPosition.y);
        printf("AGV with an %d: Velocity direction -> (xDirection: %d, yDirection: %d)\n", st->agvId, st->vInfo.x, st->vInfo.y);
        
        i++;
        st->currentPosition = st->nextPosition;
        st->nextPosition = st->route[i];
        
    }
    
    pthread_exit(NULL);
}
