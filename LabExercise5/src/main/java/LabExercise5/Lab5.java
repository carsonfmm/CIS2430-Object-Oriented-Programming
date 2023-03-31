package LabExercise5;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Lab5 {

    private myOptions myOptions = new myOptions();
    private Student students = new Student();
    private GraduateStudents graduateStudents = new GraduateStudents();

    public JFrame mainFrame;
    public JPanel searchPanel;

    public static int tester = 0;

    private JPanel controlPanel;

    public Lab5() {
        prepareGUI();
    }

    class EndingListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            System.exit(0);
        }
    }

    private void prepareGUI() {
        mainFrame = new JFrame("Java SWING Examples");
        mainFrame.setSize(900, 700);

        mainFrame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent windowEvent) {
                tester = 1;
                System.exit(0);
            }
        });
        controlPanel = new JPanel();
        controlPanel.setLayout(new FlowLayout());

        searchPanel = new JPanel();
        searchPanel.setLayout(new BorderLayout());

        // mainFrame.add(headerLabel);
        mainFrame.add(controlPanel);
        mainFrame.add(searchPanel, BorderLayout.SOUTH);
        // mainFrame.add(statusLabel);
        mainFrame.setVisible(true);
    }

    class studentListener implements ActionListener {

        private String userTypeProgram;
        private String userTypeLastName;
        private String userTypeYear;
        private String userTypeGrade;

        public void actionPerformed(ActionEvent e) {

            JTextField newProgram = new JTextField(50);
            JTextField newLastName = new JTextField(50);
            JTextField newYear = new JTextField(50);
            JTextField newGrade = new JTextField(50);
            JPanel panel4 = new JPanel();
            JPanel panel5 = new JPanel();
            JPanel panel6 = new JPanel();
            JPanel panel7 = new JPanel();
            JPanel panel8 = new JPanel();

            JLabel labelProgram = new JLabel("Enter Student Program: ");
            JLabel labelLastName = new JLabel("Enter Student LastName: ");
            JLabel labelYear = new JLabel("Enter Student Year: ");
            JLabel labelGrade = new JLabel("Enter Student Grade: ");

            FlowLayout bLayout = new FlowLayout();
            panel4.setLayout(bLayout);
            panel4.add(labelProgram);
            panel4.setBackground(Color.white);
            panel4.setSize(100, 100);
            panel4.add(newProgram);
            controlPanel.add(panel4);

            mainFrame.setVisible(true);

            panel5.setLayout(bLayout);
            panel5.add(labelLastName);
            panel5.setBackground(Color.white);
            panel5.setSize(100, 100);
            panel5.add(newLastName);
            controlPanel.add(panel5);

            mainFrame.setVisible(true);

            panel6.setLayout(bLayout);
            panel6.add(labelYear);
            panel6.setBackground(Color.white);
            panel6.setSize(100, 100);
            panel6.add(newYear);
            controlPanel.add(panel6);

            mainFrame.setVisible(true);

            panel7.setLayout(bLayout);
            panel7.add(labelGrade);
            panel7.setBackground(Color.white);
            panel7.setSize(100, 100);
            panel7.add(newGrade);
            controlPanel.add(panel7);

            mainFrame.setVisible(true);

            panel8.setLayout(bLayout);
            panel8.setBackground(Color.white);
            panel8.setSize(100, 100);
            JButton save = new JButton("Click here to Save Student Information");
            panel8.add(save);
            controlPanel.add(panel8);
            save.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    panel4.setVisible(false);
                    panel5.setVisible(false);
                    panel6.setVisible(false);
                    panel7.setVisible(false);
                    panel8.setVisible(false);
                    controlPanel.remove(panel4);
                    controlPanel.remove(panel5);
                    controlPanel.remove(panel6);
                    controlPanel.remove(panel7);
                    controlPanel.remove(panel8);
                    userTypeProgram = newProgram.getText();
                    userTypeLastName = newLastName.getText();
                    userTypeYear = newYear.getText();
                    userTypeGrade = newGrade.getText();
                    try {
                        myOptions.option1(students, userTypeProgram, userTypeLastName, userTypeGrade, userTypeYear);
                    } catch (Exception e1) {
                        e1.printStackTrace();
                    }
                }
            });

            students = new Student();

            mainFrame.setVisible(true);
        }
    }

    class graduateStudentListener implements ActionListener {

        private String userTypeProgram;
        private String userTypeLastName;
        private String userTypeYear;
        private String userTypeGrade;
        private String userTypeSupervisor;
        private String userTypePhD;
        private String userTypeUnderGraduateSchool;

        public void actionPerformed(ActionEvent e) {

            JTextField newProgram = new JTextField(50);
            JTextField newLastName = new JTextField(50);
            JTextField newYear = new JTextField(50);
            JTextField newGrade = new JTextField(50);
            JTextField newSupervisor = new JTextField(50);
            JTextField newPhD = new JTextField(50);
            JTextField newUnderGraduateSchool = new JTextField(50);
            JPanel panel4 = new JPanel();
            JPanel panel5 = new JPanel();
            JPanel panel6 = new JPanel();
            JPanel panel7 = new JPanel();
            JPanel panel8 = new JPanel();
            JPanel panel9 = new JPanel();
            JPanel panel10 = new JPanel();
            JPanel panel11 = new JPanel();

            JLabel labelProgram = new JLabel("Enter Graduate Student Program: ");
            JLabel labelLastName = new JLabel("Enter Graduate Student LastName: ");
            JLabel labelYear = new JLabel("Enter Graduate Student Year: ");
            JLabel labelGrade = new JLabel("Enter Graduate Student Grade: ");
            JLabel labelSupervisor = new JLabel("Enter Student Graduate Supervisor: ");
            JLabel labelPhD = new JLabel("Enter Graduate Student PhD: ");
            JLabel labelUnderGraduateSchool = new JLabel("Enter Student Graduate UnderGraduateSchool: ");

            FlowLayout bLayout = new FlowLayout();
            panel4.setLayout(bLayout);
            panel4.add(labelProgram);
            panel4.setBackground(Color.white);
            panel4.setSize(100, 100);
            panel4.add(newProgram);
            controlPanel.add(panel4);

            mainFrame.setVisible(true);

            panel5.setLayout(bLayout);
            panel5.add(labelLastName);
            panel5.setBackground(Color.white);
            panel5.setSize(100, 100);
            panel5.add(newLastName);
            controlPanel.add(panel5);

            mainFrame.setVisible(true);

            panel6.setLayout(bLayout);
            panel6.add(labelYear);
            panel6.setBackground(Color.white);
            panel6.setSize(100, 100);
            panel6.add(newYear);
            controlPanel.add(panel6);

            mainFrame.setVisible(true);

            panel7.setLayout(bLayout);
            panel7.add(labelGrade);
            panel7.setBackground(Color.white);
            panel7.setSize(100, 100);
            panel7.add(newGrade);
            controlPanel.add(panel7);

            mainFrame.setVisible(true);

            panel9.setLayout(bLayout);
            panel9.add(labelSupervisor);
            panel9.setBackground(Color.white);
            panel9.setSize(100, 100);
            panel9.add(newSupervisor);
            controlPanel.add(panel9);

            mainFrame.setVisible(true);

            panel10.setLayout(bLayout);
            panel10.add(labelPhD);
            panel10.setBackground(Color.white);
            panel10.setSize(100, 100);
            panel10.add(newPhD);
            controlPanel.add(panel10);

            mainFrame.setVisible(true);

            panel11.setLayout(bLayout);
            panel11.add(labelUnderGraduateSchool);
            panel11.setBackground(Color.white);
            panel11.setSize(100, 100);
            panel11.add(newUnderGraduateSchool);
            controlPanel.add(panel11);

            mainFrame.setVisible(true);

            panel8.setLayout(bLayout);
            panel8.setBackground(Color.white);
            panel8.setSize(100, 100);
            JButton save = new JButton("Click here to Save Student Information");
            panel8.add(save);
            controlPanel.add(panel8);
            save.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    panel4.setVisible(false);
                    panel5.setVisible(false);
                    panel6.setVisible(false);
                    panel7.setVisible(false);
                    panel8.setVisible(false);
                    panel9.setVisible(false);
                    panel10.setVisible(false);
                    panel11.setVisible(false);
                    controlPanel.remove(panel4);
                    controlPanel.remove(panel5);
                    controlPanel.remove(panel6);
                    controlPanel.remove(panel7);
                    controlPanel.remove(panel8);
                    controlPanel.remove(panel9);
                    controlPanel.remove(panel10);
                    controlPanel.remove(panel11);
                    userTypeProgram = newProgram.getText();
                    userTypeLastName = newLastName.getText();
                    userTypeYear = newYear.getText();
                    userTypeGrade = newGrade.getText();
                    userTypeSupervisor = newSupervisor.getText();
                    userTypePhD = newPhD.getText();
                    userTypeUnderGraduateSchool = newUnderGraduateSchool.getText();

                    try {
                        myOptions.option2(graduateStudents, userTypeProgram, userTypeLastName, userTypeGrade,
                                userTypeYear, userTypeSupervisor, userTypePhD, userTypeUnderGraduateSchool);
                    } catch (Exception e1) {
                        e1.printStackTrace();
                    }
                }
            });

            graduateStudents = new GraduateStudents();

            mainFrame.setVisible(true);
        }
    }

    class printAllListener extends Student implements ActionListener {

        private JTextArea memoDisplay = new JTextArea(10, 30);
        private JScrollPane scrolledText = new JScrollPane(memoDisplay);

        public void actionPerformed(ActionEvent e) {

            memoDisplay.setBackground(Color.WHITE);
            memoDisplay.setSize(850, 300);

            scrolledText.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

            searchPanel.add(scrolledText);
            mainFrame.add(searchPanel, BorderLayout.SOUTH);
            mainFrame.setVisible(true);
            memoDisplay.setVisible(true);
            scrolledText.setVisible(true);

            int i = 0;

            memoDisplay.setText("");

            if (myOptions.option3().size() == 0) {

                JOptionPane.showMessageDialog(null, "No student information has been entered yet.");

            }

            else {
                for (Student printer : myOptions.option3()) {

                    memoDisplay.append("Student " + (i + 1) + ":\n");
                    memoDisplay.append( printer.toString2());
                    if ( !(myOptions.option3().size()-1 == i) ) {
                        memoDisplay.append ( "\n" );
                    }

                    i = i + 1;

                }
            }

        }

    }

    class printAverageListener implements ActionListener {

        public void actionPerformed(ActionEvent e) {

            myOptions.option4(students);

        }

    }

    class fileInputListener implements ActionListener {

        public void actionPerformed(ActionEvent e) {

            JTextField fileField = new JTextField(50);
            JPanel panel12 = new JPanel();
            JPanel panel8 = new JPanel();
            JLabel newFileInput = new JLabel("Enter a file you wish to read form (ending in .txt): ");

            FlowLayout bLayout = new FlowLayout();
            panel12.setLayout(bLayout);
            panel12.add(newFileInput);
            panel12.setBackground(Color.white);
            panel12.setSize(100, 100);
            panel12.add(fileField);
            controlPanel.add(panel12);

            mainFrame.setVisible(true);

            JButton save = new JButton("Click here to read the input file.");
            panel8.add(save);
            controlPanel.add(panel8);
            save.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    panel8.setVisible(false);
                    panel12.setVisible(false);
                    controlPanel.remove(panel12);
                    controlPanel.remove(panel8);
                    try {
                        myOptions.option6(fileField.getText());
                    } catch (Exception e1) {
                        e1.printStackTrace();
                    }

                }

            });

            mainFrame.setVisible(true);

        }

    }

    class fileOutputListener implements ActionListener {

        public void actionPerformed(ActionEvent e) {

            JTextField fileField = new JTextField(50);
            JPanel panel12 = new JPanel();
            JPanel panel8 = new JPanel();
            JLabel newFileInput = new JLabel("Enter a file you wish to write to (ending in .txt): ");

            FlowLayout bLayout = new FlowLayout();
            panel12.setLayout(bLayout);
            panel12.add(newFileInput);
            panel12.setBackground(Color.white);
            panel12.setSize(100, 100);
            panel12.add(fileField);
            controlPanel.add(panel12);

            mainFrame.setVisible(true);

            JButton save = new JButton("Click here to write to the output file.");
            panel8.add(save);
            controlPanel.add(panel8);
            save.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    panel8.setVisible(false);
                    panel12.setVisible(false);
                    controlPanel.remove(panel12);
                    controlPanel.remove(panel8);
                    try {
                        myOptions.option7(fileField.getText());
                    } catch (Exception e1) {
                        e1.printStackTrace();
                    }

                }

            });

            mainFrame.setVisible(true);

        }

    }

    class hashSearchListener implements ActionListener {

        public void actionPerformed(ActionEvent e) {

            JTextField fileField2 = new JTextField(50);
            JTextField fileField3 = new JTextField(50);
            JTextField fileField4 = new JTextField(50);
            JPanel panel12 = new JPanel();
            JPanel panel13 = new JPanel();
            JPanel panel14 = new JPanel();
            JPanel panel8 = new JPanel();
            JLabel newFileInput2 = new JLabel("Please enter a program for the search request: ");
            JLabel newFileInput3 = new JLabel("Please enter a year for the search request: ");
            JLabel newFileInput4 = new JLabel("Please enter a last name for the search request: ");

            FlowLayout bLayout = new FlowLayout();
            panel12.setLayout(bLayout);
            panel12.add(newFileInput2);
            panel12.setBackground(Color.white);
            panel12.setSize(100, 100);
            panel12.add(fileField2);
            controlPanel.add(panel12);

            panel13.setLayout(bLayout);
            panel13.add(newFileInput3);
            panel13.setBackground(Color.white);
            panel13.setSize(100, 100);
            panel13.add(fileField3);
            controlPanel.add(panel13);

            panel14.setLayout(bLayout);
            panel14.add(newFileInput4);
            panel14.setBackground(Color.white);
            panel14.setSize(100, 100);
            panel14.add(fileField4);
            controlPanel.add(panel14);

            mainFrame.setVisible(true);

            JButton save = new JButton("Click here to search the Students.");
            panel8.add(save);
            controlPanel.add(panel8);
            save.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    panel8.setVisible(false);
                    panel12.setVisible(false);
                    panel13.setVisible(false);
                    panel14.setVisible(false);
                    controlPanel.remove(panel12);
                    controlPanel.remove(panel13);
                    controlPanel.remove(panel14);
                    controlPanel.remove(panel8);
                    try {
                        myOptions.option8( fileField4.getText() , fileField3.getText(), fileField2.getText() );
                    } catch (Exception e1) {
                        e1.printStackTrace();
                    }

                }

            });

            mainFrame.setVisible(true);

        }
    }

    private void showBorderLayoutDemo() {
        // headerLabel.setText("Layout in action: BorderLayout");

        JPanel panel3 = new JPanel();
        JPanel panel2 = new JPanel();

        panel3.setBackground(Color.darkGray);
        panel3.setSize(100, 100);
        panel2.setBackground(Color.darkGray);
        panel2.setSize(100, 100);
        FlowLayout fLayout = new FlowLayout();
        // BorderLayout layout = new BorderLayout();
        // fLayout.setHgap(10);
        // fLayout.setVgap(10);

        // Make panel and follow the flow layout
        panel3.setLayout(fLayout);
        panel2.setLayout(fLayout);
        panel2.setLayout(fLayout);

        JButton printAveragesButton = new JButton("Print average of student averages, as well as total number of students");
        panel2.add(printAveragesButton);
        printAveragesButton.addActionListener(new printAverageListener());

        JButton inputFile = new JButton("Read input file");
        panel2.add( inputFile );
        inputFile.addActionListener(new fileInputListener());

        JButton newStudent = new JButton("Enter info about a new Student");
        panel3.add(newStudent);
        newStudent.addActionListener(new studentListener());

        JButton newGraduateStudent = new JButton("Enter info about a new GraduateStudent");
        panel3.add(newGraduateStudent);
        newGraduateStudent.addActionListener(new graduateStudentListener());

        JButton newPrintAll = new JButton("Print out all student info");
        panel3.add(newPrintAll);
        newPrintAll.addActionListener(new printAllListener());

        JButton outputFile = new JButton ("File Data dump");
        panel2.add(outputFile);
        outputFile.addActionListener(new fileOutputListener());

        JButton hashSearch = new JButton ( "Lookup via a HashMap key" );
        panel2.add(hashSearch);
        hashSearch.addActionListener(new hashSearchListener());

        JButton endButton = new JButton("End program");
        panel3.add((endButton));
        endButton.addActionListener(new EndingListener());
        // panel.add(new JButton("South"),BorderLayout.SOUTH);
        // panel.add(new JButton("South"),BorderLayout.SOUTH);

        controlPanel.add(panel3);
        controlPanel.add(panel2);
        mainFrame.setVisible(true);
    }

    public static void main(String[] args) throws Exception {

        Lab5 swingLayoutDemo = new Lab5();
        swingLayoutDemo.showBorderLayoutDemo();

    }

}