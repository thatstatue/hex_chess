package Piece;

import ir.sharif.math.bp02_1.hex_chess.graphics.Application;
import ir.sharif.math.bp02_1.hex_chess.graphics.listeners.EventListener;
import ir.sharif.math.bp02_1.hex_chess.graphics.models.HexagonCell;
import ir.sharif.math.bp02_1.hex_chess.graphics.models.StringColor;
import ir.sharif.math.bp02_1.hex_chess.util.PieceName;

import java.awt.*;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.Clock;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import static Piece.Pawn.promote;

public class Manager implements EventListener {
    public Color enemyColor = Color.BLACK;
    public static boolean check2Called;
    public static boolean checkCalled;
    static Application changesOnApp;
    Piece piece;
    private final String columnSet = "_abcdefghikl";
    private int nowBlueRow = 0 , threatRow =0, threatICol = 12;
    private char nowBlueCol = 'q';
    private final Color nullColor1 = Color.decode("#975e64"),
            nullColor2 = Color.decode("#d3ab97"),
            nullColor3 = Color.decode("#7C4F63");
    public String king = "white";
    String soundFilePath = "move.wav";
    private String nowBlueText = "";
    private Color nowBlueTextColor ;
    private String kingPosition (Color color){
        if (color.equals(Color.BLACK)){
            for (HexagonCell cell : changesOnApp.getSavedBlacks()){
                if (cell != null && cell.getText().equals(PieceName.BLACK_KING)) {
                    return cell.getCol()+""+cell.getRow();
                }
            }
        }else{
            for (HexagonCell cell : changesOnApp.getSavedWhites()){
                if (cell != null && cell.getText().equals(PieceName.WHITE_KING)) {
                    return cell.getCol()+""+cell.getRow();
                }
            }
        }
        return "n23";
    }
    private int iColed(char col){
        int iCol = 12;
        for (int i = 1; i<= 11; i++){
            if (columnSet.charAt(i) == col){
                iCol = i;
                break;
            }
        }
        return iCol;
    }
    public Manager(Application application) {
        changesOnApp = application;
    }
    private boolean moveIsValid(int row, char col){
        int iCol = iColed(col);
        return row > 0 && row <= 11 && iCol > 0 && iCol < 12 && ((row - iCol <= 5 && iCol <= 6) || (iCol + row <= 17));
    }
    public void check4check(Color color){
        check2Called = true;
        checkCalled= false;
        String s;
        if (color.equals(Color.BLACK)){
            s = kingPosition(Color.WHITE);
            king = "white";
            char colK = s.charAt(0);
            int rowK = Integer.parseInt(s.substring(1));
            for (HexagonCell cell : changesOnApp.getSavedBlacks()){
                if (cell != null && !cell.getText().equals(PieceName.BLACK_KING)) {
                    possibleActions(cell.getRow(), cell.getCol());
                }
                if (moveIsValid(rowK, colK) && (changesOnApp.getCellBG(rowK , colK).equals(Color.RED)) || changesOnApp.getCellBG(rowK , colK).equals(Color.GREEN)){
                    checkCalled = true;
                }
                check2Called = true;
                clearPossibleActions();
            }
        }else{
            s = kingPosition(Color.BLACK);
            king = "black";
            char colK = s.charAt(0);
            int rowK = Integer.parseInt(s.substring(1));
            for (HexagonCell cell : changesOnApp.getSavedWhites()){
                if (cell != null && !cell.getText().equals(PieceName.WHITE_KING)) {
                    possibleActions(cell.getRow(), cell.getCol());
                }
                if (moveIsValid(rowK, colK) && changesOnApp.getCellBG(rowK , colK).equals(Color.RED)) {
                    checkCalled = true;
                }
                check2Called = true;
                clearPossibleActions();
            }

        }
        check2Called = false;
    }
    public void check4mate(Color color) { //color is enemy
        boolean isMate = true;
        if (color.equals(Color.BLACK)) {
            for (HexagonCell cell : changesOnApp.getSavedBlacks()) {
                if (cell != null) {
                    if (!cell.getText().equals(PieceName.BLACK_KING)) {
                        possibleActions(cell.getRow(), cell.getCol());
                        pin(cell.getRow(), cell.getCol(), cell.getText(), cell.getTextColor());
                        for (HexagonCell cell2 : changesOnApp.getCells()) {
                            if (cell2 != null && (cell2.getBackGroundColor().equals(Color.GREEN) || cell2.getBackGroundColor().equals(Color.RED))){
                                isMate = false;
                                break;
                            }
                        }
                    } else {
                        int iCol = iColed(cell.getCol());
                        kingActions(Color.WHITE);
                        hasKingMoves(cell.getRow(), iCol, Color.WHITE, false);
                        char tCol = kingPosition(Color.WHITE).charAt(0);
                        int tICol = iColed(tCol);
                        int tRow = Integer.parseInt(kingPosition(Color.WHITE).substring(1));
                        hasKingMoves(tRow, tICol, Color.BLACK, true);
                        for (HexagonCell cell2 : changesOnApp.getCells()) {
                            if (cell2 != null && (cell2.getBackGroundColor().equals(Color.GRAY) || cell2.getBackGroundColor().equals(Color.DARK_GRAY))){
                                isMate = false;
                                break;
                            }
                        }
                        clearPossibleActions();
                    }
                    clearPossibleActions();
                }
            }
            king = "white";
        } else {
            for (HexagonCell cell : changesOnApp.getSavedWhites()) {
                if (cell != null) {
                    if (!cell.getText().equals(PieceName.WHITE_KING)) {
                        possibleActions(cell.getRow(), cell.getCol());

                        pin(cell.getRow(), cell.getCol(), cell.getText(), cell.getTextColor());
                        for (HexagonCell cell2 : changesOnApp.getCells()) {
                            if (cell2 != null && (cell2.getBackGroundColor().equals(Color.GREEN) || cell2.getBackGroundColor().equals(Color.RED))){
                                isMate = false;
                                break;
                            }
                        }
                    } else {
                        int iCol = iColed(cell.getCol());
                        kingActions(Color.BLACK);
                        hasKingMoves(cell.getRow(), iCol, Color.BLACK, false);
                        char tCol = kingPosition(Color.BLACK).charAt(0);
                        int tICol = iColed(tCol);
                        int tRow = Integer.parseInt(kingPosition(Color.BLACK).substring(1));
                        hasKingMoves(tRow, tICol, Color.WHITE, true);
                        for (HexagonCell cell2 : changesOnApp.getCells()) {
                            if (cell2 != null && (cell2.getBackGroundColor().equals(Color.GRAY) || cell2.getBackGroundColor().equals(Color.DARK_GRAY))){
                                isMate = false;
                                break;
                            }
                        }
                        clearPossibleActions();
                    }
                    clearPossibleActions();
                }
            }
            king = "black";
        }
        if (isMate) {
            changesOnApp.setMessage(king + " won!");
            changesOnApp.showMessagePopup(king + " won!");
            onNewGame();
        } else {
            if (color.equals(Color.BLACK)) {
                king = "black";
            } else {
                king = "white";
            }
        }
    }

