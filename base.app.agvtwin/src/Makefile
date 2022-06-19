runProgram: simulationEngine.o utils.o agv_thread.o monitorStatus_thread.o batteryMonitor_thread.o RoutePlanner.o Positioning.o fixMatrix.o
	gcc -g -Wall simulationEngine.o utils.o agv_thread.o monitorStatus_thread.o batteryMonitor_thread.o RoutePlanner.o Positioning.o fixMatrix.o -o runProgram -lrt -lpthread -lssl -lcrypto
	
Positioning.o: Positioning.c
	gcc -g -Wall -c Positioning.c -o Positioning.o -lrt -lpthread
	
fixMatrix.o: fixMatrix.c
	gcc -g -Wall -c fixMatrix.c -o fixMatrix.o -lrt -lpthread
	
agv_thread.o: agv_thread.c
	gcc -g -Wall -c agv_thread.c -o agv_thread.o -lrt -lpthread
	
RoutePlanner.o: RoutePlanner.c
	gcc -g -Wall -c RoutePlanner.c -o RoutePlanner.o -lrt -lpthread
	
monitorStatus_thread.o: monitorStatus_thread.c
	gcc -g -Wall -c monitorStatus_thread.c -o monitorStatus_thread.o -lrt -lpthread
	
simulationEngine.o: simulationEngine.c
	gcc -g -Wall -c simulationEngine.c -o simulationEngine.o -lrt -lpthread
	
utils.o: utils.c
	gcc -g -Wall -c utils.c -o utils.o -lrt -lpthread
	
batteryMonitor_thread.o : batteryMonitor_thread.c
	gcc -g -Wall -c batteryMonitor_thread.c -o batteryMonitor_thread.o -lrt -lpthread
	
clean:
	rm -f *.o runProgram

run: runProgram
	./runProgram
