#ifndef FUNCTIONS_H
#define FUNCTIONS_H

void* simulation_engine_thread (void *arg);
void open_shared_memory(int *fd, void **p, int size);
void receiveInformationsFromServer();
void create_shared_memory(int *fd, void **p, int size);
void receiveInformationsFromServer(int opt);
void findIDS(int sock);

#endif
