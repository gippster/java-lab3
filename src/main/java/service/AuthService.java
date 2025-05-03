package service;

import model.User;

import java.util.HashMap;
import java.util.Objects;

public class AuthService
{
    private static HashMap<String, User> Users = new HashMap<>()
    {
        {
            put("baobab", new User("baobab", "12345", "sajbullin77@gmail.ru"));
        }
    };
    public static  void CreateUser(User user)
    {
        Users.put(user.getLogin(), user);
    }
    public static User GetUser(String login, String password)
    {
        User user = Users.get(login);

        if( user == null || !Objects.equals(user.getPassword(), password)) return null;

        return user;
    }
}