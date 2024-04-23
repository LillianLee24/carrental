import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;

public class ReviewsWindow extends JFrame {
    private JTextArea reviewsTextArea;
    private JTextField usernameField;
    private JTextField reviewField;

    public ReviewsWindow() {
        setTitle("Review");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null); // Центрируем окно

        // Панель для отзывов
        JPanel reviewsPanel = new JPanel(new BorderLayout());

        // Создаем текстовую область для отображения отзывов
        reviewsTextArea = new JTextArea(20, 40);
        reviewsTextArea.setEditable(false); // Запрещаем редактирование
        JScrollPane scrollPane = new JScrollPane(reviewsTextArea);
        reviewsPanel.add(scrollPane, BorderLayout.CENTER);

        // Панель для добавления нового отзыва
        JPanel addReviewPanel = new JPanel(new BorderLayout());
        JLabel usernameLabel = new JLabel("Your nick name:");
        usernameField = new JTextField(10);
        JLabel reviewLabel = new JLabel("Review:");
        reviewField = new JTextField(30);
        JButton submitButton = new JButton("Send review");
        submitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                addReview();
            }
        });
        addReviewPanel.add(usernameLabel, BorderLayout.WEST);
        addReviewPanel.add(usernameField, BorderLayout.CENTER);
        addReviewPanel.add(reviewLabel, BorderLayout.SOUTH);
        addReviewPanel.add(reviewField, BorderLayout.EAST);
        addReviewPanel.add(submitButton, BorderLayout.PAGE_END);

        // Добавляем панели на основное окно
        add(reviewsPanel, BorderLayout.CENTER);
        add(addReviewPanel, BorderLayout.SOUTH);

        // Загружаем отзывы из файла при запуске
        loadReviewsFromFile();

        setVisible(true);
    }

    private void loadReviewsFromFile() {
        try {
            BufferedReader reader = new BufferedReader(new FileReader("reviews.txt"));
            String line;
            while ((line = reader.readLine()) != null) {
                reviewsTextArea.append(line + "\n");
            }
            reader.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    private void addReview() {
        String username = usernameField.getText();
        String reviewText = reviewField.getText();
        if (!username.isEmpty() && !reviewText.isEmpty()) {
            String review = username + ": " + reviewText + "\n";
            reviewsTextArea.append(review);
            saveReviewToFile(review);
            usernameField.setText("");
            reviewField.setText("");
        } else {
            JOptionPane.showMessageDialog(this, "Please enter your name and feedback.");
        }
    }

    private void saveReviewToFile(String review) {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter("reviews.txt", true)); // true - дописывает в конец файла
            writer.write(review);
            writer.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new ReviewsWindow());
    }
}

