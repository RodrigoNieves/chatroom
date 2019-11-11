package edu.udacity.java.nano.chat;

import javax.json.Json;
import javax.websocket.EncodeException;
import javax.websocket.Encoder;
import javax.websocket.EndpointConfig;

public class MessageEncoder implements Encoder.Text<Message> {

    public void init(EndpointConfig config) {
    }

    public void destroy() {
    }

    public String encode(Message message) throws EncodeException {
        return Json.createObjectBuilder()
                .add("username", message.getUsername())
                .add("message", message.getMessage())
                .add("type", message.getType())
                .add("onlineCount", message.getOnlineCount())
                .build().toString();
    }
}