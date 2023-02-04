import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
public class UserDdb {
    private static final String INSERT_SQL = "INSERT INTO users (email, password) VALUES (?, ?)";

    public void createUser(String email, String password) {
        try (Connection conn = DriverManager.getConnection(
                "jdbc:mysql://localhost:8889/istore", "root", "");
             PreparedStatement statement = conn.prepareStatement(INSERT_SQL)) {
            statement.setString(1, email);
            statement.setString(2, hashPassword(password));
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static String hashPassword(String password) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            md.update(password.getBytes());
            byte[] hash = md.digest();
            return Base64.getEncoder().encodeToString(hash);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }
    }
}
