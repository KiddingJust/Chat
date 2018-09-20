package org.kidding.chat;

import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

import lombok.extern.log4j.Log4j;


@ServerEndpoint("/chat")
@Log4j
public class ChatAgent {

	private Session session;
	private String userIP;
	
	@OnOpen
	public void open(Session session) {
		
		log.info("Successfully Connecting.......BAAAAAM!");
		log.info(session);
		this.session = session;
		
		userIP = session.getUserProperties().get("javax.websocket.endpoint.remoteAddress").toString();
		
		log.info(this);		//this는 ChatAgent
		ChatManager.INSTANCE.broadcast(userIP + "a user connected..........");
		ChatManager.INSTANCE.addAgent(this);	// 객체 생성되면, ChatManager의 Set<ChatAgent>에 추가
	}
	
	@OnClose
	public void close() {
		
		log.info("a user disconnected..................");
		ChatManager.INSTANCE.broadcast("a user disconnected..........");

	}
	
    @OnMessage
    public void onMessage(String msg)throws Throwable {			//브라우저로부터 메세지를 받는 상황
        log.info("message.........." + msg);
        ChatManager.INSTANCE.broadcast(msg);
    }

	public void sendMsg(String msg)throws Exception {
        session.getBasicRemote().sendText(userIP + ": " + msg);

	}
	
}
