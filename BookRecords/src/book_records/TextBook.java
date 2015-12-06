package book_records;

import java.text.ParseException;
import java.util.Calendar;

public class TextBook extends Book {
	private String subject;

	public TextBook(String[] line) throws ParseException {
		super(line);
		this.subject = line[5];
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}
	
	@Override
	public String[] getBookAsRow() {
		// TODO Auto-generated method stub
		String[] row = super.getBookAsRow();
		row[5] = "Text Book";
		row[6] = subject;
		return row;
	}
}
