package application;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;

public class GestionEtudiant implements Initializable {
    @FXML private TableView<Etudiant> table;
    @FXML private TableColumn<Etudiant,Integer> id_etudiant;
    @FXML private TableColumn<Etudiant,String> nom_etudiant;
    @FXML private TableColumn<Etudiant,String> prenom_etudiant;
    @FXML private TableColumn<Etudiant,String> email;
    @FXML private TableColumn<Etudiant,Integer> telephone;
    @FXML private TableColumn<Etudiant,Integer> id_specialite;
    public ObservableList<Etudiant> data = FXCollections.observableArrayList();
    @FXML
    public void viewEtudiant()
    {
        try{
            Connection con = ConnectionClass.getConnection();
            String sql = "SELECT * FROM etudiant";
            PreparedStatement stat = con.prepareStatement(sql);
            ResultSet rs = stat.executeQuery();
            while (rs.next())
            {
                data.add(new Etudiant(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getInt(5),rs.getInt(6)));
            }
            con.close();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        id_etudiant.setCellValueFactory(new PropertyValueFactory<Etudiant,Integer>("id_etudiant"));
        nom_etudiant.setCellValueFactory(new PropertyValueFactory<Etudiant,String>("nom_etudiant"));
        prenom_etudiant.setCellValueFactory(new PropertyValueFactory<Etudiant,String>("prenom_etudiant"));
        email.setCellValueFactory(new PropertyValueFactory<Etudiant,String>("email"));
        telephone.setCellValueFactory(new PropertyValueFactory<Etudiant,Integer>("telephone"));
        id_specialite.setCellValueFactory(new PropertyValueFactory<Etudiant,Integer>("id_specialite"));
        table.setItems(data);
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {


    }
}
