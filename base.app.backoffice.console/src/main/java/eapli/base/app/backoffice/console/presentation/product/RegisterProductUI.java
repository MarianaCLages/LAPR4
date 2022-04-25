package eapli.base.app.backoffice.console.presentation.product;

import eapli.framework.presentation.console.AbstractUI;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RegisterProductUI extends AbstractUI {

    private static final Logger LOGGER = LoggerFactory.getLogger(RegisterProductUI.class);

    @Override
    protected boolean doShow() {
        System.out.println("Not yet Implemented!");
        return false;
    }

    @Override
    public String headline() {
        return "Register a Product";
    }
}
