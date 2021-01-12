package application;
	
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javafx.animation.ScaleTransition;
import javafx.animation.SequentialTransition;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.util.StringConverter;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.Group;


public class Main extends Application implements Interface {
	static Group root = new Group();
	static ImageView background = new ImageView(new Image("file:background.png"));
	static Pane panelConnexion = new Pane();
	static Pane panelInscription = new Pane();
	static Pane panelApprenant = new Pane();
	static Pane panelStaff = new Pane();
	static Pane panelCompetence = new Pane();
	static ArrayList<String> listNiveau = new ArrayList<String>();
	static ArrayList<ArrayList<Label>> listeLabel = new ArrayList<ArrayList<Label>>();
	static int i,j;
	
	
	@Override
	public void start(Stage primaryStage) {
		try {
			Scene scene = new Scene(root,900,740);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			background.relocate(0, 0);
			root.getChildren().add(background);
			primaryStage.setTitle("Skills tree");
			primaryStage.setScene(scene);
			primaryStage.show();
			connexionInscription();
			
			listNiveau.add("niveau 1, imiter");
			listNiveau.add("niveau 2, adapter");
			listNiveau.add("niveau 3, transposer");
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
	
	public static void connexionInscription() {
		connexion();
		inscription();
		hideAndShow(panelInscription, panelConnexion, 1);
	}
	
	public static void connexion() {
		
		panelConnexion.relocate(400, 120);
		panelConnexion.setPrefSize(350,500);
		panelConnexion.getStyleClass().add("pane");
		root.getChildren().add(panelConnexion);
		
		ImageView image1 = new ImageView(new Image("file:pngegg.png"));
		image1.relocate(86, 1);
		panelConnexion.getChildren().add(image1);
		
		Label title = new Label("Connexion");
		title.relocate(50, 50);
		title.getStyleClass().add("title");
		panelConnexion.getChildren().add(title);
		
		Label email = new Label("Adresse email");
		email.relocate(50, 150);
		panelConnexion.getChildren().add(email);
		
		TextField txt_email = new TextField();
		txt_email.relocate(50, 180);
		panelConnexion.getChildren().add(txt_email);
		
		Label password = new Label("Mot de passe");
		password.relocate(50, 250);
		panelConnexion.getChildren().add(password);
		
		PasswordField txt_password = new PasswordField();
		txt_password.relocate(50, 280);
		panelConnexion.getChildren().add(txt_password);
		
		Button btn_connection = new Button("Connection",new ImageView(new Image("file:fleche.png")));
		btn_connection.relocate(200, 350);
		panelConnexion.getChildren().add(btn_connection);
		btn_connection.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				Users user = DbSkills.login(txt_email.getText(), txt_password.getText());
				if (user != null) {
					root.getChildren().remove(panelConnexion);
					root.getChildren().remove(panelInscription);
					if (user.getClass() == Apprenant.class) {
						Apprenant apprenant = (Apprenant) user;
						pageApprenant(apprenant);
					} else {
						Staff staff = (Staff) user;
						pageStaff(staff);
					}
				}else {
					getAlert("votre email ou mot de passe est incorrect", "Connexion");
				}
			}
		});
		
		
		Label inscription = new Label("vous n'avez pas de compte?");
		inscription.relocate(70, 440);
		inscription.setStyle("-fx-font-weight: 400;");
		panelConnexion.getChildren().add(inscription);
		
