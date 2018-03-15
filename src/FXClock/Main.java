package FXClock;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Arc;
import javafx.scene.shape.ArcBuilder;
import javafx.scene.shape.ArcType;
import javafx.stage.Stage;

import static javafx.scene.shape.ArcBuilder.*;

public class Main extends Application {


    public static Arc backCircle = new Arc();

    private Color clockColor = Color.GREEN;
    private String clockImage = "pikachu.jpg";


    private Scene scene;
    private Group group;
    private int widthWindow = 600;
    private int heightWindow = 600;
    private int xClock = widthWindow / 2;
    private int yClock = heightWindow / 2;
    private int radiusClock = widthWindow / 3;
    private int startBackCircle = 90;
    private int lengthBackCircle = 360;



    @Override
    public void start(Stage stage) throws Exception{

        stage.setTitle("Clock");
        group = new Group();
        scene = new Scene(group, widthWindow, heightWindow, Color.WHEAT);

        backCircle = create()
                .type(ArcType.ROUND)
                .centerX(xClock)
                .centerY(xClock)
                .radiusX(radiusClock)
                .radiusY(radiusClock)
                .startAngle(startBackCircle)
                .length(lengthBackCircle)
                .fill(clockColor)
                .build();

        group.getChildren().add(backCircle);


        stage.setScene(scene);
        stage.show();


        /**/


        /*
        arc.setCenterX(xClock);
        arc.setCenterY(yClock);
        arc.setRadiusX(radiusClock);
        arc.setRadiusY(radiusClock);
        arc.setStartAngle(90);
        arc.setLength(0);
        arc.setType(ArcType.ROUND);
        arc.setFill(new ImagePattern(new Image(clockImage)));


        */

       /* group = new Group();
        scene = new Scene(group, widthWindow, heightWindow);
        stage.setScene(scene);
        stage.show();*/

        /*
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("Hello World");
        primaryStage.setScene(new Scene(root, 300, 275));
        primaryStage.show();*/

/*

        Group root = new Group();
        Scene scene = new Scene(root, 551, 400, Color.BLACK);
        Group buttonGroup = new Group();

        Arc leftButton = create()
                .type(ArcType.ROUND)
                .centerX(xClock)
                .centerY(xClock)
                .radiusX(radiusClock)
                .radiusY(radiusClock)
                .startAngle(startBackCircle)
                .length(lengthBackCircle)
                .fill(clockColor)
                .build();

        leftButton.addEventHandler(MouseEvent.MOUSE_PRESSED, new EventHandler<MouseEvent>() {
            public void handle(MouseEvent me) {
                System.out.println("left");
            }
        });
        buttonGroup.getChildren().add(leftButton);
        root.getChildren().add(buttonGroup);

        stage.setScene(scene);
        stage.show();*/
    }



    public static void main(String[] args) {
        launch(args);
    }
}

