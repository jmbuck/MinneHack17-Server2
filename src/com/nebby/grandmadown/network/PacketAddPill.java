package com.nebby.grandmadown.network;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public class PacketAddPill extends Packet
{
	
	private String pillName;
	private String pillDosage;
	private String pillInterval;
	
	public PacketAddPill() {}

	public PacketAddPill(String name, String dosage, String time)
	{
		pillName = name;
		pillDosage = dosage;
		pillInterval = time;
	}
	
	@Override
	public void writeData(DataOutputStream output) throws IOException 
	{
		output.writeUTF(pillName);
		output.writeUTF(pillDosage);
		output.writeUTF(pillInterval);
	}

	@Override
	public void copy(DataInputStream data) throws IOException
	{
		pillName = data.readUTF();
		pillDosage = data.readUTF();
		pillInterval = data.readUTF();
	}

	@Override
	public void handle(Network network)
	{

	}

}
