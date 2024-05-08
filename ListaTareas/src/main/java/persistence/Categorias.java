package persistence;

import java.sql.ResultSet;
import java.util.LinkedList;

public class Categorias {

    private LinkedList<Categoria> categorias = new LinkedList<>();

    public Categorias() {
        obtenerCategorias();
    }
    public void obtenerCategorias(){
        try {
            ResultSet r = Coneccion.ejecutarConsulta("select * from Categorias");
            while (r.next()){
                int idCategoria = r.getInt("idCategoria");
                String nombre = r.getString("nombre");
                Categoria c = new Categoria(idCategoria, nombre);
                this.categorias.add(c);
            }
        }catch (Exception e){
            System.out.println("Error al obtener categorias: "+e.getMessage());


        }
    }

    public int agregarCategoria(String nombre){
        Categoria nuevaC = new Categoria(nombre);
        this.categorias.add(nuevaC);
        int idCategoria_nueva = nuevaC.getIdCategoria();
        return idCategoria_nueva;
    }

    public LinkedList<String> getListaCategorias() {
        LinkedList<String> datos = new LinkedList<>();
        for (Categoria c: this.categorias) {
            datos.add(c.getDatos());
        }
        return datos;
    }

    public boolean existeCategoria(int idCategoria){
        for (Categoria c: this.categorias) {
            if(c.getIdCategoria() == idCategoria){
                return true;
            }
        }
        return false;
    }










}
