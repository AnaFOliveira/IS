package mdb;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.EJB;
import javax.ejb.MessageDriven;
import javax.ejb.Schedule;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;

import jpa.Payment;
import session.SessionBankRemote;
import session.ejbremote;

/**
 * Message-Driven Bean implementation class for: mdb_bank
 */
@MessageDriven(
		activationConfig = { @ActivationConfigProperty(
				propertyName = "destination", propertyValue = "Bank7"), @ActivationConfigProperty(
				propertyName = "destinationType", propertyValue = "javax.jms.Queue")
		}, 
		mappedName = "Bank7")
public class mdb_bank implements MessageListener {
	@EJB (beanName="Session_bank")
	private SessionBankRemote sender_bank;
	
	@EJB (beanName="ejb")
	private ejbremote ejb;
	
	@Schedule(minute="5")
	public void executeTimer(){
		ejb.bank_update_payment_timer("pending","failed");
		List<Long> ids=ejb.bank_failed_ids("failed");
		for (int i = 0; i < ids.size(); i++) {
			ejb.store_update_purchase_order("failed",ids.get(i));
		}
		
	}

	
    /**
     * Default constructor. 
     */
    public mdb_bank() {
        // TODO Auto-generated constructor stub
    }
	
	/**
     * @see MessageListener#onMessage(Message)
     */
    public void onMessage(Message message) {
    	try {
    		
    		if (message.getStringProperty("type").equals("check_user")) {
				System.out.println("Chegou mensagem de login");
				String check = message.getBody(String.class);
				String result = ejb.verifyUser_bank(message.getIntProperty("card_number"));
				sender_bank.sendToClientUserCheck(result, message.getIntProperty("card_number"));
			}
    		
    		
    		if (message.getStringProperty("type").equals("new_bank")) {
				System.out.println("Chegou mensagem criar novo bank");
				List<String> info=message.getBody(List.class);
				ejb.new_bank(Integer.parseInt(info.get(1)), 100.50, info.get(0));
				
			}
			
			if(message.getStringProperty("type").equals("bank_credit")){
				
				
				System.out.println("Chegou a mensagem de pedir saldo");
				int card_number=message.getIntProperty("card_numb");
				System.out.println(card_number);
				sender_bank.sendToClientCredit(card_number);
			}
			
			if(message.getStringProperty("type").equals("bank_deposit")){
				System.out.println("Chegou a mensagem do deposito");
				int card_number=message.getIntProperty("card_numb");
				double amount=message.getBody(Double.class);
				ejb.bank_update_credit(amount, card_number);
				sender_bank.sendToClientInfo("deposit", card_number);
				
			}
    		
			if(message.getStringProperty("type").equals("completed_payments")) {
				System.out.println("Chegou a mensagem de visualizar pagamentos completos");
				int card_number=message.getIntProperty("card_numb");
				sender_bank.sendToBankPayment("completed",card_number);
				
				
			}
    		
			
			if(message.getStringProperty("type").equals("pending_available_payments")) {
				System.out.println("Chegou a mensagem de visualizar pagamentos pendentes available");
				int card_number=message.getIntProperty("card_numb");
				sender_bank.sendToBankPayment("pending_available",card_number);
				
				
			}
			if(message.getStringProperty("type").equals("failed_payments")) {
				System.out.println("Chegou a mensagem de visualizar pagamentos falhados");
				int card_number=message.getIntProperty("card_numb");
				sender_bank.sendToBankPayment("failed",card_number);
				
			}
    		
			if(message.getStringProperty("type").equals("pending_payments")) {
				System.out.println("Chegou a mensagem de visualizar pagamentos pendentes");
				int card_number=message.getIntProperty("card_numb");
				sender_bank.sendToBankPayment("pending",card_number);
				
			}
			
			if (message.getStringProperty("type").equals("new_payment")) {
				System.out.println("Chegou a mensagem de novo pagamento");
				int card_number=message.getIntProperty("card_numb");
				Payment payment=message.getBody(Payment.class);
				ejb.new_payment(payment, card_number);
				double credit=ejb.bank_credit(card_number);
				if (credit>=payment.getAmount()) {
					long id=ejb.new_payment_id(payment.getDate_time(), card_number);
					System.out.println(id);
					ejb.bank_update_credit_payment(payment.getAmount(), card_number);
					ejb.bank_update_payment("completed", id);
					List<String> list_ids=new ArrayList<>();
					list_ids.add(Long.toString(id));
					sender_bank.sendToStorePayment(list_ids, card_number);
				}
				else {
					List<String> list_ids=new ArrayList<>();
					sender_bank.sendToStorePayment(list_ids, card_number);
				}
				
				
			}
    		
    		
			

		} catch (JMSException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

        
        
    }

}