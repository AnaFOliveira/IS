package session;

import java.io.Serializable;
import java.util.List;

import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.ejb.Local;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.inject.Inject;
import javax.jms.Destination;
import javax.jms.JMSContext;
import javax.jms.JMSException;
import javax.jms.JMSProducer;
import javax.jms.ObjectMessage;
import javax.jms.TextMessage;

import jpa.Payment;
import jpa.Vinyl;

/**
 * Session Bean implementation class Session_store
 */
@Stateless
public class Session_store implements SessionStoreRemote {

	@Inject
	private JMSContext context;

	@EJB(beanName = "ejb")
	private ejbremote ejb;

	// @Resource(mappedName = "jms/RemoteConnectionFactory")
	// private static ConnectionFactory connectionFactory;

	@Resource(mappedName = "java:jboss/exported/jms/queue/Store7")
	private Destination store;

	@Resource(mappedName = "java:jboss/exported/jms/queue/Bank7")
	private Destination bank;

	@Resource(mappedName = "java:jboss/exported/jms/queue/Client7")
	private Destination client;
	
	@Resource(mappedName = "java:jboss/exported/jms/topic/Topic77")
	private Destination topic;

	// @Resource(mappedName =
	// "java:jboss/exported/project3ear/project3ejb/ejb!project3ejb.ejbremote")
	// private Destination something;

	/**
	 * Default constructor.
	 */
	public Session_store() {
		// TODO Auto-generated constructor stub
	}

