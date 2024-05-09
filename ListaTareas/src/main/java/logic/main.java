package logic;

import java.sql.ResultSet;

import persistence.Categorias;
import persistence.Coneccion;
import persistence.ListaTareas;
import persistence.Tarea;
import view.Vista;

import javax.swing.table.TableRowSorter;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLOutput;
import java.util.LinkedList;
import java.util.Scanner;

public class main {
    public static void main(String[] args) {


        Coneccion.conectar();

        Vista v = new Vista();

        int respuesta = v.mostrarMenu();

        ListaTareas listaTareas = new ListaTareas();
        Categorias categorias = new Categorias();

        while (respuesta != 0) {
            while (respuesta == 404) {
                respuesta = v.mostrarMenu();
                if (respuesta == 0) {
                    break;
                }
            }

            switch (respuesta) {
                case 1:
                    LinkedList<String> datosTareas = listaTareas.getListaTareas();
                    v.mostrarTareas(datosTareas);
                    break;

                case 2:
                    int idCategoria = seleccionarCategoria(v, categorias);
                    if (idCategoria == 0) {break;}
                    datosTareas = listaTareas.filtrarTareas(idCategoria);
                    v.mostrarTareas(datosTareas);
                    break;

                case 3:
                    datosTareas = listaTareas.getNombresTareas();
                    int idTarea = v.seleccionearTarea(datosTareas);
                    while (idTarea == 404 || !listaTareas.existeTarea(idTarea)) {
                        if (idTarea != 404) {
                            System.out.println("-La tarea no existe!");
                        }
                        idTarea = v.seleccionearTarea(datosTareas);
                    }
                    Tarea tarea = listaTareas.getTarea(idTarea);
                    int campo = v.seleccionarCampo();
                    while (campo == 404) {
                        campo = v.seleccionarCampo();
                    }
                    if (campo == 0) {break;}

                    if (campo == 1) {
                            String nombre = v.ingresarCampo("NOMBRE", false);
                            while (nombre.equals("")) {
                                System.out.println("Escribe algo!");
                                nombre = v.ingresarCampo("NOMBRE", false);
                            }
                            tarea.cambiarNombre(nombre);
                            System.out.println("Cambios efectuados!");
                            break;
                    } else if (campo == 2) {
                        String descripcion = v.ingresarCampo("DESCRIPCION", false);
                        while (descripcion.equals("")) {
                            System.out.println("Escribe algo!");
                            descripcion = v.ingresarCampo("DESCRIPCION", false);
                        }
                        tarea.cambiarDescripcion(descripcion);
                        System.out.println("Cambios efectuados!");
                        break;
                    }else {
                        LinkedList<String> listaCategorias = categorias.getListaCategorias();
                        int opcion = v.crearCategoria(listaCategorias);
                        while (opcion == 404) {
                            opcion = v.crearCategoria(listaCategorias);
                        }

                        if (opcion == 1) {
                            int cat = seleccionarCategoria(v, categorias);
                            if (cat == 0) {break;}
                            tarea.cambiarCategoria(cat);
                            System.out.println("Cambios efectuados!");
                            break;
                        } else if (opcion == 2) {
                            v.limpiarBuffer();
                            String nombre_cat = ingresarCampo("NOMBRE-DE-CATEGORIA", v);

                            int id_nueva_cat = categorias.agregarCategoria(nombre_cat);
                            tarea.cambiarCategoria(id_nueva_cat);
                            System.out.println("Cambios efectuados!");
                            break;
                        }
                        break;
                    }

                case 4:
                    String[] datos_tarea = crearTarea(v, categorias);
                    String nombre = datos_tarea[0];
                    String descripcion = datos_tarea[1];
                    int idCat = Integer.parseInt( datos_tarea[2]);
                    Tarea t = new Tarea(nombre, descripcion, idCat);
                    listaTareas.agregarTarea(t);
                    System.out.println("Tarea agregada!");
                    break;

                case 5:
                    int idT = v.seleccionearTarea(listaTareas.getNombresTareas());
                    while (idT == 404 || !listaTareas.existeTarea(idT)) {
                        if (idT != 404) {
                            System.out.println("-La tarea no existe!");
                        }
                        idT = v.seleccionearTarea(listaTareas.getNombresTareas());
                    }
                    listaTareas.getTarea(idT).terminarTarea();
                    System.out.println("Tarea completada!");
                    break;

                case 6:
                    int id = v.seleccionearTarea(listaTareas.getNombresTareas());
                    while (id == 404 || !listaTareas.existeTarea(id)) {
                        if (id != 404) {
                            System.out.println("-La tarea no existe!");
                        }
                        id = v.seleccionearTarea(listaTareas.getNombresTareas());
                    }
                    Tarea T = listaTareas.getTarea(id);
                    T.eliminarTarea();
                    listaTareas.eliminarTarea(T);
                    System.out.println("Tarea eliminada!");
                    break;
            }

            respuesta = v.mostrarMenu();

        }


    }



    private static String[] crearTarea(Vista v, Categorias categorias){
        String[] salida = new String[3];
        int idCat;
        String nombre, descripcion;
        nombre = ingresarCampo("NOMBRE", v);
        descripcion = ingresarCampo("DESCRIPCION", v);
        int respuesta = v.crearCategoria(categorias.getListaCategorias());
        while (respuesta == 404){
            respuesta = v.crearCategoria(categorias.getListaCategorias());
        }

        if (respuesta == 1){
            idCat = seleccionarCategoria(v, categorias);
        }else{
            v.limpiarBuffer();
            String nombre_cat = ingresarCampo("NOMBRE-DE-CATEGORIA", v);
            idCat = categorias.agregarCategoria(nombre_cat);
        }
        salida[0] = nombre;
        salida[1] = descripcion;
        salida[2] = ""+idCat;

        return salida;

    }


    private static String ingresarCampo(String nombre, Vista v){
        String nombre_cat = v.ingresarCampo(nombre, false);
        while (nombre_cat.equals("")) {
            System.out.println("Escribe algo!");
            nombre_cat = v.ingresarCampo(nombre, false);
        }
        return nombre_cat;
    }


    private static int seleccionarCategoria(Vista v, Categorias categorias){
        LinkedList<String> listaCat = categorias.getListaCategorias();
        int idCat = v.seleccionarCategoria(listaCat);
        while (idCat == 404){
            idCat = v.seleccionarCategoria(listaCat);
        }
        if (idCat == 0){return 0;}
        while (!categorias.existeCategoria(idCat)) {
            if (idCat != 404){
                System.out.println("Seleccione una categoria existente!");
            }
            idCat = v.seleccionarCategoria(listaCat);
        }
        return idCat;
    }






}
