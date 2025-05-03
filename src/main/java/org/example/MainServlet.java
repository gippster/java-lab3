package org.example;

import java.text.SimpleDateFormat;
import java.util.Date;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import service.FileManager;
import java.io.*;
import model.User;
import service.AuthService;
import service.Utility;

@WebServlet(urlPatterns = {"/Manager"})
public class MainServlet extends HttpServlet {

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException
    {
        String login = (String) request.getSession().getAttribute("login");
        String password = (String) request.getSession().getAttribute("password");
        String email = (String) request.getSession().getAttribute("email");

        User user = AuthService.GetUser(login, password);

        if(user == null)
        {
            if(login!= null)//если незалогинен
            {
                AuthService.CreateUser(new User(login, password, email));
            }
            else
            {
                response.sendRedirect(Utility.RedirectOn(request.getRequestURL().toString(), "/Login"));
                return;
            }
        }

        String currentPath = request.getParameter("path");

        if(currentPath == null)
        {
            currentPath = "D:/dev/Java_dev/Java/2sem_Spring/Lab5/";
        }

        if (!currentPath.contains(".."))
        {
            if(!Utility.IsCorrectFolderForUser(login, currentPath))//если дирректория неверна(выше корневой)
            {
                currentPath = Utility.GetCorrectRouteForFolder(login);//вернёт в корневую
            }
        }
        else
        {
            currentPath = Utility.GetCorrectRouteForFolder(login);
        }


        FileManager fileManager = new FileManager();
        File[] folders = fileManager.allFolders(currentPath);//все папки по пути <_<
        if (folders == null)
        {
            folders = new File[0];
        }

        File[] files = fileManager.allFiles(currentPath);//все файлы по пути -_-
        if (files == null)
        {
            files = new File[0];
        }

        Date generationDate = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yy HH:mm:ss");

        request.setAttribute("generationTime", dateFormat.format(generationDate));
        request.setAttribute("folders", folders);//задаю атрибут запросу...и присвоил массив
        request.setAttribute("files", files);//и тута тоже
        request.setAttribute("currentPath", currentPath);
        request.setAttribute("login", login);
        request.getRequestDispatcher("Manager.jsp").forward(request, response);//попросил Manager.jsp обработать запросек
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException
    {
        request.getSession().removeAttribute("login");
        request.getSession().removeAttribute("password");

        response.sendRedirect(Utility.RedirectOn(request.getRequestURL().toString(),"/Login"));
    }
}