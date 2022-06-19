#ifndef FUNCTIONS_H
#define FUNCTIONS_H

//HEADER FILE THAT EXPOSES THE FUNCTIONS USED IN ANY API

//SIMULATION_API
void* simulation_engine_thread (void *arg);

//SHARED_MEMORY
void open_shared_memory(int *fd, void **p, int size);
void create_shared_memory(int *fd, void **p, int size);

//START_API
void receiveInformationsFromServer();
void sendStatusToServer();
void findIDS(int sock);

//CMD_API
void openSocket(int* sock);
void openSocketTLS(int* sock);
void* agv_thread (void *arg);
void closeConnection(int* sock);
void* batteryMonitor_thread (void *arg);
void resetDp();

//STATUS_API
void* monitorStatus_thread (void *arg);
void* position_thread(void *arg);
void mockRouteToDock(info* st);
int calculateRoute(info *st);
void* sensor_threadFunction (void *arg);

//TEST FUNCTIONS
void mockRoute(info* st);
void mockRouteToDock(info* st);
void mockRoute_2(info* st);
void mockRouteToDock_2(info* st);
void fixMatrix();

#endif
