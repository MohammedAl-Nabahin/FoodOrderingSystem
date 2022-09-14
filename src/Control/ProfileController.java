/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Control;

import static Control.db_connection.conn;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author mohdo
 */
public class ProfileController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    private Button backButton;

    @FXML
    private Button changeButton;

    @FXML
    private TextField emailTextField;

    @FXML
    private TextField nameTextField;

    @FXML
    private TextField newPasswordTextField;

    @FXML
    private TextField addressTextField;

    @FXML
    private TextField oldPasswordTextField;
    PreparedStatement pst;
    ResultSet rs;
    File logFile = new File("logfile.txt");
    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
    LocalDateTime now = LocalDateTime.now();

    @FXML
    void backButtonAction(ActionEvent event) throws IOException {
        Stage primaryStage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("/view/settings.fxml"));
        primaryStage.getIcons().add(new Image("/images/foodordering.png"));
        Scene scene = new Scene(root, 640, 640);
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();
        ((Node) (event.getSource())).getScene().getWindow().hide();
    }

    @FXML
    void changeButtonAction(ActionEvent event) throws IOException {
        db_connection.getConnection();
        try {
            String address, email, newPassword;
            int id = SignUpController.id;

            address = addressTextField.getText();
            email = emailTextField.getText();
            newPassword = newPasswordTextField.getText();
            if (address.isEmpty() || email.isEmpty() || newPassword.isEmpty()) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("Enter Something");
                alert.show();
            } else {
                pst = conn.prepareStatement("update user set  address = ?,email = ? ,password = ? where  id = ? ");
            }

            pst.setString(1, address);
            pst.setString(2, email);
            pst.setString(3, Encryption.md5Hash(newPassword));

            pst.setInt(4, id);
            pst.executeUpdate();
            FileWriter myWriter = new FileWriter(logFile, true);
            myWriter.append(pst.toString() + " On: " + dtf.format(now) + "\n");
            myWriter.close();
        } catch (SQLException ex) {
            Logger.getLogger(ProfileController.class.getName()).log(Level.SEVERE, null, ex);
        }

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setContentText("Changed Successfully");
        alert.showAndWait();
         Stage primaryStage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("/view/settings.fxml"));
        primaryStage.getIcons().add(new Image("/images/foodordering.png"));
        Scene scene = new Scene(root, 640, 640);
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();
        ((Node) (event.getSource())).getScene().getWindow().hide();

    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

}
