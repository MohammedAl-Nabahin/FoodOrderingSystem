/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Control;

import static Control.db_connection.conn;
import Model.Cart;
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
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author mohdo
 */
public class YourCartController implements Initializable {

    /**
     * Initializes the controller class.
     */
    
    PreparedStatement pst;
    ResultSet rs;
    @FXML
    private Button backButton;

    @FXML
    private TableView<Cart> cartTableView;

    @FXML
    private Button deleteButton;

    @FXML
    private TableColumn<Cart, Integer> itemIdColumn;

    @FXML
    private Label itemIdLabel;

    @FXML
    private TableColumn<Cart, String> itemNameColumn;

    @FXML
    private TableColumn<Cart, String> priceColumn;
    File logFile = new File("logfile.txt");
    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
    LocalDateTime now = LocalDateTime.now();

    @FXML
    void backButtonAction(ActionEvent event) throws IOException {
         Stage primaryStage =new Stage();
		Parent root =FXMLLoader.load(getClass().getResource("/view/settings.fxml"));
                primaryStage.getIcons().add(new Image("/images/foodordering.png"));
		Scene scene = new Scene(root,640,640);
		primaryStage.setScene(scene);
                primaryStage.setResizable(false);
		primaryStage.show();
            ((Node)(event.getSource())).getScene().getWindow().hide();
    }

    @FXML
    void deleteButtonAction(ActionEvent event) throws SQLException, IOException {
          db_connection.getConnection();
          pst = conn.prepareStatement("delete from cart where itemId = ? ");
            pst.setInt(1,Integer.parseInt(itemIdLabel.getText()));
                pst.executeUpdate();
             
                FileWriter myWriter = new FileWriter(logFile,true);
      myWriter.append(pst.toString()+" On: "+ dtf.format(now) + "\n");
// 
           
             ObservableList<Cart> carts = FXCollections.observableArrayList();
       try
       {
           pst = conn.prepareStatement("select itemId,itemName,itemPrice from cart where cart_id = '"+SignUpController.cartId+"'");  
           ResultSet rs = pst.executeQuery();
      myWriter.append(pst.toString()+" On: "+ dtf.format(now) + "\n");
      myWriter.close();
      {
        while (rs.next())
        {
            Cart cart = new Cart();
            cart.setItemId(Integer.parseInt(rs.getString("itemId")));
            cart.setItemName(rs.getString("itemName"));
            cart.setPrice(rs.getString("itemPrice"));
            carts.add(cart);
       }
    }
        itemIdColumn.setCellValueFactory(new PropertyValueFactory<Cart,Integer>("itemId"));
        itemNameColumn.setCellValueFactory(new PropertyValueFactory<Cart,String>("itemName"));
        priceColumn.setCellValueFactory(new PropertyValueFactory<Cart,String>("price"));
    
        cartTableView.setItems(carts);
       }
       catch (SQLException ex)
       {
           Logger.getLogger(YourCartController.class.getName()).log(Level.SEVERE, null, ex);
       }   
     
    }
    
     public void select(){
        
        Cart cart = cartTableView.getSelectionModel().getSelectedItem();
        if (cart != null) {
            itemIdLabel.setText(String.valueOf(cart.getItemId()));
        }

    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
         cartTableView.getSelectionModel()
                .selectedItemProperty().addListener(e -> {
                    this.select();
                });
         
         
            ObservableList<Cart> carts = FXCollections.observableArrayList();
       try
       {
           pst = conn.prepareStatement("select itemId,itemName,itemPrice from cart where cart_id = '"+SignUpController.cartId+"'");  
           ResultSet rs = pst.executeQuery();
            
                FileWriter myWriter = new FileWriter(logFile,true);
      myWriter.append(pst.toString()+" On: "+ dtf.format(now) + "\n");
      myWriter.close();
      {
        while (rs.next())
        {
            Cart cart = new Cart();
            cart.setItemId(Integer.parseInt(rs.getString("itemId")));
            cart.setItemName(rs.getString("itemName"));
            cart.setPrice(rs.getString("itemPrice"));
            carts.add(cart);
       }
    }
        itemIdColumn.setCellValueFactory(new PropertyValueFactory<Cart,Integer>("itemId"));
        itemNameColumn.setCellValueFactory(new PropertyValueFactory<Cart,String>("itemName"));
        priceColumn.setCellValueFactory(new PropertyValueFactory<Cart,String>("price"));
    
        cartTableView.setItems(carts);
       }
       catch (SQLException ex)
       {
           Logger.getLogger(YourCartController.class.getName()).log(Level.SEVERE, null, ex);
       } catch (IOException ex) {   
            Logger.getLogger(YourCartController.class.getName()).log(Level.SEVERE, null, ex);
        }   
     
    }    
    
}
