package com.nebby.grandmadown.network;

import java.io.IOException;
import java.net.Socket;
import java.security.GeneralSecurityException;
import java.security.KeyFactory;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.X509EncodedKeySpec;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;


public class ClientNetwork extends Network
{

	private boolean cipherInitialized = false;

	public void connect(String serverAddress, int port) throws IOException
	{
		connect(new Socket(serverAddress, port));
	}

	public void validate(boolean establishSecureTunnel)
	{
		super.validate();
		if(establishSecureTunnel)
		{
			sendPacket(new PacketRSA());
			while(!cipherInitialized)
			{
				try 
				{
					Thread.sleep(10);
				} 
				catch (InterruptedException e)
				{
					e.printStackTrace();
				}
			}
		}
	}

	public void createAESKey(byte[] rsaPublicKey, int aesBitLength)
	{
		try
		{
			KeyGenerator keyGen = KeyGenerator.getInstance("AES");
			keyGen.init(aesBitLength);
			SecretKey secretKey = keyGen.generateKey();
			
			encrypt = Cipher.getInstance("AES");
			encrypt.init(Cipher.ENCRYPT_MODE, secretKey);
			decrypt = Cipher.getInstance("AES");
			decrypt.init(Cipher.DECRYPT_MODE, secretKey);
			
			RSAPublicKey publicKey = (RSAPublicKey) KeyFactory.getInstance("RSA").generatePublic(new X509EncodedKeySpec(rsaPublicKey));
			Cipher rsaCipher = Cipher.getInstance("RSA");
			rsaCipher.init(Cipher.ENCRYPT_MODE, publicKey);
			
			sendPacket(new PacketAES(rsaCipher, secretKey));
			cipherInitialized = true;
		}
		catch (GeneralSecurityException e) 
		{
			e.printStackTrace();
		}
	}

}