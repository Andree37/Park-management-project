package program;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import model.Emission.Bill;
import model.Emission.PDFFiles;
import model.Emission.PrintableDAO;
import model.Emission.Ticket;
import model.Gestor.ResultadoPercurso;

public class CheckoutUI extends AnchorPane {

    protected final Label lblCheckout;
    protected final Label lblPlaces;
    protected final Label lblPaths;
    protected final TextField txtFieldPlaces;
    protected final TextField txtFieldPaths;
    protected final AnchorPane anchorPane;
    protected final Label lblBill;
    protected final Label lblNif;
    protected final Label lblName;
    protected final Label lblAddress;
    protected final TextField txtFieldNif;
    protected final TextField txtFieldName;
    protected final TextField txtFieldAddress;
    protected final Label lblBack;
    protected final Button btnBack;
    protected final AnchorPane anchorPane0;
    protected final Label lblTicket;
    protected final Label lblTotalDistance;
    protected final TextField txtFieldTotalDistance;
    protected final Label lblBikes;
    protected final TextField txtFieldBikes;
    protected final Label lblBridges;
    protected final TextField txtFieldBridges;
    protected final Label lblDate;
    protected final TextField txtFieldDate;
    protected final Label lblHour;
    protected final TextField txtFieldHour;
    protected final Label lblWithBill;
    protected final RadioButton radioYes;
    protected final RadioButton radioNo;
    protected final Label lblCost;
    protected final TextField txtFieldCost;
    protected final Button btnSubmit;
    protected ResultadoPercurso result;
    protected PrintableDAO dao;

