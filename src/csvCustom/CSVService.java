package csvCustom;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class CSVService implements IService {

private String filePath;
private List<ServiceGuid> services = new ArrayList<ServiceGuid>();
	
	public CSVService(String filePath) {
		this.setFilePath(filePath);
	}
	
	@Override
	public void write(List<ServiceGuid> services) {
		FileWriter fileWriter = null;
		try 
		{
			fileWriter = new FileWriter(filePath);

			fileWriter.append(Property.listProperties);

			for(ServiceGuid service: services) 		   
			{
				fileWriter.append(String.valueOf(service.getClientAddress()));
				fileWriter.append(",");
				fileWriter.append(service.getClientGuid());
				fileWriter.append(",");
				
							
				DateTimeFormatter f = DateTimeFormatter.ofPattern( "EEE MMM d HH:mm:ss zzz uuuu" , Locale.US);
				DateTimeFormatter f1 = DateTimeFormatter.ofPattern( "yyyy-MM-dd HH:mm:ss");
				
				ZonedDateTime zdt = ZonedDateTime.parse(service.getRequesTime().toString(), f);
				
				fileWriter.append(zdt.format(f1));
				
				
				fileWriter.append(",");
				fileWriter.append(service.getServiceGuid());
				fileWriter.append(",");
				fileWriter.append(Integer.toString(service.getRetriesRequest()));
				fileWriter.append(",");
				fileWriter.append(Integer.toString(service.getPacketsRequested()));
				fileWriter.append(",");
				fileWriter.append(Integer.toString(service.getPacketsServiced()));
				fileWriter.append(",");
				fileWriter.append(Integer.toString(service.getMaxHoleSize()));  	    
				fileWriter.append("\n");
			}
		} 
		catch (Exception ex)
		{
			ex.printStackTrace();
		} 
		finally 
		{
			try 
			{
				fileWriter.flush();
				fileWriter.close();
			}
			catch (Exception e) 
			{
				e.printStackTrace();
			}
		}
	}



	@Override
	public List<ServiceGuid> read() {
		BufferedReader reader = null;
		services = new ArrayList<ServiceGuid>();

		try 
		{

			String line = "";
			reader = new BufferedReader(new FileReader(filePath));
			reader.readLine();

			while((line = reader.readLine()) != null) {
				String[] fields = line.split(",");

				if(fields.length > 0) {
					ServiceGuid service = new ServiceGuid();
					service.setClientAddress(fields[0]);
					service.setClientGuid(fields[1]);
					
					DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
					Date date = format.parse(fields[2]);
					service.setRequesTime(date);
					
					
					service.setServiceGuid(fields[3]);
					
					service.setRetriesRequest(Integer.parseInt(fields[4]));
					service.setPacketsRequested(Integer.parseInt(fields[5]));
					service.setPacketsServiced(Integer.parseInt(fields[6]));
					service.setMaxHoleSize(Integer.parseInt(fields[7]));

					services.add(service);
				}

			} 
		} 
		catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			try {
				reader.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		return services;


	}

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	@Override
	public void summary() {
		
		System.out.println("CSV File elements : "+services.size());
	}

	@Override
	public int size() {
		// TODO Auto-generated method stub
		return services.size();
	}

}
