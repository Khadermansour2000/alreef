package com.example.alreef;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

public class CreateOrderController implements Initializable {
    private Stage stage;
    private Scene scene;
    private Parent root;


    @FXML
    private TableColumn<OrderLineRecord, Integer> ID;

    @FXML
    private TableColumn<OrderLineRecord, String> Mname;

    @FXML
    private DatePicker Odate;

    @FXML
    private Label showname;

    @FXML
    private Label Ototel;

    @FXML
    private TextField Sid;

    @FXML
    private Button add;

    @FXML
    private Button home;

    @FXML
    private Label name;

    @FXML
    private TableColumn<OrderLineRecord, Double> price;

    @FXML
    private TableColumn<OrderLineRecord, Integer> quantity;

    @FXML
    private Button save;

    @FXML
    private TableColumn<OrderLineRecord, Double> total;

    @FXML
    private TableView<OrderLineRecord> tabel;

    public void setAdd(ActionEvent e) throws IOException, SQLException, ClassNotFoundException {

        AddLineData.Odate = null;
        AddLineData.Onumber = null;
        AddLineData.id = null;
        AddLineData.total = 0.0;

        connecter connecter = new connecter();
        Connection con = connecter.connectDB();

        System.out.println("Connection established");
        String SQL = "INSERT INTO per_order (Sid, ODate) Values (" + Sid.getText() + ", \"" + Odate.getValue() + "\")";

        Statement stmt = con.createStatement();
        stmt.executeUpdate(SQL);
        stmt.close();
        con.close();

        connecter connecter1 = new connecter();
        Connection con1 = connecter1.connectDB();

        Statement stmt1 = con1.createStatement();
        ResultSet rs = stmt1.executeQuery("SELECT NO_order FROM per_order");
        String oNumber = new String();


        while (rs.next()) oNumber = rs.getString("NO_order");
        System.out.println(oNumber);

        stmt1.close();
        con1.close();
        System.out.println("Connection closed");

        AddLineData.Onumber = Integer.parseInt(oNumber);
        AddLineData.Odate = Odate.getValue();
        AddLineData.id = ID.getText();
        root = FXMLLoader.load(getClass().getResource("AddScene.fxml"));
        stage = (Stage) ((javafx.scene.Node) e.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }

    public void goHome(ActionEvent e) throws IOException {
        root = FXMLLoader.load(getClass().getResource("mainscene.fxml"));
        stage = (Stage) ((javafx.scene.Node) e.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ID.setText(AddLineData.id);
        showname.setText(AddLineData.name);
        Odate.setValue(AddLineData.Odate);
        ObservableList<OrderLineRecord> list = getData();
        ID.setCellValueFactory(new PropertyValueFactory<>("id"));
        Mname.setCellValueFactory(new PropertyValueFactory<>("name"));
        price.setCellValueFactory(new PropertyValueFactory<>("price"));
        quantity.setCellValueFactory(new PropertyValueFactory<>("Qan"));
        total.setCellValueFactory(new PropertyValueFactory<>("total"));

        tabel.setItems(list);
        Ototel.setText(String.valueOf(AddLineData.total));
        connecter connecter = new connecter();
        String SQL = "update per_order set Total= " + AddLineData.total + " where NO_order=" + AddLineData.Onumber;

        PreparedStatement st = null;
        try {
            Connection con = connecter.connectDB();
            st = con.prepareStatement(SQL);
            st.executeUpdate();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }


    }

    public static ObservableList<OrderLineRecord> getData() {
        connecter connecter = new connecter();
        String SQL = "select * from per_Line where NO_order=" + AddLineData.Onumber;
        ObservableList<OrderLineRecord> list = FXCollections.observableArrayList();

        try {
            Connection con = connecter.connectDB();
            PreparedStatement st = con.prepareStatement(SQL);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                list.add(new OrderLineRecord(Integer.parseInt(rs.getString("NO_order")),
                        rs.getString("Mname"),
                        Integer.parseInt(rs.getString("Id")),
                        Double.parseDouble(rs.getString("price")),
                        Integer.parseInt(rs.getString("Qan"))));
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public void getname() throws SQLException, ClassNotFoundException {

        String SQL = "select * from supplier where Sid=" + Sid.getText();
        connecter connecter = new connecter();
        Connection con = connecter.connectDB();
        PreparedStatement st = con.prepareStatement(SQL);
        ResultSet rs = st.executeQuery();
        String SNAME = new String();

        if (rs.next() == false) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Supplier ID");
            alert.setContentText("there is no Supplier with this ID");
            alert.showAndWait();
            Sid.setText("");
            name.setText("");
        } else {

            SNAME = rs.getString("Sname");
            showname.setText(SNAME);
            AddLineData.name = SNAME;
        }

    }
}
