package loader3;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import classes.Bank;
import classes_xml.Catalog;
import classes_xml.Music;
import classes_xml.Vinyl;


public class Loader {
	protected static Catalog catalog = Unmarshall.unmarshall("Projeto1_xml");
	protected static List<Vinyl> list_vinyl = catalog.getVinyl();
	public static void main(String[] args){
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("project_3");
		EntityManager em = emf.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		for (Vinyl v : list_vinyl)
			for (Music music: v.getMusic())
				em.persist(music);
		tx.commit();
		tx.begin();
		for (Vinyl v : list_vinyl)
			em.persist(v);
		tx.commit();
		
		em.close();
		emf.close();
	}

}