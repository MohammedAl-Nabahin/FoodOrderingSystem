/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package foodorderingsystem;

import Model.user;
import java.io.IOException;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 *
 * @author mohdo
 */
public class FXMLDocumentController implements Initializable {
    
     @FXML
    private HBox logo;
      @FXML
    private FlowPane flowImg;
    @FXML
    private Button signInButton;

    @FXML
    private Button signUpButton;
    PreparedStatement pst;
    ResultSet rs;
    user user1 = new user();
    static int userId;
    
    @FXML
    void signInAction(ActionEvent event) throws IOException {
         Stage primaryStage =new Stage();
                primaryStage.initStyle(StageStyle.UNDECORATED);
		Parent root =FXMLLoader.load(getClass().getResource("/view/signIn.fxml"));
		Scene scene = new Scene(root);
		primaryStage.setScene(scene);
		primaryStage.show();
                
            // Hide this current window (if this is what you want)
            ((Node)(event.getSource())).getScene().getWindow().hide();
    }

    
      public void getUserId(){
      
        
}
    
    @FXML
    void signUpAction(ActionEvent event) throws IOException {
        Stage primaryStage =new Stage();
                primaryStage.initStyle(StageStyle.UNDECORATED);
		Parent root =FXMLLoader.load(getClass().getResource("/view/signUp.fxml"));
		Scene scene = new Scene(root);
		primaryStage.setScene(scene);
		primaryStage.show();
                
            // Hide this current window (if this is what you want)
            ((Node)(event.getSource())).getScene().getWindow().hide();
    }

    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         Image bg = new Image("/images/food-logo.jpg");
          BackgroundImage background = new BackgroundImage(bg,
                  BackgroundRepeat.NO_REPEAT,
                  BackgroundRepeat.NO_REPEAT,
                  BackgroundPosition.CENTER,
                  new BackgroundSize(250, 250, true, true, true, true)
          );
          Background img = new Background(background);
          
        flowImg.setAlignment(Pos.TOP_CENTER);
        flowImg.setBackground(img);

    }    
    
}
