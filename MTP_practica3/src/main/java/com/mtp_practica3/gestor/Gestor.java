package com.mtp_practica3.gestor;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Juan Federico García
 */


public class Gestor {

        String driver = "org.sqlite.JDBC";
        String url = "jdbc:sqlite:zombies.db";

//        String usuario = "mtp";
//        String clave = "mtpPass";
        
        
        
        
    public String sacarArma(String dni, String nombre, String sexo, String edad){
        String arma = "";
        Statement statement = null;
        Connection conexion = null;
        ResultSet resultados = null;

        try {
            Class.forName(driver);
            conexion = DriverManager.getConnection(url);
            statement = conexion.createStatement();
            resultados = statement.executeQuery("SELECT nombre, FROM arma \nWHERE personaPropietaria == " + dni + ";");
            arma = resultados.getString("nombre");
        } catch (ClassNotFoundException | SQLException e) {
        } finally {
            try {
                if (resultados != null) {
                    resultados.close();
                }
            } catch (SQLException ex) {
            }
            try {
                if (statement != null) {
                    statement.close();
                }
            } catch (SQLException ex) {
            }
            try {
                if (conexion != null) {
                    conexion.close();
                }
            } catch (SQLException ex) {
            }
        }
        return arma;
    }
    
    public void consultar(){
        Statement statement = null;
        Connection conexion = null;
        ResultSet resultados = null;

        try {
            Class.forName(driver);
//          conexion = DriverManager.getConnection(url, usuario, clave);
            conexion = DriverManager.getConnection(url);
            statement = conexion.createStatement();

            //statement.executeUpdate("CREATE TABLE usuarios (nombre VARCHAR(25), login VARCHAR(15), edad INT, nivelParticipacion FLOAT)");

           // statement.executeUpdate("INSERT INTO usuarios VALUES('Paloma','paloma',23, 0.64)");
           //  statement.executeUpdate("INSERT INTO usuarios VALUES('Juan','juan',38, 0.23)");
           // statement.executeUpdate("INSERT INTO usuarios VALUES('Tomñas','tomasito',28, 0.82)");

            resultados = statement.executeQuery("SELECT * FROM persona;");

            while (resultados.next()) {
                System.out.println(resultados.getString("nombre") + " " +
                        resultados.getString("dni")+ " " + 
                        resultados.getString("sexo") +  " " +
                        resultados.getString("enfermedad"));
                   }
        } catch (ClassNotFoundException | SQLException e) {
        } finally {
            try {
                if (resultados != null) {
                    resultados.close();
                }
            } catch (SQLException ex) {
            }
            try {
                if (statement != null) {
                    statement.close();
                }
            } catch (SQLException ex) {
            }
            try {
                if (conexion != null) {
                    conexion.close();
                }
            } catch (SQLException ex) {
            }
        }
    }
}