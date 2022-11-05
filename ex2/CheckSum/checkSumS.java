import java.util.*;
import java.io.*;
public class checkSumS
{
	public static void main(String args[])
	{
		Scanner in=new Scanner(System.in);
		System.out.println("enter the string");
		String e=in.nextLine();
		int sum=0;
		String t="",u="";
		String []a=e.split("(?<=\\G.{"+8+"})");
		System.out.println(Arrays.toString(a));
		int k[]=new int[a.length];
		for(int i=0;i<a.length;i++)
			k[i]=Integer.parseInt(a[i],2);
		for(int i=0;i<k.length;i++)
			sum+=k[i];
		t=Integer.toBinaryString(sum);
		System.out.println(t);
		for(int i=0;i<t.length();i++)
		{
			if(t.charAt(i)=='0')
				u+="1";
			else
				u+="0";
		}
		System.out.println(u);
		t=Integer.toBinaryString((Integer.parseInt(u,2)+1));
		for(int i=0;i<=9-t.length();i++)
			t="0"+t;
		System.out.println(t);
		File o=new File("CheckSum.txt");
		boolean ob=o.exists();
		try
		{
			if(ob==false)
				o.createNewFile();
			FileWriter o_write=new FileWriter(o);
			o_write.write(e);
			o_write.write(t);
			System.out.println("Write Successfully");
			o_write.flush();
			o_write.close();
		}catch (IOException exp)
			{System.out.println("expn");}
	}
}