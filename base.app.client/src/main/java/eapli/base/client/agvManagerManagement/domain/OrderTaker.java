package eapli.base.client.agvManagerManagement.domain;


import eapli.base.warehousemanagement.application.binservice.MoveProductToAnotherBinService;

//This Class Will Eventually be moved
public class OrderTaker implements Runnable {

    private int option;
    private int option2;

    public OrderTaker(int option2, int option) {
        this.option = option;
        this.option2 = option2;
    }

    private MoveProductToAnotherBinService moveProductToAnotherBinService = new MoveProductToAnotherBinService();


    @Override
    public void run() {

        synchronized (moveProductToAnotherBinService) {
            moveProductToAnotherBinService.moveProductToAnotherBinService(option, option2);
        }
    }
}
