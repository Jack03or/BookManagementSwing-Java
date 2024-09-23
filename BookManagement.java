package osdCA3;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class BookManagement extends JFrame {

	JPanel bookTab, searchTab;
	JTextField txtIsbn;
	JTextField txtTitle;
	JTextField txtPrice;
	JTextField txtGenre;
	JButton addButton;
	JTextArea textArea;
	// JComboBox genreBox;
	private JTable bookTable;
	private DefaultTableModel tableModel;
	private List<Book> BookList;
	private JComboBox<String> genreBox;

	public BookManagement() {
		super("Book Management");

		// Creating the tab panels
		bookTab = new JPanel();
		searchTab = new JPanel();

		// Making them actual tabs
		JTabbedPane tabs = new JTabbedPane();

		// adding the panel to the tabbed pane to make them like tabs
		tabs.add(bookTab, "Book Information");
		tabs.add(searchTab, "Search Book");

		// Now add the tabs to the frame
		add(tabs);
		// Call the methods that add the stuff to the tabs
		BookInfo_GUI();
		SearchTab_GUI();

	}

	public void BookInfo_GUI() {

		// set border layout for tab
		bookTab.setLayout(new BorderLayout());

		// Labels
		JLabel isbnLabel = new JLabel("ISBN : ");
		JLabel titleLabel = new JLabel("Title : ");
		JLabel priceLabel = new JLabel("Price : ");

		// textFields
		txtIsbn = new JTextField(15);
		txtTitle = new JTextField(20);
		txtPrice = new JTextField(15);

		// ComboBox
		genreBox = new JComboBox<>(new String[] { "Fantasy", "Science Fiction", "Dystopian", "Adventure", "Romance" });

		// buttons
		addButton = new JButton("Add Book");

		// Make the table
		String[] columnNames = { "ISBN", "Title", "Genre", "Price" };
		tableModel = new DefaultTableModel(columnNames, 0);
		bookTable = new JTable(tableModel);

		// Add panels so they can be formatted
		JPanel addPanel = new JPanel();
		JPanel tablePanel = new JPanel();

		addPanel.add(isbnLabel);
		addPanel.add(txtIsbn);
		addPanel.add(titleLabel);
		addPanel.add(txtTitle);
		addPanel.add(genreBox);
		addPanel.add(priceLabel);
		addPanel.add(txtPrice);
		addPanel.add(addButton);

		tablePanel.add(bookTable);

		// add layout to the table
		tablePanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		tablePanel.add(new JScrollPane(bookTable));

		bookTab.add(addPanel, BorderLayout.NORTH);
		bookTab.add(tablePanel, BorderLayout.CENTER);

		addButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String Isbn = txtIsbn.getText();
				String title = txtTitle.getText();
				String genre = (String) genreBox.getSelectedItem();
				double price = Double.parseDouble(txtPrice.getText());
				
				// Add the details to the table
				Object[] row = {txtIsbn.getText(), txtTitle.getText(), genreBox.getSelectedItem(), txtPrice.getText() };
				tableModel.addRow(row);

				// Add the product to the list
				Book b = new Book(Isbn, title, genre, price);
				BookList.add(b);

				
				if ((txtTitle.getText().isBlank())&& (txtIsbn.getText().isBlank()) && 
						(txtPrice.getText().isBlank())) 
				{
				
				JOptionPane.showMessageDialog(null, "Fields Must not be empty", "title", JOptionPane.WARNING_MESSAGE);
				
			
				}
				
				if (!isUniqueISBN(Isbn)) {
		            JOptionPane.showMessageDialog(null, "ISBN must be unique", "Error", JOptionPane.ERROR_MESSAGE);
		            return;
		        }
				
				// Clear text
				clearText();
				
			}
			
	
			
		});	
	
		
	}
	
public void SearchTab_GUI() {
		
		//Setting the border Layout
		searchTab.setLayout(new BorderLayout());
		
		//creating the labels
		 JLabel genreLabel = new JLabel("Genre : ");
		
		 //textFields
		 txtGenre = new JTextField(20);
		 
		 // add button
		 JButton searchBtn = new JButton("Search");
		 JButton clearBtn = new JButton("Clear Search");
		 
		 //text area
		 textArea = new JTextArea(15,80); //add dimensions to the text area so its not tiny
		 
		 
		 JPanel searchPn = new JPanel();
		 JPanel displayPn = new JPanel();
		 
		 searchPn.add(genreLabel);
		 searchPn.add(txtGenre);
		 searchPn.add(searchBtn);
		 searchPn.add(clearBtn);
		
		
		 displayPn.add(new JScrollPane(textArea));
		 
		searchTab.add(searchPn, BorderLayout.NORTH);
		searchTab.add(displayPn, BorderLayout.CENTER);
		
		searchBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String genre = txtGenre.getText();
		    	
		    	textArea.setText("");
		    	
		    	//update the text area
		    	for(Book b : BookList) {
		    		textArea.append(b.toString());
		    	}

			}
		});
		

		clearBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
			
				txtGenre.setText("");
				textArea.setText("");
				
			}
		});

		 
	}
	
   private boolean isUniqueISBN(String isbn) {
    for (Book book : BookList) {
        if (book.getIsbn().equals(isbn)) {
            return false; // ISBN is not unique
        }
    }
    return true; // ISBN is unique
}


	
	private void clearText() {
		txtIsbn.setText("");
		txtTitle.setText("");
		txtPrice.setText("");

	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		BookManagement win = new BookManagement();
		win.setVisible(true);
		win.pack();

	}

}

