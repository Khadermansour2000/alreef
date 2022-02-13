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

public class AddMeterialController {
    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    private TextField inputname;
    @FXML
    private TextField inputprice;
    @FXML
    private Button home;
    String SQL;
    public void setAddbutton(ActionEvent e) {

        SQL = "INSERT INTO matrial (Mname, price) VALUES ('" + inputname.getText() + "'," + inputprice.getText() + ")";

        try {
            connecter connecter =new connecter();
            Connection con= connecter.connectDB();
            System.out.println("Connection established");

            Statement stmt = con.createStatement();
            stmt.executeUpdate(SQL);
            stmt.close();
            con.close();

            SQL= "SELECT MAX(NO_order) FROM per_order";


            System.out.println("Connection closed");
            inputname.setText("");
            inputprice.setText("");
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }


    }

    public void goHome(ActionEvent e) throws IOException {
        root= FXMLLoader.load(getClass().getResource("mainscene.fxml"));
        stage= (Stage)((javafx.scene.Node) e.getSource()).getScene().getWindow();
        scene=new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
