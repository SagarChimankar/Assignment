package com.assign.services;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.stream.StreamSource;
import javax.xml.parsers.DocumentBuilder;  
import org.w3c.dom.Document;  
import org.w3c.dom.NodeList;  
import org.w3c.dom.Node;  
import org.w3c.dom.Element;  
import java.io.File;  

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
//import com.sun.rowset.internal.Row;
//import javafx.scene.control.Cell;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import com.assign.Entity.student;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class service {
	//Itrate over Excel file and show data from include startRow 
	public List<List<String>> readExcel(MultipartFile file, int startRow)
			throws IOException, EncryptedDocumentException, InvalidFormatException {
		Workbook workbook = WorkbookFactory.create(file.getInputStream());
		Sheet sheet = workbook.getSheetAt(0); //one sheet is there
		startRow=startRow+1;
		List<List<String>> data = new ArrayList<>();
		Iterator<Row> rowIterator = sheet.iterator();
		int currentRow = 0;

		while (rowIterator.hasNext()) {
			Row row = rowIterator.next();
			currentRow++;

			if (currentRow >= startRow) {
				Iterator<Cell> cellIterator = row.cellIterator();
				List<String> rowData = new ArrayList<>();

				while (cellIterator.hasNext()) {
					Cell cell = cellIterator.next();
					rowData.add(cell.toString());
				}

				data.add(rowData);
			}
		}

		workbook.close();
		return data;
	}

	// ***************************************************************************************
	// Create a service to parse the XML file and generate JSON:
	public void XmlToJsonFileCreated() {
	 		try   
	 		{  
	 		String files="F:\\1 PRoj\\Interview\\assignment\\fileXML.xml";
	 		//creating a constructor of file class and parsing an XML file  
	 		File file = new File(files);  
	 		//an instance of factory that gives a document builder  
	 		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();  
	 		//an instance of builder to parse the specified xml file  
	 		DocumentBuilder db = dbf.newDocumentBuilder();  
	 		Document doc = db.parse(file);  
	 		doc.getDocumentElement().normalize();  
	 		System.out.println("Root element: " + doc.getDocumentElement().getNodeName());  
	 		NodeList nodeList = doc.getElementsByTagName("student");  
	 		// nodeList is not iterable, so we are using for loop  
	 		
	 		List<student> sa=new ArrayList();
	 		for (int itr = 0; itr < nodeList.getLength(); itr++){  
	 		Node node = nodeList.item(itr);  
	 		//System.out.println("\nNode Name :" + node.getNodeName());  
	 		if (node.getNodeType() == Node.ELEMENT_NODE)   
	 		{  
	 		Element eElement = (Element) node;  
	 		System.out.println("id: "+Integer. parseInt(eElement.getElementsByTagName("id").item(0).getTextContent())  );  
	 		System.out.println("fname: "+ eElement.getElementsByTagName("firstname").item(0).getTextContent());  
	 		System.out.println("lname: "+ eElement.getElementsByTagName("lastname").item(0).getTextContent());
	 		System.out.println("subject: "+ eElement.getElementsByTagName("subject").item(0).getTextContent());
	 		System.out.println("marks: "+ eElement.getElementsByTagName("marks").item(0).getTextContent());
	 		
	 		System.out.println();
	 		student s=new student();
	 		s.setId(Integer.parseInt(eElement.getElementsByTagName("id").item(0).getTextContent()));
	 		s.setFirstname(eElement.getElementsByTagName("firstname").item(0).getTextContent());
	 		s.setLastname(eElement.getElementsByTagName("lastname").item(0).getTextContent());
	 		s.setMarks(Integer.parseInt(eElement.getElementsByTagName("marks").item(0).getTextContent()));
	 		s.setSubject(eElement.getElementsByTagName("subject").item(0).getTextContent());
	 		
	 		
	 		sa.add(s);
	 		
	 		
	 		System.out.println(eElement);;
	 		String fileName = "C:\\Users\\sagar\\Downloads\\fileXML.xml";
	 	    //jaxbXmlFileToObject(fileName);
	 		
	 		ObjectMapper mapper=new ObjectMapper();
			File filess=new File("student.json");
			mapper.writeValue(filess, sa);
			System.out.println("Json file created...");
	 	
	 		
	 		}  
	 		} 
	 		}  
	 		catch (Exception e)   
	 		{  
	 		e.printStackTrace();  
	 		}  
	 	}
	

	private int parseInt(String textContent) {
		
		return 0;
	}

	
	
	
	   
	   
	  
	 	 
	  
}
