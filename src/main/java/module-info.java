module fr.btsciel.td_convertisseur_monetaire_v6 {
    requires javafx.controls;
    requires javafx.fxml;


    opens fr.btsciel.td_convertisseur_monetaire_v6 to javafx.fxml;
    exports fr.btsciel.td_convertisseur_monetaire_v6;
}