	public void sendToClientVinyls(String option, int card_number, String title, List<String> genre,
			List<String> artist) {
		if (option.equals("all")) {
			try {
				JMSProducer messageProducer = context.createProducer();
				ObjectMessage msg = context.createObjectMessage();
				msg.setObject((Serializable) ejb.total_data());
				msg.setStringProperty("type", "all_vinyls");
				msg.setIntProperty("card_numb", card_number);
				messageProducer.send(client, msg);

			} catch (JMSException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

		else if (option.equals("title")) {
			try {
				JMSProducer messageProducer = context.createProducer();
				ObjectMessage msg = context.createObjectMessage();
				
				msg.setObject((Serializable) ejb.albumByTitle(title));
				msg.setStringProperty("type", "title_vinyls");
				msg.setIntProperty("card_numb", card_number);
				messageProducer.send(client, msg);

			}

			catch (JMSException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

		else if (option.equals("genre")) {
			try {
				JMSProducer messageProducer = context.createProducer();
				ObjectMessage msg = context.createObjectMessage();

				msg.setObject((Serializable) ejb.albumByGenre(genre));
				msg.setStringProperty("type", "genre_vinyls");
				msg.setIntProperty("card_numb", card_number);
				messageProducer.send(client, msg);

			}

			catch (JMSException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

		else if (option.equals("artist")) {
			try {
				JMSProducer messageProducer = context.createProducer();
				ObjectMessage msg = context.createObjectMessage();

				msg.setObject((Serializable) ejb.albumByArtist(artist));
				msg.setStringProperty("type", "artist_vinyls");
				msg.setIntProperty("card_numb", card_number);
				messageProducer.send(client, msg);

			}

			catch (JMSException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

	}

	public void sendToClientPurchase(String option, int card_number) {
		if (option.equals("completed")) {
			try {
				JMSProducer messageProducer = context.createProducer();
				ObjectMessage msg = context.createObjectMessage();
				msg.setObject((Serializable) ejb.store_complete_orders(card_number,"completed"));
				msg.setStringProperty("type", "completed_purchases");
				msg.setIntProperty("card_numb", card_number);
				messageProducer.send(client, msg);

			} catch (JMSException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		} else if (option.equals("pending")) {
			try {
				JMSProducer messageProducer = context.createProducer();
				ObjectMessage msg = context.createObjectMessage();
				msg.setObject((Serializable) ejb.store_pending_orders(card_number,"pending"));
				msg.setStringProperty("type", "pending_purchases");
				msg.setIntProperty("card_numb", card_number);
				messageProducer.send(client, msg);

			} catch (JMSException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		} else if (option.equals("failed")) {
			try {
				JMSProducer messageProducer = context.createProducer();
				ObjectMessage msg = context.createObjectMessage();
				msg.setObject((Serializable) ejb.store_failed_orders(card_number,"failed"));
				msg.setStringProperty("type", "failed_purchases");
				msg.setIntProperty("card_numb", card_number);
				messageProducer.send(client, msg);

			} catch (JMSException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public void sendToStoreOrders(String option, int card_number) {
		if (option.equals("completed")) {
			try {
				JMSProducer messageProducer = context.createProducer();
				ObjectMessage msg = context.createObjectMessage();
				msg.setObject((Serializable) ejb.store_complete_orders(card_number,"completed"));
				msg.setStringProperty("type", "completed_purchases");
				msg.setIntProperty("card_numb", card_number);
				messageProducer.send(store, msg);

			} catch (JMSException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		} else if (option.equals("pending")) {
			try {
				JMSProducer messageProducer = context.createProducer();
				ObjectMessage msg = context.createObjectMessage();
				msg.setObject((Serializable) ejb.store_pending_orders(card_number,"pending"));
				msg.setStringProperty("type", "pending_purchases");
				msg.setIntProperty("card_numb", card_number);
				messageProducer.send(store, msg);

			} catch (JMSException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		} else if (option.equals("failed")) {
			try {
				JMSProducer messageProducer = context.createProducer();
				ObjectMessage msg = context.createObjectMessage();
				msg.setObject((Serializable) ejb.store_failed_orders(card_number,"failed"));
				msg.setStringProperty("type", "failed_purchases");
				msg.setIntProperty("card_numb", card_number);
				messageProducer.send(store, msg);

			} catch (JMSException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}


	public void sendToClientInfo(String option, int card_number) {
		if (option.equals("purchase_success")) {
			try {
				JMSProducer messageProducer = context.createProducer();
				TextMessage msg = context.createTextMessage();
				msg.setText("Pedido registado com sucesso");
				msg.setStringProperty("type", "purchase_success");
				msg.setIntProperty("card_numb", card_number);
				messageProducer.send(client, msg);

			} catch (JMSException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

		else if (option.equals("purchase_failure")) {
			try {
				JMSProducer messageProducer = context.createProducer();
				TextMessage msg = context.createTextMessage();
				msg.setText("O pedido não é possivel ser efetuado. Erro no/s vinyl/s inseridos");
				msg.setStringProperty("type", "purchase_failure");
				msg.setIntProperty("card_numb", card_number);
				messageProducer.send(client, msg);

			} catch (JMSException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

		else if (option.equals("payment_success")) {
			try {
				JMSProducer messageProducer = context.createProducer();
				TextMessage msg = context.createTextMessage();
				msg.setText("O pagamento foi efetuado com sucesso");
				msg.setStringProperty("type", "payment_success");
				msg.setIntProperty("card_numb", card_number);
				messageProducer.send(client, msg);

			} catch (JMSException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

		else if (option.equals("payment_pending")) {
			try {
				JMSProducer messageProducer = context.createProducer();
				TextMessage msg = context.createTextMessage();
				msg.setText("O pagamento não foi efetuado com sucesso. Pagamento em espera. Contactar banco");
				msg.setStringProperty("type", "payment_pending");
				msg.setIntProperty("card_numb", card_number);
				messageProducer.send(client, msg);

			} catch (JMSException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

	}
	
	

	// ____________________________BANCO_______________________________________________
	public void sendToBankPayment(int card_number, Payment payment) {
		try {
			JMSProducer messageProducer = context.createProducer();
			ObjectMessage msg = context.createObjectMessage();
			msg.setObject(payment);
			msg.setStringProperty("type", "new_payment");
			msg.setIntProperty("card_numb", card_number);
			messageProducer.send(bank, msg);

		} catch (JMSException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	
	
	//______________________________STORE___________________________________________________
	public void sendToStore_pendingOrders(int card_number)
	{
		
		try {
			JMSProducer messageProducer = context.createProducer();
			ObjectMessage msg = context.createObjectMessage();
			msg.setObject((Serializable) ejb.store_pending_orders(card_number, "pending"));
			msg.setStringProperty("type", "pending_orders");
			msg.setIntProperty("card_numb", card_number);
			messageProducer.send(store, msg);

		} catch (JMSException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	public void sendToStore_Vinyl(String confirmação)
	{
		
		try {
			JMSProducer messageProducer = context.createProducer();
			TextMessage msg = context.createTextMessage();
			msg.setText(confirmação);
			msg.setStringProperty("type", "vinyl_success");
			messageProducer.send(store, msg);

		} catch (JMSException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	//___________________________TOPIC______________________________________________________
	public void send_news_topic(String news)
	{
		
		try {
			JMSProducer messageProducer = context.createProducer();
			TextMessage msg = context.createTextMessage();
			msg.setText(news);
			msg.setStringProperty("type", "vinyl_news");
			messageProducer.send(topic, msg);

		} catch (JMSException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	

}
