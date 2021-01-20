package application;

public class Etudiant {
	private int id_etudiant;
	private String  nom_etudiant;
	private String  prenom_etudiant;
	private String  email;
	private int telephone;
	private int id_specialite;

	public int getid() {
		return id_etudiant;
	}
	public void setid(int id_etudiant) {
		this.id_etudiant = id_etudiant;
	}
	public String getnom() {
		return nom_etudiant;
	}
	public void setnom(String nom_etudiant) {
		this.nom_etudiant = nom_etudiant;
	}
	public String getprenom() {
		return prenom_etudiant;
	}
	public void setprenom(String prenom_etudiant) {
		this.prenom_etudiant = prenom_etudiant;
	}
	public String getemail() { return email; }
	public void setemail(String email) {
		this.email = email;
	}
	public int getelephone() {
		return telephone;
	}
	public void settelephone(int telephone) {
		this.telephone = telephone;
	}
	public int getidspecialite() { return id_specialite; }
	public void setidspecialite(int id_specialite) {
		this.id_specialite = id_specialite;
	}
	public Etudiant() {
		super();
	}
	public Etudiant(int id, String nom_etudiant, String prenom_etudiant,String  email,int telephone,int id_specialite) {
		this.id_etudiant = id_etudiant;
		this.nom_etudiant = nom_etudiant;
		this.prenom_etudiant = prenom_etudiant;
		this.email = email;
		this.telephone = telephone;
		this.id_specialite = id_specialite;
	}


}