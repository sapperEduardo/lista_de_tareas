package persistence;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Tarea {
    private int idTarea;
    private int idCategoria;
    private String nombre;
    private String categoria;
    private String descripcion;
    private String estado;
    private String fechaInicio;
    private String fechaVence;

    public Tarea() {}
    public Tarea(int idTarea) {
        this.idTarea = idTarea;
    }
    public Tarea(int idTarea, int idCategoria, String nombre, String descripcion, String categoria, String estado, String fechaInicio, String fechaVence) {
        this.idTarea = idTarea;
        this.idCategoria = idCategoria;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.categoria = categoria;
        this.estado = estado;
        this.fechaInicio = fechaInicio;
        this.fechaVence = fechaVence;
    }
    public Tarea(String nombre, String descripcion, int idCategoria){
        Coneccion.ejecutarModificacion("insert into Tareas (nombre, descripcion, idCategoria) " +
                "values('"+nombre+"','"+descripcion+"',"+idCategoria+")");
        try {
            ResultSet r = Coneccion.ejecutarConsulta("select * from Tareas where nombre = '"+nombre+"'");
            if (r.next()){
                this.idTarea = r.getInt(1);
            }
        }catch (Exception e){
            System.out.println("Error al cargar nueva taria: "+e.getMessage());
        }
        obtenerDatos();
    }


    public void obtenerDatos(){
        try {
            ResultSet r = Coneccion.ejecutarConsulta("select * from Tareas where idTarea = "+this.idTarea+";");
            if (r.next()){
                this.nombre = r.getString("nombre");
                this.descripcion = r.getString("descripcion");
                this.estado = (r.getBoolean("completada"))? "completada":"pendiente";
                this.idCategoria = r.getInt("idCategoria");
                this.fechaInicio = r.getString("fechaInicio");
                this.fechaVence = r.getString("fechaVence");
                try {
                    ResultSet rcat = Coneccion.ejecutarConsulta("select nombre from Categorias where idCategoria = "+idCategoria+";");
                    if (rcat.next()){
                        this.categoria = rcat.getString("nombre");
                    }
                }catch (SQLException e){
                    System.out.println("Error en categoria: "+e.getMessage());
                }
            }
        }catch (Exception e){
            System.out.println("Error al obtener datos de la tarea numero: "+e.getMessage());
        }
    }

    public void terminarTarea(){
        try {
            Coneccion.ejecutarModificacion("update Tareas set completada = 1 where idTarea = "+this.idTarea);
        }catch (Exception e){
            System.out.println("Error al terminar tarea: "+e.getMessage());
        }
        obtenerDatos();
    }
    public void eliminarTarea(){
        try{
            Coneccion.ejecutarModificacion("delete Tareas where idTarea = "+this.idTarea);
        }catch (Exception e){
            System.out.println("Error al eliminar taria: "+e.getMessage());
        }
    }

    public void cambiarNombre(String nombre){
        Coneccion.ejecutarModificacion("update Tareas set nombre = '"+nombre+"' where idTarea = "+this.idTarea);
        obtenerDatos();
    }
    public void cambiarDescripcion(String descripcion){
        Coneccion.ejecutarModificacion("update Tareas set descripcion = '"+descripcion+"' where idTarea = "+this.idTarea);
        obtenerDatos();
    }
    public void cambiarCategoria(int idCategoria){
        Coneccion.ejecutarModificacion("update Tareas set idCategoria = "+idCategoria+" where idTarea = "+this.idTarea);
        obtenerDatos();
    }


    public String getDatos(){
        String datos = "Nombre: "+this.nombre+"\nEstado: "+this.estado+"\nDescripcion: "+this.descripcion+"\nCategoria: "+
                this.categoria+"\nFecha Inicio: "+this.fechaInicio+"\nFecha de vencimiento: "+this.fechaVence;
        return datos;
    }
    public String getNombreId(){
        String nombreId = "-- "+this.nombre+" ("+this.idTarea+") ";
        return nombreId;
    }
    public int getIdTarea() {
        return idTarea;
    }

    public int getIdCategoria() {
        return this.idCategoria;
    }
}
