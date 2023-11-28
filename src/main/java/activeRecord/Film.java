package activeRecord;

import java.sql.*;

public class Film {
    private String titre;
    private int id;
    private int id_real;

    public Film(String titre, int id_real) {
        this.titre = titre;
        this.id = -1;
        this.id_real = id_real;
    }

    private Film(String titre, int id, int id_real){
        this.titre = titre;
        this.id = id;
        this.id_real = id_real;
    }

    public static void createTable() throws SQLException {
        Connection connect = DBConnection.getConnection();
        String createString = "CREATE TABLE Film ( " + "ID INTEGER  AUTO_INCREMENT, "
                + "TITRE varchar(40) NOT NULL, " + "ID_REAL NUMBER(5) NOT NULL, " + "PRIMARY KEY (ID))";
        Statement stmt = connect.createStatement();
        stmt.executeUpdate(createString);
        System.out.println("1) creation table film\n");
    }

    public static void deleteTable() throws SQLException {
        Connection connect = DBConnection.getConnection();
        String drop = "DROP TABLE Film";
        Statement stmt = connect.createStatement();
        stmt.executeUpdate(drop);
        System.out.println("9) Supprime table Personne");
    }

    public void save() throws SQLException {
        if(id > -1) update(); //la personne existe dans la table donc update
        else saveNew(); //la personne n'est pas dans la table , on l'insère
    }

    private void saveNew() throws SQLException {
        Connection connect = DBConnection.getConnection();
        String SQLPrep = "INSERT INTO Film (titre, id_real) VALUES (?,?);";
        PreparedStatement prep = connect.prepareStatement(SQLPrep, Statement.RETURN_GENERATED_KEYS);
        prep.setString(1, titre);
        prep.setInt(2, id_real);
        prep.executeUpdate();
        //System.out.println("3) ajout Ridley Scott");

        // recuperation de la derniere ligne ajoutee (auto increment)
        // recupere le nouvel id
        int autoInc = -1;
        ResultSet rs = prep.getGeneratedKeys();
        if (rs.next()) {
            autoInc = rs.getInt(1);
        }
        this.id = autoInc;
    }

    private void update() throws SQLException {
        Connection connect = DBConnection.getConnection();
        String SQLprep = "update Film set titre=?, id_real=? where id=?;";
        PreparedStatement prep = connect.prepareStatement(SQLprep);
        prep.setString(1, titre);
        prep.setInt(2, id_real);
        prep.setInt(3, id);
        prep.execute();
        //System.out.println("7) Effectue modification Personne id 2");
        //System.out.println();
    }

    public static Film findById(int id) throws SQLException {
        Connection connect = DBConnection.getConnection();
        Film film = null;

        String SQLPrep = "SELECT * FROM Film WHERE ID = ?";
        PreparedStatement prep = connect.prepareStatement(SQLPrep);
        prep.setInt(1, id);
        ResultSet rs = prep.executeQuery();

        if (rs.next()) {
            String titre = rs.getString("TITRE");
            int id_rea = rs.getInt("ID_REA");
            film = new Film(titre, id_rea);
        }
        return film;
    }

    public String getTitre() {
        return titre;
    }

    public int getId_real() {
        return id_real;
    }
}
