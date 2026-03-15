import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Random;

public class Main {

    public static void main(String[] args) {

        JFrame frame = new JFrame("Extreme Weather Crop Risk Simulator");
        frame.setSize(500,450);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        ImageIcon bg = new ImageIcon("src/crop.jpg");
        JLabel background = new JLabel(bg);
        background.setLayout(new BorderLayout());

        frame.setContentPane(background);

        // Background theme
        frame.getContentPane().setBackground(new Color(220,255,220));

        // Title
        JLabel title = new JLabel("🌱 Extreme Weather Crop Risk Simulator", JLabel.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 22));
        title.setForeground(new Color(0,100,0));
        frame.add(title, BorderLayout.NORTH);

        // Input panel
        JPanel inputPanel = new JPanel(new GridLayout(4,2,10,10));
        inputPanel.setOpaque(false);
        inputPanel.setBorder(BorderFactory.createTitledBorder("Input Data"));
        inputPanel.setBackground(new Color(220,255,220));

        JTextField cropField = new JTextField();
        JTextField expectedRainField = new JTextField();
        JTextField actualRainField = new JTextField();

        inputPanel.add(new JLabel("Crop Name:"));
        inputPanel.add(cropField);

        inputPanel.add(new JLabel("Expected Rainfall (mm):"));
        inputPanel.add(expectedRainField);

        inputPanel.add(new JLabel("Actual Rainfall (mm):"));
        inputPanel.add(actualRainField);

        JButton calcButton = new JButton("Calculate Risk");

        // Button style
        calcButton.setBackground(new Color(34,139,34));
        calcButton.setForeground(Color.WHITE);
        calcButton.setFont(new Font("Arial", Font.BOLD, 14));

        inputPanel.add(new JLabel());
        inputPanel.add(calcButton);

        frame.add(inputPanel, BorderLayout.CENTER);

        // Result area
        JTextArea resultArea = new JTextArea();
        resultArea.setOpaque(false);
        resultArea.setEditable(false);
        resultArea.setBorder(BorderFactory.createTitledBorder("Simulation Result"));
        resultArea.setBackground(new Color(245,255,245));
        resultArea.setFont(new Font("Consolas", Font.BOLD, 14));

        frame.add(resultArea, BorderLayout.SOUTH);

        calcButton.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {

                try {

                    String cropName = cropField.getText();

                    double expectedRainfall = Double.parseDouble(expectedRainField.getText());
                    double actualRainfall = Double.parseDouble(actualRainField.getText());

                    int resistance;

                    switch (cropName.toLowerCase()) {
                        case "rice": resistance = 4; break;
                        case "wheat": resistance = 6; break;
                        case "cotton": resistance = 5; break;
                        case "millet": resistance = 8; break;
                        case "maize":
                        case "corn": resistance = 6; break;
                        default: resistance = 5;
                    }

                    Random rand = new Random();

                    double tempdev = rand.nextInt(5) + 1;
                    int wind = rand.nextInt(10) + 1;

                    Crop crop = new Crop(cropName, resistance);
                    Weather weather = new Weather(expectedRainfall, actualRainfall, tempdev, wind);

                    Riskcalculator calculator = new Riskcalculator();

                    double riskScore = calculator.calculateRisk(weather, crop);

                    String riskLevel;
                    String advice;

                    if(riskScore < 10){
                        riskLevel = "Low Risk";
                        advice = "Crop conditions are stable. Continue regular monitoring.";
                    }
                    else if(riskScore < 25){
                        riskLevel = "Moderate Risk";
                        advice = "Weather impact possible. Consider protective measures.";
                    }
                    else{
                        riskLevel = "High Risk";
                        advice = "High probability of crop damage. Immediate action recommended.";
                    }

                    String result =
                            "Crop Name            : " + cropName + "\n" +
                                    "Temperature Deviation: " + tempdev + " °C\n" +
                                    "Wind Speed Index     : " + wind + "\n" +
                                    "Rainfall Deviation   : " + weather.rainfalldeviation() + "\n\n" +
                                    "Risk Score           : " + riskScore + "\n" +
                                    "Risk Level           : " + riskLevel + "\n\n" +
                                    "Advice               : " + advice;

                    resultArea.setText(result);

                } catch(Exception ex) {
                    JOptionPane.showMessageDialog(frame,"Please enter valid numbers.");
                }

            }
        });

        frame.setVisible(true);
    }
}