#Read the 3 input files reports.json, reports.csv, reports.xml and output a combined CSV file with the following characteristics:

The same column order and formatting as reports.csv
All report records with packets-serviced equal to zero should be excluded
records should be sorted by request-time in ascending order
Additionally, the application should print a summary showing the number of records in the output file associated with each service-guid.



Required Module :
- dom-2.3.0-jaxb-1.0.6.JAR : Help to read XML file
- json-simple-1.1.1.JAR : Help to read JSON file
- report.xml
- report.csv
- report.json


Install and Run :
- open with epclise IDE
- Add the Json and Dom librairies 
- run as a simple the Main method in Main.java class


