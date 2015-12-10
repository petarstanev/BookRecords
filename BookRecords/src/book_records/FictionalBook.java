package book_records;

import java.text.ParseException;
import java.util.Calendar;

/**
 * Class FictionalBook extends Book and add needed changes.
 * 
 * @author Petar Stanev
 */
public class FictionalBook extends Book {
	private String genre;

	/**
	 * Constructor for FictionalBook from String array.
	 * @param line
	 * @throws ParseException
	 */
	public FictionalBook(String[] line) throws ParseException {
		super(line);
		this.genre = line[5];
	}

	/**
	 * @return genre
	 */
	public String getGenre() {
		return genre;
	}

	/**
	 * set genre
	 * @param genre
	 */
	public void setGenre(String genre) {
		this.genre = genre;
	}

	/**
	 * Get Book as String Array row and add "Fictional Book" and genre.
	 */
	@Override
	public String[] getBookAsRow() {
		String[] row = super.getBookAsRow();
		row[5] = "Fictional Book";
		row[6] = genre;
		return row;
	}
}
