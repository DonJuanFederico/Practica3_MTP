package com.mtp_practica3.gestor;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;


/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Juan Federico García
 */

/**
 * TIPOS DE MÉTODOS: 
 * a) getTodasEnfermedades
 * b) getTipoEnfermedad(String nombreEnfermedad)
 * c) IniciarSesion(String dniSelec, String nombreSelec, String sexoSelec, int edadSelec)
 * d) getTiposZombies(String nombreZombie)
 * e) getLetalidad(String nombreArma)
 * @author ppere
 */

public class Gestor {
    String driver = "org.sqlite.JDBC";
        String url = "jdbc:sqlite:zombies.db";

        Statement statement = null;
        Connection conexion = null;
        ResultSet resultados = null;
        
        boolean dniB ;
        boolean nomB ; 
        boolean sexB ;
        boolean edadB;
        
    public void getTodasEnfermedades(){
        try {
            Class.forName(driver);
            conexion = DriverManager.getConnection(url);
            statement = conexion.createStatement();
           resultados = statement.executeQuery("SELECT * FROM enferm");
           System.out.println("TIPO DE ENFERMEDAD            IDENTIFICACIÓN DE CURA\n"
                             + " ====================================================== ");
           while (resultados.next()) {
               System.out.println( resultados.getString("nombre") + "    =======>      " + 
                       resultados.getString("id"));
                   }
        } catch (Exception e) {
            e.printStackTrace();
        }
        
    }
         
    public void getTipoEnfermedad(String nombreEnfermedad) {
        try {
            Class.forName(driver);
            conexion = DriverManager.getConnection(url);
            statement = conexion.createStatement();
           resultados = statement.executeQuery("SELECT nombre, id FROM enfermedad LEFT JOIN cura ON cura.enfermedad = enfermedad.nombre WHERE enfermedad.nombre == '" + nombreEnfermedad + "';");
            
            while (resultados.next()) {
                System.out.println("TIPO DE ENFERMEDAD: " + resultados.getString("nombre") + "\n" +
                        "IDENTIFICACIÓN DE LA CURA: " + resultados.getString("id"));
                   }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
     public boolean IniciarSesion(String dniSelec, String nombreSelec, String sexoSelec, int edadSelec) {
        try {
            Class.forName(driver);
//          conexion = DriverManager.getConnection(url, usuario, clave);
            conexion = DriverManager.getConnection(url);
            statement = conexion.createStatement();

            resultados = statement.executeQuery("SELECT * FROM persona WHERE dni == '" +dniSelec+ "' AND nombre =='"+nombreSelec+"' AND sexo == '"+sexoSelec+"' AND edad =="+edadSelec+";");
            //SELECT * FROM persona WHERE dni == dniSelec , nombre == nombreSelec , sexo == '"+sexoSelec+"', edad ==edadSelec;
            while (resultados.next()) {
                dniB = (resultados.getString("dni") == null ? dniSelec == null : resultados.getString("dni").equals(dniSelec));
                nomB = (resultados.getString("nombre") == null ? nombreSelec == null : resultados.getString("nombre").equals(nombreSelec)); 
                sexB = (resultados.getString("sexo") == null ? sexoSelec == null : resultados.getString("sexo").equals(sexoSelec));
                edadB = resultados.getInt("edad") == edadSelec; 
             
                   }
        } catch (Exception e) {
            e.printStackTrace();
        } 
            //RETURN:
            return (dniB == true)&&(nomB==true)&&(sexB==true)&&(edadB==true); 
            }   
        
     public void getTiposZombies(String nombreZombie) {
        try {
            Class.forName(driver);
//          conexion = DriverManager.getConnection(url, usuario, clave);
            conexion = DriverManager.getConnection(url);
            statement = conexion.createStatement();

            resultados = statement.executeQuery("SELECT * FROM zombie WHERE nombre == '" + nombreZombie + "';");
            
            while (resultados.next()) {
                System.out.println("nombre: " + resultados.getString("nombre") + "\n" +
                        "Tipo de Ataque: " + resultados.getString("tipoAtaque")+ "\n" + 
                       "Nivel de Infección: "+ resultados.getString("nivelInfeccion") +  "\n" +
                        "Rapidez: "+ resultados.getString("rapidez"));
                   }
        } catch (Exception e) {
            e.printStackTrace();
        } 
        
    }
     
     public void getLetalidad(String nombreArma) {

        try {
            Class.forName(driver);
            conexion = DriverManager.getConnection(url);
            statement = conexion.createStatement();
            
           resultados = statement.executeQuery("SELECT letalidad FROM arma WHERE nombre == '" + nombreArma + "';");  
            while (resultados.next()) {
                System.out.println("Letalidad: " + resultados.getString("letalidad"));
                   }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    
    
  
    
    // COMENTO LO DE JUAN (Paloma)
/**
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
    * */
}