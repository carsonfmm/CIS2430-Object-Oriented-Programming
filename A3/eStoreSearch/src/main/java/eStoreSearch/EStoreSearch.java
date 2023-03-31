package eStoreSearch;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

import javax.swing.*;

/*
 * Name: Carson Mifsud
 * Date: 2020-10-30
 * Description: This program is an eStore simulator which allows a user to add new books and electronics
 *              to the store. The program will read in a file at the start of the program and write any 
 *              new products to the same file throughout the program. The user also has the ability to
 *              search for a book or electronic by a specific productID, keyword(s) and/or date. All of
 *              this functionality is implemented using a basic Graphical User Interface
 */

public class EStoreSearch{

    public JFrame mainFrame;

    /**
     * Constructor which calls the prepareGUI() method
     */
    public EStoreSearch() {
        prepareGUI();
    }

    /**
     * Set main frame size and window close action listener
     */
    private void prepareGUI() {
        mainFrame = new JFrame("eStoreSearch");
        mainFrame.setSize(900, 700);
        mainFrame.setLayout(null);

        mainFrame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent windowEvent) {
                System.exit(0);
            }
        });

    }
    
    // Labels for main "Commands" screen
    private JComboBox<String> firstChoices;
    private JLabel entryMessage = new JLabel("Welcome to eStoreSearch");
    private JLabel entryMessage2 = new JLabel("Choose a command from the \"Commands\" menu above for");
    private JLabel entryMessage3 = new JLabel("adding a product, searching products, or quitting the program.");

    /**
     * Display "Commands" screen with several Labels and TextFields
     * 
     * @throws Exception
     */
    private void showBorderLayoutDemo() throws Exception {

        // Read the input file and store products into the store
        store.yourFile(myFile);

        // Print several Labels and ComboBox
        String[] userChoices = {"Commands", "Add", "Search", "Quit"};
        firstChoices = new JComboBox<>(userChoices);
        firstChoices.setSelectedIndex(0);
        firstChoices.setBounds(5, 5, 876, 20);

        entryMessage.setBounds(20, 200, 700, 50);
        entryMessage.setFont(new Font("Serif", Font.PLAIN, 60));
        mainFrame.add(entryMessage);

        entryMessage2.setBounds(20, 300, 700, 40);
        entryMessage2.setFont(new Font("Serif", Font.PLAIN, 25));
        mainFrame.add(entryMessage2);

        entryMessage3.setBounds(20, 335, 700, 40);
        entryMessage3.setFont(new Font("Serif", Font.PLAIN, 25));
        mainFrame.add(entryMessage3);

        mainFrame.add(firstChoices);
        
        firstChoices.addActionListener(new theFirst());

        mainFrame.setVisible(true);

    }

    /**
     * Clear all TextFields
     */
    class clearBoxes implements ActionListener {

        public void actionPerformed (ActionEvent e) {

            chooseProductID.setText("");
            chooseDescription.setText("");
            choosePrice.setText("");
            chooseYear.setText("");
            chooseAuthors.setText("");
            choosePublisher.setText("");
            chooseDescription2.setText("");
            choosePrice2.setText("");
            chooseProductID2.setText("");
            chooseYear2.setText("");
            chooseMaker.setText("");

        }

    }

    // Objects used for storing Books and Electronics in the eStore
    private Books myBook = new Books();
    private Electronics myElectronic = new Electronics();
    private theSearch store = new theSearch();
    private int adderTester = 0;

    /**
     * Adds a Book to the eStore
     */
    class addTheProductBook implements ActionListener {

        public void actionPerformed (ActionEvent e) {

            String myMessage = null;
            if ( adderTester == 0 ) {
                try {
					myMessage = store.yourBook ( myBook, myFile, chooseProductID.getText(), chooseDescription.getText(), choosePrice.getText(), chooseYear.getText(), chooseAuthors.getText(), choosePublisher.getText() );
				} catch (Exception e1) {
					e1.printStackTrace();
				}
            }
            if ( adderTester == 1 ) {
                try {
					myMessage = store.yourElectronic( myElectronic, myFile, chooseProductID.getText(), chooseDescription.getText(), choosePrice.getText(), chooseYear.getText(), chooseMaker.getText() );
				} catch (Exception e1) {
					e1.printStackTrace();
				}
            }
            memoDisplay.append(myMessage + "\n");
            counter = 0;

        }

    }

    /**
     * Adds an electronic to the eStore
     */
    class addTheProductElectronic implements ActionListener {

        public void actionPerformed (ActionEvent e) {

            String myMessage = null;

            try {
				myMessage = store.yourElectronic( myElectronic, myFile, chooseProductID.getText(), chooseDescription.getText(), choosePrice.getText(), chooseYear.getText(), chooseMaker.getText() );
			} catch (Exception e1) {
				e1.printStackTrace();
			}
            memoDisplay.append(myMessage + "\n");
            counter = 0;

        }

    }

    /**
     * Search through the eStore using users input
     */
    class theSearchingProduct implements ActionListener {

        public void actionPerformed (ActionEvent e) {

            ArrayList<String> myPrinter = new ArrayList<>();
            int nCounter = 0;

            myPrinter = (store.yourSearch (chooseProductID2.getText(), chooseDescription2.getText(), choosePrice2.getText(), chooseYear2.getText()));
            memoDisplay.setText("");
            for ( String searchTheProducts : myPrinter ) {
                if ( nCounter > 0 ) {
                    memoDisplay.append ("\n");
                }
                memoDisplay.append (searchTheProducts.toString());
                nCounter = nCounter + 1;
            }

            counter = 0;

        }

    }

    // Below are the several Labels, ComboBoxes, TextFields, TextAreas and Buttons that will be used throughout the program
    private String[] userChoices = {"Book", "Electronic"};
    private JComboBox<String> typeChoice = new JComboBox<>(userChoices);
    private JLabel addProduct = new JLabel("Adding a product");
    private JLabel addType = new JLabel("Type:");
    private JLabel addProductID = new JLabel("ProductID:");
    private JLabel addDescription = new JLabel("Description:");
    private JLabel addDescription2 = new JLabel("Description Keywords:");
    private JLabel searchProduct = new JLabel("Searching products");
    private JLabel addPrice = new JLabel("Price:");
    private JLabel addPrice2 = new JLabel("Start Year:");
    private JLabel addYear = new JLabel("Year:");
    private JLabel addYear2 = new JLabel("End Year:");
    private JLabel addAuthors = new JLabel("Authors:");
    private JLabel addPublisher = new JLabel("Publisher:");
    private JLabel addMaker = new JLabel("Maker:");
    private JTextField chooseAuthors = new JTextField(100);
    private JTextField chooseMaker = new JTextField(100);
    private JTextField choosePublisher = new JTextField(100);
    private JButton resetButton = new JButton("Reset");
    private JButton addButton = new JButton("Add");
    private JButton searchButton = new JButton("Search");
    private JLabel messagesLabel = new JLabel("Messages");
    private JLabel resultsLabel = new JLabel("Search Results");
    private JTextField chooseProductID = new JTextField(100);
    private JTextField chooseDescription = new JTextField(100);
    private JTextField choosePrice = new JTextField(100);
    private JTextField chooseYear = new JTextField(100);
    private JTextField chooseProductID2 = new JTextField(100);
    private JTextField chooseDescription2 = new JTextField(100);
    private JTextField choosePrice2 = new JTextField(100);
    private JTextField chooseYear2 = new JTextField(100);
    private JTextArea memoDisplay = new JTextArea(10, 50);
    private JScrollPane scrolledText = new JScrollPane(memoDisplay);
    private JLabel dashes1 = new JLabel("-------------------------------------------------------------------------------------------------" +
    "-----------------------------------------------------------------------------------------------------------------------------");
    private JLabel dashes2 = new JLabel("|\n");
    private JLabel dashes3 = new JLabel("|\n");
    private JLabel dashes4 = new JLabel("|\n");
    private JLabel dashes5 = new JLabel("|\n");
    private JLabel dashes6 = new JLabel("|\n");
    private JLabel dashes7 = new JLabel("|\n");
    private JLabel dashes8 = new JLabel("|\n");
    private JLabel dashes9 = new JLabel("|\n");
    private JLabel dashes10 = new JLabel("|\n");
    private JLabel dashes11 = new JLabel("|\n");
    private JLabel dashes12 = new JLabel("|\n");
    private JLabel dashes13 = new JLabel("|\n");
    private JLabel dashes14 = new JLabel("|\n");
    private int counter = 0;
    private int counter2 = 0;
    private int counter3 = 0;

    /**
     * Display the "Add" and "Search" screens, along with several Labels and Buttons
     */
    class theFirst implements ActionListener {

        public void actionPerformed (ActionEvent e) {

            // Display the entry screen if the user selects "Commands" from the ComboBox
            if (firstChoices.getSelectedIndex() == 0) {
                // Below, all Labels and Buttons are removed from the GUI
                counter = 0;
                memoDisplay.setText("");
                chooseProductID.setText("");
                chooseDescription.setText("");
                choosePrice.setText("");
                chooseYear.setText("");
                chooseAuthors.setText("");
                choosePublisher.setText("");
                chooseDescription2.setText("");
                choosePrice2.setText("");
                chooseProductID2.setText("");
                chooseYear2.setText("");
                chooseMaker.setText("");
                addType.setVisible(false);
                addProductID.setVisible(false);
                addDescription.setVisible(false);
                addPrice.setVisible(false);
                addYear.setVisible(false);
                addMaker.setVisible(false);
                addAuthors.setVisible(false);
                addPublisher.setVisible(false);
                typeChoice.setVisible(false);
                chooseProductID.setVisible(false);
                chooseDescription.setVisible(false);
                choosePrice.setVisible(false);
                chooseYear.setVisible(false);
                chooseMaker.setVisible(false);
                chooseAuthors.setVisible(false);
                choosePublisher.setVisible(false);
                resetButton.setVisible(false);
                addButton.setVisible(false);
                messagesLabel.setVisible(false);
                memoDisplay.setVisible(false);
                scrolledText.setVisible(false);
                dashes1.setVisible(false);
                dashes2.setVisible(false);
                dashes3.setVisible(false);
                dashes4.setVisible(false);
                dashes5.setVisible(false);
                dashes6.setVisible(false);
                dashes7.setVisible(false);
                dashes8.setVisible(false);
                dashes9.setVisible(false);
                dashes10.setVisible(false);
                dashes11.setVisible(false);
                dashes12.setVisible(false);
                dashes13.setVisible(false);
                dashes14.setVisible(false);
                addProduct.setVisible(false);
                addDescription2.setVisible(false);
                addPrice2.setVisible(false);
                addYear2.setVisible(false);
                chooseDescription2.setVisible(false);
                chooseProductID2.setVisible(false);
                choosePrice2.setVisible(false);
                chooseYear2.setVisible(false);
                searchProduct.setVisible(false);
                entryMessage.setVisible(false);
                entryMessage2.setVisible(false);
                entryMessage3.setVisible(false);
                mainFrame.remove(addType);
                mainFrame.remove(addProductID);
                mainFrame.remove(addDescription);
                mainFrame.remove(addPrice);
                mainFrame.remove(addYear);
                mainFrame.remove(addMaker);
                mainFrame.remove(addAuthors);
                mainFrame.remove(addPublisher);
                mainFrame.remove(typeChoice);
                mainFrame.remove(chooseProductID);
                mainFrame.remove(chooseDescription);
                mainFrame.remove(choosePrice);
                mainFrame.remove(chooseYear);
                mainFrame.remove(chooseMaker);
                mainFrame.remove(chooseAuthors);
                mainFrame.remove(choosePublisher);
                mainFrame.remove(resetButton);
                mainFrame.remove(addButton);
                mainFrame.remove(messagesLabel);
                mainFrame.remove(memoDisplay);
                mainFrame.remove(scrolledText);
                mainFrame.remove(dashes1);
                mainFrame.remove(dashes2);
                mainFrame.remove(dashes3);
                mainFrame.remove(dashes4);
                mainFrame.remove(dashes5);
                mainFrame.remove(dashes6);
                mainFrame.remove(dashes7);
                mainFrame.remove(dashes8);
                mainFrame.remove(dashes9);
                mainFrame.remove(dashes10);
                mainFrame.remove(dashes11);
                mainFrame.remove(dashes12);
                mainFrame.remove(dashes13);
                mainFrame.remove(dashes14);
                mainFrame.remove(addProduct);
                searchProduct.setVisible(false);
                searchProduct.setVisible(false);
                addDescription2.setVisible(false);
                addPrice2.setVisible(false);
                addYear2.setVisible(false);
                chooseDescription2.setVisible(false);
                chooseProductID2.setVisible(false);
                choosePrice2.setVisible(false);
                chooseYear2.setVisible(false);
                searchButton.setVisible(false);
                resultsLabel.setVisible(false);
                mainFrame.remove(chooseProductID2);
                mainFrame.remove(searchProduct);
                mainFrame.remove(addDescription2);
                mainFrame.remove(addPrice2);
                mainFrame.remove(addYear2);
                mainFrame.remove(chooseDescription2);
                mainFrame.remove(choosePrice2);
                mainFrame.remove(chooseYear2);
                mainFrame.remove(chooseYear2);
                mainFrame.remove(searchButton);
                mainFrame.remove(resultsLabel);
                mainFrame.remove(entryMessage);
                mainFrame.remove(entryMessage2);
                mainFrame.remove(entryMessage3);

                // Labels for the "Commands" screen
                entryMessage.setBounds(20, 200, 700, 50);
                entryMessage.setFont(new Font("Serif", Font.PLAIN, 60));
                mainFrame.add(entryMessage);
    
                entryMessage2.setBounds(20, 300, 700, 40);
                entryMessage2.setFont(new Font("Serif", Font.PLAIN, 25));
                mainFrame.add(entryMessage2);
    
                entryMessage3.setBounds(20, 335, 700, 40);
                entryMessage3.setFont(new Font("Serif", Font.PLAIN, 25));
                mainFrame.add(entryMessage3);
    
                entryMessage.setVisible(true);
                entryMessage2.setVisible(true);
                entryMessage3.setVisible(true);
                mainFrame.setVisible(true);

                // Set all TextFields to be blank
                memoDisplay.setText("");
                chooseProductID.setText("");
                chooseDescription.setText("");
                choosePrice.setText("");
                chooseYear.setText("");
                chooseAuthors.setText("");
                choosePublisher.setText("");
                chooseDescription2.setText("");
                choosePrice2.setText("");
                chooseProductID2.setText("");
                chooseYear2.setText("");
                chooseMaker.setText("");
                return;
            }

            // Display the "Add" screen if the user selects "Add" from the ComboBox
            if (firstChoices.getSelectedIndex() == 1) {
                if ( counter == 0 ) {
                    // Below, all Labels and Buttons are removed from the GUI
                    memoDisplay.setText("");
                    chooseProductID.setText("");
                    chooseDescription.setText("");
                    choosePrice.setText("");
                    chooseYear.setText("");
                    chooseAuthors.setText("");
                    choosePublisher.setText("");
                    chooseDescription2.setText("");
                    choosePrice2.setText("");
                    chooseProductID2.setText("");
                    chooseYear2.setText("");
                    chooseMaker.setText("");
                    addType.setVisible(false);
                    addProductID.setVisible(false);
                    addDescription.setVisible(false);
                    addPrice.setVisible(false);
                    addYear.setVisible(false);
                    addMaker.setVisible(false);
                    addAuthors.setVisible(false);
                    addPublisher.setVisible(false);
                    typeChoice.setVisible(false);
                    chooseProductID.setVisible(false);
                    chooseDescription.setVisible(false);
                    choosePrice.setVisible(false);
                    chooseYear.setVisible(false);
                    chooseMaker.setVisible(false);
                    chooseAuthors.setVisible(false);
                    choosePublisher.setVisible(false);
                    resetButton.setVisible(false);
                    addButton.setVisible(false);
                    messagesLabel.setVisible(false);
                    memoDisplay.setVisible(false);
                    scrolledText.setVisible(false);
                    dashes1.setVisible(false);
                    dashes2.setVisible(false);
                    dashes3.setVisible(false);
                    dashes4.setVisible(false);
                    dashes5.setVisible(false);
                    dashes6.setVisible(false);
                    dashes7.setVisible(false);
                    dashes8.setVisible(false);
                    dashes9.setVisible(false);
                    dashes10.setVisible(false);
                    dashes11.setVisible(false);
                    dashes12.setVisible(false);
                    dashes13.setVisible(false);
                    dashes14.setVisible(false);
                    addProduct.setVisible(false);
                    addDescription2.setVisible(false);
                    addPrice2.setVisible(false);
                    addYear2.setVisible(false);
                    chooseDescription2.setVisible(false);
                    chooseProductID2.setVisible(false);
                    choosePrice2.setVisible(false);
                    chooseYear2.setVisible(false);
                    searchProduct.setVisible(false);
                    entryMessage.setVisible(false);
                    entryMessage2.setVisible(false);
                    entryMessage3.setVisible(false);
                    mainFrame.remove(entryMessage);
                    mainFrame.remove(entryMessage2);
                    mainFrame.remove(entryMessage3);
                    mainFrame.remove(addType);
                    mainFrame.remove(addProductID);
                    mainFrame.remove(addDescription);
                    mainFrame.remove(addPrice);
                    mainFrame.remove(addYear);
                    mainFrame.remove(addMaker);
                    mainFrame.remove(addAuthors);
                    mainFrame.remove(addPublisher);
                    mainFrame.remove(typeChoice);
                    mainFrame.remove(chooseProductID);
                    mainFrame.remove(chooseDescription);
                    mainFrame.remove(choosePrice);
                    mainFrame.remove(chooseYear);
                    mainFrame.remove(chooseMaker);
                    mainFrame.remove(chooseAuthors);
                    mainFrame.remove(choosePublisher);
                    mainFrame.remove(resetButton);
                    mainFrame.remove(addButton);
                    mainFrame.remove(messagesLabel);
                    mainFrame.remove(memoDisplay);
                    mainFrame.remove(scrolledText);
                    mainFrame.remove(dashes1);
                    mainFrame.remove(dashes2);
                    mainFrame.remove(dashes3);
                    mainFrame.remove(dashes4);
                    mainFrame.remove(dashes5);
                    mainFrame.remove(dashes6);
                    mainFrame.remove(dashes7);
                    mainFrame.remove(dashes8);
                    mainFrame.remove(dashes9);
                    mainFrame.remove(dashes10);
                    mainFrame.remove(dashes11);
                    mainFrame.remove(dashes12);
                    mainFrame.remove(dashes13);
                    mainFrame.remove(dashes14);
                    mainFrame.remove(addProduct);
                    searchProduct.setVisible(false);
                    searchProduct.setVisible(false);
                    addDescription2.setVisible(false);
                    addPrice2.setVisible(false);
                    addYear2.setVisible(false);
                    chooseDescription2.setVisible(false);
                    chooseProductID2.setVisible(false);
                    choosePrice2.setVisible(false);
                    chooseYear2.setVisible(false);
                    searchButton.setVisible(false);
                    resultsLabel.setVisible(false);
                    mainFrame.remove(chooseProductID2);
                    mainFrame.remove(searchProduct);
                    mainFrame.remove(addDescription2);
                    mainFrame.remove(addPrice2);
                    mainFrame.remove(addYear2);
                    mainFrame.remove(chooseDescription2);
                    mainFrame.remove(choosePrice2);
                    mainFrame.remove(chooseYear2);
                    mainFrame.remove(chooseYear2);
                    mainFrame.remove(searchButton);
                    mainFrame.remove(resultsLabel);
                    mainFrame.setVisible(true);

                    // Labels for the input of a Book
                    addProduct.setBounds(25, 25, 250, 40);
                    addProduct.setFont(new Font("Serif", Font.BOLD, 25));
                    mainFrame.add(addProduct);

                    addType.setBounds(40, 60, 200, 40);
                    addType.setFont(new Font("Serif", Font.BOLD, 20));
                    mainFrame.add(addType);

                    addProductID.setBounds(40, 95, 200, 40);
                    addProductID.setFont(new Font("Serif", Font.BOLD, 20));
                    mainFrame.add(addProductID);

                    addDescription.setBounds(40, 130, 200, 40);
                    addDescription.setFont(new Font("Serif", Font.BOLD, 20));
                    mainFrame.add(addDescription);

                    addPrice.setBounds(40, 165, 200, 40);
                    addPrice.setFont(new Font("Serif", Font.BOLD, 20));
                    mainFrame.add(addPrice);

                    addYear.setBounds(40, 200, 200, 40);
                    addYear.setFont(new Font("Serif", Font.BOLD, 20));
                    mainFrame.add(addYear);

                    addAuthors.setBounds(40, 235, 200, 40);
                    addAuthors.setFont(new Font("Serif", Font.BOLD, 20));
                    mainFrame.add(addAuthors);

                    addPublisher.setBounds(40, 270, 200, 40);
                    addPublisher.setFont(new Font("Serif", Font.BOLD, 20));
                    mainFrame.add(addPublisher);

                    dashes1.setBounds(0, 305, 900, 20);
                    dashes1.setFont(new Font("Serif", Font.BOLD, 20));
                    mainFrame.add(dashes1);

                    // TextFields for the input of a Book
                    chooseProductID.setBounds(150, 107, 100, 20);
                    mainFrame.add(chooseProductID);

                    chooseDescription.setBounds(158, 142, 250, 20);
                    mainFrame.add(chooseDescription);

                    choosePrice.setBounds(105, 177, 100, 20);
                    mainFrame.add(choosePrice);

                    chooseYear.setBounds(103, 212, 100, 20);
                    mainFrame.add(chooseYear);

                    chooseAuthors.setBounds(128, 247, 250, 20);
                    mainFrame.add(chooseAuthors);

                    choosePublisher.setBounds(143, 282, 250, 20);
                    mainFrame.add(choosePublisher);

                    // Dashes to help organize the GUI
                    dashes2.setBounds(550, 20, 700, 30);
                    dashes3.setBounds(550, 20, 700, 75);
                    dashes4.setBounds(550, 20, 700, 120);
                    dashes5.setBounds(550, 20, 700, 165);
                    dashes6.setBounds(550, 20, 700, 210);
                    dashes7.setBounds(550, 20, 700, 255);
                    dashes8.setBounds(550, 20, 700, 300);
                    dashes9.setBounds(550, 20, 700, 345);
                    dashes10.setBounds(550, 20, 700, 390);
                    dashes11.setBounds(550, 20, 700, 435);
                    dashes12.setBounds(550, 20, 700, 480);
                    dashes13.setBounds(550, 20, 700, 525);
                    dashes14.setBounds(550, 20, 700, 570);
                    dashes2.setFont(new Font("Serif", Font.BOLD, 20));
                    dashes3.setFont(new Font("Serif", Font.BOLD, 20));
                    dashes4.setFont(new Font("Serif", Font.BOLD, 20));
                    dashes5.setFont(new Font("Serif", Font.BOLD, 20));
                    dashes6.setFont(new Font("Serif", Font.BOLD, 20));
                    dashes7.setFont(new Font("Serif", Font.BOLD, 20));
                    dashes8.setFont(new Font("Serif", Font.BOLD, 20));
                    dashes9.setFont(new Font("Serif", Font.BOLD, 20));
                    dashes10.setFont(new Font("Serif", Font.BOLD, 20));
                    dashes11.setFont(new Font("Serif", Font.BOLD, 20));
                    dashes12.setFont(new Font("Serif", Font.BOLD, 20));
                    dashes13.setFont(new Font("Serif", Font.BOLD, 20));
                    dashes14.setFont(new Font("Serif", Font.BOLD, 20));
                    mainFrame.add(dashes2);
                    mainFrame.add(dashes3);
                    mainFrame.add(dashes4);
                    mainFrame.add(dashes5);
                    mainFrame.add(dashes6);
                    mainFrame.add(dashes7);
                    mainFrame.add(dashes8);
                    mainFrame.add(dashes9);
                    mainFrame.add(dashes10);
                    mainFrame.add(dashes11);
                    mainFrame.add(dashes12);
                    mainFrame.add(dashes13);
                    mainFrame.add(dashes14);

                    // Buttons for user to either add a product or reset the TextFields
                    resetButton.setBounds(675, 100, 100, 20);
                    mainFrame.add(resetButton);
                    resetButton.addActionListener(new clearBoxes());

                    addButton.setBounds(675, 225, 100, 20);
                    mainFrame.add(addButton);
                    adderTester = 0;

                    // Add the Book to the eStore
                    if ( counter2 == 0 ) {
                        addButton.addActionListener(new addTheProductBook());
                        counter2 = 1;
                    }

                    messagesLabel.setFont(new Font("Serif", Font.BOLD, 25));
                    messagesLabel.setBounds(15, 340, 100, 40);
                    mainFrame.add(messagesLabel);

                    // TextArea for messages to be displayed to the user
                    memoDisplay.setSize(850, 300);
                    memoDisplay.setBackground(Color.WHITE);

                    scrolledText.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
                    scrolledText.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);

                    mainFrame.add(scrolledText);

                    scrolledText.setBounds(15, 400, 855, 250);
                    memoDisplay.setVisible(true);
                    scrolledText.setVisible(true);

                    typeChoice.setSelectedIndex(0);
                    typeChoice.setBounds(105, 72, 100, 20);

                    mainFrame.add(typeChoice);

                    // Set the required Buttons and Labels to be visable to the user
                    addType.setVisible(true);
                    addProductID.setVisible(true);
                    addDescription.setVisible(true);
                    addPrice.setVisible(true);
                    addYear.setVisible(true);
                    addMaker.setVisible(true);
                    addAuthors.setVisible(true);
                    addPublisher.setVisible(true);
                    typeChoice.setVisible(true);
                    chooseProductID.setVisible(true);
                    chooseDescription.setVisible(true);
                    choosePrice.setVisible(true);
                    chooseYear.setVisible(true);
                    chooseMaker.setVisible(true);
                    chooseAuthors.setVisible(true);
                    choosePublisher.setVisible(true);
                    resetButton.setVisible(true);
                    addButton.setVisible(true);
                    messagesLabel.setVisible(true);
                    memoDisplay.setVisible(true);
                    scrolledText.setVisible(true);
                    dashes1.setVisible(true);
                    dashes2.setVisible(true);
                    dashes3.setVisible(true);
                    dashes4.setVisible(true);
                    dashes5.setVisible(true);
                    dashes6.setVisible(true);
                    dashes7.setVisible(true);
                    dashes8.setVisible(true);
                    dashes9.setVisible(true);
                    dashes10.setVisible(true);
                    dashes11.setVisible(true);
                    dashes12.setVisible(true);
                    dashes13.setVisible(true);
                    dashes14.setVisible(true);
                    addProduct.setVisible(true);

                    // Set all TextFields to be blank
                    memoDisplay.setText("");
                    chooseProductID.setText("");
                    chooseDescription.setText("");
                    choosePrice.setText("");
                    chooseYear.setText("");
                    chooseAuthors.setText("");
                    choosePublisher.setText("");
                    chooseDescription2.setText("");
                    choosePrice2.setText("");
                    chooseProductID2.setText("");
                    chooseYear2.setText("");
                    chooseMaker.setText("");
                }

                typeChoice.addActionListener(new ActionListener() {

                    public void actionPerformed (ActionEvent e) {

                        if ( typeChoice.getSelectedIndex() == 0 ) {
                            // Below, all Labels and Buttons are removed from the GUI
                            memoDisplay.setText("");
                            chooseProductID.setText("");
                            chooseDescription.setText("");
                            choosePrice.setText("");
                            chooseYear.setText("");
                            chooseAuthors.setText("");
                            choosePublisher.setText("");
                            chooseDescription2.setText("");
                            choosePrice2.setText("");
                            chooseProductID2.setText("");
                            chooseYear2.setText("");
                            chooseMaker.setText("");
                            addType.setVisible(false);
                            addProductID.setVisible(false);
                            addDescription.setVisible(false);
                            addPrice.setVisible(false);
                            addYear.setVisible(false);
                            addMaker.setVisible(false);
                            addAuthors.setVisible(false);
                            addPublisher.setVisible(false);
                            typeChoice.setVisible(false);
                            chooseProductID.setVisible(false);
                            chooseDescription.setVisible(false);
                            choosePrice.setVisible(false);
                            chooseYear.setVisible(false);
                            chooseMaker.setVisible(false);
                            chooseAuthors.setVisible(false);
                            choosePublisher.setVisible(false);
                            resetButton.setVisible(false);
                            addButton.setVisible(false);
                            messagesLabel.setVisible(false);
                            memoDisplay.setVisible(false);
                            scrolledText.setVisible(false);
                            dashes1.setVisible(false);
                            dashes2.setVisible(false);
                            dashes3.setVisible(false);
                            dashes4.setVisible(false);
                            dashes5.setVisible(false);
                            dashes6.setVisible(false);
                            dashes7.setVisible(false);
                            dashes8.setVisible(false);
                            dashes9.setVisible(false);
                            dashes10.setVisible(false);
                            dashes11.setVisible(false);
                            dashes12.setVisible(false);
                            dashes13.setVisible(false);
                            dashes14.setVisible(false);
                            addProduct.setVisible(false);
                            addDescription2.setVisible(false);
                            addPrice2.setVisible(false);
                            addYear2.setVisible(false);
                            chooseDescription2.setVisible(false);
                            chooseProductID2.setVisible(false);
                            choosePrice2.setVisible(false);
                            chooseYear2.setVisible(false);
                            searchProduct.setVisible(false);
                            entryMessage.setVisible(false);
                            entryMessage2.setVisible(false);
                            entryMessage3.setVisible(false);
                            mainFrame.remove(entryMessage);
                            mainFrame.remove(entryMessage2);
                            mainFrame.remove(entryMessage3);
                            mainFrame.remove(addType);
                            mainFrame.remove(addProductID);
                            mainFrame.remove(addDescription);
                            mainFrame.remove(addPrice);
                            mainFrame.remove(addYear);
                            mainFrame.remove(addMaker);
                            mainFrame.remove(addAuthors);
                            mainFrame.remove(addPublisher);
                            mainFrame.remove(typeChoice);
                            mainFrame.remove(chooseProductID);
                            mainFrame.remove(chooseDescription);
                            mainFrame.remove(choosePrice);
                            mainFrame.remove(chooseYear);
                            mainFrame.remove(chooseMaker);
                            mainFrame.remove(chooseAuthors);
                            mainFrame.remove(choosePublisher);
                            mainFrame.remove(resetButton);
                            mainFrame.remove(addButton);
                            mainFrame.remove(messagesLabel);
                            mainFrame.remove(memoDisplay);
                            mainFrame.remove(scrolledText);
                            mainFrame.remove(dashes1);
                            mainFrame.remove(dashes2);
                            mainFrame.remove(dashes3);
                            mainFrame.remove(dashes4);
                            mainFrame.remove(dashes5);
                            mainFrame.remove(dashes6);
                            mainFrame.remove(dashes7);
                            mainFrame.remove(dashes8);
                            mainFrame.remove(dashes9);
                            mainFrame.remove(dashes10);
                            mainFrame.remove(dashes11);
                            mainFrame.remove(dashes12);
                            mainFrame.remove(dashes13);
                            mainFrame.remove(dashes14);
                            mainFrame.remove(addProduct);
                            searchProduct.setVisible(false);
                            searchProduct.setVisible(false);
                            addDescription2.setVisible(false);
                            addPrice2.setVisible(false);
                            addYear2.setVisible(false);
                            chooseDescription2.setVisible(false);
                            chooseProductID2.setVisible(false);
                            choosePrice2.setVisible(false);
                            chooseYear2.setVisible(false);
                            searchButton.setVisible(false);
                            resultsLabel.setVisible(false);
                            mainFrame.remove(chooseProductID2);
                            mainFrame.remove(searchProduct);
                            mainFrame.remove(addDescription2);
                            mainFrame.remove(addPrice2);
                            mainFrame.remove(addYear2);
                            mainFrame.remove(chooseDescription2);
                            mainFrame.remove(choosePrice2);
                            mainFrame.remove(chooseYear2);
                            mainFrame.remove(chooseYear2);
                            mainFrame.remove(searchButton);
                            mainFrame.remove(resultsLabel);
                            mainFrame.setVisible(true);

                            // Labels for adding a Book
                            addProduct.setBounds(25, 25, 250, 40);
                            addProduct.setFont(new Font("Serif", Font.BOLD, 25));
                            mainFrame.add(addProduct);
            
                            addType.setBounds(40, 60, 200, 40);
                            addType.setFont(new Font("Serif", Font.BOLD, 20));
                            mainFrame.add(addType);
            
                            addProductID.setBounds(40, 95, 200, 40);
                            addProductID.setFont(new Font("Serif", Font.BOLD, 20));
                            mainFrame.add(addProductID);
            
                            addDescription.setBounds(40, 130, 200, 40);
                            addDescription.setFont(new Font("Serif", Font.BOLD, 20));
                            mainFrame.add(addDescription);
            
                            addPrice.setBounds(40, 165, 200, 40);
                            addPrice.setFont(new Font("Serif", Font.BOLD, 20));
                            mainFrame.add(addPrice);
            
                            addYear.setBounds(40, 200, 200, 40);
                            addYear.setFont(new Font("Serif", Font.BOLD, 20));
                            mainFrame.add(addYear);
            
                            addAuthors.setBounds(40, 235, 200, 40);
                            addAuthors.setFont(new Font("Serif", Font.BOLD, 20));
                            mainFrame.add(addAuthors);
            
                            addPublisher.setBounds(40, 270, 200, 40);
                            addPublisher.setFont(new Font("Serif", Font.BOLD, 20));
                            mainFrame.add(addPublisher);
            
                            dashes1.setBounds(0, 305, 900, 20);
                            dashes1.setFont(new Font("Serif", Font.BOLD, 20));
                            mainFrame.add(dashes1);
            
                            // TextFields for adding a Book
                            chooseProductID.setBounds(150, 107, 100, 20);
                            mainFrame.add(chooseProductID);
            
                            chooseDescription.setBounds(158, 142, 250, 20);
                            mainFrame.add(chooseDescription);
            
                            choosePrice.setBounds(105, 177, 100, 20);
                            mainFrame.add(choosePrice);
            
                            chooseYear.setBounds(103, 212, 100, 20);
                            mainFrame.add(chooseYear);
            
                            chooseAuthors.setBounds(128, 247, 250, 20);
                            mainFrame.add(chooseAuthors);
            
                            choosePublisher.setBounds(143, 282, 250, 20);
                            mainFrame.add(choosePublisher);

                            // Dashes to make the GUI more user-friendly
                            dashes2.setBounds(550, 20, 700, 30);
                            dashes3.setBounds(550, 20, 700, 75);
                            dashes4.setBounds(550, 20, 700, 120);
                            dashes5.setBounds(550, 20, 700, 165);
                            dashes6.setBounds(550, 20, 700, 210);
                            dashes7.setBounds(550, 20, 700, 255);
                            dashes8.setBounds(550, 20, 700, 300);
                            dashes9.setBounds(550, 20, 700, 345);
                            dashes10.setBounds(550, 20, 700, 390);
                            dashes11.setBounds(550, 20, 700, 435);
                            dashes12.setBounds(550, 20, 700, 480);
                            dashes13.setBounds(550, 20, 700, 525);
                            dashes14.setBounds(550, 20, 700, 570);
                            dashes2.setFont(new Font("Serif", Font.BOLD, 20));
                            dashes3.setFont(new Font("Serif", Font.BOLD, 20));
                            dashes4.setFont(new Font("Serif", Font.BOLD, 20));
                            dashes5.setFont(new Font("Serif", Font.BOLD, 20));
                            dashes6.setFont(new Font("Serif", Font.BOLD, 20));
                            dashes7.setFont(new Font("Serif", Font.BOLD, 20));
                            dashes8.setFont(new Font("Serif", Font.BOLD, 20));
                            dashes9.setFont(new Font("Serif", Font.BOLD, 20));
                            dashes10.setFont(new Font("Serif", Font.BOLD, 20));
                            dashes11.setFont(new Font("Serif", Font.BOLD, 20));
                            dashes12.setFont(new Font("Serif", Font.BOLD, 20));
                            dashes13.setFont(new Font("Serif", Font.BOLD, 20));
                            dashes14.setFont(new Font("Serif", Font.BOLD, 20));
                            mainFrame.add(dashes2);
                            mainFrame.add(dashes3);
                            mainFrame.add(dashes4);
                            mainFrame.add(dashes5);
                            mainFrame.add(dashes6);
                            mainFrame.add(dashes7);
                            mainFrame.add(dashes8);
                            mainFrame.add(dashes9);
                            mainFrame.add(dashes10);
                            mainFrame.add(dashes11);
                            mainFrame.add(dashes12);
                            mainFrame.add(dashes13);
                            mainFrame.add(dashes14);
            
                            // Buttons for user to add a product or reset the TextFields
                            resetButton.setBounds(675, 100, 100, 20);
                            mainFrame.add(resetButton);
                            resetButton.addActionListener(new clearBoxes());
            
                            addButton.setBounds(675, 225, 100, 20);
                            mainFrame.add(addButton);
                            adderTester = 0;

                            // Add the Book to the eStore
                            if ( counter <= 0 ) {
                                if ( counter2 == 0 ) {
                                    addButton.addActionListener(new addTheProductBook());
                                    counter2 = 1;
                                }
                            }

                            messagesLabel.setFont(new Font("Serif", Font.BOLD, 25));
                            messagesLabel.setBounds(15, 340, 100, 40);
                            mainFrame.add(messagesLabel);
            
                            // TextArea for messages to be displayed to the user
                            memoDisplay.setSize(850, 300);
                            memoDisplay.setBackground(Color.WHITE);
            
                            scrolledText.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
                            scrolledText.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
            
                            mainFrame.add(scrolledText);
            
                            scrolledText.setBounds(15, 400, 855, 250);
                            memoDisplay.setVisible(true);
                            scrolledText.setVisible(true);
            
                            typeChoice.setSelectedIndex(0);
                            typeChoice.setBounds(105, 72, 100, 20);
            
                            mainFrame.add(typeChoice);
            
                            // Set all required Labels and Buttons to be visable on the GUI
                            addType.setVisible(true);
                            addProductID.setVisible(true);
                            addDescription.setVisible(true);
                            addPrice.setVisible(true);
                            addYear.setVisible(true);
                            addMaker.setVisible(true);
                            addAuthors.setVisible(true);
                            addPublisher.setVisible(true);
                            typeChoice.setVisible(true);
                            chooseProductID.setVisible(true);
                            chooseDescription.setVisible(true);
                            choosePrice.setVisible(true);
                            chooseYear.setVisible(true);
                            chooseMaker.setVisible(true);
                            chooseAuthors.setVisible(true);
                            choosePublisher.setVisible(true);
                            resetButton.setVisible(true);
                            addButton.setVisible(true);
                            messagesLabel.setVisible(true);
                            memoDisplay.setVisible(true);
                            scrolledText.setVisible(true);
                            dashes1.setVisible(true);
                            dashes2.setVisible(true);
                            dashes3.setVisible(true);
                            dashes4.setVisible(true);
                            dashes5.setVisible(true);
                            dashes6.setVisible(true);
                            dashes7.setVisible(true);
                            dashes8.setVisible(true);
                            dashes9.setVisible(true);
                            dashes10.setVisible(true);
                            dashes11.setVisible(true);
                            dashes12.setVisible(true);
                            dashes13.setVisible(true);
                            dashes14.setVisible(true);
                            addProduct.setVisible(true);
                            memoDisplay.setText("");
                            chooseProductID.setText("");
                            chooseDescription.setText("");
                            choosePrice.setText("");
                            chooseYear.setText("");
                            chooseAuthors.setText("");
                            choosePublisher.setText("");
                            chooseDescription2.setText("");
                            choosePrice2.setText("");
                            chooseProductID2.setText("");
                            chooseYear2.setText("");
                            chooseMaker.setText("");
                            return;
                        }

                        if ( typeChoice.getSelectedIndex() == 1) {
                            // Below, all required Labels and Buttons are removed from the GUI
                            memoDisplay.setText("");
                            chooseProductID.setText("");
                            chooseDescription.setText("");
                            choosePrice.setText("");
                            chooseYear.setText("");
                            chooseAuthors.setText("");
                            choosePublisher.setText("");
                            chooseDescription2.setText("");
                            choosePrice2.setText("");
                            chooseProductID2.setText("");
                            chooseYear2.setText("");
                            chooseMaker.setText("");
                            addButton.setVisible(false);
                            addAuthors.setVisible(false);
                            addPublisher.setVisible(false);
                            chooseAuthors.setVisible(false);
                            choosePublisher.setVisible(false);
                            mainFrame.remove(addButton);
                            mainFrame.remove(addAuthors);
                            mainFrame.remove(addPublisher);
                            mainFrame.remove(chooseAuthors);
                            mainFrame.remove(choosePublisher);
                            mainFrame.setVisible(true);

                            addButton.setBounds(675, 225, 100, 20);
                            mainFrame.add(addButton);
                            adderTester = 1;
                            // Add the Electronic to the eStore
                            if ( counter <= 0 ) {
                                if ( counter2 == 0 ) {
                                    addButton.addActionListener(new addTheProductElectronic());
                                    counter2 = 1;
                                }
                            }

                            // TextField and Label for the Maker input
                            chooseMaker.setBounds(118, 247, 250, 20);
                            mainFrame.add(chooseMaker);

                            addMaker.setBounds(40, 235, 200, 40);
                            addMaker.setFont(new Font("Serif", Font.BOLD, 20));
                            mainFrame.add(addMaker);

                            // Required Buttons and Labels are made visable on the GUI
                            addButton.setVisible(true);
                            addMaker.setVisible(true);
                            chooseMaker.setVisible(true);
                            mainFrame.setVisible(true);
                            return;
                        }
                        return;
                    }

                });

                counter = counter + 1;

                mainFrame.setVisible(true);
                return;
            }

            // Display the "Search" screen if the user selects "Search" from the ComboBox
            if (firstChoices.getSelectedIndex() == 2) {
                // Below, all Labels and Buttons are removed from the GUI
                counter = 0;
                memoDisplay.setText("");
                chooseProductID.setText("");
                chooseDescription.setText("");
                choosePrice.setText("");
                chooseYear.setText("");
                chooseAuthors.setText("");
                choosePublisher.setText("");
                chooseDescription2.setText("");
                choosePrice2.setText("");
                chooseProductID2.setText("");
                chooseYear2.setText("");
                chooseMaker.setText("");
                addType.setVisible(false);
                addProductID.setVisible(false);
                addDescription.setVisible(false);
                addPrice.setVisible(false);
                addYear.setVisible(false);
                addMaker.setVisible(false);
                addAuthors.setVisible(false);
                addPublisher.setVisible(false);
                typeChoice.setVisible(false);
                chooseProductID.setVisible(false);
                chooseDescription.setVisible(false);
                choosePrice.setVisible(false);
                chooseYear.setVisible(false);
                chooseMaker.setVisible(false);
                chooseAuthors.setVisible(false);
                choosePublisher.setVisible(false);
                resetButton.setVisible(false);
                addButton.setVisible(false);
                messagesLabel.setVisible(false);
                memoDisplay.setVisible(false);
                scrolledText.setVisible(false);
                dashes1.setVisible(false);
                dashes2.setVisible(false);
                dashes3.setVisible(false);
                dashes4.setVisible(false);
                dashes5.setVisible(false);
                dashes6.setVisible(false);
                dashes7.setVisible(false);
                dashes8.setVisible(false);
                dashes9.setVisible(false);
                dashes10.setVisible(false);
                dashes11.setVisible(false);
                dashes12.setVisible(false);
                dashes13.setVisible(false);
                dashes14.setVisible(false);
                addProduct.setVisible(false);
                addDescription2.setVisible(false);
                addPrice2.setVisible(false);
                addYear2.setVisible(false);
                chooseDescription2.setVisible(false);
                chooseProductID2.setVisible(false);
                choosePrice2.setVisible(false);
                chooseYear2.setVisible(false);
                searchProduct.setVisible(false);
                entryMessage.setVisible(false);
                entryMessage2.setVisible(false);
                entryMessage3.setVisible(false);
                mainFrame.remove(entryMessage);
                mainFrame.remove(entryMessage2);
                mainFrame.remove(entryMessage3);
                mainFrame.remove(addType);
                mainFrame.remove(addProductID);
                mainFrame.remove(addDescription);
                mainFrame.remove(addPrice);
                mainFrame.remove(addYear);
                mainFrame.remove(addMaker);
                mainFrame.remove(addAuthors);
                mainFrame.remove(addPublisher);
                mainFrame.remove(typeChoice);
                mainFrame.remove(chooseProductID);
                mainFrame.remove(chooseDescription);
                mainFrame.remove(choosePrice);
                mainFrame.remove(chooseYear);
                mainFrame.remove(chooseMaker);
                mainFrame.remove(chooseAuthors);
                mainFrame.remove(choosePublisher);
                mainFrame.remove(resetButton);
                mainFrame.remove(addButton);
                mainFrame.remove(messagesLabel);
                mainFrame.remove(memoDisplay);
                mainFrame.remove(scrolledText);
                mainFrame.remove(dashes1);
                mainFrame.remove(dashes2);
                mainFrame.remove(dashes3);
                mainFrame.remove(dashes4);
                mainFrame.remove(dashes5);
                mainFrame.remove(dashes6);
                mainFrame.remove(dashes7);
                mainFrame.remove(dashes8);
                mainFrame.remove(dashes9);
                mainFrame.remove(dashes10);
                mainFrame.remove(dashes11);
                mainFrame.remove(dashes12);
                mainFrame.remove(dashes13);
                mainFrame.remove(dashes14);
                mainFrame.remove(addProduct);
                searchProduct.setVisible(false);
                searchProduct.setVisible(false);
                addDescription2.setVisible(false);
                addPrice2.setVisible(false);
                addYear2.setVisible(false);
                chooseDescription2.setVisible(false);
                chooseProductID2.setVisible(false);
                choosePrice2.setVisible(false);
                chooseYear2.setVisible(false);
                searchButton.setVisible(false);
                resultsLabel.setVisible(false);
                mainFrame.remove(chooseProductID2);
                mainFrame.remove(searchProduct);
                mainFrame.remove(addDescription2);
                mainFrame.remove(addPrice2);
                mainFrame.remove(addYear2);
                mainFrame.remove(chooseDescription2);
                mainFrame.remove(choosePrice2);
                mainFrame.remove(chooseYear2);
                mainFrame.remove(chooseYear2);
                mainFrame.remove(searchButton);
                mainFrame.remove(resultsLabel);
                mainFrame.setVisible(true);

                // Labels for the "Search" screen
                searchProduct.setBounds(25, 25, 250, 40);
                searchProduct.setFont(new Font("Serif", Font.BOLD, 25));
                mainFrame.add(searchProduct);

                addProductID.setBounds(40, 95, 200, 40);
                addProductID.setFont(new Font("Serif", Font.BOLD, 20));
                mainFrame.add(addProductID);

                addDescription2.setBounds(40, 130, 200, 40);
                addDescription2.setFont(new Font("Serif", Font.BOLD, 20));
                mainFrame.add(addDescription2);

                addPrice2.setBounds(40, 165, 200, 40);
                addPrice2.setFont(new Font("Serif", Font.BOLD, 20));
                mainFrame.add(addPrice2);

                addYear2.setBounds(40, 200, 200, 40);
                addYear2.setFont(new Font("Serif", Font.BOLD, 20));
                mainFrame.add(addYear2);

                dashes1.setBounds(0, 305, 900, 20);
                dashes1.setFont(new Font("Serif", Font.BOLD, 20));
                mainFrame.add(dashes1);

                // TextFields for the "Search" screen
                chooseProductID2.setBounds(150, 107, 100, 20);
                mainFrame.add(chooseProductID2);

                chooseDescription2.setBounds(247, 142, 250, 20);
                mainFrame.add(chooseDescription2);

                choosePrice2.setBounds(147, 177, 100, 20);
                mainFrame.add(choosePrice2);

                chooseYear2.setBounds(139, 212, 100, 20);
                mainFrame.add(chooseYear2);

                searchButton.setBounds(675, 225, 100, 20);
                mainFrame.add(searchButton);

                // Search for the product based on the users input
                if ( counter3 == 0 ) {
                    searchButton.addActionListener(new theSearchingProduct());
                    counter3 = 1;
                }

                resultsLabel.setFont(new Font("Serif", Font.BOLD, 25));
                resultsLabel.setBounds(15, 340, 300, 40);
                mainFrame.add(resultsLabel);

                // Dashes to make the GUI more organized
                dashes2.setBounds(550, 20, 700, 30);
                dashes3.setBounds(550, 20, 700, 75);
                dashes4.setBounds(550, 20, 700, 120);
                dashes5.setBounds(550, 20, 700, 165);
                dashes6.setBounds(550, 20, 700, 210);
                dashes7.setBounds(550, 20, 700, 255);
                dashes8.setBounds(550, 20, 700, 300);
                dashes9.setBounds(550, 20, 700, 345);
                dashes10.setBounds(550, 20, 700, 390);
                dashes11.setBounds(550, 20, 700, 435);
                dashes12.setBounds(550, 20, 700, 480);
                dashes13.setBounds(550, 20, 700, 525);
                dashes14.setBounds(550, 20, 700, 570);
                dashes2.setFont(new Font("Serif", Font.BOLD, 20));
                dashes3.setFont(new Font("Serif", Font.BOLD, 20));
                dashes4.setFont(new Font("Serif", Font.BOLD, 20));
                dashes5.setFont(new Font("Serif", Font.BOLD, 20));
                dashes6.setFont(new Font("Serif", Font.BOLD, 20));
                dashes7.setFont(new Font("Serif", Font.BOLD, 20));
                dashes8.setFont(new Font("Serif", Font.BOLD, 20));
                dashes9.setFont(new Font("Serif", Font.BOLD, 20));
                dashes10.setFont(new Font("Serif", Font.BOLD, 20));
                dashes11.setFont(new Font("Serif", Font.BOLD, 20));
                dashes12.setFont(new Font("Serif", Font.BOLD, 20));
                dashes13.setFont(new Font("Serif", Font.BOLD, 20));
                dashes14.setFont(new Font("Serif", Font.BOLD, 20));
                mainFrame.add(dashes2);
                mainFrame.add(dashes3);
                mainFrame.add(dashes4);
                mainFrame.add(dashes5);
                mainFrame.add(dashes6);
                mainFrame.add(dashes7);
                mainFrame.add(dashes8);
                mainFrame.add(dashes9);
                mainFrame.add(dashes10);
                mainFrame.add(dashes11);
                mainFrame.add(dashes12);
                mainFrame.add(dashes13);
                mainFrame.add(dashes14);

                // Buttons to search the eStore and a reset button to clear the TextFields
                resetButton.setBounds(675, 100, 100, 20);
                mainFrame.add(resetButton);
                resetButton.addActionListener(new clearBoxes());

                // TextArea to display the search results to the user
                memoDisplay.setSize(850, 300);
                memoDisplay.setBackground(Color.WHITE);

                scrolledText.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
                scrolledText.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);

                mainFrame.add(scrolledText);

                scrolledText.setBounds(15, 400, 855, 250);

                // Set all required Buttons and Fields to be visable
                searchProduct.setVisible(true);
                addDescription2.setVisible(true);
                addPrice2.setVisible(true);
                addYear2.setVisible(true);
                chooseProductID2.setVisible(true);
                chooseDescription2.setVisible(true);
                choosePrice2.setVisible(true);
                chooseYear2.setVisible(true);
                searchButton.setVisible(true);
                resultsLabel.setVisible(true);
                resetButton.setVisible(true);
                memoDisplay.setVisible(true);
                scrolledText.setVisible(true);
                dashes1.setVisible(true);
                dashes2.setVisible(true);
                dashes3.setVisible(true);
                dashes4.setVisible(true);
                dashes5.setVisible(true);
                dashes6.setVisible(true);
                dashes7.setVisible(true);
                dashes8.setVisible(true);
                dashes9.setVisible(true);
                dashes10.setVisible(true);
                dashes11.setVisible(true);
                dashes12.setVisible(true);
                dashes13.setVisible(true);
                dashes14.setVisible(true);
                addProductID.setVisible(true);
                memoDisplay.setText("");
                chooseProductID.setText("");
                chooseDescription.setText("");
                choosePrice.setText("");
                chooseYear.setText("");
                chooseAuthors.setText("");
                choosePublisher.setText("");
                chooseDescription2.setText("");
                choosePrice2.setText("");
                chooseProductID2.setText("");
                chooseYear2.setText("");
                chooseMaker.setText("");

                mainFrame.setVisible(true);
                return;
            }

            // Close the program if the user selects "Quit" from the ComboBox
            if (firstChoices.getSelectedIndex() == 3) {

                System.exit(0);

            }
        }

    }

    // Variable for the users file
    public static String myFile;

    public static void main(String[] args) throws Exception {

        // This initializes the variable myFile with the first argument given in the command line
        myFile = args[0];

        // Call the GUI
        EStoreSearch swingLayoutDemo = new EStoreSearch();
        swingLayoutDemo.showBorderLayoutDemo();

    }
}