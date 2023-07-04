package com.team4.backend.handler;

import com.team4.backend.model.User;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.BinaryMessage;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.AbstractWebSocketHandler;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Component
public class WebSocketHandler extends AbstractWebSocketHandler {
    List<WebSocketSession> sessions = new ArrayList<>();
    Map<String,WebSocketSession> userSessions = new LinkedHashMap<>();
    //클라이언트가 서버에 접속 성공했을 때
    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        System.out.println("afterConnectionEstablished: " + session);
        sessions.add(session);
        String senderId = getId(session);
        userSessions.put(senderId,session);
    }
    // 소켓에 메세지를 보냈을 때
    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        System.out.println("handleTextMessage: " + session + " : " + message);
        String senderId = getId(session);
//        for(WebSocketSession sess : sessions){
//            sess.sendMessage(new TextMessage(senderId + ": "+ message.getPayload()));
//        }
        //protocol: 댓글작성자
    }

    //jwt토큰을 추출해서 값 구현하게 변경해야함
    private String getId(WebSocketSession session) {
//        Map<String,Object> httpSession = session.getAttributes();
//        User loginUser = (User)httpSession.get(SessionNames.LOGIN);
//        if (null == loginUser)
//            return session.getId();
//        else
//            return loginUser.getUID();
        return null;
    }

    // 소켓이 closed 됬을 때
    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        System.out.println("afterConnectionClosed: " + session + " : " + status);
    }
    @Override
    protected void handleBinaryMessage(WebSocketSession session, BinaryMessage message) throws Exception {
        System.out.println("handleBinaryMessage: " + session + " : " + message);
    }
}
