package ua.lviv.trainapplogos;

public class Reader {
	private int id;
	private String firstname;
	private String lastname;
	private String readersTicketId;
	
	public Reader(int id, String firstname, String lastname, String readersTicketId) {
		this.id = id;
		this.firstname = firstname;
		this.lastname = lastname;
		this.readersTicketId = readersTicketId;
	}

	public Reader(String firstname, String lastname, String readersTicketId) {
		this.firstname = firstname;
		this.lastname = lastname;
		this.readersTicketId = readersTicketId;
	}
	
	public Reader(String firstname, String lastname) {
		this.firstname = firstname;
		this.lastname = lastname;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getReadersTicketId() {
		return readersTicketId;
	}

	public void setReadersTicketId(String readersTicketId) {
		this.readersTicketId = readersTicketId;
	}

	@Override
	public String toString() {
		return "Reader [id=" + id + ", firstname=" + firstname + ", lastname=" + lastname + ", readersTicketId="
				+ readersTicketId + "]";
	}
}
