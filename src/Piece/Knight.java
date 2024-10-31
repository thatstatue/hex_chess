package Piece;

import java.awt.*;

import static Piece.Manager.*;

public class Knight extends Piece {


    public Knight(int row, char col, String name, Color BGColor, Color textColor, Color enemy) {
        super(row, col, name, BGColor, textColor, enemy);
    }

    @Override
    public void showValidMoves() {
        int newRow;
        char newCol;

        int rowLLD =0, rowLLU =0;
        int rowLDD =0 , rowLDU = 0;
        int rowLUD =0 , rowLUU = 0;
        int iColLL = iCol - 3, iColLUD_DU =iCol -2, iColLUU_DD = iCol - 1;
        if (iCol <= 6){
            rowLLU = row -1;
            rowLLD = row - 2;
            rowLDD = row - 3;
            rowLDU = row - 3;
            rowLUD =  row + 1;
            rowLUU = row + 2;
        }else if (iCol == 7){
            rowLLU = row;
            rowLLD = row - 1;
            rowLDD = row - 2;
            rowLDU = row - 2;
            rowLUD =  row + 2;
            rowLUU = row + 3;
        }else{
            if (iCol == 8) {
                rowLLU = row + 1;
                rowLLD = row;
            }else{
                rowLLU = row + 2;
                rowLLD = row + 1;
            }
            rowLDD = row - 2;
            rowLDU = row - 1;
            rowLUD =  row + 3;
            rowLUU = row + 3;
        }
        int[] rows = new  int[]{ rowLLD, rowLLU, rowLDD , rowLDU , rowLUD, rowLUU};
        int[] iCols = new int[]{ iColLL,  iColLL,
                iColLUU_DD, iColLUD_DU,
                iColLUD_DU, iColLUU_DD};
        for (int i = 0; i < 6; i++){
            if (rows[i]>=1 && rows[i]<=11 && iCols[i]>=1 && iCols[i]<= 11 ){
                newRow = rows[i];
                newCol = getNewCol(iCols[i]);
                if (changesOnApp.getCell(newRow, newCol) == null
                        || changesOnApp.getCell(newRow, newCol).getText().equals("") ||
                        changesOnApp.getCell(newRow, newCol).getText() == null) {
                    changesOnApp.setCellBG(newRow, newCol, Color.GREEN);
                } else if (changesOnApp.getCell(newRow, newCol).getTextColor().equals(enemy)) {
                    changesOnApp.setCellBG(newRow, newCol, Color.RED);
                }else{
                    if (check2Called){
                        changesOnApp.setCellBG(newRow, newCol, Color.GREEN);
                    }
                }
            }
        }


        int rowRRD =0, rowRRU =0;
        int rowRDD =0 , rowRDU = 0;
        int rowRUD =0 , rowRUU = 0;
        int iColRR = iCol + 3, iColRUD_DU =iCol + 2, iColRUU_DD = iCol + 1;
        if (iCol <= 4){
            if(iCol ==4) {
                rowRRU = row + 1;
                rowRRD = row;
            }else{
                rowRRU = row + 2;
                rowRRD = row + 1;
            }
            rowRDD = row - 2;
            rowRDU = row - 1;
            rowRUD =  row + 3;
            rowRUU = row + 3;
        }else if (iCol == 5){
            rowRRU = row;
            rowRRD = row - 1;
            rowRDD = row - 2;
            rowRDU = row - 2;
            rowRUD =  row + 2;
            rowRUU = row + 3;
        }else{
            rowRRU = row -1;
            rowRRD = row - 2;
            rowRDD = row - 3;
            rowRDU = row - 3;
            rowRUD =  row + 1;
            rowRUU = row + 2;
        }
        rows = new  int[]{ rowRRD, rowRRU, rowRDD , rowRDU , rowRUD, rowRUU};
        iCols = new int[]{ iColRR,  iColRR,
                iColRUU_DD, iColRUD_DU,
                iColRUD_DU, iColRUU_DD};
        for (int i = 0; i < 6; i++){
            if (rows[i]>=1 && rows[i]<=11 && iCols[i]>=1 && iCols[i]<= 11 ){
                newRow = rows[i];
                newCol = getNewCol(iCols[i]);
                if (changesOnApp.getCell(newRow, newCol) == null
                        || changesOnApp.getCell(newRow, newCol).getText().equals("") ||
                        changesOnApp.getCell(newRow, newCol).getText() == null) {
                    changesOnApp.setCellBG(newRow, newCol, Color.GREEN);
                } else if (changesOnApp.getCell(newRow, newCol).getTextColor().equals(enemy)) {
                    changesOnApp.setCellBG(newRow, newCol, Color.RED);
                }else{
                    if (check2Called){
                        changesOnApp.setCellBG(newRow, newCol, Color.GREEN);
                    }
                }
            }
        }
    }
}