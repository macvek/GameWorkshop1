package gamer;

import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class UdpServer {
	public static void main(String[] args) throws Exception {
		DatagramSocket socket = new DatagramSocket(4445);
		try {
			byte[] buf = new byte[1024];
		    
			mainloop:
			for(;;) {
				DatagramPacket packet = new DatagramPacket(buf, buf.length);
		    	socket.receive(packet);
		    	
	    		String asString = new String(packet.getData(), 0, packet.getLength());
	    		if (Messages.STOP.equals(asString)) {
	    			break mainloop;
	    		}
	    		if (Messages.PING.equals(asString)) {
	    			DatagramPacket response = new DatagramPacket(Messages.PONG.getBytes(), Messages.PONG.length());
	    			response.setSocketAddress(packet.getSocketAddress());
	    			socket.send(response);
	    		}
	    		else {
	    			DatagramPacket response = new DatagramPacket(Messages.DONTKNOW.getBytes(), Messages.DONTKNOW.length());
	    			response.setSocketAddress(packet.getSocketAddress());
	    			socket.send(response);
	    		}
		    	
			}
		}
		finally {
			socket.close();
		}
	    
	}
}
