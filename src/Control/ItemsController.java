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
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
//import Control.db_connection;

/**
 * FXML Controller class
 *
 * @author 
 */

  
public class ItemsController implements Initializable {
  
  
     ResultSet rs;
     PreparedStatement pst;
    
  @FXML
    private Button cartButton;

    @FXML
    private HBox hBox1;

    @FXML
    private HBox hBox2;

    @FXML
    private HBox hBox3;

    @FXML
    private HBox hBox4;

    @FXML
    private HBox hBox5;

    @FXML
    private HBox hBox6;

    @FXML
    private HBox hBox7;

    @FXML
    private HBox hBox8;

    @FXML
    private Label item1;

    @FXML
    private Button item1Add;

    @FXML
    private Label item1Price;

    @FXML
    private Label item2;

    @FXML
    private Button item2Add;

    @FXML
    private Label item2Price;

    @FXML
    private Label item3;

    @FXML
    private Button item3Add;

    @FXML
    private Label item3Price;

    @FXML
    private Label item4;

    @FXML
    private Button item4Add;

    @FXML
    private Label item4Price;

    @FXML
    private Label item5;

    @FXML
    private Button item5Add;

    @FXML
    private Label item5Price;

    @FXML
    private Label item6;

    @FXML
    private Button item6Add;

    @FXML
    private Label item6Price;

    @FXML
    private Label item7;

    @FXML
    private Button item7Add;

    @FXML
    private Label item7Price;

    @FXML
    private Label item8;

    @FXML
    private Button item8Add;

    @FXML
    private Label item8Price;

    @FXML
    private Button logOutButton;

    @FXML
    private Button menuButton;

    @FXML
    private Button orderButton;

    @FXML
    private Button settingsButton;
    
    static int ItemId; 
    int cart_ID = SignUpController.cartId;
        File logFile = new File("logfile.txt");
          DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");  
          LocalDateTime now = LocalDateTime.now();  
    @FXML
    void cartButtonAction(ActionEvent event) throws IOException {
           Stage primaryStage =new Stage();
		Parent root =FXMLLoader.load(getClass().getResource("/view/cart.fxml"));
                primaryStage.getIcons().add(new Image("/images/foodordering.png"));
		Scene scene = new Scene(root,600,550);
		primaryStage.setScene(scene);
                primaryStage.setResizable(false);
		primaryStage.show();
                
            // Hide this current window (if this is what you want)
            ((Node)(event.getSource())).getScene().getWindow().hide();
   
    }

     @FXML
    void menuButtonAction(ActionEvent event) throws IOException {
         Stage primaryStage =new Stage();
		Parent root =FXMLLoader.load(getClass().getResource("/view/Items.fxml"));
                primaryStage.getIcons().add(new Image("/images/foodordering.png"));
                primaryStage.setTitle("Main Screen");
		Scene scene = new Scene(root,680,700);
		primaryStage.setScene(scene);
                primaryStage.setResizable(false);
		primaryStage.show();
            ((Node)(event.getSource())).getScene().getWindow().hide();
    }

    @FXML
    void orderButtonAction(ActionEvent event) throws IOException {
        Stage primaryStage =new Stage();
		Parent root =FXMLLoader.load(getClass().getResource("/view/order.fxml"));
                primaryStage.getIcons().add(new Image("/images/foodordering.png"));
		Scene scene = new Scene(root,635,640);
		primaryStage.setScene(scene);
                primaryStage.setResizable(false);
		primaryStage.show();
            ((Node)(event.getSource())).getScene().getWindow().hide();
    }
    
