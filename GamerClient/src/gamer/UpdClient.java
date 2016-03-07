package gamer;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class UpdClient {
	public static void main(String[] args) throws Exception {
		DatagramSocket socket = new DatagramSocket();

		// send request
		byte[] buf = Messages.PING.getBytes();
		InetAddress address = InetAddress.getByName("10.254.22.19");
		DatagramPacket packet = new DatagramPacket(buf, buf.length, address, 4445);
		socket.send(packet);
		
		byte[] recvBuf = new byte[1024];
		DatagramPacket received = new DatagramPacket(recvBuf, packet.getLength());
		socket.receive(received);
		socket.close();
		
		System.out.println(new String(received.getData()));
	}
}
