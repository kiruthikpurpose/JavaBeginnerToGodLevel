import javax.swing.*;

public class GUIApp {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Simple GUI");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300, 200);

        JButton button = new JButton("Click Me!");
        button.addActionListener(e -> JOptionPane.showMessageDialog(frame, "Button Clicked"));

        frame.getContentPane().add(button);
        frame.setVisible(true);
    }
}
