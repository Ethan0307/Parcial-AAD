
package Modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JOptionPane;


public class SedeBD {
    
    Conexion conSE = new Conexion();
    Connection conec;
    PreparedStatement ps;
    ResultSet rs;
    
    
    public int AgregarSede(Sede s){
        
        String sql = "INSERT INTO sedes (Nombre, dirrecion, telefono, Cant_Salas) VALUES (?,?,?,?)";
        
        try {
            
            conec = conSE.getConexion();
            ps = conec.prepareStatement(sql);
            
            ps.setString(1, s.getNombre());
            ps.setString(2, s.getDirecion());
            ps.setInt(3, s.getTelefono());
            ps.setInt(4, s.getCant_s());
            
            ps.executeUpdate();    
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al ingresar en la base de datos "+e.getMessage());
        }
        return 1;
    }
    
}
