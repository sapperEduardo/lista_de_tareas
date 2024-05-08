package persistence;

import java.sql.ResultSet;

public class Categoria {

    private int idCategoria;
    private String nombre;

    public Categoria(int idCategoria) {}
    public Categoria(int idCategoria, String nombre) {
        this.idCategoria = idCategoria;
        this.nombre = nombre;
    }
    public Categoria(String nombre){
        this.nombre = nombre;
        Coneccion.ejecutarModificacion("insert into Categorias values('"+nombre+"')");
        try {
            ResultSet r = Coneccion.ejecutarConsulta("select * from Categorias where nombre = '"+nombre+"'");
            if (r.next()){
                this.idCategoria = r.getInt(1);
            }
        }catch (Exception e){
            System.out.println("Error al buscar id de categoria: "+e.getMessage());
        }
    }



    public String getDatos(){
        return "Nombre: "+this.nombre+ "("+this.idCategoria+")";
    }
    public int getIdCategoria() {
        return idCategoria;
    }



}
