package Data;


import java.io.FileReader;
import java.io.IOException;

import org.openqa.selenium.json.JsonException;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;


public class JSON {
	public JsonParser parser = new JsonParser();
	public Object obj = null;
	
     public JSON(String jsonFile) throws JsonException, JsonException, IOException {
    		obj = parser.parse(new FileReader(jsonFile));
     }
     
     public JsonArray GetArray(String varProperty) {
    	 JsonObject jsonObject = (JsonObject) obj;
    	 JsonArray propertyValue = jsonObject.get(varProperty).getAsJsonArray();
    	 
		return propertyValue;
     }
     
     public String GetValue(String varProperty) {
    	 JsonObject jsonObject = (JsonObject) obj;
    	 String propertyValue = jsonObject.get(varProperty).getAsString();
    	 
    	 return propertyValue;
     }
}


	
