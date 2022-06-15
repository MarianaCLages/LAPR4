simulationEngine: simulationEngine.c
	gcc -Wall simulationEngine.c -o simulationEngine -lrt -lpthread

utils: utils.c
	gcc -Wall utils.c -o utils -lrt -lpthread

run: utils simulationEngine
	./simulationEngine
