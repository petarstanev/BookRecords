package book_records;

import java.awt.BorderLayout;
import java.awt.Cursor;
import java.awt.EventQueue;

import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.ListSelectionModel;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.BoxLayout;

import java.awt.GridLayout;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.JPopupMenu;

import java.awt.Color;
import java.awt.Font;

import javax.swing.SwingConstants;
import javax.swing.JTextField;

/**
 * Create the Frame and all components.
 * 
 * @author Petar Stanev
 */
public class Frame extends JFrame {
	private JPanel contentPane;
	private BooksModel<String> model;
	private JList<String> booksList;
	
	private JSpinner spinner;
	private JTextField textEndDay;
	private JTextField textStartDay;
	private JTabbedPane sourceTabbedPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Frame frame = new Frame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame and all components on it.
	 */
	public Frame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1000, 700);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		model = new BooksModel<>();

		booksList = new JList<>(model);

		booksList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		String dir = System.getProperty("user.dir");
		JFileChooser fileChososer = new JFileChooser(dir);

		JButton btnOpen = new JButton("Open");
		btnOpen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (fileChososer.showOpenDialog(contentPane) != JFileChooser.APPROVE_OPTION)
					return;
				File f = fileChososer.getSelectedFile();

				try {
					model.addFromFile(new BufferedReader(new FileReader(f)));
				} catch (FileNotFoundException e1) {
					e1.printStackTrace();
				}

				SwingUtilities.invokeLater(new Runnable() {
					public void run() {
						contentPane.setCursor(Cursor
								.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
					}
				});
			}
		});
		btnOpen.setBounds(10, 10, 89, 23);
		contentPane.add(btnOpen);

		JButton btnSave = new JButton("Save");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (model.getNumberOfBooks() > 0) {
					try {
						FileWriter writer = new FileWriter(fileChososer
								.getSelectedFile());
						for (int i = 0; i < model.getNumberOfBooks(); i++) {
							String appender = "";
//							for (int j = 1; j < model.getNumberOfBooks(); j++) {
//								
//								writer.write(appender
//										+ (String) booksList.getBook);
//								appender = ",";
//							}
							writer.write("\n");
							writer.flush();
						}
						writer.close();
					} catch (IOException e1) {
						e1.printStackTrace();
					}
				}
			}
		});
		btnSave.setBounds(10, 246, 89, 23);
		contentPane.add(btnSave);

		JButton btnClose = new JButton("Close");
		btnClose.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				model.removeAllElements();
			}
		});
		btnClose.setBounds(10, 44, 89, 23);
		contentPane.add(btnClose);

		booksList.setBounds(109, 11, 665, 434);

		JScrollPane scrollPaneList = new JScrollPane(booksList);
		scrollPaneList.setLocation(120, 10);
		scrollPaneList.setSize(800, 620);
		scrollPaneList.setLocation(120, 10);
		scrollPaneList.setSize(800, 620);

		JTabbedPane tabbedPane = new JTabbedPane();

		JPanel reportView = new JPanel();

		reportView.setLayout(null);

		JLabel numberOfBooks = new JLabel("Total number of books:");
		numberOfBooks.setBounds(10, 11, 775, 17);
		numberOfBooks.setFont(new Font("Tahoma", Font.PLAIN, 14));
		reportView.add(numberOfBooks);

		JLabel numberBooksPerType = new JLabel("Number books per type:");
		numberBooksPerType.setFont(new Font("Tahoma", Font.PLAIN, 14));
		numberBooksPerType.setBounds(10, 39, 775, 17);
		reportView.add(numberBooksPerType);

		JLabel fictional = new JLabel("Fictional: ");
		fictional.setFont(new Font("Tahoma", Font.PLAIN, 14));
		fictional.setBounds(41, 68, 775, 17);
		reportView.add(fictional);

		JLabel hisotry = new JLabel("History: ");
		hisotry.setFont(new Font("Tahoma", Font.PLAIN, 14));
		hisotry.setBounds(41, 96, 775, 17);
		reportView.add(hisotry);

		JLabel textBook = new JLabel("Text Book: ");
		textBook.setFont(new Font("Tahoma", Font.PLAIN, 14));
		textBook.setBounds(41, 124, 775, 17);

		reportView.add(textBook);

		JLabel namesOfAllArtists = new JLabel("Names of all artists: ");
		namesOfAllArtists.setVerticalAlignment(SwingConstants.TOP);
		namesOfAllArtists.setFont(new Font("Tahoma", Font.PLAIN, 14));
		namesOfAllArtists.setBounds(10, 152, 775, 78);
		reportView.add(namesOfAllArtists);

		JLabel totalValue = new JLabel("Total value:");
		totalValue.setFont(new Font("Tahoma", Font.PLAIN, 14));
		totalValue.setBounds(10, 241, 775, 17);
		reportView.add(totalValue);

		JLabel timeOfAllBooks = new JLabel("Time of all books: ");
		timeOfAllBooks.setVerticalAlignment(SwingConstants.TOP);
		timeOfAllBooks.setFont(new Font("Tahoma", Font.PLAIN, 14));
		timeOfAllBooks.setBounds(10, 269, 775, 78);
		reportView.add(timeOfAllBooks);

		JPanel detailView = new JPanel();

		detailView.setLayout(null);

		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 320, 612);
		detailView.add(panel);
		panel.setLayout(new GridLayout(7, 2, 0, 0));

		JLabel name = new JLabel("Name");
		panel.add(name);

		JLabel nameValue = new JLabel("");
		panel.add(nameValue);

		JLabel lblAuthor = new JLabel("Author");
		panel.add(lblAuthor);

		JLabel authorValue = new JLabel("");
		panel.add(authorValue);

		JLabel publisher = new JLabel("Publisher");
		panel.add(publisher);

		JLabel publisherValue = new JLabel("");
		panel.add(publisherValue);

		JLabel publicationDate = new JLabel("Publication date");
		panel.add(publicationDate);

		JLabel publicationDateValue = new JLabel("");
		panel.add(publicationDateValue);

		JLabel type = new JLabel("Type");
		panel.add(type);

		JLabel typeValue = new JLabel("");
		panel.add(typeValue);

		JLabel detail = new JLabel("Detail");// change
		panel.add(detail);
		
		JLabel detailValue = new JLabel("");
		panel.add(detailValue);
		
		JLabel price = new JLabel("Price");
		panel.add(price);

		JLabel priceValue = new JLabel("");
		panel.add(priceValue);
		

		ChangeListener changeListener = new ChangeListener() {
			public void stateChanged(ChangeEvent changeEvent) {
				sourceTabbedPane = (JTabbedPane) changeEvent.getSource();
				int index = sourceTabbedPane.getSelectedIndex();
				if (sourceTabbedPane.getTitleAt(index).equals("List view")) {
					model.render();

				} else if (sourceTabbedPane.getTitleAt(index).equals(
						"Details view")) {
					if (booksList.getSelectedIndex() > -1) {
						String[] bookInfo = model.getSelected(index);
						nameValue.setText(bookInfo[0]);
						authorValue.setText(bookInfo[1]);
						publisherValue.setText(bookInfo[2]);
						publicationDateValue.setText(bookInfo[3]);
						typeValue.setText(bookInfo[4]);
						detailValue.setText(bookInfo[5]);
						price.setText(bookInfo[6]);		
					}
				} else {
					numberOfBooks.setText(numberBooksPerType.getText() + " "
							+ model.getNumberOfBooks());
					fictional.setText("Fictional: "
							+ model.getBooksByType("Fictional Book"));
					hisotry.setText("History: "
							+ model.getBooksByType("Hisotry Book"));
					textBook.setText("Text Book: "
							+ model.getBooksByType("Text Book"));
					namesOfAllArtists.setText("Names of all artists: "
							+ model.getNamesOfArtists());
					totalValue.setText("Total value: " + model.getTotalValue());
					timeOfAllBooks.setText("Time of all books: "
							+ model.getAllTimes());

				}
			}
		};

		tabbedPane.setTabPlacement(JTabbedPane.BOTTOM);
		tabbedPane.setBounds(120, 10, 800, 640);
		tabbedPane.addTab("List view", scrollPaneList);
		tabbedPane.addTab("Details view", detailView);

		
		tabbedPane.addTab("Report View", reportView);
		contentPane.add(tabbedPane);

		tabbedPane.addChangeListener(changeListener);

		spinner = new JSpinner(new SpinnerNumberModel(1, 1, null, 1));
		spinner.setBounds(10, 113, 89, 20);
		contentPane.add(spinner);

		JButton btnEdit = new JButton("Edit");
		btnEdit.setBounds(10, 144, 89, 23);
		contentPane.add(btnEdit);

		ModalDialog editDialog = new ModalDialog(this, "Edit row.", "Edit");
		btnEdit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int row = (Integer) spinner.getValue();
				model.editBook(row, editDialog);
			}
		});

		JButton btnRemove = new JButton("Remove");
		btnRemove.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int value = (Integer) spinner.getValue();
				model.removeBook(value);
			}
		});
		btnRemove.setBounds(10, 178, 89, 23);
		contentPane.add(btnRemove);

		ModalDialog addDialog = new ModalDialog(this, "Add row.", "Add");
		JButton btnAdd = new JButton("Add");
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				model.addBook(addDialog);
			}
		});
		btnAdd.setBounds(10, 212, 89, 23);
		contentPane.add(btnAdd);

		JButton btnSearch = new JButton("Search");
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String startDayString = textStartDay.getText();
				String endDayString = textEndDay.getText();

				SimpleDateFormat fmt = new SimpleDateFormat("d-M-yyyy");

				try {
					Date startDate = fmt.parse(startDayString);
					Date endDate = fmt.parse(endDayString);

					model.renderSelected(startDate, endDate);

				} catch (Exception ex) {

				}
			}
		});
		btnSearch.setBounds(10, 626, 89, 23);
		contentPane.add(btnSearch);

		textEndDay = new JTextField();
		textEndDay.setBounds(10, 595, 89, 20);
		contentPane.add(textEndDay);
		textEndDay.setColumns(10);

		JLabel lblNewLabel = new JLabel("End day");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(10, 580, 89, 14);
		contentPane.add(lblNewLabel);

		textStartDay = new JTextField();
		textStartDay.setColumns(10);
		textStartDay.setBounds(10, 539, 89, 20);
		contentPane.add(textStartDay);

		JLabel lblStartDay = new JLabel("Start day");
		lblStartDay.setHorizontalAlignment(SwingConstants.CENTER);
		lblStartDay.setBounds(10, 519, 89, 14);
		contentPane.add(lblStartDay);
	}
}
