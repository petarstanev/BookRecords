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
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

/**
 * ModalDialog extends Jdialog and it is used for add and edit buttons.
 * 
 * @author Petar Stanev
 */
public class ModalDialog extends JDialog {
	private JTextField textName;
	private JTextField textAuthor;
	private JTextField textPublisher;
	private JTextField textPublicationDate;
	private JComboBox comboBoxType;
	private JTextField textSpecificDetail;
	private JTextField textPrice;
	private boolean closeButton;

	/**
	 * Create ModalDialog pass  JFrame, title and buttonMessage .
	 * @param jframe
	 * @param title
	 * @param buttonMessage
	 */
	public ModalDialog(JFrame jframe, String title, String buttonMessage) {
		
		super(jframe, title, true);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		closeButton=true;
		getContentPane().setLayout(new BorderLayout(0, 0));
		JPanel buttonPane = new JPanel();
		JButton button = new JButton(buttonMessage);

		buttonPane.add(button);
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
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

		JLabel lblPublicationDate = new JLabel("<html>Publication date (27-12-2000)</html>");
		lblPublicationDate.setToolTipText("");
		panel.add(lblPublicationDate);

		JLabel lblType = new JLabel("Type");
		panel.add(lblType);

		JLabel lblNewLabel = new JLabel("Specific detail");
		panel.add(lblNewLabel);

		JLabel lblPrice = new JLabel("Price (12.4)");
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
		
		comboBoxType = new JComboBox();
		comboBoxType.setModel(new DefaultComboBoxModel(new String[] {"Fictional Book", "History Book", "Text Book"}));
		comboBoxType.setMaximumRowCount(3);
		panel.add(comboBoxType);

		textSpecificDetail = new JTextField();
		panel.add(textSpecificDetail);
		textSpecificDetail.setColumns(10);

		textPrice = new JTextField();
		panel.add(textPrice);
		textPrice.setColumns(10);
		getContentPane().add(buttonPane, BorderLayout.SOUTH);

		setBounds(100, 100, 740, 159);
	}

	/**
	 * set closeButton. Used if use chose to close the ModalDialog.
	 * @param closeButton
	 */
	public void setCloseButton(boolean closeButton) {
		this.closeButton = closeButton;
	}

	/**
	 * @return closeButton
	 */
	public boolean isCloseButton() {
		return closeButton;
	}

	/**
	 * 
	 * @return String[] of the values of all inputs.
	 */
	public String[] getValues(){
		String[] output = new String[7];
		output[0]=textName.getText();
		output[1]=textAuthor.getText();
		output[2]=textPublisher.getText();		
		output[3]=textPublicationDate.getText();
		output[4]=comboBoxType.getSelectedItem().toString();
		output[5]=textSpecificDetail.getText();
		output[6]=textPrice.getText();
		return output;
	}

	/**
	 * Set values of the of text boxes to 
	 * @param book
	 */
	public void setValues(Book book) {
		textName.setText(book.getName());
		textAuthor.setText(book.getAuthor());
		
		textPublisher.setText(book.getPublisher());
		DateFormat dateFormat = new SimpleDateFormat("MM-dd-yyyy");
		
		textPublicationDate.setText(book.getPublicationDateAsString());
		
		comboBoxType.setSelectedItem(book.getType());
		
		textPrice.setText(book.getPriceAsString());
		
		if(book instanceof FictionalBook){
			textSpecificDetail.setText(((FictionalBook) book).getGenre());
		}else if(book instanceof HistoryBook){
			textSpecificDetail.setText(((HistoryBook) book).getPeriod());
		}else if(book instanceof TextBook){
			textSpecificDetail.setText(((TextBook) book).getSubject());
		}		
	}
}