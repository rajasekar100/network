import java.util.*;
import java.io.*;
public class VRCs
{
	public static void main(String args[])
	{
		Scanner in=new Scanner(System.in);
		System.out.println("Enter The Values");
		String e=in.nextLine();
		File o=new File("VRC.txt");
		boolean ob=o.exists();
		try
		{
			if(ob==false)
				o.createNewFile();
			FileWriter o_write=new FileWriter(o);
			o_write.write(e);
			System.out.println("Write Successfully");
			o_write.flush();
			o_write.close();
		}catch (IOException exp)
			{System.out.println("expn");}	
	}
}