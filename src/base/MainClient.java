package base;
import graui.*;
import server.*;

import java.util.*;


public class MainClient {
	public static void main(String[] args) throws Exception{
		
		String host = "localhost";  //设置地址
		
	   LibraryClientIdea lci = new LibraryClientIdea(host,2223);  //设置端口
	   
	    //ClientFrame cf2 = new ClientFrame(lci.list,lci);
	    ClientFrame cf = new ClientFrame(lci.list,lci);  
	    ClientFrameController cfc = new ClientFrameController(lci,cf);
	   // ClientFrameController cfc2 = new ClientFrameController(lci,cf2);    

	}
	

}
