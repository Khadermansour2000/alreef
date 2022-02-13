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
import java.util.Objects;

public class DeleteMaterialController {
    @FXML
    TextField inputname;
    @FXML
    Button home;

    String SQL;

    public void setDelete() throws SQLException, ClassNotFoundException {
        SQL="select * FROM matrial WHERE (Mname = \"" + inputname.getText() + "\")";

        connecter connecter1 = new connecter();
        Connection con1 = connecter1.connectDB();
        System.out.println("Connection established");

        Statement stmt1 = con1.createStatement();
        ResultSet rs=stmt1.executeQuery(SQL);

        System.out.println("Connection closed");

        if(!rs.next()){
            Alert alert=new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Unknown Material");
            alert.setContentText("there is no material with this name");
            alert.showAndWait();
            inputname.setText("");

        }else {
            SQL = "DELETE FROM matrial WHERE (Mname = \"" + inputname.getText() + "\")";

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

            } catch (ClassNotFoundException | SQLException ex) {
                ex.printStackTrace();
            }
        }
        stmt1.close();

        con1.close();
    }
    public void goHome(ActionEvent e)throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("mainscene.fxml")));
        Stage stage = (Stage) ((javafx.scene.Node) e.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
