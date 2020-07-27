package csvCustom;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class JsonService implements IService 
{
	
private String filePath;
private List<ServiceGuid> services = new ArrayList<ServiceGuid>();
	
	public JsonService(String filePath) {
		this.setFilePath(filePath);
	}


	@Override
	public List<ServiceGuid> read() 
	{
		//JSON parser object to parse read file
		JSONParser jsonParser = new JSONParser();

		services = new ArrayList<ServiceGuid>();

		try (FileReader reader = new FileReader(filePath))
		{
			//Read JSON file
			Object obj = jsonParser.parse(reader);

			JSONArray serviceList = (JSONArray) obj;

			//Iterate over services array
			serviceList.forEach( emp -> services.add(parseEmployeeObject( (JSONObject) emp )) );



		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		}

		return services;
	}

	@Override
	public void write(List<ServiceGuid> services) {
		// TODO Auto-generated method stub

	}

	private static ServiceGuid parseEmployeeObject(JSONObject services) 
	{
		ServiceGuid service = new ServiceGuid();

		//Get service object within list
		JSONObject serviceObject = (JSONObject) services;


		service.setClientGuid((String)serviceObject.get(Property.ClientGuid)); 
		service.setClientAddress((String)serviceObject.get(Property.ClientAddress));
		service.setPacketsRequested((int)(long)serviceObject.get(Property.PacketsRequested));
		service.setPacketsServiced((int)(long)serviceObject.get(Property.PacketsServiced));
		
		Long date = (Long)serviceObject.get(Property.RequestTime);
		service.setRequesTime(new Date(date));
		
		service.setRetriesRequest((int)(long)serviceObject.get(Property.RetriesRequest));
		service.setServiceGuid((String)serviceObject.get(Property.ServiceGuid));
		service.setMaxHoleSize((int)(long)serviceObject.get(Property.MaxHoleSize));


		return service;
	}


	public String getFilePath() {
		return filePath;
	}


	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}
	
	@Override
	public void summary() {
		
		System.out.println("JSON File elements : "+services.size());
	}
	
	@Override
	public int size() {
		// TODO Auto-generated method stub
		return services.size();
	}


}
