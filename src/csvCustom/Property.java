package csvCustom;

public final class Property {
	
	static String ClientAddress = "client-address";
	static String ClientGuid = "client-guid";
	static String PacketsRequested = "packets-requested";
	static String PacketsServiced = "packets-serviced";
	static String RetriesRequest = "retries-request";
	static String MaxHoleSize = "max-hole-size";
	static String RequestTime = "request-time";
	static String ServiceGuid = "service-guid";
	
	static String listProperties = ClientAddress + "," + ClientGuid + "," + RequestTime + "," + ServiceGuid + "," + RetriesRequest + "," + PacketsRequested + "," + PacketsServiced + "," + MaxHoleSize + "\n"; 
			


}
