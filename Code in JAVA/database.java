
import java.sql.*;
import java.util.ArrayList;
public class database {

	private Connection con;
	private Statement stmt;
	private String username,password,str;
	private ArrayList<String>res;
	
	public database() {
		con=null;		
	}
	public void connect()
	{
		try
		{  
			Class.forName("com.mysql.jdbc.Driver");  
			username="root";
			password="Ajit 123";
			str="jdbc:mysql://localhost:3306/DBLP?autoReconnect=true&useSSL=false";
			con=DriverManager.getConnection(str,username,password);  
		  
			//System.out.println("Connection Done");
		}
		catch(Exception e)
		{ 
			System.out.println(e);
		}  
		try {
			stmt=con.createStatement();
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
	}
	public ArrayList<String> run_statement1(String str)
	{
		System.out.println("Info of Donor");
		res=new ArrayList<String>();
		res.add("Donor Id,Donor Name,Donor Address,Phne No,Blood Group, Age");
		try {
			
			ResultSet ans=stmt.executeQuery("select * from donor where dname='"+str+ "';");
			while(ans.next())  
			 {
				String temp=ans.getInt(1)+","+ans.getString(2)+","+ans.getString(3)+","+ans.getInt(4)+","+ans.getString(5)+","+ans.getInt(6);
				res.add(temp);
			 }
			
		} catch (SQLException e) {
			
			e.printStackTrace();
			
		}
		return res;
	}

	public ArrayList<String> run_statement2(String str)
	{

		System.out.println("Check Camp on a particular date");
		System.out.println("Blood:"+str);
		res=new ArrayList<String>();
		res.add("Camp ID,Centre ID,Camp Location");
		try
		{
			ResultSet ans=stmt.executeQuery("select * from camp where dateorganized='"+str+"';");
			while(ans.next())  
			 {
				String temp=ans.getInt(1)+","+ans.getInt(2) +","+ans.getString(3);
				//System.out.println(temp);
				res.add(temp);
			 }
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		return res;
	}
	public ArrayList<String> run_statement3(String str)
	{

		System.out.println("Info of Blood Stock available at a centre");
		res=new ArrayList<String>();
		res.add("Centre Id,A,B,O,AB");
		try
		{
			ResultSet ans=stmt.executeQuery("select centreid,SUM(A),SUM(B),SUM(O),SUM(AB) from bloodcollection group by(centreid) having centreid="+str+";");
			while(ans.next())  
			{
				String temp=ans.getInt(1)+","+ans.getInt(2)+","+ans.getInt(3)+","+ans.getInt(4)+","+ans.getInt(5);
				//System.out.println(temp);
				res.add(temp);
			}
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		return res;
	}
	public ArrayList<String> run_statement4(String str)
	{

		System.out.println("List Centres with availabilty of a particular blood group");
		
		res=new ArrayList<String>();
		res.add("CentreId,Blood Group "+str);
		try
		{
			ResultSet ans=stmt.executeQuery("select centreid,SUM("+str+") from bloodcollection group by(centreid) having SUM("+str+")>0;");
			while(ans.next())  
			 {
				String temp=ans.getInt(1)+","+ans.getInt(2);
				//System.out.println(temp);
				res.add(temp);
			 }
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		return res;
	}
	public ArrayList<String> run_statement5()
	{

		System.out.println("Details of camps that are being organised");
		res=new ArrayList<String>();
		res.add("Camp Id,Centre Id,Camp Location,Date Organised");
		try
		{
			ResultSet ans=stmt.executeQuery("select * from camp;");
			while(ans.next())  
			{
				String temp=ans.getInt(1)+","+ans.getInt(2)+","+ans.getString(3)+","+ans.getDate(4).toString();
				//System.out.println(ans.getInt(1)+"  "+ans.getInt(2)+"  "+ans.getString(3)+"  "+ans.getDate(4).toString());
				res.add(temp);
			}
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		return res;
	}
	public ArrayList<String> run_statement6(String str)
	{
		System.out.println("Info of Employees that were not present in the camp organised by their centre");
		//
		
		
		res=new ArrayList<String>();
		res.add("Employee Id,Employee Name");
		try {
			
			ResultSet ans=stmt.executeQuery("select e1.eid,e1.ename from employee e1, camp c where c.centreid=e1.centreid and c.campid="+str+" and e1.eid not in (select e.eid from employee e, camp c , waspresent where c.centreid=e.centreid and c.campid="+str+" and waspresent.eid=e.eid and waspresent.campid=c.campid);");
			while(ans.next())  
			 {	
				String temp=ans.getInt(1)+","+ans.getString(2);
				//System.out.println(temp);
				res.add(temp);
			 }
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		return res;
	}
	public ArrayList<String> run_statement7(String str)
	{
		System.out.println("Info of Employee");
		res=new ArrayList<String>();
		res.add("Employee Id,Name,Age,Phone No.,Centre Id");
		try {
			
			ResultSet ans=stmt.executeQuery("select * from employee where ename='"+str+ "';");
			while(ans.next())  
			 {	
				String temp=ans.getInt(1)+","+ans.getString(2)+","+ans.getInt(3)+","+ans.getInt(4)+","+ans.getInt(5);
				res.add(temp);
			 }
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		return res;
	}
	public ArrayList<String> run_statement8()
	{
		System.out.println("Max 2 donors");
		res=new ArrayList<String>();
		res.add("Donor Id,Donor Name,Donated Blood");
		try {
			
			ResultSet ans=stmt.executeQuery("select * from (select donor.did,dname,SUM(units) from donations,donor where donor.did=donations.did group by donor.did order by SUM(units) desc) as x limit 2;  ");
			while(ans.next())  
			 {	
				String temp=ans.getInt(1)+","+ans.getString(2)+","+ans.getInt(3);
				//System.out.println(temp);
				res.add(temp);
			 }
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		return res;
	}
	public ArrayList<String> run_statement9()
	{
		System.out.println("Camps that have organised more than average number of donations");
		res=new ArrayList<String>();
		res.add("Camp Id,Average Donations");
		try 
		{
			ResultSet ans=stmt.executeQuery("select campid,y.s/count(*)as Aver from ( select campid,sum(units) as s from donations group by campid) as y;");
			while(ans.next())  
			 {	
				String temp=ans.getInt(1)+","+ans.getInt(2);
				//System.out.println(temp);
				res.add(temp);
			 }
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		return res;
	}
	public ArrayList<String> run_statement10()
	{
		System.out.println("Employee worked in maximum camps");
		res=new ArrayList<String>();
		res.add("Employee Id,No. of camps Worked in");
		try {
			ResultSet ans=stmt.executeQuery("select eid, max(y.num) as \'No of Camps worked in\' from (select eid,count(campid) as num from waspresent group by eid)y;") ;
			while(ans.next())  
			 {	
				String temp=ans.getInt(1)+","+ans.getInt(2);
				//System.out.println(temp);
				res.add(temp);
			 }
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		return res;
	}
	public void disconnect()
	{		
		try {
			if(con!=null)
				{
					con.close();
					//System.out.println("Connection Closed");
				}
		} catch (SQLException e) {
			
			e.printStackTrace();
		} 
	}
	/*public static void main(String args[])
	{  
		
		database d=new database();
		//d.connect();
		d.run_statement1();
		d.run_statement2();
		d.disconnect();
	}  */
		 

}
