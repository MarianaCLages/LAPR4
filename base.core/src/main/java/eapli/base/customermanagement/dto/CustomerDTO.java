package eapli.base.customermanagement.dto;


import eapli.framework.representations.dto.DTO;

@DTO
public class CustomerDTO {

    private String customerName;
    private String vat;
    private String customerEmail;

    public String customerName() {
        return customerName;
    }

    public String vat() {
        return vat;
    }

    public String customerEmail() {
        return customerEmail;
    }

    public String customerAddress() {
        return customerAddress;
    }

    private String customerAddress;

    public CustomerDTO(final String customerName, final String customerEmail,
                       final String vat, final String customerAddress) {

        this.customerName = customerName;
        this.customerEmail = customerEmail;
        this.vat = vat;
        this.customerAddress = customerAddress;
    }

    @Override
    public String toString() {
        return "Customer -> [Name: " + customerName + '\n' +
                "Email: " + customerEmail + '\n' +
                "VAT: " + vat + '\n' +
                "" + customerAddress + ']';
    }

}
