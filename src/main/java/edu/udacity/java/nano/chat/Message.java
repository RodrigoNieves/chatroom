package edu.udacity.java.nano.chat;

/**
 * WebSocket message model
 */

public class Message {
    private String username;
    private String message;
    private String type;
    private String onlineCount;

    public Message() {}

    public Message(String username, String message, String type, String onlineCount) {
        this.username = username;
        this.type = type;
        this.message = message;
        this.onlineCount = onlineCount;
    }
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getOnlineCount() {
        return onlineCount;
    }

    public void setOnlineCount(String onlineCount) {
        this.onlineCount = onlineCount;
    }
}
