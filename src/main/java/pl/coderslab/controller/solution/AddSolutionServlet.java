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

@WebServlet("/solution/add")
public class AddSolutionServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        SolutionDAO solutionDao = new SolutionDAO();

        int userId = Integer.parseInt(request.getParameter("chooseUser"));
        int exerciseId = Integer.parseInt(request.getParameter("chooseExercise"));
        String description = request.getParameter("description");

        Solution solution = new Solution(description, exerciseId, userId);
        solutionDao.create(solution);

        response.sendRedirect("/");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        ExerciseDAO exerciseDao = new ExerciseDAO();
        UserDAO userDao = new UserDAO();

        request.setAttribute("exercises", exerciseDao.findAll());
        request.setAttribute("users", userDao.findAll());
        getServletContext().getRequestDispatcher("/addSolution.jsp").forward(request, response);
    }
}
