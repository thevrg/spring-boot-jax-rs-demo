package hu.dpc.edu.rest;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by vrg on 2016. 11. 10..
 */
public class Message {
    private int code;
    private String message;
    private String displayName;

    @JsonCreator
    public Message(int code, String message, String displayName) {
        this.code = code;
        this.message = message;
        this.displayName = displayName;
    }

    public int getCode() {
        return code;
    }

    @JsonProperty("uzenet")
    public String getMessage() {
        return message;
    }

    public String getDisplayName() {
        return displayName;
    }
}
