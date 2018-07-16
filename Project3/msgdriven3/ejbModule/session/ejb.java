package session;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import jpa.Bank;
import jpa.Music;
import jpa.Payment;
import jpa.Purchase;
import jpa.Vinyl;

@Stateless
public class ejb implements ejbremote {
	
	@PersistenceContext(name = "project_3")
	private EntityManager em;
	
	public String verifyUser_bank(int card_number) { 
		String jpql = "SELECT b FROM Bank b WHERE b.card_number='"+card_number+"'";
		Query q = em.createQuery(jpql);
		List<Bank> user = q.getResultList();
		String isthereanUser = null;
		if (user.size() == 0) {
			isthereanUser = "failed";
		} else {
			isthereanUser = user.get(0).getName();
		}
		return isthereanUser;
	}
	
	public List<Vinyl> total_data() {
		String jpql = "SELECT v FROM Vinyl v";
		Query q = em.createQuery(jpql);
		List<Vinyl> list_vinyl = q.getResultList();
		return list_vinyl;
	}
	
	public List<Vinyl> albumByTitle(String title) {
		String jpql = "SELECT v FROM Vinyl v WHERE lower(title)='" + title + "'";
		Query q = em.createQuery(jpql);
		List<Vinyl> list_vinyl = q.getResultList();
		return list_vinyl;
	}
	
