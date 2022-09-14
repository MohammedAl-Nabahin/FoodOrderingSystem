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
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author mohdo
 */
public class SettingsController implements Initializable {

    @FXML
    private Button cartButton;

    @FXML
    private Button logOutButton;

    @FXML
    private Button menuButton;

    @FXML
    private Button orderButton;

    @FXML
    private Button settingsButton;

    @FXML
    private Button yourCartButton;

    @FXML
    private Button yourProfileButton;
    
    @FXML 
    private Button deleteButton;
    
    PreparedStatement pst;
     File logFile = new File("logfile.txt");
    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
    LocalDateTime now = LocalDateTime.now();
    @FXML
    void cartButtonAction(ActionEvent event) throws IOException {
        Stage primaryStage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("/view/cart.fxml"));
        primaryStage.getIcons().add(new Image("/images/foodordering.png"));
        Scene scene = new Scene(root, 600, 550);
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();
        ((Node) (event.getSource())).getScene().getWindow().hide();
    }

    @FXML
    void logOutButtonAction(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/foodorderingsystem/FXMLDocument.fxml"));
        Stage stage = new Stage();
        Scene scene = new Scene(root, 625, 440);
        stage.setTitle("Food Ordering System");
        stage.setScene(scene);
        stage.getIcons().add(new Image("/images/foodordering.png"));
        stage.setResizable(false);
        stage.show();
        ((Node) (event.getSource())).getScene().getWindow().hide();
    }

    @FXML
    void menuButtonAction(ActionEvent event) throws IOException {
        Stage primaryStage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("/view/Items.fxml"));
        primaryStage.getIcons().add(new Image("/images/foodordering.png"));
        primaryStage.setTitle("Main Screen");
        Scene scene = new Scene(root, 680, 700);
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();
        ((Node) (event.getSource())).getScene().getWindow().hide();
    }

    @FXML
    void orderButtonAction(ActionEvent event) throws IOException {
        Stage primaryStage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("/view/order.fxml"));
        primaryStage.getIcons().add(new Image("/images/foodordering.png"));
        Scene scene = new Scene(root, 640, 640);
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();

        // Hide this current window (if this is what you want)
        ((Node) (event.getSource())).getScene().getWindow().hide();
    }

    @FXML
    void settingsButtonAction(ActionEvent event) throws IOException {
        Stage primaryStage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("/view/settings.fxml"));
        primaryStage.getIcons().add(new Image("/images/foodordering.png"));
        Scene scene = new Scene(root, 640, 640);
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();

        // Hide this current window (if this is what you want)
        ((Node) (event.getSource())).getScene().getWindow().hide();
    }

    @FXML
    void yourCartButtonAction(ActionEvent event) throws IOException {
        Stage primaryStage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("/view/yourCart.fxml"));
        primaryStage.getIcons().add(new Image("/images/foodordering.png"));
        Scene scene = new Scene(root, 600, 600);
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();
        ((Node) (event.getSource())).getScene().getWindow().hide();
    }

    @FXML
    void yourProfileButtonAction(ActionEvent event) throws IOException {
        Stage primaryStage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("/view/profile.fxml"));
        primaryStage.getIcons().add(new Image("/images/foodordering.png"));
        Scene scene = new Scene(root, 490, 500);
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();

        // Hide this current window (if this is what you want)
        ((Node) (event.getSource())).getScene().getWindow().hide();
    }
    
    @FXML
    void deleteButtonAction(ActionEvent event) throws SQLException, IOException{
          db_connection.getConnection();
             Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setContentText("Are You Sure!");
                  pst = conn.prepareStatement("delete from user where id = ? ");
            pst.setInt(1,SignUpController.id);
                pst.executeUpdate();

                  FileWriter myWriter = new FileWriter(logFile,true);
      myWriter.append(pst.toString()+" On: "+ dtf.format(now) + "\n");
        alert.showAndWait();
        

      
       Parent root = FXMLLoader.load(getClass().getResource("/foodorderingsystem/FXMLDocument.fxml"));
        Stage stage = new Stage();
        Scene scene = new Scene(root, 625, 440);
        stage.setTitle("Food Ordering System");
        stage.setScene(scene);
        stage.getIcons().add(new Image("/images/foodordering.png"));
        stage.setResizable(false);
        stage.show();
        ((Node) (event.getSource())).getScene().getWindow().hide();
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

}
