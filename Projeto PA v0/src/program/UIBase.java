package program;

import javafx.geometry.Insets;
import javafx.scene.Cursor;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;

public class UIBase extends BorderPane {

    protected final AnchorPane anchorPane;
    protected final Label label;
    protected final Label label0;
    protected final Label label1;
    protected final Label label2;
    protected final Label label3;
    protected final TextField textField;
    protected final TextField textField0;
    protected final TextField textField1;
    protected final TextField textField2;
    protected final TextField textField3;
    protected final ImageView imageView;
    protected final AnchorPane anchorPane0;
    protected final ListView listView;
    protected final Button button;
    protected final Button button0;
    protected final Label label4;
    protected final ListView listView0;
    protected final Button button1;
    protected final Label label5;
    protected final TextField textField4;
    protected final TextField textField5;
    protected final Label label6;
    protected final RadioButton radioButton;
    protected final RadioButton radioButton0;
    protected final RadioButton radioButton1;
    protected final RadioButton radioButton2;
    protected final Label label7;
    protected final Label label8;
    protected final Button button2;

    public UIBase() {

        anchorPane = new AnchorPane();
        label = new Label();
        label0 = new Label();
        label1 = new Label();
        label2 = new Label();
        label3 = new Label();
        textField = new TextField();
        textField0 = new TextField();
        textField1 = new TextField();
        textField2 = new TextField();
        textField3 = new TextField();
        imageView = new ImageView();
        anchorPane0 = new AnchorPane();
        listView = new ListView();
        button = new Button();
        button0 = new Button();
        label4 = new Label();
        listView0 = new ListView();
        button1 = new Button();
        label5 = new Label();
        textField4 = new TextField();
        textField5 = new TextField();
        label6 = new Label();
        radioButton = new RadioButton();
        radioButton0 = new RadioButton();
        radioButton1 = new RadioButton();
        radioButton2 = new RadioButton();
        label7 = new Label();
        label8 = new Label();
        button2 = new Button();

        BorderPane.setAlignment(anchorPane, javafx.geometry.Pos.CENTER);
        anchorPane.setPrefHeight(250.0);
        anchorPane.setPrefWidth(481.0);

        label.setLayoutX(7.0);
        label.setLayoutY(39.0);
        label.setPrefHeight(17.0);
        label.setPrefWidth(68.0);
        label.setText("Name:");

        label0.setLayoutX(7.0);
        label0.setLayoutY(82.0);
        label0.setPrefHeight(17.0);
        label0.setPrefWidth(81.0);
        label0.setText("Type of Path:");

        label1.setLayoutX(7.0);
        label1.setLayoutY(127.0);
        label1.setPrefHeight(17.0);
        label1.setPrefWidth(68.0);
        label1.setText("Duration");

        label2.setLayoutX(7.0);
        label2.setLayoutY(168.0);
        label2.setPrefHeight(17.0);
        label2.setPrefWidth(59.0);
        label2.setText("Cost");

        label3.setLayoutX(7.0);
        label3.setLayoutY(207.0);
        label3.setPrefHeight(17.0);
        label3.setPrefWidth(59.0);
        label3.setText("Bikes?");

        textField.setEditable(false);
        textField.setLayoutX(88.0);
        textField.setLayoutY(78.0);
        textField.setPrefHeight(25.0);
        textField.setPrefWidth(149.0);
        textField.setOpaqueInsets(new Insets(0.0));
        textField.setCursor(Cursor.TEXT);

        textField0.setEditable(false);
        textField0.setLayoutX(88.0);
        textField0.setLayoutY(123.0);
        textField0.setPrefHeight(25.0);
        textField0.setPrefWidth(149.0);
        textField0.setOpaqueInsets(new Insets(0.0));
        textField0.setCursor(Cursor.TEXT);

        textField1.setEditable(false);
        textField1.setLayoutX(87.0);
        textField1.setLayoutY(164.0);
        textField1.setOpaqueInsets(new Insets(0.0));
        textField1.setCursor(Cursor.TEXT);

        textField2.setEditable(false);
        textField2.setLayoutX(87.0);
        textField2.setLayoutY(203.0);
        textField2.setOpaqueInsets(new Insets(0.0));
        textField2.setCursor(Cursor.TEXT);

        textField3.setEditable(false);
        textField3.setLayoutX(87.0);
        textField3.setLayoutY(35.0);

        imageView.setFitHeight(150.0);
        imageView.setFitWidth(200.0);
        imageView.setLayoutX(267.0);
        imageView.setLayoutY(52.0);
        imageView.setPickOnBounds(true);
        imageView.setPreserveRatio(true);
        setBottom(anchorPane);

        BorderPane.setAlignment(anchorPane0, javafx.geometry.Pos.CENTER);
        anchorPane0.setPrefHeight(570.0);
        anchorPane0.setPrefWidth(374.0);

        listView.setLayoutX(149.0);
        listView.setLayoutY(14.0);
        listView.setPrefHeight(307.0);
        listView.setPrefWidth(211.0);

        button.setLayoutX(14.0);
        button.setLayoutY(35.0);
        button.setMnemonicParsing(false);
        button.setPrefHeight(32.0);
        button.setPrefWidth(118.0);
        button.setText("Add Path to visit");

        button0.setLayoutX(14.0);
        button0.setLayoutY(83.0);
        button0.setMnemonicParsing(false);
        button0.setText("Undo");

        label4.setLayoutX(15.0);
        label4.setLayoutY(334.0);
        label4.setText("Chosen Places to visit:");

        listView0.setLayoutX(160.0);
        listView0.setLayoutY(352.0);
        listView0.setPrefHeight(150.0);
        listView0.setPrefWidth(200.0);

        button1.setLayoutX(18.0);
        button1.setLayoutY(358.0);
        button1.setMnemonicParsing(false);
        button1.setText("Remove Place");

        label5.setLayoutX(14.0);
        label5.setLayoutY(462.0);
        label5.setText("Final Distance:");

        textField4.setLayoutX(15.0);
        textField4.setLayoutY(480.0);
        textField4.setPrefHeight(26.0);
        textField4.setPrefWidth(134.0);

        textField5.setLayoutX(15.0);
        textField5.setLayoutY(429.0);

        label6.setLayoutX(14.0);
        label6.setLayoutY(411.0);
        label6.setText("Final Cost");

        radioButton.setLayoutX(14.0);
        radioButton.setLayoutY(159.0);
        radioButton.setMnemonicParsing(false);
        radioButton.setPrefHeight(18.0);
        radioButton.setPrefWidth(118.0);
        radioButton.setText("Yes, I am");

        radioButton0.setLayoutX(15.0);
        radioButton0.setLayoutY(250.0);
        radioButton0.setMnemonicParsing(false);
        radioButton0.setPrefHeight(18.0);
        radioButton0.setPrefWidth(118.0);
        radioButton0.setText("Yes");

        radioButton1.setLayoutX(14.0);
        radioButton1.setLayoutY(184.0);
        radioButton1.setMnemonicParsing(false);
        radioButton1.setPrefHeight(18.0);
        radioButton1.setPrefWidth(118.0);
        radioButton1.setText("No, I'm not");

        radioButton2.setLayoutX(15.0);
        radioButton2.setLayoutY(275.0);
        radioButton2.setMnemonicParsing(false);
        radioButton2.setPrefHeight(18.0);
        radioButton2.setPrefWidth(118.0);
        radioButton2.setText("No, please");

        label7.setLayoutX(15.0);
        label7.setLayoutY(127.0);
        label7.setText("Using a Bike?");

        label8.setLayoutX(15.0);
        label8.setLayoutY(218.0);
        label8.setText("Are bridges okay?");

        button2.setLayoutX(15.0);
        button2.setLayoutY(515.0);
        button2.setMnemonicParsing(false);
        button2.setPrefHeight(43.0);
        button2.setPrefWidth(346.0);
        button2.setText("Proceed to checkout");
        setRight(anchorPane0);

        anchorPane.getChildren().add(label);
        anchorPane.getChildren().add(label0);
        anchorPane.getChildren().add(label1);
        anchorPane.getChildren().add(label2);
        anchorPane.getChildren().add(label3);
        anchorPane.getChildren().add(textField);
        anchorPane.getChildren().add(textField0);
        anchorPane.getChildren().add(textField1);
        anchorPane.getChildren().add(textField2);
        anchorPane.getChildren().add(textField3);
        anchorPane.getChildren().add(imageView);
        anchorPane0.getChildren().add(listView);
        anchorPane0.getChildren().add(button);
        anchorPane0.getChildren().add(button0);
        anchorPane0.getChildren().add(label4);
        anchorPane0.getChildren().add(listView0);
        anchorPane0.getChildren().add(button1);
        anchorPane0.getChildren().add(label5);
        anchorPane0.getChildren().add(textField4);
        anchorPane0.getChildren().add(textField5);
        anchorPane0.getChildren().add(label6);
        anchorPane0.getChildren().add(radioButton);
        anchorPane0.getChildren().add(radioButton0);
        anchorPane0.getChildren().add(radioButton1);
        anchorPane0.getChildren().add(radioButton2);
        anchorPane0.getChildren().add(label7);
        anchorPane0.getChildren().add(label8);
        anchorPane0.getChildren().add(button2);

    }
}
