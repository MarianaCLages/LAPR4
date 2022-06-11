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

//SIMULATION ENGINE MODULE

pthread_mutex_t mux;

int main(void) {
	
	//### Shared Memory ###
    data * shm;

    //size of data
    int size = sizeof(data);
	int fd;

	open_shared_memory(&fd, (void **)&shm, size);
	
	//geral information
	int i;
	int nAgvs = shm->numAgvs;
	
	//threads information
	pthread_t threads[size];
	
	for(i = 0 ; i < nAgvs ; i++) {
		pthread_create(&threads[i], NULL, simulation_engine_thread, (void*) &shm);
	
	}
	
	//EMPTY CYCLE
	for(;;){ }
	
	//DEVE SER LANÇADO X THREADS CONSOANTE O NÚMERO DE AGVS A TRABALHAR!
	//FAZER ALGO PARA TERMINAR AS THREADS QUANDO O AGV ACABA A ROUTE!
	
}

//SIMULATION ENGINE THREAD

void* simulation_engine_thread (void *arg) {

	data shm = *((data*) arg);
	
	pthread_mutex_lock(&mux);
	int currIndex = shm.index;
	shm.index++;
	pthread_mutex_unlock(&mux);
	
	for(;;) {
	
		int nx = shm.infoAgvs[currIndex].nextPosition.x;
		int ny = shm.infoAgvs[currIndex].nextPosition.y;
	
		int cx = shm.infoAgvs[currIndex].currentPosition.x;
		int cy = shm.infoAgvs[currIndex].currentPosition.y;
	
		if(shm.plant[nx][ny] == 1) {
	
			//MUDAR VALOR SENSORES
			//TALVEZ SEMPRE QUE É ALTERADO UM VALOR DO SENSOR LANÇAR 1 THREAD, DESSE MESMO SENSOR, UMA THREAD QUE EXECUTE O SENSOR MODULE E QUE VERIFIQUE O SENTIDO/VELOCIDADE DE UM AGV? E SE FOR NECESSÁRIO LANÇAR UM SINAL PARA FAZER UMA NOVA ROUTE?
		
			if(cx == nx) { //CIMA OU BAIXO
				if(cy < ny) shm.infoAgvs[currIndex].sInfo.back = 1; //BAIXO
				else if(cy > ny) shm.infoAgvs[currIndex].sInfo.front = 1; //CIMA
				
				//ESQUERDA DIREIRA
				shm.infoAgvs[currIndex].sInfo.right = 0;
				shm.infoAgvs[currIndex].sInfo.left = 0;
				
				//OBLIQUO
				shm.infoAgvs[currIndex].sInfo.frontRight = 0;
				shm.infoAgvs[currIndex].sInfo.backRight = 0;
				shm.infoAgvs[currIndex].sInfo.frontLeft = 0;
				shm.infoAgvs[currIndex].sInfo.backLeft = 0;
				
				//VERIFICAR QUE A POSIÇÃO CONTINUA OCUPADA!
				shm.plant[cx][cy] = 1;
			
			} else if(cy == ny) { //ESQUERDA OU DIREITA
				if(cx < nx) shm.infoAgvs[currIndex].sInfo.right = 1; //DIREITA
				else if(cx > nx) shm.infoAgvs[currIndex].sInfo.left = 1; //ESQUERDA
				
				//CIMA OU BAIXO
				shm.infoAgvs[currIndex].sInfo.back = 0;
				shm.infoAgvs[currIndex].sInfo.front = 0;
				
				//OBLIQUO
				shm.infoAgvs[currIndex].sInfo.frontRight = 0;
				shm.infoAgvs[currIndex].sInfo.backRight = 0;
				shm.infoAgvs[currIndex].sInfo.frontLeft = 0;
				shm.infoAgvs[currIndex].sInfo.backLeft = 0;
				
				//VERIFICAR QUE A POSIÇÃO CONTINUA OCUPADA!
				shm.plant[cx][cy] = 1;
			
			} else if(cy != ny && cx != nx){ //OBLIQUO
			
				if(cx < nx && cy > ny) shm.infoAgvs[currIndex].sInfo.frontRight = 1; //CIMA DIREITA
				else if(cx < nx && cy < ny) shm.infoAgvs[currIndex].sInfo.backRight = 1; //BAIXO DIREITA
				else if(cx > nx && cy > ny) shm.infoAgvs[currIndex].sInfo.frontLeft = 1; //CIMA ESQUERDA
				else if(cx > nx && cy < ny) shm.infoAgvs[currIndex].sInfo.backLeft = 1; //BAIXO ESQUERDA
		
				//ESQUERDA DIREIRA
				shm.infoAgvs[currIndex].sInfo.right = 0;
				shm.infoAgvs[currIndex].sInfo.left = 0;
				
				//CIMA OU BAIXO
				shm.infoAgvs[currIndex].sInfo.back = 0;
				shm.infoAgvs[currIndex].sInfo.front = 0;
				
				//VERIFICAR QUE A POSIÇÃO CONTINUA OCUPADA!
				shm.plant[cx][cy] = 1;
				
			}
		
		}else if(shm.plant[nx + 1][ny] == 1 && ((nx + 1) != cx)) { 
				
			//LANÇAR ALGO PARA REDUZIR A VELOCIDADE CASO A DIREÇÃO DO AGV SEJA NO SENTIDO DESTE OBSTÁCULO
				
			if(ny == cy) shm.infoAgvs[currIndex].sInfo.right = 2;
			else if(ny < cy) shm.infoAgvs[currIndex].sInfo.frontRight = 2;
			else if(ny > cy) shm.infoAgvs[currIndex].sInfo.backRight = 2;
			
			//ALTERAR VALOR DA PLANTA PARA INFORMAR QUE AQUELA POSIÇÃO ESTA OCUPADA!
			
			shm.plant[nx][ny] = 1;
			shm.plant[cx][cy] = 0;
				
		} else if(shm.plant[nx - 1][ny] == 1 && ((nx - 1) != cx)) { 
				
			//LANÇAR ALGO PARA REDUZIR A VELOCIDADE CASO A DIREÇÃO DO AGV SEJA NO SENTIDO DESTE OBSTÁCULO
				
			if(ny == cy) shm.infoAgvs[currIndex].sInfo.left = 2;
			else if(ny < cy) shm.infoAgvs[currIndex].sInfo.frontLeft = 2;
			else if(ny > cy) shm.infoAgvs[currIndex].sInfo.backLeft = 2;
			
			//ALTERAR VALOR DA PLANTA PARA INFORMAR QUE AQUELA POSIÇÃO ESTA OCUPADA!
			
			shm.plant[nx][ny] = 1;
			shm.plant[cx][cy] = 0;
				
		} else if(shm.plant[nx][ny - 1] == 1 && ((ny - 1) != cy)) { 
				
			//LANÇAR ALGO PARA REDUZIR A VELOCIDADE CASO A DIREÇÃO DO AGV SEJA NO SENTIDO DESTE OBSTÁCULO
				
			if(nx == cx) shm.infoAgvs[currIndex].sInfo.front = 2;
			
			//ALTERAR VALOR DA PLANTA PARA INFORMAR QUE AQUELA POSIÇÃO ESTA OCUPADA!
			
			shm.plant[nx][ny] = 1;
			shm.plant[cx][cy] = 0;
							
		} else if(shm.plant[nx][ny + 1] == 1 && ((ny + 1) != cy)) { 
				
			//LANÇAR ALGO PARA REDUZIR A VELOCIDADE CASO A DIREÇÃO DO AGV SEJA NO SENTIDO DESTE OBSTÁCULO
				
			if(nx == cx) shm.infoAgvs[currIndex].sInfo.back = 2;
			
			//ALTERAR VALOR DA PLANTA PARA INFORMAR QUE AQUELA POSIÇÃO ESTA OCUPADA!
			
			shm.plant[nx][ny] = 1;
			shm.plant[cx][cy] = 0;
								
		} 
		//DORMIR 100MS 
		usleep(100);
	}

}

// função que cria a memoria partilhada e o apontador para a mesma

void open_shared_memory(int *fd, void **p, int size) {
    // cria memoria partilhada e trata o erro
    if ((*fd = shm_open(SHM_NAME, O_RDWR, 0666)) == -1)
    {
        perror("erro ao criar memoria partilhada");
        exit(1);
    }

    // atribui o tamanho da memoria partilhada
    if (ftruncate(*fd, size) == -1)
    {
        perror("erro ao atribuir tamanho da memoria partilhada");
        exit(1);
    }

    // mapeia a memoria partilhada e trata o erro
    *p = mmap(NULL, size, PROT_READ | PROT_WRITE, MAP_SHARED, *fd, 0);
    if (*p == MAP_FAILED)
    {
        perror("erro ao mapear memoria partilhada");
        exit(1);
    }
}
