package gk.phonebook.facade.service;

public class PhonebookEntry {
	private Integer id;
	private String name;
	private String surname;
	private String phonenumber;

	public PhonebookEntry() {
	}

	public PhonebookEntry(int id, String name, String surname, String phonenumber) {
		this.id = id;
		this.name = name;
		this.surname = surname;
		this.phonenumber = phonenumber;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getPhonenumber() {
		return phonenumber;
	}

	public void setPhonenumber(String phonenumber) {
		this.phonenumber = phonenumber;
	}

	public String toString(){
		StringBuilder sb = new StringBuilder();

		sb.append("ID = ").append(getId().toString()).append("\t Name = \"").append(getName())
		.append("\" \t Surname = \"").append(getSurname())
		.append("\" \t Phonenumber = ").append(getPhonenumber());

		return sb.toString();
	}
}
