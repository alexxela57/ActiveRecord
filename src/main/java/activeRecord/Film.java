package activeRecord;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class Film {
    private String titre;
    private int id;
    private int id_real;

    public Film(String titre, int id) {
        this.titre = titre;
        this.id = id;
        this.id_real = -1;
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
}
