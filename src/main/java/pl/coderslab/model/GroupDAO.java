package pl.coderslab.model;

import pl.coderslab.DBUtil;

import java.sql.*;
import java.util.Arrays;

public class GroupDAO {

    private static final String CREATE_USER_QUERY = "INSERT INTO user_group (name) VALUES (?)";

    private static final String READ_USER_QUERY = "SELECT * FROM user_group WHERE id = ?";

    private static final String UPDATE_USER_QUERY = "UPDATE user_group SET name = ? WHERE ID = ?";

    private static final String DELETE_USER_QUERY = "DELETE FROM user_group WHERE id = ?";

    private static final String FIND_ALL_USERS_QUERY = "SELECT * FROM user_group";

    public Group create(Group group) {
        try (Connection conn = DBUtil.getConnection()) {
            // Utworzenie obiektu reprezentujacego zapytanie
            PreparedStatement statement =
                    conn.prepareStatement(CREATE_USER_QUERY, Statement.RETURN_GENERATED_KEYS);

            // ustawienie parametrow zapytania (wartosci do wstawienia)
            statement.setString(1, group.getName());

            // wykonanie zapytania
            statement.executeUpdate();
            // Pobranie zestawu wygenerowanych kluczy
            ResultSet resultSet = statement.getGeneratedKeys();
            if (resultSet.next()) {
                // Ustawienie id uzytkownikowi na podstawie klucza nadanego przez baze danych
                group.setId(resultSet.getInt(1));
            }
            return group;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
    public Group read(int groupId) {

        try (Connection conn = DBUtil.getConnection()) {
            PreparedStatement statement = conn.prepareStatement(READ_USER_QUERY);
            statement.setInt(1, groupId);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                Group group = new Group();
                group.setId(resultSet.getInt("id"));
                group.setName(resultSet.getString("name"));
                return group;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void update(Group group) {
        try (Connection conn = DBUtil.getConnection()) {
            PreparedStatement statement = conn.prepareStatement(UPDATE_USER_QUERY);
            statement.setString(1, group.getName());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void delete(int groupId) {
        try (Connection conn = DBUtil.getConnection()){
            PreparedStatement statement = conn.prepareStatement(DELETE_USER_QUERY);
            statement.setInt(1, groupId);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public Group[] findAll() {
        try (Connection conn = DBUtil.getConnection()) {
            Group[] groups = new Group[0];
            PreparedStatement statement = conn.prepareStatement(FIND_ALL_USERS_QUERY);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Group group = new Group();
                group.setId(resultSet.getInt("id"));
                group.setName(resultSet.getString("name"));
               groups= addToArray(group, groups);
            }
            return groups;
        } catch (SQLException e) {
            e.printStackTrace(); return null;
        }
    }
    private Group[] addToArray(Group g, Group[] groups) {
        Group[] tmpGroups= Arrays.copyOf(groups, groups.length + 1);
        tmpGroups[groups.length] = g;
        return tmpGroups;
    }
}
