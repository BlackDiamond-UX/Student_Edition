package application;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DbSkills implements Interface {
	
	public static Connection getConnection(){
		Connection con = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con =DriverManager.getConnection("jdbc:mysql://localhost:3306/skills", "root","");
		} catch (SQLException | ClassNotFoundException e) {
			Main.getAlert("erreur de connection", "Connection");
		}
		return con;
	}
	
	public static Users login(String email, String password) {
		Users user = null;
		try {
			String query ="SELECT * FROM users WHERE email=? and password=?";
			Connection con = DbSkills.getConnection();
			PreparedStatement ps=con.prepareStatement(query);
			ps.setString(1, email);
			ps.setString(2, password);
			System.out.println(ps);
			ResultSet rs= ps.executeQuery();
			if(rs.next()) {
				if(rs.getString(6).equals("apprenant")) {
					user = new Apprenant(rs.getString(2), rs.getString(3), email, password, rs.getString(7));
				} else {
					user = new Staff(rs.getString(2), rs.getString(3), email, password);
				}
				user.setIdUser(rs.getInt(1));
			}
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return user;
	}
	
	public static int inscription(Apprenant user) {
		int ret=0;
		try {
			String query = "INSERT into users (`nom`,`prenom`,`email`,`password`,`reference`) values(?,?,?,?,?)";
			Connection con = DbSkills.getConnection();
			PreparedStatement ps = con.prepareStatement(query);
			ps.setString(1, user.getNom());
			ps.setString(2, user.getPrenom());
			ps.setString(3, user.getEmail());
			ps.setString(4, user.getPassword());
			ps.setString(5, user.getReference());
			ret = ps.executeUpdate();
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ret;
	}
	
	public static void getListApprenants() {
		try {
			listeApprenant.clear();
			String query ="SELECT * FROM `users` WHERE role='apprenant'";
			Connection con = DbSkills.getConnection();
			PreparedStatement ps=con.prepareStatement(query);
			ResultSet rs= ps.executeQuery();
			while(rs.next()) {
				listeApprenant.add(new Apprenant(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(7)));
			}
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void getCompetenceApprenant(int id) {
		try {
			listeCompetence.clear();
			String query ="SELECT id_competence,title,users.reference,niveau from niveaux,competences,users WHERE users.reference=competences.reference and users.id=niveaux.id_user and niveaux.id_competence=competences.id and users.id="+id;
			Connection con = DbSkills.getConnection();
			PreparedStatement ps=con.prepareStatement(query);
			ResultSet rs= ps.executeQuery();
			while(rs.next()) {
				listeCompetence.add(new Competence(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4)));
			}
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void updateCompetences(int id){
		for (Competence competence : listeCompetence) {
			try {
				String query = "UPDATE `niveaux` SET `niveau`=? WHERE id_competence=? and id_user=?";
				Connection con = DbSkills.getConnection();
				PreparedStatement ps = con.prepareStatement(query);
				ps.setInt(1, competence.getNiveau());
				ps.setInt(2, competence.getId());
				ps.setInt(3, id);
				ps.executeUpdate();
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	public static void updateReference(int id){
			try {
				String query = "CALL change_user_reference(?)";
				Connection con = DbSkills.getConnection();
				CallableStatement stmt = con.prepareCall(query);
				stmt.setInt(1, id);
				stmt.execute();
				stmt.close();
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
	}
}
