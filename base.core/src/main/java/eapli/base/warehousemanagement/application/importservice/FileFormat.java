package eapli.base.warehousemanagement.application.importservice;

public enum FileFormat {
    CSV("csv"),
    XML("xml"),
    JSON("json");

    private final String extension;

    FileFormat(String extension) {
        this.extension = extension;
    }

    // reverse lookup from code to enum
    public static FileFormat fromString(String extension) {
        for (FileFormat f : FileFormat.values()) {
            if (f.extension.equalsIgnoreCase(extension)) {
                return f;
            }
        }
        return null;
    }
}
