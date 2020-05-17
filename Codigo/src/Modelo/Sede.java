
package Modelo;


public class Sede {
    
    
    private int codigo, telefono, cant_s;
    private String Nombre, dirrecion;


    public Sede() {
    }

    public int getCant_s() {
        return cant_s;
    }

    public void setCant_s(int cant_s) {
        this.cant_s = cant_s;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public int getTelefono() {
        return telefono;
    }

    public void setTelefono(int telefono) {
        this.telefono = telefono;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String Nombre) {
        this.Nombre = Nombre;
    }

    public String getDirecion() {
        return dirrecion;
    }

    public void setDirrecion(String Dirrecion) {
        this.dirrecion = Dirrecion;
    }
    
    
    
    
}
