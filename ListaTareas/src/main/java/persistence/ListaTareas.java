package persistence;

import java.sql.ResultSet;
import java.sql.SQLOutput;
import java.util.LinkedList;

public class ListaTareas {

    private LinkedList<Tarea> listaTareas = new LinkedList<>();


    public ListaTareas() {
        obtenerTareas();
    }
    public void obtenerTareas() {
        if( cantidadTareas() > 0){
            try {
                ResultSet resultado = Coneccion.ejecutarConsulta("select idTarea from Tareas");
                while (resultado.next()){
                    int indice = resultado.getInt(1);
                    Tarea t = new Tarea(indice);
                    t.obtenerDatos();
                    listaTareas.add(t);
                }
            }catch (Exception e){
                System.out.println("Error al obterner tareas: "+e.getMessage());
            }
        }
    }

    public void agregarTarea(Tarea t){
        this.listaTareas.add(t);
    }

    public int cantidadTareas(){
        int cantidad = 0;
        try {
            ResultSet resultado =  Coneccion.ejecutarConsulta("select count(*) from Tareas");
            if (resultado.next()){
                cantidad = resultado.getInt(1);
            }
        }catch (Exception e){
            System.out.println("Error: "+e.getMessage());
        }
        return cantidad;
    }

    public LinkedList<String> filtrarTareas(int idCategoria) {
        LinkedList<Tarea> tareasFiltradas = new LinkedList<>();
        for (Tarea tarea : this.listaTareas) {
            if (tarea.getIdCategoria() == idCategoria) {
                tareasFiltradas.add(tarea);
            }
        }
        LinkedList<String> datos = new LinkedList<>();
        for (Tarea t: tareasFiltradas) {
            datos.add(t.getDatos());
        }
        return datos;
    }

    public void eliminarTarea(Tarea t){
        this.listaTareas.remove(t);
    }

    public LinkedList<String> getNombresTareas(){
        LinkedList<String> datos = new LinkedList<>();
        for (Tarea t: this.listaTareas) {
            datos.add(t.getNombreId());
        }
        return datos;
    }

    public LinkedList<String> getListaTareas() {
        LinkedList<String> datos = new LinkedList<>();
        for (Tarea t: this.listaTareas) {
            datos.add(t.getDatos());
        }
        return datos;
    }

    public Tarea getTarea(int idTarea){
        for (Tarea t: this.listaTareas) {
            if (t.getIdTarea() == idTarea){
                return t;
            }
        }
        return new Tarea();
    }

    public boolean existeTarea(int idTarea){
        for (Tarea t: this.listaTareas) {
            if(t.getIdTarea() == idTarea){
                return true;
            }
        }
        return false;
    }



}
