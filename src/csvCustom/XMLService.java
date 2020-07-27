package csvCustom;

import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.xml.parsers.DocumentBuilderFactory;  
import javax.xml.parsers.DocumentBuilder;  
import org.w3c.dom.Document;  
import org.w3c.dom.NodeList;  
import org.w3c.dom.Node;  
import org.w3c.dom.Element;  

public class XMLService implements IService {
	
	private String filePath;
	private String rootName;
	private List<ServiceGuid> services = new ArrayList<ServiceGuid>();
	
	public XMLService(String filePath, String rootName) {
		this.filePath = filePath;
		this.rootName = rootName;
	}

	@Override
	public List<ServiceGuid> read() 
	{
		services = new ArrayList<ServiceGuid>();
		try   
		{ 		
			File file = new File(filePath);  
			//an instance of factory that gives a document builder  
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();  
			//an instance of builder to parse the specified xml file  
			DocumentBuilder db = dbf.newDocumentBuilder();  
			Document doc = db.parse(file);  
			doc.getDocumentElement().normalize();  
			
			NodeList nodeList = doc.getElementsByTagName(rootName);  
			// nodeList is not iterable, so we are using for loop  
			for (int itr = 0; itr < nodeList.getLength(); itr++)   
			{  
				Node node = nodeList.item(itr);  
				
				if (node.getNodeType() == Node.ELEMENT_NODE)   
				{  
					Element eElement = (Element) node; 
					ServiceGuid service = new ServiceGuid();
					
					service.setClientAddress(eElement.getElementsByTagName(Property.ClientAddress).item(0).getTextContent());
					service.setClientGuid(eElement.getElementsByTagName(Property.ClientGuid).item(0).getTextContent());
					service.setServiceGuid(eElement.getElementsByTagName(Property.ServiceGuid).item(0).getTextContent());
					
					service.setPacketsRequested(Integer.parseInt(eElement.getElementsByTagName(Property.PacketsRequested).item(0).getTextContent()));
					service.setPacketsServiced(Integer.parseInt(eElement.getElementsByTagName(Property.PacketsServiced).item(0).getTextContent()));
					service.setRetriesRequest(Integer.parseInt(eElement.getElementsByTagName(Property.RetriesRequest).item(0).getTextContent()));
					service.setMaxHoleSize(Integer.parseInt(eElement.getElementsByTagName(Property.MaxHoleSize).item(0).getTextContent()));
					
					
					
					DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
					Date date = format.parse(eElement.getElementsByTagName(Property.RequestTime).item(0).getTextContent());
					service.setRequesTime(date);
					
								
					
					services.add(service);
				}  
			}  
		}   
		catch (Exception e)   
		{  
			e.printStackTrace();  
		}  
		
		return services;
				
	}

	@Override
	public void write(List<ServiceGuid> services) {
		// TODO Auto-generated method stub
		
		
	}

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}
	
	@Override
	public void summary() {
		
		System.out.println("XML File elements : "+services.size());
	}
	
	@Override
	public int size() {
		// TODO Auto-generated method stub
		return services.size();
	}


}
