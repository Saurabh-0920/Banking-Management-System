
package dashboard;


import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import login.LoginScreenController;


public class MainScreenController implements Initializable {

    @FXML
    private Label name;
    
    @FXML
    private Label body;
    
    
    
    public void setInfo(){
        Connection con=null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        
        try{
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/bank?serverTimezone=UTC" , "root", "");
            String sql = "SELECT * FROM userdata WHERE AccountNo=?";
            ps = con.prepareStatement(sql);
            ps.setString(1, LoginScreenController.acc);
            
            
             rs = ps.executeQuery();
            if(rs.next()){
               name.setText(rs.getString("Name"));
               
               
                
                
            }
            else
            {   Alert a = new Alert(Alert.AlertType.ERROR);
                a.setTitle("Error");
                a.setHeaderText("Error In Login");
                a.setContentText("Your Account Number or PIN is wrong \nPlease enter again !" );
                a.showAndWait();
                
                
            }
            
            
        }catch(Exception e){
                Alert a = new Alert(Alert.AlertType.ERROR);
                a.setTitle("Error");
                a.setHeaderText("Error in login .");
                a.setContentText("There is some error \nPLEASE TRY AGAIN !");
                a.showAndWait();
            
        }
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        body.setText("This is your personal Bank Account Management System\n" +
"\n" +
"You can:\n" +
"Deposit Money\n" +
"Withdraw Money\n" +
"Transfer Money\n" +
"Change PIN");
        setInfo();
    }    
    
}
