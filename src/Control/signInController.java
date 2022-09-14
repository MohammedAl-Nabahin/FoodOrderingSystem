/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Control;

import static Control.db_connection.conn;
import Model.user;
import static Control.SignUpController.userId;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.control.Alert;

/**
 *
 * @author Anas
 */
public class signInController implements Initializable {

    ResultSet rs;
    PreparedStatement pst;

  
    user user1 = new user();
    File logFile = new File("logfile.txt");
    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
    LocalDateTime now = LocalDateTime.now();

    @FXML
    private Button cancelButton;

    @FXML
    private PasswordField passwordField;

    @FXML
    private Button signInButton;

    @FXML
    private TextField userNameTextField;

    @FXML
    private Label statusID;

    @FXML
    void cancelButtonAction(ActionEvent event) throws IOException {
        Stage primaryStage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("/foodorderingsystem/FXMLDocument.fxml"));
        primaryStage.getIcons().add(new Image("/images/foodordering.png"));
        primaryStage.setTitle("Food Ordering System");
        Scene scene = new Scene(root, 625, 440);
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();

        // Hide this current window (if this is what you want)
        ((Node) (event.getSource())).getScene().getWindow().hide();
    }

    @FXML
    void signInButtonAction(ActionEvent event) throws IOException, SQLException {
        db_connection.getConnection();

        String pass = passwordField.getText();
        pst = conn.prepareStatement(
                "select * from user WHERE name='" + userNameTextField.getText() +
                        "' AND password='"+ Encryption.md5Hash(passwordField.getText())+"'");
        rs = pst.executeQuery();
        FileWriter myWriter = new FileWriter(logFile, true);
        myWriter.append(pst.toString() + " On: " + dtf.format(now) + "\n");

        if (rs.next()) {
            try {
                pst = conn.prepareStatement("select id from user WHERE name='" + userNameTextField.getText() + "'");
//             pst = conn.prepareStatement("select cartId from user WHERE name='"+userNameTextField.getText()+"'");
            } catch (SQLException ex) {
                Logger.getLogger(signInController.class.getName()).log(Level.SEVERE, null, ex);
            }
            try {
                rs = pst.executeQuery();
                myWriter.append(pst.toString() + " On: " + dtf.format(now) + "\n");
                while (rs.next()) {

                    user1.setUserId(Integer.parseInt(rs.getString("id")));
                    userId = user1.getUserId();
                    SignUpController.id = userId;
                    System.out.println(userId);
                }
            } catch (SQLException ex) {
                Logger.getLogger(signInController.class.getName()).log(Level.SEVERE, null, ex);
            }
            
              try {
                pst = conn.prepareStatement("select cartId from user WHERE name='" + userNameTextField.getText() + "'");
//             pst = conn.prepareStatement("select cartId from user WHERE name='"+userNameTextField.getText()+"'");
            } catch (SQLException ex) {
                Logger.getLogger(signInController.class.getName()).log(Level.SEVERE, null, ex);
            }
            try {
                rs = pst.executeQuery();
                myWriter.append(pst.toString() + " On: " + dtf.format(now) + "\n");
                while (rs.next()) {
                     
                    user1.setCartId(Integer.parseInt(rs.getString("cartId")));
                      SignUpController.cartId = user1.getCartId();
                    System.out.println(SignUpController.cartId);
                }
            } catch (SQLException ex) {
                Logger.getLogger(signInController.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            Alert logInTrue = new Alert(Alert.AlertType.CONFIRMATION);
            logInTrue.setContentText("Hi , Your ID is "+SignUpController.userId);
            logInTrue.showAndWait();
            Stage primaryStage = new Stage();
            Parent root = FXMLLoader.load(getClass().getResource("/view/Items.fxml"));
            primaryStage.getIcons().add(new Image("/images/foodordering.png"));
            primaryStage.setTitle("Main Screen");
            Scene scene = new Scene(root, 680, 700);
            primaryStage.setScene(scene);
            primaryStage.setResizable(false);
            primaryStage.show();
             
        } else {
            Alert logInWrong = new Alert(Alert.AlertType.ERROR);
            logInWrong.setContentText("Your name or your password is wrong");
            logInWrong.show();
            Stage primaryStage = new Stage();
            Parent root = FXMLLoader.load(getClass().getResource("/view/logIn.fxml"));
            primaryStage.getIcons().add(new Image("/images/foodordering.png"));
            primaryStage.setTitle("Main Screen");
            Scene scene = new Scene(root, 680, 700);
            primaryStage.setScene(scene);
            primaryStage.setResizable(false);
            primaryStage.show();
        }
        ((Node) (event.getSource())).getScene().getWindow().hide();
        userId++;
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        // TODO
        passwordField.setPromptText("Password");
        userNameTextField.setPromptText("User Name");
    }

}
