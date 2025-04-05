package librarymanagementsystem;
// day3
import javax.swing.*;
import java.awt.*;
import java.util.regex.Pattern;

public class Security2 {

    public Security2() {
        JFrame frame = new JFrame("Library Management Login");
        frame.setSize(600, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null);
        frame.setLocation(400, 80);

        JPanel gradientPanel = new JPanel() {
            protected void paintComponent(Graphics g) {
                Graphics2D g2d = (Graphics2D) g;
                Color color1 = new Color(0, 51, 102);
                Color color2 = new Color(255, 51, 153);
                GradientPaint gradient = new GradientPaint(0, 0, color1, getWidth(), getHeight(), color2);
                g2d.setPaint(gradient);
                g2d.fillRect(0, 0, getWidth(), getHeight());
            }
        };
        gradientPanel.setBounds(0, 0, 600, 400);
        gradientPanel.setLayout(null);

        JLabel titleLabel = new JLabel("Choose Login Type");
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 32));
        titleLabel.setBounds(140, 50, 300, 40);
        gradientPanel.add(titleLabel);

        JButton adminButton = new JButton("Admin Login");
        adminButton.setBounds(220, 130, 130, 40);
        adminButton.setBackground(Color.WHITE);
        gradientPanel.add(adminButton);

        JButton userButton = new JButton("User Login");
        userButton.setBounds(220, 200, 130, 40);
        userButton.setBackground(Color.WHITE);
        gradientPanel.add(userButton);

        // New Register button
        JButton registerButton = new JButton("Register");
        registerButton.setBounds(220, 270, 130, 40);
        registerButton.setBackground(Color.WHITE);
        gradientPanel.add(registerButton);

        adminButton.addActionListener(e -> {
            frame.dispose();
            showLoginPage("Admin");
        });

        userButton.addActionListener(e -> {
            frame.dispose();
            showLoginPage("User");
        });

        // Register button action
        registerButton.addActionListener(e -> {
            frame.dispose();
            showRegistrationPage();
        });

        frame.add(gradientPanel);
        frame.setVisible(true);
    }

    private void showLoginPage(String role) {
        JFrame loginFrame = new JFrame(role + " Login");
        loginFrame.setSize(600, 400);
        loginFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        loginFrame.setLayout(null);
        loginFrame.setLocation(400, 80);

        JPanel gradientPanel = new JPanel() {
            protected void paintComponent(Graphics g) {
                Graphics2D g2d = (Graphics2D) g;
                Color color1 = new Color(0, 51, 102);
                Color color2 = new Color(255, 51, 153);
                GradientPaint gradient = new GradientPaint(0, 0, color1, getWidth(), getHeight(), color2);
                g2d.setPaint(gradient);
                g2d.fillRect(0, 0, getWidth(), getHeight());
            }
        };
        gradientPanel.setBounds(0, 0, 600, 400);
        gradientPanel.setLayout(null);

        JLabel loginLabel = new JLabel(role + " Login");
        loginLabel.setForeground(Color.WHITE);
        loginLabel.setFont(new Font("Arial", Font.BOLD, 32));
        loginLabel.setBounds(220, 40, 200, 40);
        gradientPanel.add(loginLabel);

        JLabel userLabel = new JLabel(role.equals("Admin") ? "Admin Name:" : "User Name:");
        userLabel.setForeground(Color.WHITE);
        userLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        userLabel.setBounds(120, 120, 120, 25);
        gradientPanel.add(userLabel);

        JTextField usernameField = new JTextField();
        usernameField.setBounds(250, 120, 200, 30);
        gradientPanel.add(usernameField);

        JLabel passLabel = new JLabel("Password:");
        passLabel.setForeground(Color.WHITE);
        passLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        passLabel.setBounds(120, 180, 120, 25);
        gradientPanel.add(passLabel);

        JPasswordField passwordField = new JPasswordField();
        passwordField.setBounds(250, 180, 200, 30);
        gradientPanel.add(passwordField);

        JButton loginButton = new JButton("Login");
        loginButton.setBounds(250, 250, 100, 30);
        loginButton.setBackground(Color.WHITE);
        gradientPanel.add(loginButton);

        loginButton.addActionListener(e -> {
            try {
                String username = usernameField.getText();
                String password = new String(passwordField.getPassword());

                if (username.isEmpty() || password.isEmpty()) {
                    throw new IllegalArgumentException("Fields cannot be empty.");
                }

                if (role.equals("Admin")) {
                    if (username.equals("admin") && password.equals("12345")) {
                        JOptionPane.showMessageDialog(loginFrame, "Admin Login Successful!", "Success", JOptionPane.INFORMATION_MESSAGE);
                        new AdminManagement3();
                        loginFrame.dispose();
                    } else {
                        throw new Exception("Invalid Admin Credentials.");
                    }
                } else {
                    if (username.equals("user") && password.equals("12345")) {
                        JOptionPane.showMessageDialog(loginFrame, "User Login Successful!", "Success", JOptionPane.INFORMATION_MESSAGE);
                        new UserManagement4();
                        loginFrame.dispose();
                    } else {
                        throw new Exception("Invalid User Credentials.");
                    }
                }
            } catch (IllegalArgumentException ex) {
                JOptionPane.showMessageDialog(loginFrame, ex.getMessage(), "Input Error", JOptionPane.ERROR_MESSAGE);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(loginFrame, ex.getMessage(), "Login Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        loginFrame.add(gradientPanel);
        loginFrame.setVisible(true);
    }

    private void showRegistrationPage() {
        JFrame registrationFrame = new JFrame("User Registration");
        registrationFrame.setSize(600, 500); // Increased height for additional fields
        registrationFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        registrationFrame.setLayout(null);
        registrationFrame.setLocation(400, 80);

        JPanel gradientPanel = new JPanel() {
            protected void paintComponent(Graphics g) {
                Graphics2D g2d = (Graphics2D) g;
                Color color1 = new Color(0, 51, 102);
                Color color2 = new Color(255, 51, 153);
                GradientPaint gradient = new GradientPaint(0, 0, color1, getWidth(), getHeight(), color2);
                g2d.setPaint(gradient);
                g2d.fillRect(0, 0, getWidth(), getHeight());
            }
        };
        gradientPanel.setBounds(0, 0, 600, 500);
        gradientPanel.setLayout(null);

        JLabel registrationLabel = new JLabel("User Registration");
        registrationLabel.setForeground(Color.WHITE);
        registrationLabel.setFont(new Font("Arial", Font.BOLD, 32));
        registrationLabel.setBounds(180, 40, 300, 40);
        gradientPanel.add(registrationLabel);

        JLabel userLabel = new JLabel("Username:");
        userLabel.setForeground(Color.WHITE);
        userLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        userLabel.setBounds(120, 100, 120, 25);
        gradientPanel.add(userLabel);

        JTextField userField = new JTextField();
        userField.setBounds(250, 100, 200, 30);
        gradientPanel.add(userField);

        JLabel emailLabel = new JLabel("Email:");
        emailLabel.setForeground(Color.WHITE);
        emailLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        emailLabel.setBounds(120, 150, 120, 25);
        gradientPanel.add(emailLabel);

        JTextField emailField = new JTextField();
        emailField.setBounds(250, 150, 200, 30);
        gradientPanel.add(emailField);

        JLabel passLabel = new JLabel("Password:");
        passLabel.setForeground(Color.WHITE);
        passLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        passLabel.setBounds(120, 200, 120, 25);
        gradientPanel.add(passLabel);

        JPasswordField passField = new JPasswordField();
        passField.setBounds(250, 200, 200, 30);
        gradientPanel.add(passField);

        JLabel confirmPassLabel = new JLabel("Confirm Password:");
        confirmPassLabel.setForeground(Color.WHITE);
        confirmPassLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        confirmPassLabel.setBounds(110, 250, 150, 25);
        gradientPanel.add(confirmPassLabel);

        JPasswordField confirmPassField = new JPasswordField();
        confirmPassField.setBounds(250, 250, 200, 30);
        gradientPanel.add(confirmPassField);

        JButton registerButton = new JButton("Register");
        registerButton.setBounds(250, 310, 100, 30);
        registerButton.setBackground(Color.WHITE);
        gradientPanel.add(registerButton);

        registerButton.addActionListener(e -> {
            String username = userField.getText();
            String email = emailField.getText();
            String password = new String(passField.getPassword());
            String confirmPassword = new String(confirmPassField.getPassword());

            // Email validation regex
            String emailRegex = "^[A-Za-z0-9+_.-]+@(.+)$";
            boolean isEmailValid = Pattern.matches(emailRegex, email);

            if (username.isEmpty() || email.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()) {
                JOptionPane.showMessageDialog(registrationFrame, "Fields cannot be empty.", "Input Error", JOptionPane.ERROR_MESSAGE);
            } else if (!isEmailValid) {
                JOptionPane.showMessageDialog(registrationFrame, "Invalid email format.", "Input Error", JOptionPane.ERROR_MESSAGE);
            } else if (!password.equals(confirmPassword)) {
                JOptionPane.showMessageDialog(registrationFrame, "Passwords do not match.", "Input Error", JOptionPane.ERROR_MESSAGE);
            } else {
                // Registration logic (e.g., save to database or a file)
                JOptionPane.showMessageDialog(registrationFrame, "Registration Successful!", "Success", JOptionPane.INFORMATION_MESSAGE);
                registrationFrame.dispose();
                new Security2(); // Return to login screen
            }
        });

        registrationFrame.add(gradientPanel);
        registrationFrame.setVisible(true);
    }

    public static void main(String[] args) {
        new Security2();
    }
}
