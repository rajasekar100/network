import java.util.*;
import java.io.*;
public class ECR
{
	public static void main(String args[])
	{
		StringBuffer sb=new StringBuffer();
		String u="";                                                                                      //
		File o=new File("EC.txt");                                                                 //
		boolean ob=o.exists();                                                                      //
		if(ob==true)                                                                                      //
			try                                                                                                //
			{                                                                                                  //
				FileReader o_read=new FileReader(o);                                      // 
				int r=o_read.read();                                                                   //
				while(r!=-1)                                                                              //
				{                                                                                              //
					u+=((char)r);                                                                        //read the value in string (char by char)
					r=o_read.read();                                                                   //
				}                                                                                              //
			}catch (IOException e)                                                                 //
				{System.out.println("expn");}                                                   //
		else                                                                                                  //
		{                                                                                                      //
			System.out.println("File Not Avaliable");                                        //
		}                                                                                                      //
		System.out.println("In file : "+u);                                                       //
		sb.append(u);                                                                                   // string changed into stringBuffer
		String a[]=new String[u.length()];                                                       // create array file value length (11)
		for(int i=0;i<u.length();i++)                                                                //
				a[i]=Character.toString(u.charAt(i));                                          //put the value into array (by each char)
		int m=a.length;                                                                                  //m=11
		String s,t="";                                                                                    //
		int r=a.length,k=0;                                                                            //r=11
		String b[]=new String[m];                                                                  // create array for storing binary values
		for(int i=0;i<r;i++)                                                                            // loop 0 to 10
			b[i]=Integer.toBinaryString(i+1);                                                    // store b in binary value of 1 to 11 
		for(int i=0;i<b.length;i++)                                                                  //
			while(b[r-1].length()!=b[i].length())                                                //loop 0 to 11
				b[i]="0"+b[i];                                                                           // last binary value length != current value length (loop b[i] value) then add 0 in front
		for(int j=1;j<=b[b.length-1].length();j++)                                            // j loop 0 to last binary value length (here 4 (1011(11)))
		{                                                                                                      //
			s="";                                                                                            //
			for(int i=0;i<b.length;i++)                                                              //i loop 0 to 11
			{                                                                                                  //
				if(Character.toString(b[i].charAt(b[i].length()-j )).equals("1"))		//b[0](0001).charAt means ({0001(4)-1(j)}3)(index 3 means 1)==1
					s+=a[i];                                                                                //s=empty first now add a[2](1) assign into s
			}                                                                                                  //
			m=(int)(s.chars().filter(ch -> ch == '1').count());                            //occurence of 1 in s
			if(m%2==1)                                                                                  // if add number of ones 
				t="1"+t;                                                                                    // a[0](x)(r value in 55th line) assign =1
			else                                                                                              //  if even number of ones
				t="0"+t;                                                                                    // a[1](x)(r value in 55th line) assign =0
		}                                                                                                      //
		System.out.println(t);                                                                        // t have edited index(binary value)
		System.out.println("Edited Index : "+Integer.parseInt(t,2));                 // binary converted to decimal
		m=Integer.parseInt(t,2);                                                                     //
		m-=1;                                                                                               // m= index value
		try                                                                                                    //
		{                                                                                                      //
		if((u.charAt(m))=='0')                                                                       //  if index value have 0 change to 1 else 
			sb.setCharAt(m,'1');                                                                      //
		else if((u.charAt(m))=='1')                                                                 // index value 1 change to 0
			sb.setCharAt(m,'0');                                                                      //
		System.out.println("After Change :"+sb);                                           // 
		}catch(Exception expn){System.err.println();}                                   //
		try                                                                                                    //
		{                                                                                                      //
			FileWriter o_write=new FileWriter(o);                                            //
			o_write.write(String.valueOf(sb));                                                  // write the correct value
			System.out.println("Write in File Succcess");                                 //
			o_write.flush();                                                                             //
			o_write.close();                                                                             //
		}catch(IOException ex){System.err.println();}                                   //
	}
}