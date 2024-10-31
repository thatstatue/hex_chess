package ir.sharif.math.bp02_1.hex_chess.graphics;

import ir.sharif.math.bp02_1.hex_chess.graphics.listeners.EventListener;
import ir.sharif.math.bp02_1.hex_chess.graphics.models.HexagonCell;
import ir.sharif.math.bp02_1.hex_chess.graphics.models.StringColor;
import ir.sharif.math.bp02_1.hex_chess.graphics.panel.BoardPanel;
import ir.sharif.math.bp02_1.hex_chess.graphics.panel.RemovedPiecesPanel;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class Application {
    private final Frame mainFrame;
    private final BoardPanel boardPanel;
    private final RemovedPiecesPanel removedPiecesPanel;

    public static Color turn = Color.WHITE;
    public Application() {

        this.mainFrame = new Frame();
        this.boardPanel = new BoardPanel();
        this.mainFrame.getContentPane().setLayout(new BorderLayout());
        this.mainFrame.getContentPane().add(boardPanel, BorderLayout.CENTER);

        removedPiecesPanel = new RemovedPiecesPanel();
        this.mainFrame.getContentPane().add(removedPiecesPanel, BorderLayout.LINE_END);

        this.mainFrame.setVisible(true);
        boardPanel.initialize();
    }
    public List<HexagonCell> getCells() {
        return boardPanel.getCells();
    }
    public StringColor[] getPieces() {
        return removedPiecesPanel.getPieces();
    }
    public List<HexagonCell> getSavedWhites(){
        return boardPanel.getSavedWhites();
    }
    public List<HexagonCell> getSavedBlacks(){
        return boardPanel.getSavedBlacks();
    }
// nobat
    public void switchTurn(){
        if (turn.equals(Color.WHITE)) {
            turn = Color.BLACK;
        }else{
            turn = Color.WHITE;
        }
    }

    public void registerEventListener(EventListener eventListener) {
        if (eventListener != null) {
            boardPanel.setEventListener(eventListener);
            mainFrame.setEventListener(eventListener);
        }
    }

    public void setCellProperties(int row, char col, String text, Color backGroundColor, Color textColor) {
        boardPanel.setCellProperties(row, col, text, backGroundColor, textColor);
    }
    public void setCellBG(int row, char col, Color backGroundColor){
        boardPanel.setCellBG( row,  col, backGroundColor);
    }
    public Color getCellBG(int row, char col){
        return boardPanel.getCellBG( row,  col);
    }
    public HexagonCell getCell(int row, char col){
        return boardPanel.getCellColor(row, col);
    }
    public void setMessage(String text) {
        boardPanel.setMessage(text);
    }

    public static void setWhiteClock(String text) {
        BoardPanel.setWhiteClock(text);
    }
    public static String getClock() {
       return BoardPanel.getClock();
    }
    public static void setBlackClock(String text) {
        BoardPanel.setBlackClock(text);
    }

    public void showMessagePopup(String text) {
        JOptionPane.showMessageDialog(mainFrame, text);
    }

    public void setRemovedPieces(StringColor[] pieces) {
        this.removedPiecesPanel.setPieces(pieces);
    }
    public void addRemovedPiece(StringColor piece) {
        this.removedPiecesPanel.addPiece(piece);
    }


    public String showPromotionPopup() {
        String[] options = new String[]{"Knight", "Bishop", "Rook", "Queen"};
        return options[JOptionPane.showOptionDialog(mainFrame, "Choose your piece ^-^", "Promotion",
                JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE,
                null, options, null)];
    }

}
