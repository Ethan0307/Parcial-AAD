
package Controlador;

import Modelo.Sede;
import Modelo.SedeBD;
import Vista.Pestaña;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;


public class SedeControlador implements ActionListener {
    
    SedeBD seBD = new SedeBD();
    Sede se = new Sede();
    Pestaña pe = new Pestaña();
    DefaultTableModel modelo = new DefaultTableModel();

    public SedeControlador(Pestaña pe) {
        
        this.pe = pe;
        this.pe.Crear.addActionListener(this);
        
         
    }
    
    @Override
    public void actionPerformed(ActionEvent ae) {
        
        if(ae.getSource() == pe.Crear){
            
            agregarSede();
            
        }
        
        
    }
    
    public void agregarSede(){
        
      String name = pe.Nombre_Se.getText(), dire = pe.Dirrecion_se.getText();
      int tel = Integer.parseInt(pe.Telefono_Se.getText());
      int cant_s = (Integer) pe.Cant_Sal.getValue();
    
      se.setNombre(name);
      se.setDirrecion(dire);
      se.setTelefono(tel);
      se.setCant_s(cant_s);
      
      int r = seBD.AgregarSede(se);
      
      if(r == 1){
          JOptionPane.showMessageDialog(null, "Sede agregada correctamente");
      }
        
    }
    
    
}
