package fr.eni.javaee.bo;

public class UserAccount {	
	
	public int noUtilisateur;
	public String pseudo;
	public String nom;
	public String prenom;
	public String email;
	
	public String telephone;
	public String rue;
	public String code_postal;
	public String ville;
	public String mot_de_passe;
	public Integer credit;
	public boolean administrateur;
	
	
	
	public UserAccount(int noUtilisateur) {
	
		this.noUtilisateur = noUtilisateur;
	}
	
	
	
	public String getPseudo() {
		return pseudo;
	}
	public void setPseudo(String pseudo) {
		this.pseudo = pseudo;
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public String getPrenom() {
		return prenom;
	}
	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getTelephone() {
		return telephone;
	}
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
	public String getRue() {
		return rue;
	}
	public void setRue(String rue) {
		this.rue = rue;
	}
	public String getCode_postal() {
		return code_postal;
	}
	public void setCode_postal(String code_postal) {
		this.code_postal = code_postal;
	}
	public String getVille() {
		return ville;
	}
	public void setVille(String ville) {
		this.ville = ville;
	}
	public String getMot_de_passe() {
		return mot_de_passe;
	}
	public void setMot_de_passe(String mot_de_passe) {
		this.mot_de_passe = mot_de_passe;
	}
	public Integer getCredit() {
		return credit;
	}
	public void setCredit(Integer credit) {
		this.credit = credit;
	}
	public boolean isAdministrateur() {
		return administrateur;
	}
	public void setAdministrateur(boolean administrateur) {
		this.administrateur = administrateur;
	}
	
	
	
	
	public int getNoUtilisateur() {
		return noUtilisateur;
	}



	@Override
	public String toString() {
		return "UserAccount [pseudo=" + pseudo + ", nom=" + nom + ", prenom=" + prenom + ", email=" + email
				+ ", telephone=" + telephone + ", rue=" + rue + ", code_postal=" + code_postal + ", ville=" + ville
				+ ", mot_de_passe=" + mot_de_passe + ", credit=" + credit + ", administrateur=" + administrateur + "]";
	}
	
	public UserAccount(String pseudo, String nom, String prenom, String email, String telephone, String rue,
			String code_postal, String ville, String mot_de_passe, Integer credit, boolean administrateur) {
		super();
		this.pseudo = pseudo;
		this.nom = nom;
		this.prenom = prenom;
		this.email = email;
		this.telephone = telephone;
		this.rue = rue;
		this.code_postal = code_postal;
		this.ville = ville;
		this.mot_de_passe = mot_de_passe;
		this.credit = credit;
		this.administrateur = administrateur;
	}
	public UserAccount(String pseudo, String nom, String prenom, String email, String telephone, String rue,
			String code_postal, String ville) {
		super();
		this.pseudo = pseudo;
		this.nom = nom;
		this.prenom = prenom;
		this.email = email;
		this.telephone = telephone;
		this.rue = rue;
		this.code_postal = code_postal;
		this.ville = ville;
	}



	public UserAccount(int noUtilisateur, String pseudo, String nom, String prenom, String email, String telephone) {
		
		this.noUtilisateur = noUtilisateur;
		this.pseudo = pseudo;
		this.nom = nom;
		this.prenom = prenom;
		this.email = email;
		this.telephone = telephone;
	}



	public UserAccount() {
		
	}



	
	
	
}
