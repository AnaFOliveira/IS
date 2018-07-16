package session;

import java.util.List;

import javax.ejb.Remote;

import jpa.Bank;
import jpa.Payment;
import jpa.Purchase;
import jpa.Vinyl;

@Remote
public interface ejbremote {
	public String verifyUser_bank(int card_number);
	public List<Vinyl> total_data();
	public List<Vinyl> albumByTitle(String title);
	public List<Vinyl> albumByGenre(List<String> genre);
	public List<Vinyl> albumByArtist(List<String> artists);
	public void store_purchase_order(Purchase purchase);
	public void store_update_purchase_order(String status, long id);
	public double bank_credit(int card_number);
	public void bank_update_credit(double amount, int card_number);
	public List<Purchase> store_pending_orders(int card_number,String status);
	public List<Purchase> store_complete_orders(int card_number,String status);
	public List<Purchase> store_failed_orders(int card_number,String status);
	public void new_vinyl(Vinyl vinyl);
	public void new_bank(int card_number,double value, String nome);
	public void new_payment(Payment payment,int card_number);
	public List<Payment> bank_payments(int card_number,String status);
	public void bank_update_payment(String status, long id);
	public void bank_update_credit_payment(double amount, int card_number);
	public Long new_payment_id(String date_time, int card_number);
	public List<Payment> bank_payments_pending_available(int card_number,double credit);
	public void bank_update_payment_timer(String status,String status2);
	public List<Long> bank_failed_ids(String status);
	

}
