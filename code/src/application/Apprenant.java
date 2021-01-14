package application;


public class Apprenant extends Users {
   private String reference;

	public String getReference() {
		return reference;
	}
	
	public void setReference(String reference) {
		this.reference = reference;
	}
	
	public Apprenant(String nom, String prenom, String email, String password, String reference) {
		super(nom, prenom, email, password);
		this.reference = reference;
	}
   
	public Apprenant(int idUser, String nom, String prenom, String reference) {
		super(idUser,nom, prenom);
		this.reference = reference;
	}
   
}