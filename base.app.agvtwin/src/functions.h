#ifndef FUNCTIONS_H
#define FUNCTIONS_H

//SIMULATION_ENGINE_THREAD
void* simulation_engine_thread (void *arg);

//SHARED_MEMORY
void open_shared_memory(int *fd, void **p, int size);
void create_shared_memory(int *fd, void **p, int size);
void fixMatrix();

//START_API
void receiveInformationsFromServer();
void sendStatusToServer();
void findIDS(int sock);

//CMD_API
void openSocket(int* sock);
void* agv_thread (void *arg);
void closeConnection(int* sock);
void* batteryMonitor_thread (void *arg);
int calculateRoute(info *st);
void* position_thread(void *arg);
void resetDp();

//STATUS_API
void* monitorStatus_thread (void *arg);
void mockRoute(info* st);

#endif
