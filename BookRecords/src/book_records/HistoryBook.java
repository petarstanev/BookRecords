package book_records;

import java.text.ParseException;
import java.util.Calendar;

/**
 * Class HistoryBook extends Book and add needed changes.
 * 
 * @author Petar Stanev
 */
public class HistoryBook extends Book {
	private String period;
	
	/**
	 * Constructor for HistoryBook from String array.
	 * @param line
	 * @throws ParseException
	 */
	public HistoryBook(String[] line) throws ParseException{
		super(line);
		this.period = line[5];
	}
	
	/**
	 * @return period
	 */
	public String getPeriod() {
		return period;
	}

	/**
	 * set period
	 * @param period
	 */
	public void setPeriod(String period) {
		this.period = period;
	}
	
	/**
	 * Get Book as String Array row and add "History Book" and period.
	 */
	@Override
	public String[] getBookAsRow() {
		// TODO Auto-generated method stub
		String[] row = super.getBookAsRow();
		row[5] = "History Book";
		row[6] = period;
		return row;
	}
}
