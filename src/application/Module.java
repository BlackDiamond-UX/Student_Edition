package application;

public class Module {
    private int id_module;
    private String  nom_module;

    public int getidmodule() {
        return id_module;
    }
    public void setidmodule(int id_module) {
        this.id_module = id_module;
    }
    public String getnommodule() {
        return nom_module;
    }
    public void setnommodule(String nom_module) {
        this.nom_module = nom_module;
    }
    public Module(int id_module, String nom_module) {
        super();
        this.id_module = id_module;
        this.nom_module = nom_module;
    }

}
