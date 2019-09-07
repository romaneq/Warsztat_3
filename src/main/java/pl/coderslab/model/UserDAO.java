package pl.coderslab.model;

import pl.coderslab.DBUtil;

import java.sql.*;
import java.util.Arrays;

public class UserDAO {

    private static final String CREATE_USER_QUERY = "INSERT INTO users (email, username, password) VALUES (?, ?, ?)";

    private static final String READ_USER_QUERY = "SELECT * FROM users WHERE id = ?";

    private static final String UPDATE_USER_QUERY = "UPDATE users SET email = ?, username = ?, password = ? WHERE ID = ?";

    private static final String DELETE_USER_QUERY = "DELETE FROM users WHERE id = ?";

    private static final String FIND_ALL_USERS_QUERY = "SELECT * FROM users";

    private static final String READ_USER_GROUP_QUERY = "SELECT * FROM users WHERE user_group_id = ?";

    public User create(User user) {
        try (Connection conn = DBUtil.getConnection()) {
            // Utworzenie obiektu reprezentujacego zapytanie
            PreparedStatement statement =
                    conn.prepareStatement(CREATE_USER_QUERY, Statement.RETURN_GENERATED_KEYS);

            // ustawienie parametrow zapytania (wartosci do wstawienia)
            statement.setString(1, user.getUsername());
            statement.setString(2, user.getEmail());
            statement.setString(3, user.getPassword());

            // wykonanie zapytania
            statement.executeUpdate();
            // Pobranie zestawu wygenerowanych kluczy
            ResultSet resultSet = statement.getGeneratedKeys();
            if (resultSet.next()) {
                // Ustawienie id uzytkownikowi na podstawie klucza nadanego przez baze danych
                user.setId(resultSet.getInt(1));
            }
            return user;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }


    public User read(int userId) {

        try (Connection conn = DBUtil.getConnection()) {
            PreparedStatement statement = conn.prepareStatement(READ_USER_QUERY);
            statement.setInt(1, userId);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getInt("id"));
                user.setUsername(resultSet.getString("username"));
                user.setEmail(resultSet.getString("email"));
                user.setPassword(resultSet.getString("password"));
                return user;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void update(User user) {
        try (Connection conn = DBUtil.getConnection()) {
            PreparedStatement statement = conn.prepareStatement(UPDATE_USER_QUERY);
            statement.setString(1, user.getEmail());
            statement.setString(2, user.getUsername());
            statement.setString(3, user.getPassword());
            statement.setInt(4, user.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void delete(int userId) {
        try (Connection conn = DBUtil.getConnection()){
            PreparedStatement statement = conn.prepareStatement(DELETE_USER_QUERY);
            statement.setInt(1, userId);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public User[] findAll() {
        try (Connection conn = DBUtil.getConnection()) {
            User[] users = new User[0];
            PreparedStatement statement = conn.prepareStatement(FIND_ALL_USERS_QUERY);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getInt("id"));
                user.setUsername(resultSet.getString("username"));
                user.setEmail(resultSet.getString("email"));
                user.setPassword(resultSet.getString("password"));
                users = addToArray(user, users);
            }
            return users;
        } catch (SQLException e) {
            e.printStackTrace(); return null;
        }
    }

    private User[] addToArray(User u, User[] users) {
        User[] tmpUsers = Arrays.copyOf(users, users.length + 1);
        tmpUsers[users.length] = u;
        return tmpUsers;
    }
    public User[] findAllByGroupId( int userGroupId) {
        try (Connection conn = DBUtil.getConnection()) {
            User[] users = new User[0];
            PreparedStatement statement = conn.prepareStatement(READ_USER_GROUP_QUERY);
            statement.setInt(1, userGroupId);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getInt("id"));
                user.setUsername(resultSet.getString("username"));
                user.setEmail(resultSet.getString("email"));
                user.setPassword(resultSet.getString("password"));
                users = addToArray(user, users);
            }
            return users;
        } catch (SQLException e) {
            e.printStackTrace(); return null;
        }
    }
}
