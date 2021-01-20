package application;

public class Specialite {
	private int id_specialite;
	private String  nom_specialite;
	private int  id_module;

	public int getidspecialite() {
		return id_specialite;
	}
	public void setidspecialite(int id_specialite) {
		this.id_specialite = id_specialite;
	}
	public String getnomspecialite() {
		return nom_specialite;
	}
	public void setnomspecialite(String nom_specialite) {
		this.nom_specialite = nom_specialite;
	}
	public int getidmodule() { return id_module; }
	public void setidmodule(int id_module) {
		this.id_module = id_module;
	}

	public Specialite(int id_specialite, String nom_specialite,int id_module) {
		super();
		this.id_specialite = id_specialite;
		this.nom_specialite = nom_specialite;
		this.id_module = id_module;
	}


}