import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.io.File;

public class SlangOptions {
	
	Properties gameOptions;
	String defaultOptions;

	public void setOptions() {
		Properties gameOptions = new Properties();
		InputStream input = null;
		String defaultOptions = "";
		
		try {
			input = new FileInputStream("config.cfg");
			File f = new File("config.cfg");
		
			if(f.exists()) {
				gameOptions.load(input);
				System.out.println("Found config file " + f.getName());
				// Test code below:
	   			System.out.println(gameOptions.getProperty("enableViewer"));
				System.out.println(gameOptions.getProperty("logSteps"));
				System.out.println(gameOptions.getProperty("inputType"));
				//return gameOptions;
				System.out.println("Now returning gameOptions");
	   		} else {
	   			defaultOptions = "test string";
	   			// return defaultOptions;
	   			System.out.println("Now returning defaultOptions");
	   		}
							
		} catch (IOException ex) {
			ex.printStackTrace();
		} finally {
			if(input != null) {
				try {
					input.close();
				} catch (IOException ex) {
					ex.printStackTrace();
				}
			}
		}
	}
}