package org.jsp.dto;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;

import com.google.protobuf.compiler.PluginProtos.CodeGeneratorResponse.File;

public class Test {
	
   public static void main(String[] args) {
	 EntityManager manager=Persistence.createEntityManagerFactory("dev").createEntityManager();
	//Flights f= new Flights("AirIndia✈", "NewDelhi", "Mumbai", "12:00am", "3:00pm", "3:00h", 9233 );
	// manager.persist(f);
	// EntityTransaction t=manager.getTransaction();
	// t.begin();t.commit();
	 
	//1 "Indigo", "Bengluru", "New Delhi", "7:10am", "9:30am", "2:20h", 7233
	//2 "Indigo✈", "Bengluru", "Mumbai", "9:10am", "11:00am", "1:50h", 5233
	//3 Flights f=  new Flights("AirIndia✈", "NewDelhi", "Varanasi", "8:10pm", "9:10pm", "1:00h", 3233);
	 //4 Flights f= new Flights("AirIndia✈", "NewDelhi", "Mumbai", "12:00am", "3:00pm", "3:00h", 9233 );
	  //5  Flights f= new Flights( "Vistara✈", "Bengluru", "Chennai", "6:10pm", "8:10pm", "2:00h", 4233);
	    
    // SeatClass scl=new SeatClass();
     // scl.setClass_name("EconomyClass");
	//scl.setClass_name("PremiumClass");
 	//scl.setClass_name("BusinessClass");
   //  EntityTransaction t=manager.getTransaction();
   // manager.persist(scl);
 	// t.begin();t.commit();

       //FOR SEAT ENTRY
       
   Flights f =manager.find(Flights.class,1);
     SeatClass scl=manager.find(SeatClass.class,3);
     
	SeatFId1 s=new SeatFId1();
	
	s.setSclass(scl);
	s.setSeat_availability("available");
    s.setSeat_num("B3");
	  scl.getSeats().add(s);
	  f.getLseat().add(s);
	  s.setFlight(f);
	  EntityTransaction t=manager.getTransaction();
	  manager.persist(s);
	 t.begin();t.commit();

	
	  
	  

}
}
