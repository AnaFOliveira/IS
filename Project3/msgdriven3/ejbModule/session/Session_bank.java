package session;

import java.io.Serializable;
import java.util.List;

import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.ejb.Local;
import javax.ejb.LocalBean;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.inject.Inject;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSContext;
import javax.jms.JMSException;
import javax.jms.JMSProducer;
import javax.jms.ObjectMessage;
import javax.jms.TextMessage;

/**
 * Session Bean implementation class session_bank
 */
@Stateless
public class Session_bank implements SessionBankRemote {

	/**
	 * Default constructor.
	 */
	public Session_bank() {
		// TODO Auto-generated constructor stub
	}

	@Inject
	private JMSContext context;
	
	@EJB (beanName="ejb")
	private ejbremote ejb;


	@Resource(mappedName = "java:jboss/exported/jms/queue/Store7")
	private Destination store;

	@Resource(mappedName = "java:jboss/exported/jms/queue/Bank7")
	private Destination bank;

	@Resource(mappedName = "java:jboss/exported/jms/queue/Client7")
	private Destination client;
	
	@Resource(mappedName = "java:jboss/exported/jms/topic/Topic77")
	private Destination topic;
	
	
	
	
	public void sendToClientUserCheck(String option,int card_number) {

		try {
			JMSProducer messageProducer = context.createProducer();
			TextMessage msg = context.createTextMessage();
			msg.setText(option);
			msg.setStringProperty("type", "bank_user_checked");
			msg.setIntProperty("card_numb", card_number);
			messageProducer.send(client, msg);

		} catch (JMSException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	public void sendToBankPayment(String option,int card_number) {
    	if (option.equals("completed")) {
    		try {
    			JMSProducer messageProducer = context.createProducer();
    			ObjectMessage msg = context.createObjectMessage();
    			msg.setObject((Serializable) ejb.bank_payments(card_number,"completed"));
    			msg.setStringProperty("type","completed_payments");
    			msg.setIntProperty("card_numb", card_number);
    			messageProducer.send(bank,msg);

    			
    		} catch (JMSException e) {
    			// TODO Auto-generated catch block
    			e.printStackTrace();
    		}
			
		}
    	else if (option.equals("pending")) {
    		try {
    			JMSProducer messageProducer = context.createProducer();
    			ObjectMessage msg = context.createObjectMessage();
    			msg.setObject((Serializable) ejb.bank_payments(card_number,"pending"));
    			msg.setStringProperty("type","pending_payments");
    			msg.setIntProperty("card_numb", card_number);
    			messageProducer.send(bank,msg);

    			
    		} catch (JMSException e) {
    			// TODO Auto-generated catch block
    			e.printStackTrace();
    		}
			
		}
    	
    	else if (option.equals("pending_available")) {
    		try {
    			JMSProducer messageProducer = context.createProducer();
    			ObjectMessage msg = context.createObjectMessage();
    			msg.setObject((Serializable) ejb.bank_payments_pending_available(card_number,ejb.bank_credit(card_number)));
    			msg.setStringProperty("type","pending_available_payments");
    			msg.setIntProperty("card_numb", card_number);
    			messageProducer.send(bank,msg);

    			
    		} catch (JMSException e) {
    			// TODO Auto-generated catch block
    			e.printStackTrace();
    		}
			
		}
    	
    	
    	
    	
    	
    	
    	else if (option.equals("failed")) {
    		try {
    			JMSProducer messageProducer = context.createProducer();
    			ObjectMessage msg = context.createObjectMessage();
    			msg.setObject((Serializable) ejb.bank_payments(card_number,"failed"));
    			msg.setStringProperty("type","failed_payments");
    			msg.setIntProperty("card_numb", card_number);
    			messageProducer.send(bank,msg);

    			
    		} catch (JMSException e) {
    			// TODO Auto-generated catch block
    			e.printStackTrace();
    		}
		}
    	
    
    }
	
	public void sendToClientCredit(int card_number) {
		try {
			JMSProducer messageProducer = context.createProducer();
			ObjectMessage msg = context.createObjectMessage();
			msg.setObject(ejb.bank_credit(card_number));
			msg.setStringProperty("type","bank_credit");
			msg.setIntProperty("card_numb", card_number);
			messageProducer.send(client,msg);

			
		} catch (JMSException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void sendToClientInfo(String option,int card_number) {
		if (option.equals("deposit")) {
			try {
				JMSProducer messageProducer = context.createProducer();
				TextMessage msg = context.createTextMessage();
				msg.setText("Deposito efetuado com sucesso");
				msg.setStringProperty("type","bank_deposit");
				msg.setIntProperty("card_numb", card_number);
				messageProducer.send(client,msg);

				
			} catch (JMSException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
		else if (option.equals("payment_manual_success")) {
			try {
				JMSProducer messageProducer = context.createProducer();
				TextMessage msg = context.createTextMessage();
				msg.setText("O pagamento manual foi efetuado com sucesso");
				msg.setStringProperty("type","payment_manual_success");
				msg.setIntProperty("card_numb", card_number);
				messageProducer.send(client,msg);

				
			} catch (JMSException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
		else if (option.equals("payment_manual_failure")) {
			try {
				JMSProducer messageProducer = context.createProducer();
				TextMessage msg = context.createTextMessage();
				msg.setText("O pagamento manual rejeitado. Não tem credito.");
				msg.setStringProperty("type","payment_manual_failure");
				msg.setIntProperty("card_numb", card_number);
				messageProducer.send(client,msg);

				
			} catch (JMSException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
	}
		
		// ____________________________________________LOJA_________________________________________________
		public void sendToStorePayment(List<String> purchase_update,int card_number) {
	    	
	    		try {
	    			JMSProducer messageProducer = context.createProducer();
	    			ObjectMessage msg = context.createObjectMessage();
	    			msg.setObject((Serializable) purchase_update);
	    			msg.setStringProperty("type","purchase_update");
	    			msg.setIntProperty("card_numb", card_number);
	    			messageProducer.send(store,msg);

	    			
	    		} catch (JMSException e) {
	    			// TODO Auto-generated catch block
	    			e.printStackTrace();
	    		}
	    	}
}
