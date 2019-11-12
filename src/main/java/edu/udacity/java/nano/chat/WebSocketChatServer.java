package edu.udacity.java.nano.chat;

import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * WebSocket Server
 *
 * @see ServerEndpoint WebSocket Client
 * @see Session   WebSocket Session
 */

@Component
@ServerEndpoint(value = "/chat", encoders={MessageEncoder.class})
public class WebSocketChatServer {

    private static Logger logger = LoggerFactory.getLogger(WebSocketChatServer.class);
    /**
     * All chat sessions.
     */
    private static Map<String, Session> onlineSessions = new ConcurrentHashMap<>();

    private static void sendMessageToAll(String username, String msg) {
        for (Map.Entry<String, Session> entry: onlineSessions.entrySet()) {
            try {
                logger.info(entry.getValue().toString());
                logger.info(entry.getValue().getUserProperties().toString());
                entry.getValue().getBasicRemote().sendObject(
                        new Message(
                                username,
                                msg,
                                "SPEAK",
                                Integer.toString(onlineSessions.size())));
            } catch (IOException | EncodeException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Open connection, 1) add session, 2) add user.
     */
    @OnOpen
    public void onOpen(Session session) {
        String username = session.getRequestParameterMap().get("username").get(0);
        onlineSessions.put(username, session);
    }

    /**
     * Send message, 1) get username and session, 2) send message to all.
     */
    @OnMessage
    public void onMessage(Session session, String jsonStr) {
        try {
            JSONObject jsonObject = new JSONObject(jsonStr);
            String username = jsonObject.getString("username");
            String msg = jsonObject.getString("msg");
            sendMessageToAll(username, msg);
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
    }

    /**
     * Close connection, 1) remove session, 2) update user.
     */
    @OnClose
    public void onClose(Session session) {
        String username = session.getRequestParameterMap().get("username").get(0);
        if (onlineSessions.containsKey(username)) {
            onlineSessions.remove(username);
        }
    }

    /**
     * Print exception.
     */
    @OnError
    public void onError(Session session, Throwable error) {
        error.printStackTrace();
    }

}
