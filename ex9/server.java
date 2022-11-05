import java.io.*;
import java.net.*;
import java.util.*;
import java.sql.*;

public class server
{
	public static void main(String args[]) throws Exception
	{
	try
		{
			ServerSocket skt=new ServerSocket(2255);
			Socket sk=skt.accept();
			System.out.println("Connected");
			DataInputStream odis=new DataInputStream(sk.getInputStream());
			String s=odis.readUTF();
			System.out.println("read  ; "+s);
				sqlCon o=new sqlCon();

			String as[]=o.sql(s);
			OutputStream os = sk.getOutputStream();
			ObjectOutputStream oos = new ObjectOutputStream(os);
			List<Message> messages = new ArrayList<>();
			for(int i=0;i<=18;i++)
				messages.add(new Message(as[i]));
			oos.writeObject(messages);
			skt.close();
			sk.close();
			
		}catch(EOFException exp){}
		catch(Exception ep){}
	}
}

class sqlCon
{
	public String[]  sql(String s)
	{
			Connection con=null;
			String ary[]=new String[19];
		try
		{
			Class.forName("com.mysql.cj.jdbc.Driver");
			con=DriverManager.getConnection("jdbc:mysql://localhost:3306/info","root","");
			Statement stmt=con.createStatement();
			ResultSet rs=null;
			rs=stmt.executeQuery("SELECT * FROM student WHERE Rollno='"+s+"'");
			while(rs.next())
			{
				ary[0]=rs.getString("Rollno");
				ary[1]=rs.getString("AdNo");
				ary[2]=rs.getString("Name");
				ary[3]=rs.getString("Dept");
				ary[4]=Integer.toString(rs.getInt("Batch"));
				ary[5]=rs.getString("Quota");
				ary[6]=rs.getString("City");
				ary[7]=rs.getString("Mobile");
				ary[8]=rs.getString("BloodGroup");
				ary[9]=rs.getString("D_H");
			}
			rs=null;
			rs=stmt.executeQuery("SELECT * FROM 	i1marks WHERE Rollno='"+s+"'");
			while(rs.next())
			{
				ary[10]=Integer.toString(rs.getInt("m1"));
				ary[11]=Integer.toString(rs.getInt("m2"));
				ary[12]=Integer.toString(rs.getInt("m3"));
			}
			rs=null;
			rs=stmt.executeQuery("SELECT * FROM 	i2marks WHERE Rollno='"+s+"'");
			while(rs.next())
			{
				ary[13]=Integer.toString(rs.getInt("m1"));
				ary[14]=Integer.toString(rs.getInt("m2"));
				ary[15]=Integer.toString(rs.getInt("m3"));
			}
			rs=null;
			rs=stmt.executeQuery("SELECT * FROM 	i3marks WHERE Rollno='"+s+"'");
			while(rs.next())
			{
				ary[16]=Integer.toString(rs.getInt("m1"));
				ary[17]=Integer.toString(rs.getInt("m2"));
				ary[18]=Integer.toString(rs.getInt("m3"));
			}
			System.out.println(Arrays.toString(ary));
		}catch(Exception e)
		{System.err.println(e);}
		return ary;
	}
}

class Message implements Serializable{
    private final String text;

    public Message(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }
}