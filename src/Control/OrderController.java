/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Control;

import static Control.db_connection.conn;
import Model.Order;
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
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Anas
 */
public class OrderController implements Initializable {
    
      @FXML
    private Button cartButton;

    @FXML
    private TableColumn<Order, Integer> cartIdColumn;

    @FXML
    private Button logOutButton;

    @FXML
    private Button menuButton;
    
    @FXML
    private Button cancelOrderButton;


    @FXML
    private Button orderButton;

    @FXML
    private TableColumn<Order, Integer> orderIdColumn;
    
     @FXML
    private TableColumn<Order, String> deliveredCol;


    @FXML
    private TableView<Order> orderTableView;

    @FXML
    private Button settingsButton;

    @FXML
    private TableColumn<Order, String> timeColumn;

    @FXML
    private TableColumn<Order,Integer> userIdColumn;
    PreparedStatement pst;
    ResultSet rs;
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
                
            // Hide this current window (if this is what you want)
            ((Node)(event.getSource())).getScene().getWindow().hide();
    }

    @FXML
    void orderButtonAction(ActionEvent event) throws IOException {
         Stage primaryStage =new Stage();
		Parent root =FXMLLoader.load(getClass().getResource("/view/order.fxml"));
                primaryStage.getIcons().add(new Image("/images/foodordering.png"));
		Scene scene = new Scene(root,640,640);
		primaryStage.setScene(scene);
                primaryStage.setResizable(false);
		primaryStage.show();
                
            // Hide this current window (if this is what you want)
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

    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
          db_connection.getConnection();
    
         ObservableList<Order> order = FXCollections.observableArrayList();
//       int USERID = SignUpController.userId;
//       where user_id = '"+USERID+"'
         try {
             pst = conn.prepareStatement("select * from orders");
         } catch (SQLException ex) {
             Logger.getLogger(CartController.class.getName()).log(Level.SEVERE, null, ex);
         }
        
         try {
             rs = pst.executeQuery();
              FileWriter myWriter = new FileWriter(logFile,true);
      myWriter.append(pst.toString()+" On: "+ dtf.format(now) + "\n");
          myWriter.close();
         } catch (SQLException ex) {
             Logger.getLogger(CartController.class.getName()).log(Level.SEVERE, null, ex);
         } catch (IOException ex) {
              Logger.getLogger(OrderController.class.getName()).log(Level.SEVERE, null, ex);
          }
        
         try {
             while(rs.next()){
                 int orderId = rs.getInt("orderId");
                 int userId = rs.getInt("user_id");
                 int cartId = rs.getInt("cart_id");
                 String timeStamp = rs.getString("time_stamp");
                 String delivred = rs.getString("delivered");
                 Order o = new Order( orderId,userId ,cartId, timeStamp,delivred);
                 order.add(o);    
             }    } catch (SQLException ex) {
             Logger.getLogger(CartController.class.getName()).log(Level.SEVERE, null, ex);
         }
         
           orderIdColumn.setCellValueFactory(new PropertyValueFactory<Order,Integer>("orderId"));
        userIdColumn.setCellValueFactory(new PropertyValueFactory<Order,Integer>("userId"));
        cartIdColumn.setCellValueFactory(new PropertyValueFactory<Order,Integer>("cartId"));
        timeColumn.setCellValueFactory(new PropertyValueFactory<Order,String>("timeStamp"));
        deliveredCol.setCellValueFactory(new PropertyValueFactory<Order,String>("delivred"));

        orderTableView.setItems(order);
    }  
    }    
    

