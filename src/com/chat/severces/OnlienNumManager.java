package com.chat.severces;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Vector;

/**
 * @author 作者 E-mail:
 * @date 创建时间：2018年2月16日 下午5:07:05
 * @version 1.0
 * @parameter
 * @since
 * @return
 */
public class OnlienNumManager {
	private Socket socket;
	private ArrayList<String> list;
	private BufferedReader br;
	private PrintWriter pw;
	private Vector<Socket> clients;

	public OnlienNumManager(Socket s, ArrayList<String> al, Vector<Socket> v) {
		socket = s;
		clients = v;
		new Thread() {
			@Override
			public void run() {
				// TODO 自动生成的方法存根
				if (!al.equals(list)) {
					list = al;
					sendMsg();
				}
			}
		}.start();
	}

	public void sendMsg() {
		if (socket != null) {
			for (int i = 0; i <= clients.size() - 1; i++) {
				try {
					System.out.println(clients.get(i));
					pw = new PrintWriter(clients.get(i).getOutputStream(), true);
					pw.println(list);
					pw.flush();
				} catch (IOException e) {
					// TODO 自动生成的 catch 块
					e.printStackTrace();
				}
			}
		}
	}

}
