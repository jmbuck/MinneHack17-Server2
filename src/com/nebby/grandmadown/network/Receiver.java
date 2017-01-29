package com.nebby.grandmadown.network;
import java.io.*;
import java.util.*;
import java.util.concurrent.CountDownLatch;

import javax.swing.SwingUtilities;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.*;
import javafx.embed.swing.JFXPanel; 

public class Receiver {
	public static void main(String[] args) throws InterruptedException {
		//connect();
		
		final CountDownLatch latch = new CountDownLatch(1);
		SwingUtilities.invokeLater(new Runnable() {
		    public void run() {
		        new JFXPanel(); // initializes JavaFX environment
		        latch.countDown();
		    }
		});
		latch.await();
		
		while(true) {
			try
			{
				String url = "http://ec2-54-172-226-18.compute-1.amazonaws.com:8888/bluetooth";

				HttpClient client = HttpClientBuilder.create().build();
				HttpGet request = new HttpGet(url);


				HttpResponse res = client.execute(request);
				String response = EntityUtils.toString(res.getEntity(), "UTF-8");
				if(response.equals("true")) {
					System.out.println("Playing mp3...");
					Media speech = new Media(new File("response.mp3").toURI().toString());
					MediaPlayer mediaPlayer = new MediaPlayer(speech);
					mediaPlayer.play(); 
					Thread.sleep(2000);
				}
				
			}
			catch(Exception e)
			{
			}
			
			Thread.sleep(1000);
		}
	}
	
	/*private static void connect() {
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
	}*/
	
	public static void handleMessage(String text) {
		try {
			System.out.println("Playing mp3...");
			Media speech = new Media(new File("response.mp3").toURI().toString());
			MediaPlayer mediaPlayer = new MediaPlayer(speech);
			mediaPlayer.play(); 
			Thread.sleep(2000);
		}
		catch(Exception e) { 
			System.out.println("Error receiving message");
		}	
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
