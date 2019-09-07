package pl.coderslab.model;

import pl.coderslab.DBUtil;

import java.sql.*;
import java.util.Arrays;

public class ExerciseDAO {

    private static final String CREATE_USER_QUERY = "INSERT INTO exercise (title, description) VALUES (?, ?)";

    private static final String READ_USER_QUERY = "SELECT * FROM exercise WHERE id = ?";

    private static final String UPDATE_USER_QUERY = "UPDATE exercise SET title = ?, description= ? WHERE ID = ?";

    private static final String DELETE_USER_QUERY = "DELETE FROM exercise WHERE id = ?";

    private static final String FIND_ALL_USERS_QUERY = "SELECT * FROM exercise";

    public Exercise create(Exercise exercise) {
        try (Connection conn = DBUtil.getConnection()) {
            // Utworzenie obiektu reprezentujacego zapytanie
            PreparedStatement statement =
                    conn.prepareStatement(CREATE_USER_QUERY, Statement.RETURN_GENERATED_KEYS);

            // ustawienie parametrow zapytania (wartosci do wstawienia)
            statement.setString(1, exercise.getTitle());
            statement.setString(2, exercise.getDescription());

            // wykonanie zapytania
            statement.executeUpdate();
            // Pobranie zestawu wygenerowanych kluczy
            ResultSet resultSet = statement.getGeneratedKeys();
            if (resultSet.next()) {
                // Ustawienie id uzytkownikowi na podstawie klucza nadanego przez baze danych
                exercise.setId(resultSet.getInt(1));
            }
            return exercise;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
    public Exercise read(int exerciseId) {

        try (Connection conn = DBUtil.getConnection()) {
            PreparedStatement statement = conn.prepareStatement(READ_USER_QUERY);
            statement.setInt(1, exerciseId);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                Exercise exercise = new Exercise();
                exercise.setId(resultSet.getInt("id"));
                exercise.setTitle(resultSet.getString("title"));
                exercise.setDescription(resultSet.getString("description"));
                return exercise;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void update(Exercise exercise) {
        try (Connection conn = DBUtil.getConnection()) {
            PreparedStatement statement = conn.prepareStatement(UPDATE_USER_QUERY);
            statement.setString(1, exercise.getTitle());
            statement.setString(2, exercise.getDescription());
            statement.setInt(3, exercise.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void delete(int exerciseId) {
        try (Connection conn = DBUtil.getConnection()){
            PreparedStatement statement = conn.prepareStatement(DELETE_USER_QUERY);
            statement.setInt(1, exerciseId);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public Exercise[] findAll() {
        try (Connection conn = DBUtil.getConnection()) {
            Exercise[] exercises = new Exercise[0];
            PreparedStatement statement = conn.prepareStatement(FIND_ALL_USERS_QUERY);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Exercise exercise = new Exercise();
                exercise.setId(resultSet.getInt("id"));
                exercise.setTitle(resultSet.getString("title"));
                exercise.setDescription(resultSet.getString("description"));
                exercises= addToArray(exercise, exercises);
            }
            return exercises;
        } catch (SQLException e) {
            e.printStackTrace(); return null;
        }
    }
    private Exercise[] addToArray(Exercise e, Exercise[] exercises) {
        Exercise[] tmpExercises = Arrays.copyOf(exercises, exercises.length + 1);
        tmpExercises[exercises.length] = e;
        return tmpExercises;
    }
}
