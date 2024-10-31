package Piece;

import java.awt.*;

public class Queen extends Piece{
    Rook qRook;
    Bishop qBishop;
    public Queen(int row, char col, String name, Color BGColor, Color textColor, Color enemy) {
        super(row, col, name, BGColor, textColor, enemy);

        qBishop = new Bishop(row, col, name, BGColor, textColor, enemy);
        qRook = new Rook(row, col, name, BGColor, textColor, enemy);
    }

    @Override
    public void showValidMoves() {
        qBishop.showValidMoves();
        qRook.showValidMoves();
    }
}
