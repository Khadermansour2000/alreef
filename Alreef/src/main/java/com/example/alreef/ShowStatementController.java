package com.example.alreef;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class ShowStatementController implements Initializable {

    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    private TableColumn<StatementLineRecord, Integer> NO_order;

    @FXML
    private TableColumn<StatementLineRecord, Integer> Qan;

    @FXML
    private Label fdate;

    @FXML
    private TableColumn<StatementLineRecord, Integer> id;

    @FXML
    private TableColumn<StatementLineRecord, String> date;

    @FXML
    private Label ototal;

    @FXML
    private TableColumn<StatementLineRecord, Double> price;

    @FXML
    private Label sID;

    @FXML
    private Label sname;

    @FXML
    private Label tdate;

    @FXML
    private TableColumn<StatementLineRecord, Double> total;

    @FXML
    private TableView<StatementLineRecord> table;

    @FXML
    void goBack(ActionEvent e) throws IOException {
        root = FXMLLoader.load(getClass().getResource("SupplierAccountStatement.fxml"));
        stage = (Stage) ((javafx.scene.Node) e.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void goToHome(ActionEvent e) throws IOException {
        root = FXMLLoader.load(getClass().getResource("mainscene.fxml"));
        stage = (Stage) ((javafx.scene.Node) e.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ReportRecord.ototal = 0.0;
        ObservableList<StatementLineRecord> list = getData();

        ototal.setText(String.valueOf(ReportRecord.ototal));
        sname.setText(ReportRecord.name);
        sID.setText(ReportRecord.ID);
        fdate.setText(ReportRecord.fdate);
        tdate.setText(ReportRecord.tdate);

        NO_order.setCellValueFactory(new PropertyValueFactory<>("Number"));
        date.setCellValueFactory(new PropertyValueFactory<>("date"));
        id.setCellValueFactory(new PropertyValueFactory<>("id"));
        Qan.setCellValueFactory(new PropertyValueFactory<>("Qan"));
        total.setCellValueFactory(new PropertyValueFactory<>("total"));
        price.setCellValueFactory(new PropertyValueFactory<>("price"));

        table.setItems(list);

    }

    public static ObservableList<StatementLineRecord> getData(){
        connecter connecter = new connecter();
        System.out.println("select * from per_order where Sid = " + ReportRecord.ID +
                " AND ODate between '" + ReportRecord.fdate + "' AND '" +
                ReportRecord.tdate + "'");

        String SQL = "select * from per_order where Sid = "+ ReportRecord.ID+" AND ODate between '"+ReportRecord.fdate+"' AND '"+ReportRecord.tdate+"' ";
        String SQL2 = null;
        ObservableList<StatementLineRecord> list = FXCollections.observableArrayList();

        try {
            String ordernumber = new String();
            String orderdate = new String();
            Connection con = connecter.connectDB();
            PreparedStatement st = con.prepareStatement(SQL);
            ResultSet rs = st.executeQuery();
            PreparedStatement st2 = null;
            ResultSet rs2 = null;
            while (rs.next()) {
                ordernumber = rs.getString("NO_order");
                orderdate = rs.getString("ODate");
                SQL2 = "select * from per_line where NO_order= " + ordernumber;
                st2 = con.prepareStatement(SQL2);
                rs2 = st2.executeQuery();

                while (rs2.next()) {
                    list.add(new StatementLineRecord(Integer.parseInt(ordernumber),
                            orderdate,
                            Integer.parseInt(rs2.getString("Id")),
                            Double.parseDouble(rs2.getString("price")),
                            Integer.parseInt(rs2.getString("Qan"))));
                    ReportRecord.ototal = ReportRecord.ototal +
                            Double.parseDouble(rs2.getString("price")) *
                                    Integer.parseInt(rs2.getString("Qan"));

                }

            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
}
