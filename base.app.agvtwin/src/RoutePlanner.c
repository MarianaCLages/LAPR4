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

void main()
{
    // creates an random plant
    int plant[5][5];
    // fills the plant with 0 and 1
    for (int i = 0; i < 5; i++)
    {
        for (int j = 0; j < 5; j++)
        {
            plant[i][j] = rand() % 2;
        }
    }
    position path[256];
    routePlanner(plant, 5, 5, 0, 0, 4, 4, path);

    // prints the plant
    for (int i = 0; i < 5; i++)
    {
        for (int j = 0; j < 5; j++)
        {
            printf("%d ", plant[i][j]);
        }
        printf("\n");
    }
}

void routePlanner(int *plant, int rows, int columns, int finalX, int finalY, int initialX, int initialY, position *route)
{

    // se a posição inicial for a mesma da posição final, então a rota é vazia
    if (initialX == finalX && initialY == finalY)
    {
        route[0].x = initialX;
        route[0].y = initialY;
        return;
    }

    // se existe um obstáculo na posição inicial, então a rota é vazia
    if (*(plant + initialX * rows + initialY) == 1)
    {
        route[0].x = initialX;
        route[0].y = initialY;
        return;
    }

    // primeira posição da rota é a posição inicial
    route[0].x = initialX;
    route[0].y = initialY;
}

int routePlannerRecursive(int finalX, int finalY, int initialX, int initialY, int *plant, position *route, int index, int rows, int columns)
{
    if (initialX == finalX || initialY == finalY)
    {
        route[index].x = initialX;
        route[index].y = initialY;
        return 0;
    }

    // existe obstáculo
    if (*(plant + initialX * rows + initialY) == 1)
    {
        return 0;
    }

    if (initialX == finalX - 1 && initialY == finalY - 1)
    {
        route[index].x = initialX;
        route[index].y = initialY;
        return 1;
    }

    return routePlannerRecursive(finalX, finalY, initialX + 1, initialY, plant, route, index + 1, rows, columns) +
           routePlannerRecursive(finalX, finalY, initialX, initialY + 1, plant, route, index + 1, rows, columns);
}