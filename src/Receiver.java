import java.io.*;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

public class Receiver {
	public static void main(String[] args) throws InterruptedException {
		while(true) {
			String message = getMessage();
			if(!message.equals("none")) {
				String command = "wget "+message+" -O ../response.mp3";
				executeCommand(command);
				//possibly sleep to let file download
				Thread.sleep(1000);
				Media speech = new Media("response.mp3");
				MediaPlayer mediaPlayer = new MediaPlayer(speech);
				mediaPlayer.play();  
				
			}
			Thread.sleep(1000);
		}
		
		
	}
	
	private static String getMessage() {
		String msg = "";
		
		return msg;
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
