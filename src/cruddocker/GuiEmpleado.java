package cruddocker;

import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author exkapp
 */
public class GuiEmpleado extends GuiGeneral{

    public GuiEmpleado() {
        this.setTitle("CRUD EMPLEADOS");
        modelo.setColumnIdentifiers(new Object[]{"ID","NOMBRE","APELLIDO"});
        tData.setModel(modelo);
    }
    
    
    
    private DaoEmpleado myDao = new DaoEmpleado();
    private Empleado e = new Empleado();
    
    
    @Override
    protected void insert() {
        e.setId(Integer.parseInt(txtId.getText()));
        e.setNombre(txtNombre.getText());
        e.setApellido(txtApellido.getText());
        if (myDao.insert(e))
            JOptionPane.showMessageDialog(null, "Registro agregado");
        else
            JOptionPane.showMessageDialog(null, "¡ERROR!");
    }

    @Override
    protected void update() {
        e.setId(Integer.parseInt(txtId.getText()));
        e.setNombre(txtNombre.getText());
        e.setApellido(txtApellido.getText());
        if (myDao.update(e))
            JOptionPane.showMessageDialog(null, "Registro actualizado");
        else
            JOptionPane.showMessageDialog(null, "¡ERROR!");
    }

    @Override
    protected void delete() {
        e.setId(Integer.parseInt(txtId.getText()));
        if (myDao.delete(e))
            JOptionPane.showMessageDialog(null, "Registro eliminado");
        else
            JOptionPane.showMessageDialog(null, "¡ERROR!");
    }

    @Override
    protected void readOne() {
        e.setId(Integer.parseInt(txtId.getText()));
        ResultSet data = myDao.readOne(e);
        
        try{
            while(data.next()){
                modelo.addRow(new Object[]{data.getInt(1), data.getString(2), data.getString(3)});
            }
        }catch(SQLException ex){
            
        }
    }    
    

    @Override
    protected void readAll() {
        ResultSet data = myDao.readAll();
        try{
            while(data.next()){
                modelo.addRow(new Object[]{data.getInt(1), data.getString(2), data.getString(3)});
            }
        }catch(SQLException ex){
            
        }
    }
    
}
