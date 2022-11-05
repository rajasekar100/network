import java.util.*;
import java.io.*;
public class charactorCountSender
{
	public static void main(String args[])
	{
		Scanner in=new Scanner(System.in);
		System.out.print("Enter The String : ");
		String string=in.nextLine();
		StringBuffer a=new StringBuffer("");
		String b="";
		int count =0;
		for (int i=0;i<string .length();i++)
		{
			if(string.charAt(i)==' ')
			{
				a.append(count);
				count=0;
				continue;
			}
			a.append(string.charAt(i));
			count++;
		}
		b=a.append(count).toString();
		File o=new File("charCount.txt");
		boolean ob=o.exists();
		try
		{
			if(ob==false)
			{
				o.createNewFile();
			}
			FileWriter o_write=new FileWriter(o);
			o_write.write(b);
			System.out.println("Write Successfully");
			o_write.flush();
			o_write.close();
		}catch (IOException e)
			{System.out.println("expn");}
	}
}
