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
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
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
 * @author mohdo
 */
public class CartController implements Initializable {

    static int orderId;
    PreparedStatement pst;
    ResultSet rs;

    @FXML
    private Button cancelAllButton;

    @FXML
    private Button showButton;

    @FXML
    private Button confirmOrderButton;

    @FXML
    private TableColumn<Cart, Integer> itemIdColumn;

    @FXML
    private TableColumn<Cart, String> itemNameColumn;

    @FXML
    private TableColumn<Cart, String> priceColumn;

    @FXML
    private Button menuButton;

    @FXML
    private Button orderButton;

    @FXML
    private Button cartButton;

    @FXML
    private Button logOutButton;

    @FXML
    private Button settingsButton;

    @FXML
    private TableView<Cart> cartTableView;

    PreparedStatement statement;
    File logFile = new File("logfile.txt");
    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
    LocalDateTime now = LocalDateTime.now();

    @FXML
    void confirmOrderButtonAction(ActionEvent event) throws SQLException, IOException {
        db_connection.getConnection();

        String timeStamp = new SimpleDateFormat("yyyy/MM/dd_HH:mm:ss").format(Calendar.getInstance().getTime());
        int userId, cartId;
        String delivred = "No";
        userId = SignUpController.id;
        cartId = SignUpController.cartId;

        pst = conn.prepareStatement("INSERT INTO orders"
                + " VALUES (?,?,?,?,?)"
        );

        pst.setInt(1, orderId);
        pst.setInt(2, cartId);
        pst.setString(3, timeStamp);
        pst.setString(4, delivred);
        pst.setInt(5,userId );
        pst.executeUpdate();
        FileWriter myWriter = new FileWriter(logFile, true);
        myWriter.append(pst.toString() + " On: " + dtf.format(now) + "\n");
        myWriter.close();

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
        Scene scene = new Scene(root, 640, 625);
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

        ((Node) (event.getSource())).getScene().getWindow().hide();
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        db_connection.getConnection();

        ObservableList<Cart> cart = FXCollections.observableArrayList();

        try {
            statement = conn.prepareStatement("select itemId,itemName,itemPrice from cart where cart_id = '" + SignUpController.cartId + "'");
             rs = statement.executeQuery();
          
        } catch (SQLException ex) {
            Logger.getLogger(CartController.class.getName()).log(Level.SEVERE, null, ex);
        }
   

       

        try {
            while (rs.next()) {
                int itemId = rs.getInt("itemId");
                String itemName = rs.getString("itemName");
                String price = rs.getString("itemPrice");
                Cart c = new Cart(itemId, itemName, price);
                cart.add(c);
            }
        } catch (SQLException ex) {
            Logger.getLogger(CartController.class.getName()).log(Level.SEVERE, null, ex);
        }

        itemIdColumn.setCellValueFactory(new PropertyValueFactory<Cart, Integer>("itemId"));
        itemNameColumn.setCellValueFactory(new PropertyValueFactory<Cart, String>("itemName"));
        priceColumn.setCellValueFactory(new PropertyValueFactory<Cart, String>("price"));

        cartTableView.setItems(cart);
    }
}