    private void coloredBGAction(int row, char col){
        SoundPlayer.playSound(soundFilePath);
        changesOnApp.setCellProperties(row, col, nowBlueText, null, nowBlueTextColor);
        changesOnApp.setCellProperties(nowBlueRow, nowBlueCol, null, null, null);
        clearPossibleActions();
        check4check(changesOnApp.turn);
        changesOnApp.switchTurn();
        if (checkCalled){
            changesOnApp.showMessagePopup(king + " king is in check!");
        }
        check4mate(changesOnApp.turn);

    }
    public Color other(Color color){
        if (color.equals(Color.BLACK)){
            return Color.WHITE;
        }
        return Color.BLACK;
    }
    @Override
    public void onClick(int row, char col) {
        HexagonCell targetCell = changesOnApp.getCell(row, col);
        piece = null;
        Color clickedColor = Color.WHITE;
        if (changesOnApp.getCellBG(row, col).equals(Color.CYAN)) {
            clearPossibleActions();
        }
        else if (changesOnApp.getCellBG(row, col).equals(Color.GREEN)) {
            coloredBGAction(row, col);

        } else if (changesOnApp.getCellBG(row, col).equals(Color.RED)) {
            capture(changesOnApp.getCell(row, col));
            coloredBGAction(row, col);

        } else {
            //if it's background is null, u're choosing another piece
            //checking if allowed to click there:
            if (targetCell == null || targetCell.getText().equals("") || targetCell.getText() == null) {
                clearPossibleActions();
                changesOnApp.setCellBG(row, col, Color.YELLOW);

            } else {
                if (targetCell.getTextColor().equals(Color.BLACK)) {
                    clickedColor = Color.BLACK;
                }
                if (changesOnApp.turn.equals(clickedColor)) {

                    //saving the data of this clicked slot
                    nowBlueRow = targetCell.getRow();
                    nowBlueCol = targetCell.getCol();
                    nowBlueText = targetCell.getText();
                    nowBlueTextColor = targetCell.getTextColor();
                    clearPossibleActions();
                    //highlighting the new slot
                    enemyColor = other(clickedColor);
                    changesOnApp.setCellBG(row, col, Color.CYAN);
                    possibleActions(row, col);
                    pin(row, col, nowBlueText, nowBlueTextColor);

                } else {
                    changesOnApp.showMessagePopup("invalid move, it's not your turn!");
                }
            }
        }
        //promotion for pawn
        int iCol = iColed(col);
        if ((Manager.changesOnApp.getCell(row, col).getText().equals(PieceName.BLACK_PAWN) && row == 1) ||
                Manager.changesOnApp.getCell(row, col).getText().equals(PieceName.WHITE_PAWN) && ((row - iCol == 5 && iCol <= 6) || (iCol + row == 17))) {
            promote(row, col);
            Manager.changesOnApp.switchTurn();
            check4check(Manager.changesOnApp.turn);
            if (checkCalled){
                changesOnApp.showMessagePopup(king + " king is in check!");
            }
            Manager.changesOnApp.switchTurn();
            check4mate(Manager.changesOnApp.turn);
        }
        changesOnApp.setMessage(king + "'s turn");
        File here = new File("");
        File save = new File(here.getAbsolutePath() , "saved");
        if (save.exists()) {
            onSave(save);
        }else{
            System.out.println("file doesn't exist!");
        }
    }

