package ua.lviv.trainapplogos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ReaderDao {
	private static String CREATE = "INSERT INTO READER (first_name, last_name, readers_ticket_id) VALUES (?, ?, ?)";
	private static String READ_ALL = "SELECT * FROM READER";
	private static String READ_BY_ID = "SELECT * FROM READER WHERE ID = ?";
	private static String DELETE_BY_ID = "DELETE FROM READER WHERE ID = ?";
	private static String UPDATE_BY_ID = "UPDATE READER SET first_name = ?, last_name = ?, readers_ticket_id = ? WHERE ID = ?";
	private static String CREATE_TABLE = "CREATE TABLE READER (" +
								"id INT NOT NULL PRIMARY KEY AUTO_INCREMENT," +
							    "first_name VARCHAR(255) NOT NULL," +
							    "last_name VARCHAR(255) NOT NULL," +
							    "readers_ticket_id VARCHAR(255) NOT NULL)";
	private static String DROP_TABLE = "DROP TABLE IF EXISTS READER";
	private static String GET_COUNT = "SELECT COUNT(id) AS readers_count FROM READER";
	
	Connection connection;
	PreparedStatement preperedStatement;
	
	ReaderDao(Connection connection) {
		this.connection = connection;
	}
	
	public int getCount() throws SQLException {
		preperedStatement = connection.prepareStatement(GET_COUNT);
		ResultSet result = preperedStatement.executeQuery();
		result.next();
		return result.getInt("readers_count");
	}
	
	public List<Reader> readAll() throws SQLException {
		List<Reader> readersList = new ArrayList<>();
		preperedStatement = connection.prepareStatement(READ_ALL);
		ResultSet result = preperedStatement.executeQuery();
		
		while (result.next()) {
			readersList.add(ReaderMapper.map(result));
		}
		
		return readersList;
	}
	
	public Reader read(int id) throws SQLException {
		preperedStatement = connection.prepareStatement(READ_BY_ID);
		preperedStatement.setInt(1, id);
		ResultSet result = preperedStatement.executeQuery();
		result.next();
		
		return ReaderMapper.map(result);
	}
	
	public void createUser(Reader reader) throws SQLException {
		int count = getCount();
		preperedStatement = connection.prepareStatement(CREATE);
		preperedStatement.setString(1, reader.getFirstname());
		preperedStatement.setString(2, reader.getLastname());
		preperedStatement.setString(3, "AA" + String.format("%0" + 5 + "d", (count + 1)));
		preperedStatement.executeUpdate();
	}
	
	public void delete(int id) throws SQLException {
		preperedStatement = connection.prepareStatement(DELETE_BY_ID);
		preperedStatement.setInt(1, id);
		preperedStatement.executeUpdate();
	}
	
	public void update(Reader reader) throws SQLException {
		preperedStatement = connection.prepareStatement(UPDATE_BY_ID);
		preperedStatement.setString(1, reader.getFirstname());
		preperedStatement.setString(2, reader.getLastname());
		preperedStatement.setString(3, reader.getReadersTicketId());
		preperedStatement.setInt(4, reader.getId());
		preperedStatement.executeUpdate();
	}
	
	public void createTable() throws SQLException {
		boolean commitState = connection.getAutoCommit();
		preperedStatement = connection.prepareStatement(DROP_TABLE);
//		preperedStatement.executeQuery();
		connection.setAutoCommit(false);
		
		preperedStatement.execute();
		preperedStatement = connection.prepareStatement(CREATE_TABLE);
		preperedStatement.execute();
		connection.setAutoCommit(commitState);
	}
	
	public void dropTable() throws SQLException {
		Scanner sc = new Scanner(System.in);
		System.out.println("All data will bw erased from table! Are you sure want to delete table?");
		System.out.println("Enter Yes to delete table, No to Cancel:");
		if (sc.next() == "Yes") {
			preperedStatement = connection.prepareStatement(DROP_TABLE);
			preperedStatement.executeQuery();
			System.out.println("> Table was droped!");
		}
	}
	
	
}
