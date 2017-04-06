import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.paint.*;
import javafx.scene.paint.Color;
import javafx.scene.text.*;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import java.awt.*;


public class TypeBox extends Application{

    private BorderPane pane = new BorderPane();
    private FlowPane top = new FlowPane();
    private FlowPane bottom = new FlowPane();
    private RadioButton centered = new RadioButton();
    private RadioButton bold = new RadioButton();
    private RadioButton italic = new RadioButton();
    private ComboBox<Integer> size = new ComboBox<>();
    private ComboBox<String> fonts = new ComboBox();
    private Text text = new Text(100, 100, "JavaFX is the future!");
    private Font font = new Font("Times New Roman", 20);
    private boolean isBold = false, isItalic = false;

    public static void main(String[] args){

        launch(args);

    }

    public void start(Stage stage){

        Scene scene = new Scene(pane);
        pane.setPrefSize(1000, 600);

        GraphicsEnvironment e = GraphicsEnvironment.getLocalGraphicsEnvironment();
        String[] fontnames = e.getAvailableFontFamilyNames();

        for (int i = 0; i < fontnames.length; i++)
            fonts.getItems().addAll(fontnames[i]);



        for (int i = 1; i < 101; i++)
            size.getItems().addAll(i);

        fonts.setValue("Courier New");
        size.setValue(25);
        top.setAlignment(Pos.CENTER);
        top.setHgap(5);
        top.getChildren().addAll(new Label("Fonts"), fonts, new Label("Font Size"), size, new Label("Color"));
        pane.setTop(top);

        bottom.setAlignment(Pos.CENTER);
        bottom.setHgap(5);
        bottom.getChildren().addAll(new Label("Centered"), centered, new Label("Bold"), bold, new Label("Italic"), italic);
        pane.setBottom(bottom);

        text.setFont(Font.font(fonts.getValue(), size.getValue()));
        text.setFill(Color.rgb((int)(Math.random() * 256), (int)(Math.random() * 256), (int)(Math.random() * 256)));
        pane.getChildren().add(text);

        stage.setScene(scene);
        stage.show();

        fonts.setOnAction(event -> {
            handleEvent();
        });

        size.setOnAction(event -> {
            handleEvent();
        });

        centered.setOnAction(event -> {
            if(centered.isSelected()) {
                text.setY(pane.getHeight() / 2);
                text.setX(pane.getWidth() / 2 - 85);
            }

            else {
                text.setY(100);
                text.setX(100);
            }
        });

        bold.setOnAction(event -> {
            if(bold.isSelected()) {
                isBold = true;
                handleEvent();
            }

            else {
                isBold = false;
                handleEvent();
            }
        });


        italic.setOnAction(event -> {
            if(italic.isSelected()) {
                isItalic = true;
                handleEvent();
            }
            else {
                isItalic = false;
                handleEvent();
            }
        });
    }

    public void handleEvent(){
        if(isBold && isItalic)
            text.setFont(Font.font(fonts.getValue(), FontWeight.BOLD, FontPosture.ITALIC, size.getValue()));

        else if(isBold && !isItalic)
            text.setFont(Font.font(fonts.getValue(), FontWeight.BOLD, size.getValue()));

        else if(!isBold && isItalic)
            text.setFont(Font.font(fonts.getValue(), FontPosture.ITALIC, size.getValue()));

        else if(!isBold && !isItalic)
            text.setFont(Font.font(fonts.getValue(), size.getValue()));

    }
}
  
