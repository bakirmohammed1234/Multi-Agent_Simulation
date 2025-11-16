import jade.core.Agent;
import jade.core.AID;
import jade.lang.acl.ACLMessage;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class AgentUser extends Agent {

    private JTextField fieldA;
    private JTextField fieldB;
    private JComboBox<String> operatorBox;
    private JLabel resultLabel;

    @Override
    protected void setup() {
        System.out.println(getLocalName() + " started.");
        SwingUtilities.invokeLater(this::createUI);
    }

    private void createUI() {

        JFrame frame = new JFrame("Smart Calculator");
        frame.setSize(420, 320);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);

        // ===== Style global =====
        Font fontTitle = new Font("Segoe UI", Font.BOLD, 22);
        Font fontText = new Font("Segoe UI", Font.PLAIN, 16);

        Color primary = new Color(60, 130, 200);
        Color secondary = new Color(235, 235, 235);

        frame.setLayout(new BorderLayout());

        // ===== Titre =====
        JLabel title = new JLabel("Smart Calculator", SwingConstants.CENTER);
        title.setFont(fontTitle);
        title.setBorder(BorderFactory.createEmptyBorder(15, 0, 10, 0));
        frame.add(title, BorderLayout.NORTH);

        // ===== Centre =====
        JPanel center = new JPanel();
        center.setLayout(new GridLayout(4, 2, 12, 12));
        center.setBorder(BorderFactory.createEmptyBorder(20, 30, 20, 30));
        center.setBackground(Color.white);

        fieldA = new JTextField();
        fieldA.setFont(fontText);
        fieldA.setHorizontalAlignment(JTextField.CENTER);
        fieldA.setBorder(BorderFactory.createLineBorder(primary, 2));

        fieldB = new JTextField();
        fieldB.setFont(fontText);
        fieldB.setHorizontalAlignment(JTextField.CENTER);
        fieldB.setBorder(BorderFactory.createLineBorder(primary, 2));

        operatorBox = new JComboBox<>(new String[]{"+", "-", "*", "/"});
        operatorBox.setFont(fontText);
        operatorBox.setBorder(BorderFactory.createLineBorder(primary, 2));
        operatorBox.setBackground(secondary);

        JButton btn = new JButton("Calculate");
        btn.setFont(new Font("Segoe UI", Font.BOLD, 18));
        btn.setBackground(primary);
        btn.setForeground(Color.WHITE);
        btn.setFocusPainted(false);
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btn.setBorder(BorderFactory.createEmptyBorder(10, 15, 10, 15));

        // rendre bouton arrondi
        btn.setBorder(new RoundedBorder(20));

        resultLabel = new JLabel("Result : --", SwingConstants.CENTER);
        resultLabel.setFont(new Font("Segoe UI", Font.BOLD, 18));
        resultLabel.setBorder(BorderFactory.createEmptyBorder(10, 0, 15, 0));

        // Ajout des éléments
        center.add(new JLabel("First Number :", SwingConstants.RIGHT));
        center.add(fieldA);

        center.add(new JLabel("Second Number :", SwingConstants.RIGHT));
        center.add(fieldB);

        center.add(new JLabel("Operator :", SwingConstants.RIGHT));
        center.add(operatorBox);

        center.add(new JLabel(""));
        center.add(btn);

        frame.add(center, BorderLayout.CENTER);
        frame.add(resultLabel, BorderLayout.SOUTH);

        frame.setVisible(true);

        // Listener envoi message
        btn.addActionListener((ActionEvent e) -> sendCalculation());

        // Behaviour JADE pour recevoir le résultat
        addBehaviour(new jade.core.behaviours.CyclicBehaviour() {
            public void action() {
                ACLMessage message = receive();
                if (message != null) {
                    resultLabel.setText("Result : " + message.getContent());
                } else {
                    block();
                }
            }
        });
    }

    private void sendCalculation() {
        try {
            double a = Double.parseDouble(fieldA.getText());
            double b = Double.parseDouble(fieldB.getText());
            String op = (String) operatorBox.getSelectedItem();

            ACLMessage msg = new ACLMessage(ACLMessage.REQUEST);
            msg.addReceiver(new AID("AgentCalcule", AID.ISLOCALNAME));
            msg.setContent(a + ";" + b + ";" + op);
            send(msg);

            resultLabel.setText("Calculating...");
        } catch (Exception ex) {
            resultLabel.setText("Error: invalid input");
        }
    }

    // ===== BOUTON ARRONDI =====
    class RoundedBorder implements javax.swing.border.Border {
        private int radius;

        RoundedBorder(int radius) {
            this.radius = radius;
        }

        public Insets getBorderInsets(Component c) {
            return new Insets(this.radius + 1, this.radius + 1, this.radius + 1, this.radius + 1);
        }

        public boolean isBorderOpaque() {
            return true;
        }

        public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
            g.drawRoundRect(x, y, width - 1, height - 1, radius, radius);
        }
    }
}