    private void clearPossibleActions(){
        for (HexagonCell cell : changesOnApp.getCells()) {
            if (changesOnApp.getCellBG(cell.getRow(), cell.getCol()).equals(Color.DARK_GRAY)){
                changesOnApp.setCellBG(cell.getRow(), cell.getCol(), Color.RED);
            }else if(changesOnApp.getCellBG(cell.getRow(), cell.getCol()).equals(Color.GRAY)){
                changesOnApp.setCellBG(cell.getRow(), cell.getCol(), Color.GREEN);
            }else {
                changesOnApp.setCellBG(cell.getRow(), cell.getCol(), null);
            }
        }
    }

    private void kingActions(Color enemy){
        changesOnApp.switchTurn();
        check2Called = true;
        if (enemy.equals(Color.BLACK)){
            for (HexagonCell cell : changesOnApp.getSavedBlacks()) {
                if (!cell.getText().equals(PieceName.BLACK_KING)) {
                    possibleActions(cell.getRow(), cell.getCol());
                }else {
                    threatRow = cell.getRow();
                    threatICol = iColed(cell.getCol());
                }
            }
        }else{
            for (HexagonCell cell : changesOnApp.getSavedWhites()) {
                if (!cell.getText().equals(PieceName.WHITE_KING)) {
                    possibleActions(cell.getRow(), cell.getCol());
                }else {
                    threatRow = cell.getRow();
                    threatICol = iColed(cell.getCol());
                }
            }
        }
        check2Called = false;
        changesOnApp.switchTurn();
    }
    private void capture(HexagonCell enemyCell){
        changesOnApp.addRemovedPiece(new StringColor(enemyCell.getText() , enemyCell.getTextColor()));
    }
    public void oneMove (int newRow, char newCol, Color enemy, boolean threat) {
        if (newRow>=1 && newRow<=11 && newCol>='a' && newCol<= 'l' &&
                ((newCol<= 'f' && newRow - newCol <= 6 - 'a') || (newCol>'f' && newRow + newCol <= 'f' + 11))) {
            HexagonCell targetCell = changesOnApp.getCell(newRow, newCol);
            if (targetCell == null ||
                    targetCell.getText().equals("") ||
                    targetCell.getText() == null) {
                if (threat) {
                    changesOnApp.setCellBG(newRow, newCol, null);
                } else {
                    if (changesOnApp.getCellBG(newRow, newCol).equals(nullColor1)
                            || changesOnApp.getCellBG(newRow, newCol).equals(nullColor2)
                            || changesOnApp.getCellBG(newRow, newCol).equals(nullColor3)) {
                        changesOnApp.setCellBG(newRow, newCol, Color.GRAY);
                    } else { //threaten
                        changesOnApp.setCellBG(newRow, newCol, null);
                    }
                }
            } else if (targetCell.getTextColor().equals(enemy)) {
                if (threat) {
                    changesOnApp.setCellBG(newRow, newCol, null);
                } else {
                    if (changesOnApp.getCellBG(newRow, newCol).equals(nullColor1)
                            || changesOnApp.getCellBG(newRow, newCol).equals(nullColor2)
                            || changesOnApp.getCellBG(newRow, newCol).equals(nullColor3) ||
                            changesOnApp.getCellBG(newRow, newCol).equals(Color.RED)) {
                        changesOnApp.setCellBG(newRow, newCol, Color.DARK_GRAY);
                    }
                }
            }
        }
    }
    private void pin (int tryRow, char tryCol, String tryText, Color tryTestColor){
        java.util.List<HexagonCell> gredMoves = new CopyOnWriteArrayList<>();
        java.util.List<HexagonCell> greenMoves = new CopyOnWriteArrayList<>();
        List<HexagonCell> redMoves = new CopyOnWriteArrayList<>();
        for (HexagonCell cell : changesOnApp.getCells()){
            if (cell!= null && cell.getBackGroundColor().equals(Color.GREEN)){
                gredMoves.add(cell);
            }
        }
        for (HexagonCell cell :gredMoves){
            String beforeText = cell.getText();
            Color beforeColor = cell.getTextColor();
            changesOnApp.setCellProperties(cell.getRow(), cell.getCol(), tryText, null, tryTestColor);
            changesOnApp.setCellProperties(tryRow, tryCol, null, null, null);
            clearPossibleActions();
            changesOnApp.switchTurn();
            check4check(changesOnApp.turn);
            changesOnApp.switchTurn();
            changesOnApp.setCellProperties(cell.getRow(), cell.getCol(), beforeText, Color.GREEN, beforeColor);
            changesOnApp.setCellProperties(tryRow, tryCol, tryText, Color.CYAN, tryTestColor);
            if (checkCalled){
                changesOnApp.setCellProperties(cell.getRow(), cell.getCol(), beforeText, null, beforeColor);
            }else{
                greenMoves.add(cell);
            }
            checkCalled = true;
        }
        gredMoves.clear();
        possibleActions(tryRow, tryCol);
        for (HexagonCell cell : changesOnApp.getCells()){
            if (cell!= null && cell.getBackGroundColor().equals(Color.RED)){
                gredMoves.add(cell);
            }
        }
        for (HexagonCell cell : gredMoves){
            String beforeText = cell.getText();
            Color beforeColor = cell.getTextColor();
            changesOnApp.setCellProperties(cell.getRow(), cell.getCol(), tryText, null, tryTestColor);
            changesOnApp.setCellProperties(tryRow, tryCol, null, null, null);
            clearPossibleActions();
            changesOnApp.switchTurn();
            check4check(changesOnApp.turn);
            changesOnApp.switchTurn();
            changesOnApp.setCellProperties(cell.getRow(), cell.getCol(), beforeText, Color.RED, beforeColor);
            changesOnApp.setCellProperties(tryRow, tryCol, tryText, Color.CYAN, tryTestColor);
            if (checkCalled){
                changesOnApp.setCellBG(cell.getRow(), cell.getCol(), null);
            }else{
                redMoves.add(cell);
            }
            checkCalled = true;
        }
        clearPossibleActions();
        changesOnApp.setCellBG(tryRow,tryCol,Color.CYAN);
        for (HexagonCell cell: greenMoves){
            changesOnApp.setCellBG(cell.getRow(), cell.getCol(), Color.GREEN);
        }
        for (HexagonCell cell: redMoves){
            changesOnApp.setCellBG(cell.getRow(), cell.getCol(), Color.RED);
        }
    }

