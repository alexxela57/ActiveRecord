package activeRecord;

public class Film {
    private String titre;
    private int id;
    private int id_real;

    public Film(String titre, int id) {
        this.titre = titre;
        this.id = id;
        this.id_real = -1;
    }


}