    @FXML
    void settingsButtonAction(ActionEvent event) throws IOException {
         Stage primaryStage =new Stage();
		Parent root =FXMLLoader.load(getClass().getResource("/view/settings.fxml"));
                primaryStage.getIcons().add(new Image("/images/foodordering.png"));
		Scene scene = new Scene(root,640,640);
		primaryStage.setScene(scene);
                primaryStage.setResizable(false);
		primaryStage.show();
                
            // Hide this current window (if this is what you want)
            ((Node)(event.getSource())).getScene().getWindow().hide();
    }

    


    
    @FXML
    void item1Add(ActionEvent event) throws SQLException, IOException {
        db_connection.getConnection();
       
          
             ItemId = 1;
          
                 String name,price;
            int id = ItemId;
            name = item1.getText();
            price = item1Price.getText();
            
        try
        {
              pst = conn.prepareStatement("INSERT INTO cart"
                      + " VALUES (?,?,?,?)"
                      );
            
            pst.setInt(1, id);
            pst.setString(2, name);
            pst.setString(3, price);
            pst.setInt(4, SignUpController.cartId);
          
            pst.executeUpdate();
            FileWriter myWriter = new FileWriter(logFile, true);
            myWriter.append(pst.toString() + " On: " + dtf.format(now) + "\n");
            myWriter.close();
            
         
            
          
        }
        catch (SQLException ex)
        {
            Logger.getLogger(ItemsController.class.getName()).log(Level.SEVERE, null, ex);
        }
                
                Stage primaryStage =new Stage();
		Parent root =FXMLLoader.load(getClass().getResource("/view/cart.fxml"));
                primaryStage.getIcons().add(new Image("/images/foodordering.png"));
		Scene scene = new Scene(root,600,550);
		primaryStage.setScene(scene);
                primaryStage.setResizable(false);
		primaryStage.show();
                
            // Hide this current window (if this is what you want)
            ((Node)(event.getSource())).getScene().getWindow().hide();
            
            
    }
     
    

    @FXML
    void item2Add(ActionEvent event) throws IOException {
         db_connection.getConnection();
       
          
             ItemId = 2;
            
                
                   String name,price;
            int id = ItemId;
            name = item2.getText();
            price = item2Price.getText();
            
        try
        {
                 pst = conn.prepareStatement("INSERT INTO cart"
                      + " VALUES (?,?,?,?)"
                      );
            
            pst.setInt(1, id);
            pst.setString(2, name);
            pst.setString(3, price);
            pst.setInt(4, cart_ID);
            pst.executeUpdate();
             FileWriter myWriter = new FileWriter(logFile,true);
            myWriter.append(pst.toString()+" On: "+ dtf.format(now) + "\n");
             myWriter.close();
         
            
          
        }
        catch (SQLException ex)
        {
            Logger.getLogger(ItemsController.class.getName()).log(Level.SEVERE, null, ex);
        }
               Stage primaryStage =new Stage();
		Parent root =FXMLLoader.load(getClass().getResource("/view/cart.fxml"));
                primaryStage.getIcons().add(new Image("/images/foodordering.png"));
		Scene scene = new Scene(root,600,550);
		primaryStage.setScene(scene);
                primaryStage.setResizable(false);
		primaryStage.show();
                
            // Hide this current window (if this is what you want)
            ((Node)(event.getSource())).getScene().getWindow().hide();
            
       
    }

    @FXML
    void item3Add(ActionEvent event) throws IOException {
                 db_connection.getConnection();
       
          
             ItemId = 3;
          
                
                   String name,price;
            int id = ItemId;
            name = item3.getText();
            price = item3Price.getText();
            
            
        try
        {
               pst = conn.prepareStatement("INSERT INTO cart"
                      + " VALUES (?,?,?,?)"
                      );
            
            pst.setInt(1, id);
            pst.setString(2, name);
            pst.setString(3, price);
            pst.setInt(4, cart_ID);
            pst.executeUpdate();
             FileWriter myWriter = new FileWriter(logFile,true);
            myWriter.append(pst.toString()+" On: "+ dtf.format(now) + "\n");
            myWriter.close();
          
        }
        catch (SQLException ex)
        {
            Logger.getLogger(ItemsController.class.getName()).log(Level.SEVERE, null, ex);
        }
                Stage primaryStage =new Stage();
		Parent root =FXMLLoader.load(getClass().getResource("/view/cart.fxml"));
                primaryStage.getIcons().add(new Image("/images/foodordering.png"));
		Scene scene = new Scene(root,600,550);
		primaryStage.setScene(scene);
                primaryStage.setResizable(false);
		primaryStage.show();
                
            // Hide this current window (if this is what you want)
            ((Node)(event.getSource())).getScene().getWindow().hide();
            
       
    }

