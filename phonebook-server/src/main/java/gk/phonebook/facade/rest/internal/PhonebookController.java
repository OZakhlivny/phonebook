package gk.phonebook.facade.rest.internal;

import org.springframework.beans.factory.annotation.Required;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import gk.phonebook.facade.service.IPhonebookService;
import gk.phonebook.facade.service.PhonebookEntry;

@Controller
public class PhonebookController {

	private IPhonebookService phonebookService;

	@Required
	public void setPhonebookService(IPhonebookService phonebookService) {
		this.phonebookService = phonebookService;
	}

	@ResponseBody
	@RequestMapping(value="/phonebook", method=RequestMethod.GET)
	public String getPhonebook() {
		StringBuilder sb = new StringBuilder();
		for (PhonebookEntry entry : phonebookService.getPhonebook()) {
			sb.append(entry.toString()).append("<br/>");
		}
		return sb.toString();
	}

	  @ResponseBody
	  @RequestMapping(value="/entry/{key:.*}", method=RequestMethod.GET)
	  public String getPhonebookEntry(@PathVariable Integer key) {
	    return phonebookService.getPhonebookEntry(key).toString();
	  }
}
