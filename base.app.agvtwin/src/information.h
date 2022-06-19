#ifndef INFORMATION_H
#define INFORMATION_H

//MACROS
#define BUF_SIZE sizeof(info)

// read a string from stdin protecting buffer overflow
#define GETS(B,S) {fgets(B,S-2,stdin);B[strlen(B)-1]=0;}

#define SHM_NAME "SCOMP_LEI_SHM"
#define IPSERVER "vsgate-s2.dei.isep.ipp.pt"
#define MAX 80
#define PORT "10639"
#define SA struct sockaddr
#define BUF_SIZE 60

int eleSize;
int array[256];

#define SERVER_SSL_CERT_FILE "digitalTwinServer_J.pem"

#endif
