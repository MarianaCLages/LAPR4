// Priority Queue implementation in C inspired by: https://www.programiz.com/dsa/priority-queue
#include "geralHeader.h"

int size = 0;

int rowD[] = {-1, 0, 0, 1};
int colD[] = {0, -1, 1, 0};

typedef struct
{
    size_t head;
    size_t tail;
    size_t size;
    void **data;
} queue_t;

void *queue_read(queue_t *queue)
{
    if (queue->tail == queue->head)
    {
        return NULL;
    }
    void *handle = queue->data[queue->tail];
    queue->data[queue->tail] = NULL;
    queue->tail = (queue->tail + 1) % queue->size;
    return handle;
}

int queue_write(queue_t *queue, void *handle)
{
    if (((queue->head + 1) % queue->size) == queue->tail)
    {
        return -1;
    }
    queue->data[queue->head] = handle;
    queue->head = (queue->head + 1) % queue->size;
    return 0;
}

// the queue empty
int queue_is_empty(queue_t *queue)
{
    return queue->tail == queue->head;
}

int isSafe(int *matrix, int *visited, int row, int col, int x, int y)
{
    return (x >= 0 && x < row && y >= 0 && y < col && matrix[x * col + y] == 0 && visited[x * col + y] == 0);
}

cell *bfs(int *matrix, int row, int col, cell initial)
{
    // set the visited array
    int *visited = (int *)malloc(row * col * sizeof(int));
    for (int i = 0; i < row * col; i++)
    {
        visited[i] = 0;
    }

    // set the queue
    queue_t *queue = (queue_t *)malloc(sizeof(queue_t));
    queue->head = 0;
    queue->tail = 0;
    queue->size = row * col;
    queue->data = (void **)malloc(row * col * sizeof(void *));

    // set the initial cell
    cell *initialCell = (cell *)malloc(sizeof(cell));
    initialCell->x = initial.x;
    initialCell->y = initial.y;

    // add the initial cell to the queue
    queue_write(queue, initialCell);

    // set the prev array
    cell *prev = (cell *)malloc(row * col * sizeof(cell));

    // while the queue is not empty
    while (!queue_is_empty(queue))
    {
        // get the cell from the queue
        cell *current = (cell *)queue_read(queue);

        // for all the 4 directions
        for (int i = 0; i < 4; i++)
        {
            // get the next cell
            int x = current->x + rowD[i];
            int y = current->y + colD[i];

            if (isSafe(matrix, visited, row, col, x, y))
            {
                // add the next cell to the queue
                cell *nextCell = (cell *)malloc(sizeof(cell));
                nextCell->x = x;
                nextCell->y = y;
                queue_write(queue, nextCell);
                // set as visited
                visited[x * col + y] = 1;
                // set the prev cell
                prev[x * col + y].x = current->x;
                prev[x * col + y].y = current->y;
            }
        }
    }
    return prev;
}

cell *reconstructPath(cell *prev, int nRows, int nCols, int startX, int startY, int endX, int endY)
{
    cell *path = (cell *)malloc((nRows * nCols) * sizeof(cell));
    // reconstruct the path from the end to the start
    int currentX = endX;
    int currentY = endY;
    int index = 0;
    while (currentX != startX || currentY != startY)
    {
        cell *cell;
        cell->x = currentX;
        cell->y = currentY;
        path[index] = *cell;
        index++;
        int recIndex = currentX * nCols + currentY;
        currentX = prev[recIndex].x;
        currentY = prev[recIndex].y;
    }

    // invert the path
    cell *invertedPath = (cell *)malloc((nRows * nCols) * sizeof(cell));
    int j = 0;
    for (int i = index - 1; i >= 0; i--)
    {
        invertedPath[j] = path[i];
        j++;
    }

    return invertedPath;
}

position *findShortestPath(int *matrix, int *visited, int rows, int column, int x, int y, int i, int j, int minDist, int distance, position *path)
{

    if (i == x && j == y)
    {
        if (distance < minDist)
        {
            minDist = distance;
            return path;
        }
    }
    // printf("\n\n\n\nNova posição: %d %d %d\n", i, j, distance);

    // set as visited
    visited[x * column + y] = 1;

    // go to the bottom position
    if (isSafe(matrix, visited, rows, column, x, y - 1))
    {
        // add to the path
        position c;
        c.x = i;
        c.y = j;
        path[distance] = c;
        printf("\n\n\n\nNova posição: %d %d %d\n", i, j, distance);

        return findShortestPath(matrix, visited, rows, column, x, y - 1, i, j, minDist, distance + 1, path);
    }
    // go to the right position
    if (isSafe(matrix, visited, rows, column, x + 1, y))
    {
        // add to the path
        position c;
        c.x = i;
        c.y = j;
        path[distance] = c;
        return findShortestPath(matrix, visited, rows, column, x + 1, y, i, j, minDist, distance + 1, path);
    }
    // go to the top position
    if (isSafe(matrix, visited, rows, column, x, y + 1))
    {
        // add to the path
        position c;
        c.x = i;
        c.y = j;
        path[distance] = c;
        return findShortestPath(matrix, visited, rows, column, x, y + 1, i, j, minDist, distance + 1, path);
    }
    // go to the left position
    if (isSafe(matrix, visited, rows, column, x - 1, y))
    {
        // add to the path
        position c;
        c.x = i;
        c.y = j;
        path[distance] = c;
        return findShortestPath(matrix, visited, rows, column, x - 1, y, i, j, minDist, distance + 1, path);
    }

    // backtrack: remove (i, j) from the visited matrix and path
    // remove from the path

    visited[x * column + y] = 0;
}

int calculateRoute(info *st)
{
    int nRows = 21;
    int nCols = 19;

    int startX = st->currentPosition.x;
    int startY = st->currentPosition.y;
    int endX = st->destiny.x;
    int endY = st->destiny.y;

    // prints the initial position and the final position
    printf("\n\n\n\nInicial: %d %d\n", startX, startY);
    printf("Final: %d %d\n", endX, endY);
    // creates the cell
    cell initial;
    initial.x = startX;
    initial.y = startY;

    // print the geralPlant
    for (int i = 0; i < nRows; i++)
    {
        for (int j = 0; j < nCols; j++)
        {
            printf("%d ", geralPlant[i * nCols + j]);
        }
        printf("\n");
    }

    cell *prev = bfs(geralPlant, nRows, nCols, initial);

    // reconstruct the path
    cell *path = reconstructPath(prev, nRows, nCols, startX, startY, endX, endY);

    // print the path
    printf("\n\n\n\nCaminho: \n");

    int i = 0;
    while (path[i].x != 0 && path[i].y != 0)
    {
        printf("%d %d\n", path[i].x, path[i].y);
        i++;
    }

    return 0;
}
