#include "geralHeader.h"

int move(info *st) {
    st->currentPosition = st->route[0];
    st->nextPosition = st->route[1];
    int i = 0;

    while (i < st->routeLength) {
        // update velocity
        st->vInfo.x = st->route[i].x - st->currentPosition.x;
        st->vInfo.y = st->route[i].y - st->currentPosition.y;

        sleep(1);

        // update position
        printf("AGV %d: Moving to (%d, %d)\n", st->agvId, st->nextPosition.x, st->nextPosition.y);
        printf("AGV %d: moving %d, %d\n", st->agvId, st->vInfo.x, st->vInfo.y);
        
        i++;
        st->currentPosition = st->nextPosition;
        st->nextPosition = st->route[i];
        
    }
    
    return 0;
}
