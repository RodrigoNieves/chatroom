package edu.udacity.java.nano.model;

public class User {
    private String username;
    private String message;
    private String type;

    public User() {}

    public User(String username, String type, String message) {
        this.username = username;
        this.type = type;
        this.message = message;
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
}
