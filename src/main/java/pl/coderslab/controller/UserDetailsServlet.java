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

@WebServlet("/users/details")
public class UserDetailsServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        UserDAO userDao = new UserDAO();
        GroupDAO userGroupDao = new GroupDAO();
        SolutionDAO solutionDao = new SolutionDAO();
        ExerciseDAO exerciseDao = new ExerciseDAO();

        int userId = Integer.parseInt(request.getParameter("userId"));
        int groupId = Integer.parseInt(request.getParameter("groupId"));

        List<Solution> solutions = Arrays.asList(solutionDao.findAllByUserId(userId));
        List<Exercise> exercises = new ArrayList<>();

        for(Solution solution: solutions){
            exercises.add(exerciseDao.read(solution.getExerciseId()));
        }

        request.setAttribute("user", userDao.read(userId));
        request.setAttribute("group", userGroupDao.read(groupId));
        request.setAttribute("solutions", solutions);
        request.setAttribute("exercises", exercises);

        getServletContext().getRequestDispatcher("/detailsUser.jsp").forward(request, response);
    }
}
