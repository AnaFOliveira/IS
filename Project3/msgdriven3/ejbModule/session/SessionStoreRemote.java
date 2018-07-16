 package session;

 import java.util.List;

import javax.ejb.Remote;

import jpa.Payment;

 @Remote
 public interface SessionStoreRemote {
	 public void sendToClientVinyls(String option,int card_number,String title,List<String> genre,List<String> artist);
	 public void sendToClientPurchase(String option,int card_number);
	 public void sendToStoreOrders(String option, int card_number);
	 public void sendToClientInfo(String option,int card_number);
	 public void sendToBankPayment(int card_number,Payment payment);
	 public void sendToStore_pendingOrders(int card_number);
	 public void sendToStore_Vinyl(String confirmação);
	 public void send_news_topic(String news);

 }
