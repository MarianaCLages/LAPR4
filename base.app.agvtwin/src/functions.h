#ifndef FUNCTIONS_H
#define FUNCTIONS_H

void* simulation_engine_thread (void *arg);
void open_shared_memory(int *fd, void **p, int size);

#endif
