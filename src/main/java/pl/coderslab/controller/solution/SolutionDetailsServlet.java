package pl.coderslab.controller.solution;

import pl.coderslab.model.ExerciseDAO;
import pl.coderslab.model.Solution;
import pl.coderslab.model.SolutionDAO;
import pl.coderslab.model.UserDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/solution/details")
public class SolutionDetailsServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        SolutionDAO solutionDao = new SolutionDAO();
        ExerciseDAO exerciseDao = new ExerciseDAO();
        UserDAO userDao = new UserDAO();

        int id = Integer.parseInt(request.getParameter("id"));

        Solution solution = solutionDao.read(id);

        request.setAttribute("solution", solution);
        request.setAttribute("exercise", exerciseDao.read(solution.getExerciseId()));
        request.setAttribute("user", userDao.read(solution.getUsersId()));

        getServletContext().getRequestDispatcher("/detailsSolution.jsp").forward(request, response);
    }
}
