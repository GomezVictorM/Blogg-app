package gomez.victor.bloggapp.controllers;

import com.google.gson.Gson;
import gomez.victor.bloggapp.service.ArticleService;
import gomez.victor.bloggapp.service.SocketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.io.IOException;

@Controller
public class SocketController extends TextWebSocketHandler {
    Gson gson = new Gson();

    @Autowired
    ArticleService articleService;

    private SocketService socketService;

    public void setSocketService(SocketService socketService) {
        this.socketService = socketService;
    }

    @Override
    public void handleTextMessage(WebSocketSession session, TextMessage message) throws IOException {
        System.out.println("Received msg: " + message.getPayload());
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) {
        socketService.removeSession(session);
    }

}
