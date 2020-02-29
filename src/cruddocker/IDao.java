package cruddocker;

import java.sql.ResultSet;

/**
 *
 * @author exkapp
 */
public interface IDao<T>{
    boolean insert(T t);
    boolean delete(T t);
    boolean update(T t);
    ResultSet readOne(T t);
    ResultSet readAll();
}
