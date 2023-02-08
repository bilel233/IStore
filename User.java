import java.sql.*;

public class User {
    /* creation d'une classe User avec les champs suivants : username , password, email

     */
    private static String username;
    private static String password;
    private static String email;
    private static String role;
    private static int id;

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
    public static void setUsername(String username)
    {
        User.username = username;
    }
    public String getPassword()
    {
        return password;
    }

    public static void setPassword(String password)
    {
        User.password =password;
    }
    public String getEmail()
    {
        return email;
    }
    public static void setEmail(String email)
    {
        User.email=email;
    }
    public int getId()
    {
        return id;
    }
    public static void setId(int id)
    {
        User.id = id;
    }

    public String getRole()
    {
        return role;
    }
    public boolean isAdmin() {
        return "admin".equals(this.role); /* controler les autorisations d'acces au fonctionnalites dans l'application */
    }
    public static void setRole(String role)
    {
        User.role = role;
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
            resultSet = statement.executeQuery("SELECT * FROM Users WHERE id = " + userId);
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
    public static void  updateUser(User currentUser, User updatedUser) {
        if (currentUser.getId() == updatedUser.getId() || currentUser.getRole().equals("administrator")) {
            // autoriser la mise à jour de l'utilisateur
            setPassword(password);
            setEmail(email);
            setId(id);
            setRole(role);
            setUsername(username);
        } else {
            // afficher un message d'erreur
            System.out.println("Vous n'avez pas les autorisations nécessaires pour mettre à jour cet utilisateur.");
        }
    }
    public static  void deleteUser(User currentUser, User targetUser) {
        if (currentUser.getId() == targetUser.getId() || currentUser.isAdmin()) {

            // Code de suppression de l'utilisateur
            /* on etablit une connction a la base de donnees */
            Connection connection = null;
            try
            {
                connection = DriverManager.getConnection("jdbc:mysql://localhost:8889/istore","root","");
            }catch (SQLException e){
                System.out.println("Erreur de connexion a la base de donnees : "+ e.getMessage());
            }
            /* executez une requete DELETE pour supprimer l'utilisateur

             */
            try {
                String sql = "DELETE FROM Users WHERE email = ? AND role = ?";
                PreparedStatement statement = connection.prepareStatement(sql);
                statement.setString(1, "user@mail.com");
                statement.setString(2, "user");
                int rowsDeleted = statement.executeUpdate();
                if (rowsDeleted > 0) {
                    System.out.println("L'utilisateur a été supprimé avec succès.");
                } else {
                    System.out.println("Erreur: L'utilisateur n'a pas pu être supprimé.");
                }
            }
            catch(SQLException e) {
                System.out.println("Erreur d'execution de la requete DELETE : " + e.getMessage());
            }finally{
                try{
                    connection.close();

                }catch(SQLException e){
                    System.out.println("Erreur de fermeture de la connextion : "+e.getMessage());
                }
            }
        } else {
            // Refuser la suppression et afficher un message d'erreur

            System.out.println("Vous n'êtes pas autorisé à supprimer cet utilisateur.");
        }
    }




}

