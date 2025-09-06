package com.demo;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.dao.ProductDao;
import com.model.Bill;
import com.model.Product;
import com.model.Staff;

public class App 
{
    public static void main( String[] args )
    {
    	ArrayList<Product>list = new ArrayList();
    	int ch = 0;
    	do {
    		System.out.println("---------------Dmart Application---------------");
    		System.out.println("1. Generate Bill");
    		System.out.println("2. Search Product");
    		System.out.println("3. Admin Login");
    		System.out.println("4. Exit");
    		System.out.println("-----------------------------------------------");
    		System.out.println("Enter Your Choise:");
    		ch = new Scanner(System.in).nextInt();
    		switch(ch)
    		{
    		case 1:

    		{
    			System.out.println("Enter Staff Name:");
    			String sname=new Scanner(System.in).nextLine();
    			System.out.println("Enter Staff Password");
    			String spwd=new Scanner(System.in).nextLine();
    			if(new ProductDao().staffLogin(sname, spwd)) {
    			int ch2=0;
    			do
    			{
    				System.out.println("----------------Dmart Application-----------------");
    				System.out.println("1. Add Product");
    				System.out.println("2. Remove Product");
    				System.out.println("3. Generate Bill");
    				System.out.println("4. Clear");
    				System.out.println("5. Exit");
    				System.out.println("--------------------------------------------------");
    				System.out.println("Enter Your Choise");
    				ch2 = new Scanner(System.in).nextInt();
    				switch(ch2)
    				{
    				case 1:{
    					System.out.println("Added Product in Bill:"+list.size());
    					System.out.println("Enter Product Details");
    					String pname = new Scanner(System.in).nextLine();
    					for(Product p:new ProductDao().searchProductByAny(pname))
    					{
    						System.out.println(p);
    					}
    					if(new ProductDao().searchProductByAny(pname).size()!=0)
    					{
    						System.out.println("Enter Product ID:");
    						int id = new Scanner(System.in).nextInt();
    						Product p = new ProductDao().displayProductById(id);
    						list.add(p);
    						System.out.println("Product Added Successfully in Bill");
    					}
    					else
    					{
    						System.out.println("Product not Found!!!");
    					}
    					break;
    				}
    				case 2:{
    					for(Product p:list)
    					{
    						System.out.println(p.getPid()+"/t"+p.getPname()+"/t"+p.getPprice());
    					}
    					System.out.println("Enter Product ID");
    					int id = new Scanner(System.in).nextInt();
    					if(list.size()>1)
    					{
    						for(Product p:list)
    						{
    							if(id==p.getPid())
    								list.remove(p);
    						}
    					}
    					else
    					{
    						list.clear();
    					}
    					break; 
    				}
    				case 3:
    				{
    					new ProductDao().generateBill(list);
    					System.out.println("Generate Bill?       (y/n):");
    					char c = new Scanner(System.in).nextLine().charAt(0);
    					if(c=='y'||c=='Y')
    					{
    						Bill b = new Bill();
    						String s = "";
    						double amount=0;
    						for(Product s1:list)
    						{
    							s+=s1.getPname()+",";
    							amount+=s1.getPprice();
    						}
    						b.setBdescription(s);
    						b.setBamount(amount);
    						b.setBby(sname);
    						if(new ProductDao().insertBill(b)>0)
    						{
    							System.out.println("Bill Printed Successfully..");
    							list.clear();
    						}
    					}
    					break; 
    				}
    				case 4:
    					new ProductDao().generateBill(list);
    					System.out.println("Clear Bill?        (y/n):");
    					char c = new Scanner(System.in).nextLine().charAt(0);
    					if(c=='y'||c=='Y')
    						list.clear();
    					break;
    				}
    			}while(ch2<=4);
    			}
    			break;
    		}
    		case 2:
    		{
    			int ch2=0;
    			do
    			{
    				System.out.println("----------------Dmart Application-----------------");
    				System.out.println("1. Search Product By Name");
    				System.out.println("2. Search Product By Description");
    				System.out.println("3. Search Product By Price");
    				System.out.println("4. Search Product By Anything");
    				System.out.println("5. Exit");
    				System.out.println("--------------------------------------------------");
    				System.out.println("Enter Your Choise");
    				ch2 = new Scanner(System.in).nextInt();
    				switch(ch2)
    				{
    				case 1:
    				{
    					System.out.println("Enter The Product Name:");
    					String pname = new Scanner(System.in).nextLine();
    					for(Product p : new ProductDao().searchProductByName(pname))
    					{
    						System.out.println(p);
    					}
    					break;	
    				}
    				case 2:
    				{
    					System.out.println("Enter The Product Description:");
    					String pdescription = new Scanner(System.in).nextLine();
    					for(Product p : new ProductDao().searchProductByDescription(pdescription))
    					{
    						System.out.println(p);
    					}
    					break;
    				}
    				case 3:
    				{
    					System.out.println("Enter The Product Price ");
    					Double pprice = new Scanner(System.in).nextDouble();
    					for(Product p : new ProductDao().searchProductByPrice(pprice))
    					{
    						System.out.println(p);
    					}
    					break;
    				}
    				case 4:
    				{
    					System.out.println("Enter Anything About The Product");
    					String pword = new Scanner(System.in).nextLine();
    					for(Product p : new ProductDao().searchProductByAny(pword))
    					{
    						System.out.println(p);
    					}
    					break;
    				}
    				}
    			}while(ch2<=4);
        		
    		}
    			break;
    		case 3:
    		{
    			
    			int ch2=0;
    			do
    			{
    				System.out.println("----------------Dmart Application-----------------");
    				System.out.println("1. About Staff");
    				System.out.println("2. About Bill");
    				System.out.println("3. About Product");
    				System.out.println("6. Exit");
    				System.out.println("--------------------------------------------------");
    				System.out.println("Enter Your Choise");
    				ch2 = new Scanner(System.in).nextInt();
    				switch(ch2)
    				{
    				case 1:
    				{
    					int ch3=0;
    	    			do
    	    			{
    	    				System.out.println("----------------Dmart Application-----------------");
    	    				System.out.println("1. Add Staff");
    	    				System.out.println("2. View Staff");
    	    				System.out.println("3. Update Staff");
    	    				System.out.println("4. Remove Staff");
    	    				System.out.println("5. Exit");
    	    				System.out.println("--------------------------------------------------");
    	    				System.out.println("Enter Your Choise");
    	    				ch2 = new Scanner(System.in).nextInt();
    	    				switch(ch2)
    	    				{
    	    				case 1:
    	    				{
    	    					Staff s = new Staff();
    	    					s.setSid(10001);
    	    					s.setSname("Tejas Rokade");
    	    					s.setSpassword("Tejas@123");
    	    					s.setSemailid("rokadetejas@123");
    	    					s.setSsalary(20000);
    	    					if(new ProductDao().AddStaff(s)>0)
    	    						System.out.println("Staff Added Successfully");
    	    					
    	    					break;
    	    				}
    	    				case 2:
    	    				{
    	    					Staff s1 = new Staff();
    	    					for(Staff s: new ProductDao().viewStaff())
    	    					{
    	    						System.out.println(s);
    	    					}
    	    					break;
    	    				}
    	    				case 3:
    	    				{
    	    					Staff s = new ProductDao().displayStaffById(10001);
    	    					System.out.println(s);
    	    					s.setSpassword("Mayur@123");
    	    					if(new ProductDao().updateStaff(s)>0)
    	    					{
    	    						System.out.println("Record Updated Successfully");
    	    					}
    	    					break;
    	    				}
    	    				case 4:
    	    				{
    	    					Staff s = new Staff();
    	    					if(new ProductDao().deleteStaff(10001)>0)
    	    					{
    	    						System.out.println("Staff Removed Successfully");
    	    					}
    	    					break;
    	    				}
    	    				}
    	    			}while(ch2<=4);
    					break;
    				}
    				case 2:
    				{
    					int ch3=0;
    	    			do
    	    			{
    	    				System.out.println("----------------Dmart Application-----------------");
    	    				System.out.println("1. Add Bill");
    	    				System.out.println("2. View Bill");
    	    				System.out.println("3. Update Bill");
    	    				System.out.println("4. Delete Bill");
    	    				System.out.println("5. Exit");
    	    				System.out.println("--------------------------------------------------");
    	    				System.out.println("Enter Your Choise");
    	    				ch2 = new Scanner(System.in).nextInt();
    	    				switch(ch2)
    	    				{
    	    				case 1:
    	    				{
    	    					Bill b = new Bill();
    	    					b.setBid(3);
    	    					b.setBdescription("Books");
    	    					b.setBamount(143);
    	    					b.setBby("Manoj Pawar");
    	    					if(new ProductDao().AddBill(b)>0)
    	    					{
    	    						System.out.println("Bill Added Successfully");
    	    					}
    	    					break;
    	    				}
    	    				case 2:
    	    				{
    	    					Bill b1 = new Bill();
    	    					for(Bill b: new ProductDao().viewBill())
    	    					{
    	    						System.out.println(b);
    	    					}
    	    					break;
    	    				}
    	    				case 3:
    	    				{
        	    				Bill b = new ProductDao().displayBillById(1);
        	    				System.out.println(b);
        	    				b.setBamount(12143);
        	    				if(new ProductDao().updateBill(b)>0)
        	    				{
        	    					System.out.println("Record Updated Successfully");
        	    				}
    	    					break;
    	    				}
    	    				case 4:
    	    				{
    	    					Bill b = new Bill();
    	    					if(new ProductDao().deleteBill(3)>0)
    	    					{
    	    						System.out.println("Staff Removed Successfully");
    	    					}
    	    					
    	    					break;
    	    				}
    	    				}
    	    			}while(ch2<=4);
    					break;
    				}
    				case 3:
    				{
    					int ch3=0;
    	    			do
    	    			{
    	    				System.out.println("----------------Dmart Application-----------------");
    	    				System.out.println("1. Add Product");
    	    				System.out.println("2. View Product");
    	    				System.out.println("3. Update Product");
    	    				System.out.println("4. Delete Product");
    	    				System.out.println("5. Exit");													
    	    				System.out.println("--------------------------------------------------" );   	    				
    	    				System.out.println("Enter Your Choise");
    	    				ch2 = new Scanner(System.in).nextInt();
    	    				switch(ch2)
    	    				{
    	    				case 1:
    	    				{
    	    					 Product p = new Product();
    	    				        p.setPname("Arihant");
    	    				        p.setPtype("MCA Entrance");
    	    				        p.setPdescription("Full study material,PYQ Papers");
    	    				        p.setPprice(523);
    	    				        
    	    				        if(new ProductDao().addProduct(p)>0)
    	    				        {
    	    				        	System.out.println("Product inserted sucessfully");
    	    				        }
    	    					break;
    	    				}
    	    				case 2:
    	    				{
    	    					
    	    					for(Product p: new ProductDao().viewProduct()) //Product View
    	    					{
    	    						System.out.println(p);
    	    					}
    	    					break;
    	    				}
    	    				case 3:
    	    				{
    	    					Product p = new ProductDao().displayProductById(1);
        	    				System.out.println(p);
        	    				p.setPprice(15325);
        	    				if(new ProductDao().updateProduct(p)>0)
        	    				{
        	    					System.out.println("Product Updated Successfully");
        	    				}
    	    					break;
    	    				}
    	    				case 4:
    	    				{
    	    					Product p = new Product();
    	    					if(new ProductDao().deleteProductById(12)>0)
    	    					{
    	    						System.out.println("Product Delete Successfully");
    	    					}
    	    					break;
    	    				}
    	    				}
    	    			}while(ch2<=4);
    					break;
    				}
    				case 4:
    					break;
    				}
    			}while(ch2<=4);
    			
    			break;
    		}
    		}
    	}while(ch<=3);
    	
    }
}
