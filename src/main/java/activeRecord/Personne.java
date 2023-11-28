package activeRecord;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;



public class Personne {
    private int id;
    private String nom;
    private String prenom;

    public Personne(String nom, String prenom) {
        this.id = -1;
        this.nom = nom;
        this.prenom = prenom;
    }

    public String getNom() {
        return nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public static List<Personne> findAll() throws SQLException {
        Connection connect = DBConnection.getConnection();
        System.out.println("4) Recupere les personnes de la table Personne");
        String SQLPrep = "SELECT * FROM Personne;";

        PreparedStatement prep1 = connect.prepareStatement(SQLPrep);
        prep1.execute();
        ResultSet rs = prep1.getResultSet();
        // s'il y a un resultat
        List<Personne> list = new ArrayList<Personne>();
        while (rs.next()) {
            String nom = rs.getString("nom");
            String prenom = rs.getString("prenom");
            int id = rs.getInt("id");
            //System.out.println("  -> (" + id + ") " + nom + ", " + prenom);
            Personne personne = new Personne(nom, prenom);
            personne.id = id;
            list.add(personne);
        }
        connect.close();
        return list;
    }

    public static Personne findById(int id) throws SQLException {
        Connection connect = DBConnection.getConnection();
        Personne personne = null;

        String SQLPrep = "SELECT * FROM Personne WHERE ID = ?";
        PreparedStatement prep = connect.prepareStatement(SQLPrep);
        prep.setInt(1, id);
        ResultSet rs = prep.executeQuery();

        if (rs.next()) {
            String nom = rs.getString("NOM");
            String prenom = rs.getString("PRENOM");
            personne = new Personne(nom, prenom);
        }

        connect.close();
        return personne;
    }

    public static List<Personne> findByName(String nom) throws SQLException {
        Connection connect = DBConnection.getConnection();
        List<Personne> personnes = new ArrayList<>();

        String SQLPrep = "SELECT * FROM Personne WHERE NOM = ?";
        PreparedStatement prep = connect.prepareStatement(SQLPrep);
        prep.setString(1, nom);
        ResultSet rs = prep.executeQuery();

        while (rs.next()) {
            int id = rs.getInt("ID");
            String prenom = rs.getString("PRENOM");
            Personne personne = new Personne( nom, prenom);
            personnes.add(personne);
        }

        connect.close();
        return personnes;
    }

    public static void createTable() throws SQLException {
        Connection connect = DBConnection.getConnection();
        String createString = "CREATE TABLE Personne ( " + "ID INTEGER  AUTO_INCREMENT, "
                + "NOM varchar(40) NOT NULL, " + "PRENOM varchar(40) NOT NULL, " + "PRIMARY KEY (ID))";
        Statement stmt = connect.createStatement();
        stmt.executeUpdate(createString);
        System.out.println("1) creation table Personne\n");
    }

    public static void deleteTable() throws SQLException {
        Connection connect = DBConnection.getConnection();
        String drop = "DROP TABLE Personne";
        Statement stmt = connect.createStatement();
        stmt.executeUpdate(drop);
        System.out.println("9) Supprime table Personne");
    }
}
