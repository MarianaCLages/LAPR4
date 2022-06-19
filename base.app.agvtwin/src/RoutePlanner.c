// Priority Queue implementation in C inspired by: https://www.programiz.com/dsa/priority-queue
#include "geralHeader.h"

int size = 0;

int rowD[] = {-1, 0, 0, 1};
int colD[] = {0, -1, 1, 0};

// queue
typedef struct
{
    size_t head;
    size_t tail;
    size_t size;
    void **d;
} queue_t;


void *queue_read(queue_t *queue)
{
    if (queue->tail == queue->head)
    {
        return NULL;
    }
    void *handle = queue->d[queue->tail];
    queue->d[queue->tail] = NULL;
    queue->tail = (queue->tail + 1) % queue->size;
    return handle;
}

int queue_write(queue_t *queue, void *handle)
{
    if (((queue->head + 1) % queue->size) == queue->tail)
    {
        return -1;
    }
    queue->d[queue->head] = handle;
    queue->head = (queue->head + 1) % queue->size;
    return 0;
}

// Function that verifies if the queue is empty or not
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
    // Set the visited array
    int *visited = (int *)malloc(row * col * sizeof(int));
    
    for (int i = 0; i < row * col; i++)
    {
        visited[i] = 0;
    }

    // Set the queue
    queue_t *queue = (queue_t *)malloc(sizeof(queue_t));
    queue->head = 0;
    queue->tail = 0;
    queue->size = row * col;
    queue->d = (void **)malloc(row * col * sizeof(void *));

    // Set the initial cell
    cell *initialCell = (cell *)malloc(sizeof(cell));
    initialCell->x = initial.x;
    initialCell->y = initial.y;

    // Add the initial cell to the queue
    queue_write(queue, initialCell);

    // Set the prev array
    cell *prev = (cell *)malloc(row * col * sizeof(cell));

    // While the queue is not empty
    while (!queue_is_empty(queue))
    {
        // Get the cell from the queue
        cell *current = (cell *)queue_read(queue);

        // For all the 4 directions
        for (int i = 0; i < 4; i++)
        {
            // Get the next cell
            int x = current->x + rowD[i];
            int y = current->y + colD[i];

            if (isSafe(matrix, visited, row, col, x, y))
            {
                // Add the next cell to the queue
                cell *nextCell = (cell *)malloc(sizeof(cell));
                nextCell->x = x;
                nextCell->y = y;
                
                queue_write(queue, nextCell);
                
                // Set as visited
                visited[x * col + y] = 1;
                
                // Set the prev cell
                prev[x * col + y].x = current->x;
                prev[x * col + y].y = current->y;
                
            }
        }
    }

    return prev;
}

cell *reconstructPath(cell *prev, int nRows, int nCols, int startX, int startY, int endX, int endY)
{
    cell *path = (cell *)malloc(nRows * nCols * sizeof(cell));
    // Reconstruct the path from the end to the start
    int currentX = endX;
    int currentY = endY;
    int index = 0;

    while (currentX != startX || currentY != startY)
    {
        cell c;
        c.x = currentX;
        c.y = currentY;
        path[index] = c;
        index++;
        int recIndex = currentX * nCols + currentY;
        cell prevCell = prev[recIndex];
        currentX = prevCell.x;
        currentY = prevCell.y;
    }

    // Invert the path
    cell *invertedPath = (cell *)malloc((nRows * nCols) * sizeof(cell));
    int j = 0;

    for (int i = index - 1; i >= 0; i--)
    {
        invertedPath[j] = path[i];
        j++;
    }
    return invertedPath;
}

// Function that calculates the route and returns the route length

int calculateRoute(info *st)
{
	//As mentioned before, there is only 1 warehouse so both of the rows and columns are fixed values
    int nRows = 21;
    int nCols = 19;

	//Reset the Route Size
    size = 0;

	//Initialize the Route Information
    int startX = st->currentPosition.x;
    int startY = st->currentPosition.y;
    int endX = st->destiny.x;
    int endY = st->destiny.y;

    // Prints the initial position and the final position
    printf("\n\nInicial: %d %d\n", startX, startY);
    printf("Final: %d %d\n", endX, endY);
    
    // Creates the cell
    cell initial;
    initial.x = startX;
    initial.y = startY;

    // Print the geral plant
    for (int i = 0; i < nRows; i++)
    {
        for (int j = 0; j < nCols; j++)
        {
            printf("%d ", geralPlant[i * nCols + j]);
        }
        printf("\n");
    }

    cell *prev = bfs(geralPlant, nRows, nCols, initial);

    // Reconstruct the path
    cell *path = reconstructPath(prev, nRows, nCols, startX, startY, endX, endY);
    free(prev);

    // Print the path
    printf("\n\nCaminho: \n");

    int i = 0;
    while (path[i].x != 0 && path[i].y != 0)
    {
        printf("%d %d\n", path[i].x, path[i].y);
        i++;
    }

    // Transform the path to a path with positions
    for (int j = 0; j < i; j++)
    {
        position c;
        c.x = path[j].x;
        c.y = path[j].y;
        st->route[j] = c;
    }

	// Assign the route length and free the dynamically allocated structure
    st->routeLength = i;
    free(path);
    
    return i;
}
