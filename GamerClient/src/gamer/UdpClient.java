package gamer;

import java.awt.Button;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Arrays;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

public class UdpClient {
	public static final String REMOTE = "10.254.22.19";
	
	public static void main(String[] args) throws Exception {
		final UdpClient client = new UdpClient();
		
//		client.map();
//		client.move("a", "P");
//		client.create();
//		client.list();
//		client.apple();
//		
		
		JFrame frame = new JFrame();
		frame.setSize(400, 400);	
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		frame.setVisible(true);
		
		JPanel listPane = new JPanel();
		listPane.setLayout(new BoxLayout(listPane, BoxLayout.PAGE_AXIS));
		
		Button map = new Button("MAP");
		map.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					client.map();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
		});
		listPane.add(map);
		Button create = new Button("CREATE");
		create.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					client.create();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
		});
		listPane.add(create);
		Button list = new Button("LIST");
		list.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					client.list();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
		});
		listPane.add(list);
		Button apple = new Button("APPLE");
		apple.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					client.apple();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
		});
		listPane.add(apple);
		
		Button move = new Button("MOVE");
		listPane.add(move);
		
		TextField moveFirst = new TextField();
		listPane.add(moveFirst);
		TextField moveSecond = new TextField();
		listPane.add(moveSecond);
		
		
		move.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					client.move(moveFirst.getText().trim(), moveSecond.getText().trim());
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		
		frame.add(listPane);
				
	}
	
	public void map() throws Exception {
		sendTxtAndShowOnOutput(Arrays.asList(Messages.MAP));
	}
	
	public void move(String what, String where) throws Exception {
		sendTxtAndShowOnOutput(Arrays.asList(Messages.MOVE, what, where));
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
