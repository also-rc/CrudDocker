package cruddocker;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/*
/*Autor: Carlos Alonso Escamilla Rocafuerte
/*Fecha de modificación: 05/Marzo/2020
/*Descripción: Clase conexión a una base de datos de nombre empleados que se encuentra 
/*en un contenedor, mismo que está ligado através del puerto 7000 del host, se implementa 
/*patrón singleton y se incluyen dos métodos para ejecutar sentencias sql.
*/
public class ConnectionToDb {
    
    //Variable de instancia de clase única para implementar patrón singleton
    private static ConnectionToDb connectToDb;
    //Variable para gestionar la conexión
    private Connection driverPostgres = null;
    //Constructor privado con las credenciales para cceder a la base de datos
    private ConnectionToDb() {        
        try {
            Class.forName("org.postgresql.Driver");
            driverPostgres = DriverManager.getConnection("jdbc:postgresql://localhost:7000/empleados",
                    "postgres", "12334");
            System.out.println("-Conectado-");
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(ConnectionToDb.class.getName()).log(Level.SEVERE, null, ex);
        }            
    }
    
    //Implementación patrón Singleton
    public static ConnectionToDb getInstance(){
        if(connectToDb == null)
            connectToDb = new ConnectionToDb();
        return connectToDb;
    }
    
    //Recibe una sentencias sql de tipo  insert, delete o update para actualizar una tabla
    public boolean execute(String sql){
        boolean res = false;
        try {
            Statement st = driverPostgres.createStatement();
            st.execute(sql);
            res = true;
        } catch (SQLException ex) {
            Logger.getLogger(ConnectionToDb.class.getName()).log(Level.SEVERE, null, ex);
        }
         return res;
    }
    //Recibe una sentencias sql de tipo select para consultar registros de una tabla
    public ResultSet executeQuery(String sql){
        //ResultSet: almacena de forma temporal el resultado de la consulta select y permite recorrer la información obtenida.
        ResultSet res= null;        
        try {
            Statement st = driverPostgres.createStatement();
            res = st.executeQuery(sql);
        } catch (SQLException ex) {
            Logger.getLogger(ConnectionToDb.class.getName()).log(Level.SEVERE, null, ex);
        }
         return res;
    }
}