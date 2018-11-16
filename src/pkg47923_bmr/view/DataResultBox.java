package pkg47923_bmr.view;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import pkg47923_bmr.model.Person;

/**
 * Represents the box the managing the
 *
 * @author g47923
 */
public class DataResultBox extends VBox {

    private final int WINDOW_HEIGHT = 200;
    private final int WINDOW_WIDTH = 500;

    private final Content content;
    private final Button submit;
    private final Button clear;

    private Person user;

    public DataResultBox(Person user) {
        this.user = user;
        this.content = new Content();
        this.submit = new Button("Calculer mon BMR");
        this.clear = new Button("Clear");
        setPaneProperties();
        setComponentsProperties();
        addComponents();
    }

    /**
     * Sets this pane properties.
     */
    final void setPaneProperties() {
        setMinWidth(WINDOW_WIDTH);
        setMinHeight(WINDOW_HEIGHT);
        this.setPadding(new Insets(10, 10, 10, 10));
        this.setAlignment(Pos.CENTER);
    }

    /**
     * Sets the components properties of this pane.
     */
    final void setComponentsProperties() {
        submit.setOnAction((ActionEvent event) -> {
            try {
                user.setBmr(content.getGender().equals("Femme"),
                        content.getWeight(), content.getSize(), content.getAge());
                user.setCalories(content.getLifeStyle());
            } catch (IllegalStateException | IllegalArgumentException ex) {
                Alert fail = new Alert(Alert.AlertType.WARNING);
                fail.setHeaderText("Valeur erronée!");
                fail.setContentText(ex.getMessage());
                fail.showAndWait();
            }
        });
        clear.addEventHandler(ActionEvent.ACTION, new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                content.clear();
            }
        });
    }

    /**
     * Adds the components to this pane.
     */
    final void addComponents() {
        this.getChildren().addAll(content, submit, clear);
    }

    /**
     * Updates the content of this pane.
     */
    void update() {
        content.setBMR(user.getBmr());
        content.setCalories(user.getCalories());
    }

}