		Label linkToInscription = new Label("Inscription");
		linkToInscription.relocate(240, 440);
		linkToInscription.setStyle("-fx-underline: true;");
		panelConnexion.getChildren().add(linkToInscription);
		linkToInscription.setOnMouseClicked(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent arg0) {
				hideAndShow(panelConnexion, panelInscription, 1000);
			}
		});
	}
	
	
	public static void inscription() {
		
		panelInscription.relocate(400, 120);
		panelInscription.setPrefSize(350,500);
		panelInscription.getStyleClass().add("pane");
		root.getChildren().add(panelInscription);
		
		ImageView image1 = new ImageView(new Image("file:pngegg.png"));
		image1.relocate(86, 1);
		panelInscription.getChildren().add(image1);
		
		Label title = new Label("Inscription");
		title.relocate(50, 50);
		title.getStyleClass().add("title");
		panelInscription.getChildren().add(title);
		
		Label nom = new Label("Nom");
		nom.relocate(50, 100);
		panelInscription.getChildren().add(nom);
		
		TextField txt_nom = new TextField();
		txt_nom.relocate(50, 120);
		panelInscription.getChildren().add(txt_nom);
		
		Label prenom = new Label("Prenom");
		prenom.relocate(50, 150);
		panelInscription.getChildren().add(prenom);
		
		TextField txt_prenom = new TextField();
		txt_prenom.relocate(50, 170);
		panelInscription.getChildren().add(txt_prenom);
		
		Label email = new Label("Email");
		email.relocate(50, 200);
		panelInscription.getChildren().add(email);
		
		TextField txt_email = new TextField();
		txt_email.relocate(50, 220);
		panelInscription.getChildren().add(txt_email);
		
		Label password = new Label("Mot de passe");
		password.relocate(50, 250);
		panelInscription.getChildren().add(password);
		
		PasswordField txt_password = new PasswordField();
		txt_password.relocate(50, 270);
		panelInscription.getChildren().add(txt_password);
		
		Label reference = new Label("Reference");
		reference.relocate(50, 300);
		panelInscription.getChildren().add(reference);
		
		ComboBox<String> combo_reference = new ComboBox<String>();
		combo_reference.getItems().addAll("Développeur⋅se web et web mobile", "Concepteur⋅rice développeur⋅se d`applications");
		combo_reference.relocate(25, 320);
		panelInscription.getChildren().add(combo_reference);
		
		Button btn_inscription = new Button("Inscription",new ImageView(new Image("file:fleche.png")));
		btn_inscription.relocate(200, 400);
		panelInscription.getChildren().add(btn_inscription);
		btn_inscription.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				if (!txt_nom.getText().equals("") && !txt_prenom.getText().equals("") && !txt_email.getText().equals("") && !txt_password.getText().equals("") && !combo_reference.getSelectionModel().getSelectedItem().equals("")) {
					if (validate_email(txt_email.getText())) {
						Apprenant appenant =new Apprenant(txt_nom.getText(), txt_prenom.getText(), txt_email.getText(), txt_password.getText(), combo_reference.getSelectionModel().getSelectedItem());
						int ret = DbSkills.inscription(appenant);
						if(ret == 1) {
							getAlert("Inscription est terminée avec succès", "Inscription");
							hideAndShow(panelInscription, panelConnexion, 1000);
						}else {
							getAlert("Erreur d'inscription", "Inscription");
						}
					} else {
						getAlert("Email est invalide", "Inscription");
					}
					
				} else {
					getAlert("Merci de remplir tous les champs", "Inscription");
				}
			}
		});
		
		Label connexion = new Label("Vous avez déjà un compte?");
		connexion.relocate(70, 440);
		connexion.setStyle("-fx-font-weight: 400;");
		panelInscription.getChildren().add(connexion);
		
		Label linkToConnexion = new Label("Connexion");
		linkToConnexion.relocate(240, 440);
		linkToConnexion.setStyle("-fx-underline: true;");
		panelInscription.getChildren().add(linkToConnexion);
		linkToConnexion.setOnMouseClicked(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent arg0) {
				hideAndShow(panelInscription, panelConnexion, 1000);
			}
		});
		
	}
	
	public static void pageApprenant(Apprenant apprenant) {
		
		DbSkills.getCompetenceApprenant(apprenant.getIdUser());
		
		panelApprenant.relocate(0, 0);
		root.getChildren().add(panelApprenant);
		
		panelCompetence.relocate(0, 0);
		panelApprenant.getChildren().add(panelCompetence);
		
		Label title = new Label(apprenant.getNom()+" "+apprenant.getPrenom());
		title.relocate(50, 30);
		title.getStyleClass().add("title");
		panelCompetence.getChildren().add(title);
		
		Label reference = new Label(apprenant.getReference());
		reference.relocate(500, 40);
		reference.setStyle("-fx-font-size: 14px;");
		panelCompetence.getChildren().add(reference);
		
		int y=110;
		
		for(i = 0; i < listeCompetence.size(); i++) {
			Competence competence = listeCompetence.get(i);
			
			int x=30;
			
			Label competence1 = new Label(competence.getTitle());
			competence1.relocate(x, y);
			panelCompetence.getChildren().add(competence1);
			
			ArrayList<Label> groupLabel = new ArrayList<Label>();
			
			int a=40;
			for (j = 0; j < 3; j++) {
				int b=y+35;
				Label niveau1 = new Label(listNiveau.get(j));
				niveau1.relocate(a, b);
				panelCompetence.getChildren().add(niveau1);
				niveau1.getStyleClass().add("lapel-niveau");
				
				if (j < competence.getNiveau() ) {
					niveau1.getStyleClass().add("lapel-niveau-true");
				}
				
				niveau1.setOnMouseClicked(new EventHandler<MouseEvent>() {
					int a=i, b=j;
					@Override
					public void handle(MouseEvent arg0) {
						changerCompetence(a, b);
					}
				});
				
				groupLabel.add(niveau1);
				
				a+=130;
				
			}
			
			listeLabel.add(groupLabel);
			
			i++;
			
			competence = listeCompetence.get(i);
			
			x=470;
			
			Label competence2 = new Label(competence.getTitle());
			competence2.relocate(x, y);
			panelCompetence.getChildren().add(competence2);
			ArrayList<Label> groupLabel1 = new ArrayList<Label>();
			a=480;
			for (j = 0; j < 3; j++) {
				int b=y+35;
				Label niveau1 = new Label(listNiveau.get(j));
				niveau1.relocate(a, b);
				panelCompetence.getChildren().add(niveau1);
				niveau1.getStyleClass().add("lapel-niveau");
				
				if (j < competence.getNiveau() ) {
					niveau1.getStyleClass().add("lapel-niveau-true");
				}
				
				niveau1.setOnMouseClicked(new EventHandler<MouseEvent>() {
					int a=i, b=j;
					@Override
					public void handle(MouseEvent arg0) {
						changerCompetence(a, b);
					}
				});
				
				groupLabel1.add(niveau1);
				
				a+=130;
				
			}
			
			listeLabel.add(groupLabel1);
			
			y += 80;
		}
		
		Button btn_modifier = new Button("Modifier",new ImageView(new Image("file:fleche.png")));
		btn_modifier.relocate(620, 690);
		panelApprenant.getChildren().add(btn_modifier);
		btn_modifier.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				DbSkills.updateCompetences(apprenant.getIdUser());
				getAlert("La modification est terminée avec succès", "Modifier");
				if(listeCompetence.size()==8) {
					boolean flag = true;
					
					for (int i = 0; i< listeCompetence.size() && flag; i++)
				    {
				        if(listeCompetence.get(i).getNiveau() != 3) flag = false;
				    }
					
					if(flag == true) {
						DbSkills.updateReference(apprenant.getIdUser());
						getAlert("vous avez terminé les competence de Développeur⋅se web et web mobile, maintent vous passé au référence de Concepteur⋅rice développeur⋅se d`applications", "Félicitation");
						listeLabel.clear();
						panelCompetence.getChildren().clear();
						panelApprenant.getChildren().remove(panelCompetence);
						root.getChildren().remove(panelApprenant);
						pageApprenant(apprenant);
						
					}
				}
			}
		});
		
		Button btn_deconnexion = new Button("Déconnexion",new ImageView(new Image("file:logout.png")));
		btn_deconnexion.relocate(750, 690);
		panelApprenant.getChildren().add(btn_deconnexion);
		btn_deconnexion.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				listeCompetence.clear();
				listeLabel.clear();
				panelCompetence.getChildren().clear();
				panelApprenant.getChildren().remove(panelCompetence);
				root.getChildren().remove(panelApprenant);
				connexionInscription();
			}
		});
	}
	
	public static void pageStaff(Staff staff) {
		
		DbSkills.getListApprenants();
		
		panelStaff.relocate(0, 0);
		root.getChildren().add(panelStaff);
		
		panelCompetence.relocate(0, 100);
		panelStaff.getChildren().add(panelCompetence);
		
		Label title = new Label("Bonjour "+staff.getNom()+" "+staff.getPrenom());
		title.relocate(50, 30);
		title.getStyleClass().add("title");
		panelStaff.getChildren().add(title);
		
		Label label_reference = new Label();
		label_reference.relocate(500, 70);
		panelStaff.getChildren().add(label_reference);
		
		ObservableList<Apprenant> list = FXCollections.observableArrayList();
		list.addAll(listeApprenant);

		ComboBox<Apprenant> combo_apprenant = new ComboBox<Apprenant>();
		combo_apprenant.relocate(50, 70);
		panelStaff.getChildren().add(combo_apprenant);
		combo_apprenant.setItems(list);
		combo_apprenant.setConverter(new StringConverter<>() {

			@Override
			public String toString(Apprenant apprenant) {
				if (apprenant!=null){
					return apprenant.getNom() + " " + apprenant.getPrenom();
				}
				return "";
			}

		    @Override
		    public Apprenant fromString(String string) {
		        return null;
		    }
		});
		
		combo_apprenant.valueProperty().addListener((obs, oldval, newval) -> {
		    if(newval != null) {
		    	label_reference.setText(newval.getReference());
		    	DbSkills.getCompetenceApprenant(newval.getIdUser());
		    	panelCompetence.getChildren().clear();
		    	
		    	int y=30;
				
				for(int i = 0; i < listeCompetence.size(); i++) {
					Competence Competence = listeCompetence.get(i);
					
					int x=30;
					
					Label cometence1 = new Label(Competence.getTitle());
					cometence1.relocate(x, y);
					panelCompetence.getChildren().add(cometence1);
					
					int a=40;
					for (int j = 0; j < 3; j++) {
						int b=y+35;
						Label niveau1 = new Label(listNiveau.get(j));
						niveau1.relocate(a, b);
						panelCompetence.getChildren().add(niveau1);
						niveau1.getStyleClass().add("lapel-niveau");
						
						if (j < Competence.getNiveau() ) {
							niveau1.getStyleClass().add("lapel-niveau-true");
						}
						
						a+=130;
						
					}
					
					i++;
					
					Competence = listeCompetence.get(i);
					
					x=470;
					
					Label cometence2 = new Label(Competence.getTitle());
					cometence2.relocate(x, y);
					panelCompetence.getChildren().add(cometence2);
					
					a=480;
					for (int j = 0; j < 3; j++) {
						int b=y+35;
						Label niveau1 = new Label(listNiveau.get(j));
						niveau1.relocate(a, b);
						panelCompetence.getChildren().add(niveau1);
						niveau1.getStyleClass().add("lapel-niveau");
						
						if (j < Competence.getNiveau() ) {
							niveau1.getStyleClass().add("lapel-niveau-true");
						}
						
						a+=130;
						
					}
					
					y += 80;
				}
		    }
		});
		
		
		Button btn_deconnexion = new Button("Déconnexion",new ImageView(new Image("file:logout.png")));
		btn_deconnexion.relocate(750, 690);
		panelStaff.getChildren().add(btn_deconnexion);
		btn_deconnexion.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				listeCompetence.clear();
				listeApprenant.clear();
				panelCompetence.getChildren().clear();
				panelStaff.getChildren().clear();
				root.getChildren().remove(panelStaff);
				connexionInscription();
			}
		});
	}
	
	
	public static void hideAndShow(Pane hide, Pane show, int time) {
		ScaleTransition hidePanel = new ScaleTransition(Duration.millis(time), hide);
		hidePanel.setToX(0);
	    
		ScaleTransition showPanel = new ScaleTransition(Duration.millis(time), show);
		showPanel.setToX(1);
		
	    SequentialTransition sequentialTransition = new SequentialTransition();
	    sequentialTransition.getChildren().addAll(hidePanel, showPanel);
	    sequentialTransition.setCycleCount(1);
	    sequentialTransition.play();
	}
	
	public static void getAlert(String text, String title) {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle(title);
		alert.setHeaderText(null);
		alert.setContentText(text);
		alert.showAndWait();
	}
	
	public static void changerCompetence(int i, int j) {
		if(listeLabel.get(i).get(j).getStyleClass().size() != 3){
			for (int k = 0; k <= j; k++) {
				if(listeLabel.get(i).get(k).getStyleClass().size() != 3){
					listeLabel.get(i).get(k).getStyleClass().add("lapel-niveau-true");
				}
			}
			listeCompetence.get(i).setNiveau(j+1);
		} else if (j == 2) {
			listeLabel.get(i).get(j).getStyleClass().remove("lapel-niveau-true");
			listeCompetence.get(i).setNiveau(j);
		}
		else {
			boolean ret = true;
			for (int k = j+1; k < 3; k++) {
				if(listeLabel.get(i).get(k).getStyleClass().size() == 3){
					ret = false;
					listeLabel.get(i).get(k).getStyleClass().remove("lapel-niveau-true");
				}
			}
			if(ret == true) {
				listeLabel.get(i).get(j).getStyleClass().remove("lapel-niveau-true");
				listeCompetence.get(i).setNiveau(j);
			}
			else {
				listeCompetence.get(i).setNiveau(j+1);
			}
		}
	}
	
		
		public static boolean validate_email(String email) {
				Pattern regex = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);
		        Matcher matcher = regex.matcher(email);
		        return matcher.find();
		}
}
