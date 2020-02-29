package cruddocker;

import java.sql.ResultSet;

/**
 *
 * @author exkapp
 */
public class DaoEmpleado implements IDao<Empleado>{
    
    private Conection c = Conection.getInstance();
    private String sql = "";
    
    @Override
    public boolean insert(Empleado t) {
        sql = "INSERT INTO empleados (id, nombre, apellido) VALUES (" + t.getId() + ",'"+t.getNombre()+"','"+t.getApellido()+"');";
        return c.execute(sql);
    }

    @Override
    public boolean delete(Empleado t) {
        sql = "DELETE FROM empleados WHERE(id = " + t.getId() + ");";
        return c.execute(sql);
    }

    @Override
    public boolean update(Empleado t) {
        sql = "UPDATE empleados SET nombre = '" + t.getNombre() + "', apellido = '" + t.getApellido() + "' WHERE (id = " + t.getId() + ");";
        return c.execute(sql);
    }

    @Override
    public ResultSet readOne(Empleado t) {
        sql = "SELECT * FROM empleados WHERE (id =" + t.getId() + ");";
        return c.executeQuery(sql);
    }

    @Override
    public ResultSet readAll() {
        sql = "SELECT * FROM empleados;";
        return c.executeQuery(sql);
    }
    
}
