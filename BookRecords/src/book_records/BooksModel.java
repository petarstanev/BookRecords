package book_records;

import java.io.BufferedReader;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.swing.AbstractListModel;
import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.table.DefaultTableModel;

/**
 * BooksModel is used from Frame as list and it is extended from
 * DefaultTableModel.
 * 
 * @author Petar Stanev
 */
public class BooksModel extends DefaultListModel<String> {
	private ArrayList<Book> allRows;

	/**
	 * Constructor for BooksModel.
	 */
	public BooksModel() {
		super();
		allRows = new ArrayList<Book>();
	}

	/**
	 * Add rows from a file using BufferReader.
	 * 
	 * @param br
	 */
	public void addFromFile(BufferedReader br) {
		String line = null;
		allRows.clear();
		try {
			while ((line = br.readLine()) != null) {
				String inputRow[] = line.split(",");

				Book book;

				book = chooseBook(inputRow);

				allRows.add(book);
			}
			render();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create Book from String array.
	 * 
	 * @param info
	 * @return Book
	 * @throws ParseException
	 */
	private Book chooseBook(String[] info) throws ParseException {
		Book book;
		switch (info[4]) {
		case "Fictional Book":
			book = new FictionalBook(info);
			;
			break;
		case "History Book":
			book = new HistoryBook(info);
			break;
		case "Text Book":
			book = new TextBook(info);
			break;
		default:
			book = null;
			System.out.println("Invalid type of book.  -" + info[4] + "- ");
			break;
		}
		return book;
	}

	/**
	 * Remove book by row.
	 * 
	 * @param row
	 */
	public void removeBook(int row) {
		int index = (row - 1);
		if (allRows.size() > index) {
			allRows.remove(index);
		}
		render();
	}

	/**
	 * Edit book by row and ModalDDailog.
	 * 
	 * @param row
	 * @param editDialog
	 */
	public void editBook(int row, ModalDialog editDialog) {
		int index = (row - 1);

		editDialog.setValues(getBook(index));
		editDialog.setVisible(true);
		if (!editDialog.isCloseButton()) {
			String[] output = editDialog.getValues();

			try {
				Book editedBoook = chooseBook(output);
				allRows.set(index, editedBoook);
			} catch (ParseException e) {
				e.printStackTrace();
			}
			editDialog.setCloseButton(true);
		}
		render();
	}

	/**
	 * Render all rows from allRows array. Used by List view.
	 */
	public void render() {
		removeAllElements();
		addElement("asd");
		for (int i = 0; i < allRows.size(); i++) {
			addElement(allRows.get(i).getName());
		}
	}

	public String[] getSelected(int selectedRow) {
		return allRows.get(selectedRow).getBookAsRow();
	}

	/**
	 * Render rows where date is between selected. Used by Detailed view.
	 * 
	 * @param selectedRows
	 */
	public void renderSelected(Date startDate, Date endDate) {
		removeAllElements();

		for (int i = 0; i < allRows.size(); i++) {
			Date bookDate = allRows.get(i).getPublicationDate();
			if (bookDate.after(startDate) && bookDate.before(endDate)) {
				addElement(allRows.get(i).getName());
			}
		}
	}

	/**
	 * Get Book from position.
	 * 
	 * @param position
	 * @return
	 */
	public Book getBook(int position) {
		return allRows.get(position);
	}

	/**
	 * Add Book form ModalDialog.
	 * 
	 * @param addDialog
	 */
	public void addBook(ModalDialog addDialog) {
		if (allRows.size() > 0) {
			addDialog.setVisible(true);
			if (!addDialog.isCloseButton()) {
				String[] output = addDialog.getValues();

				try {
					Book addedBoook = chooseBook(output);
					allRows.add(addedBoook);
				} catch (ParseException e) {
					e.printStackTrace();
				}
				addDialog.setCloseButton(true);
			}
			render();
		}
	}

	/**
	 * Get number of all Books in allRows.
	 */
	public int getNumberOfBooks() {
		return allRows.size();
	}

	/**
	 * Get number of all Books in allRows by specific type.
	 * 
	 * @param type
	 * @return
	 */
	public int getBooksByType(String type) {
		int booksByType = 0;

		for (Book book : allRows) {
			if (book.getType().equals(type))
				booksByType++;
		}
		return booksByType;
	}

	/**
	 * Get all unique Names of artist as String ArrayList.
	 */
	public String getNamesOfArtists() {
		ArrayList<String> artists = new ArrayList<String>();
		String output = "";

		for (Book book : allRows) {
			String author = book.getAuthor();

			if (!artists.contains(author)) {
				artists.add(author);
				output += author + "  ";
			}
		}

		return output;
	}

	/**
	 * Get all unique times as String ArrayList.
	 * 
	 * @return
	 */
	public String getAllTimes() {
		ArrayList<String> artists = new ArrayList<String>();
		String output = "";

		for (Book book : allRows) {
			String date = book.getPublicationDateAsString();

			if (!artists.contains(date)) {
				artists.add(date);
				output += date + "  ";
			}
		}

		return output;
	}

	/**
	 * Get total value of all books.
	 */
	public float getTotalValue() {

		float totalValue = 0;

		for (Book book : allRows) {
			totalValue += book.getPrice();
		}

		return totalValue;
	}

	@Override
	public int getSize() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Object getElementAt(int index) {
		// TODO Auto-generated method stub
		return null;
	}
}
