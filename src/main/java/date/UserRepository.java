
package date;

import model.User;
import java.sql.*;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

public class UserRepository
{
    public static void CreateUser(User user)
    {
        Session session = Listener.sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.save(user);
        transaction.commit();
        session.close();
    }

    public static User GetUserByLogin(String login)
    {
        Session session = Listener.sessionFactory.openSession();
        Criteria criteria = session.createCriteria(User.class);
        User user = (User) criteria.add(Restrictions.eq("login", login)).uniqueResult();
        session.close();
        return user;
    }
}
