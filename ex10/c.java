import java.io.*;
import java.util.*;
import java.net.*;
public class c
{
	public static void main(String args[]) throws Exception
	{
		Scanner in=new Scanner(System.in); 
		String x;
		try
		{
			
			int i,j;
		Socket skt=new Socket("localhost",2598);
		DataOutputStream odos=new DataOutputStream(skt.getOutputStream());
		DataInputStream odis=new DataInputStream(skt.getInputStream());

		BufferedReader obr=new BufferedReader(new InputStreamReader(System.in));
	
					
		System.out.print("enter Website Name :  ");
		String user = in.next();
		System.out.println();
	
		odos.writeUTF(user);
		
	
		System.out.println("connecting to server..");
			  String ans=odis.readUTF();
			  System.out.println("ipV4 (Local Server) : "+ans);
			  if(ans.equals("Not found in local server.."))
			  {
			  		
			  	try{
					  System.out.println("Searching for in Root Server!! ");
			  		
			  		Socket skt1=new Socket("localhost",1111);
		DataOutputStream odos1=new DataOutputStream(skt1.getOutputStream());
		DataInputStream odis1=new DataInputStream(skt1.getInputStream());
		skt.close();
		BufferedReader obr1=new BufferedReader(new InputStreamReader(System.in));

		odos1.writeUTF(user);
		
		 String ans1=odis1.readUTF();
		 System.out.println("connecting to server..");
			   System.out.println("\n--------------------------------------------------------\n");
			  System.out.println("ipV4 (Root Server) : "+ans1);
System.out.println("--------------------------------------------------------\n");

System.out.println("--------------------------------------------------------\n");
System.out.println("ipv4 in Root server gets copied to Local server..");
System.out.println("Now "+user+" will be available in local server..");
System.out.println("--------------------------------------------------------\n");
		skt1.close();
			  	}
			  	catch(ConnectException ex)
			  	{
			  		System.out.println("error in root server");

			  	}
			  }
	
			
	else
	{	
		skt.close();
	}
	
	}
	catch(ConnectException ex)
	{
		System.out.println("Server Not Avaliable 404 Error Found");
	}

	}
}
