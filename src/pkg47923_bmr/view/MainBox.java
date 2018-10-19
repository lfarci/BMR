package pkg47923_bmr.view;

import java.util.Observable;
import java.util.Observer;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import pkg47923_bmr.model.Model;

/**
 *
 * @author g47923
 */
public class MainBox extends VBox implements Observer {

    private final Content content;
    private final Button submit;

    private Model model;

    public MainBox(Model model) {
        this.model = model;
        this.content = new Content();
        this.submit = new Button("Submit");
        setPaneProperties();
        setComponentsProperties();
        addComponents();
        model.addObserver(this);
    }

    /**
     * Sets this pane properties.
     */
    final void setPaneProperties() {
        this.setPadding(new Insets(10, 10, 10, 10));
        this.setAlignment(Pos.CENTER);
    }

    /**
     * Sets the components properties of this pane.
     */
    final void setComponentsProperties() {
        submit.setOnAction((ActionEvent event) -> {
            try {
                model.notifyView();
            } catch (IllegalStateException | IllegalArgumentException ex) {
                Alert fail = new Alert(Alert.AlertType.INFORMATION);
                fail.setHeaderText("Failure");
                fail.setContentText(ex.getMessage());
                fail.showAndWait();
            }
        });
    }

    /**
     * Adds the components to this pane.
     */
    final void addComponents() {
        this.getChildren().addAll(content, submit);
    }

    @Override
    public void update(Observable o, Object arg) {
        model.setBmr(content.getGender().equals("Femme"),
                content.getWeight(), content.getSize(), content.getAge());
        model.setCalories(content.getLifeStyle());
        content.setBMR(model.getBmr());
        content.setCalories(model.getCalories());
    }

}