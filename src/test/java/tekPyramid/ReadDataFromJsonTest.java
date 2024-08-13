package tekPyramid;

import java.io.FileReader;
import java.io.IOException;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class ReadDataFromJsonTest {

	public static void main(String[] args) throws Throwable, IOException, ParseException {
		
		//step1 :parse Json Physical file in to Java object using JsonParse class
		JSONParser parser= new JSONParser();
		//parse is convert the one object to another object.
	    Object obj = parser.parse(new FileReader("C:\\Users\\Ramya Bhukya\\OneDrive\\Desktop\\data\\appCommonData.json"));
		
        //step 2 : convert java object in to JsonObject using down casting
	    JSONObject map = (JSONObject)obj;
	    
	    //step 3 : get the value from json file using key
	    System.out.println(map.get("url"));
	    System.out.println(map.get("browser"));
	    System.out.println(map.get("username"));
	    System.out.println(map.get("password"));
	    System.out.println(map.get("timeout"));
	}

}
