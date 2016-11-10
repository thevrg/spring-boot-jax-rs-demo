package hu.dpc.edu.rest;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 * Created by vrg on 2016. 11. 10..
 */
@XmlRootElement
public class Message {
    private int code;
    private String message;
    private String displayName;

    public Message() {
    }

    @JsonCreator
    public Message(int code, String message, String displayName) {
        this.code = code;
        this.message = message;
        this.displayName = displayName;
    }

    @XmlTransient
    public int getCode() {
        return code;
    }

    @JsonProperty("uzenet")
    @XmlElement(name = "uzenet")
    public String getMessage() {
        return message;
    }

    @XmlElement
    public String getDisplayName() {
        return displayName;
    }
}
