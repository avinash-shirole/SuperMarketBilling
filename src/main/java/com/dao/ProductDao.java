package com.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.model.Bill;
import com.model.Product;
import com.model.Staff;
import com.util.MyDatabase;

public class ProductDao {
	public List<Product> searchProductByAny(String pword)
	{
		List<Product> list = new ArrayList();
		Connection con = new MyDatabase().getConnection();
		PreparedStatement pst = null;
		ResultSet rs = null;
		
		String sql ="select pid,pname,ptype,pdescription,pprice from product where pname like '%"+pword+"%' or ptype like '%"+pword+"%' or pdescription like '%"+pword+"%'";
		String s1[] = pword.split(" ");
		String num="";
		for(int i=0; i<s1.length; i++)
			if(Character.isDigit(s1[i].charAt(0)))
				num=s1[i];
		if(num.length()!=0)
		if(Character.isDigit(num.charAt(0)))
			sql+=" or pid=? or pprice=?";
		try {
			pst = con.prepareStatement(sql);
			if(num.length()!=0)
			{
				pst.setInt(1, Integer.parseInt(num));
				pst.setDouble(2, Double.parseDouble(num));
			}
			rs = pst.executeQuery();
			while(rs.next())
			{
				Product
				p = new Product();
				p.setPid((int)rs.getObject("pid"));
				p.setPname((String)rs.getObject("pname"));
				p.setPtype((String)rs.getObject("ptype"));
				p.setPdescription((String)rs.getObject("pdescription"));
				p.setPprice((double)rs.getObject("pprice"));
				list.add(p);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally
		{
			new MyDatabase().closeConnection(rs, pst, con);
		}
		return list;
	}
	public Product displayProductById(int id)
	{
		Product p = null;
		Connection con = new MyDatabase().getConnection();
		PreparedStatement pst = null;
		ResultSet rs = null;
		String sql = "select pid,pname,ptype,pdescription,pprice from product where pid=?";
		try {
			pst = con.prepareStatement(sql);
			pst.setInt(1, id);
			rs = pst.executeQuery();
			while(rs.next())
			{
				p = new Product();
				p.setPid((int)rs.getObject("pid"));
				p.setPname((String)rs.getObject("pname"));
				p.setPtype((String)rs.getObject("ptype"));
				p.setPdescription((String)rs.getObject("pdescription"));
				p.setPprice((double)rs.getObject("pprice"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally
		{
			new MyDatabase().closeConnection(rs, pst, con);
		}
		return p;
	}
	public void generateBill(List<Product>list)
	{
		if(list.size()==0)
		{
			System.out.println("No Product Added In Bill");
		}
		else
		{
			double total=0;
			System.out.println("--------------------------D-Mart--------------------------");
			for(Product p:list)
			{
				System.out.println(p.getPid()+"\t"+p.getPname()+"\t"+p.getPprice());
				total+=p.getPprice();
			}
			System.out.println("\t\t\tTotal Amount:"+total);
			System.out.println("----------------------------------------------------------");
		}
	}
	public int insertBill(Bill b)
	{
		int check = 0;
		Connection con= new MyDatabase().getConnection();
		PreparedStatement pst = null;
		String sql = "insert into bill(bdescription,bamount,bby)values(?,?,?)";
		try {
			pst = con.prepareStatement(sql);
			pst.setString(1, b.getBdescription());
			pst.setDouble(2, b.getBamount());
			pst.setString(3, b.getBby());
			check = pst.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return check;
		
	}
	public boolean staffLogin(String sname, String spwd)
	{
		boolean b = false;
		Connection con= new MyDatabase().getConnection();
		PreparedStatement pst = null;
		ResultSet rs = null;
		String sql = "select sid from staff where sname=? and spassword=?";
		try {
			pst = con.prepareStatement(sql);
			pst.setString(1, sname);
			pst.setString(2, spwd);
			rs = pst.executeQuery();
			while(rs.next())
			{
				b = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return b;
	}
	public List<Product> searchProductByName(String name)
	{
		List<Product> list = new ArrayList();
		Connection con = new MyDatabase().getConnection();
		PreparedStatement pst = null;
		ResultSet rs = null;
		String sql ="select pid,pname,ptype,pdescription,pprice from product where pname like '%"+name+"%'";
		try {
			pst = con.prepareStatement(sql);
			rs = pst.executeQuery();
			while(rs.next())
			{
				Product
				p = new Product();
				p.setPid((int)rs.getObject("pid"));
				p.setPname((String)rs.getObject("pname"));
				p.setPtype((String)rs.getObject("ptype"));
				p.setPdescription((String)rs.getObject("pdescription"));
				p.setPprice((double)rs.getObject("pprice"));
				list.add(p);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally
		{
			new MyDatabase().closeConnection(rs, pst, con);
		}
		return list;
	}
	public List<Product> searchProductByDescription(String pdescription)
	{
		List<Product> list = new ArrayList();
		Connection con = new MyDatabase().getConnection();
		PreparedStatement pst = null;
		ResultSet rs = null;
		String sql ="select pid,pname,ptype,pdescription,pprice from product where pdescription like '%"+pdescription+"%'";
		try {
			pst = con.prepareStatement(sql);
			rs = pst.executeQuery();
			while(rs.next())
			{
				Product
				p = new Product();
				p.setPid((int)rs.getObject("pid"));
				p.setPname((String)rs.getObject("pname"));
				p.setPtype((String)rs.getObject("ptype"));
				p.setPdescription((String)rs.getObject("pdescription"));
				p.setPprice((double)rs.getObject("pprice"));
				list.add(p);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally
		{
			new MyDatabase().closeConnection(rs, pst, con);
		}
		return list;
	}
	public List<Product> searchProductByPrice(double pprice)
	{
		List<Product> list = new ArrayList();
		Connection con = new MyDatabase().getConnection();
		PreparedStatement pst = null;
		ResultSet rs = null;
		String sql ="select pid,pname,ptype,pdescription,pprice from product where pprice like '%"+pprice+"%'";
		try {
			pst = con.prepareStatement(sql);
			rs = pst.executeQuery();
			while(rs.next())
			{
				Product
				p = new Product();
				p.setPid((int)rs.getObject("pid"));
				p.setPname((String)rs.getObject("pname"));
				p.setPtype((String)rs.getObject("ptype"));
				p.setPdescription((String)rs.getObject("pdescription"));
				p.setPprice((double)rs.getObject("pprice"));
				list.add(p);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally
		{
			new MyDatabase().closeConnection(rs, pst, con);
		}
		return list;
	}
	public int AddStaff(Staff s)
	{
		{
			int check=0;
			Connection con = new MyDatabase().getConnection();
			PreparedStatement pst = null;
			String sql ="insert into staff(sname,sid,spassword,semailid,ssalary)values(?,?,?,?,?)";
			try {
				pst = con.prepareStatement(sql);			
				pst.setString(1, s.getSname());
				pst.setInt(2, s.getSid());
				pst.setString(3, s.getSpassword());
				pst.setString(4, s.getSemailid());
				pst.setDouble(5, s.getSsalary());
				check=pst.executeUpdate();
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
			finally {
				new MyDatabase().closeConnection(pst, con);
			}
			return check;
		}
		
	}
	public List<Staff> viewStaff()
	{
		List<Staff>list = new ArrayList();
		Connection con = new MyDatabase().getConnection();
		PreparedStatement pst = null;
		ResultSet rs = null;
		String sql = "select sid,sname,spassword,semailid,ssalary from staff";
		try {
			pst = con.prepareStatement(sql);
			rs = pst.executeQuery();
			while(rs.next())
			{
				Staff s = new Staff();
				s.setSid((int)rs.getObject("sid"));
				s.setSname((String)rs.getObject("sname"));
				s.setSpassword((String)rs.getObject("spassword"));
				s.setSemailid((String)rs.getObject("semailid"));
				s.setSsalary((Double)rs.getObject("ssalary"));
				list.add(s);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally
		{
			new MyDatabase().closeConnection(rs, pst, con);
		}
		return list;
	}
	public int deleteStaff(int sid) // problem on this method
	{
		int check = 0;
		Connection con = new MyDatabase().getConnection();	
		PreparedStatement pst = null;
		String sql = "delete from staff where sid=?";
		try {
			pst = con.prepareStatement(sql);
			pst.setInt(1, sid);
			check = pst.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally
		{
			new MyDatabase().closeConnection(pst, con);
		}
		return check;
	}
	public int AddBill(Bill b)
	{
		{
			int check=0;
			Connection con = new MyDatabase().getConnection();
			PreparedStatement pst = null;
			String sql ="insert into bill(bid,bdescription,bamount,bby)values(?,?,?,?)";
			try {
				pst = con.prepareStatement(sql);			
				pst.setInt(1, b.getBid());
				pst.setString(2, b.getBdescription());
				pst.setDouble(3, b.getBamount());
				pst.setString(4, b.getBby());
				check=pst.executeUpdate();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			finally {
				new MyDatabase().closeConnection(pst, con);
			}
			return check;
		}
	}
	public int updateStaff(Staff s)
	{
		int check = 0;
		Connection con = new MyDatabase().getConnection();
		PreparedStatement pst = null;
		String sql = "update staff set sname=?,spassword=?,semailid=?,ssalary=? where sid=?";
		try {
			pst = con.prepareStatement(sql);
			pst.setString(1, s.getSname());
			pst.setString(2, s.getSpassword());
			pst.setString(3, s.getSemailid());
			pst.setDouble(4, s.getSsalary());
			pst.setInt(5, s.getSid());;
			check=pst.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally
		{
			new MyDatabase().closeConnection(pst, con);
		}
		return check;
	}
	public Staff displayStaffById(int id)
	{
		Staff s = null;
		Connection con = new MyDatabase().getConnection();
		PreparedStatement pst = null;
		ResultSet rs = null;
		String sql = "select sid,sname,spassword,semailid,ssalary from staff where sid=?";
		try {
			pst = con.prepareStatement(sql);
			pst.setInt(1, id);
			rs = pst.executeQuery();
			while(rs.next())
			{
				s = new Staff();
				s.setSid((int)rs.getObject("sid"));
				s.setSname((String)rs.getObject("sname"));
				s.setSpassword((String)rs.getObject("spassword"));
				s.setSemailid((String)rs.getObject("semailid"));
				s.setSsalary((Double)rs.getObject("ssalary"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally
		{
			new MyDatabase().closeConnection(rs, pst, con);
		}
		return s;
	}
	public List<Bill> viewBill()
	{
		List<Bill>list = new ArrayList();
		Connection con = new MyDatabase().getConnection();
		PreparedStatement pst = null;
		ResultSet rs = null;
		String sql = "select bid,bdescription,bamount, bby from bill";
		try {
			pst = con.prepareStatement(sql);
			rs = pst.executeQuery();
			while(rs.next())
			{
				Bill b = new Bill();
				b.setBid((int)rs.getObject("bid"));
				b.setBdescription((String)rs.getObject("bdescription"));
				b.setBamount((Double)rs.getObject("bamount"));
				b.setBby((String)rs.getObject("bby"));
				list.add(b);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally
		{
			new MyDatabase().closeConnection(rs, pst, con);
		}
		return list;
	}
	public int updateBill(Bill b)
	{
		int check = 0;
		Connection con = new MyDatabase().getConnection();
		PreparedStatement pst = null;
		String sql = "update bill set bdescription=?, bamount=?, bby=? where bid=?";
		try {
			pst = con.prepareStatement(sql);
			pst.setString(1, b.getBdescription());
			pst.setDouble(2, b.getBamount());
			pst.setString(3, b.getBby());
			pst.setInt(4, b.getBid());
			check=pst.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally
		{
			new MyDatabase().closeConnection(pst, con);
		}
		return check;
	}
	public Bill displayBillById(int id)
	{
		Bill b = null;
		Connection con = new MyDatabase().getConnection();
		PreparedStatement pst = null;
		ResultSet rs = null;
		String sql = "select bid,bdescription,bamount,bby from bill where bid=?";
		try {
			pst = con.prepareStatement(sql);
			pst.setInt(1, id);
			rs = pst.executeQuery();
			while(rs.next())
			{
				b = new Bill();
				b.setBid((int)rs.getObject("bid"));
				b.setBdescription((String)rs.getObject("bdescription"));
				b.setBamount((Double)rs.getObject("bamount"));
				b.setBby((String)rs.getObject("bby"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally
		{
			new MyDatabase().closeConnection(rs, pst, con);
		}
		return b;
	}
	public int deleteBill(int bid) // problem on this method
	{
		int check = 0;
		Connection con = new MyDatabase().getConnection();	
		PreparedStatement pst = null;
		String sql = "delete from bill where bid=?";
		try {
			pst = con.prepareStatement(sql);
			pst.setInt(1, bid);
			check = pst.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally
		{
			new MyDatabase().closeConnection(pst, con);
		}
		return check;
	}
	public int addProduct(Product p) 
	{
		int check = 0;
		Connection con = new MyDatabase().getConnection();
		PreparedStatement pst = null;
		String sql = "insert into product (pname,ptype,pdescription,pprice)values(?,?,?,?)";
		try {
			pst = con.prepareStatement(sql);
			pst.setString(1, p.getPname());
			pst.setString(2, p.getPtype());
			pst.setString(3, p.getPdescription());
			pst.setDouble(4, p.getPprice()); 
			check = pst.executeUpdate();	
		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally
		{
			new MyDatabase().closeConnection(pst, con);
		}
		return check;
	}
	public List<Product> viewProduct()
	{
		List<Product>list = new ArrayList();
		Connection con = new MyDatabase().getConnection();
		PreparedStatement pst = null;
		ResultSet rs = null;
		String sql = "select pid, pname, ptype, pdescription, pprice from product";
		try {
			pst = con.prepareStatement(sql);
			rs = pst.executeQuery();
			while(rs.next())
			{
				Product p = new Product();
				p.setPid((int)rs.getObject("pid"));
				p.setPname((String)rs.getObject("pname"));
				p.setPdescription((String)rs.getObject("pdescription"));
				p.setPprice((Double)rs.getObject("pprice"));
				list.add(p);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally
		{
			new MyDatabase().closeConnection(rs, pst, con);
		}
		return list;
	}
	public int updateProduct(Product p)
	{
		int check=0;
		Connection con = new MyDatabase().getConnection();
		PreparedStatement pst = null;
		String sql="update product set pname=?,ptype=?,pdescription=?,pprice=? where pid=?";
		try {
			pst = con.prepareStatement(sql);
			pst.setString(1, p.getPname());
			pst.setString(2, p.getPtype());
			pst.setString(3, p.getPdescription());
			pst.setDouble(4, p.getPprice());
			pst.setInt(5, p.getPid());
			check=pst.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally
		{
			new MyDatabase().closeConnection(pst, con);
		}
		return check;
	}
	public int deleteProductById(int id)
	{
		int check = 0;
		Connection con = new MyDatabase().getConnection();
		PreparedStatement pst = null;
		String sql="delete from Product where pid=?";
		try {
			pst = con.prepareStatement(sql);
			pst.setInt(1, id);
			check = pst.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally
		{
			new MyDatabase().closeConnection(pst, con);
		}
		return check;
	}
}
