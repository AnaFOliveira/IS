package project_2_ejb;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.From;

import classes.Vinyl;

@Stateless
public class webservice implements webserviceremote {
	@PersistenceContext(name = "project_2")
	private EntityManager em;

	public List<Vinyl> total_data() {
		String jpql = "SELECT v FROM Vinyl v";
		Query q = em.createQuery(jpql);
		List<Vinyl> list_vinyl = q.getResultList();
		return list_vinyl;
	}

	public List<String> titles() {
		String jpql = "SELECT v.title FROM Vinyl v";
		Query q = em.createQuery(jpql);
		List<String> list_titles = q.getResultList();
		return list_titles;

	}

	public List<Vinyl> album_getinfo(String title) {
		String jpql = "SELECT v FROM Vinyl v WHERE title='" + title + "'";
		Query q = em.createQuery(jpql);
		List<Vinyl> list_vinyl = q.getResultList();
		return list_vinyl;
	}

	public List<String> artists() {
		String jpql = "SELECT v FROM Vinyl v";
		Query q = em.createQuery(jpql);
		List<Vinyl> list_vinyl = q.getResultList();
		List<String> artists = new ArrayList<>();
		for (int i = 0; i < list_vinyl.size(); i++) {
			artists.addAll(list_vinyl.get(i).getArtist());
		}
		for (int i = 0; i < artists.size(); i++) {
			if (i == artists.size() - 1) {
				break;
			}
			for (int j = i + 1; j < artists.size(); j++) {

				if (artists.get(i).equals(artists.get(j))) {
					artists.remove(j);
				}
			}
		}

		return artists;
	}

	public List<Vinyl> scoresabovex(BigDecimal x) {
		String jpql = "SELECT v FROM Vinyl v WHERE rating>'" + x + "'";
		Query q = em.createQuery(jpql);
		List<Vinyl> list_rating_total = q.getResultList();
		// List<BigDecimal> list_rating = new ArrayList<>();
		/*
		 * for (int i = 0; i < list_rating.size(); i++) { if
		 * (list_rating_total.get(i).compareTo(x)==1) {
		 * list_rating.add(list_rating_total.get(i)); } }
		 */
		return list_rating_total;
	}

	public List<Vinyl> albumBygenre(List<String> genre) {
		String jpql = "SELECT v FROM Vinyl v";
		Query q = em.createQuery(jpql);
		List<Vinyl> list_vinyl = q.getResultList();
		List<Vinyl> result_vinyl = new ArrayList<>();
		List<String> list_genre = new ArrayList<>();
		int contador = 0;
		for (int i = 0; i < list_vinyl.size(); i++) {
			for (int j = 0; j < list_vinyl.get(i).getGenre().size(); j++) {

				for (int k = 0; k < genre.size(); k++) {

					if (list_vinyl.get(i).getGenre().get(j).toLowerCase().equals(list_genre.get(k).toLowerCase())) {
						result_vinyl.add(list_vinyl.get(i));
						contador++;
					}
					if (contador >= 1) {
						k = genre.size();
						j = list_vinyl.get(i).getGenre().size();
						contador = 0;
					}
				}
			}
		}
		return result_vinyl;
	}

}
