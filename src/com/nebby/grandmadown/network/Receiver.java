package com.nebby.grandmadown.network;
import java.io.*;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

public class Receiver {
	public static void main(String[] args) throws InterruptedException {
		connect();
		while(true) {
			
			Thread.sleep(1000);
		}
		
		
	}
	
	private static void connect() {
		ClientNetwork network = new ClientNetwork();
		try
		{
			network.connect("ec2-54-172-226-18.compute-1.amazonaws.com", 8888);
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		
		network.update();
		
		network.validate(true);
	}
	
	public static void handleMessage(String text) {
		try {
			String command = "wget "+text+" -O ../response.mp3";
			executeCommand(command);
			//possibly sleep to let file download
			Thread.sleep(1000);
			Media speech = new Media("response.mp3");
			MediaPlayer mediaPlayer = new MediaPlayer(speech);
			mediaPlayer.play();  
		}
		catch(Exception e) { }	
	}
	
	private static String executeCommand(String command) {

	    StringBuffer output = new StringBuffer();

	    Process p;
	    try {
	        p = Runtime.getRuntime().exec(command);
	        p.waitFor();
	        BufferedReader reader = 
	                        new BufferedReader(new InputStreamReader(p.getInputStream()));

	        String line = "";           
	        while ((line = reader.readLine())!= null) {
	            output.append(line + "\n");
	        }

	    } catch (Exception e) {
	        e.printStackTrace();
	    }

	    return output.toString();
	}
}
