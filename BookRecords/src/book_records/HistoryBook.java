package book_records;

import java.text.ParseException;
import java.util.Calendar;

public class HistoryBook extends Book {
	private String period;
	
	public HistoryBook(String[] line) throws ParseException{
		super(line);
		this.period = line[5];
	}
	

	public String getPeriod() {
		return period;
	}

	public void setPeriod(String period) {
		this.period = period;
	}
	
	@Override
	public String[] getBookAsRow() {
		// TODO Auto-generated method stub
		String[] row = super.getBookAsRow();
		row[5] = "History Book";
		row[6] = period;
		return row;
	}
}
