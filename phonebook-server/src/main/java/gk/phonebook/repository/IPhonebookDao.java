package gk.phonebook.repository;

import gk.phonebook.repository.dbi.DbiPhonebook;
import gk.phonebook.repository.dbi.DbiPhonebookRow;

public interface IPhonebookDao {

	  public void store(DbiPhonebookRow row);

	  public DbiPhonebook load();

	  public DbiPhonebookRow loadPhonebookEntry(Integer key);

	  public void removeAll();

}
