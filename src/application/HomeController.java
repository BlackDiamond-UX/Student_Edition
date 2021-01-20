package application;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class HomeController implements Initializable {
    @FXML
    private AnchorPane rootPane;
    @FXML
    public void lancerEtudiant(ActionEvent event) throws IOException
    {
     AnchorPane pane = FXMLLoader.load(getClass().getResource("Etudiant.fxml"));
     rootPane.getChildren().setAll(pane);
    }
    @FXML
    public void lancerSpecialite() throws IOException
    {
     AnchorPane pane = FXMLLoader.load(getClass().getResource("Specialite.fxml"));
     rootPane.getChildren().setAll(pane);
    }
    @FXML
    public void lancerModule() throws IOException
    {
     AnchorPane pane = FXMLLoader.load(getClass().getResource("Module.fxml"));
     rootPane.getChildren().setAll(pane);
    }
    @FXML
    public void quitter(ActionEvent event)
    {
        System.exit(0);
    }
    @FXML
    public void About(ActionEvent event)
    {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("INFORMATION");
        alert.setHeaderText(null);
        alert.setContentText("THIS APPLICATION CREATED BY ROUIHA AYOUB AND SOUFIANE EL KADIRI");
        alert.showAndWait();
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
