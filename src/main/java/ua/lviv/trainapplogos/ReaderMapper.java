package ua.lviv.trainapplogos;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ReaderMapper {
	public static Reader map(ResultSet result) throws SQLException {
		int id = result.getInt("id");
		String firstName = result.getString("first_name");
		String lastName = result.getString("last_name");
		String readersTicketId = result.getString("readers_ticket_id");
		
		return new Reader(id, firstName, lastName, readersTicketId);
	} 
}
