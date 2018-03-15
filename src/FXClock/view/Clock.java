package FXClock.view;

import FXClock.model.Time;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.*;


import javafx.scene.transform.Rotate;
import javafx.scene.transform.RotateBuilder;
import javafx.stage.Stage;

import static javafx.scene.shape.ArcBuilder.*;

public class Clock extends Application {

    //Создаем объект времени
    private static final Time time = new Time();

    private Scene scene;
    private Group group;

    //Три уровня покрытия часов(задняя - выполняет функцию шторки, то есть закрывает и открывает)
    public static Arc backCircle = new Arc(); // Задняя с цветом
    public static Arc frontCircle = new Arc(); // Внешняя с картинкой
    public static Arc strokeCircle = new Arc(); // Обводка

    //Размеры окна
    private int widthWindow = 600;
    private int heightWindow = 600;

    //Расположение часов
    private int clockCenter = widthWindow / 2;
    private int radiusClock = widthWindow / 3;

    //Параметры для stroke
    private int startStrokeCircle = 90;
    private int lengthStrokeCircle = 360;
    private Color strokeColor = Color.DARKSEAGREEN;

    //Параметры заднего фона часов
    private int startBackCircle = 90;
    private int lengthBackCircle = 360;
    private Color backColor = Color.GREEN;


    //Параметры переднего фона часов
    private int startFrontCircle = 90;
    private int lengthFrontCircle = 360;
    private String frontImage = "pikachu.png"; // Картинка с пикачу


    //Толщина отметок на часах
    private int lineStroke = 3;

    //Цвета стрелок
    private Color colorMinute = Color.BLUE;
    private Color colorHours = Color.RED;

    public static void main(String[] args) {

        launch(args);

    }

    @Override
    public void start(Stage stage) throws Exception{

        stage.setTitle("Clock");
        group = new Group();
        scene = new Scene(group, widthWindow, heightWindow, Color.WHEAT);

        // Создаем обводку
        strokeCircle = create()
                .type(ArcType.ROUND)
                .centerX(clockCenter)
                .centerY(clockCenter)
                .radiusX(radiusClock)
                .radiusY(radiusClock)
                .startAngle(startStrokeCircle)
                .length(lengthStrokeCircle)
                .strokeWidth(24)
                .stroke(strokeColor)
                .build();

        //Создаем задний фон
        backCircle = create()
                .type(ArcType.ROUND)
                .centerX(clockCenter)
                .centerY(clockCenter)
                .radiusX(radiusClock)
                .radiusY(radiusClock)
                .startAngle(startBackCircle)
                .length(lengthBackCircle)
                .fill(backColor)
                .build();

        //Создаем фон картинкой
        frontCircle = create()
                .type(ArcType.ROUND)
                .centerX(clockCenter)
                .centerY(clockCenter)
                .radiusX(radiusClock)
                .radiusY(radiusClock)
                .startAngle(startFrontCircle)
                .length(lengthFrontCircle)
                .fill(new ImagePattern(new Image(frontImage)))
                .build();

        //Заносим все элементы в группу
        group.getChildren().addAll(strokeCircle, frontCircle, backCircle, Lines(), seconds(), minutes(), hours());


        //Устанавливаем Scene
        stage.setScene(scene);
        stage.show();

    }



    //Делаем группу из 12 отметок
    private Node Lines() {

        Group lineGroup = new Group();

        for (int n = 0; n < 12; n++) {

            lineGroup.getChildren().add(Lines(n));

        }

        return lineGroup;

    }

    private Node Lines(int n) {

        return LineBuilder.create()
                .startX(clockCenter)
                .endX(clockCenter)
                .startY(clockCenter * 0.35)
                .endY(clockCenter * (n % 3 == 0 ? 0.50 : 0.40)) // Каждую четвертую делаем больше
                .transforms(
                        RotateBuilder.create()
                                .pivotX(clockCenter)
                                .pivotY(clockCenter)
                                .angle(360 / 12 * n)
                                .build()
                )
                .strokeWidth(lineStroke)
                .build();

    }

    //Рисуем часовую стрелку
    private Node hours() {

        Rotate rotate = rotation();
        rotate.angleProperty().bind(time.hour.multiply(360 / 12));

        return paintHours(clockCenter * 0.6, colorHours, rotate);

    }

    //Рисуем минутную стрелку
    private Node minutes() {

        Rotate rotate = rotation();
        rotate.angleProperty().bind(time.minute.multiply(360 / 60));

        return paintMinutes(clockCenter * 0.5, colorMinute, rotate);

    }

    //Рисуем секундную стрелку
    private Node seconds() {

        Rotate rotate = rotation();
        rotate.angleProperty().bind(time.second.multiply(360 / 60));

        return LineBuilder.create()
                .startX(clockCenter)
                .endX(clockCenter)
                .startY(clockCenter * 1.1)
                .endY(clockCenter * 0.35)
                .transforms(rotate)
                .build();

    }


    private Rotate rotation() {

        return RotateBuilder.create()
                .pivotX(clockCenter)
                .pivotY(clockCenter)
                .build();

    }

    private Node paintMinutes(double stretchRelativeToRim, Color color, Rotate rotate) {

        return PathBuilder.create()
                .fill(color)
                .stroke(Color.TRANSPARENT)
                .elements(
                        new MoveTo(clockCenter, clockCenter),
                        new LineTo(clockCenter * 0.97, clockCenter * 0.90),
                        new LineTo(clockCenter, stretchRelativeToRim),
                        new LineTo(clockCenter * 1.03, clockCenter * 0.90),
                        new LineTo(clockCenter, clockCenter)
                )
                .transforms(rotate)
                .build();

    }

    private Node paintHours(double stretchRelativeToRim, Color color, Rotate rotate) {

        return PathBuilder.create()
                .fill(color)
                .stroke(Color.TRANSPARENT)
                .elements(
                        new MoveTo(clockCenter, clockCenter),
                        new LineTo(clockCenter * 0.95, clockCenter * 0.95),
                        new LineTo(clockCenter, stretchRelativeToRim),
                        new LineTo(clockCenter * 1.05, clockCenter * 0.95),
                        new LineTo(clockCenter, clockCenter)
                )
                .transforms(rotate)
                .build();

    }

}

