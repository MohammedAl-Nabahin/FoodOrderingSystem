    /*
     * To change this license header, choose License Headers in Project Properties.
     * To change this template file, choose Tools | Templates
     * and open the template in the editor.
     */
    package Control;

import static Control.db_connection.conn;
//import static Control.signInController.cartId;
import Model.user;
import java.io.File;
import java.io.FileWriter;
//import static food_fxml.signInController.userId;
    import java.io.IOException;
    import java.net.URL;
    import java.sql.Connection;
    import java.sql.DriverManager;
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
    import javafx.scene.control.Button;
    import javafx.scene.control.PasswordField;
    import javafx.scene.control.TextField;
    import javafx.scene.image.Image;
    import javafx.stage.Stage;

    /**
     * FXML Controller class
     *
     * @author mohdo
     */
    public class SignUpController implements Initializable {


        private static  Connection conn = null;
       private static String url = "jdbc:mysql://127.0.0.1:3306/fos";  //127.0.0.1 same as : localhost  // port:3306
       private static String username = "root";
       private static String password = "";
       ResultSet rs;
        static int userId;
        static int id;
        static int cartId;
       user user1 = new user();


       public static void getConnection(){
           try {
               Class.forName("com.mysql.jdbc.Driver");
               conn = DriverManager.getConnection(url, username, password);
               System.out.println("Connection OK");

           } catch (ClassNotFoundException ex) {
               Logger.getLogger(SignUpController.class.getName()).log(Level.SEVERE, null, ex);
           } catch (SQLException ex) {
               Logger.getLogger(SignUpController.class.getName()).log(Level.SEVERE, null, ex);
           }
       }
       
       static int cart;
      
       PreparedStatement pst;

       @FXML
        private TextField addressField;

        @FXML
        private Button cancelButton;

        @FXML
        private TextField emailField;

         @FXML
        private PasswordField passwordField;

        @FXML
        private TextField phoneField;

        @FXML
        private Button signUpButton;
          
        @FXML
        private TextField userNameField;
         File logFile = new File("logfile.txt");
          DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");  
          LocalDateTime now = LocalDateTime.now();  

        @FXML
        void cancelAction(ActionEvent event) throws IOException {
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
        void signUpAction(ActionEvent event) throws IOException, SQLException {
             getConnection();
             
             
             pst = conn.prepareStatement("select id from user ORDER BY id DESC LIMIT 1");
//             pst = conn.prepareStatement("select cartId from user WHERE name='"+userNameTextField.getText()+"'");
       
             rs = pst.executeQuery();
            
            FileWriter myWriter = new FileWriter(logFile, true);
            myWriter.append(pst.toString() + " On: " + dtf.format(now) + "\n");
      
     
             while(rs.next()){
               
                 user1.setUserId(Integer.parseInt(rs.getString("id")));
                 userId = user1.getUserId();
                 System.out.println(userId);
                   
                      userId++;
                  
                    id = userId;
             }
        
           
                  
                    
               
                    pst = conn.prepareStatement("select cartId from user ORDER BY cartId DESC LIMIT 1");
            try {
                rs = pst.executeQuery();

                myWriter.append(pst.toString() + " On: " + dtf.format(now) + "\n");
                myWriter.close();
                while (rs.next()) {

                    user1.setCartId(Integer.parseInt(rs.getString("cartId")));
                    cartId = user1.getCartId();
                      cartId++;
                    System.out.println(cartId);
                }
            } catch (SQLException ex) {
                Logger.getLogger(signInController.class.getName()).log(Level.SEVERE, null, ex);
            }      
                       
            
               String name,email,phone,password,address;
                name = userNameField.getText();
                email = emailField.getText();
                phone = phoneField.getText();
                password =Encryption.md5Hash(passwordField.getText());
                address = addressField.getText();

            try
            {
                pst = conn.prepareStatement("insert into user values(?,?,?,?,?,?,?)");
                pst.setInt(1, id);
                pst.setString(2, name);
                pst.setString(3, email);
                pst.setString(4, password);
                pst.setString(5, phone);            
                pst.setString(6, address);
                pst.setInt(7, cartId);
                pst.executeUpdate();
               
//              
//      myWriter.append(pst.toString()+" On: "+ dtf.format(now) + "\n");
//          myWriter.close();
                


            }
            catch (SQLException ex)
            {
                Logger.getLogger(SignUpController.class.getName()).log(Level.SEVERE, null, ex);
            }

             Stage primaryStage =new Stage();
                    Parent root =FXMLLoader.load(getClass().getResource("/view/signIn.fxml"));
                    primaryStage.getIcons().add(new Image("/images/foodordering.png"));
                    Scene scene = new Scene(root,600,550);
                    primaryStage.setScene(scene);
                    primaryStage.setResizable(false);
                    primaryStage.show();
                ((Node)(event.getSource())).getScene().getWindow().hide();

        }
        
       
     

        @Override
        public void initialize(URL url, ResourceBundle rb) {
            addressField.setPromptText("Address");
            userNameField.setPromptText("User Name");
            passwordField.setPromptText("Password");
            phoneField.setPromptText("Phone Number");
            emailField.setPromptText("Email");
        }    

    }
