package fr.btsciel.td_convertisseur_monetaire_v6;

import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import javafx.scene.control.*;


import java.net.URL;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.ResourceBundle;


public class HelloController implements Initializable {
    @FXML
    private TextField textField_init;
    @FXML
    private TextField textField_Final;
    @FXML
    private Label label_Init;
    @FXML
    private Label label_Final;
    @FXML
    private Button buttonConvertion;
    private double valeur_Conversion;
    private double valeur_Init;
    private double valeur_Final;
    private final double taux_Euro_Dollar = 1.1796;
    private final double taux_Euro_Yen = 185.01;
    private final double taux_Euro_Livre = 0.86;
    private final DecimalFormat df = new DecimalFormat("#.####");
    private static ArrayList<ConversionDevise> conversionDevises = new ArrayList<>();
    @FXML
    private ComboBox comboSelection;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        textField_Final.setDisable(true);
        textField_init.setDisable(true);
        buttonConvertion.setDisable(true);
        fabriqueDonnees();
        conversionDevises.forEach(info -> comboSelection.getItems().add(info.getPrompt()));
        comboSelection.setOnAction(e -> {
            comboSelection(e);
        });
    }

    private void fabriqueDonnees() {
        conversionDevises.add(new ConversionDevise("Euro - Dollar Us",taux_Euro_Dollar,"Euro","Dollar"));
        conversionDevises.add(new ConversionDevise("Dollar Us - Euro",taux_Euro_Dollar,"Dollar","Euro"));
        conversionDevises.add(new ConversionDevise("Euro - Livre",taux_Euro_Livre,"Euro","Livre"));
        conversionDevises.add(new ConversionDevise("Livre - Euro",taux_Euro_Livre,"Livre","Euro"));
        conversionDevises.add(new ConversionDevise("Euro - Yen",taux_Euro_Yen,"Euro","Yen"));
        conversionDevises.add(new ConversionDevise("Yen - Euro",taux_Euro_Yen,"Yen","Euro"));
    }


    public void alerteFormat(){
        Alert dialog = new Alert(Alert.AlertType.WARNING);
        dialog.setTitle("Attention");
        dialog.setHeaderText(null);
        dialog.setContentText("La valeur initial n'est pas au bon format !");
        dialog.showAndWait();
    }

    public void convertion(){
        ConversionDevise conversionDevise = conversionDevises.get(comboSelection.getSelectionModel().getSelectedIndex());
        buttonConvertion.setOnAction(e -> {
            valeur_Init = Double.parseDouble(textField_init.getText());
            if(conversionDevise.getSource().equals("Euro")){
                valeur_Conversion = valeur_Init * conversionDevise.getTaux();
            }else{
                valeur_Conversion = valeur_Init / conversionDevise.getTaux();
            }
            valeur_Final = valeur_Conversion;
            textField_Final.setText(String.valueOf(df.format(valeur_Final)).replaceAll(",", "."));
        });

    }

    private void initConvertion(ConversionDevise conversionDevise){
        label_Init.setText(conversionDevise.getSource());
        label_Final.setText(conversionDevise.getCible());
    }

    private void comboSelection(Event e){
        try {
            textField_init.setDisable(false);
            buttonConvertion.setDisable(false);
            initConvertion(conversionDevises.get(comboSelection.getSelectionModel().getSelectedIndex()));
            convertion();
        }catch(NumberFormatException ex){
            alerteFormat();
        };
    }

}