package pt.morla.app;

import java.util.HashMap;
import java.util.List;

public class FindAndReturnTextBetweenChar {

	HashMap<String,String > HASH_KEY_FOUNDER = null;
	List<String> list = null;
	String textSource =null;
	String keyBegin=null;
	String keyEnd=null;


	
	public String setParameters(String textSource, String keyBegin, String keyEnd) {
		this.textSource =textSource;
		this.keyBegin=keyBegin;
		this.keyEnd=keyEnd;
		if (textSource.contains(keyBegin) && textSource.contains(keyEnd)) {
			 return process();
		}
		return null;
	}
	
	private String process() {
		try {
			String href ="";
			char[] charArray = null;
			String[] parts = textSource.split(" ");
			
			for (String str : parts) {
				if (str.contains(keyBegin)) {
			 		charArray = str.toCharArray();
		    		for (
		    				int lastPosition = str.indexOf(keyBegin)+keyBegin.length(); 
		    				(String.valueOf(charArray[lastPosition]).equals(keyEnd)?false:true); 
		    				lastPosition++
		    			) 
		    		{
		    			href +=String.valueOf(charArray[lastPosition]);
		    		}
		    		System.out.println(href);
		    		return href;
		    		
				}
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	
	
	
}
