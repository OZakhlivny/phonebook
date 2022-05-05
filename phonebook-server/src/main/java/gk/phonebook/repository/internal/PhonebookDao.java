package gk.phonebook.repository.internal;


import gk.dbi.criteria.Columns;
import gk.dbi.criteria.Condition;
import gk.dbi.spring.dao.DataAccessObjectBase;
import gk.phonebook.repository.IPhonebookDao;
import gk.phonebook.repository.dbi.DbiPhonebook;
import gk.phonebook.repository.dbi.DbiPhonebookRow;


public class PhonebookDao extends DataAccessObjectBase implements IPhonebookDao {

	public void store(DbiPhonebookRow row) {
	    if (updateRow_Row(new DbiPhonebook(), row) == 0) {
	      insertRow_Row(new DbiPhonebook(), row);
	    }
	}

	public DbiPhonebook load() {
		return selectTable_Full(new DbiPhonebook());
	}

	public DbiPhonebookRow loadPhonebookEntry(Integer key) {
	    return selectRow_Criteria( createCriteriaBuilder(new DbiPhonebook())
	    	        .select(Columns.all())
	    	        .where(Condition.equal(DbiPhonebookRow.CI_KEY, key))
	    	    );
	}

	public void removeAll() {
		deleteTable_Full(new DbiPhonebook());

	}

}
