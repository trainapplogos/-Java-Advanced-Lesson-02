package ua.lviv.trainapplogos;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BookLibraryApplication {
	public static void main(String[] args) throws SQLException {
		Connection connection = ConnectionUtils.OpenConnection();
		ReaderDao readerDao = new ReaderDao(connection);
		
		//CERATE-TABLE
		System.out.println("> Create new reader's table:");
		readerDao.createTable();

		//ADD-READERS
		System.out.println("> Add new readers:");
		List<Reader> listOfReaders = new ArrayList<>();
		listOfReaders.add(new Reader("Taras", "Shevchenko"));
		listOfReaders.add(new Reader("Donald", "Trampovych"));
		listOfReaders.add(new Reader("Leonid", "Kuchmiv"));
		listOfReaders.add(new Reader("Ivan", "Frankovs'kyj"));
	
		listOfReaders.forEach(reader -> {
			try {
				readerDao.createUser(reader);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		});
		
		OutputList(readerDao);
		
		//READ-BY-ID
		System.out.println("> Show 2nd reader:");
		Reader someReader = readerDao.read(2);
		System.out.println(someReader);
		writeStarLine();
		
		//READ-ALL
		System.out.println("> Show list of all reader:");
		OutputList(readerDao);
		
		//DELETE
		System.out.println("> Delete reader with id = 5:");
		readerDao.delete(5);
		OutputList(readerDao);
		
		//GET-COUNT
		System.out.println("> Show count of reader:");
		System.out.println("There are " + readerDao.getCount() + " readers in Database");
		writeStarLine();

		//UPDATE
		System.out.println("> Update 2nd reader's lastname:");
		someReader.setLastname(someReader.getLastname() + " - NewYorkovych");
		readerDao.update(someReader);
		OutputList(readerDao);
		
		//DROP-TABLE
		System.out.println("> Delete reader's table:");
		readerDao.dropTable();
		
		
		
		connection.close();
	}
	
	public static void writeStarLine() {
		System.out.println("***************************************************************************************\n");
	}
	
	public static void OutputList(ReaderDao readerDao) throws SQLException {
		System.out.println(">> Result:");
		readerDao.readAll().forEach(System.out::println);
		writeStarLine();
	}
}
