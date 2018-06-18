package com.scp.designpatterns;
import java.util.*;

public class TestVendingMachine {

	public static void main(String[] args) {
		
		AbstractVendingMachineFactory abFactory = null; 
		
		Scanner sc = new Scanner(System.in);
		do{
			System.out.println("\nWhat do you want..?? : ");
			System.out.println("\n1.Coffee\n2.Tea\n3.Exit\n");
			String choice = sc.next();
			
			switch(choice){
			case "coffee":
				abFactory = new CoffeeFactory();
				VendingMachine obj1 = VendingMachineFactory.getInstance(abFactory);
				System.out.println(obj1);
				break;
			case "tea":
				abFactory = new TeaFactory();
				VendingMachine obj2 = VendingMachineFactory.getInstance(abFactory);
				System.out.println(obj2);
				break;
			case "exit":
				System.out.println("Thank You..... Visit Again....");
				System.exit(0);
				break;
			default:
				System.out.println("Sorry.... We dont serve this....");	
			}
			
		}while(true);		
		
	}
}

abstract class VendingMachine{
	
	public String item;
	public int quantity;
	public double price;
	
	public abstract String getItem(String flv);
	public abstract int getQuantity();
	public abstract double getPrice();
	@Override
	public String toString() {
		return "VendingMachine [Item=" + getItem(item) + " , Price="  + getPrice() + "]";
	}
	
	
	
}

class Coffee extends VendingMachine{
	
	Scanner sc = new Scanner(System.in);
	String flavour;
	int quanti = 0;
	double totalBill = 0;

	{
		System.out.println("\n1.Cappuccino-200Rs \n2.Expresso-100Rs \n3.Mocha Latte-300Rs \n4.Caramel Latte Supreme-350Rs \n5.Normal Coffee-50Rs\n");
		System.out.println("Which Flavour ?? ");
		flavour = sc.next();
		
	}
	

	@Override
	public String getItem(String flv) {
		return flavour;
	}

	@Override
	public int getQuantity() {
		System.out.println("Enter Quantity : ");
		quanti = sc.nextInt();
		return quanti;
	}

	@Override
	public double getPrice() {
		
		switch(flavour){
			case "cappuccino":
				getItem(flavour);
				totalBill = getQuantity()*200;
				break;
			case "expresso":
				getItem(flavour);
				totalBill = getQuantity()*100;
				break;
			case "mocha_latte":
				getItem(flavour);
				totalBill = getQuantity()*300;
				break;
			case "caramel_latte_supreme":
				getItem(flavour);
				totalBill = getQuantity()*350;
				break;
			case "normal_coffee":
				getItem(flavour);
				totalBill = getQuantity()*50;
				break;
			default:
				System.out.println("Sorry... You didnt select Flavour...");
				System.exit(0);
		}
		
		return totalBill;
	}	
	
}

class Tea extends VendingMachine{
	
	Scanner sc = new Scanner(System.in);
	String flavour;
	int quanti = 0;
	double totalBill = 0;

	{
		System.out.println("\n1.Green Tea-50Rs \n2.Black Tea-20Rs \n3.Lemon Tea-30Rs \n4.Normal Tea-10Rs\n");
		System.out.println("Which Flavour ?? ");
		flavour = sc.next();
		
	}
	
	@Override
	public String getItem(String flv) {
		return flavour;
	}

	@Override
	public int getQuantity() {
		System.out.println("Enter Quantity : ");
		quanti = sc.nextInt();
		return quanti;
	}

	@Override
	public double getPrice() {
		
		switch(flavour){
			case "green_tea":
				getItem(flavour);
				totalBill = getQuantity()*50;
				break;
			case "black_tea":
				getItem(flavour);
				totalBill = getQuantity()*20;
				break;
			case "lemon_tea":
				getItem(flavour);
				totalBill = getQuantity()*30;
				break;
			case "normal_tea":
				getItem(flavour);
				totalBill = getQuantity()*10;
				break;
			default:
				System.out.println("Sorry... You didnt select Flavour...");
				System.exit(0);
		}
		
		return totalBill;
	}	
	

	
	
}

interface AbstractVendingMachineFactory{
	
	public VendingMachine getItem();
}

class CoffeeFactory implements AbstractVendingMachineFactory{
	
	@Override
	public VendingMachine getItem() {
		return new Coffee();
		//return null;
	}
	
}

class TeaFactory implements AbstractVendingMachineFactory{

	@Override
	public VendingMachine getItem() {
		return new Tea();
		//return null;
	}
	
}

class VendingMachineFactory{
	
	public static VendingMachine getInstance(AbstractVendingMachineFactory abFactory){
		return abFactory.getItem();
	}
}