    private void possibleActions(int row, char col){
        piece = null;
        HexagonCell cell = changesOnApp.getCell(row, col);
        int iCol = iColed(col);
        // jaee ke col vaisade toye iCol rikhte shod
        int newRow = row;
        int iColPointer = iCol;
        char newCol = columnSet.charAt(iColPointer);

        Color enemy = other(cell.getTextColor());

        if (cell.getText().equals(PieceName.BLACK_ROCK) || cell.getText().equals(PieceName.WHITE_ROCK)){
            piece = new Rook(row, col, cell.getText(), cell.getBackGroundColor(), cell.getTextColor(), enemy);
        }
        else if(cell.getText().equals(PieceName.WHITE_PAWN) || cell.getText().equals(PieceName.BLACK_PAWN)){
            piece = new Pawn(row, col, cell.getText(), cell.getBackGroundColor(), cell.getTextColor(), enemy);
        }
        else if (cell.getText().equals(PieceName.WHITE_BISHOP) ||
                cell.getText().equals(PieceName.BLACK_BISHOP)){
            piece = new Bishop(row, col, cell.getText(), cell.getBackGroundColor(), cell.getTextColor(), enemy);
        }
        else if (cell.getText().equals(PieceName.WHITE_QUEEN) ||
                cell.getText().equals(PieceName.BLACK_QUEEN)){
            piece = new Queen(row, col, cell.getText(), cell.getBackGroundColor(), cell.getTextColor(), enemy);
        }
        else if(cell.getText().equals(PieceName.WHITE_KING) || cell.getText().equals(PieceName.BLACK_KING)) {
            kingActions(enemy);
            hasKingMoves(row, iCol, enemy , false); // king can go to
//            possible enemy moves

            hasKingMoves(threatRow, threatICol, enemy , true); //kings should not threaten each other
            clearPossibleActions();
            changesOnApp.setCellBG(row, col, Color.CYAN);
            piece = null;

        }
        else if (cell.getText().equals(PieceName.WHITE_KNIGHT) ||
                cell.getText().equals(PieceName.BLACK_KNIGHT)){
            piece = new Knight(row, col, cell.getText(), cell.getBackGroundColor(), cell.getTextColor(), enemy);
        }
        if(piece!= null) {
            piece.showValidMoves();
        }
    }

