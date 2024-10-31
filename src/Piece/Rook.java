package Piece;

import java.awt.*;

import static Piece.Manager.check2Called;

public class Rook extends Piece{


    @Override
    public void showValidMoves() {
        //up and down:
        int iColPointer = iCol;
        int newRow = row+1;
        char newCol = col;
        while (newRow - iColPointer <= 5 && newRow>=1 && newRow<=11 && iColPointer<=11 ){
            if(Manager.changesOnApp.getCell(newRow, newCol) == null ||
                    Manager.changesOnApp.getCell(newRow, newCol).getText().equals("") ||
                    Manager.changesOnApp.getCell(newRow, newCol).getText() == null){
                Manager.changesOnApp.setCellBG(newRow, newCol, Color.GREEN);
            }
            else if (Manager.changesOnApp.getCell(newRow, newCol).getTextColor().equals(Manager.changesOnApp.turn) ){
                if (check2Called){
                    Manager.changesOnApp.setCellBG(newRow, newCol, Color.GREEN);
                }
                break;
            }else if(Manager.changesOnApp.getCell(newRow, newCol).getTextColor().equals(enemy)){
                Manager.changesOnApp.setCellBG(newRow, newCol, Color.RED);
                break;
            }
            newRow++;
        }
        newRow = row-1;
        while (newRow - iColPointer <= 5 && newRow>=1 && newRow<=11 && iColPointer<=11){
            if(Manager.changesOnApp.getCell(newRow, newCol) == null ||
                    Manager.changesOnApp.getCell(newRow, newCol).getText().equals("") ||
                    Manager.changesOnApp.getCell(newRow, newCol).getText() == null){
                Manager.changesOnApp.setCellBG(newRow, newCol, Color.GREEN);

            }
            else if (Manager.changesOnApp.getCell(newRow, newCol).getTextColor().equals(Manager.changesOnApp.turn) ){
                if (check2Called){
                    Manager.changesOnApp.setCellBG(newRow, newCol, Color.GREEN);
                }
                break;
            }else if(Manager.changesOnApp.getCell(newRow, newCol).getTextColor().equals(enemy)){
                Manager.changesOnApp.setCellBG(newRow, newCol, Color.RED);
                break;
            }
            newRow--;
        }

        if (iCol <= 6) {
            //from f to upleft
            iColPointer = iCol - 1;
            newRow = row;
            while (iColPointer > 0) {
                newCol = getNewCol(iColPointer);
                if (Manager.changesOnApp.getCell(newRow, newCol) == null
                        || Manager.changesOnApp.getCell(newRow, newCol).getText().equals("") ||
                        Manager.changesOnApp.getCell(newRow, newCol).getText() == null) {
                    Manager.changesOnApp.setCellBG(newRow, newCol, Color.GREEN);

                } else if (!(newRow == row && iColPointer == iCol) &&
                        Manager.changesOnApp.getCell(newRow, newCol).getTextColor().equals(Manager.changesOnApp.turn)) {
                    if (check2Called){
                        Manager.changesOnApp.setCellBG(newRow, newCol, Color.GREEN);
                    }
                    break;
                } else if (Manager.changesOnApp.getCell(newRow, newCol).getTextColor().equals(enemy)) {
                    Manager.changesOnApp.setCellBG(newRow, newCol, Color.RED);

                    break;
                }
                iColPointer--;
            }
            iColPointer = iCol + 1;
            boolean canContinue = true;
            while (iColPointer < 6) {
                newCol = getNewCol(iColPointer);
                if (Manager.changesOnApp.getCell(newRow, newCol) == null
                        || Manager.changesOnApp.getCell(newRow, newCol).getText().equals("") ||
                        Manager.changesOnApp.getCell(newRow, newCol).getText() == null) {
                    Manager.changesOnApp.setCellBG(newRow, newCol, Color.GREEN);

                } else if (!(newRow == row && iColPointer == iCol) && Manager.changesOnApp.getCell(newRow, newCol).getTextColor().equals(Manager.changesOnApp.turn)) {
                    if (check2Called){
                        Manager.changesOnApp.setCellBG(newRow, newCol, Color.GREEN);
                    }
                    canContinue = false;
                    break;
                } else if (Manager.changesOnApp.getCell(newRow, newCol).getTextColor().equals(enemy)) {
                    Manager.changesOnApp.setCellBG(newRow, newCol, Color.RED);
                    canContinue = false;
                    break;
                }
                iColPointer++;
            }
            //from f to downright
            if (canContinue) {
                iColPointer = 6;
                while (iColPointer <= 11 && newRow >= 1) {
                    newCol = getNewCol(iColPointer);
                    if (Manager.changesOnApp.getCell(newRow, newCol) == null
                            || Manager.changesOnApp.getCell(newRow, newCol).getText().equals("") ||
                            Manager.changesOnApp.getCell(newRow, newCol).getText() == null) {
                        Manager.changesOnApp.setCellBG(newRow, newCol, Color.GREEN);

                    } else if (!(newRow == row && iColPointer == iCol) && Manager.changesOnApp.getCell(newRow, newCol).getTextColor().equals(Manager.changesOnApp.turn)) {
                        if (check2Called){
                            Manager.changesOnApp.setCellBG(newRow, newCol, Color.GREEN);
                        }
                        break;
                    } else if (Manager.changesOnApp.getCell(newRow, newCol).getTextColor().equals(enemy)) {
                        Manager.changesOnApp.setCellBG(newRow, newCol, Color.RED);

                        break;
                    }
                    iColPointer++;
                    newRow--;
                }
            }
            //upright
            //newRow = (row -iCol + 6);
            newRow = row + 1;
            iColPointer = iCol + 1;
            canContinue = true;
            while (iColPointer < 6 && newRow <= 11) {
                newCol = getNewCol(iColPointer);
                if (Manager.changesOnApp.getCell(newRow, newCol) == null
                        || Manager.changesOnApp.getCell(newRow, newCol).getText().equals("") ||
                        Manager.changesOnApp.getCell(newRow, newCol).getText() == null) {
                    Manager.changesOnApp.setCellBG(newRow, newCol, Color.GREEN);

                } else if (!(newRow == row && iColPointer == iCol) && Manager.changesOnApp.getCell(newRow, newCol).getTextColor().equals(Manager.changesOnApp.turn)) {
                    if (check2Called){
                        Manager.changesOnApp.setCellBG(newRow, newCol, Color.GREEN);
                    }
                    canContinue = false;
                    break;
                } else if (Manager.changesOnApp.getCell(newRow, newCol).getTextColor().equals(enemy)) {
                    Manager.changesOnApp.setCellBG(newRow, newCol, Color.RED);
                    canContinue = false;
                    break;
                }
                newRow++;
                iColPointer++;
            }
            if (canContinue) {

                if (iCol>5){
                    newRow--;
                }
                iColPointer= 6;
                while (iColPointer <= 11 && newRow <= 11) {
                    newCol = getNewCol(iColPointer);
                    if (Manager.changesOnApp.getCell(newRow, newCol) == null
                            || Manager.changesOnApp.getCell(newRow, newCol).getText().equals("") ||
                            Manager.changesOnApp.getCell(newRow, newCol).getText() == null) {
                        Manager.changesOnApp.setCellBG(newRow, newCol, Color.GREEN);

                    } else if (!(newRow == row && iColPointer == iCol) && Manager.changesOnApp.getCell(newRow, newCol).getTextColor().equals(Manager.changesOnApp.turn)) {
                        if (check2Called){
                            Manager.changesOnApp.setCellBG(newRow, newCol, Color.GREEN);
                        }
                        break;
                    } else if (Manager.changesOnApp.getCell(newRow, newCol).getTextColor().equals(enemy)) {
                        Manager.changesOnApp.setCellBG(newRow, newCol, Color.RED);
                        break;
                    }
                    iColPointer++;
                }
            }
            // downleft
            iColPointer = iCol - 1;
            newRow = row - 1;
            while (newRow - iColPointer <= 5 && iColPointer > 0 && newRow > 0) {
                newCol = getNewCol(iColPointer);
                if (Manager.changesOnApp.getCell(newRow, newCol) == null
                        || Manager.changesOnApp.getCell(newRow, newCol).getText().equals("") ||
                        Manager.changesOnApp.getCell(newRow, newCol).getText() == null) {
                    Manager.changesOnApp.setCellBG(newRow, newCol, Color.GREEN);

                } else if (!(newRow == row && iColPointer == iCol) && Manager.changesOnApp.getCell(newRow, newCol).getTextColor().equals(Manager.changesOnApp.turn)) {
                    if (check2Called){
                        Manager.changesOnApp.setCellBG(newRow, newCol, Color.GREEN);
                    }
                    break;
                } else if (Manager.changesOnApp.getCell(newRow, newCol).getTextColor().equals(enemy)) {
                    Manager.changesOnApp.setCellBG(newRow, newCol, Color.RED);

                    break;
                }
                iColPointer--;
                newRow--;
            }
        }
        else{
            //from f to upRight
            newRow = row;
            iColPointer =iCol + 1;
            while (iColPointer<=11){
                newCol = getNewCol(iColPointer);
                if(Manager.changesOnApp.getCell(newRow, newCol)==null
                        || Manager.changesOnApp.getCell(newRow, newCol).getText().equals("") ||
                        Manager.changesOnApp.getCell(newRow, newCol).getText()== null){
                    Manager.changesOnApp.setCellBG(newRow, newCol, Color.GREEN);
                }
                else if (!(newRow == row && iColPointer == iCol) && Manager.changesOnApp.getCell(newRow, newCol).getTextColor().equals(Manager.changesOnApp.turn) ){
                    if (check2Called){
                        Manager.changesOnApp.setCellBG(newRow, newCol, Color.GREEN);
                    }
                    break;
                }else if(Manager.changesOnApp.getCell(newRow, newCol).getTextColor().equals(enemy)){
                    Manager.changesOnApp.setCellBG(newRow, newCol, Color.RED);
                    break;
                }
                iColPointer++;
            }
            boolean canContinue = true;
            iColPointer = iColPointer -1;
            while (iColPointer>6){
                newCol = getNewCol(iColPointer);
                if(Manager.changesOnApp.getCell(newRow, newCol)==null
                        || Manager.changesOnApp.getCell(newRow, newCol).getText().equals("") ||
                        Manager.changesOnApp.getCell(newRow, newCol).getText()== null){
                    Manager.changesOnApp.setCellBG(newRow, newCol, Color.GREEN);
                }
                else if (!(newRow == row && iColPointer == iCol) && Manager.changesOnApp.getCell(newRow, newCol).getTextColor().equals(Manager.changesOnApp.turn) ){
                    canContinue = false;
                    if (check2Called){
                        Manager.changesOnApp.setCellBG(newRow, newCol, Color.GREEN);
                    }
                    break;
                }else if(Manager.changesOnApp.getCell(newRow, newCol).getTextColor().equals(enemy)){
                    canContinue = false;
                    Manager.changesOnApp.setCellBG(newRow, newCol, Color.RED);
                    break;
                }
                iColPointer--;
            }
            //from f to downLeft
            if (canContinue) {
                iColPointer = 6;
                while (iColPointer > 0 && newRow > 0) {
                    newCol = getNewCol(iColPointer);
                    if (Manager.changesOnApp.getCell(newRow, newCol) == null
                            || Manager.changesOnApp.getCell(newRow, newCol).getText().equals("") ||
                            Manager.changesOnApp.getCell(newRow, newCol).getText() == null) {
                        Manager.changesOnApp.setCellBG(newRow, newCol, Color.GREEN);

                    } else if (!(newRow == row && iColPointer == iCol) && Manager.changesOnApp.getCell(newRow, newCol).getTextColor().equals(Manager.changesOnApp.turn)) {
                        if (check2Called){
                            Manager.changesOnApp.setCellBG(newRow, newCol, Color.GREEN);
                        }
                        break;
                    } else if (Manager.changesOnApp.getCell(newRow, newCol).getTextColor().equals(enemy)) {
                        Manager.changesOnApp.setCellBG(newRow, newCol, Color.RED);

                        break;
                    }
                    newRow--;
                    iColPointer--;
                }
            }

            //from f to downRight (check)
            newRow = row -1;
            iColPointer = iCol +1;
            while (iColPointer<=11 && newRow>=1){
                newCol = getNewCol(iColPointer);
                if(Manager.changesOnApp.getCell(newRow, newCol)==null
                        || Manager.changesOnApp.getCell(newRow, newCol).getText().equals("") ||
                        Manager.changesOnApp.getCell(newRow, newCol).getText()== null){
                    Manager.changesOnApp.setCellBG(newRow, newCol, Color.GREEN);
                }
                else if (!(newRow == row && iColPointer == iCol) && Manager.changesOnApp.getCell(newRow, newCol).getTextColor().equals(Manager.changesOnApp.turn) ){
                    if (check2Called){
                        Manager.changesOnApp.setCellBG(newRow, newCol, Color.GREEN);
                    }
                    break;
                }else if(Manager.changesOnApp.getCell(newRow, newCol).getTextColor().equals(enemy)){
                    Manager.changesOnApp.setCellBG(newRow, newCol, Color.RED);
                    break;
                }
                iColPointer++;
                newRow--;
            }
            newRow = row + 1;
            iColPointer = iCol -1;
            canContinue = true;
            while (iColPointer>6 && newRow<=11){
                newCol = getNewCol(iColPointer);
                if(Manager.changesOnApp.getCell(newRow, newCol)==null
                        || Manager.changesOnApp.getCell(newRow, newCol).getText().equals("") ||
                        Manager.changesOnApp.getCell(newRow, newCol).getText()== null){
                    Manager.changesOnApp.setCellBG(newRow, newCol, Color.GREEN);
                }
                else if (!(newRow == row && iColPointer == iCol) && Manager.changesOnApp.getCell(newRow, newCol).getTextColor().equals(Manager.changesOnApp.turn) ){
                    canContinue = false;
                    if (check2Called){
                        Manager.changesOnApp.setCellBG(newRow, newCol, Color.GREEN);
                    }
                    break;
                }else if(Manager.changesOnApp.getCell(newRow, newCol).getTextColor().equals(enemy)){
                    Manager.changesOnApp.setCellBG(newRow, newCol, Color.RED);
                    canContinue = false;
                    break;
                }
                iColPointer--;
                newRow++;
            }
            //from f to upLeft (check)
            if (canContinue){
                iColPointer = 6;
                while (iColPointer>=1 && newRow<=11){
                    newCol = getNewCol(iColPointer);
                    if(Manager.changesOnApp.getCell(newRow, newCol)==null
                            || Manager.changesOnApp.getCell(newRow, newCol).getText().equals("") ||
                            Manager.changesOnApp.getCell(newRow, newCol).getText()== null){
                        Manager.changesOnApp.setCellBG(newRow, newCol, Color.GREEN);

                    }
                    else if (!(newRow == row && iColPointer == iCol) && Manager.changesOnApp.getCell(newRow, newCol).getTextColor().equals(Manager.changesOnApp.turn) ){
                        if (check2Called){
                            Manager.changesOnApp.setCellBG(newRow, newCol, Color.GREEN);
                        }
                        break;
                    }else if(Manager.changesOnApp.getCell(newRow, newCol).getTextColor().equals(enemy)){
                        Manager.changesOnApp.setCellBG(newRow, newCol, Color.RED);

                        break;
                    }
                    iColPointer--;

                }
            }
        }

    }

    public Rook(int row, char col, String name, Color BGColor, Color textColor, Color enemy) {
        super(row, col, name, BGColor, textColor, enemy);
    }
}
