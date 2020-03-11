package cruddocker;

import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/*
/*Autor: Carlos Alonso Escamilla Rocafuerte
/*Fecha de modificación: 05/Marzo/2020
/*Descripción:Clase tipo gui que hereda de la clase GuiGeneral, toma su forma 
/*y define cómo se implementarán los métodos abstractos de la clase, mismos
/*que nos permiten gestionar la tabla de la base de datos
*/

public class GuiEmpleado extends GuiGeneral{
    //Instncia que servirá como transporte de datos hacia la tabla
    private Empleado empleado = new Empleado();
    //Instancia que recibirá un ibjeto de tipo empleado
    private DaoEmpleado myDao = new DaoEmpleado();
    
    //Constructor de clase que asigna título a la ventana, y las cabeceras 
    public GuiEmpleado() {
        this.setTitle("CRUD EMPLEADOS");
        modelo.setColumnIdentifiers(new Object[]{"ID","NOMBRE","APELLIDO"});
        tData.setModel(modelo);
    }
    
    //Asigna al objeto de tipo empleado todos sus atributos para guardar un registro
    @Override
    protected void insertRecord() {
        empleado.setId(Integer.parseInt(txtId.getText()));
        empleado.setNombre(txtNombre.getText());
        empleado.setApellido(txtApellido.getText());
        if (myDao.insertRecord(empleado))
            JOptionPane.showMessageDialog(null, "Registro agregado");
        else
            JOptionPane.showMessageDialog(null, "¡ERROR!");
    }
    /*Asigna al objeto de tipo empleado todos sus atributos y ejectua 
    /*el método para actualizar un registro
   */
    @Override
    protected void updateRecord() {
        empleado.setId(Integer.parseInt(txtId.getText()));
        empleado.setNombre(txtNombre.getText());
        empleado.setApellido(txtApellido.getText());
        if (myDao.updateRecord(empleado))
            JOptionPane.showMessageDialog(null, "Registro actualizado");
        else
            JOptionPane.showMessageDialog(null, "¡ERROR!");
    }
    //Asigna al objeto de tipo empleado un id y ejectua el método para eliminar un registro
    @Override
    protected void deleteRecord() {
        empleado.setId(Integer.parseInt(txtId.getText()));
        if (myDao.deleteRecord(empleado))
            JOptionPane.showMessageDialog(null, "Registro eliminado");
        else
            JOptionPane.showMessageDialog(null, "¡ERROR!");
    }
    //Asigna al objeto de tipo empleado un id y ejectua el método para buscar un registro
    @Override
    protected void readOneRecord() {
        empleado.setId(Integer.parseInt(txtId.getText()));
        ResultSet data = myDao.readOneRecord(empleado);
        try{
            while(data.next()){
                //Recupera valores de la tabla según su tipo, smallint -> int, varchar ->String
                modelo.addRow(new Object[]{data.getInt(1), data.getString(2), data.getString(3)});
            }
        }catch(SQLException ex){
            
        }
    }    
    
    //Retorna todos los registros de la tabla
    @Override
    protected void readAllRecords() {
        ResultSet data = myDao.readAllRecords();
        try{
            while(data.next()){
                //Recupera valores de la tabla según su tipo, smallint -> int, varchar ->String
                modelo.addRow(new Object[]{data.getInt(1), data.getString(2), data.getString(3)});
            }
        }catch(SQLException ex){
            
        }
    }
    
}
