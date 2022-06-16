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
#include <limits.h>

position route[15];

// A structure to represent a queue
struct Queue
{
    int front, rear, size;
    unsigned capacity;
    position *array;
};

// function to create a queue
// of given capacity.
// It initializes size of queue as 0
struct Queue *_createQueue(unsigned capacity)
{
    struct Queue *queue = (struct Queue *)malloc(
        sizeof(struct Queue));
    queue->capacity = capacity;
    queue->front = queue->size = 0;

    // This is important, see the enqueue
    queue->rear = capacity - 1;
    queue->array = (int *)malloc(
        queue->capacity * sizeof(int));
    return queue;
}

// Queue is full when size becomes
// equal to the capacity
int isFull(struct Queue *queue)
{
    return (queue->size == queue->capacity);
}

// Queue is empty when size is 0
int isEmpty(struct Queue *queue)
{
    if (queue->size == 0)
        return 1;
    else
        return 0;
}

// Function to add an item to the queue.
// It changes rear and size
void enqueue(struct Queue *queue, position item)
{
    if (isFull(queue))
        return;
    queue->rear = (queue->rear + 1) % queue->capacity;
    queue->array[queue->rear] = item;
    queue->size = queue->size + 1;
   // printf("%d enqueued to queue\n", item);
}

// Function to remove an item from queue.
// It changes front and size
position _dequeue(struct Queue *queue)
{
    if (isEmpty(queue))
    {
        perror("Queue is empty");
    }
    position item = queue->array[queue->front];
    queue->front = (queue->front + 1) % queue->capacity;
    queue->size = queue->size - 1;
    return item;
}

// Function to get front of queue
position front(struct Queue *queue)
{
    if (isEmpty(queue))
    {
        perror("Queue is empty");
    }
    return queue->array[queue->front];
}

int isSafe(int i, int j, int *matrix, int rows, int columns)
{
    if (i < 0 || i >= rows || j < 0 || j >= columns)
    {
        return 0;
    }
    return 1;
}


// BFS to find the path
void getPath(int **matrix, int initialX, int initialY, int rows, int columns, int destX, int destY, position *route)
{
    struct Queue *q = _createQueue(rows * columns);
    int *visited = (int *)malloc(rows * columns * sizeof(int));
    // mark all the cells as unvisited
    for (int i = 0; i < rows * columns; i++)
    {
        visited[i] = 0;
    }
    printf("afgsgd");
    // get the initial position
    position initial;
    initial.x = initialX;
    initial.y = initialY;
    enqueue(q, initial);

    while (isEmpty(q) == 0)
    {
        // get the front cell
        // printf("heyyy");
        position p = _dequeue(q);

        if (p.x == destX && p.y == destY)
        {
            break;
        }

        // move up
        if (isSafe(p.x - 1, p.y, matrix, rows, columns) && matrix[p.x - 1][p.y] != 0 && !visited[(p.x - 1) * columns + p.y])
        {
            printf("moved up\n");
            visited[(p.x - 1) * columns + p.y] = 1;
            // gets the position of the cell
            route[p.x * columns + p.y].x = p.x - 1;
            route[p.x * columns + p.y].y = p.y;
            enqueue(q, route[p.x * columns + p.y]);
        }

        // move left
        if (isSafe(p.x, p.y - 1, matrix, rows, columns) && matrix[p.x][p.y - 1] != 0 && !visited[p.x * columns + p.y - 1])
        {
            printf("moved left\n");

            visited[p.x * columns + p.y - 1] = 1;
            // gets the position of the cell
            route[p.x * columns + p.y].x = p.x;
            route[p.x * columns + p.y].y = p.y - 1;
            enqueue(q, route[p.x * columns + p.y]);
        }

        // move down
        if (isSafe(p.x + 1, p.y, matrix, rows, columns) && matrix[p.x + 1][p.y] != 0 && !visited[(p.x + 1) * columns + p.y])
        {

            printf("moved down\n");

            visited[(p.x + 1) * columns + p.y] = 1;
            // gets the position of the cell
            route[p.x * columns + p.y].x = p.x + 1;
            route[p.x * columns + p.y].y = p.y;
            enqueue(q, route[p.x * columns + p.y]);
        }

        // move right
        if (isSafe(p.x, p.y + 1, matrix, rows, columns) && matrix[p.x][p.y + 1] != 0 && !visited[p.x * columns + p.y + 1])
        {
            printf("moved right\n");

            visited[p.x * columns + p.y + 1] = 1;
            // gets the position of the cell
            route[p.x * columns + p.y].x = p.x;
            route[p.x * columns + p.y].y = p.y + 1;
            enqueue(q, route[p.x * columns + p.y]);
        }
    }
}

// Driver program to
// check above function
int main()
{
    int rows = 4;
    int columns = 4;
    int matrix[4][4] = {
        {{0, 3, 0, 1},
         {3, 0, 3, 3},
         {2, 3, 3, 3},
         {0, 3, 3, 3}}};

    // calling isPath method
    printf("Following is the shortest path from source to destination\n");
    // clears the route
    for (int i = 0; i < 10; i++)
    {
        route[i].x = 0;
        route[i].y = 0;
    }

    int visited[4][4];
    int initialX = 0;
    int initialY = 0;
    int destX = 3;
    int destY = 3;

    getPath(matrix, initialX, initialY, rows, columns, destX, destY, route);

    // prints the route
    for (int i = 0; i < rows * columns; i++)
    {
        printf("(%d, %d) ", route[i].x, route[i].y);
    }
    return 0;
}