    private void hasKingMoves(int row, int iCol, Color enemy, boolean threat){
        int newRow = row ,iColPointer = iCol ;
        char newCol = columnSet.charAt(iColPointer);

        //down and up
        if (row - 1 >= 1 ){
            newRow = row - 1;
            oneMove(newRow, newCol, enemy , threat);
        }
        if (row + 1 <= 11){
            newRow = row + 1;
            oneMove(newRow, newCol, enemy , threat);
        }

        if (iCol - 1 >= 1) {
            //left <6
            if (iCol - 1 < 6) {
                iColPointer = iCol - 1;
                newCol = columnSet.charAt(iColPointer);
                if (row - 1 >= 1) {
                    newRow = row - 1;
                    oneMove(newRow, newCol, enemy , threat);
                    if (row - 2 >= 1) {
                        newRow = row - 2;
                        oneMove(newRow, newCol, enemy , threat);
                    }
                }
                if (row <= 11) {
                    newRow = row;
                    oneMove(newRow, newCol, enemy , threat);
                    if (row + 1 <= 11) {
                        newRow = row + 1;
                        oneMove(newRow, newCol, enemy , threat);
                    }
                }
            }
            //left >=6
            else {
                iColPointer = iCol - 1;
                newCol = columnSet.charAt(iColPointer);
                if (row >= 1) {
                    newRow = row;
                    oneMove(newRow, newCol, enemy , threat);
                    if (row - 1 >= 1) {
                        newRow = row - 1;
                        oneMove(newRow, newCol, enemy , threat);
                    }
                }
                if (row + 1 <= 11) {
                    newRow = row + 1;
                    oneMove(newRow, newCol, enemy , threat);
                    if (row + 2 <= 11) {
                        newRow = row + 2;
                        oneMove(newRow, newCol, enemy , threat);
                    }
                }
            }
        }
        if (iCol + 1 <= 11) {
            //right >6
            if (iCol + 1 > 6) {
                iColPointer = iCol + 1;
                newCol = columnSet.charAt(iColPointer);
                if (row - 1 >= 1) {
                    newRow = row - 1;
                    oneMove(newRow, newCol, enemy , threat);
                    if (row - 2 >= 1) {
                        newRow = row - 2;
                        oneMove(newRow, newCol, enemy , threat);
                    }
                }
                if (row <= 11) {
                    newRow = row;
                    oneMove(newRow, newCol, enemy , threat);
                    if (row + 1 <= 11) {
                        newRow = row + 1;
                        oneMove(newRow, newCol, enemy , threat);
                    }
                }
            }
            //right <=6
            else {
                iColPointer = iCol + 1;
                newCol = columnSet.charAt(iColPointer);
                if (row >= 1) {
                    newRow = row;
                    oneMove(newRow, newCol, enemy , threat);
                    if (row - 1 >= 1) {
                        newRow = row - 1;
                        oneMove(newRow, newCol, enemy , threat);
                    }
                }
                if (row + 1 <= 11) {
                    newRow = row + 1;
                    oneMove(newRow, newCol, enemy, threat);
                    if (row + 2 <= 11) {
                        newRow = row + 2;
                        oneMove(newRow, newCol, enemy , threat);
                    }
                }
            }
        }

        if (iCol - 2 >= 1){
            iColPointer = iCol - 2;
            newCol = columnSet.charAt(iColPointer);
            // leftleft < 5
            if( iCol - 2 < 5) {
                newRow = row - 1;
            }
            // leftleft == 5
            else if (iCol == 7){
                newRow = row;
            }
            // leftleft > 5
            else {
                newRow = row + 1;
            }
            oneMove(newRow, newCol, enemy , threat);
        }
        if (iCol + 2 <= 11){
            iColPointer = iCol + 2;
            newCol = columnSet.charAt(iColPointer);
            // rightright >7
            if( iCol + 2 > 7) {
                newRow = row - 1;
            }
            // rightright == 7
            else if (iCol == 5){
                newRow = row;
            }
            // rightright < 7
            else {
                newRow = row + 1;
            }
            oneMove(newRow, newCol, enemy , threat);
        }
    }//done
    @Override
    public void onSave(File file) {
        String turn = "";
        if (changesOnApp.turn.equals(Color.BLACK)){
            turn += "B";
        }else {
            turn += "W";
        }
        String pieces = "";
        int t = 0;
        for (HexagonCell cell: changesOnApp.getCells()){
            if (cell != null){
                pieces += "(" +cell.getCol() +cell.getRow() +","+ cell.getText()+"~"+cell.getTextColor() + ")";
                t++;
            }
        }
        String removed = "";
        int t2 = 0;
        for (StringColor cell: changesOnApp.getPieces()){
            if (cell != null){
                removed += "(,"+ cell.getText()+"~"+cell.getColor() + ")";
                t2++;
            }
        }
        try {
            Path fileName = Path.of(file.getAbsolutePath());
            String check = "D";
            if (checkCalled){
                check = "C";
            }
            String w = Application.getClock();
            Files.writeString(fileName, ( t +"#" + pieces + turn+ check + "|" + t2 +"#" + w + ">" +removed ));
        }
        catch (Exception e) {
            System.out.println("saving moves failed");
        }

    }

