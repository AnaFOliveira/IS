package session;

import java.util.List;

import javax.ejb.Remote;
import javax.naming.NamingException;

@Remote
public interface SessionBankRemote {
	public void sendToClientUserCheck(String option,int card_number);
	public void sendToBankPayment(String option,int card_number);
	public void sendToClientCredit(int card_number);
	public void sendToClientInfo(String option,int card_number);
	public void sendToStorePayment(List<String> purchase_update,int card_number);

}
