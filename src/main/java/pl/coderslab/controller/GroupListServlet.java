package pl.coderslab.controller;

import pl.coderslab.model.GroupDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/groups")
public class GroupListServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        GroupDAO userGroupDao = new GroupDAO();
        request.setAttribute("groups", userGroupDao.findAll());

        getServletContext().getRequestDispatcher("/listGroup.jsp").forward(request, response);
    }
}
