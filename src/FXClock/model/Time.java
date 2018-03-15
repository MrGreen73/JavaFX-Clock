package FXClock.model;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.animation.TimelineBuilder;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.event.EventHandler;
import javafx.util.Duration;

import java.time.Clock;
import java.util.Calendar;

public class Time {

    public SimpleIntegerProperty hour = new SimpleIntegerProperty(0);
    public SimpleIntegerProperty minute = new SimpleIntegerProperty(0);
    public SimpleIntegerProperty second = new SimpleIntegerProperty(0);

    public Time() {

        startTime();

    }

    //Проверка для смены рисунка
    public boolean stepMunute = false;

    // Шаг для сдвига
    public int step = 6;

    private void startTime() {

        TimelineBuilder.create()
                .cycleCount(Timeline.INDEFINITE)
                .keyFrames(
                        new KeyFrame(Duration.seconds(1), updateTime())
                )
                .build()
                .play();
    }

    private EventHandler updateTime() {
        return event -> {

            Calendar calendar = Calendar.getInstance();

            hour.set(calendar.get(Calendar.HOUR));
            minute.set(calendar.get(Calendar.MINUTE));
            second.set(calendar.get(Calendar.SECOND));

            /*if (calendar.get(Calendar.SECOND) == 1){

                pr = true;

            }

            if (pr){

            //    Clock.arc.setLength(Clock.arc.getLength() + k);


                if (pr && calendar.get(Calendar.SECOND) == 59) {

                    k = 6;

                }
            }*/
        };
    }

}
