package gui;/**
 * Created by djemaa on 01/12/14.
 */

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class WindowLogIn extends Application {
    private GUI gui;
    private GridPane grid;
    private Text scenetitle;
    private final Label userName;
    private final TextField editUserName;
    private Button connectButton;
    private HBox connectButtonHBox;
    private Scene scene;
    public WindowLogIn(GUI guy){

        gui = guy;
        grid = new GridPane();
        scenetitle = new Text("Login plz");
        userName = new Label("UserName");
        editUserName = new TextField();
        connectButton = new Button("Connect");
        connectButtonHBox = new HBox(10);
        scene = new Scene(grid);


    }
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(final Stage primaryStage) {
        primaryStage.setTitle("Welcome");
        scenetitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));

        connectButtonHBox.getChildren().add(connectButton);
        connectButton.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent e) {
                if (!editUserName.getText().equals("")) {
                    gui.doConnect(editUserName.getText(), primaryStage);
                }
            }
        });

        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));
        grid.add(scenetitle, 0, 0, 2, 1);
        grid.add(userName, 0, 1);
        grid.add(editUserName, 1, 1);
        grid.add(connectButtonHBox, 2, 1);

        primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent event) {
                gui.shutdown();
            }
        });
        scene.getStylesheets().add("gui/superStyleSheet.css");
        primaryStage.setScene(scene);
        primaryStage.show();

    }

}
