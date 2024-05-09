package view;

import javax.print.DocFlavor;
import java.sql.SQLOutput;
import java.util.LinkedList;
import java.util.Scanner;

public class Vista {

    Scanner in = new Scanner(System.in);


    public Vista(){}

    public int mostrarMenu(){
        espacios(2);
        System.out.println("-----MENU----PRINCIPAL-----");
        System.out.println("-ver tareas (1)");
        System.out.println("-ver tareas por segmento (2)");
        System.out.println("-editar tarea (3)");
        System.out.println("-nueva tarea (4)");
        System.out.println("-terminar tarea (5)");
        System.out.println("-eliminar tarea (6)");
        System.out.println("--salir(0)");
        System.out.print("--OPCION: ");
        try {
            int opcion = in.nextInt();
            in.nextLine();
            if (0 <= opcion && opcion <= 6 ){return opcion;}
            System.out.println("-Opcion no existente!");
            return 404;
        }catch (Exception e){
            in.nextLine();
            System.out.println("-Entrada no valida!");
            return 404;
        }
    }


    public int crearCategoria(LinkedList<String> lista){
        espacios(2);
        System.out.println("---¿EXISTE--LA--CATEGORIA?---");
        System.out.println("-LISTA:-----");
        for (String datos: lista) {
            System.out.println(datos);
        }
        System.out.println("-Si (1)");
        System.out.println("-Nueva categoria (2)");
        System.out.print("--OPCION: ");
        try {
            int opcion = in.nextInt();
            if (1 <= opcion && opcion <= 2 ){return opcion;}
            System.out.println("-Opcion no existente!");
            in.nextLine();
            return 404;
        }catch (Exception e){
            in.nextLine();
            System.out.println("-Entrada no valida!");
            return 404;
        }


    }
    public int seleccionarCategoria(LinkedList<String> lista){
        espacios(2);
        System.out.println("--SELECCIONE-UNA-CATEGORIA--");
        for (String datos: lista) {
            System.out.println(datos);
        }
        System.out.println("-salir (0)");
        System.out.print("--CATEGORIA: ");
        try {
            int opcion =  in.nextInt();
            in.nextLine();
            return opcion;
        }catch (Exception e){
            in.nextLine();
            System.out.println("-Entrada no valida!");
            return 404;
        }
    }


    public int seleccionearTarea(LinkedList<String> datos){
        espacios(2);
        System.out.println("----ELEGIR--TAREA----");
        for (String datoTarea: datos) {
            System.out.println(datoTarea);
        }
        System.out.println("-Numero de tarea: ");
        try {
            int opcion =  in.nextInt();
            in.nextLine();
            return opcion;
        }catch (Exception e){
            in.nextLine();
            System.out.println("-Entrada no valida!");
            return 404;
        }
    }

    public int seleccionarCampo(){
        espacios(2);
        System.out.println("--¿QUE--QUERES--CAMBIAR?--");
        System.out.println("-Nombre (1)");
        System.out.println("-Descripcion (2)");
        System.out.println("-Categoria (3)");
        System.out.println("-Volver al menu (0)");
        System.out.print("--OPCION: ");
        try {
            int opcion =  in.nextInt();
            in.nextLine();
            if (0 <= opcion && opcion <= 3){return opcion;}
            System.out.println("-El campo no existe!");
            return 404;
        }catch (Exception e){
            in.nextLine();
            System.out.println("-Etrada no valida!");
            return 404;
        }

    }

    public String ingresarCampo(String campo, boolean primera){
        if (primera){in.nextLine();}
        espacios(1);
        System.out.println("---INGRESAR--"+campo+"---");
        System.out.print("---> ");
        String opcion = in.nextLine();
        return opcion;
    }


    public void mostrarTareas(LinkedList<String> datosTareas){
        espacios(1);
        System.out.println("----TAREAS----");
        espacios(1);
        for (String datoTarea: datosTareas) {
            System.out.println(datoTarea);
            espacios(2);
        }
    }


    public void limpiarBuffer(){
        in.nextLine();
    }

    public void espacios(int lineas){
        for (int i = 0; i < lineas; i++){
            System.out.println("");
        }
    }



}
