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
	    		if (Messages.MAP.equals(asString)) {
	    			String responseS = "";
	    			for (int i = 0; i < 7; i++) {
	    				//if (i==0||i==6){
	    					responseS += "x"+"\n";
	    				//}
//	    				for (int j = 0; j < 7; j++) {
//	    					if (j==0||j==6){
//		    					responseS += "x";
//		    				}
//						}
					}
	    			DatagramPacket response = new DatagramPacket(responseS.getBytes(), responseS.length());
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
