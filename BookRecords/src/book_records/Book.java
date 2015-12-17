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

/**
 * Class Book implements BookInterface. Used by BooksModel.
 * 
 * @author Petar Stanev
 */
public abstract class Book implements BookInterface {
	private String name;
	private String author;
	private String publisher;
	private Date publicationDate;
	private float price;
	private String type;

	/**
	 * Constructor for Book using line array.
	 * 
	 * @param line
	 * @throws ParseException
	 */
	public Book(String[] line) throws ParseException {
		name = line[0];
		author = line[1];
		publisher = line[2];
		setPublicationDate(line[3]);
		type = line[4];
		setPrice(line[6]);
	}

	/**
	 * @return name.
	 */
	public String getName() {
		return name;
	}

	/**
	 * set name.
	 */
	public void setName(String name) {
		this.name = name;
	}

	public String getAuthor() {
		return author;
	}

	/**
	 * set author.
	 */
	public void setAuthor(String author) {
		this.author = author;
	}

	/**
	 * @return publisher.
	 */
	public String getPublisher() {
		return publisher;
	}

	/**
	 * set publisher.
	 */
	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}

	/**
	 * @return publictaionDate as Date.
	 */
	public Date getPublicationDate() {
		return publicationDate;
	}

	/**
	 * @return publictaionDate as String.
	 */
	public String getPublicationDateAsString() {
		SimpleDateFormat fmt = new SimpleDateFormat("d-M-yyyy");
		return fmt.format(publicationDate.getTime());
	}

	/**
	 * @return price
	 */
	public double getPrice() {
		return price;
	}

	/**
	 * set price from String. If null, set it to 0.0f;
	 */
	public void setPrice(String price) {
		try {
			this.price = Float.parseFloat(price);
		} catch (Exception e) {
			this.price = 0.0f;
		}
	}

	/**
	 * set price from float.
	 */
	public void setPrice(float price) {
		this.price = price;
	}

	/**
	 * @return type
	 */
	public String getType() {
		return type;
	}

	/**
	 * set type
	 */
	public void setType(String type) {
		this.type = type;
	}

	/**
	 * Set publicationDate from Date
	 * 
	 * @param publicationDate
	 */
	public void setPublicationDate(Date publicationDate) {
		this.publicationDate = publicationDate;
	}

	/**
	 * Set publicationDate from String. If null set it to 0-0-0.
	 * 
	 * @param publicationDate
	 */
	public void setPublicationDate(String publicationDateString) {
		SimpleDateFormat fmt = new SimpleDateFormat("d-M-yyyy");

		try {
			publicationDate = fmt.parse(publicationDateString);
		} catch (ParseException e) {
			publicationDate = new Date(0, 0, 0);
		}
	}

	/**
	 * @return price as String.
	 */
	public String getPriceAsString() {
		DecimalFormat decimalFormat = new DecimalFormat("#.##");
		return String.valueOf(decimalFormat.format(price));
	}

	/**
	 * @return Book as a String array;
	 */
	public String[] getBookAsRow() {
		String dateFormatted = getPublicationDateAsString();

		return new String[] { name, author, publisher, dateFormatted,
				null, null, getPriceAsString() };
	}
}
