package book_records;

import java.text.ParseException;
import java.util.Calendar;

/**
 * Class TextBook extends Book and add needed changes.
 * 
 * @author Petar Stanev
 */
public class TextBook extends Book {
	private String subject;

	/**
	 * Constructor for TextBook from String array.
	 * @param line
	 * @throws ParseException
	 */
	public TextBook(String[] line) throws ParseException {
		super(line);
		this.subject = line[5];
	}

	/**
	 * @return subject
	 */
	public String getSubject() {
		return subject;
	}

	/**
	 * set subject
	 * @param subject
	 */
	public void setSubject(String subject) {
		this.subject = subject;
	}
	
	/**
	 * Get Book as String Array row and add "Text Book" and subject.
	 */
	@Override
	public String[] getBookAsRow() {
		// TODO Auto-generated method stub
		String[] row = super.getBookAsRow();
		row[5] = "Text Book";
		row[6] = subject;
		return row;
	}
}
