package gk.phonebook.repository;

import java.util.List;

import gk.phonebook.facade.service.PhonebookEntry;

public interface IPhonebookRepository {
	  public void store(List<PhonebookEntry> phonebook);

	  public List<PhonebookEntry> load();

	  public PhonebookEntry loadPhonebookEntry(Integer key);

	  public void removeAll();
}
