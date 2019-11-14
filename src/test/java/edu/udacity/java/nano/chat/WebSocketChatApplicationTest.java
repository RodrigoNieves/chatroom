package edu.udacity.java.nano.chat;

import org.hamcrest.core.StringContains;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.socket.client.standard.StandardWebSocketClient;
import org.springframework.web.socket.sockjs.client.SockJsClient;
import org.springframework.web.socket.sockjs.client.Transport;
import org.springframework.web.socket.sockjs.client.WebSocketTransport;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@RunWith(SpringRunner.class)
@WebMvcTest
public class WebSocketChatApplicationTest {

    @Autowired
    private MockMvc mockMvc;

    private Message message;

    @Test
    public void login() throws Exception {
        this.mockMvc.perform(get("/")).andDo(print()).andExpect(status().isOk())
                .andExpect(view().name("login"));
    }

    @Test
    public void userJoin() throws Exception{
        this.mockMvc.perform(
                get("/index?username=Rodrigo"))
                .andDo(print()).andExpect(status().isOk())
                .andExpect(view().name("chat"));
    }

    @Test
    public void chat() throws Exception {
        this.mockMvc.perform(get("/index?username=Rodrigo"))
                .andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(containsString("ws:\\/\\/localhost:8080\\/chat?username=Rodrigo")));
    }

    @Test
    public void leave() throws Exception {
        String exitButton = "<a class=\"mdui-btn mdui-btn-icon\" href=\"/\">\n" +
                "            <i class=\"mdui-icon material-icons\">exit_to_app</i>\n" +
                "        </a>";
        this.mockMvc.perform(get("/index?username=Rodrigo"))
                .andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(containsString(exitButton)));
    }
}