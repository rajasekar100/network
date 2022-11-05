import java.util.*;
import java.io.*;
public class CRCS
{
	public static void main(String args[])
	{	
		Scanner in=new Scanner(System.in);
		System.out.println("Enter the Divident");
		String s=in.nextLine();
		System.out.println("Enter the Diviser");
		String t=in.nextLine();		
		String u=s;
		String v="";
		String x=s.substring(0,4);
		for(int i=0;i<t.length()-1;i++)
			u+="0";
		String w=u;
		try
		{
		for(int j=0;j<s.length();j++)
		{
			v="";			
			for(int i=0;i<t.length();i++)
			{
				if(x.charAt(0)=='0'&&x.charAt(1)=='0')
					v+=(u.charAt(i)^'0');
				else
				v+=(u.charAt(i)^t.charAt(i));
			}
			x=v;
			System.out.println(v);
			v=v.substring(1,s.indexOf("(")+v.length()+1);
			System.out.println(v);
			u=v+w.charAt((t.length())+j);
			System.out.println(u);
		}
		}catch(Exception e){}
		s=s+v;
		System.out.println(s);
		//System.out.println(v);
		File o=new File("CRC.txt");
		boolean ob=o.exists();
		try
		{
			if(ob==false)
				o.createNewFile();
			BufferedWriter o_write=new BufferedWriter(new FileWriter(o));
			o_write.write(s);
			o_write.newLine();
			o_write.write(t);
			System.out.println("Write Successfully");
			o_write.flush();
			o_write.close();
		}catch (IOException exp)
			{System.out.println("expn");}	
	}
}