    @FXML
    void item4Add(ActionEvent event) throws IOException {
          db_connection.getConnection();
       
          
             ItemId = 4;
            
                
                   String name,price;
            int id = ItemId;
            name = item4.getText();
            price = item4Price.getText();
            
        try
        {
               pst = conn.prepareStatement("INSERT INTO cart"
                      + " VALUES (?,?,?,?)"
                      );
            
            pst.setInt(1, id);
            pst.setString(2, name);
            pst.setString(3, price);
            pst.setInt(4, cart_ID);
            pst.executeUpdate();
             FileWriter myWriter = new FileWriter(logFile,true);
             myWriter.append(pst.toString()+" On: "+ dtf.format(now) + "\n");
             myWriter.close();
         
            
         
            
          
        }
        catch (SQLException ex)
        {
            Logger.getLogger(ItemsController.class.getName()).log(Level.SEVERE, null, ex);
        }
               Stage primaryStage =new Stage();
		Parent root =FXMLLoader.load(getClass().getResource("/view/cart.fxml"));
                primaryStage.getIcons().add(new Image("/images/foodordering.png"));
		Scene scene = new Scene(root,600,550);
		primaryStage.setScene(scene);
                primaryStage.setResizable(false);
		primaryStage.show();
                
            // Hide this current window (if this is what you want)
            ((Node)(event.getSource())).getScene().getWindow().hide();
            
    }

    @FXML
    void item5Add(ActionEvent event) throws IOException {
           db_connection.getConnection();
       
          
             ItemId = 5;
          
                 String name,price;
            int id = ItemId;
            name = item5.getText();
            price = item5Price.getText();
            
        try
        {
              pst = conn.prepareStatement("INSERT INTO cart"
                      + " VALUES (?,?,?,?)"
                      );
            
            pst.setInt(1, id);
            pst.setString(2, name);
            pst.setString(3, price);
            pst.setInt(4, cart_ID);
            pst.executeUpdate();
             FileWriter myWriter = new FileWriter(logFile,true);
             myWriter.append(pst.toString()+" On: "+ dtf.format(now) + "\n");
             myWriter.close();

        }
        catch (SQLException ex)
        {
            Logger.getLogger(ItemsController.class.getName()).log(Level.SEVERE, null, ex);
        }
                
                Stage primaryStage =new Stage();
		Parent root =FXMLLoader.load(getClass().getResource("/view/cart.fxml"));
                primaryStage.getIcons().add(new Image("/images/foodordering.png"));
		Scene scene = new Scene(root,600,550);
		primaryStage.setScene(scene);
                primaryStage.setResizable(false);
		primaryStage.show();
                
            // Hide this current window (if this is what you want)
            ((Node)(event.getSource())).getScene().getWindow().hide();
            
            
    }

    @FXML
    void item6Add(ActionEvent event) throws IOException {
           db_connection.getConnection();
       
          
             ItemId = 6;
          
                 String name,price;
            int id = ItemId;
            name = item6.getText();
            price = item6Price.getText();
            
        try
        {
              pst = conn.prepareStatement("INSERT INTO cart"
                      + " VALUES (?,?,?,?)"
                      );
            
            pst.setInt(1, id);
            pst.setString(2, name);
            pst.setString(3, price);
            pst.setInt(4, cart_ID);
            pst.executeUpdate();
             FileWriter myWriter = new FileWriter(logFile,true);
             myWriter.append(pst.toString()+" On: "+ dtf.format(now) + "\n");
             myWriter.close();
   
        }
        catch (SQLException ex)
        {
            Logger.getLogger(ItemsController.class.getName()).log(Level.SEVERE, null, ex);
        }
                
                Stage primaryStage =new Stage();
		Parent root =FXMLLoader.load(getClass().getResource("/view/cart.fxml"));
                primaryStage.getIcons().add(new Image("/images/foodordering.png"));
		Scene scene = new Scene(root,600,550);
		primaryStage.setScene(scene);
                primaryStage.setResizable(false);
		primaryStage.show();
                
            // Hide this current window (if this is what you want)
            ((Node)(event.getSource())).getScene().getWindow().hide();
            
            
    }

