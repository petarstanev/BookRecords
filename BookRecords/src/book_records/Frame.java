package book_records;

import java.awt.BorderLayout;
import java.awt.Cursor;
import java.awt.EventQueue;

import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;
import javax.swing.BoxLayout;

import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.Arrays;
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

public class Frame extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private JSpinner spinner;
	private boolean close = true;//

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
	 * Create the frame.
	 */
	public Frame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1000, 700);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		BooksModel model = new BooksModel();

		table = new JTable(model);

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
				try {
					FileWriter writer = new FileWriter(fileChososer
							.getSelectedFile());
					for (int i = 0; i < model.getRowCount(); i++) {
						String appender = "";
						for (int j = 0; j < model.getColumnCount(); j++) {
							writer.write(appender
									+ (String) table.getValueAt(i, j));
							appender = ",";
						}
						writer.write("\n");
						writer.flush();
					}
					writer.close();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnSave.setBounds(10, 246, 89, 23);
		contentPane.add(btnSave);

		JButton btnClose = new JButton("Close");
		btnClose.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				model.setRowCount(0);
			}
		});
		btnClose.setBounds(10, 44, 89, 23);
		contentPane.add(btnClose);

		table.setBounds(109, 11, 665, 434);
		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setLocation(120, 10);
		scrollPane.setSize(800, 620);

		JTabbedPane tabbedPane = new JTabbedPane();
		tabbedPane.setTabPlacement(JTabbedPane.BOTTOM);
		tabbedPane.setBounds(120, 10, 800, 640);
		tabbedPane.addTab("List view", scrollPane);

		contentPane.add(tabbedPane);

		spinner = new JSpinner(new SpinnerNumberModel(1, 1, null, 1));
		spinner.setBounds(10, 113, 89, 20);
		contentPane.add(spinner);

		JButton btnEdit = new JButton("Edit");
		btnEdit.setBounds(10, 144, 89, 23);
		contentPane.add(btnEdit);

		EditDialog editDialog = new EditDialog(this, "Edit Book.", "Edit", 1);
		btnEdit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int row = (Integer) spinner.getValue();
				model.editBook(row,editDialog);
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

		JButton btnAdd = new JButton("Add");
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//model.editBook(row, editDialog);
			}
		});
		btnAdd.setBounds(10, 212, 89, 23);
		contentPane.add(btnAdd);
	}
}
