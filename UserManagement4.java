package librarymanagementsystem;
//day9 
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class UserManagement4 extends JFrame {
    private ArrayList<String[]> fictionBooks = new ArrayList<>();
    private ArrayList<String[]> nonFictionBooks = new ArrayList<>();

    public UserManagement4() {
        setTitle("User Management");
        setSize(500, 400);
        setLocation(500, 200);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        // Set a custom background panel with gradient
        JPanel backgroundPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g;
                Color color1 = new Color(58, 123, 213); // Gradient start color
                Color color2 = new Color(0, 210, 255);  // Gradient end color
                g2d.setPaint(new GradientPaint(0, 0, color1, getWidth(), getHeight(), color2));
                g2d.fillRect(0, 0, getWidth(), getHeight());
            }
        };
        backgroundPanel.setLayout(null); // Use absolute layout for free placement
        setContentPane(backgroundPanel);

        // Add a title label
        JLabel titleLabel = new JLabel("Library User Management", SwingConstants.CENTER);
        titleLabel.setBounds(50, 20, 400, 40);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 22));
        titleLabel.setForeground(Color.WHITE);
        backgroundPanel.add(titleLabel);

        // Add buttons with unique styling
        JButton borrowBookButton = createStyledButton("Borrow Book", 100);
        JButton returnBookButton = createStyledButton("Return Book", 180);
        JButton viewBooksButton = createStyledButton("View Books", 260);

        borrowBookButton.addActionListener(e -> showBookOptions("Borrow"));
        returnBookButton.addActionListener(e -> showBookOptions("Return"));
        viewBooksButton.addActionListener(e -> showBookOptions("View"));

        backgroundPanel.add(borrowBookButton);
        backgroundPanel.add(returnBookButton);
        backgroundPanel.add(viewBooksButton);

        // Sample book data
        fictionBooks.add(new String[]{"F1", "The Great Gatsby", "10"});
        fictionBooks.add(new String[]{"F2", "1984", "5"});
        nonFictionBooks.add(new String[]{"NF1", "Sapiens", "8"});
        nonFictionBooks.add(new String[]{"NF2", "Educated", "7"});

        setVisible(true);
    }

    // Method to create styled buttons
    private JButton createStyledButton(String text, int yPosition) {
        JButton button = new JButton(text);
        button.setBounds(100, yPosition, 300, 50);
        button.setFont(new Font("Arial", Font.BOLD, 16));
        button.setForeground(Color.WHITE);
        button.setBackground(new Color(41, 128, 185)); // Button background color
        button.setFocusPainted(false);
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        button.setBorder(BorderFactory.createEmptyBorder());
        return button;
    }

    private void showBookOptions(String action) {
        String[] options = {"Fiction", "Non-Fiction"};
        String choice = (String) JOptionPane.showInputDialog(this, "Select Book Type", action + " Book", JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
        if (choice != null) {
            handleBookAction(action, choice.equals("Fiction") ? fictionBooks : nonFictionBooks);
        }
    }

    private void handleBookAction(String action, ArrayList<String[]> books) {
        String bookID = JOptionPane.showInputDialog(this, "Enter Book ID to " + action.toLowerCase() + ":");
        for (String[] book : books) {
            if (book[0].equals(bookID)) {
                int copies = Integer.parseInt(book[2]);
                if (action.equals("Borrow") && copies > 0) {
                    book[2] = String.valueOf(copies - 1);
                    JOptionPane.showMessageDialog(this, "Book borrowed successfully!");
                } else if (action.equals("Return")) {
                    book[2] = String.valueOf(copies + 1);
                    JOptionPane.showMessageDialog(this, "Book returned successfully!");
                } else if (action.equals("View")) {
                    JOptionPane.showMessageDialog(this, "Book Title: " + book[1] + "\nAvailable Copies: " + book[2]);
                } else {
                    JOptionPane.showMessageDialog(this, "No copies available.", "Error", JOptionPane.ERROR_MESSAGE);
                }
                return;
            }
        }
        JOptionPane.showMessageDialog(this, "Book not found.", "Error", JOptionPane.ERROR_MESSAGE);
    }

    public static void main(String[] args) {
        new UserManagement4();
    }
}
