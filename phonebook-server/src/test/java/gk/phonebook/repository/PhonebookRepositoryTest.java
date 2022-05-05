package gk.phonebook.repository;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.transaction.annotation.Transactional;

import gk.phonebook.facade.service.PhonebookEntry;
import gk.phonebook.repository.dbi.DbiPhonebook;


@Transactional
@ContextConfiguration
public class PhonebookRepositoryTest extends AbstractTransactionalJUnit4SpringContextTests {
	  @Autowired
	  private IPhonebookRepository phonebookRepository;

	  @Test
	  @DirtiesContext
	  public void testPhonebookRepository() {
		assertPhonebookTableRowCount(0);
		PhonebookEntry entry = new PhonebookEntry();

		entry.setId(1);
		entry.setName("Ozzy");
		entry.setSurname("Osbourne");
		entry.setPhonenumber("+420111111111");

	    List<PhonebookEntry> phonebook = new ArrayList<>();
	    phonebook.add(entry);

	    phonebookRepository.store(phonebook);
	    assertPhonebookTableRowCount(1);

	    assertEquals("Ozzy", phonebookRepository.loadPhonebookEntry(1).getName());
	    assertNull(phonebookRepository.loadPhonebookEntry(2));

	    phonebookRepository.removeAll();
	    assertPhonebookTableRowCount(0);
	  }

	  private void assertPhonebookTableRowCount(int count) {
	    assertEquals(
	      "PHONEBOOK table row count",
	      count,
	      countRowsInTable(new DbiPhonebook().getTableName())
	    );
	  }

}
