package TestDbconnection;

import activeRecord.DBConnection;
import java.sql.Connection;
import java.sql.SQLException;

public class DBConnectionTest {

    public static void main(String[] args) {
        try {
            // Test de l'obtention de la connexion unique
            Connection conn1 = DBConnection.getConnection();
            Connection conn2 = DBConnection.getConnection();

            System.out.println("Connexion 1 : " + conn1);
            System.out.println("Connexion 2 : " + conn2);
            System.out.println("Les deux connexions référencent-elles le même objet ? " + (conn1 == conn2));

            // Test du changement de base de données
            String dbName2 = "secondedb";
            Connection conn3 = DBConnection.getConnection();

            System.out.println("Connexion 3 : " + conn3);
            System.out.println("Connexion 3 utilise-t-elle la deuxième base de données ? " + conn3.getCatalog().equals(dbName2));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}