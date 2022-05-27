package eapli.base.shoppingCartManagement.dto;

import eapli.base.shoppingCartManagement.domain.ShoppingCartLine;
import eapli.framework.representations.dto.DTO;

import java.util.List;

@DTO
public class ShoppingCartDto {

    private String customerName;
    private List<ShoppingCartLine> list;

    public ShoppingCartDto(final String customerName, final List<ShoppingCartLine> list) {
        this.customerName = customerName;
        this.list = list;
    }


    @Override
    public String toString() {
        return "ShoppingCart -> [" + "\n" +
                "Customer Name : " + customerName + "\n" +
                "Shopping Cart Lines -> " + list +
                ']';
    }
}
