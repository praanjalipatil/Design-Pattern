package com.scp.designpatterns;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.*;

public class SingleToneDesignPattern {
	
	public static void main(String[] args) throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException{
		
		Scanner sc =  new Scanner(System.in);

		do{
			System.out.println("\n1.Eager Initialization \n2.Satic Block Initialization \n3.LazyInitialization \n4.Thread Safe Singletone \n5.DoubleChecking \n6.BillPugh \n7.EnumSingleton \n8.Break Double Checking Using Reflection Mechanism \n9.Exit \n");
			System.out.println("Enter Your Choice : ");
			int ch = sc.nextInt();
			
			switch(ch){
			case 1:
				EagerInitialization ei1 = EagerInitialization.getInstance();
				EagerInitialization ei2 = EagerInitialization.getInstance();
				System.out.println(ei1==ei2);
				break;
			case 2:
				StaticBlockInitialization sb1 = StaticBlockInitialization.getInstance();
				StaticBlockInitialization sb2 = StaticBlockInitialization.getInstance();
				System.out.println(sb1==sb2);
				break;
			case 3:
				LazyInitialization li1 = LazyInitialization.getInstance();
				LazyInitialization li2 = LazyInitialization.getInstance();
				System.out.println(li1==li2);
				break;
			case 4:
				ThreadSafe ts1 = ThreadSafe.getInstance();
				ThreadSafe ts2 = ThreadSafe.getInstance();
				System.out.println(ts1==ts2);
				break;
			case 5:
				DoubleChecking dc1 = DoubleChecking.getInstance();
				DoubleChecking dc2 = DoubleChecking.getInstance();
				System.out.println(dc1==dc2);
				break;
			case 6:
				BillPugh bp1 = BillPugh.getInstance();
				BillPugh bp2 = BillPugh.getInstance();
				System.out.println(bp1==bp2);
				break;
			case 7:
				EnumSingleton eob1 = EnumSingleton.ob;
				EnumSingleton eob2 = EnumSingleton.ob;
				System.out.println(eob1==eob2);
				break;
			case 8:
				ReflectionMechanism rm = ReflectionMechanism.test();
				break;
			case 9:
				System.exit(0);
				break;
			default :
				System.out.println("Wrong Choice");
				System.exit(0);		
			}
			
		}while(true);
		
	}

}

class EagerInitialization{
	//Eager Initialization
	
		private static final EagerInitialization ob = new EagerInitialization();
		
		private EagerInitialization(){
			System.out.println("Eager Initialization");
		}
		
		public static EagerInitialization getInstance(){
			return ob;
		}
}

class StaticBlockInitialization{
	//Static Block Initialization
	
		private static final StaticBlockInitialization ob;
		
		private StaticBlockInitialization(){
			System.out.println("Eager Initialization Using Static Block");
		}
		
		static{
			ob = new StaticBlockInitialization();
		}
		
		public static StaticBlockInitialization getInstance()
		{
			return ob;
		}
}

class LazyInitialization{
	//Lazy Initialization
	
		private static LazyInitialization ob;
		
		private LazyInitialization(){
			System.out.println("Lazy Initialization");
		}
		
		public static LazyInitialization getInstance(){
			if(ob==null){
				ob = new LazyInitialization();
			}
			return ob;
		}
}

class ThreadSafe{
	//Thread-Safe SingleTone
	
		private static ThreadSafe ob;
		
		private ThreadSafe(){
			System.out.println("Thread Safe Singleton");
		}
		
		public static synchronized ThreadSafe getInstance(){
			if(ob==null){
				ob = new ThreadSafe();
			}
			return ob;
		}
}

class DoubleChecking{
	//Double Checked Locking

	private static DoubleChecking ob;
	
	private DoubleChecking(){
		System.out.println("Double Checking");
	}
	
	public static DoubleChecking getInstance(){
		if(ob==null){
			synchronized (DoubleChecking.class) {
				if(ob==null){
					ob = new DoubleChecking();
				}
			}
		}
		return ob;
	}
}

class BillPugh{
	
	private BillPugh(){
		System.out.println("Bill Pugh");
	}
	
	private static final class InnerClass{
		private static final BillPugh ob = new BillPugh();
	}
	
	public static BillPugh getInstance(){
		return InnerClass.ob;
	}
}

enum EnumSingleton{
	ob;
	private EnumSingleton() {
		System.out.println("Enum");
	}
}

class ReflectionMechanism{
	public static ReflectionMechanism test() throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException{
		//Breaking Singleton using Reflection Mechanism
		
				DoubleChecking oldObj = DoubleChecking.getInstance();
				DoubleChecking newObj=null;
				Constructor[] dcOb = DoubleChecking.class.getConstructors();
				for (Constructor constructor : dcOb) {
					constructor.setAccessible(true);
					newObj = (DoubleChecking)constructor.newInstance();
				}
				
				System.out.println(oldObj==newObj);
				
				return null;
	}
	
}