package main.util.filechooser;

import java.io.File;

import javax.swing.JFileChooser;


public class FileChooser {
	
	public String getFilePath(){
		JFileChooser chooser = new JFileChooser();
	    chooser.setCurrentDirectory(new java.io.File("."));
	    chooser.setDialogTitle("FILE SELECT");
	    chooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
	    chooser.setAcceptAllFileFilterUsed(false);

	    if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
	     File c = chooser.getSelectedFile();
	     String path = c.getAbsolutePath();
		return path;	
	}
	    return "";
}
	public String getDirectoryPath(){
		JFileChooser chooser = new JFileChooser();
	    chooser.setCurrentDirectory(new java.io.File("."));
	    chooser.setDialogTitle("DIRECTORY SELECT");
	    chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
	    chooser.setAcceptAllFileFilterUsed(false);
	    if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
	    	String path = chooser.getSelectedFile().getAbsolutePath();
	    	return path;
	    	
	    }
	    return "";

	}
	public static void main(String args[]){
		//FileChooser fc = new FileChooser();
		//System.out.println(fc.getDirectoryPath());
		
	}
}

   

