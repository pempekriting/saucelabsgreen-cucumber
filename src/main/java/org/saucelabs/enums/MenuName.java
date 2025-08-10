package org.saucelabs.enums;

public enum MenuName {
    CATALOG("Catalog"),
    LOGIN("Log In"),
    LOGOUT("Log Out");

    private final String catalog;

    MenuName(String catalog) {
        this.catalog = catalog;
    }

    public String getCatalog() {
        return catalog;
    }
}
