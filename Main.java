import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.io.IOException;
import java.net.URI;
import java.io.File;

public class Main extends JFrame implements ActionListener {
    // Компоненты для третьего окна
    JFrame mainFrame;
    JPanel menuPanel;
    JLabel pickUpLocationLabel, dropOffLocationLabel, pickUpDateLabel, pickUpTimeLabel, dropOffDateLabel, dropOffTimeLabel;
    JTextField pickUpLocationField, dropOffLocationField, pickUpDateField, pickUpTimeField, dropOffDateField, dropOffTimeField;
    JButton searchBtn;

    // Остальные компоненты
    JLabel loginLabel, passwordLabel, imageLabel;
    JTextField loginField;
    JPasswordField passwordField;
    JButton loginBtn, registerBtn;

    Connection con;
    Statement stmt;
    ResultSet rs;

    private static final String DB_URL = "jdbc:postgresql://localhost:5432/testjava";
    private static final String USER = "postgres";
    private static final String PASSWORD = "ineed21u";

    Main() {
        setTitle("Login");
        setSize(400, 300); // Уменьшил размер окна
        setLayout(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Загрузка изображения
        ImageIcon imageIcon = new ImageIcon("C:\\Users\\aruza\\Downloads\\test1.png");
        // Уменьшение размера изображения
        Image image = imageIcon.getImage().getScaledInstance(150, 150, Image.SCALE_SMOOTH);
        // Создание нового ImageIcon с уменьшенным изображением
        ImageIcon scaledImageIcon = new ImageIcon(image);

        imageLabel = new JLabel(scaledImageIcon);
        imageLabel.setBounds(0, 50, 150, 150);
        add(imageLabel);

        loginLabel = new JLabel("Login:");
        loginLabel.setBounds(180, 50, 100, 20);
        add(loginLabel);

        loginField = new JTextField();
        loginField.setBounds(180, 75, 150, 20);
        add(loginField);

        passwordLabel = new JLabel("Password:");
        passwordLabel.setBounds(180, 100, 100, 20);
        add(passwordLabel);

        passwordField = new JPasswordField();
        passwordField.setBounds(180, 125, 150, 20);
        add(passwordField);

        loginBtn = new JButton("Login");
        loginBtn.setBounds(180, 160, 100, 30);
        loginBtn.addActionListener(this);
        add(loginBtn);

        registerBtn = new JButton("Register");
        registerBtn.setBounds(180, 200, 100, 30); // Переместил кнопку регистрации под кнопку логин
        registerBtn.addActionListener(this);
        add(registerBtn);

        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == loginBtn) {
            String login = loginField.getText();
            String password = new String(passwordField.getPassword());
            if (validateUser(login, password)) {
                openMainApplication();
            } else {
                JOptionPane.showMessageDialog(this, "Invalid login or password");
            }
        } else if (e.getSource() == registerBtn) {
            openRegistrationWindow();
        } else if (e.getSource() == searchBtn) {
            ResultSet resultSet = searchCars();
            if (resultSet != null) {
                displayAvailableCars(resultSet);
            } else {
                JOptionPane.showMessageDialog(mainFrame, "Error searching for cars");
            }
        }
    }

