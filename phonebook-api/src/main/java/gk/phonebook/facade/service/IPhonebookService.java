package gk.phonebook.facade.service;

import java.util.List;

public interface IPhonebookService {

	public List<PhonebookEntry> getPhonebook();

	public PhonebookEntry getPhonebookEntry(Integer key);

}
