package TestFilm;

import activeRecord.Personne;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class TestFilm {
    @BeforeEach
    void setUp() throws SQLException {
        Film.createTable();
        (new Film("TOTO",1)).save();
        (new Film("TATA",2)).save();
    }

    @AfterEach
    void end() throws SQLException {
        Film.deleteTable();
    }

    @Test
    public void testFindByIdExistingPersonne() throws SQLException {
        Filù film = Film.findById(1);
        assertNotNull(personne);
        assertEquals("Spielberg", film.getTitre());
        assertEquals("Steven", film.getId_real());
    }

    @Test
    public void testFindByIdNonExistingPersonne() throws SQLException {
        Film film = Film.findById(100);
        assertNull(film);
    }

}