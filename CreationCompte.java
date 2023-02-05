import javax.swing.*;
import java.io.*;
import java.util.*;
public class CreationCompte {
    private static final List<String> whitelist = Arrays.asList("example1@email.com", "example2@email.com", "example3@email.com");

    public static boolean isEmailAllowed(String email) {
        // Verifier si l'email est au format exact
        if (!email.matches("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$")) {
            return false;
        }
        // Verifier si l'email est dans la whitelist
        return whitelist.contains(email);
    }

    public static void createAccount(String email, String password) {

        if (!isEmailAllowed(email)) {
            System.out.println("Error: Email address not allowed");
            return;
        }
        //Creation du compte
        try {
            /*  creation d'un utilisateur

             */
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,"une erreur est survenue :" + e.getMessage(),"Erreur",JOptionPane.ERROR_MESSAGE);
        }
        System.out.println("Account created successfully");


    }
}
