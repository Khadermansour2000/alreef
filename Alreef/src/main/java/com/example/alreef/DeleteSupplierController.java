package com.example.alreef;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DeleteSupplierController {
    @FXML
    TextField inputname;
    @FXML
    Button delete;
    @FXML
    Button home;

    private Stage stage;
    private Scene scene;
    private Parent root;
    String SQL;

    public void setDelete(ActionEvent e) throws SQLException, ClassNotFoundException {
        SQL="select * FROM supplier  WHERE (Sname = \"" + inputname.getText() + "\")";

        connecter connecter1 = new connecter();
        Connection con1 = connecter1.connectDB();
        System.out.println("Connection established");

        Statement stmt1 = con1.createStatement();
        ResultSet rs=stmt1.executeQuery(SQL);

        System.out.println("Connection closed");
        if(rs.next()==false){
            Alert alert=new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Unknown Supplier");
            alert.setContentText("there is no supplier with this name");
            alert.showAndWait();
            inputname.setText("");
        } else{
            SQL = "DELETE FROM supplier WHERE (Sname = \"" + inputname.getText() + "\")";

            try {
                connecter connecter = new connecter();
                Connection con = connecter.connectDB();
                System.out.println("Connection established");

                Statement stmt = con.createStatement();
                stmt.executeUpdate(SQL);

                stmt.close();

                con.close();
                System.out.println("Connection closed");
                inputname.setText("");
            } catch (ClassNotFoundException ex) {
                ex.printStackTrace();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }
    public void goHome(ActionEvent e)throws IOException {
        root= FXMLLoader.load(getClass().getResource("mainscene.fxml"));
        stage= (Stage)((javafx.scene.Node) e.getSource()).getScene().getWindow();
        scene=new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
