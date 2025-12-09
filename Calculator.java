import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Calculator extends JFrame implements ActionListener {
    
    private JTextField display;    // Display field
    private String currentInput = ""; 
    private double firstOperand = 0;
    private String operator = "";

    public Calculator() {
        // Frame settings
        setTitle("GUI Calculator");
        setSize(400, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Display TextField
        display = new JTextField();
        display.setFont(new Font("Arial", Font.BOLD, 28));
        display.setHorizontalAlignment(SwingConstants.RIGHT);
        display.setEditable(false);
        add(display, BorderLayout.NORTH);

        // Panel for buttons (Grid Layout)
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(5, 4, 5, 5));

        // List of buttons
        String[] buttons = {
            "7", "8", "9", "/",
            "4", "5", "6", "*",
            "1", "2", "3", "-",
            "0", ".", "=", "+",
            "C"
        };

        // Add buttons
        for (String text : buttons) {
            JButton btn = new JButton(text);
            btn.setFont(new Font("Arial", Font.BOLD, 24));
            btn.addActionListener(this);
            panel.add(btn);
        }

        add(panel, BorderLayout.CENTER);
        
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String input = e.getActionCommand();

        // Clear button
        if (input.equals("C")) {
            currentInput = "";
            firstOperand = 0;
            operator = "";
            display.setText("");
            return;
        }

        // Operator buttons
        if (input.equals("+") || input.equals("-") || input.equals("*") || input.equals("/")) {
            if (!currentInput.isEmpty()) {
                firstOperand = Double.parseDouble(currentInput);
                operator = input;

                // Show step: "7 +"
                display.setText(currentInput + " " + operator);

                currentInput = "";
            }
            return;
        }

        // Equals button
        if (input.equals("=")) {
            if (!currentInput.isEmpty() && !operator.isEmpty()) {
                double secondOperand = Double.parseDouble(currentInput);
                double result = 0;

                try {
                    switch (operator) {
                        case "+": result = firstOperand + secondOperand; break;
                        case "-": result = firstOperand - secondOperand; break;
                        case "*": result = firstOperand * secondOperand; break;
                        case "/":
                            if (secondOperand == 0) {
                                display.setText(firstOperand + " " + operator + " " + secondOperand + " = ERROR");
                                return;
                            }
                            result = firstOperand / secondOperand; 
                            break;
                    }

                    // FULL EXPRESSION DISPLAY
                    String fullExpression = firstOperand + " " + operator + " " + secondOperand + " = " + result;
                    display.setText(fullExpression);

                    // allow continued operations
                    currentInput = String.valueOf(result);

                } catch (Exception ex) {
                    display.setText("Error");
                }
            }
            return;
        }

        // Number or decimal button
        currentInput += input;
        display.setText(currentInput);
    }

    public static void main(String[] args) {
        new Calculator();
    }
}
