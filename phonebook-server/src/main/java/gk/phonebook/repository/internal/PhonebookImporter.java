package gk.phonebook.repository.internal;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionTemplate;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import gk.phonebook.facade.service.PhonebookEntry;
import gk.phonebook.repository.IPhonebookRepository;

public class PhonebookImporter implements InitializingBean{

	private static final String filename = "/phonebook-server/src/main/resources/META-INF/com.gk-software.ee-platform/phonebook/xml-data/phonebook.xml";
	private static final String entryTagName = "entry";
	private static final String idTagName = "id";
	private static final String nameTagName = "name";
	private static final String surnameTagName = "surname";
	private static final String phonenumberTagName = "phonenumber";

	private IPhonebookRepository phonebookRepository;
	private PlatformTransactionManager transactionManager;


	 @Required
	 public void setPropertiesRepository(IPhonebookRepository propertiesRepository) {
		 this.phonebookRepository = propertiesRepository;
	 }

	 @Required
	 public void setTransactionManager(PlatformTransactionManager transactionManager) {
		 this.transactionManager = transactionManager;
	 }

	 private List<PhonebookEntry> readXMLDataFileIntoList(){
		 List<PhonebookEntry> phonebook = null;
		 try{
			 File file = new File(filename);
			 DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			 DocumentBuilder db = dbf.newDocumentBuilder();
			 Document doc = db.parse(file);
			 doc.getDocumentElement().normalize();
			 NodeList nodeList = doc.getElementsByTagName(entryTagName);
			 phonebook = new ArrayList<>();
			 for(int i = 0; i < nodeList.getLength(); i++){
				 Node node = nodeList.item(i);
				 if(node.getNodeType() == Node.ELEMENT_NODE){
					 PhonebookEntry entry = new PhonebookEntry();
					 entry.setId(Integer.parseInt(((Element) node).getElementsByTagName(idTagName).item(0).getTextContent()));
					 entry.setName(((Element) node).getElementsByTagName(nameTagName).item(0).getTextContent());
					 entry.setSurname(((Element) node).getElementsByTagName(surnameTagName).item(0).getTextContent());
					 entry.setPhonenumber(((Element) node).getElementsByTagName(phonenumberTagName).item(0).getTextContent());
					 phonebook.add(entry);
				 }
			 }
		 }
		 catch (Exception e)
		 {
		 	e.printStackTrace();
		 }
		 return phonebook;
	 }

	 public void afterPropertiesSet() throws Exception {
	    TransactionTemplate transactionTemplate = new TransactionTemplate(transactionManager);
	    transactionTemplate.execute(new TransactionCallback<Void>() {
	      public Void doInTransaction(TransactionStatus status) {
	        phonebookRepository.removeAll();
	        phonebookRepository.store(readXMLDataFileIntoList());
	        return null;
	      }
	    });
	}

}
