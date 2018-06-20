package pl.oskarpolak.randomchat.models;


import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;

import java.io.IOException;


@Getter
@Setter
public class UserModel {
    private String nickname;
    private WebSocketSession userSession;

    public UserModel(WebSocketSession userSession) {
        this.userSession = userSession;
    }

    public void sendMessage(TextMessage textMessage) throws IOException {
        userSession.sendMessage(textMessage);
    }
}
