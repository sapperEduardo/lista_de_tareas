package persistence;

import java.security.PublicKey;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Coneccion {

    private static String user = "usuarioA";
    private static String password = "123";
    private static String url = "jdbc:sqlserver://DESKTOP-1VRJ5PA:1433;databaseName=ListaTareas;encrypt=true;trustServerCertificate=true";
    private static Connection conn;
    private static boolean estado;

    public static void conectar() {
        try {
            conn = DriverManager.getConnection(url, user, password);
            estado = true;
            if (conn != null) {
                System.out.println("Conexión exitosa");
            }
        } catch (SQLException e) {
            System.out.println("Error al conectar a la base de datos: " + e.getMessage());
        }
    }

    public static ResultSet ejecutarConsulta(String consulta) {
        ResultSet resultado = null;
        try {
            PreparedStatement statement = conn.prepareStatement(consulta);
            resultado = statement.executeQuery();
        } catch (SQLException e) {
            System.out.println("Error al ejecutar consulta: " + e.getMessage());
        }
        return resultado;
    }
    public static int ejecutarModificacion(String consulta){
        int filas = 0;
        PreparedStatement statement = null;
        try {
            statement = conn.prepareStatement(consulta);
            filas = statement.executeUpdate();
        }catch (SQLException e){
            System.out.println("Error al moidficar datos: "+e.getMessage());
        }
        return filas;
    }


    public static void cerrarConexion() {
        try {
            if (conn != null) {
                estado = false;
                conn.close();
                System.out.println("Conexión cerrada correctamente");
            }
        } catch (SQLException e) {
            System.out.println("Error al cerrar conexión: " + e.getMessage());
        }
    }

    public static boolean getEstado(){
        return estado;
    }

    public static String obtenerFecha(){
        try {
            ResultSet r = ejecutarConsulta("SELECT fechaInicio FROM Tareas");
            if (r.next()){
                return r.getString("fechaInicio");
            }
        }catch (Exception e){
            System.out.println("Error: "+e.getMessage());
        }
        return "";
    }



}

