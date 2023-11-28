package activeRecord;

import java.sql.SQLException;
import java.util.List;

public class Main {
    static void main(String[] args)throws SQLException {
        List<Personne> list = Personne.findAll();
        System.out.println(list);
    }
}
