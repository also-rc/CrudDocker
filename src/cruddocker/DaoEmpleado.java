package cruddocker;

import java.sql.ResultSet;

/*
/*Autor: Carlos Alonso Escamilla Rocafuerte
/*Fecha de modificación: 05/Marzo/2020
/*Descripción: Clase que implementa la interfaz Idao<T> 
/*reemplazando la literal por el objeto Empleado. 
/*Por cada método se define la sentencia sql a la que hace referencia y
/*se concatena con los datos obtenidos del objeto
*/
public class DaoEmpleado implements IDao<Empleado>{
    //Variable de instancia única para ejecutar sentencias sql
    private ConnectionToDb con2Db = ConnectionToDb.getInstance();
    //Variable para asignar la sentencia sql
    private String sql = "";
    
    @Override
    public boolean insertRecord(Empleado t) {
        sql = "INSERT INTO empleados (id, nombre, apellido) VALUES (" + t.getId() + ",'"+t.getNombre()+"','"+t.getApellido()+"');";
        return con2Db.execute(sql);
    }

    @Override
    public boolean deleteRecord(Empleado t) {
        sql = "DELETE FROM empleados WHERE(id = " + t.getId() + ");";
        return con2Db.execute(sql);
    }

    @Override
    public boolean updateRecord(Empleado t) {
        sql = "UPDATE empleados SET nombre = '" + t.getNombre() + "', apellido = '" + t.getApellido() + "' WHERE (id = " + t.getId() + ");";
        return con2Db.execute(sql);
    }

    @Override
    public ResultSet readOneRecord(Empleado t) {
        sql = "SELECT * FROM empleados WHERE (id =" + t.getId() + ");";
        return con2Db.executeQuery(sql);
    }

    @Override
    public ResultSet readAllRecords() {
        sql = "SELECT * FROM empleados;";
        return con2Db.executeQuery(sql);
    }
    
}
