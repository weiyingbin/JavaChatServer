package com.chat.severces;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Vector;

import com.chat.server.ChatServer;

/**
 * @author 作者 E-mail:
 * @date 创建时间：2018年2月16日 上午11:54:30
 * @version 1.0
 * @param <ChatSocket>
 * @parameter
 * @since
 * @return
 */
public class ChatManager {
	private Vector<Socket> vector=new Vector<>();
	private Socket socket;

	public ChatManager(Socket s) {
		socket=s;
		if(s!=null){
			vector.add(s);
		}
	}
	public void sendMsg() {
		private PrintWriter pw;
		if (socket != null) {
			for (int i = vector.size() - 1; i >= 0; i--) {
				try {
					pw = new PrintWriter(vector.get(i).getOutputStream(),
							true);
					pw.println(msg);
					pw.flush();
				} catch (IOException e) {
					// TODO 自动生成的 catch 块
					e.printStackTrace();
				}
			}
		}
	
}
