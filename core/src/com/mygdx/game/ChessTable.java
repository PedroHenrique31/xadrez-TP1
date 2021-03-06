package com.mygdx.game;


import com.mygdx.pieces.Piece;
import com.mygdx.web.Web;

public class ChessTable extends ChessBoard {
    public boolean EndOfTheGame = false;
    public boolean onLine;
    public Player whoseTurn;
    public Player winner;
    public boolean needPromotion = false;
    public Piece pawnToPromote;
    public Player Me;
    public Player Enemy;
    public int myNumber;
    public ChessLogic chessLogic = new ChessLogic();

    public ChessTable(int myNumber, boolean onLine) {
        this.onLine = onLine;
        this.myNumber = myNumber;
        if (myNumber == 1) {
            Me = new Player(true, false);
            Enemy = new Player(false, true);
            Me.setTurn(true);
            Enemy.setTurn(false);
            whoseTurn = Me;
        }else if (myNumber == 2) {
            Me = new Player(false, false);
            Enemy = new Player(true, true);
            Me.setTurn(false);
            Enemy.setTurn(true);
            whoseTurn=Enemy;
        }

        Me.setEnemy(Enemy);
        Me.setName("Você");
        Enemy.setEnemy(Me);
        Enemy.setName("Seu adversario");

        super.placePieces(Me.getPieces());
        super.placePieces(Enemy.getPieces());


    }

    public boolean requestMove(Player p, Position source, Position dest, boolean me) {

        if (!chessLogic.preMoveAnalisys(this, p, source, dest)) {
            return false;
        }

        this.move(source, dest);
        if( me && onLine ) Web.sendMove(myNumber, source, dest);

        if (chessLogic.isPawnPromotion(this.getSquareByPosition(dest).getPiece())) {
            pawnToPromote = this.getSquareByPosition(dest).getPiece();
            needPromotion = true;
            return true;
        }

        chessLogic.postMoveAnalisys(p, this );
        changeTurn();

        return true;
    }


    public void procceedPromotion(int choice) {
        // manda na WEB a escolha
        pawnToPromote.promotePawn(choice, this);
        needPromotion = false;
        chessLogic.postMoveAnalisys(pawnToPromote.getPlayer(), this);
        changeTurn();
    }


    public void changeTurn() {
        if(whoseTurn==Me)whoseTurn=Enemy;
        else if(whoseTurn==Enemy)whoseTurn=Me;
        Me.setTurn(!Me.getTurn());
        Enemy.setTurn(!Enemy.getTurn());
    }

    public boolean verifyEnemyMove() {
        int pl, expected;

        if (Me.getTurn()) return false;

        if (myNumber == 1) expected = 2;
        else expected = 1;

        String lastMove = Web.getMove();
        pl = (int) lastMove.charAt(0) - 48;

        if (pl != expected) return false;

        Position source = new Position((int) lastMove.charAt(1) - 65, (int) lastMove.charAt(2) - 49);
        Position dest = new Position((int) lastMove.charAt(3) - 65, (int) lastMove.charAt(4) - 49);

        source.invert();
        dest.invert();

        if (requestMove(Enemy, source, dest, false)){

            if (this.needPromotion) {
                // funcão web que recebe a promoção do adversario
                // chama this.proceedPromotion(choice) com a escolha do usuario
            }
            return true;
        }

        return false;
    }
}


