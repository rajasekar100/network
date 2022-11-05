import java.util.*;
import java.io.*;
public class PLCsender
{
	public static void main(String args[])
	{
		Scanner in=new Scanner(System.in);
		System.out.print("Enter The Value : ");
		String c=in.nextLine();
		String b="";
		int rem,rev=0;
		int a=Integer.parseInt(c);
		while(a!=0)
		{
			rem=a%10;
			if(rem==0)
			{
				b="01"+b;
			}
			else if(rem==1)
			{
				b="10"+b;
			}
			else
			{
				System.out.println("Wrong input");
				break;
			}
			a/=10;
		}
		File o=new File("file.txt");
		boolean ob=o.exists();
		try
		{
			if(ob==false)
			{
				o.createNewFile();
			}
			FileWriter o_write=new FileWriter(o);
			o_write.write("00"+b+"11");
			System.out.println("Write Successful");
			o_write.flush();
			o_write.close();
		}catch (IOException e)
			{System.out.println("expn");}
	}
}
