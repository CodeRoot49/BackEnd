
package com.access.DBCONNECTION;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author mili
 */
public class DBCONNECTION {
    /*Coonection MYSQL ONLINE 
    private String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    private String JDBC_DB = "b2cktote1e918dx6vjfa";
    private String JDBC_USER = "uns7n0gdnu3mboec";
    private String JDBC_PASS = "sL1prqougxYZkuwUR5Tl";
    private String JDBC_URL = "jdbc:mysql://uns7n0gdnu3mboec:sL1prqougxYZkuwUR5Tl@b2cktote1e918dx6vjfa-mysql.services.clever-cloud.com:3306/b2cktote1e918dx6vjfa";
   */
    
    
    //access_inspection?useTimeZone=true&serverTimezone=UTC&autoReconnect=true&useSSL=false
    
    //root
    //025940
    //localhost:3306
    private String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    private String JDBC_DB = "access_inspection?useTimeZone=true&serverTimezone=UTC&autoReconnect=true&useSSL=false";
    private String JDBC_USER = "root";
    private String JDBC_PASS = "025940";
    private String JDBC_URL = "jdbc:mysql://localhost:3306/" + JDBC_DB;
    // esta variable va a guardar la conecion
    Connection conn = null;
    
    public DBCONNECTION(){
        try{
            // Obtenemos el Driver para MySQL
            Class.forName(JDBC_DRIVER);
            conn = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASS);
            if( conn != null){
                // Si conn es diferente de null es porque se realizao la conexion
                System.out.println("Conexion Database ["+ conn +"] OK");
            }
        }catch (SQLException e){
            System.out.println("Fallo en la conexion");
            System.out.println(e.getMessage());
        }catch (ClassNotFoundException e){
            System.err.println("Error al cargar el Driver JDBC");
            System.err.println(e.getMessage());
        }
    }
    public Connection getConnetion(){
        return conn;
    }
    public void Disconnect(){
        System.err.println("Cerrando Conexion... ["+ conn +"] OK");
        if(conn != null){
            try{
                System.err.println("Desconectado de ["+JDBC_DB+"] OK");
                conn.close();
            }catch (SQLException e){
                System.err.println("Error al desconectar");
                System.err.println(e);
            }
        }
    }
    public static void main(String[] args) {
        DBCONNECTION bd = new DBCONNECTION();
        bd.Disconnect();
    }
}
