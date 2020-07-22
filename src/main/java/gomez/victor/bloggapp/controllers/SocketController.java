package gomez.victor.bloggapp.controllers;

import gomez.victor.bloggapp.entities.Socket;
import gomez.victor.bloggapp.service.ArticleService;
import gomez.victor.bloggapp.service.SocketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import javax.websocket.Encoder;
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
