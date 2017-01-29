package com.nebby.grandmadown.network;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public class PacketQueryPills extends Packet
{
	
	private String[] pillNames;
	
	public PacketQueryPills() {}
	
	public PacketQueryPills(String[] names)
	{
		pillNames = names;
	}

	@Override
	public void writeData(DataOutputStream output) throws IOException
	{
		
	}

	@Override
	public void copy(DataInputStream data) throws IOException 
	{
		pillNames = new String[data.readInt()];
		for(int i = 0; i < pillNames.length; i++)
		{
			pillNames[i] = data.readUTF();
		}
	}

	@Override
	public void handle(Network network) 
	{
		((ClientNetwork) network).queryPills(network, pillNames);
	}

}