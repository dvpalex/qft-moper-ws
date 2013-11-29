package br.com.ninb.moper.dal;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;

public class EntityManagerUtil {

	private static EntityManager enMan = null;
	
	public static EntityManager getEntityManager(){
		
		if(enMan == null || !enMan.isOpen()){
			enMan = Persistence.createEntityManagerFactory("").createEntityManager();
		}
		
		return enMan;
	}
	
}
