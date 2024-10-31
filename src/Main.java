import Piece.Manager;
import Piece.MyClock;
import Piece.SoundPlayer;
import ir.sharif.math.bp02_1.hex_chess.graphics.Application;
import ir.sharif.math.bp02_1.hex_chess.graphics.listeners.SystemOutEventListener;
import java.io.File;


public class Main {
    public static void main(String[] args) {
        SoundPlayer.playSound("newGame.wav");
        File here = new File("");
        File save = new File(here.getAbsolutePath() , "saved");
        Application application = new Application();
        Manager manager = new Manager(application);
        application.registerEventListener(new SystemOutEventListener());
        application.registerEventListener(manager);
        if (!save.exists()) {
            manager.onNewGame();
            MyClock.startTimer();
        }else {
            manager.onLoad(save);
            System.out.println("previous game still on");
            application.switchTurn();
            MyClock.startTimer();
            manager.check4check(Application.turn);
            if (Manager.checkCalled){
                application.showMessagePopup((manager.king) + " king is in check!");
            }
            application.switchTurn();
            manager.check4mate(Application.turn);

        }
    }
}