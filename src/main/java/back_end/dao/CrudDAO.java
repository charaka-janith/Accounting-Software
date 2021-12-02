package back_end.dao;

import java.sql.SQLException;
import java.util.ArrayList;

public interface CrudDAO<T,ID> extends SuperDAO{
    public boolean add(T t) throws SQLException, ClassNotFoundException;
    public boolean delete(ID id)throws Exception;
    public boolean update(T t)throws Exception;
    public T search(ID id)throws Exception;
    public ArrayList<T> getAll()throws Exception;
}
