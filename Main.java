
import javax.swing.*;
import java.sql.*;
import java.util.Scanner;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class Main {
    public static void main(String[] args) {

        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new Fenetre();
                String login = "example";
                char[] password = "password".toCharArray();

            }
        });
        CreationCompte.createAccount("example1@email.com", "password");
        /* Utilisation de la classe User pour creer un nouvel
        utilisateur
         */
        User user = new User(1, "email@example.com", "username", "password", "admin");

        /* utilisation des getters pour lire les informations de l'utilisateur

         */
        String username = user.getUsername();
        String password = user.getPassword();
        String email = user.getEmail();

        /*Utilisation des setters pour mettre a jour les informations de l'utilisateur

         */
        user.setEmail("newmail@mail.com");
        user.setPassword("newpassword456");

        /* on cree une banniere de connexion reussie apres
        avoir rentre l'eamil et le password

         */
        boolean loginSucceesful = true;

        if (loginSucceesful){
            JFrame frame = new JFrame ("Connexion reussie");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(300,200);
            frame.setVisible(true);
        }
// on implemente la la classe Login avec exception d'erreur

        Scanner sc = new Scanner(System.in);
        System.out.println("Entrez votre email: ");
        String em = sc.nextLine();
        System.out.println("Entrez votre mot de passe: ");
        String pass = sc.nextLine();

        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:8889/istore", "root", "")) {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM Users WHERE email = ?");
            statement.setString(1, email);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                String storedPassword = resultSet.getString("password");
                // On Compare le mot de passe saisi par l'utilisateur avec celui stocké dans la base de données
                if (storedPassword.equals(pass)) {
                    System.out.println("Connexion réussie!");
                } else {
                    System.out.println("Mot de passe incorrect.");
                }
            } else {
                System.out.println("Aucun compte trouvé avec cet email.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

