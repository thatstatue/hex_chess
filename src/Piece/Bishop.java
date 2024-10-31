package Piece;
import java.awt.*;

import static Piece.Manager.check2Called;

public class Bishop extends Piece{
    
    public Bishop(int row, char col, String name, Color BGColor, Color textColor, Color enemy) {
        super(row, col, name, BGColor, textColor, enemy);
    }
    @Override
    public void showValidMoves() {
        int iColPointer = iCol;
        int newRow = row;
        char newCol = col;
        if (iColPointer <= 6) {
            //upleft to downright
            newRow = row + 1;
            iColPointer = iCol - 1;
            while (newRow <= 11 && iColPointer >= 1 && (newRow - iColPointer <= 5)) {
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
                newRow++;
                iColPointer--;
            }
            newRow = row - 1;
            iColPointer = iCol + 1;
            boolean canContinue = true;
            while (newRow >= 1 && iColPointer <= 6) {
                newCol = getNewCol(iColPointer);
                if (Manager.changesOnApp.getCell(newRow, newCol) == null ||
                        Manager.changesOnApp.getCell(newRow, newCol).getText().equals("") ||
                        Manager.changesOnApp.getCell(newRow, newCol).getText() == null) {
                    Manager.changesOnApp.setCellBG(newRow, newCol, Color.GREEN);

                } else if (!(newRow == row && iColPointer == iCol) && Manager.changesOnApp.getCell(newRow, newCol).getTextColor().equals(Manager.changesOnApp.turn)) {
                    canContinue = false;
                    if (check2Called){
                        Manager.changesOnApp.setCellBG(newRow, newCol, Color.GREEN);
                    }
                    break;
                } else if (Manager.changesOnApp.getCell(newRow, newCol).getTextColor().equals(enemy)) {
                    canContinue = false;
                    Manager.changesOnApp.setCellBG(newRow, newCol, Color.RED);
                    break;
                }
                newRow--;
                iColPointer++;
            }
            if (canContinue){
                iColPointer--;
                newRow++;

                while (newRow >= 1 && iColPointer <= 11) {
                    newCol = getNewCol(iColPointer);
                    if (Manager.changesOnApp.getCell(newRow, newCol) == null ||
                            Manager.changesOnApp.getCell(newRow, newCol).getText().equals("") ||
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
                    newRow-= 2;
                    iColPointer++;
                }

            }
            // downleft to upright
            newRow = row - 2;
            iColPointer = iCol - 1;
            while (newRow >= 1 && iColPointer >= 1) {
                newCol = getNewCol(iColPointer);
                if (Manager.changesOnApp.getCell(newRow, newCol) == null ||
                        Manager.changesOnApp.getCell(newRow, newCol).getText().equals("") ||
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
                newRow-= 2;
                iColPointer--;
            }
            newRow = row + 2;
            iColPointer = iCol + 1;
            canContinue = true;
            while (newRow <= 11 && iColPointer < 7) {
                newCol = getNewCol(iColPointer);
                if (Manager.changesOnApp.getCell(newRow, newCol) == null ||
                        Manager.changesOnApp.getCell(newRow, newCol).getText().equals("") ||
                        Manager.changesOnApp.getCell(newRow, newCol).getText() == null) {
                    Manager.changesOnApp.setCellBG(newRow, newCol, Color.GREEN);
                } else if (!(newRow == row && iColPointer == iCol) && Manager.changesOnApp.getCell(newRow, newCol).getTextColor().equals(Manager.changesOnApp.turn)) {
                    canContinue = false;
                    if (check2Called){
                        Manager.changesOnApp.setCellBG(newRow, newCol, Color.GREEN);
                    }
                    break;
                } else if (Manager.changesOnApp.getCell(newRow, newCol).getTextColor().equals(enemy)) {
                    Manager.changesOnApp.setCellBG(newRow, newCol, Color.RED);
                    canContinue = false;
                    break;
                }
                newRow+= 2;
                iColPointer++;
            }
            if (canContinue) {
                iColPointer--;
                newRow -= 2;
                while (newRow <= 11 && iColPointer <= 11) {
                    newCol = getNewCol(iColPointer);
                    if (Manager.changesOnApp.getCell(newRow, newCol) == null ||
                            Manager.changesOnApp.getCell(newRow, newCol).getText().equals("") ||
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
                    newRow++;
                    iColPointer++;
                }
            }
            //left
            iColPointer = iCol - 2;
            newRow = row - 1;
            while (newRow >= 1 && iColPointer>=1){
                newCol = getNewCol(iColPointer);
                if (Manager.changesOnApp.getCell(newRow, newCol) == null ||
                        Manager.changesOnApp.getCell(newRow, newCol).getText().equals("") ||
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
                iColPointer -= 2;
            }
            //right
            iColPointer = iCol;
            newRow = row;
            canContinue = true;
            while (newRow <= 11 && iColPointer<=6){
                newCol = getNewCol(iColPointer);
                if (Manager.changesOnApp.getCell(newRow, newCol) == null ||
                        Manager.changesOnApp.getCell(newRow, newCol).getText().equals("") ||
                        Manager.changesOnApp.getCell(newRow, newCol).getText() == null) {
                    Manager.changesOnApp.setCellBG(newRow, newCol, Color.GREEN);
                } else if (!(newRow == row && iColPointer == iCol) &&
                        Manager.changesOnApp.getCell(newRow, newCol).getTextColor().equals(Manager.changesOnApp.turn)) {
                    canContinue = false;
                    if (check2Called){
                        Manager.changesOnApp.setCellBG(newRow, newCol, Color.GREEN);
                    }
                    break;
                } else if (Manager.changesOnApp.getCell(newRow, newCol).getTextColor().equals(enemy)) {
                    Manager.changesOnApp.setCellBG(newRow, newCol, Color.RED);
                    canContinue = false;
                    break;
                }
                newRow++;
                iColPointer += 2;
            }
            if (canContinue){
                newRow --;
                if (iCol== 2 || iCol== 4 || iCol == 6) {
                    iColPointer -= 2;
                }
                while (newRow >= 1 && iColPointer<=11){
                    newCol = getNewCol(iColPointer);
                    if (Manager.changesOnApp.getCell(newRow, newCol) == null ||
                            Manager.changesOnApp.getCell(newRow, newCol).getText().equals("") ||
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
                    newRow--;
                    iColPointer += 2;
                }
            }
            if ((iCol *2 == row && iCol<6 )|| ((12 - iCol)*2 == row && iCol>6)){
                Manager.changesOnApp.setCellBG(11, 'f', null);
            }

        }else{
            //upright to downleft
            newRow = row;
            iColPointer = iCol;
            while (newRow <= 11 && iColPointer <= 11 && (newRow - iColPointer <= 17)) {
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
                newRow++;
                iColPointer++;
            }
            newRow = row;
            iColPointer = iCol;
            boolean canContinue = true;
            while (newRow >= 1 && iColPointer >= 6) {
                newCol = getNewCol(iColPointer);
                if (Manager.changesOnApp.getCell(newRow, newCol) == null ||
                        Manager.changesOnApp.getCell(newRow, newCol).getText().equals("") ||
                        Manager.changesOnApp.getCell(newRow, newCol).getText() == null) {
                    Manager.changesOnApp.setCellBG(newRow, newCol, Color.GREEN);

                } else if (!(newRow == row && iColPointer == iCol) && Manager.changesOnApp.getCell(newRow, newCol).getTextColor().equals(Manager.changesOnApp.turn)) {
                    canContinue = false;
                    if (check2Called){
                        Manager.changesOnApp.setCellBG(newRow, newCol, Color.GREEN);
                    }
                    break;
                } else if (Manager.changesOnApp.getCell(newRow, newCol).getTextColor().equals(enemy)) {
                    canContinue = false;
                    Manager.changesOnApp.setCellBG(newRow, newCol, Color.RED);
                    break;
                }
                newRow--;
                iColPointer--;
            }
            if (canContinue){
                iColPointer++;
                newRow++;
                while (newRow >= 1 && iColPointer >= 1) {
                    newCol = getNewCol(iColPointer);
                    if (Manager.changesOnApp.getCell(newRow, newCol) == null ||
                            Manager.changesOnApp.getCell(newRow, newCol).getText().equals("") ||
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
                    newRow-= 2;
                    iColPointer--;
                }
            }
            // downright to upleft
            newRow = row;
            iColPointer = iCol;
            while (newRow >= 1 && iColPointer <= 11) {
                newCol = getNewCol(iColPointer);
                if (Manager.changesOnApp.getCell(newRow, newCol) == null ||
                        Manager.changesOnApp.getCell(newRow, newCol).getText().equals("") ||
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
                newRow-= 2;
                iColPointer++;
            }
            newRow = row;
            iColPointer = iCol;
            canContinue = true;
            while (newRow <= 11 && iColPointer >= 6) {
                newCol = getNewCol(iColPointer);
                if (Manager.changesOnApp.getCell(newRow, newCol) == null ||
                        Manager.changesOnApp.getCell(newRow, newCol).getText().equals("") ||
                        Manager.changesOnApp.getCell(newRow, newCol).getText() == null) {
                    Manager.changesOnApp.setCellBG(newRow, newCol, Color.GREEN);
                } else if (!(newRow == row && iColPointer == iCol) &&
                        Manager.changesOnApp.getCell(newRow, newCol).getTextColor().equals(Manager.changesOnApp.turn)) {
                    canContinue = false;
                    if (check2Called){
                        Manager.changesOnApp.setCellBG(newRow, newCol, Color.GREEN);
                    }
                    break;
                } else if (Manager.changesOnApp.getCell(newRow, newCol).getTextColor().equals(enemy)) {
                    Manager.changesOnApp.setCellBG(newRow, newCol, Color.RED);
                    canContinue = false;
                    break;
                }
                newRow+= 2;
                iColPointer--;
            }
            if (canContinue) {
                iColPointer++;
                newRow -= 2;
                while (newRow <= 11 && iColPointer >= 1) {
                    newCol = getNewCol(iColPointer);
                    if (Manager.changesOnApp.getCell(newRow, newCol) == null ||
                            Manager.changesOnApp.getCell(newRow, newCol).getText().equals("") ||
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
                    newRow++;
                    iColPointer--;
                }
            }
            //right
            iColPointer = iCol ;
            newRow = row ;
            while (newRow >= 1 && iColPointer <= 11){
                newCol = getNewCol(iColPointer);
                if (Manager.changesOnApp.getCell(newRow, newCol) == null ||
                        Manager.changesOnApp.getCell(newRow, newCol).getText().equals("") ||
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
                newRow--;
                iColPointer += 2;
            }
            //left
            iColPointer = iCol;
            newRow = row;
            canContinue = true;
            while (newRow >= 1 && iColPointer>=6){
                newCol = getNewCol(iColPointer);
                if (Manager.changesOnApp.getCell(newRow, newCol) == null ||
                        Manager.changesOnApp.getCell(newRow, newCol).getText().equals("") ||
                        Manager.changesOnApp.getCell(newRow, newCol).getText() == null) {
                    Manager.changesOnApp.setCellBG(newRow, newCol, Color.GREEN);
                } else if (!(newRow == row && iColPointer == iCol) &&
                        Manager.changesOnApp.getCell(newRow, newCol).getTextColor().equals(Manager.changesOnApp.turn)) {
                    canContinue = false;
                    if (check2Called){
                        Manager.changesOnApp.setCellBG(newRow, newCol, Color.GREEN);
                    }
                    break;
                } else if (Manager.changesOnApp.getCell(newRow, newCol).getTextColor().equals(enemy)) {
                    Manager.changesOnApp.setCellBG(newRow, newCol, Color.RED);
                    canContinue = false;
                    break;
                }
                newRow++;
                iColPointer -= 2;
            }
            if (canContinue){
                newRow --;
                if (iCol == 10 || iCol == 8) {
                    iColPointer += 2;
                }
                while (newRow >= 1 && iColPointer>=1){
                    newCol = getNewCol(iColPointer);
                    if (Manager.changesOnApp.getCell(newRow, newCol) == null ||
                            Manager.changesOnApp.getCell(newRow, newCol).getText().equals("") ||
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
                    newRow--;
                    iColPointer -= 2;
                }
            }
            if ((iCol *2 == row && iCol<6 )|| ((12 - iCol)*2 == row && iCol>6)){
                Manager.changesOnApp.setCellBG(11, 'f', null);
            }
        }
    }
}
