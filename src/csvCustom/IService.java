package csvCustom;

import java.util.List;

public interface IService {

	List<ServiceGuid> read();

	void write (List<ServiceGuid> services);
	
	void summary();
	
	int size();

}