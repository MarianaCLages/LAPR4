package eapli.base.shoppingCartManagement.domain;

import eapli.base.customermanagement.domain.*;
import eapli.base.productmanagement.domain.*;
import eapli.framework.general.domain.model.Description;
import eapli.framework.general.domain.model.Money;
import org.junit.jupiter.api.Test;
import org.springframework.security.core.parameters.P;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class ShoppingCartTest {

    private Email email1 = new Email("ola@gmail.com");
    private Gender gender1 = new Gender("Male");
    private BirthDate birthDate1 = new BirthDate(new Date("11/1/2001"));
    private Name name2 = new Name("Tiago Ferreira");
    private VAT VAT1 = new VAT(2);
    private PhoneNumber phoneNumber1 = new PhoneNumber(123, 123456789);
    private Address address1 = new Address("aa", 11, "aa", "aa", "aa");

    private static final Long CATEGORY_ID = 123456L;
    private static final Code CODE = Code.valueOf("P0001");
    private static final Description SHORT_DESCRIPTION = Description.valueOf("Test short description");
    private static final Description EXTENDED_DESCRIPTION = Description.valueOf("Test extended description");
    private static final Description TECHNICAL_DESCRIPTION = Description.valueOf("Test technical description");
    private static final BrandName BRAND_NAME = BrandName.valueOf("Test brand name");
    private static final Reference REFERENCE = Reference.valueOf("Teste reference");
    private static final Barcode BARCODE = Barcode.valueOf(1L);
    private static final Money PRICE = Money.valueOf("10 EUR");
    private static final List<Photo> PHOTO_LIST = new ArrayList<>();
    private static final ProductionCode PRODUCTION_CODE = ProductionCode.valueOf("PC1");

    private Product buildProductWithProductionCode() {
        return new ProductBuilder().withACategoryId(CATEGORY_ID).coded(CODE).withAShortDescription(SHORT_DESCRIPTION).withAnExtendedDescription(EXTENDED_DESCRIPTION).withATechnicalDescription(TECHNICAL_DESCRIPTION).withABrandName(BRAND_NAME).withAReference(REFERENCE).withABarcode(BARCODE).withAPrice(PRICE).withASetOfPhotos(PHOTO_LIST).withAProductionCode(PRODUCTION_CODE).build();
    }

    private Customer buildCustomer1() {
        return new Customer(phoneNumber1, birthDate1, name2, gender1, VAT1, email1, address1);
    }

    private List<ShoppingCartLine> buildShoppingCartList() {
        List<ShoppingCartLine> list = new ArrayList<>();
        list.add(new ShoppingCartLine(buildProductWithProductionCode(), new ShoppingCartLineProductQuantity(1)));
        return list;
    }

    private ShoppingCart buildShoppingCart() {
        return new ShoppingCart(buildCustomer1(), buildShoppingCartList());
    }

    private ShoppingCart buildShoppingCartWithoutProducts() {
        return new ShoppingCart(buildCustomer1(), new ArrayList<>());
    }

    private ShoppingCart buildInvalidShoppingCartWithoutCustomer() {
        return new ShoppingCart(null, buildShoppingCartList());
    }

    private ShoppingCart buildInvalidShoppingCartWithoutShoppingCartLine() {
        return new ShoppingCart(buildCustomer1(), null);
    }

    private ShoppingCartLine buildInvalidShoppingCartLineWithoutProduct() {
        return new ShoppingCartLine(null, new ShoppingCartLineProductQuantity(1));
    }

    private ShoppingCartLine buildInvalidShoppingCartLineWithoutQuantity() {
        return new ShoppingCartLine(buildProductWithProductionCode(), null);
    }

    @Test
    void ensureShoppingCartCanBeCorrectlyBuilt() {
        try {
            ShoppingCart sp = buildShoppingCart();
            assertNotNull(sp);
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    void ensureShoppingCartMustHaveCustomer() {
        assertThrows(IllegalArgumentException.class, () -> buildInvalidShoppingCartWithoutCustomer());
    }

    @Test
    public void ensureShoppingCartMustHaveShoppingCartLines() {
        assertThrows(IllegalArgumentException.class, () -> buildInvalidShoppingCartWithoutShoppingCartLine());
    }

    @Test
    public void ensureTheShoppingCartCanAddLines() {
        List<ShoppingCartLine> list = buildShoppingCartList();
        ShoppingCart shoppingCart = buildShoppingCartWithoutProducts();

        shoppingCart.updateShoppingCartLine(list.get(0));

        if (shoppingCart.verifyShoppingCartLines()) {
            assertNotNull(shoppingCart);
        } else {
            fail();
        }
    }


    @Test
    public void ensureShoppingCartLineMustHaveACustomer() {
        assertThrows(IllegalArgumentException.class, () -> buildInvalidShoppingCartLineWithoutProduct());
    }

    @Test
    public void ensureShoppingCartLineMustHaveAValidQuantity() {
        assertThrows(IllegalArgumentException.class, () -> buildInvalidShoppingCartLineWithoutQuantity());
    }

}