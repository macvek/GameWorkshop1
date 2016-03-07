package gamer;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Arrays;
import java.util.List;

public class UdpClient {
//	public static final String REMOTE = "10.254.22.19";
	public static final String REMOTE = "localhost";
	
	public static void main(String[] args) throws Exception {
		UdpClient client = new UdpClient();
		
		client.map();
//		client.move("a", "P");
//		client.create();
//		client.list();
//		client.apple();
	}
	
	public void map() throws Exception {
		sendTxtAndShowOnOutput(Arrays.asList(Messages.MAP));
	}
	
	public void move(String what, String where) throws Exception {
		sendTxtAndShowOnOutput(Arrays.asList(Messages.MOVE, where));
	}
	
	public void create() throws Exception {
		sendTxtAndShowOnOutput(Arrays.asList(Messages.CREATE));
	}
	
	public void list() throws Exception {
		sendTxtAndShowOnOutput(Arrays.asList(Messages.LIST));
	}
	
	public void apple() throws Exception {
		sendTxtAndShowOnOutput(Arrays.asList(Messages.APPLE));
	}
	
	
	
	
	
	private void sendTxtAndShowOnOutput(List<String> args) throws Exception {

		DatagramSocket socket = new DatagramSocket();

		String msg = concatMsg(args);
		
		// send request
		byte[] buf = msg.getBytes();
		InetAddress address = InetAddress.getByName(REMOTE);
		DatagramPacket packet = new DatagramPacket(buf, buf.length, address, 4445);
		socket.send(packet);
		
		byte[] recvBuf = new byte[1024];
		DatagramPacket received = new DatagramPacket(recvBuf, recvBuf.length);
		socket.receive(received);
		socket.close();
		
		System.out.println("RECEIVED: "+new String(recvBuf,0,received.getLength()));

	}

	private String concatMsg(List<String> args) {
		String msg = new String();
		for (String each : args) {
			msg += each + ";";
		}
		
		return msg.substring(0, msg.length()-1);
		
	}
}
