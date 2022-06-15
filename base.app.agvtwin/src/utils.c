#include "geralHeader.h"

int* openSocket() {
	//### Connection ###
	int err, sock;
	struct addrinfo  req, *list;
	
	bzero((char *)&req,sizeof(req));
	// let getaddrinfo set the family depending on the supplied server address
	req.ai_family = AF_UNSPEC;
	req.ai_socktype = SOCK_STREAM;
	err=getaddrinfo(IPSERVER, PORT , &req, &list);

	if(err) {
		printf("Failed to get server address, error: %s\n",gai_strerror(err)); 
		exit(EXIT_FAILURE); 
		
	}

	sock = socket(list->ai_family,list->ai_socktype,list->ai_protocol);
	
	if(sock == -1) {
		perror("Failed to open socket"); 
		freeaddrinfo(list); 
		exit(EXIT_FAILURE);
		
	}

	//Connection
	if(connect(sock,(struct sockaddr *)list->ai_addr, list->ai_addrlen) != 0) {
		perror("Failed connect"); 
		freeaddrinfo(list); 
		close(sock); 
		exit(EXIT_FAILURE);
		
	}
	
	exit(&sock);

}

// função que cria a memoria partilhada e o apontador para a mesma

void create_shared_memory(int *fd, void **p, int size) {
    // Cria memoria partilhada e trata o erro
    if ((*fd = shm_open(MEMORIE_NAME, O_RDWR | O_CREAT, 0666)) == -1) {
        perror("erro ao criar memoria partilhada");
        exit(EXIT_FAILURE);
        
    }

    // Atribui o tamanho da memoria partilhada
    if (ftruncate(*fd, size) == -1) {
        perror("erro ao atribuir tamanho da memoria partilhada");
        exit(EXIT_FAILURE);
        
    }

    // Mapeia a memoria partilhada e trata o erro
    *p = mmap(NULL, size, PROT_READ | PROT_WRITE, MAP_SHARED, *fd, 0);
    
    if (*p == MAP_FAILED) {
        perror("erro ao mapear memoria partilhada");
        exit(EXIT_FAILURE);
        
    }
    
}


// Função que abre a memoria partilhada e o apontador para a mesma

void open_shared_memory(int *fd, void **p, int size) {
    // Abre memoria partilhada e trata do erro
    if ((*fd = shm_open(SHM_NAME, O_RDWR, 0666)) == -1) {
        perror("erro ao criar memoria partilhada");
        exit(EXIT_FAILURE);
        
    }

    // Atribui o tamanho da memoria partilhada
    if (ftruncate(*fd, size) == -1) {
        perror("erro ao atribuir tamanho da memoria partilhada");
        exit(EXIT_FAILURE);
        
    }

    // Mapeia a memoria partilhada e trata o erro
    *p = mmap(NULL, size, PROT_READ | PROT_WRITE, MAP_SHARED, *fd, 0);
    
    if (*p == MAP_FAILED) {
        perror("erro ao mapear memoria partilhada");
        exit(EXIT_FAILURE);
        
    }
    
}
