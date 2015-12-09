package book_records;

import java.io.BufferedReader;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.swing.table.DefaultTableModel;

public class BooksModel extends DefaultTableModel {
	private ArrayList<Book> allRows;

	public BooksModel() {
		super();
		allRows = new ArrayList<Book>();
		String header[] = new String[] { "#", "Name", "Author", "Publisher",
				"Publication date", "Type", "Specific detail", "Price" };

		setColumnIdentifiers(header);
	}

	public void addFromFile(BufferedReader br) {
		String line = null;
		try {
			while ((line = br.readLine()) != null) {
				String inputRow[] = line.split(",");

				Book book;

				book = chooseBook(inputRow);

				allRows.add(book);
			}
			render();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ArrayIndexOutOfBoundsException e) {
			// TODO: handle exception
			e.printStackTrace();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

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

	@Override
	public boolean isCellEditable(int row, int column) {
		// all cells false
		return false;
	}

	public void removeBook(int row) {
		int index = (row - 1);
		if (allRows.size() > index) {
			allRows.remove(index);
			for (int i = index; i < allRows.size(); i++) {
				// every next row must decrease row number by one.
				// allRows.get(i).setRowNumber(allRows.get(i).getRowNumber() -
				// 1);
			}
		}
		render();
	}

	public void editBook(int row, ModalDialog editDialog) {
		int index = (row - 1);
		if (getRowCount() >= row) {

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
		}
		render();
	}

	public void addBook(EditDialog addDialog) {

	}

	public void render() {
		setRowCount(0);

		for (int i = 0; i < allRows.size(); i++) {

			String[] row = allRows.get(i).getBookAsRow();
			row[0] = String.valueOf(i + 1);
			addRow(row);
		}
	}

	public void renderSelected(int[] selectedRows) {
		setRowCount(0);
		int i = 1;

		for (int j : selectedRows) {
			String[] row = allRows.get(j).getBookAsRow();
			row[0] = String.valueOf(i);
			addRow(row);
			i++;
		}
	}

	public Book getBook(int position) {
		return allRows.get(position);
	}

	public void addBook(ModalDialog addDialog) {
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

	public int getNumberOfBooks() {
		return allRows.size();
	}

	public int getBooksByType(String type) {
		int booksByType = 0;

		for (Book book : allRows) {
			if (book.getType().equals(type))
				booksByType++;
		}
		return booksByType;
	}

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

	public float getTotalValue() {

		float totalValue = 0;

		for (Book book : allRows) {
			totalValue += book.getPrice();
		}

		return totalValue;
	}
}
