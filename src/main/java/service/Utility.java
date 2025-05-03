package service;

import jakarta.servlet.http.HttpServletRequest;

import java.io.File;

public class Utility
{
    private static String path = "C:/Users/sajbu/IdeaProjects/lab3/lab5/";

    public static String RedirectOn(String currentURL, String redirectPath)
    {
        return currentURL.substring(0,currentURL.lastIndexOf("/")) + redirectPath;
    }

    public static void CreateNewUserFolder(String login) throws Exception
    {
        File folder = new File(path + login);
        if(!folder.mkdir())
        {
            throw new Exception("could not create folder");
        }
    }

    public static boolean IsCorrectFolderForUser(String login, String currentPath)
    {
        return currentPath.startsWith(path + login);
    }

    public static  String GetCorrectRouteForFolder(String login)
    {
        return path + login;
    }
}