    @Override
    public void onLoad(File file) {
        Path fileName = Path.of(file.getAbsolutePath());
        try {
            String fileIn = Files.readString(fileName);
            int i = 0;
            while (fileIn.charAt(i) != '#'){
                i++;
            }
            int t = 0;
            if (i>0) {
                t = Integer.parseInt(fileIn.substring(0, i));
            }
            if (t >0) {
                fileIn = fileIn.substring(i+1);
                for (i = 0 ; i < t; i++){
                    int endChar = 0;
                    while (fileIn.charAt(endChar) != ')'){
                        endChar ++;
                    }
                    endChar++;
                    String content = fileIn.substring(0, endChar);
                    char col = content.charAt(1);
                    int rowEnd = 2;
                    while (content.charAt(rowEnd) != ','){
                        rowEnd ++;
                    }
                    int row = Integer.parseInt(content.substring(2, rowEnd));
                    int textEnd = rowEnd;
                    while (content.charAt(textEnd) != '~'){
                        textEnd ++;
                    }
                    String text  = "";
                    if (textEnd>rowEnd+1){
                        text = content.substring(rowEnd+1 , textEnd);
                    }
                    int start = textEnd+1;

                    while (content.charAt(start) != '='){
                        start++;
                    }
                    start++;
                    int end = start;
                    while (content.charAt(end) != ','){
                        end ++;
                    }
                    int R = Integer.parseInt(content.substring(start, end));
                    end+= 3;
                    start = end ;
                    while (content.charAt(end) != ','){
                        end ++;
                    }
                    int G = Integer.parseInt(content.substring(start, end));
                    end+= 3;
                    start = end ;
                    while (content.charAt(end) != ']'){
                        end ++;
                    }
                    int B = Integer.parseInt(content.substring(start, end));
                    Color textColor = new Color(R, G, B);
                    changesOnApp.setCellProperties(row, col, text, null, textColor);
                    fileIn = fileIn.substring(endChar);
                }
                if (fileIn.charAt(0) == 'B'){
                    changesOnApp.turn = Color.BLACK;
                    king = "black";
                }else{
                    changesOnApp.turn = Color.WHITE;
                    king = "white";
                }

                changesOnApp.setMessage(king + "'s turn");
                if (fileIn.charAt(1) == 'C'){
                    checkCalled = true;
                }
            }
            int newS = 0;
            while (fileIn.charAt(newS) != '|'){
                newS++;
            }
            newS++;
            int i2 = 0;
            while (fileIn.charAt(i2) != '#'){
                i2++;
            }
            int t2 = 0;
            if (i2>0) {
                t2 = Integer.parseInt(fileIn.substring(newS, i2)); // + w + ">"
            }
            i2++;
            int i3 = i2;
            while (fileIn.charAt(i3) != '<'){
                i3++;
            }
            String wC = fileIn.substring(i2, i3);
            MyClock.setWhiteMinutes(wC.charAt(0) - 48);
            MyClock.setWhiteSeconds(Integer.parseInt(wC.substring(2)));
            i3++;
            int i4 = i3;
            while (fileIn.charAt(i4) != '>'){
                i4++;
            }
            String bC = fileIn.substring(i3, i4);
            MyClock.setBlackMinutes(bC.charAt(0)- 48);
            MyClock.setBlackSeconds(Integer.parseInt(bC.substring(2)));
            i4++;
            if (t2 >0) {
                fileIn = fileIn.substring(i4 + 1);
                for (i = 0; i < t2; i++) {
                    int endChar = 0;
                    while (fileIn.charAt(endChar) != ')') {
                        endChar++;
                    }
                    endChar++;
                    String content = fileIn.substring(0, endChar);

                    int rowEnd = 0;
                    while (content.charAt(rowEnd) != ',') {
                        rowEnd++;
                    }
                    int textEnd = rowEnd;
                    while (content.charAt(textEnd) != '~') {
                        textEnd++;
                    }
                    String text = "";
                    if (textEnd > rowEnd + 1) {
                        text = content.substring(rowEnd + 1, textEnd);
                    }
                    int start = textEnd + 1;

                    while (content.charAt(start) != '=') {
                        start++;
                    }
                    start++;
                    int end = start;
                    while (content.charAt(end) != ',') {
                        end++;
                    }
                    int R = Integer.parseInt(content.substring(start, end));
                    end += 3;
                    start = end;
                    while (content.charAt(end) != ',') {
                        end++;
                    }
                    int G = Integer.parseInt(content.substring(start, end));
                    end += 3;
                    start = end;
                    while (content.charAt(end) != ']') {
                        end++;
                    }
                    int B = Integer.parseInt(content.substring(start, end));
                    Color textColor = new Color(R, G, B);
                    changesOnApp.addRemovedPiece(new StringColor(text , textColor));
                    fileIn = fileIn.substring(endChar);
                }
            }
        }catch (Exception e){
            System.out.println("error occurred!");
        }
    }


