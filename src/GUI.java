import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.border.TitledBorder;

public class GUI implements ActionListener {

    private JFrame frame;
    private JPanel mainPanel;
    private JButton[] buttons;
    private JTextField[] textFields;
    private JButton btn_run;
    private StringBuilder Opcode;
    private StringBuilder GPR;
    private StringBuilder IXR;
    private StringBuilder I;
    private StringBuilder Address;

    public GUI() {
        frame = new JFrame();
        frame.setTitle("Register Toggler");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 200);

        mainPanel = new JPanel(new GridLayout(1, 5, 10, 10));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        buttons = new JButton[16];
        textFields = new JTextField[16];

        createBox("Operation", 15, 10, 6);
        createBox("GPR", 9, 8, 2);
        createBox("IXR", 7, 6, 2);
        createBox("I", 5, 5, 1);
        createBox("Address", 4, 0, 5);
        btn_run = new JButton("Run");
        btn_run.addActionListener(this);

        frame.add(mainPanel,BorderLayout.CENTER);
        frame.add(btn_run, BorderLayout.SOUTH);
        frame.setVisible(true);
    }

    private void createBox(String title, int startNumber, int endNumber, int numButtons) {
        JPanel boxPanel = new JPanel(new BorderLayout());
        TitledBorder border = BorderFactory.createTitledBorder(title);
        border.setTitleJustification(TitledBorder.CENTER);
        boxPanel.setBorder(border);

        JPanel buttonPanel = new JPanel(new GridLayout(1, numButtons * 2));

        for (int i = startNumber; i >= endNumber; i--) {
            buttons[i] = new JButton(Integer.toString(i));
            buttons[i].addActionListener(this);
            textFields[i] = new JTextField("0", 5);
            textFields[i].setEditable(false);

            // Style improvements
            buttons[i].setBackground(new Color(0x66B2FF)); // Light blue background
            buttons[i].setForeground(Color.WHITE);
            buttons[i].setFocusPainted(false);
            textFields[i].setBackground(new Color(0xEFEFEF)); // Light gray background
            textFields[i].setHorizontalAlignment(JTextField.CENTER);

            JPanel fieldButtonPanel = new JPanel(new BorderLayout());
            fieldButtonPanel.add(textFields[i], BorderLayout.NORTH);
            fieldButtonPanel.add(buttons[i], BorderLayout.SOUTH);
            buttonPanel.add(fieldButtonPanel);
        }

        boxPanel.add(buttonPanel, BorderLayout.CENTER);
        mainPanel.add(boxPanel);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new GUI();
        });
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == btn_run) {
            Opcode = new StringBuilder();
            GPR = new StringBuilder();
            IXR = new StringBuilder();
            I = new StringBuilder();
            Address = new StringBuilder();
            for(int i=15;i>=0;i--) {
                if(i<=4)
                {
                    Address.append(textFields[i].getText());
                }
                else if(i==5)
                {
                    I.append(textFields[i].getText());
                }
                else if(i == 6 || i==7)
                {
                    IXR.append(textFields[i].getText());
                }
                else if(i == 8 || i==9)
                {
                    GPR.append(textFields[i].getText());
                }
                else if(i>=10 && i<=15)
                {
                    Opcode.append(textFields[i].getText());
                }
            }
            System.out.println(Opcode+" "+GPR+" "+IXR+" "+I+" "+Address);

        }
        else
        {
            for (int i = 0; i < 16; i++) {
                if (e.getSource() == buttons[i]) {
                    int currentValue = Integer.parseInt(textFields[i].getText());
                    int newValue = 1 - currentValue;
                    textFields[i].setText(Integer.toString(newValue));
                }
            }
        }

    }
}

