package PageObjects;

import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.mail.BodyPart;
import javax.mail.Flags;
import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Store;
import javax.mail.search.SubjectTerm;

public class EmailHelper {
	public static String getVerifyUrl(String email, String password) throws Exception {
		Properties props = System.getProperties();
		props.setProperty("mail.store.protocol", "imaps");

		Session session = Session.getDefaultInstance(props, null);
		Store store = session.getStore("imaps");
		store.connect("imap.gmail.com", email, password);

		Folder folder = store.getFolder("INBOX");
		folder.open(Folder.READ_WRITE);

		Message[] messages = null;
		boolean isMailFound = false;
		Message mailFromProx = null;

		messages = folder.search(new SubjectTerm("Verify your email"), folder.getMessages());

		for (Message mail : messages) {
			if (!mail.isSet(Flags.Flag.SEEN)) {
				mailFromProx = mail;
				isMailFound = true;
			}
		}

		if (!isMailFound) {
			throw new Exception("Could not find new the verification mail");

			// Read the content of mail and get password
		} else {
			Multipart multipart = (Multipart) mailFromProx.getContent();
	        BodyPart part = multipart.getBodyPart(0);
	        String contentReturn = part.getContent().toString();
	        
	        Pattern pattern = Pattern.compile("(.*?)(\\d+)(.*)");
	        Matcher matcher = pattern.matcher(contentReturn);
	        while(matcher.find()) {
	        	String urlStr = matcher.group();
	        	if(urlStr.startsWith("http"))
	        		return urlStr;
	        }
	        /*matcher.find();
	        if(matcher.find())
		        return matcher.group(0);
	        else*/
	        	return null;
		}
	}
}
