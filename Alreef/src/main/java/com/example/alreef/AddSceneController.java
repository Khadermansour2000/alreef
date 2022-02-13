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
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AddSceneController {
    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    private TextField ID;

    @FXML
    private Button add;

    @FXML
    private Button done;

    @FXML
    private TextField quantity;
    String SQL;

    public void setAdd(ActionEvent e) throws SQLException, ClassNotFoundException {
        connecter connecter = new connecter();
        String SQL = "select Mname,price from matrial where Id=" + ID.getText();

        Connection con = connecter.connectDB();
        PreparedStatement st = con.prepareStatement(SQL);
        ResultSet rs = st.executeQuery();

        while (rs.next()) {
            SQL = "insert INTO per_line values(" + AddLineData.Onumber + ",'" +
                    rs.getString("Mname") + "'," +
                    ID.getText() + "," +
                    rs.getString("price") + "," +
                    quantity.getText() +
                    ")";
            System.out.println("Connection established");
            connecter connecter1 = new connecter();
            Connection con1 = connecter1.connectDB();
            PreparedStatement st1 = con1.prepareStatement(SQL);
            st1.executeUpdate();
            con1.close();
            st1.close();
            AddLineData.total = AddLineData.total +
                    (Double.parseDouble(rs.getString("price")) * Integer.parseInt(quantity.getText()));
            System.out.println("Connection closed");

        }
        con.close();
        st.close();
        ID.setText("");
        quantity.setText("");

    }

    public void done(ActionEvent e) throws IOException {
        root = FXMLLoader.load(getClass().getResource("CreateOrder.fxml"));
        stage = (Stage) ((javafx.scene.Node) e.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }

}
