package com.nebby.grandmadown.network;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public class PacketMessage extends Packet{

	String text;
	@Override
	public void writeData(DataOutputStream output) throws IOException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void copy(DataInputStream data) throws IOException {
		// TODO Auto-generated method stub
		text = data.readUTF();
	}

	@Override
	public void handle(Network network)  {
		// TODO Auto-generated method stub
		Receiver.handleMessage(text);
	}
}
