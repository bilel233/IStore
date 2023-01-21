import javax.swing.*;

public class Fenetre extends JFrame {
    public Fenetre()
    {
        setTitle("Ma fenetre");  /* titre de la fenetre */
        setSize(400,300); /* taille de la fenetre */

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JPanel panel = new JPanel(); /* instanciation de l'objet' */
        JLabel label = new JLabel("Enter Your email:");
        JTextField textField = new JTextField(20);
        JButton button = new JButton("Submit");

        panel.add(label); /* rajout du label dans l'objet panel */
        panel.add(textField);
        panel.add(button);
        add(panel);
        setVisible(true);

    }
}