    public CheckoutUI(ResultadoPercurso result, PrintableDAO dao) {

        this.dao = dao;
        this.result = result;
        lblCheckout = new Label();
        lblPlaces = new Label();
        lblPaths = new Label();
        txtFieldPlaces = new TextField();
        txtFieldPaths = new TextField();
        anchorPane = new AnchorPane();
        lblBill = new Label();
        lblNif = new Label();
        lblName = new Label();
        lblAddress = new Label();
        txtFieldNif = new TextField();
        txtFieldName = new TextField();
        txtFieldAddress = new TextField();
        lblBack = new Label();
        btnBack = new Button();
        anchorPane0 = new AnchorPane();
        lblTicket = new Label();
        lblTotalDistance = new Label();
        txtFieldTotalDistance = new TextField();
        lblBikes = new Label();
        txtFieldBikes = new TextField();
        lblBridges = new Label();
        txtFieldBridges = new TextField();
        lblDate = new Label();
        txtFieldDate = new TextField();
        lblHour = new Label();
        txtFieldHour = new TextField();
        lblWithBill = new Label();
        radioYes = new RadioButton();
        radioNo = new RadioButton();
        lblCost = new Label();
        txtFieldCost = new TextField();
        btnSubmit = new Button();

        setId("AnchorPane");
        setMaxHeight(USE_PREF_SIZE);
        setMaxWidth(USE_PREF_SIZE);
        setMinHeight(USE_PREF_SIZE);
        setMinWidth(USE_PREF_SIZE);
        setPrefHeight(600.0);
        setPrefWidth(900.0);

        lblCheckout.setLayoutX(14.0);
        lblCheckout.setLayoutY(14.0);
        lblCheckout.setPrefHeight(21.0);
        lblCheckout.setPrefWidth(84.0);
        lblCheckout.setText("CHECKOUT");
        lblCheckout.setFont(new Font(15.0));

        lblPlaces.setLayoutX(14.0);
        lblPlaces.setLayoutY(46.0);
        lblPlaces.setPrefHeight(32.0);
        lblPlaces.setPrefWidth(175.0);
        lblPlaces.setText("Places to visit:");
        lblPlaces.setFont(new Font(22.0));

        lblPaths.setLayoutX(14.0);
        lblPaths.setLayoutY(107.0);
        lblPaths.setText("Paths to travel:");
        lblPaths.setFont(new Font(22.0));

        txtFieldPlaces.setEditable(false);
        txtFieldPlaces.setLayoutX(14.0);
        txtFieldPlaces.setLayoutY(78.0);
        txtFieldPlaces.setPrefHeight(25.0);
        txtFieldPlaces.setPrefWidth(384.0);

        txtFieldPlaces.setText("" + result.getListPlacesCopy());

        txtFieldPaths.setEditable(false);
        txtFieldPaths.setLayoutX(14.0);
        txtFieldPaths.setLayoutY(139.0);
        txtFieldPaths.setPrefHeight(25.0);
        txtFieldPaths.setPrefWidth(384.0);

        txtFieldPaths.setText("" + result.getListConnectionsCopy());

        anchorPane.setLayoutX(14.0);
        anchorPane.setLayoutY(211.0);
        anchorPane.setPrefHeight(366.0);
        anchorPane.setPrefWidth(384.0);

        lblBill.setLayoutX(10.0);
        lblBill.setLayoutY(14.0);
        lblBill.setPrefHeight(30.0);
        lblBill.setPrefWidth(46.0);
        lblBill.setText("BILL:");
        lblBill.setFont(new Font(20.0));

        lblNif.setLayoutX(14.0);
        lblNif.setLayoutY(110.0);
        lblNif.setText("NIF:");
        lblNif.setFont(new Font(22.0));

        lblName.setLayoutX(15.0);
        lblName.setLayoutY(184.0);
        lblName.setText("Name:");
        lblName.setFont(new Font(22.0));

        lblAddress.setLayoutX(15.0);
        lblAddress.setLayoutY(262.0);
        lblAddress.setText("Address:");
        lblAddress.setFont(new Font(22.0));

        txtFieldNif.setLayoutX(15.0);
        txtFieldNif.setLayoutY(142.0);
        txtFieldNif.setPrefHeight(25.0);
        txtFieldNif.setPrefWidth(328.0);

        txtFieldName.setLayoutX(15.0);
        txtFieldName.setLayoutY(213.0);
        txtFieldName.setPrefHeight(25.0);
        txtFieldName.setPrefWidth(328.0);

        txtFieldAddress.setLayoutX(15.0);
        txtFieldAddress.setLayoutY(296.0);
        txtFieldAddress.setPrefHeight(25.0);
        txtFieldAddress.setPrefWidth(328.0);

        lblBack.setLayoutX(568.0);
        lblBack.setLayoutY(16.0);
        lblBack.setPrefHeight(30.0);
        lblBack.setPrefWidth(175.0);
        lblBack.setText("Forgot something?");
        lblBack.setFont(new Font(20.0));

        btnBack.setLayoutX(743.0);
        btnBack.setLayoutY(18.0);
        btnBack.setMnemonicParsing(false);
        btnBack.setPrefHeight(25.0);
        btnBack.setPrefWidth(137.0);
        btnBack.setText("GO BACK");

        btnBack.setOnAction(e -> {
            Stage closeStage = (Stage) btnBack.getScene().getWindow();
            closeStage.close();
        });

        anchorPane0.setLayoutX(409.0);
        anchorPane0.setLayoutY(64.0);
        anchorPane0.setPrefHeight(530.0);
        anchorPane0.setPrefWidth(486.0);

        lblTicket.setLayoutX(36.0);
        lblTicket.setLayoutY(14.0);
        lblTicket.setText("TICKET:");
        lblTicket.setFont(new Font(20.0));

        lblTotalDistance.setLayoutX(36.0);
        lblTotalDistance.setLayoutY(56.0);
        lblTotalDistance.setText("Total distance:");
        lblTotalDistance.setFont(new Font(20.0));

        txtFieldTotalDistance.setEditable(false);
        txtFieldTotalDistance.setLayoutX(186.0);
        txtFieldTotalDistance.setLayoutY(58.0);

        txtFieldTotalDistance.setText("" + result.getDistance());

        lblBikes.setLayoutX(36.0);
        lblBikes.setLayoutY(107.0);
        lblBikes.setText("Bikes?");
        lblBikes.setFont(new Font(22.0));

        txtFieldBikes.setEditable(false);
        txtFieldBikes.setLayoutX(186.0);
        txtFieldBikes.setLayoutY(111.0);

        if (result.isBikeAccess()) {
            txtFieldBikes.setText("Yes");
        } else {
            txtFieldBikes.setText("No");
        }

        lblBridges.setLayoutX(39.0);
        lblBridges.setLayoutY(163.0);
        lblBridges.setText("Bridges?");
        lblBridges.setFont(new Font(22.0));

        txtFieldBridges.setEditable(false);
        txtFieldBridges.setLayoutX(186.0);
        txtFieldBridges.setLayoutY(167.0);

        if (result.isBridgesAllowed()) {
            txtFieldBridges.setText("Yes");
        } else {
            txtFieldBridges.setText("No");
        }

        lblDate.setLayoutX(39.0);
        lblDate.setLayoutY(226.0);
        lblDate.setText("Date:");
        lblDate.setFont(new Font(22.0));

        txtFieldDate.setEditable(false);
        txtFieldDate.setLayoutX(186.0);
        txtFieldDate.setLayoutY(230.0);

        txtFieldDate.setText("Data placeholder");

        lblHour.setLayoutX(39.0);
        lblHour.setLayoutY(258.0);
        lblHour.setText("Hour:");
        lblHour.setFont(new Font(22.0));

        txtFieldHour.setEditable(false);
        txtFieldHour.setLayoutX(186.0);
        txtFieldHour.setLayoutY(262.0);

        txtFieldHour.setText("Data placeholder");

        lblWithBill.setLayoutX(148.0);
        lblWithBill.setLayoutY(366.0);
        lblWithBill.setText("Print ticket with Bill?");
        lblWithBill.setFont(new Font(22.0));

        radioYes.setLayoutX(143.0);
        radioYes.setLayoutY(413.0);
        radioYes.setMnemonicParsing(false);
        radioYes.setPrefHeight(17.0);
        radioYes.setPrefWidth(46.0);
        radioYes.setText("YES");

        radioNo.setLayoutX(295.0);
        radioNo.setLayoutY(413.0);
        radioNo.setMnemonicParsing(false);
        radioNo.setText("NO");

        ToggleGroup billGroup = new ToggleGroup();
        radioYes.setToggleGroup(billGroup);
        radioNo.setToggleGroup(billGroup);

        lblCost.setLayoutX(295.0);
        lblCost.setLayoutY(313.0);
        lblCost.setText("Cost:");
        lblCost.setFont(new Font(22.0));

        txtFieldCost.setEditable(false);
        txtFieldCost.setLayoutX(351.0);
        txtFieldCost.setLayoutY(317.0);
        txtFieldCost.setPrefHeight(25.0);
        txtFieldCost.setPrefWidth(112.0);

        txtFieldCost.setText("" + result.getCost());

        btnSubmit.setLayoutX(175.0);
        btnSubmit.setLayoutY(454.0);
        btnSubmit.setMnemonicParsing(false);
        btnSubmit.setPrefHeight(63.0);
        btnSubmit.setPrefWidth(137.0);
        btnSubmit.setText("Submit");

        btnSubmit.setOnAction(e -> {
            submit();
        });

        getChildren().add(lblCheckout);
        getChildren().add(lblPlaces);
        getChildren().add(lblPaths);
        getChildren().add(txtFieldPlaces);
        getChildren().add(txtFieldPaths);
        anchorPane.getChildren().add(lblBill);
        anchorPane.getChildren().add(lblNif);
        anchorPane.getChildren().add(lblName);
        anchorPane.getChildren().add(lblAddress);
        anchorPane.getChildren().add(txtFieldNif);
        anchorPane.getChildren().add(txtFieldName);
        anchorPane.getChildren().add(txtFieldAddress);
        getChildren().add(anchorPane);
        getChildren().add(lblBack);
        getChildren().add(btnBack);
        anchorPane0.getChildren().add(lblTicket);
        anchorPane0.getChildren().add(lblTotalDistance);
        anchorPane0.getChildren().add(txtFieldTotalDistance);
        anchorPane0.getChildren().add(lblBikes);
        anchorPane0.getChildren().add(txtFieldBikes);
        anchorPane0.getChildren().add(lblBridges);
        anchorPane0.getChildren().add(txtFieldBridges);
        anchorPane0.getChildren().add(lblDate);
        anchorPane0.getChildren().add(txtFieldDate);
        anchorPane0.getChildren().add(lblHour);
        anchorPane0.getChildren().add(txtFieldHour);
        anchorPane0.getChildren().add(lblWithBill);
        anchorPane0.getChildren().add(radioYes);
        anchorPane0.getChildren().add(radioNo);
        anchorPane0.getChildren().add(lblCost);
        anchorPane0.getChildren().add(txtFieldCost);
        anchorPane0.getChildren().add(btnSubmit);
        getChildren().add(anchorPane0);
    }

