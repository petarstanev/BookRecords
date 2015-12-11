package book_records;

/**
 * Interface for Book class.
 * @author Petar
 */
public interface BookInterface {
	public String getName();

	public void setName(String name);

	public String getAuthor();

	public void setAuthor(String author);

	public double getPrice();

	public void setPrice(float price);

	public String getPublisher();

	public void setPublisher(String publisher);

	public String[] getBookAsRow();
}
