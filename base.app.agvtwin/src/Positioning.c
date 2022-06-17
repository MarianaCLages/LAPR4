#include "geralHeader.h"

int move(info *info)
{
    info->currentPosition = info->route[0];
    info->nextPosition = info->route[1];
    int i = 0;

    while (i < info->routeLength)
    {
        // update velocity
        info->vInfo.x = info->route[i].x - info->currentPosition.x;
        info->vInfo.y = info->route[i].y - info->currentPosition.y;

        sleep(1);

        // update position
        printf("AGV %d: Moving to (%d, %d)\n", info->agvId, info->nextPosition.x, info->nextPosition.y);
        printf("AGV %d: moving %d, %d\n", info->agvId, info->vInfo.x, info->vInfo.y);
        
        i++;
        info->currentPosition = info->nextPosition;
        info->nextPosition = info->route[i];
    }
}
