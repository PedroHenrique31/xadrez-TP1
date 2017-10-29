package com.mygdx.game;

import com.mygdx.pieces.*;

/*
*
* Esta Classe implementa um tabuleiro de Xadrez com seus métodos.
* O Tabuleiro é uma matriz de Squares.
*
* */


public class ChessBoard {
    private Square[][] board = new Square[8][8];
    private Piece lastKilled=null;
    private Position lastSource;
    private Position lastDestination;
    ChessLogic chessLogic = new ChessLogic();
    public ChessBoard(){
        for(int i=0; i<8; i++){
            for(int j=0; j<8; j++){
                board[i][j] = new Square(i,j);
            }
        }
    }

    protected void move(Position source, Position destination){
        if(this.getSquareByPosition(source).isEmpty())
            return;

        if(this.getSquareByPosition(destination).hasPiece()){
            lastKilled = this.getSquareByPosition(destination).getPiece();
            this.getSquareByPosition(destination).getPiece().kill();
        }else{
            lastKilled = null;
        }

        Piece piece = this.getSquareByPosition(source).takePiece();
        this.getSquareByPosition(destination).putPiece(piece);
        this.getSquareByPosition(source).setEmpty();
        piece.setPosition(destination);
        lastSource = source;
        lastDestination = destination;
    }

    protected void unmove(){

        Piece piece = this.getSquareByPosition(this.lastDestination).takePiece();
        this.getSquareByPosition(this.lastSource).putPiece(piece);
        this.getSquareByPosition(this.lastDestination).setEmpty();
        piece.setPosition(this.lastSource);

        if(this.lastKilled!=null){
            this.lastKilled.revive();
            this.getSquareByPosition(this.lastDestination).putPiece(this.lastKilled);
            this.lastKilled=null;
        }
    }

    /*Este método imprime o MENU na linha de comando*/
    public void printMenu(){
        System.out.println(" -------------------------");
        System.out.println("|                         |");
        System.out.println("| (1)Procurar uma Partida |");
        System.out.println("|                         |");
        System.out.println("| (2)Sair                 |");
        System.out.println("|                         |");
        System.out.println(" -------------------------");
        System.out.println();
    }

    /*Este método imprime o tabuleiro na linha de comando num formato "bonitinho"*/
    public void printBoard(){
        for(int j=7; j>-1; j--){
            System.out.println();
            System.out.print(" "+(j+1)+" ");
            for(int i=0; i<8; i++){
                if(board[i][j].isEmpty()){
                    System.out.print("  X   ");
                }else{
                        System.out.print(" " + board[i][j].getPiece().getPieceCode()
                                + board[i][j].getPiece().getNumPlayer()+ " ");
                }
            }
            System.out.println();

        }
        System.out.print("   ");
        for( int i=0; i<8; i++){
            System.out.print("  "+(char)(65 + i) +"   ");
        }
        System.out.println();
    }

    public Square getSquareByPosition(Position pos){
        if(pos.isValidPosition())
            return board[pos.getX()][pos.getY()];
        return null;
    }

    public Square getSquare(int i, int j) {
        return board[i][j];
    }

    public Square[][] getBoard() {
        return board;
    }
}
