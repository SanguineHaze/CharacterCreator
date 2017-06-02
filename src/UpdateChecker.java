import java.io.InputStream;
import java.net.URL;

public class UpdateChecker{
    
	private final static String versionURL = "http://programs.hazegaming.com/npcgenstatus";
	private final static String historyURL = "http://programs.hazegaming.com/npcgenstatus";
	
	public static String getLatestVersion() throws Exception{
		String data = getData(versionURL);
		return data.substring(data.indexOf("[version]")+9,data.indexOf("[/version]")); //+9 because [version] is 9 characters
	}
	
	public static String getWhatsNew() throws Exception{
		String data = getData(historyURL);
		return data.substring(data.indexOf("[history]")+9, data.indexOf("[/history]")); // [history] is also 9 characters
	}
	
	private static String getData(String address) throws Exception{
		URL url = new URL(address);
		InputStream html = null;
		html = url.openStream();
		
		int c = 0;
		StringBuffer buffer = new StringBuffer("");
		
		while(c != -1){ //indexOf will return -1 if it finds nothing
			c = html.read();
			buffer.append((char)c);
		}
		return buffer.toString();
	}
}
