package main.util.CSVfiles;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import com.csvreader.CsvReader;
import com.csvreader.CsvWriter;
import com.jcraft.jsch.SftpException;

import main.util.assignmentutils.assignment.Assignment;
import main.util.filedetails.FileDetails;
import main.util.sshcommands.UsingJsch;

public class CSVfiles {

	public static ArrayList<Assignment> ReadMarksFile(String path) throws Exception{
		
		String str[] = FileDetails.getFileList(path);
		ArrayList<Assignment> list = new ArrayList<Assignment>();
		ArrayList<CSVMarks> csvlist = new ArrayList<CSVMarks>();
		for(int i=0;i<str.length;i++)
		{
			Assignment temp = new Assignment();
			temp.setName("\""+str[i]+"\"");
			temp.setPath(path);
			String s[]=FileDetails.getStats(path,temp.getName());
			temp.setLastModified(s[1]);
			temp.setSize(s[0]);
			temp.setMarks(0);
			temp.setStatus("Not tested");
			list.add(temp);
			//READING MARKS, ERROR FROM CSV FILE
		}	
		
		BufferedReader br = null;
		FileReader fr = null;
		fr = new FileReader(path+"\\marks.txt");
		br = new BufferedReader(fr);	
		CsvReader read = new CsvReader(br);
		read.readHeaders();
		int count = 0;
		while(read.readRecord()){
			String filename = read.get("FileName");
			String marks = read.get("Marks");
			String status = read.get("Status");
			CSVMarks obj = new CSVMarks(filename,marks,status);		
			csvlist.add(obj);
			count++;
	}
		read.close();
		for(int i=0;i<str.length;i++){
			for(int j=0;j<str.length;j++){
				if(list.get(j).getName().equals(csvlist.get(i).getFilename()))
						{
					list.get(j).setMarks(Integer.parseInt(csvlist.get(i).getFilename()));
					list.get(j).setStatus(csvlist.get(i).getStatus());
					break;
				}
			}
		}
		return list;
	}
	public static void WriteMarksFile(String path,int marks,String status) throws Exception{
   String outputFile = "finalmarks.csv";
		
		// before we open the file check to see if it already exists
		
   boolean alreadyExists = false;
			
		try {
			// use FileWriter constructor that specifies open for appending
			CsvWriter csvOutput = new CsvWriter(new FileWriter(outputFile, true), ',');
			// if the file didn't already exist then we need to write out the header line
			if (!alreadyExists)
			{
				csvOutput.write("FileName");
				csvOutput.write("Marks");
				csvOutput.write("Status");
				csvOutput.endRecord();
			}
			// else assume that the file already has the correct header line
			
			// write out a few records
			UsingJsch.writingFile(path, csvOutput.toString(), "finalmarks.csv");
			csvOutput.flush();
			csvOutput.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
	}
	

	
	public static void main(String[] args) {
		try {
			
			CsvReader products = new CsvReader("products.csv");
		
			products.readHeaders();

			while (products.readRecord())
			{
				String productID = products.get("ProductID");
				String productName = products.get("ProductName");
				String supplierID = products.get("SupplierID");
				String categoryID = products.get("CategoryID");
				String quantityPerUnit = products.get("QuantityPerUnit");
				String unitPrice = products.get("UnitPrice");
				String unitsInStock = products.get("UnitsInStock");
				String unitsOnOrder = products.get("UnitsOnOrder");
				String reorderLevel = products.get("ReorderLevel");
				String discontinued = products.get("Discontinued");
				
				// perform program logic here
				System.out.println(productID + ":" + productName);
			}
	
			products.close();
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

}