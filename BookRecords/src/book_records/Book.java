package book_records;

import java.awt.List;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public abstract class Book {
	private String name;
	private String author;
	private String publisher;
	private Date publicationDate;
	private float price;
	private String type;

	public Book(String[] line) throws ParseException {
		name = line[0];
		author = line[1];
		publisher = line[2];
		setPublicationDate(line[3]);
		type = line[4];
		setPrice(line[6]);
	}

	public void setPrice(String price) {
		try {
			this.price = Float.parseFloat(price);
		} catch (Exception e) {
			this.price = 0.0f;
		}
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getPublisher() {
		return publisher;
	}

	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}

	public Date getPublicationDate() {
		return publicationDate;
	}

	public String getPublicationDateAsString() {
		SimpleDateFormat fmt = new SimpleDateFormat("d-M-yyyy");
		return fmt.format(publicationDate.getTime());
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public String[] getBookAsRow() {
		String dateFormatted = publicationDateAsString(publicationDate);

		return new String[] { null, name, author, publisher, dateFormatted,
				null, null, Float.toString(price) };
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public void setPublicationDate(Date publicationDate) {
		this.publicationDate = publicationDate;
	}

	public void setPublicationDate(String publicationDateString) {
		SimpleDateFormat fmt = new SimpleDateFormat("d-M-yyyy");

		try {
			publicationDate = fmt.parse(publicationDateString);
		} catch (ParseException e) {
			publicationDate = new Date(0, 0, 0);
		}
	}

	public static String publicationDateAsString(Date date) {
		SimpleDateFormat fmt = new SimpleDateFormat("d-M-yyyy");
		return fmt.format(date.getTime());
	}

	public static String priceAsString(double price) {
		DecimalFormat decimalFormat = new DecimalFormat("#.##");
		return String.valueOf(decimalFormat.format(price));
	}
}
