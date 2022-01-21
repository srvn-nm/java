package org.ce.ap.com.company.server.model;

public class Errortype {
    //we should add our errors here!

    private final int code;
    private final String description;

    public Errortype(int code, String description) {
        this.code = code;
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public int getCode() {
        return code;
    }

    @Override
    public String toString() {
        return code + ": " + description;
    }
}