    @Override
    public void onNewGame() {
        for (HexagonCell cell : changesOnApp.getCells()) {
            changesOnApp.setCellProperties(cell.getRow(), cell.getCol(), null, null, null);
        }
        changesOnApp.turn = Color.WHITE;
        checkCalled = false;
        king = "white";
        changesOnApp.setRemovedPieces(new StringColor[]{});
        changesOnApp.setMessage("white's turn");
        MyClock.setWhiteMinutes(0);
        MyClock.setBlackMinutes(0);
        MyClock.setBlackSeconds(0);
        MyClock.setWhiteSeconds(0);

        //    black:
        changesOnApp.setCellProperties(11, 'f', PieceName.BLACK_BISHOP, null, Color.BLACK);
        changesOnApp.setCellProperties(10, 'f', PieceName.BLACK_BISHOP, null, Color.BLACK);
        changesOnApp.setCellProperties(9, 'f', PieceName.BLACK_BISHOP, null, Color.BLACK);
        changesOnApp.setCellProperties(10, 'e', PieceName.BLACK_QUEEN, null, Color.BLACK);
        changesOnApp.setCellProperties(10, 'g', PieceName.BLACK_KING, null, Color.BLACK);
        changesOnApp.setCellProperties(9, 'd', PieceName.BLACK_KNIGHT, null, Color.BLACK);
        changesOnApp.setCellProperties(9, 'h', PieceName.BLACK_KNIGHT, null, Color.BLACK);
        changesOnApp.setCellProperties(8, 'c', PieceName.BLACK_ROCK, null, Color.BLACK);
        changesOnApp.setCellProperties(8, 'i', PieceName.BLACK_ROCK, null, Color.BLACK);
        changesOnApp.setCellProperties(7, 'b', PieceName.BLACK_PAWN, null, Color.BLACK);
        changesOnApp.setCellProperties(7, 'c', PieceName.BLACK_PAWN, null, Color.BLACK);
        changesOnApp.setCellProperties(7, 'd', PieceName.BLACK_PAWN, null, Color.BLACK);
        changesOnApp.setCellProperties(7, 'e', PieceName.BLACK_PAWN, null, Color.BLACK);
        changesOnApp.setCellProperties(7, 'k', PieceName.BLACK_PAWN, null, Color.BLACK);
        changesOnApp.setCellProperties(7, 'f', PieceName.BLACK_PAWN, null, Color.BLACK);
        changesOnApp.setCellProperties(7, 'g', PieceName.BLACK_PAWN, null, Color.BLACK);
        changesOnApp.setCellProperties(7, 'h', PieceName.BLACK_PAWN, null, Color.BLACK);
        changesOnApp.setCellProperties(7, 'i', PieceName.BLACK_PAWN, null, Color.BLACK);

        //    white:
        changesOnApp.setCellProperties(1, 'f', PieceName.WHITE_BISHOP, null, Color.WHITE);
        changesOnApp.setCellProperties(2, 'f', PieceName.WHITE_BISHOP, null, Color.WHITE);
        changesOnApp.setCellProperties(3, 'f', PieceName.WHITE_BISHOP, null, Color.WHITE);
        changesOnApp.setCellProperties(1, 'e', PieceName.WHITE_QUEEN, null, Color.WHITE);
        changesOnApp.setCellProperties(1, 'g', PieceName.WHITE_KING, null, Color.WHITE);
        changesOnApp.setCellProperties(1, 'd', PieceName.WHITE_KNIGHT, null, Color.WHITE);
        changesOnApp.setCellProperties(1, 'h', PieceName.WHITE_KNIGHT, null, Color.WHITE);
        changesOnApp.setCellProperties(1, 'h', PieceName.WHITE_KNIGHT, null, Color.WHITE);
        changesOnApp.setCellProperties(1, 'c', PieceName.WHITE_ROCK, null, Color.WHITE);
        changesOnApp.setCellProperties(1, 'i', PieceName.WHITE_ROCK, null, Color.WHITE);
        changesOnApp.setCellProperties(1, 'b', PieceName.WHITE_PAWN, null, Color.WHITE);
        changesOnApp.setCellProperties(1, 'k', PieceName.WHITE_PAWN, null, Color.WHITE);
        changesOnApp.setCellProperties(2, 'c', PieceName.WHITE_PAWN, null, Color.WHITE);
        changesOnApp.setCellProperties(2, 'i', PieceName.WHITE_PAWN, null, Color.WHITE);
        changesOnApp.setCellProperties(3, 'd', PieceName.WHITE_PAWN, null, Color.WHITE);
        changesOnApp.setCellProperties(3, 'h', PieceName.WHITE_PAWN, null, Color.WHITE);
        changesOnApp.setCellProperties(4, 'e', PieceName.WHITE_PAWN, null, Color.WHITE);
        changesOnApp.setCellProperties(4, 'g', PieceName.WHITE_PAWN, null, Color.WHITE);
        changesOnApp.setCellProperties(5, 'f', PieceName.WHITE_PAWN, null, Color.WHITE);
    }
}
