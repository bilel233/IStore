import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class User {
    /* creation d'une classe User avec les champs suivants : username , password, email

     */
    private String username;
    private String password;
    private String email;
    private String role;
    private int id;

    /* constructeur pour creer un nouvel utilisateur avec des valeur specifiees

     */
    public User(int id,String email,String username,String password,String role)
    {
       this.id = id;
       this.email = email;
       this.username=username;
       this.password=password;
       this.role=role;

    }
    /* on ajoute un nouveau constructeur sans le mot de passe */
    public User(int id,String email,String username,String role)
    {
        this.id = id;
        this.email=email;
        this.username=username;
        this.role=role;
    }

    /* getters et setters pour les champs de l'utilisateur */
    public String getUsername()
    {
        return username;
    }
    public void setUsername(String username)
    {
        this.username = username;
    }
    public String getPassword()
    {
        return password;
    }

    public void setPassword(String password)
    {
        this.password =password;
    }
    public String getEmail()
    {
        return email;
    }
    public void setEmail(String email)
    {
        this.email=email;
    }
    public int getId()
    {
        return id;
    }
    public void setId(int id)
    {
        this.id = id;
    }

    public String getRole()
    {
        return role;
    }
    public void setRole(String role)
    {
        this.role = role;
    }
    /* utilisation d'une methode de supression pour supprimer l'utilisateur
    Reamrque : ceci est un exemple simple et generiqsue,
    il peut y avoir des etapes supplementaires pour
    supprimer un utilisateur reel d'une base de données ou d'un systeme de fichiers
     */
    public void deleteUser(User user)
    {
        user = null;
    }

    public static User getUserById(int userId) throws SQLException {
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;

        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:8889/istore", "root", "");
            statement = connection.createStatement();
            resultSet = statement.executeQuery("SELECT * FROM users WHERE id = " + userId);
            if (resultSet.next()) {
                int id = resultSet.getInt("id");
                String email = resultSet.getString("email");
                String username = resultSet.getString("username");
                String role = resultSet.getString("role");
                return new User(id, email, username, role);
            } else {
                return null;
            }
        } finally {
            if (resultSet != null) {
                resultSet.close();
            }
            if (statement != null) {
                statement.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
    }


}

