import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
public class catalog extends JFrame {
    private JPanel contentPanel;
    private JLabel searchResultLabel;
    private JTextField searchField;
    public catalog() {
        setTitle("Car Catalog");
        setSize(700, 600);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        getContentPane().setBackground(Color.LIGHT_GRAY);
        setLayout(new BorderLayout());
        contentPanel = new JPanel();
        contentPanel.setLayout(new GridLayout(7, 1, 0, 10));
        searchField = new JTextField();
        searchField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                searchCars(searchField.getText());
            }
        });
        add(searchField, BorderLayout.NORTH);
        searchResultLabel = new JLabel();
        add(searchResultLabel, BorderLayout.SOUTH);
        addCar("C:\\Users\\aruza\\Desktop\\www\\java1.2.png", "HYUNDAI SONATA 2022", "<html>" +
                "<b>Город:</b> Алматы<br>" +
                "<b>Год выпуска:</b> 2022<br>" +
                "<b>Пробег:</b> 68 200 км<br>" +
                "<b>Состояние:</b> На ходу<br>" +
                "<b>Кузов:</b> Седан<br>" +
                "<b>Цвет кузова:</b> Черный<br>" +
                "<b>Двигатель:</b> 2.0 л / 150 л.с. / Бензин<br>" +
                "<b>Коробка передач:</b> 6-AT (автомат)<br>" +
                "<b>Привод:</b> 2WD (передний)<br>" +
                "<b>Руль:</b> Левый<br>" +
                "<b>Растаможен:</b> Да<br>" +
                "<b>Налог:</b> 14 569 ₸/год<br>" +
                "<b>Цена:</b> 11 990 000 тг" +
                "</html>");
        addCar("C:\\Users\\aruza\\Desktop\\www\\java2.png", "TOYOTA CAMRY 2022", "<html>" +
                "<b>Город:</b> Алматы<br>" +
                "<b>Год выпуска:</b> 2022<br>" +
                "<b>Пробег:</b> 68 200 км<br>" +
                "<b>Состояние:</b> На ходу<br>" +
                "<b>Кузов:</b> Седан<br>" +
                "<b>Цвет кузова:</b> Черный<br>" +
                "<b>Двигатель:</b> 2.0 л / 150 л.с. / Бензин<br>" +
                "<b>Коробка передач:</b> 6-AT (автомат)<br>" +
                "<b>Привод:</b> 2WD (передний)<br>" +
                "<b>Руль:</b> Левый<br>" +
                "<b>Растаможен:</b> Да<br>" +
                "<b>Налог:</b> 14 569 ₸/год<br>" +
                "<b>Цена:</b> 19 690 000  тг" +
                "</html>");
        addCar("C:\\Users\\aruza\\Desktop\\www\\java3.png", "MERCEDES-BENZ GLE 2021", "<html>" +
                "<b>Город:</b> Алматы<br>" +
                "<b>Год выпуска:</b> 2022<br>" +
                "<b>Пробег:</b> 68 200 км<br>" +
                "<b>Состояние:</b> На ходу<br>" +
                "<b>Кузов:</b> Седан<br>" +
                "<b>Цвет кузова:</b> Черный<br>" +
                "<b>Двигатель:</b> 2.0 л / 150 л.с. / Бензин<br>" +
                "<b>Коробка передач:</b> 6-AT (автомат)<br>" +
                "<b>Привод:</b> 2WD (передний)<br>" +
                "<b>Руль:</b> Левый<br>" +
                "<b>Растаможен:</b> Да<br>" +
                "<b>Налог:</b> 14 569 ₸/год<br>" +
                "<b>Цена:</b> 38 660 000 тг" +
                "</html>");
        addCar("C:\\Users\\aruza\\Desktop\\www\\java4.png", "LEXUS RX 2023", "<html>" +
                "<b>Город:</b> Алматы<br>" +
                "<b>Год выпуска:</b> 2022<br>" +
                "<b>Пробег:</b> 68 200 км<br>" +
                "<b>Состояние:</b> На ходу<br>" +
                "<b>Кузов:</b> Седан<br>" +
                "<b>Цвет кузова:</b> Черный<br>" +
                "<b>Двигатель:</b> 2.0 л / 150 л.с. / Бензин<br>" +
                "<b>Коробка передач:</b> 6-AT (автомат)<br>" +
                "<b>Привод:</b> 2WD (передний)<br>" +
                "<b>Руль:</b> Левый<br>" +
                "<b>Растаможен:</b> Да<br>" +
                "<b>Налог:</b> 25 561 ₸/год ₸/год<br>" +
                "<b>Цена:</b> 45 642 000 тг" +
                "</html>");
        addCar("C:\\Users\\aruza\\Desktop\\www\\java6.png", "BMW 3 СЕРИИ 2014", "<html>" +
                "<b>Город:</b> Алматы<br>" +
                "<b>Год выпуска:</b> 2022<br>" +
                "<b>Пробег:</b> 68 200 км<br>" +
                "<b>Состояние:</b> На ходу<br>" +
                "<b>Кузов:</b> Седан<br>" +
                "<b>Цвет кузова:</b> Черный<br>" +
                "<b>Двигатель:</b> 2.0 л / 150 л.с. / Бензин<br>" +
                "<b>Коробка передач:</b> 6-AT (автомат)<br>" +
                "<b>Привод:</b> 2WD (передний)<br>" +
                "<b>Руль:</b> Левый<br>" +
                "<b>Растаможен:</b> Да<br>" +
                "<b>Налог:</b> 25 561 ₸/год ₸/год<br>" +
                "<b>Цена:</b> 8 490 000 тг" +
                "</html>");
        addCar("C:\\Users\\aruza\\Desktop\\www\\java8.png", "KIA SELTOS 2023", "<html>" +
                "<b>Город:</b> Алматы<br>" +
                "<b>Год выпуска:</b> 2022<br>" +
                "<b>Пробег:</b> 68 200 км<br>" +
                "<b>Состояние:</b> На ходу<br>" +
                "<b>Кузов:</b> Седан<br>" +
                "<b>Цвет кузова:</b> Черный<br>" +
                "<b>Двигатель:</b> 2.0 л / 150 л.с. / Бензин<br>" +
                "<b>Коробка передач:</b> 6-AT (автомат)<br>" +
                "<b>Привод:</b> 2WD (передний)<br>" +
                "<b>Руль:</b> Левый<br>" +
                "<b>Растаможен:</b> Да<br>" +
                "<b>Налог:</b> 25 561 ₸/год ₸/год<br>" +
                "<b>Цена:</b> 14 990 000 тг" +
                "</html>");


        JScrollPane scrollPane = new JScrollPane(contentPanel);
        add(scrollPane, BorderLayout.CENTER);

        setLocationRelativeTo(null);
        setVisible(true);
    }
    private void addCar(String imagePath, String carName, String carInfo) {
        ImageIcon carIcon = new ImageIcon(imagePath);
        Image scaledCarImage = carIcon.getImage().getScaledInstance(350, 250, Image.SCALE_SMOOTH);
        ImageIcon scaledCarIcon = new ImageIcon(scaledCarImage);
        JLabel carLabel = new JLabel(scaledCarIcon);
        carLabel.setHorizontalAlignment(SwingConstants.CENTER);

        JLabel infoLabel = new JLabel(carInfo);
        infoLabel.setHorizontalAlignment(SwingConstants.LEFT);

        JPanel carPanel = new JPanel(new BorderLayout());
        carPanel.add(carLabel, BorderLayout.WEST);
        carPanel.add(infoLabel, BorderLayout.CENTER);
        contentPanel.add(carPanel);
    }

    private void searchCars(String searchText) {
        int count = 0;
        for (Component comp : contentPanel.getComponents()) {
            if (comp instanceof JPanel) {
                JPanel carPanel = (JPanel) comp;
                JLabel infoLabel = (JLabel) carPanel.getComponent(1);

                String infoText = infoLabel.getText();
                if (infoText.toLowerCase().contains(searchText.toLowerCase())) {
                    count++;
                    carPanel.setVisible(true);
                } else {
                    carPanel.setVisible(false);
                }
            }
        }
        if (count > 0) {
            searchResultLabel.setText("Found " + count + "cars");
        } else {
            searchResultLabel.setText("Not Found");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new catalog());
    }
}
