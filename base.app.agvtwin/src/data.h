#ifndef DATA_H
#define DATA_H

typedef struct
{
	//pid_t agvPid;
	int sensorValue;

} sensorAgvInfo;

// position
typedef struct
{
    unsigned short x;
    unsigned short y;
} position;

// velocity
typedef struct
{
    short x;
    short y;
} velocity;

// sensors (8)
typedef struct
{
    unsigned char left;
    unsigned char right;
    unsigned char front;
    unsigned char back;
    unsigned char frontLeft;
    unsigned char frontRight;
    unsigned char backLeft;
    unsigned char backRight;

} sensors;

//AGVS Information

typedef struct
{
    int battery;
    position currentPosition;
    position nextPosition;
    position route[256];
    velocity vInfo;
    sensors sInfo;
    unsigned char routeLength;
} info;

//In order to have multiple AGVS information we have to make a geral structure with a certain capacity

typedef struct
{
	info infoAgvs[256];
	int idAgvs[256];
	int numAgvs;
	int index;
	int ids[256];
	int plant[19][19] ;
	//planta aqui?
} data;

#endif
