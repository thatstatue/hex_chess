package Piece;


import java.awt.*;

public abstract class Piece {
    String name;
    private final String columnSet = "_abcdefghikl";
    Color textColor, BGColor, enemy;
    int row;
    char col;
    int iCol;
    public abstract void showValidMoves();

    public Piece(int row, char col, String name, Color BGColor, Color textColor ,Color enemy){
        this.name = name;
        this.row= row;
        this.col = col;
        this.BGColor = BGColor;
        this.textColor = textColor;
        iCol = getICol(col);
        this.enemy = enemy;
    }
    private int getICol(char col){
        int iCol = 12;
        for (int i = 1; i<= 11; i++){
            if (columnSet.charAt(i) == col){
                iCol = i;
                break;
            }
        }
        return iCol;
    }
    public char getNewCol(int iCol){
        return columnSet.charAt(iCol);
    }

}
