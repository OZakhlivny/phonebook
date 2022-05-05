package gk.phonebook.repository.internal;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Required;

import gk.phonebook.facade.service.PhonebookEntry;
import gk.phonebook.repository.IPhonebookDao;
import gk.phonebook.repository.IPhonebookRepository;
import gk.phonebook.repository.dbi.DbiPhonebook;
import gk.phonebook.repository.dbi.DbiPhonebookRow;

public class PhonebookRepository implements IPhonebookRepository {

	  private IPhonebookDao phonebookDao;

	  @Required
	  public void setPhonebookDao(IPhonebookDao phonebookDao) {
	    this.phonebookDao = phonebookDao;
	  }

	public void store(List<PhonebookEntry> phonebook) {
		if((phonebook != null) && (!phonebook.isEmpty())){
	    for (PhonebookEntry entry : phonebook) {
	    	DbiPhonebookRow row = new DbiPhonebookRow();
	        row.setKEY(entry.getId());
	        row.setNAME(entry.getName());
	        row.setSURNAME(entry.getSurname());
	        row.setPHONENUMBER(entry.getPhonenumber());
	        phonebookDao.store(row);
	      }
		}
	}

	public List<PhonebookEntry> load() {

	     DbiPhonebook phonebook = phonebookDao.load();
	     List<PhonebookEntry> list = new ArrayList<>();
	     for(DbiPhonebookRow row : phonebook){
	    	 list.add(mapEntry(row));
	     }

	     return list;
	}

	public PhonebookEntry loadPhonebookEntry(Integer key) {
		return mapEntry(phonebookDao.loadPhonebookEntry(key));
	}

	public void removeAll() {
		phonebookDao.removeAll();
	}

	private PhonebookEntry mapEntry(DbiPhonebookRow row){
		PhonebookEntry entry = null;
		if(row != null){
			entry = new PhonebookEntry();
	    	entry.setId(row.getKEY());
	    	entry.setName(row.getNAME());
	    	entry.setSurname(row.getSURNAME());
	    	entry.setPhonenumber(row.getPHONENUMBER());
		}

		return entry;
	}
}
