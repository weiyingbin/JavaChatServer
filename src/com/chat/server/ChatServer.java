package com.chat.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Vector;

import com.chat.severces.Login;

/**
 * @author 作者 E-mail:
 * @date 创建时间：2018年2月15日 下午4:53:50
 * @version 1.0
 * @parameter
 * @since
 * @return
 */
public class ChatServer {
	private static ServerSocket ss;
	private Vector<Socket> clients;
	private ArrayList<String> list;
	static {
		try {
			ss = new ServerSocket(8888);
			System.out.println("服务器已启动");
		} catch (IOException e) {
			// TODO 自动生成的 catch 块
			System.out.println("服务器启动失败！");
		}
	}

	public void start() {
		clients = new Vector<Socket>();
		list = new ArrayList<>();
		while (true) {
			Socket client;
			try {
				client = ss.accept();
				new Thread() {
					public void run() {

						try {
							String line = new BufferedReader(
									new InputStreamReader(
											client.getInputStream()))
									.readLine();
							System.out.println(line);
							String[] split = line.split("#");
							String user = split[0];
							boolean login = new Login().verifyLogin(line);
							if (login) {
								clients.add(client);
								list.add(user);
								new PrintStream(client.getOutputStream())
										.println(true);
								ChatSocket chatSocket = new ChatSocket(client,
										user);
								chatSocket.start();

							} else {
								new PrintStream(client.getOutputStream())
										.println(false);
							}
						} catch (IOException e) {
							// TODO 自动生成的 catch 块
							e.printStackTrace();
						}
					}
				}.start();

			} catch (IOException e1) {
				// TODO 自动生成的 catch 块
				e1.printStackTrace();
			}
		}
	}

	class ChatSocket extends Thread {
		Socket socket;
		private BufferedReader br;
		private PrintWriter pw;
		String msg;
		String user;

		public ChatSocket(Socket s, String u) {
			socket = s;
			user = u;
		}

		@Override
		public void run() {
			// TODO 自动生成的方法存根
			try {
				br = new BufferedReader(new InputStreamReader(
						socket.getInputStream()));
				msg = user + "来到聊天室！";
				sendMsg();
				while ((msg = user + "说：" + br.readLine()) != null) {
					System.out.println(msg);
					sendMsg();
				}
			} catch (IOException e) {
				// TODO 自动生成的 catch 块
				e.printStackTrace();
			}
		}

		public void sendMsg() {
			if (socket != null) {
				for (int i = 0; i <= clients.size() - 1; i++) {
					try {
						System.out.println(clients.get(i));
						pw = new PrintWriter(clients.get(i).getOutputStream(),
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
	}

	public static void main(String[] args) {
		new ChatServer().start();
	}
}
