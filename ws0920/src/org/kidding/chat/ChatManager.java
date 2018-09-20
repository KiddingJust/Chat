package org.kidding.chat;

import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;

public enum ChatManager {

	INSTANCE;
	
	//���� ���� ChatAgent�� ������ ��
	Set<ChatAgent> agentSet;
	
	
	//�������̹Ƿ� public �Ұ���
	ChatManager(){
		agentSet = new CopyOnWriteArraySet<>();	//agentset �ʱ�ȭ
	}
	
	public void addAgent(ChatAgent agent) {
		agentSet.add(agent);
	}
	
	public void broadcast(String msg) {
		
		//iterator�� �ݺ���. �ڷᱸ���� ���� ������ ��� �� �ϳ�
		Iterator<ChatAgent> it = agentSet.iterator();
		
		while(it.hasNext()) {
			ChatAgent agent = it.next();
			try {
				agent.sendMsg(msg);			//������ ChatAgent ��ü���� �޼����� ������ �ϴ� ��. sendMsg�� ȣ���ϴ� ��?
			} catch (Exception e) {
				agentSet.remove(agent);
				e.printStackTrace();
			}		
		}
		
	}
	
}
