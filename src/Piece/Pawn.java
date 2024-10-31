package Piece;
import ir.sharif.math.bp02_1.hex_chess.util.PieceName;

import java.awt.*;

import static Piece.Manager.check2Called;

public class Pawn extends Piece {
    public Pawn(int row, char col, String name, Color BGColor, Color textColor, Color enemy) {
        super(row, col, name, BGColor, textColor, enemy);
    }
    
    @Override
    public void showValidMoves() {
        int newRow = row;
        char newCol = col;
        if (textColor.equals(Color.WHITE)){
            if (check2Called){
                if (iCol + 1 <= 11) {
                    newCol = getNewCol(iCol + 1);
                    if (iCol >= 6) {
                        newRow = row;
                    } else {
                        newRow = row + 1;
                    }
                    Manager.changesOnApp.setCellBG(newRow, newCol, Color.GREEN);
                }
                if (iCol - 1 > 0) {
                    newCol = getNewCol(iCol - 1);
                    if (iCol <= 6) {
                        newRow = row;
                    } else {
                        newRow = row + 1;
                    }
                    Manager.changesOnApp.setCellBG(newRow, newCol, Color.GREEN);

                }
            }else {
                if (newRow + 1 > 0 && newRow + 1 <= 11) {
                    newRow++;
                    newCol = getNewCol(iCol);
                    if (Manager.changesOnApp.getCell(newRow, newCol) == null ||
                            Manager.changesOnApp.getCell(newRow, newCol).getText().equals("") ||
                            Manager.changesOnApp.getCell(newRow, newCol).getText() == null) {
                        Manager.changesOnApp.setCellBG(newRow, newCol, Color.GREEN);
                        //second step check
                        if ((getNewCol(row + 1) == col || getNewCol(11 - row) == col) && row < 6) {
                            newRow++;
                            if (newRow > 0 && newRow <= 11 && (Manager.changesOnApp.getCell(newRow, newCol) == null ||
                                    Manager.changesOnApp.getCell(newRow, newCol).getText().equals("") ||
                                    Manager.changesOnApp.getCell(newRow, newCol).getText() == null)) {
                                Manager.changesOnApp.setCellBG(newRow, newCol, Color.GREEN);
                            }
                        }
                    }

                    //enemy right and left check
                    if (iCol + 1 <= 11) {
                        newCol = getNewCol(iCol + 1);
                        if (iCol >= 6) {
                            newRow = row;
                        } else {
                            newRow = row + 1;
                        }
                        if (!(Manager.changesOnApp.getCell(newRow, newCol) == null ||
                                Manager.changesOnApp.getCell(newRow, newCol).getText().equals("") ||
                                Manager.changesOnApp.getCell(newRow, newCol).getText() == null) &&
                                Manager.changesOnApp.getCell(newRow, newCol).getTextColor().equals(enemy)) {
                            Manager.changesOnApp.setCellBG(newRow, newCol, Color.RED);
                        }
                    }
                    if (iCol - 1 > 0) {
                        newCol = getNewCol(iCol - 1);
                        if (iCol <= 6) {
                            newRow = row;
                        } else {
                            newRow = row + 1;
                        }
                        if (!(Manager.changesOnApp.getCell(newRow, newCol) == null ||
                                Manager.changesOnApp.getCell(newRow, newCol).getText().equals("") ||
                                Manager.changesOnApp.getCell(newRow, newCol).getText() == null) &&
                                Manager.changesOnApp.getCell(newRow, newCol).getTextColor().equals(enemy)) {
                            Manager.changesOnApp.setCellBG(newRow, newCol, Color.RED);
                        }
                    }
                }
            }
        }else  {
            if (check2Called){
                if (iCol + 1 <= 11) {
                    newCol = getNewCol(iCol + 1);
                    if (iCol >= 6) {
                        newRow = row - 1;
                    } else {
                        newRow = row;
                    }
                    Manager.changesOnApp.setCellBG(newRow, newCol, Color.GREEN);
                }
                if (iCol - 1 > 0) {
                    newCol = getNewCol(iCol - 1);
                    if (iCol <= 6) {
                        newRow = row - 1;
                    } else {
                        newRow = row;
                    }
                    Manager.changesOnApp.setCellBG(newRow, newCol, Color.GREEN);
                }
            }else {
                if (newRow - 1 > 0 && newRow + 1 <= 11) {
                    newRow--;
                    newCol = getNewCol(iCol);
                    if (Manager.changesOnApp.getCell(newRow, newCol) == null ||
                            Manager.changesOnApp.getCell(newRow, newCol).getText().equals("") ||
                            Manager.changesOnApp.getCell(newRow, newCol).getText() == null) {
                        Manager.changesOnApp.setCellBG(newRow, newCol, Color.GREEN);
                        //second step check
                        if (row == 7) {
                            newRow--;
                            if (Manager.changesOnApp.getCell(newRow, newCol) == null ||
                                    Manager.changesOnApp.getCell(newRow, newCol).getText().equals("") ||
                                    Manager.changesOnApp.getCell(newRow, newCol).getText() == null) {
                                Manager.changesOnApp.setCellBG(newRow, newCol, Color.GREEN);

                            }
                        }
                    }

                    //enemy right and left check
                    if (iCol + 1 <= 11) {
                        newCol = getNewCol(iCol + 1);
                        if (iCol >= 6) {
                            newRow = row - 1;
                        } else {
                            newRow = row;
                        }
                        if (!(Manager.changesOnApp.getCell(newRow, newCol) == null ||
                                Manager.changesOnApp.getCell(newRow, newCol).getText().equals("") ||
                                Manager.changesOnApp.getCell(newRow, newCol).getText() == null) &&
                                Manager.changesOnApp.getCell(newRow, newCol).getTextColor().equals(enemy)) {
                            Manager.changesOnApp.setCellBG(newRow, newCol, Color.RED);
                        }
                    }
                    if (iCol - 1 > 0) {
                        newCol = getNewCol(iCol - 1);
                        if (iCol <= 6) {
                            newRow = row - 1;
                        } else {
                            newRow = row;
                        }
                        if (Manager.changesOnApp.getCell(newRow, newCol).getText() != null && !Manager.changesOnApp.getCell(newRow, newCol).getText().equals("") &&
                                Manager.changesOnApp.getCell(newRow, newCol).getTextColor().equals(Color.WHITE)) {
                            Manager.changesOnApp.setCellBG(newRow, newCol, Color.RED);
                        }
                    }
                }
            }
        }
    }
    public static void promote(int row,char col){
        String s = Manager.changesOnApp.showPromotionPopup();
        if (s.equals("Knight")){
            if (row == 1){
                Manager.changesOnApp.setCellProperties(row, col, PieceName.BLACK_KNIGHT, null, Color.BLACK);
            }else{
                Manager.changesOnApp.setCellProperties(row, col, PieceName.WHITE_KNIGHT, null, Color.WHITE);
            }
        }else if(s.equals("Queen")){
            if (row == 1){
                Manager.changesOnApp.setCellProperties(row, col, PieceName.BLACK_QUEEN, null, Color.BLACK);
            }else{
                Manager.changesOnApp.setCellProperties(row, col, PieceName.WHITE_QUEEN, null, Color.WHITE);
            }
        }else if(s.equals("Rook")){
            if (row == 1){
                Manager.changesOnApp.setCellProperties(row, col, PieceName.BLACK_ROCK, null, Color.BLACK);
            }else{
                Manager.changesOnApp.setCellProperties(row, col, PieceName.WHITE_ROCK, null, Color.WHITE);
            }
        }else if(s.equals("Bishop")){
            if (row == 1){
                Manager.changesOnApp.setCellProperties(row, col, PieceName.BLACK_BISHOP, null, Color.BLACK);
            }else{
                Manager.changesOnApp.setCellProperties(row, col, PieceName.WHITE_BISHOP, null, Color.WHITE);
            }
        }
    }

}
