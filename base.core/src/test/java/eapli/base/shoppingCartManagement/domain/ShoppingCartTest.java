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

class ShoppingCartTest {

    private Email email1 = new Email("ola@gmail.com");
    private Email email2 = new Email("sadas@gmail.com");
    private Gender gender1 = new Gender("Male");
    private Gender gender2 = new Gender("Female");
    private BirthDate birthDate1 = new BirthDate(new Date("11/1/2001"));
    private BirthDate birthDate2 = new BirthDate(new Date("2/2/2002"));
    private Name name2 = new Name("Tiago Ferreira");
    private Name name3 = new Name("Ambrosio dos Brosios");
    private VAT VAT1 = new VAT(2);
    private VAT VAT2 = new VAT(3);
    private PhoneNumber phoneNumber1 = new PhoneNumber(123, 123456789);
    private PhoneNumber phoneNumber2 = new PhoneNumber(333, 111456789);
    private Address address1 = new Address("aa", 11, "aa", "aa", "aa");
    private Address address2 = new Address("aa", 12, "bb", "cc", "dd");

    private Customer customer1 = new Customer(phoneNumber1, birthDate1, name2, gender1, VAT1, email1, address1);
    private Customer customer2 = new Customer(phoneNumber2, birthDate2, name3, gender2, VAT2, email2, address2);

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

    private Product buildProductWithoutProductionCode() {
        return new ProductBuilder().withACategoryId(CATEGORY_ID).coded(CODE).withAShortDescription(SHORT_DESCRIPTION).withAnExtendedDescription(EXTENDED_DESCRIPTION).withATechnicalDescription(TECHNICAL_DESCRIPTION).withABrandName(BRAND_NAME).withAReference(REFERENCE).withABarcode(BARCODE).withAPrice(PRICE).withASetOfPhotos(PHOTO_LIST).build();
    }

    private Product buildProductWithProductionCode() {
        return new ProductBuilder().withACategoryId(CATEGORY_ID).coded(CODE).withAShortDescription(SHORT_DESCRIPTION).withAnExtendedDescription(EXTENDED_DESCRIPTION).withATechnicalDescription(TECHNICAL_DESCRIPTION).withABrandName(BRAND_NAME).withAReference(REFERENCE).withABarcode(BARCODE).withAPrice(PRICE).withASetOfPhotos(PHOTO_LIST).withAProductionCode(PRODUCTION_CODE).build();
    }

    private Customer buildCustomer1() {
        return new Customer(phoneNumber1, birthDate1, name2, gender1, VAT1, email1, address1);
    }

    private Customer buildCustomer2() {
        return new Customer(phoneNumber2, birthDate2, name3, gender2, VAT2, email2, address2);
    }

    @Test
    void ensureCanBuildProductWithoutOnlyProductionCode() {
        try {
            buildProductWithoutProductionCode();
            assertTrue(true);
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    void ensureCanBuildProduct() {
        try {
            buildProductWithProductionCode();
            assertTrue(true);
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    void ensureCanBuildCustomer() {
        try {
            buildCustomer1();
            buildCustomer2();
            assertTrue(true);
        } catch (Exception e) {
            fail();
        }
    }




}