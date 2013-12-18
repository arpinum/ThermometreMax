package domaine;

public class Releve {

    public Releve() {
    }

    public Releve(double température) {
        this.température = température;
    }

    public String getId() {
        return id;
    }

    public  double température;
    private String id;
}
