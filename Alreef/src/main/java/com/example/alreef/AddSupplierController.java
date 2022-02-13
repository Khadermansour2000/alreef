package com.example.alreef;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class AddSupplierController {
    @FXML
    private Button add;

    @FXML
    private TextField address;

    @FXML
    private Button home;

    @FXML
    private TextField inputname;

    @FXML
    private TextField inputphone;
    private Stage stage;
    private Scene scene;
    private Parent root;

    public void goHome(ActionEvent e) throws IOException {
        root= FXMLLoader.load(getClass().getResource("mainscene.fxml"));
        stage= (Stage)((javafx.scene.Node) e.getSource()).getScene().getWindow();
        scene=new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void setAddbutton(ActionEvent e) {

        String SQL = "INSERT INTO supplier (Sname, Saddress, Sphone) VALUES ('"+inputname.getText()+
                "','" +address.getText()+"',"+inputphone.getText()+")";


        try {
            connecter connecter =new connecter();
            Connection con= connecter.connectDB();
            System.out.println("Connection established");

            Statement stmt = con.createStatement();
            stmt.executeUpdate(SQL);
            stmt.close();
            con.close();

            System.out.println("Connection closed");
            inputname.setText("");
            inputphone.setText("");
            address.setText("");

        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }


    }
}
