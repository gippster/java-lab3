package date;

import model.User;//
import java.sql.*;

public class UserRepository
{
    private static final String dbUrl = "jdbc:postgresql://localhost:5432/java_lab6";
    private static final String user = "postgres";
    private static final String password = "12345";
    private static Connection connection;

    public  static void Connect() throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        DriverManager.registerDriver((Driver) Class.forName("org.postgresql.Driver").newInstance());
        connection = DriverManager.getConnection(dbUrl, user, password);
    }

    public static void Close() throws SQLException {
        connection.close();
    }

    public static void CreateUser(User user) throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        if(connection == null) Connect();

        PreparedStatement st =  connection.prepareStatement("insert into java_lab6.public.user(login, email, password) values(?, ?, ?)");//
        st.setString(1, user.getLogin());
        st.setString(2, user.getEmail());
        st.setString(3, user.getPassword());
        st.executeUpdate();
        st.close();
    }

    public static User GetUserByLogin(String login) throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        if(connection == null) Connect();

        Statement st =  connection.createStatement();
        st.execute("select id, login, email, password from java_lab6.public.user where login = '" + login + "'");//
        ResultSet resultSet = st.getResultSet();

        User user = null;
        while (resultSet.next()) {
            // Обработка результатов запроса
            String fromDbLogin = resultSet.getString("login");
            String email = resultSet.getString("email");
            String password = resultSet.getString("password");

            user = new User(fromDbLogin, password, email);
        }

        resultSet.close();
        st.close();
        return user;
    }
}