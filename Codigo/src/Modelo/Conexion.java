
package Modelo;

import java.sql.Connection;
import java.sql.DriverManager;
import javax.swing.JOptionPane;


public class Conexion {
    
    public Conexion(){
    }
    
    public Connection getConexion (){
        Connection con = null;
        try{
              Class.forName("com.mysql.jdbc.Driver");
              con = DriverManager.getConnection("jdbc:mysql://localhost/prueba","root", "");
        }catch(Exception e){
            
            JOptionPane.showMessageDialog(null, "Error al conectarse a la base de datos"+e.getMessage());
        
        }
        return con;
    }
    
    
}