	public List<Vinyl> albumByGenre(List<String> genre) {
		String jpql = "SELECT v FROM Vinyl v";
		Query q = em.createQuery(jpql);
		List<Vinyl> list_vinyl = q.getResultList();
		List<Vinyl> result_vinyl = new ArrayList<>();
		for (int i = 0; i < list_vinyl.size(); i++) {
			List<String> list_genre = list_vinyl.get(i).getGenre();
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
	
	public List<Vinyl> albumByArtist(List<String> artists) {
		String jpql = "SELECT v FROM Vinyl v";
		Query q = em.createQuery(jpql);
		List<Vinyl> list_vinyl = q.getResultList();
		List<Vinyl> result_vinyl = new ArrayList<>();
		for (int i = 0; i < list_vinyl.size(); i++) {
			for (int j = 0; j < list_vinyl.get(i).getArtist().size(); j++) {
				for (int j2 = 0; j2 < artists.size(); j2++) {
					if (list_vinyl.get(i).getArtist().get(j).toLowerCase().equals(artists.get(j2).toLowerCase())) {
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
	
	
	public void store_purchase_order(Purchase purchase) {
		em.persist(purchase);
		
	}
	
	public void store_update_purchase_order(String status, long id) {
		String jpql="UPDATE Purchase SET status='"+status+"' WHERE id='"+id+"'";
		Query q=em.createQuery(jpql);
		q.executeUpdate();
	}
	
	public double bank_credit(int card_number) {
		String jpql="SELECT b FROM Bank b WHERE b.card_number='"+card_number+"'";
		Query q=em.createQuery(jpql);
		List<Bank> list_banks=q.getResultList();
		double credit=list_banks.get(0).getCredit();
		return credit;
	}
	
	public void bank_update_credit(double amount, int card_number) {
		String jpql="UPDATE Bank SET credit=credit+'"+amount+"' WHERE card_number='"+card_number+"'";
		Query q=em.createQuery(jpql);
		q.executeUpdate();
	}
	
	public void bank_update_credit_payment(double amount, int card_number) {
		String jpql="UPDATE Bank SET credit=credit-'"+amount+"' WHERE card_number='"+card_number+"'";
		Query q=em.createQuery(jpql);
		q.executeUpdate();
	}
	
	public List<Purchase> store_pending_orders(int card_number,String status) {
		List<Purchase> list_purchase=new ArrayList<>();
		String jpql="Select P from Purchase P where card_number='"+card_number+"' and status='"+status+"'";
		Query q=em.createQuery(jpql);
		list_purchase=q.getResultList();
		return list_purchase;
	}
	
	public List<Purchase> store_complete_orders(int card_number,String status){
		List<Purchase> list_purchase=new ArrayList<>();
		String jpql="Select P from Purchase P where card_number='"+card_number+"' and status='"+status+"'";
		Query q=em.createQuery(jpql);
		list_purchase=q.getResultList();
		return list_purchase;
	}
	
	public List<Purchase> store_failed_orders(int card_number,String status){
		List<Purchase> list_purchase=new ArrayList<>();
		String jpql="Select P from Purchase P where card_number='"+card_number+"' and status='"+status+"'";
		Query q=em.createQuery(jpql);
		list_purchase=q.getResultList();
		return list_purchase;
	}
	
	public void new_vinyl(Vinyl vinyl) {
		List<Music> list_music=vinyl.getMusic();
		for(Music m : list_music)
			em.persist(m);
		em.persist(vinyl);
	}
	
	public void new_bank(int card_number,double value, String nome) {
		Bank bank=new Bank();
		List<Payment> list_payment=new ArrayList<>();
		bank.setCard_number(card_number);
		bank.setCredit(value);
		bank.setName(nome);
		bank.setPayment_history(list_payment);
		em.persist(bank);
	}
	
	public void new_payment(Payment payment,int card_number) {
		List<Bank> bank_users=new ArrayList<>();
		String jpql="Select B FROM Bank B WHERE card_number='"+card_number+"'";
		Query q=em.createQuery(jpql);
		bank_users=q.getResultList();
		for (Bank b : bank_users)
			if (b.getCard_number()==card_number) {
				List<Payment> list_payment= b.getPayment_history();
				list_payment.add(payment);
				b.setPayment_history(list_payment);
				for(Payment p : list_payment)
					em.persist(p);
				em.persist(b);	
			}
	}
	public Long new_payment_id(String date_time, int card_number) {
		String jpql="Select B from Bank B where card_number='"+card_number+"'";
		Query q=em.createQuery(jpql);
		List<Bank>banks=q.getResultList();
		List<Payment> payments=banks.get(0).getPayment_history();
		long id = 0;
		for(Payment p:payments)
			if (p.getDate_time().equals(date_time)) {
				id=p.getId();
			}
		return id;
		
	}
	
	
	public List<Payment> bank_payments(int card_number,String status) {
		List<Payment> list_payment=new ArrayList<>();
		String jpql="Select B from Bank B where card_number='"+card_number+"'";
		Query q=em.createQuery(jpql);
		List<Bank> list_banks=q.getResultList();
		List<Payment> list_payment_status=new ArrayList<>();
		if (list_banks.size()==0) {
			return list_payment_status;
		}
		else {
			Bank bank=list_banks.get(0);
			list_payment=bank.getPayment_history();
			if (list_payment.size()==0) {
				return list_payment_status;
			}
			else {
				for (int i = 0; i < list_payment.size(); i++) {
					if (list_payment.get(i).getStatus().equals(status)) {
						list_payment_status.add(list_payment.get(i));
					}
				}
				return list_payment_status;			
			}
		}
	}
	
	
	
	public void bank_update_payment(String status, long id) {
		String jpql="UPDATE Payment SET status='"+status+"' WHERE id='"+id+"'";
		Query q=em.createQuery(jpql);
		q.executeUpdate();
	}
	
	public void bank_update_payment_timer(String status,String status2) {
		String jpql="UPDATE Payment SET status='"+status+"' WHERE status='"+status2+"'";
		Query q=em.createQuery(jpql);
		q.executeUpdate();
	}
	
	public List<Long> bank_failed_ids(String status){
		String jpql="SELECT id FROM Payment where status='"+status+"'";
		Query q=em.createQuery(jpql);
		List<Long> ids=q.getResultList();
		return ids;
	}
	
	public List<Payment> bank_payments_pending_available(int card_number,double credit){
		List<Payment> list_payment=new ArrayList<>();
		String jpql="SELECT B FROM Bank B WHERE card_number='"+card_number+"'";
		Query q=em.createQuery(jpql);
		List<Bank> list_banks=q.getResultList();
		List<Payment> list_payment_status=new ArrayList<>();
		if (list_banks.size()==0) {
			return list_payment_status;
		}
		else {
			Bank bank=list_banks.get(0);
			list_payment=bank.getPayment_history();
			if (list_payment.size()==0) {
				return list_payment_status;
			}
			else {
				for (int i = 0; i < list_payment.size(); i++) {
					if (list_payment.get(i).getStatus().equals("pending") && list_payment.get(i).getAmount()<=credit) {
						list_payment_status.add(list_payment.get(i));
					}
				}
				return list_payment_status;			
			}
		}
	}

	
	
	
	
	
	
	
	
	

	
	
	
	
	

	

	

	
	
	
	
	
	
	
	
	
	
	
	

	
	
	
	
	
}