    private boolean validateUser(String login, String password) {
        try {
            Class.forName("org.postgresql.Driver");
            con = DriverManager.getConnection(DB_URL, USER, PASSWORD);
            stmt = con.createStatement();
            String query = "SELECT * FROM users WHERE login='" + login + "' AND password='" + password + "'";
            rs = stmt.executeQuery(query);
            return rs.next();
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }

    private ResultSet searchCars() {
        try {
            String query = "SELECT * FROM carrental WHERE availability = true";
            return stmt.executeQuery(query);
        } catch (SQLException ex) {
            ex.printStackTrace();
            return null;
        }
    }

    private void displayAvailableCars(ResultSet resultSet) {
        JFrame resultFrame = new JFrame("Available Cars");
        DefaultTableModel tableModel = new DefaultTableModel();
        JTable table = new JTable(tableModel);

        tableModel.addColumn("Car Name");
        tableModel.addColumn("Year");
        tableModel.addColumn("Color");
        tableModel.addColumn("Price per Day");

        try {
            while (resultSet.next()) {
                String carName = resultSet.getString("car_name");
                int year = resultSet.getInt("year");
                String color = resultSet.getString("color");
                double pricePerDay = resultSet.getDouble("price_per_day");
                tableModel.addRow(new Object[]{carName, year, color, pricePerDay});
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        JScrollPane scrollPane = new JScrollPane(table);
        resultFrame.add(scrollPane);
        resultFrame.setSize(600, 400);
        resultFrame.setVisible(true);
    }

    private void openMainApplication() {
        // После успешного входа пользователя
        // Создаем новое окно "Airline Management System"
        mainFrame = new JFrame("Car Rental");
        mainFrame.setLayout(null);
        mainFrame.setSize(500, 500);
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Левая часть окна с меню
        menuPanel = new JPanel();
        menuPanel.setBounds(0, 0, 200, 600);
        menuPanel.setBackground(Color.GRAY);
        menuPanel.setLayout(null);
        mainFrame.add(menuPanel);

        JLabel menuLabel = new JLabel("Menu");
        menuLabel.setForeground(Color.WHITE);
        menuLabel.setBounds(50, 20, 100, 20);
        menuPanel.add(menuLabel);

        JButton reviewsBtn = new JButton("Review");
        reviewsBtn.setBounds(20, 50, 150, 30);
        reviewsBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                openReviewsWindow(); // Вызываем метод для открытия окна отзывов
            }
        });
        menuPanel.add(reviewsBtn);

        JButton catalogBtn = new JButton("Catalog");
        catalogBtn.setBounds(20, 100, 150, 30);
        catalogBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                openCatalog();
            }
        });
        menuPanel.add(catalogBtn);

        JButton aboutUsBtn = new JButton("About Us");
        aboutUsBtn.setBounds(20, 150, 150, 30);
        aboutUsBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                openAboutUsWindow();
            }
        });
        menuPanel.add(aboutUsBtn);

        // Правая часть окна с формой и кнопкой поиска
        JLabel searchLabel = new JLabel("Поиск");
        searchLabel.setBounds(250, 20, 100, 20);
        mainFrame.add(searchLabel);

        pickUpLocationLabel = new JLabel("Pick Up place :");
        pickUpLocationLabel.setBounds(250, 50, 150, 20);
        mainFrame.add(pickUpLocationLabel);

        pickUpLocationField = new JTextField();
        pickUpLocationField.setBounds(250, 75, 150, 20);
        mainFrame.add(pickUpLocationField);

        dropOffLocationLabel = new JLabel("Drop off place:");
        dropOffLocationLabel.setBounds(250, 100, 150, 20);
        mainFrame.add(dropOffLocationLabel);

        dropOffLocationField = new JTextField();
        dropOffLocationField.setBounds(250, 125, 150, 20);
        mainFrame.add(dropOffLocationField);

        pickUpDateLabel = new JLabel("Pick up date");
        pickUpDateLabel.setBounds(250, 150, 150, 20);
        mainFrame.add(pickUpDateLabel);

        pickUpDateField = new JTextField();
        pickUpDateField.setBounds(250, 175, 150, 20);
        mainFrame.add(pickUpDateField);

        pickUpTimeLabel = new JLabel("Pick up time:");
        pickUpTimeLabel.setBounds(250, 200, 150, 20);
        mainFrame.add(pickUpTimeLabel);

        pickUpTimeField = new JTextField();
        pickUpTimeField.setBounds(250, 225, 150, 20);
        mainFrame.add(pickUpTimeField);

        dropOffDateLabel = new JLabel("Drop off date:");
        dropOffDateLabel.setBounds(250, 250, 150, 20);
        mainFrame.add(dropOffDateLabel);

        dropOffDateField = new JTextField();
        dropOffDateField.setBounds(250, 275, 150, 20);
        mainFrame.add(dropOffDateField);

        dropOffTimeLabel = new JLabel("Drop off time:");
        dropOffTimeLabel.setBounds(250, 300, 150, 20);
        mainFrame.add(dropOffTimeLabel);

        dropOffTimeField = new JTextField();
        dropOffTimeField.setBounds(250, 325, 150, 20);
        mainFrame.add(dropOffTimeField);

        searchBtn = new JButton("Search");
        searchBtn.setBounds(250, 360, 100, 30);
        searchBtn.addActionListener(this);
        mainFrame.add(searchBtn);

        mainFrame.setVisible(true);
    }


    private void openRegistrationWindow() {
        JFrame registrationFrame = new JFrame("Registration");
        registrationFrame.setSize(400, 300);
        registrationFrame.setLayout(null);
        registrationFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JLabel titleLabel = new JLabel("Register to rent a car"); // Добавил заголовок
        titleLabel.setBounds(50, 20, 300, 20);
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER); // Выравнивание по центру
        registrationFrame.add(titleLabel);

        JLabel nameLabel = new JLabel("Name:");
        nameLabel.setBounds(50, 50, 60, 20);
        registrationFrame.add(nameLabel);

        JTextField nameField = new JTextField();
        nameField.setBounds(160, 50, 150, 20);
        registrationFrame.add(nameField);

        JLabel surnameLabel = new JLabel("Surname:");
        surnameLabel.setBounds(50, 80, 60, 20);
        registrationFrame.add(surnameLabel);

        JTextField surnameField = new JTextField();
        surnameField.setBounds(160, 80, 150, 20);
        registrationFrame.add(surnameField);

        JLabel emailLabel = new JLabel("Email:");
        emailLabel.setBounds(50, 110, 60, 20);
        registrationFrame.add(emailLabel);

        JTextField emailField = new JTextField();
        emailField.setBounds(160, 110, 150, 20);
        registrationFrame.add(emailField);

        JLabel registerLoginLabel = new JLabel("Login:");
        registerLoginLabel.setBounds(50, 140, 60, 20);
        registrationFrame.add(registerLoginLabel);

        JTextField registerLoginField = new JTextField();
        registerLoginField.setBounds(160, 140, 150, 20);
        registrationFrame.add(registerLoginField);

        JLabel registerPasswordLabel = new JLabel("Password:");
        registerPasswordLabel.setBounds(50, 170, 60, 20);
        registrationFrame.add(registerPasswordLabel);

        JTextField registerPasswordField = new JTextField();
        registerPasswordField.setBounds(160, 170, 150, 20);
        registrationFrame.add(registerPasswordField);

        JButton registerButton = new JButton("Register");
        registerButton.setBounds(150, 220, 100, 30); // Центрирование по горизонтали
        registerButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    Class.forName("org.postgresql.Driver");
                    con = DriverManager.getConnection(DB_URL, USER, PASSWORD);
                    String query = "INSERT INTO users (name, surname, email, login, password) VALUES (?, ?, ?, ?, ?)";
                    PreparedStatement preparedStatement = con.prepareStatement(query);
                    preparedStatement.setString(1, nameField.getText());
                    preparedStatement.setString(2, surnameField.getText());
                    preparedStatement.setString(3, emailField.getText());
                    preparedStatement.setString(4, registerLoginField.getText());
                    preparedStatement.setString(5, registerPasswordField.getText());
                    preparedStatement.executeUpdate();
                    JOptionPane.showMessageDialog(null, "Registration successful!");
                    registrationFrame.dispose();
                } catch (Exception ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(null, "Error: Registration failed!");
                }
            }
        });
        registrationFrame.add(registerButton);

        registrationFrame.setLocationRelativeTo(null); // Центрирование по центру экрана
        registrationFrame.setVisible(true);
    }

    private void openAboutUsWindow() {
        SwingUtilities.invokeLater(() -> new AboutUsWindow());
    }

    private void openReviewsWindow() {
        SwingUtilities.invokeLater(ReviewsWindow::new);
    }


    private void openCatalog() {
        SwingUtilities.invokeLater(() -> new catalog());
    }


    public static void main(String[] args) {
        new Main();
    }
}
