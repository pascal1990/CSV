package csvCustom;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;




 
public class Main 
{
   
    public static void main(String[] args) 
    {
    	List<ServiceGuid> results = new ArrayList<ServiceGuid>();
    	
    	List<IService> AllService = new ArrayList<IService>();
    	
    	AllService.add(new XMLService("src/csvCustom/report.xml","report"));
    	AllService.add(new JsonService("src/csvCustom/report.json"));
    	AllService.add(new CSVService("src/csvCustom/report.csv"));
    	
    	int totalServices = 0;
    	
    	for(IService servicesList : AllService) {    		
    		List<ServiceGuid> services = servicesList.read();
    		servicesList.summary();
    		totalServices += servicesList.size();    		
    		for(ServiceGuid temp : services) {    			
    			if(temp.getPacketsRequested() != 0) {
    				results.add(temp);
    			}    			
    		}
    		
    	}
    	
    	System.out.println("ALL File elements : "+totalServices);
    	
    	CSVService SR = new CSVService("src/csvCustom/result.csv");
    	
    	Collections.sort(results,new ServiceGuidComparer());;
    	
    	SR.write(results);
    	
    	
        
    }
    
       
}