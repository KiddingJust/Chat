package org.kidding.chat;

import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;

public enum ChatManager {

	INSTANCE;
	
	//여러 개의 ChatAgent를 가져야 함
	Set<ChatAgent> agentSet;
	
	
	//생성자이므로 public 불가능
	ChatManager(){
		agentSet = new CopyOnWriteArraySet<>();	//agentset 초기화
	}
	
	public void addAgent(ChatAgent agent) {
		agentSet.add(agent);
	}
	
	public void broadcast(String msg) {
		
		//iterator는 반복자. 자료구조의 루프 돌리는 방법 중 하나
		Iterator<ChatAgent> it = agentSet.iterator();
		
		while(it.hasNext()) {
			ChatAgent agent = it.next();
			try {
				agent.sendMsg(msg);			//각각의 ChatAgent 객체에게 메세지를 보내라 하는 것. sendMsg를 호출하는 것?
			} catch (Exception e) {
				agentSet.remove(agent);
				e.printStackTrace();
			}		
		}
		
	}
	
}