    private void submit() {
        //checking for bill errors
        boolean withBill = true;
        if (radioNo.isSelected()) {
            withBill = false;
        }
        String nif = txtFieldNif.getText();
        String name = txtFieldName.getText();
        String address = txtFieldAddress.getText();

        if (withBill && nif.isEmpty()) {
            new ErrorWindow("Bill Error", "Provide NIF", "You have chosen to take the ticket with a bill and provided no NIF");
        } else if (withBill && name.isEmpty()) {
            new ErrorWindow("Bill Error", "Provide Name", "You have chosen to take the ticket with a bill and provided no Name");
        } else if (withBill && address.isEmpty()) {
            new ErrorWindow("Bill Error", "Provide Address", "You have chosen to take the ticket with a bill and provided no Address");
        }

        //if user chose with bill
        if (withBill) {
            Bill bill = new Bill(nif, name, address, result);
            dao.savePrintable(bill);
            new PDFFiles(bill.getID()+bill.getType(), bill.print());
        }
        
        Ticket ticket = new Ticket(name, result);
        dao.savePrintable(ticket);
        new PDFFiles(ticket.getID()+ticket.getType(), ticket.print());
        
        
        
        
        
        //close window 
        Stage closeStage = (Stage) btnBack.getScene().getWindow();
        closeStage.close();
    }
}
