package Piece;

import ir.sharif.math.bp02_1.hex_chess.graphics.Application;

import java.awt.*;
import java.util.Timer;
import java.util.TimerTask;

public class MyClock {

    private static final int DELAY = 1000;
    private static final int INITIAL_DELAY = 0;

    public static int whiteMinutes = 0;
    public static int blackMinutes = 0;
    public static int whiteSeconds = 0;
    public static void setWhiteMinutes(int whiteMinutes) {
        MyClock.whiteMinutes = whiteMinutes;
    }

    public static void setBlackMinutes(int blackMinutes) {
        MyClock.blackMinutes = blackMinutes;
    }

    public static void setWhiteSeconds(int whiteSeconds) {
        MyClock.whiteSeconds = whiteSeconds;
    }


    public static void setBlackSeconds(int blackSeconds) {
        MyClock.blackSeconds = blackSeconds;
    }

    public static int blackSeconds = 0;
    private static Timer timer = new Timer();

    public static void startTimer() {
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                if (Application.turn.equals(Color.WHITE)) {
                    if (whiteMinutes == 10 && whiteSeconds == 0) {
                        System.out.println("black won! (time limit)");
                        timer.cancel();
                    } else if (blackMinutes == 10 && blackSeconds == 0) {
                        System.out.println("white won! (time limit)");
                        timer.cancel();
                    } else {
                        if (whiteSeconds == 59) {
                            whiteMinutes++;
                            whiteSeconds = 0;
                        } else {
                            whiteSeconds++;
                        }
                    }

                }else {
                    if (blackSeconds == 59) {
                        blackMinutes++;
                        blackSeconds = 0;
                    } else {
                        blackSeconds++;
                    }

                }
                Application.setWhiteClock(whiteMinutes + ":" + whiteSeconds);
                Application.setBlackClock(blackMinutes + ":" + blackSeconds);
            }
        }, INITIAL_DELAY, DELAY);
    }

}