package eapli.base.warehousemanagement.domain;

import java.util.Arrays;
import java.util.Optional;

public enum Accessibility {
    WIDTH_MINUS("w-"), WIDTH_PLUS("w+"), LENGHT_MINUS("l-"), LENGHT_PLUS("l+");

    private final String code;

    Accessibility(String code) {
        this.code = code;
    }

    public String code() {
        return this.code;
    }

    // reverse lookup from code to enum
    public static Optional<Accessibility> get(String url) {
        return Arrays.stream(Accessibility.values())
                .filter(env -> env.code.equals(url))
                .findFirst();
    }
}

