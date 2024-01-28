package org.jsp.dto;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class AMBI {
	public static void main(String args[]){
		EntityManager manager=Persistence.createEntityManagerFactory("dev").createEntityManager();
		Admin ad=new Admin("Ambikesh Gond",8318594059l,"kumarambikesh34@gmail.com","ambi1234");
		manager.persist(ad);
		EntityTransaction t=manager.getTransaction();
		t.begin();
		t.commit();
	}

}
