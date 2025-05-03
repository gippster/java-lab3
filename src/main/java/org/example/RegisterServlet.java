package org.example;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.User;
import service.AuthService;
import service.Utility;

import java.io.IOException;

@WebServlet(urlPatterns = {"/Register"})
public class RegisterServlet extends HttpServlet {

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/Register.jsp").forward(request, response);
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException
    {
        String login = request.getParameter("login");
        String password = request.getParameter("password");
        String email = request.getParameter("email");

        if(email.isEmpty() || login.isEmpty() || password.isEmpty()) {
            response.getWriter().println("Incorrect login, password or email");
        }
        User user = AuthService.GetUser(login, password);
        if (user != null) {
            response.getWriter().println("User already exists");
            return;
        }

        AuthService.CreateUser(new User(login, password, email));

        request.getSession().setAttribute("email", email);
        try {
            Utility.CreateNewUserFolder(login);
        } catch (Exception ex) {
            response.getWriter().println(ex.getMessage());
            return;
        }

        response.sendRedirect(Utility.RedirectOn(request.getRequestURL().toString(), "/Manager?path=C:/Users/sajbu/IdeaProjects/lab3/lab5/"+login+"/"));
    }
}
