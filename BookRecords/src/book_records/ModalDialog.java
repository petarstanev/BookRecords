package book_records;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.management.modelmbean.ModelMBean;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import java.awt.GridLayout;

import javax.swing.JFormattedTextField;
import javax.swing.JTextPane;
import javax.swing.SwingConstants;

import java.awt.FlowLayout;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;

public class ModalDialog extends JDialog {
	private JTextField textName;
	private JTextField textAuthor;
	private JTextField textPublisher;
	private JTextField textPublicationDate;
	private JTextField textType;
	private JTextField textSpecificDetail;
	private JTextField textPrice;
	private boolean closeButton;

	public ModalDialog(JFrame jframe, String title, String message, int value) {
		
		super(jframe, title, true);
		closeButton=true;
		getContentPane().setLayout(new BorderLayout(0, 0));
		JPanel buttonPane = new JPanel();
		JButton button = new JButton(message);

		buttonPane.add(button);
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				closeButton=false;
				dispose();
			}
		});

		JPanel panel = new JPanel();
		getContentPane().add(panel);
		panel.setLayout(new GridLayout(2, 7, 10, 3));

		JLabel lblName = new JLabel("Name");
		panel.add(lblName);

		JLabel lblAuthor = new JLabel("Author");
		panel.add(lblAuthor);

		JLabel lblPublisher = new JLabel("Publisher");
		panel.add(lblPublisher);

		JLabel lblPublicationDate = new JLabel("<html>Publication date</html>");
		panel.add(lblPublicationDate);

		JLabel lblType = new JLabel("Type");
		panel.add(lblType);

		JLabel lblNewLabel = new JLabel("Specific detail");
		panel.add(lblNewLabel);

		JLabel lblPrice = new JLabel("Price");
		panel.add(lblPrice);

		textName = new JTextField();
		panel.add(textName);
		textName.setColumns(10);

		textAuthor = new JTextField();
		panel.add(textAuthor);
		textAuthor.setColumns(10);

		textPublisher = new JTextField();
		panel.add(textPublisher);
		textPublisher.setColumns(10);

		textPublicationDate = new JTextField();
		panel.add(textPublicationDate);
		textPublicationDate.setColumns(10);

		textType = new JTextField();
		panel.add(textType);
		textType.setColumns(10);

		textSpecificDetail = new JTextField();
		panel.add(textSpecificDetail);
		textSpecificDetail.setColumns(10);

		textPrice = new JTextField();
		panel.add(textPrice);
		textPrice.setColumns(10);
		getContentPane().add(buttonPane, BorderLayout.SOUTH);

//		JButton btnCancel = new JButton("Cancel");
//		btnCancel.addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent e) {
//				dispose();
//			}
//		});
//		buttonPane.add(btnCancel);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		// pack();
		setBounds(100, 100, 700, 159);
	}

	public void setCloseButton(boolean closeButton) {
		this.closeButton = closeButton;
	}

	public boolean isCloseButton() {
		return closeButton;
	}

	public String[] getValues(){
		String[] output = new String[8];
		//start from 1 because 0 is saved for the row number
		output[0]=textName.getText();
		output[1]=textAuthor.getText();
		output[2]=textPublisher.getText();		
		output[3]=textPublicationDate.getText();
		output[4]=textType.getText();
		output[5]=textSpecificDetail.getText();
		output[6]=textPrice.getText();
		return output;		
	}

	public void setValues(Book book) {
		textName.setText(book.getName());
		textAuthor.setText(book.getAuthor());
		
		textPublisher.setText(book.getPublisher());
		DateFormat dateFormat = new SimpleDateFormat("MM-dd-yyyy");
		
		textPublicationDate.setText(Book.publicationDateAsString(book.getPublicationDate()));
		
		textType.setText(book.getType());
		
		textPrice.setText(Book.priceAsString(book.getPrice()));
		
		if(book instanceof FictionalBook){
			textSpecificDetail.setText(((FictionalBook) book).getGenre());
		}else if(book instanceof HistoryBook){
			textSpecificDetail.setText(((HistoryBook) book).getPeriod());
		}else if(book instanceof TextBook){
			textSpecificDetail.setText(((TextBook) book).getSubject());
		}		
	}
}
