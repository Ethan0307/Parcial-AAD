
package Controlador;

import Modelo.Sede;
import Modelo.SedeBD;
import Vista.Pestaña;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Iterator;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;


public class SedeControlador implements ActionListener {
    
    SedeBD seBD = new SedeBD();
    Sede se = new Sede();
    Pestaña pe = new Pestaña();
    DefaultTableModel modelo = new DefaultTableModel();

    public SedeControlador(Pestaña pe) {
        
        this.pe = pe;
        this.pe.Crear.addActionListener(this);
        this.pe.Modificar.addActionListener(this);
        this.pe.Guardar.addActionListener(this);
        this.pe.Eliminar.addActionListener(this);
        this.pe.Buscar.addActionListener(this);
        this.pe.Listar.addActionListener(this);
        listar_Se(pe.jTable1);
           
    }
    
    @Override
    public void actionPerformed(ActionEvent ae) {
        
        if(ae.getSource() == pe.Crear){
            
            agregarSede();
            limpiarText();
            limpiarTabal();
            listar_Se(pe.jTable1);
        }
        
        if(ae.getSource() == pe.Modificar){
            
            int fila = pe.jTable1.getSelectedRow();
            
            if(fila == -1){
                JOptionPane.showMessageDialog(null, "Debe seleccionar una fila");
            }else{
                
                String name = pe.jTable1.getValueAt(fila, 1).toString();
                String dirre = pe.jTable1.getValueAt(fila, 2).toString();
                int tel = Integer.parseInt((String) pe.jTable1.getValueAt(fila, 3).toString());
                int ca_s = Integer.parseInt((String) pe.jTable1.getValueAt(fila, 4).toString());
                
                pe.Nombre_Se.setText(name);
                pe.Dirrecion_se.setText(dirre);
                pe.Telefono_Se.setText(""+tel);
                pe.Cant_Sal.setValue(ca_s);
                
                
            }
            
        }
        
        if(ae.getSource() == pe.Guardar){
           
                int fila = pe.jTable1.getSelectedRow();
                if(fila != -1){
                int cod = Integer.parseInt((String) pe.jTable1.getValueAt(fila, 0).toString());
                Actualizar_Se(cod);
                limpiarTabal();
                listar_Se(pe.jTable1);
                limpiarText(); 
                }
                
                
  
        }
        
        if(ae.getSource() == pe.Eliminar){
            
          int fila = pe.jTable1.getSelectedRow();
           
           if(fila == -1){
               JOptionPane.showMessageDialog(null, "Debe seleccionar un usuario");
           }else{
               
              int c = Integer.parseInt((String) pe.jTable1.getValueAt(fila, 0).toString());
              int res = JOptionPane.showConfirmDialog(null, "Desea eliminar esta sede");
              
              if(res == JOptionPane.YES_OPTION){
                  int r = seBD.delete_se(c);
                  
                  if(r == 1){
                      JOptionPane.showMessageDialog(null, "Sede eliminada");
                      limpiarTabal();
                      listar_Se(pe.jTable1);
                  }
              }
               
           }  
            
        }
        
        if(ae.getSource() == pe.Buscar){
            String codi = pe.Cod.getText();
            if(codi.equals("")){
                JOptionPane.showMessageDialog(null, "Ingrese un codigo");
            }else{
                limpiarTabal();
                int co = Integer.parseInt(pe.Cod.getText());
                Buscar_Se(pe.jTable1, co);
                
            }
            
        }
        
        if(ae.getSource() == pe.Listar){
            limpiarTabal();
            listar_Se(pe.jTable1);
        }
        
        
    }
    
    public void agregarSede(){
      boolean s; 
      s = Verificar();
    
      if (s == true){
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
    
    public void listar_Se(JTable table){
        
        modelo = (DefaultTableModel) table.getModel();
        
        ArrayList <Sede> lista_se = seBD.listar_se();
        
        Object [] object = new Object[5];
        
        for(int i = 0; i<lista_se.size(); i++){
            object[0] = lista_se.get(i).getCodigo();
            object[1] = lista_se.get(i).getNombre();
            object[2] = lista_se.get(i).getDirecion();
            object[3] = lista_se.get(i).getTelefono();
            object[4] = lista_se.get(i).getCant_s();
            
            modelo.addRow(object);
        }
        
        pe.jTable1.setModel(modelo);
    }
    
    public void Buscar_Se(JTable table, int cod){
        boolean s = true;
        modelo = (DefaultTableModel) table.getModel();
        
        ArrayList <Sede> lista_se = seBD.listar_se();
        
        Iterator it = lista_se.iterator();
        
        while(it.hasNext() && s == true){
            se = (Sede)it.next();
            if(se.getCodigo() == cod){
                s = false;
            }
        }
        
        if(s == false){
            
            ArrayList <Sede> Busca_se = seBD.Buscar_se(cod);
            
           Object [] object = new Object[5];
        
        for(int i = 0; i<Busca_se.size(); i++){
            object[0] = Busca_se.get(i).getCodigo();
            object[1] = Busca_se.get(i).getNombre();
            object[2] = Busca_se.get(i).getDirecion();
            object[3] = Busca_se.get(i).getTelefono();
            object[4] = Busca_se.get(i).getCant_s();
            
            modelo.addRow(object);
        }
        
        pe.jTable1.setModel(modelo); 
            
        }else{
            JOptionPane.showMessageDialog(null, "Codigo invalido");
            pe.Cod.setText("");
        }
        
        
    }
    
    public void Actualizar_Se(int id){
        
      boolean s;
      s = Verificar();
      if(s == true){
      String name = pe.Nombre_Se.getText(), dire = pe.Dirrecion_se.getText();
      int tel = Integer.parseInt(pe.Telefono_Se.getText());
      int cant_s = (Integer) pe.Cant_Sal.getValue();
          
          se.setCodigo(id);
          se.setNombre(name);
          se.setDirrecion(dire);
          se.setTelefono(tel);
          se.setCant_s(cant_s);
          
          int r = seBD.Actualizar_Se(se);
          
          if(r == 1){
              JOptionPane.showMessageDialog(null, "Sede actualizada correctamente");
          }
          
      }
        
        
    }
    
    void limpiarTabal(){
        
        for(int i = 0; i<pe.jTable1.getRowCount(); i++){
            modelo.removeRow(i);
            i--;
        }
        
    }
    
    
    
    void limpiarText(){
        pe.Nombre_Se.setText("");
        pe.Dirrecion_se.setText("");
        pe.Telefono_Se.setText("");
        pe.Cant_Sal.setValue(0);
    }
    
    public boolean Verificar(){
        
        boolean sw = true;
        int ca;
        if(pe.Nombre_Se.getText().isEmpty() || pe.Dirrecion_se.getText().isEmpty() || pe.Telefono_Se.getText().isEmpty() || (ca = (Integer) pe.Cant_Sal.getValue()) == 0){
            JOptionPane.showMessageDialog(null, "Ingrese todos los campos");
            sw = false;
        }
       
        return sw;
        
    }
    
    
}
