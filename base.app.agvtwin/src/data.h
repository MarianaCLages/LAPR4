#ifndef DATA_H
#define DATA_H

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

typedef struct
{
    
    int battery;
    position currentPosition;
    position nextPosition;
    position route[256];
    velocity velocity;
    sensors sensors;
    unsigned char routeLength;

} data;

#endif