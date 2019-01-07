package program;

import graph.Vertex;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import model.Gestor.Place;

public class UIBase extends BorderPane {

    protected final AnchorPane anchorPane;
    protected final ListView allPlacesView;
    protected final Button btnAddPlaces;
    protected final Button btnUndoAddPlaces;
    protected final Label lblChosenPlaces;
    protected final ListView addedPlacesView;
    protected final Button btnRemovePlace;
    protected final RadioButton radioBikeYes;
    protected final RadioButton radioBridgeYes;
    protected final RadioButton radioBikeNo;
    protected final RadioButton radioBridgeNo;
    protected final Label lblBike;
    protected final Label lblBridge;
    protected final Button btnCheckout;
    protected final Label lblCriteria;
    protected final RadioButton radioCost;
    protected final RadioButton radioDistance;

    public UIBase(Iterable<Vertex<Place>> places) {

        anchorPane = new AnchorPane();
        allPlacesView = new ListView();
        btnAddPlaces = new Button();
        btnUndoAddPlaces = new Button();
        lblChosenPlaces = new Label();
        addedPlacesView = new ListView();
        btnRemovePlace = new Button();
        radioBikeYes = new RadioButton();
        radioBridgeYes = new RadioButton();
        radioBikeNo = new RadioButton();
        radioBridgeNo = new RadioButton();
        lblBike = new Label();
        lblBridge = new Label();
        btnCheckout = new Button();
        lblCriteria = new Label();
        radioCost = new RadioButton();
        radioDistance = new RadioButton();

        BorderPane.setAlignment(anchorPane, javafx.geometry.Pos.CENTER);
        anchorPane.setPrefHeight(570.0);
        anchorPane.setPrefWidth(374.0);

        allPlacesView.setId("allPlaces");
        allPlacesView.setLayoutX(149.0);
        allPlacesView.setLayoutY(14.0);
        allPlacesView.setPrefHeight(307.0);
        allPlacesView.setPrefWidth(211.0);

        ObservableList<Place> observablePlacesList = FXCollections.observableArrayList();
        for (Vertex<Place> p : places) {
            if (p.element().getId() == 1) {
                continue;
            }
            observablePlacesList.add(p.element());
        }
        allPlacesView.setItems(observablePlacesList);

        lblChosenPlaces.setLayoutX(15.0);
        lblChosenPlaces.setLayoutY(334.0);
        lblChosenPlaces.setText("Chosen Places to visit:");

        addedPlacesView.setId("addedPlaces");
        addedPlacesView.setLayoutX(160.0);
        addedPlacesView.setLayoutY(352.0);
        addedPlacesView.setPrefHeight(150.0);
        addedPlacesView.setPrefWidth(200.0);

        ObservableList<Place> observableAddedPlaces = FXCollections.observableArrayList();
        addedPlacesView.setItems(observableAddedPlaces);

        btnAddPlaces.setId("addPlaces");
        btnAddPlaces.setLayoutX(14.0);
        btnAddPlaces.setLayoutY(35.0);
        btnAddPlaces.setMnemonicParsing(false);
        btnAddPlaces.setPrefHeight(32.0);
        btnAddPlaces.setPrefWidth(118.0);
        btnAddPlaces.setText("Add Place to visit");

        btnAddPlaces.setOnAction(e -> {
            Place selected = (Place) allPlacesView.getSelectionModel().getSelectedItem();
            if (selected != null && !observableAddedPlaces.contains(selected)) {
                observableAddedPlaces.add(selected);
            }
        });

        btnUndoAddPlaces.setId("undoAddPath");
        btnUndoAddPlaces.setLayoutX(14.0);
        btnUndoAddPlaces.setLayoutY(83.0);
        btnUndoAddPlaces.setMnemonicParsing(false);
        btnUndoAddPlaces.setText("Undo");

        btnUndoAddPlaces.setOnAction(e -> {
            if (observableAddedPlaces.size() > 0) {
                observableAddedPlaces.remove(observableAddedPlaces.size() - 1);
            }
        });

        btnRemovePlace.setId("removePlace");
        btnRemovePlace.setLayoutX(18.0);
        btnRemovePlace.setLayoutY(358.0);
        btnRemovePlace.setMnemonicParsing(false);
        btnRemovePlace.setText("Remove Place");

        btnRemovePlace.setOnAction(e -> {
            Place selected = (Place) addedPlacesView.getSelectionModel().getSelectedItem();
            if (selected != null) {
                observableAddedPlaces.remove(selected);
            }
        });

        ToggleGroup bikeGroup = new ToggleGroup();

        radioBikeYes.setId("bikeYes");
        radioBikeYes.setLayoutX(14.0);
        radioBikeYes.setLayoutY(159.0);
        radioBikeYes.setMnemonicParsing(false);
        radioBikeYes.setPrefHeight(18.0);
        radioBikeYes.setPrefWidth(118.0);
        radioBikeYes.setText("Yes, I am");

        radioBikeYes.setToggleGroup(bikeGroup);

        radioBikeNo.setId("bikeNo");
        radioBikeNo.setLayoutX(14.0);
        radioBikeNo.setLayoutY(184.0);
        radioBikeNo.setMnemonicParsing(false);
        radioBikeNo.setPrefHeight(18.0);
        radioBikeNo.setPrefWidth(118.0);
        radioBikeNo.setSelected(true);
        radioBikeNo.setText("No, I'm not");

        radioBikeNo.setToggleGroup(bikeGroup);

        ToggleGroup bridgeGroup = new ToggleGroup();

        radioBridgeYes.setId("bridgeYes");
        radioBridgeYes.setLayoutX(15.0);
        radioBridgeYes.setLayoutY(250.0);
        radioBridgeYes.setMnemonicParsing(false);
        radioBridgeYes.setPrefHeight(18.0);
        radioBridgeYes.setPrefWidth(118.0);
        radioBridgeYes.setSelected(true);
        radioBridgeYes.setText("Yes");

        radioBridgeYes.setToggleGroup(bridgeGroup);

        radioBridgeNo.setId("bridgeNo");
        radioBridgeNo.setLayoutX(15.0);
        radioBridgeNo.setLayoutY(275.0);
        radioBridgeNo.setMnemonicParsing(false);
        radioBridgeNo.setPrefHeight(18.0);
        radioBridgeNo.setPrefWidth(118.0);
        radioBridgeNo.setText("No, please");

        radioBridgeNo.setToggleGroup(bridgeGroup);

        lblBike.setLayoutX(15.0);
        lblBike.setLayoutY(127.0);
        lblBike.setText("Using a Bike?");

        lblBridge.setLayoutX(15.0);
        lblBridge.setLayoutY(218.0);
        lblBridge.setText("Are bridges okay?");

        btnCheckout.setId("checkout");
        btnCheckout.setLayoutX(15.0);
        btnCheckout.setLayoutY(515.0);
        btnCheckout.setMnemonicParsing(false);
        btnCheckout.setPrefHeight(43.0);
        btnCheckout.setPrefWidth(346.0);
        btnCheckout.setText("Proceed to checkout");
        
        lblCriteria.setLayoutX(14.0);
        lblCriteria.setLayoutY(395.0);
        lblCriteria.setText("Criteria");

        radioCost.setLayoutX(10.0);
        radioCost.setLayoutY(453.0);
        radioCost.setMnemonicParsing(false);
        radioCost.setText("Cost");

        radioDistance.setLayoutX(10.0);
        radioDistance.setLayoutY(427.0);
        radioDistance.setMnemonicParsing(false);
        radioDistance.setSelected(true);
        radioDistance.setText("Distance");
        
        ToggleGroup criteriaGroup = new ToggleGroup();
        radioCost.setToggleGroup(criteriaGroup);
        radioDistance.setToggleGroup(criteriaGroup);
                
        setRight(anchorPane);

        anchorPane.getChildren().add(allPlacesView);
        anchorPane.getChildren().add(btnAddPlaces);
        anchorPane.getChildren().add(btnUndoAddPlaces);
        anchorPane.getChildren().add(lblChosenPlaces);
        anchorPane.getChildren().add(addedPlacesView);
        anchorPane.getChildren().add(btnRemovePlace);
        anchorPane.getChildren().add(lblCriteria);
        anchorPane.getChildren().add(radioCost);
        anchorPane.getChildren().add(radioDistance);
        anchorPane.getChildren().add(radioBikeYes);
        anchorPane.getChildren().add(radioBridgeYes);
        anchorPane.getChildren().add(radioBikeNo);
        anchorPane.getChildren().add(radioBridgeNo);
        anchorPane.getChildren().add(lblBike);
        anchorPane.getChildren().add(lblBridge);
        anchorPane.getChildren().add(btnCheckout);

    }
}
