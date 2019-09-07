package pl.coderslab.controller;

import pl.coderslab.model.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@WebServlet("")
public class HomeServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int solutionsToPrint = Integer.parseInt(getServletContext().getInitParameter("number-solutions"));
        SolutionDAO solutionDao = new SolutionDAO();
        ExerciseDAO exerciseDAO = new ExerciseDAO();
        UserDAO userDAO = new UserDAO();

        List<Solution> solutions = Arrays.asList(solutionDao.findRecent(solutionsToPrint));
        List<Exercise> exercises = new ArrayList<>();
        List<User> users = new ArrayList<>();

        for (Solution solution: solutions){
            exercises.add(exerciseDAO.read(solution.getExerciseId()));
            users.add(userDAO.read(solution.getUsersId()));
        }

        request.setAttribute("solutions", solutions);
        request.setAttribute("exercises", exercises);
        request.setAttribute("users", users);
        getServletContext().getRequestDispatcher("/home.jsp").forward(request, response);



    }
}
