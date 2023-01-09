package org.biletado.personal.v1.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;

@JsonTypeName("message")
public class Message {

    @JsonProperty("message")
    String message = "";

    public Message(String message) {
        this.message = message;
    }
}
