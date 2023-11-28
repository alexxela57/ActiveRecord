package TestPersonne;

import activeRecord.Personne;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class TestPersonne {
    @BeforeEach
    void setUp() throws SQLException {
        Personne.createTable();
        (new Personne("TOTO","toto")).save();
        (new Personne("TATA","tata")).save();
    }

    @AfterEach
    void end() throws SQLException {
        Personne.deleteTable();
    }
    @Test
    public void testFindAll() throws SQLException {
        List<Personne> personnes = Personne.findAll();
        assertEquals(2, personnes.size(),"il devrait y avoir 2 personnes");
    }

    @Test
    public void testFindByIdExistingPersonne() throws SQLException {
        Personne personne = Personne.findById(1);
        assertNotNull(personne);
        assertEquals("Spielberg", personne.getNom());
        assertEquals("Steven", personne.getPrenom());
    }

    @Test
    public void testFindByIdNonExistingPersonne() throws SQLException {
        Personne personne = Personne.findById(100);
        assertNull(personne);
    }

    @Test
    public void testFindByNameExistingPersonnes() throws SQLException {
        List<Personne> personnes = Personne.findByName("Scott");
        assertEquals(1, personnes.size());

        Personne personne = personnes.get(0);
        assertEquals("Ridley", personne.getPrenom());
    }

    @Test
    public void testFindByNameNonExistingPersonnes() throws SQLException {
        List<Personne> personnes = Personne.findByName("Smith");
        assertEquals(0, personnes.size());
    }
}