    @FXML
    void item7Add(ActionEvent event) throws IOException {
            db_connection.getConnection();
       
          
             ItemId = 7;
          
                 String name,price;
            int id = ItemId;
            name = item7.getText();
            price = item7Price.getText();
            
        try
        {
              pst = conn.prepareStatement("INSERT INTO cart"
                      + " VALUES (?,?,?,?)"
                      );
            
            pst.setInt(1, id);
            pst.setString(2, name);
            pst.setString(3, price);
            pst.setInt(4, cart_ID);
            pst.executeUpdate();
             FileWriter myWriter = new FileWriter(logFile,true);
             myWriter.append(pst.toString()+" On: "+ dtf.format(now) + "\n");
             myWriter.close();

        }
        catch (SQLException ex)
        {
            Logger.getLogger(ItemsController.class.getName()).log(Level.SEVERE, null, ex);
        }
                
                Stage primaryStage =new Stage();
		Parent root =FXMLLoader.load(getClass().getResource("/view/cart.fxml"));
                primaryStage.getIcons().add(new Image("/images/foodordering.png"));
		Scene scene = new Scene(root,600,550);
		primaryStage.setScene(scene);
                primaryStage.setResizable(false);
		primaryStage.show();
                
            // Hide this current window (if this is what you want)
            ((Node)(event.getSource())).getScene().getWindow().hide();
            
            
    }

    @FXML
    void item8Add(ActionEvent event) throws IOException {
             db_connection.getConnection();
       
          
             ItemId = 8;
          
                 String name,price;
            int id = ItemId;
            name = item8.getText();
            price = item8Price.getText();
            
        try
        {
            String sql = "INSERT INTO cart VALUES (?,?,?,?)";
              pst = conn.prepareStatement(sql);
            
            pst.setInt(1, id);
            pst.setString(2, name);
            pst.setString(3, price);
            pst.setInt(4, cart_ID);
            pst.executeUpdate();
             FileWriter myWriter = new FileWriter(logFile,true);
      myWriter.append(pst.toString()+" On: "+ dtf.format(now) + "\n");
          myWriter.close();
         
            
         
            
          
        }
        catch (SQLException ex)
        {
            Logger.getLogger(ItemsController.class.getName()).log(Level.SEVERE, null, ex);
        }
                
                Stage primaryStage =new Stage();
		Parent root =FXMLLoader.load(getClass().getResource("/view/cart.fxml"));
                primaryStage.getIcons().add(new Image("/images/foodordering.png"));
		Scene scene = new Scene(root,600,550);
		primaryStage.setScene(scene);
                primaryStage.setResizable(false);
		primaryStage.show();
                
            // Hide this current window (if this is what you want)
            ((Node)(event.getSource())).getScene().getWindow().hide();
            
            
    }
    
