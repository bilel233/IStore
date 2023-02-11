
import javax.swing.*;
import java.util.Random;
import java.util.Scanner;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class Main {
    public static void main(String[] args) {
        User u = new User(1, "user1@mail.com", "user", "password", "utilisateur");
        User a = new User(1, "user1@mail.com", "user", "password", "administrateur");
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
        // boolean loginSucceesful = true;

        // if (loginSucceesful){
        //    JFrame frame = new JFrame ("Connexion reussie");
        //    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //    frame.setSize(300,200);
        //   frame.setVisible(true);
        //  }
// on implemente  la classe Login avec exception d'erreur

        Scanner sc = new Scanner(System.in);
        System.out.println("Entrez votre email: ");
        String em = sc.nextLine();
        System.out.println("Entrez votre mot de passe: ");
        String pass = sc.nextLine();

        /* test de la fonction login */
        Login.log();

        /* test de la fonction db */
        UserDdb.db("user@mail.com", "password");


        /* hasshage du password */
        Password.hashPassword("password");

        /* on verifie si l'email est autorisée */

        boolean email1 = CreationCompte.isEmailAllowed("user@mail.com");

        /* appel de la methode createAccount */

        CreationCompte.createAccount("user@mail.com", "password"); // user@mail.com se trouve dans la bdd de dbeaver
        /* pour creer un compte, regarder de maniere specifique la ligne 4 dans le fichier CreationCompte.java , la whitelist*/

        /* on met a jour l'administrateur */

        User.updateUser(a, u);
        /* on supprime un utilsateur


         */

        //User.deleteUser(u,a);

        /* on recherche les informations stockees sur les marchandises */
        for (int i = 0; i < 7; i++) {
            AccesInformation.acces();

        }
        System.out.println("===============================================");
        System.out.println("vous avez affiche toute les marchandises");

        System.out.println("Sélectionnez une option:");
        System.out.println("1. Ajouter des articles");
        System.out.println("2. Retirer des articles");
        System.out.println("3. Quitter");


        while (true) {
            System.out.println("Sélectionnez une option:");
            System.out.println("1. Ajouter des articles");
            System.out.println("2. Retirer des articles");
            System.out.println("3. Quitter");
            Scanner scan =new Scanner(System.in);
            int option = scan.nextInt();
            if (option == 3) {
                break;
            }

            System.out.println("Entrez l'identifiant de l'article:");
            int id = scan.nextInt();

            System.out.println("Entrez le nom de l'article:");
            String name = scan.nextLine();

            System.out.println("Entrez la quantité:");
            int quantity = scan.nextInt();

            System.out.println("Entrez le prix:");
            double  price = scan.nextDouble();

            // Ajouter/retirer des articles en utilisant les informations entrées
            // et en effectuant des mises à jour sur la base de données.

            String query = "DELETE FROM Produits WHERE idProduits = ?";
            try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost:8889/istore", "root", "");
                 PreparedStatement pst = con.prepareStatement(query)) {
                pst.setInt(1, id);
                pst.executeUpdate();
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
            String que = "INSERT INTO Produits (idProduits, titre, prix, quantite) values (?, ?, ?, ?)";
            try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost:8889/istore", "root", "");
                 PreparedStatement pst = con.prepareStatement(que)) {
                pst.setInt(1, id);
                pst.setString(2, name);
                pst.setDouble(3,price );
                pst.setInt(4, quantity);
                pst.executeUpdate();
            } catch (SQLException e) {
                System.out.println(e.getMessage());

            }
            break;
        }


    }

}

















