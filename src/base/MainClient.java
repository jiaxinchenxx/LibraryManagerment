package base;
import graui.*;
import server.*;

import java.util.*;


public class MainClient {
	public static void main(String[] args) throws Exception{
		
		String host = "localhost";  //���õ�ַ
		
	   LibraryClientIdea lci = new LibraryClientIdea(host,2223);  //���ö˿�
	   
	    //ClientFrame cf2 = new ClientFrame(lci.list,lci);
	    ClientFrame cf = new ClientFrame(lci.list,lci);  
	    ClientFrameController cfc = new ClientFrameController(lci,cf);
	   // ClientFrameController cfc2 = new ClientFrameController(lci,cf2);    

	}
	

}
