package csvCustom;

import java.util.Comparator;

public class ServiceGuidComparer implements Comparator<ServiceGuid> {

	@Override
	public int compare(ServiceGuid o1, ServiceGuid o2) {
		return o1.getRequesTime().compareTo(o2.getRequesTime());
	}
	
	

}
