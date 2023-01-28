
import javax.swing.*;



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



    }
}
