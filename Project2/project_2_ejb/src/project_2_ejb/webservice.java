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
		String jpql = "SELECT v FROM Vinyl v WHERE lower(title)='" + title + "'";
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
		return list_rating_total;
	}

	public List<Vinyl> albumBygenre(List<String> genre) {
		String jpql = "SELECT v FROM Vinyl v";
		Query q = em.createQuery(jpql);
		List<Vinyl> list_vinyl = q.getResultList();
		List<Vinyl> result_vinyl = new ArrayList<>();
		for (int i = 0; i < list_vinyl.size(); i++) {
			List<String> list_genre=list_vinyl.get(i).getGenre();
			for (int j = 0; j < list_genre.size(); j++) {
				for (int j2 = 0; j2 < genre.size(); j2++) {
					if (list_genre.get(j).toLowerCase().equals(genre.get(j2).toLowerCase())) {
						result_vinyl.add(list_vinyl.get(i));
					}
				}
			}
		}
		for (int i = 0; i < result_vinyl.size(); i++) {
			if (i == result_vinyl.size() - 1) {
				break;
			}
			for (int j = i + 1; j < result_vinyl.size(); j++) {

				if (result_vinyl.get(i).getId().equals(result_vinyl.get(j).getId())) {
					result_vinyl.remove(j);
				}
			}
		}
		
		
		

		return result_vinyl;
	}

}
