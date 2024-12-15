import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.border.EmptyBorder;

public class chadsVascCalculatorGUI extends JFrame {

    private JCheckBox chfHistory;
    private JCheckBox hypertensionHistory;
    private JTextField ageField;
    private JCheckBox diabetesHistory;
    private JCheckBox tiaHistory;
    private JCheckBox vascularDisease;
    private JRadioButton maleButton;
    private JRadioButton femaleButton;
    private JLabel resultLabel;

    public chadsVascCalculatorGUI() {
        setTitle("CHA2DS2-VASc Calculator");
        setSize(500, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel mainPanel = new JPanel();
        mainPanel.setBorder(new EmptyBorder(12, 12, 12, 12));
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));

        // Adding fields and components to the main panel with custom size for JTextField and JButton
        mainPanel.add(createRow("CHF History", chfHistory = new JCheckBox()));
        mainPanel.add(createRow("Hypertension History", hypertensionHistory = new JCheckBox()));

        JLabel ageFieldLabel = new JLabel("Age");
        ageField = new JTextField();
        ageField.setPreferredSize(new Dimension(60, 30)); // Set preferred size for ageField
        mainPanel.add(createRow(ageFieldLabel, ageField));

        mainPanel.add(createRow("Diabetes History", diabetesHistory = new JCheckBox()));
        mainPanel.add(createRow("TIA History", tiaHistory = new JCheckBox()));
        mainPanel.add(createRow("Vascular Disease", vascularDisease = new JCheckBox()));

        JLabel sexAtBirthLabel = new JLabel("Sex At Birth: ");
        maleButton = new JRadioButton("Male");
        femaleButton = new JRadioButton("Female");
        ButtonGroup sexAtBirthGroup = new ButtonGroup();
        sexAtBirthGroup.add(maleButton);
        sexAtBirthGroup.add(femaleButton);

        // Panel for sex radio buttons
        JPanel sexPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        sexPanel.add(maleButton);
        sexPanel.add(femaleButton);

        mainPanel.add(createRow(sexAtBirthLabel, sexPanel));

        resultLabel = new JLabel("Risk Score: ");
        mainPanel.add(resultLabel);

        // Panel for calculate button
        JButton calculateButton = new JButton("Calculate");
        calculateButton.setPreferredSize(new Dimension(150, 40)); // Set preferred size for calculateButton
        //calculateButton.addActionListener(new ActionListener() {
           // @Override
           // public void actionPerformed(ActionEvent e) {
                //calculateCHADSVASC();
            //}
       // });
        calculateButton.addActionListener( e-> calculateCHADSVASC());

        // Center the button using a FlowLayout panel
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.add(calculateButton);
        mainPanel.add(buttonPanel);

        add(mainPanel);
    }

    // Helper method to create a row with a label and component
    private JPanel createRow(String labelText, JComponent component) {
        JPanel rowPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        rowPanel.add(new JLabel(labelText));
        rowPanel.add(component);
        return rowPanel;
    }

    // Overloaded helper method to create a row with JLabel and another component
    private JPanel createRow(JLabel label, JComponent component) {
        JPanel rowPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        rowPanel.add(label);
        rowPanel.add(component);
        return rowPanel;
    }

    private void calculateCHADSVASC() {

        try {
        int result = 0;
        int age = Integer.parseInt(ageField.getText());

        if (chfHistory.isSelected()) result++;
        if (hypertensionHistory.isSelected()) result++;

        if (age < 1) throw new NumberFormatException();
        else if (age >= 75) result += 2;
        else if (age >= 65) result++;
        

        if (diabetesHistory.isSelected()) result++;
        if (tiaHistory.isSelected()) result++;
        if (vascularDisease.isSelected()) result++;
        if (femaleButton.isSelected()) result++;

        resultLabel.setText("Risk Score: " + result);
        } catch(NumberFormatException e){
            JOptionPane.showMessageDialog( this, "Please enter valid Age (e.g., a number above zero)", "Input Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) {
        //SwingUtilities.invokeLater(() -> new chadsVascCalculatorGUI().setVisible(true));
        SwingUtilities.invokeLater( () -> {
            chadsVascCalculatorGUI cha = new chadsVascCalculatorGUI();
            cha.setVisible(true);
        });
    }
}
