package librarymanagementsystem;
//day6
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class AdminManagement3 extends JFrame {
    private ArrayList<String[]> members = new ArrayList<>();
    private ArrayList<String[]> fictionBooks = new ArrayList<>();
    private ArrayList<String[]> nonFictionBooks = new ArrayList<>();

    public AdminManagement3() {
        setTitle("Admin Management");
        setSize(600, 580);
        setLocation(450, 100);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(null); // Absolute layout for a custom design

        // Set background color or gradient
        JPanel backgroundPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g;
                Color color1 = new Color(30, 87, 153); // Gradient start color
                Color color2 = new Color(41, 137, 216); // Gradient end color
                g2d.setPaint(new GradientPaint(0, 0, color1, getWidth(), getHeight(), color2));
                g2d.fillRect(0, 0, getWidth(), getHeight());
            }
        };
        backgroundPanel.setLayout(null); // Ensure components are freely placed
        setContentPane(backgroundPanel);

        // Add a title label with a unique style
        JLabel titleLabel = new JLabel("Library Admin Management", SwingConstants.CENTER);
        titleLabel.setBounds(50, 20, 500, 50);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setForeground(Color.WHITE);
        backgroundPanel.add(titleLabel);

        // Array of button labels
        String[] buttonLabels = {
            "View All Members",
            "Add Member",
            "Remove Member",
            "View Fiction Books",
            "View Non-Fiction Books",
            "Add Book",
            "Edit Book"
        };

        // Adding buttons with a unique design
        int yPosition = 100; // Initial Y position for buttons
        for (String label : buttonLabels) {
            JButton button = new JButton(label);
            button.setBounds(150, yPosition, 300, 40);
            button.setFont(new Font("Arial", Font.BOLD, 14));
            button.setForeground(Color.WHITE);
            button.setBackground(new Color(41, 128, 185)); // Button color
            button.setFocusPainted(false);
            button.setBorder(BorderFactory.createEmptyBorder());
            button.setCursor(new Cursor(Cursor.HAND_CURSOR));
            button.addActionListener(e -> handleButtonAction(label));
            backgroundPanel.add(button);
            yPosition += 60; // Increase Y position for the next button
        }

        // Sample data for members and books
        members.add(new String[]{"M1", "Mamun Hasan Shehab", "Premium"});
        members.add(new String[]{"M2", "Shoriful Islam", "Regular"});
        members.add(new String[]{"M3", "Amit Roy", "Regular"});
        members.add(new String[]{"M4", "Joy Goswami", "Premium"});
        members.add(new String[]{"M5", "Salehin Ornob", "Premium"});
        fictionBooks.add(new String[]{"F1", "The Great Gatsby", "10", "Romance"});
        fictionBooks.add(new String[]{"F2", "1984", "5", "Dystopian"});
        nonFictionBooks.add(new String[]{"NF1", "Sapiens", "8", "Historical"});
        nonFictionBooks.add(new String[]{"NF2", "Educated", "7", "Biography"});

        setVisible(true);
    }

    private void handleButtonAction(String action) {
        switch (action) {
            case "View All Members": handleViewMembers(); break;
            case "Add Member": handleAddMember(); break;
            case "Remove Member": handleRemoveMember(); break;
            case "View Fiction Books": handleViewBooks(fictionBooks, "Fiction Books"); break;
            case "View Non-Fiction Books": handleViewBooks(nonFictionBooks, "Non-Fiction Books"); break;
            case "Add Book": handleAddBook(); break;
            case "Edit Book": handleEditBook(); break;
        }
    }

    private void handleViewMembers() {
        displayData(members, new String[]{"Member ID", "Member Name", "Membership Type"}, "Members List");
    }

    private void handleAddMember() {
        JTextField memberID = new JTextField();
        JTextField memberName = new JTextField();
        JComboBox<String> membershipType = new JComboBox<>(new String[]{"Regular", "Premium"});
        Object[] inputs = {"Member ID:", memberID, "Member Name:", memberName, "Membership Type:", membershipType};
        if (JOptionPane.showConfirmDialog(this, inputs, "Add Member", JOptionPane.OK_CANCEL_OPTION) == JOptionPane.OK_OPTION) {
            members.add(new String[]{memberID.getText(), memberName.getText(), (String) membershipType.getSelectedItem()});
            JOptionPane.showMessageDialog(this, "Member added successfully!");
        }
    }

    private void handleRemoveMember() {
        String memberID = JOptionPane.showInputDialog(this, "Enter Member ID to remove:");
        if (members.removeIf(member -> member[0].equals(memberID))) {
            JOptionPane.showMessageDialog(this, "Member removed successfully!");
        } else {
            JOptionPane.showMessageDialog(this, "Member not found.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void handleViewBooks(ArrayList<String[]> books, String title) {
        displayData(books, new String[]{"Book ID", "Title", "Copies", "Category"}, title);
    }

    private void displayData(ArrayList<String[]> data, String[] columns, String title) {
        Object[][] tableData = new Object[data.size()][columns.length];
        for (int i = 0; i < data.size(); i++) {
            tableData[i] = data.get(i);
        }
        JTable table = new JTable(tableData, columns);
        JOptionPane.showMessageDialog(this, new JScrollPane(table), title, JOptionPane.INFORMATION_MESSAGE);
    }

    private void handleAddBook() {
        JTextField bookID = new JTextField();
        JTextField bookTitle = new JTextField();
        JTextField copies = new JTextField();
        String bookType = (String) JOptionPane.showInputDialog(this, "Select Book Type:", "Book Type",
                JOptionPane.QUESTION_MESSAGE, null, new String[]{"Fiction", "Non-Fiction"}, "Fiction");
        if (bookType != null) {
            String[] categories = bookType.equals("Fiction")
                    ? new String[]{"Stories", "Mystery", "Romance", "Fantasy"}
                    : new String[]{"Text Books", "Reference Books", "Religious Books", "Scientific Books", "Historical Books", "Biographies"};
            JComboBox<String> categorySelector = new JComboBox<>(categories);
            Object[] inputs = {"Book ID:", bookID, "Book Title:", bookTitle, "Copies:", copies, "Category:", categorySelector};
            if (JOptionPane.showConfirmDialog(this, inputs, "Add Book", JOptionPane.OK_CANCEL_OPTION) == JOptionPane.OK_OPTION) {
                ArrayList<String[]> targetList = bookType.equals("Fiction") ? fictionBooks : nonFictionBooks;
                targetList.add(new String[]{bookID.getText(), bookTitle.getText(), copies.getText(), (String) categorySelector.getSelectedItem()});
                JOptionPane.showMessageDialog(this, "Book added successfully!");
            }
        }
    }

    private void handleEditBook() {
        String bookType = (String) JOptionPane.showInputDialog(this, "Select Book Type to Edit:", "Book Type",
                JOptionPane.QUESTION_MESSAGE, null, new String[]{"Fiction", "Non-Fiction"}, "Fiction");
        if (bookType != null) {
            ArrayList<String[]> booksToEdit = bookType.equals("Fiction") ? fictionBooks : nonFictionBooks;
            String bookSelection = JOptionPane.showInputDialog(this, "Select a book to edit:\n" + getBookList(booksToEdit));
            try {
                int index = Integer.parseInt(bookSelection) - 1;
                if (index >= 0 && index < booksToEdit.size()) {
                    String[] selectedBook = booksToEdit.get(index);
                    JTextField bookID = new JTextField(selectedBook[0]);
                    JTextField bookTitle = new JTextField(selectedBook[1]);
                    JTextField copies = new JTextField(selectedBook[2]);
                    JComboBox<String> categorySelector = new JComboBox<>(getCategories(bookType));
                    categorySelector.setSelectedItem(selectedBook[3]);
                    Object[] inputs = {"Book ID:", bookID, "Book Title:", bookTitle, "Copies:", copies, "Category:", categorySelector};
                    if (JOptionPane.showConfirmDialog(this, inputs, "Edit Book", JOptionPane.OK_CANCEL_OPTION) == JOptionPane.OK_OPTION) {
                        booksToEdit.set(index, new String[]{bookID.getText(), bookTitle.getText(), copies.getText(), (String) categorySelector.getSelectedItem()});
                        JOptionPane.showMessageDialog(this, "Book details updated successfully!");
                    }
                } else {
                    JOptionPane.showMessageDialog(this, "Invalid selection.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(this, "Invalid input. Please enter a number.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private String getBookList(ArrayList<String[]> books) {
        StringBuilder bookList = new StringBuilder();
        for (int i = 0; i < books.size(); i++) {
            bookList.append((i + 1) + ". " + books.get(i)[1] + " (ID: " + books.get(i)[0] + ")\n");
        }
        return bookList.toString();
    }

    private String[] getCategories(String bookType) {
        return bookType.equals("Fiction")
                ? new String[]{"Stories", "Mystery", "Romance", "Fantasy"}
                : new String[]{"Text Books", "Reference Books", "Religious Books", "Scientific Books", "Historical Books", "Biographies"};
    }

    public static void main(String[] args) {
        new AdminManagement3();
    }
}
