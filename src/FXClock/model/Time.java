package FXClock.model;

import FXClock.view.Clock;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.animation.TimelineBuilder;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.event.EventHandler;
import javafx.util.Duration;

import java.util.Calendar;

public class Time {

    //Свойства параметров часов
    public SimpleIntegerProperty hour = new SimpleIntegerProperty(0);
    public SimpleIntegerProperty minute = new SimpleIntegerProperty(0);
    public SimpleIntegerProperty second = new SimpleIntegerProperty(0);

    public Time() {

        startTime();

    }

    // Для старта
    private boolean proofStart = false;

    //Проверка для смены рисунка
    private boolean stepCase = true;

    // Шаг для сдвига
    private int step = 6;

    private void startTime() {

        TimelineBuilder.create()
                .cycleCount(Timeline.INDEFINITE)
                .keyFrames(
                        new KeyFrame(Duration.seconds(1), updateTime())
                )
                .build()
                .play();
    }

    //Обрабатываем обновление времени
    private EventHandler updateTime() {
        return event -> {

            Calendar calendar = Calendar.getInstance();

            hour.set(calendar.get(Calendar.HOUR));
            minute.set(calendar.get(Calendar.MINUTE));
            second.set(calendar.get(Calendar.SECOND));

            //Если секундная стрелка впервые достигла старта, то proofStart = true
            if (proofStart) {

                //Если картинка должна открываться
                if (stepCase){

                    Clock.backCircle.setLength(Clock.backCircle.getLength() - step);

                } else { // Закрываться

                    Clock.backCircle.setLength(Clock.backCircle.getLength() + step);
                    Clock.backCircle.setStartAngle(Clock.backCircle.getStartAngle() - step);

                }

                // В случае изменения движения шторки
                if (calendar.get(Calendar.SECOND) == 0){

                    if (stepCase) {

                        stepCase = false;

                    } else {

                        stepCase = true;
                        Clock.backCircle.setStartAngle(90);

                    }

                }

            } else if (calendar.get(Calendar.SECOND) == 0){

                proofStart = true;

            }

        };

    }

}
