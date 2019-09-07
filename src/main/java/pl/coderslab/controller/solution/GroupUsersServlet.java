package pl.coderslab.controller.solution;

import pl.coderslab.model.GroupDAO;
import pl.coderslab.model.User;
import pl.coderslab.model.UserDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

@WebServlet("/group/users")
public class GroupUsersServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        GroupDAO userGroupDao = new GroupDAO();
        UserDAO userDao = new UserDAO();
        int groupId = Integer.parseInt(request.getParameter("id"));
        List<User> allByGroupId = Arrays.asList(userDao.findAllByGroupId(groupId));

        request.setAttribute("group", userGroupDao.read(groupId));
        request.setAttribute("users", userDao.findAllByGroupId(groupId));

        getServletContext().getRequestDispatcher("/userList.jsp").forward(request, response);
    }
}
