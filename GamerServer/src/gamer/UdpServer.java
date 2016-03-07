package gamer;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class UdpServer {
	public static void main(String[] args) throws Exception {
		DatagramSocket socket = new DatagramSocket(4445);
		try {
			byte[] buf = new byte[1024];
			Gred gred = new Gred(5);

			mainloop: for (;;) {
				DatagramPacket packet = new DatagramPacket(buf, buf.length);

				socket.receive(packet);

				String asString = new String(packet.getData(), 0, packet.getLength());
				System.out.println("SERVER RECEIVED:" + asString);
				if (Messages.STOP.equals(asString)) {
					break mainloop;
				}
				if (Messages.PING.equals(asString)) {
					sendResponse(Messages.PONG, socket, packet);
				}
				if (Messages.MAP.equals(asString)) {
					sendResponse(gred.getMap(), socket, packet);
				} else if (Messages.CREATE.equals(asString)) {
					sendResponse(gred.create(), socket, packet);
				} else if (Messages.APPLE.equals(asString)) {
					sendResponse(gred.apple(), socket, packet);
				} else if (Messages.LIST.equals(asString)) {
					sendResponse(gred.list(), socket, packet);
				} else if (asString.startsWith(Messages.MOVE)) {
					String[] moveArgs = asString.split(";");
					if (moveArgs.length != 3) {
						failedToComply(socket, packet);
					} else {
						String what = moveArgs[1];
						String where = moveArgs[2];
						sendResponse(gred.move(what, where), socket, packet);
					}

				} else {
					failedToComply(socket, packet);
				}

			}
		} finally {
			socket.close();
		}

	}

	private static void sendResponse(String msg, DatagramSocket socket, DatagramPacket packet) throws IOException {
		DatagramPacket response = new DatagramPacket(msg.getBytes(), msg.length());
		response.setSocketAddress(packet.getSocketAddress());
		socket.send(response);
	}

	private static void failedToComply(DatagramSocket socket, DatagramPacket packet) throws IOException {
		DatagramPacket response = new DatagramPacket(Messages.DONTKNOW.getBytes(), Messages.DONTKNOW.length());
		response.setSocketAddress(packet.getSocketAddress());
		socket.send(response);
	}
}
