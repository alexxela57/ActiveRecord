package activeRecord;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static activeRecord.DBConnection.connect;

public class Personne {
    private int id;
    private String nom;
    private String prenom;

    public Personne(String nom, String prenom) {
        this.id = -1;
        this.nom = nom;
        this.prenom = prenom;
    }

    public static List<Personne> findAll() throws SQLException {
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

        return list;
    }
}
