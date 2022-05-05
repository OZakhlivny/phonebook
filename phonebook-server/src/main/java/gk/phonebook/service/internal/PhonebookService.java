package gk.phonebook.service.internal;

import java.util.List;

import org.springframework.beans.factory.annotation.Required;

import gk.phonebook.facade.service.IPhonebookService;
import gk.phonebook.facade.service.PhonebookEntry;
import gk.phonebook.repository.IPhonebookRepository;

public class PhonebookService implements IPhonebookService {
	private IPhonebookRepository phonebookRepository;

  @Required
  public void setPropertiesRepository(IPhonebookRepository phonebookRepository) {
    this.phonebookRepository = phonebookRepository;
  }

	@Override
	public List<PhonebookEntry> getPhonebook() {
		return phonebookRepository.load();
	}

	@Override
	public PhonebookEntry getPhonebookEntry(Integer key) {
		return phonebookRepository.loadPhonebookEntry(key);
	}

}
