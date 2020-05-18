
package Modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
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
    
    public ArrayList listar_se(){
        
        ArrayList <Sede> datos_se = new ArrayList<>();
        String sql = "SELECT * FROM sedes";
        
        try {
            
           conec = conSE.getConexion();
           ps = conec.prepareStatement(sql);
           rs = ps.executeQuery();
           
           while(rs.next()){
               Sede s = new Sede();
               
               s.setCodigo(rs.getInt("Codigo"));
               s.setNombre(rs.getString("Nombre"));
               s.setDirrecion(rs.getString("dirrecion"));
               s.setTelefono(rs.getInt("telefono"));
               s.setCant_s(rs.getInt("Cant_Salas"));
               
               datos_se.add(s);
           }
            
        } catch (Exception e) {
            
            JOptionPane.showMessageDialog(null, "Error al listar "+e.getMessage());
        }
        
        
        return datos_se;
           
    }
    
    public int Actualizar_Se(Sede se){
        
       String sql = "UPDATE sedes SET Nombre = ?, dirrecion = ?, telefono=?, Cant_Salas = ? WHERE Codigo = ?";
       
        try {
            
           conec = conSE.getConexion();
           ps = conec.prepareStatement(sql);
            
           ps.setString(1, se.getNombre());
           ps.setString(2, se.getDirecion());
           ps.setInt(3, se.getTelefono());
           ps.setInt(4, se.getCant_s());
           ps.setInt(5, se.getCodigo());
           ps.executeUpdate();
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al actualizar base de datos"+e.getMessage());
        }
        
        return 1;
    }
    
    public int delete_se(int co){
        
        String sql = "DELETE FROM sedes WHERE Codigo = ?";
        
        try {
            
           conec = conSE.getConexion();
           ps = conec.prepareStatement(sql);
           ps.setInt(1, co);
            
           ps.executeUpdate();
        } catch (Exception e) {
            
            JOptionPane.showMessageDialog(null, "Error al eliminar sede "+e.getMessage());
            
        }
        return 1;
    }
    
    public ArrayList Buscar_se(int cod){
        
        ArrayList <Sede> datos_se = new ArrayList<>();
        String sql = "SELECT * FROM sedes WHERE Codigo = ?";
        
        try {
            
           conec = conSE.getConexion();
           ps = conec.prepareStatement(sql);
           ps.setInt(1, cod);
           rs = ps.executeQuery();
           
           while(rs.next()){
               Sede s = new Sede();
               
               s.setCodigo(rs.getInt("Codigo"));
               s.setNombre(rs.getString("Nombre"));
               s.setDirrecion(rs.getString("dirrecion"));
               s.setTelefono(rs.getInt("telefono"));
               s.setCant_s(rs.getInt("Cant_Salas"));
               
               datos_se.add(s);
           }
            
        } catch (Exception e) {
            
            JOptionPane.showMessageDialog(null, "Error al listar "+e.getMessage());
        }
        
        
        return datos_se;
           
    }
    
}
