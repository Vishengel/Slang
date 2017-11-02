import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.File;
import java.io.InputStream;
import java.io.IOException;
import java.util.Properties;

public class SlangOptions {
	public Properties gameOptions;
	public boolean defaultOptions;
	public String config_path;

	public void setOptions() {
		Properties gameOptions = new Properties();
		boolean defaultOptions = false;
		InputStream input = null;
		String config_path = "config.cfg";

		try {
			try {
				input = new FileInputStream(config_path);
				File f = new File(config_path);
				System.out.println("Found config file " + f.getName());			
				gameOptions.load(input);
			} catch(FileNotFoundException nf) {
	   			defaultOptions = true;
	   			System.out.println("defaultOptions == true");
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