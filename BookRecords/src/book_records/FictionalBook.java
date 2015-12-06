package book_records;

import java.text.ParseException;
import java.util.Calendar;

public class FictionalBook extends Book {
	private String genre;

	public FictionalBook(String[] line) throws ParseException {
		super(line);
		this.genre = line[5];
	}

	public String getGenre() {
		return genre;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}

	@Override
	public String[] getBookAsRow() {
		// TODO Auto-generated method stub
		String[] row = super.getBookAsRow();
		row[5] = "Fictional Book";
		row[6] = genre;
		return row;
	}
}
