package gk.phonebook.facade.service.internal;

import java.util.List;

import org.springframework.beans.factory.annotation.Required;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.annotation.Propagation;

import gk.phonebook.facade.service.IPhonebookService;
import gk.phonebook.facade.service.PhonebookEntry;
import gk.phonebook.repository.IPhonebookRepository;

@Transactional(propagation=Propagation.REQUIRED, readOnly = true)
public class PhonebookService implements IPhonebookService {
	private IPhonebookRepository phonebookRepository;

  @Required
  public void setPhonebookRepository(IPhonebookRepository phonebookRepository) {
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