    @FXML
    void logOutButtonAction(ActionEvent event) throws IOException {
          Parent root = FXMLLoader.load(getClass().getResource("/foodorderingsystem/FXMLDocument.fxml"));
        Stage stage = new Stage();
        Scene scene = new Scene(root,625,440);
        stage.setTitle("Food Ordering System");
        stage.setScene(scene);
        stage.getIcons().add(new Image("/images/foodordering.png"));
        stage.setResizable(false);
        stage.show();
        ((Node)(event.getSource())).getScene().getWindow().hide();
    }
   
   
    @Override
    public void initialize(URL url, ResourceBundle rb) {
//         db_connection.getConnection();
          Image bg = new Image("/images/burger.jpg");
          BackgroundImage background = new BackgroundImage(bg,
                  BackgroundRepeat.NO_REPEAT,
                  BackgroundRepeat.NO_REPEAT,
                  BackgroundPosition.CENTER,
                  new BackgroundSize(250, 250, true, true, true, true)
          );
          Background img = new Background(background);
         
        hBox1.setAlignment(Pos.TOP_CENTER);
        hBox1.setMaxWidth(150);
        hBox1.setMaxHeight(150);
        hBox1.setBackground(img);
        
           Image bg2 = new Image("/images/pizza.jpg");
          BackgroundImage background2 = new BackgroundImage(bg2,
                  BackgroundRepeat.NO_REPEAT,
                  BackgroundRepeat.NO_REPEAT,
                  BackgroundPosition.CENTER,
                  new BackgroundSize(250, 250, true, true, true, true)
          );
          Background img2 = new Background(background2);
          
        hBox2.setAlignment(Pos.TOP_CENTER);
        hBox2.setMaxWidth(150);
        hBox2.setMaxHeight(150);
        hBox2.setBackground(img2);
        
          Image bg3 = new Image("/images/chicken wings.jpg");
          BackgroundImage background3 = new BackgroundImage(bg3,
                  BackgroundRepeat.NO_REPEAT,
                  BackgroundRepeat.NO_REPEAT,
                  BackgroundPosition.CENTER,
                  new BackgroundSize(250, 250, true, true, true, true)
          );
          Background img3= new Background(background3);
          
        hBox3.setAlignment(Pos.TOP_CENTER);
        hBox3.setMaxWidth(150);
        hBox3.setMaxHeight(150);
        hBox3.setBackground(img3);
        
        
          Image bg4 = new Image("/images/fries.jpg");
          BackgroundImage background4 = new BackgroundImage(bg4,
                  BackgroundRepeat.NO_REPEAT,
                  BackgroundRepeat.NO_REPEAT,
                  BackgroundPosition.CENTER,
                  new BackgroundSize(250, 250, true, true, true, true)
          );
          Background img4= new Background(background4);
          
        hBox4.setAlignment(Pos.TOP_CENTER);
        hBox4.setMaxWidth(150);
        hBox4.setMaxHeight(150);
        hBox4.setBackground(img4);
        
        
        
          Image bg5 = new Image("/images/pasta.jpg");
          BackgroundImage background5 = new BackgroundImage(bg5,
                  BackgroundRepeat.NO_REPEAT,
                  BackgroundRepeat.NO_REPEAT,
                  BackgroundPosition.CENTER,
                  new BackgroundSize(250, 250, true, true, true, true)
          );
          Background img5= new Background(background5);
          
        hBox5.setAlignment(Pos.TOP_CENTER);
        hBox5.setMaxWidth(150);
        hBox5.setMaxHeight(150);
        hBox5.setBackground(img5);
        
        
          Image bg6 = new Image("/images/spaghetti.jpg");
          BackgroundImage background6 = new BackgroundImage(bg6,
                  BackgroundRepeat.NO_REPEAT,
                  BackgroundRepeat.NO_REPEAT,
                  BackgroundPosition.CENTER,
                  new BackgroundSize(250, 250, true, true, true, true)
          );
          
          Background img6= new Background(background6);
          
        hBox6.setAlignment(Pos.TOP_CENTER);
        hBox6.setMaxWidth(150);
        hBox6.setMaxHeight(150);
        hBox6.setBackground(img6);
        
         Image bg7 = new Image("/images/kabab.jpg");
          BackgroundImage background7 = new BackgroundImage(bg7,
                  BackgroundRepeat.NO_REPEAT,
                  BackgroundRepeat.NO_REPEAT,
                  BackgroundPosition.CENTER,
                  new BackgroundSize(250, 250, true, true, true, true)
          );
          Background img7= new Background(background7);
          
        hBox7.setAlignment(Pos.TOP_CENTER);
        hBox7.setMaxWidth(150);
        hBox7.setMaxHeight(150);
        hBox7.setBackground(img7);
        
         Image bg8 = new Image("/images/knafeh.jpg");
          BackgroundImage background8 = new BackgroundImage(bg8,
                  BackgroundRepeat.NO_REPEAT,
                  BackgroundRepeat.NO_REPEAT,
                  BackgroundPosition.CENTER,
                  new BackgroundSize(250, 250, true, true, true, true)
          );
          Background img8= new Background(background8);
          
        hBox8.setAlignment(Pos.TOP_CENTER);
        hBox8.setMaxWidth(150);
        hBox8.setMaxHeight(150);
        hBox8.setBackground(img8);
    }    
    
}
