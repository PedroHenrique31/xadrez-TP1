package com.mygdx.pieces;

import com.mygdx.game.ChessBoard;
import com.mygdx.game.Player;
import com.mygdx.game.Position;
import com.mygdx.game.PositionList;

/**
 * Created by felipecosta on 10/2/17.
 */
public class King extends Piece {

    private boolean hasMoved=false;
    private boolean hasMadeCastling=false;

    public King(Player p, char X, int Y){
        super(PieceCode.KNG, X, Y);
        super.player = p;
        super.numPlayer = p.getNumber();
    }

    public void moved() { this.hasMoved = true; }

    public PositionList canGo(ChessBoard cb) {

        PositionList list = new PositionList();
        Position p = new Position(this.getPosition());

        p.moveUpRight();
        if (p.isValidPosition()) {
            if (cb.byPosition(p).isEmpty()) {
                list.add(new Position(p));
            }else{
                if(isEnemy(cb.byPosition(p).getPiece())){
                    list.add(new Position(p));
                }
            }
        }

        p.setPosition(this.getPosition());

        p.moveUpLeft();
        if (p.isValidPosition()) {
            if (cb.byPosition(p).isEmpty()) {
                list.add(new Position(p));
            }else{
                if(isEnemy(cb.byPosition(p).getPiece())){
                    list.add(new Position(p));
                }
            }
        }

        p.setPosition(this.getPosition());
        p.moveDownRight();
        if (p.isValidPosition()) {
            if (cb.byPosition(p).isEmpty()) {
                list.add(new Position(p));
            }else{
                if(isEnemy(cb.byPosition(p).getPiece())){
                    list.add(new Position(p));
                }
            }
        }

        p.setPosition(this.getPosition());
        p.moveDownLeft();
        if (p.isValidPosition()) {
            if (cb.byPosition(p).isEmpty()) {
                list.add(new Position(p));
            }else{
                if(isEnemy(cb.byPosition(p).getPiece())){
                    list.add(new Position(p));
                }
            }
        }

        p.setPosition(this.getPosition());
        p.moveUp();
        if (p.isValidPosition()) {
            if (cb.byPosition(p).isEmpty()) {
                list.add(new Position(p));
            }else{
                if(isEnemy(cb.byPosition(p).getPiece())){
                    list.add(new Position(p));
                }

            }
        }

        p.setPosition(this.getPosition());
        p.moveDown();
        if (p.isValidPosition()) {
            if (cb.byPosition(p).isEmpty()) {
                list.add(new Position(p));
            }else{
                if(isEnemy(cb.byPosition(p).getPiece())){
                    list.add(new Position(p));
                }
            }
        }

        p.setPosition(this.getPosition());
        p.moveRight();
        if (p.isValidPosition()) {
            if (cb.byPosition(p).isEmpty()) {
                list.add(new Position(p));
            }else{
                if(isEnemy(cb.byPosition(p).getPiece())){
                    list.add(new Position(p));
                }
            }
        }

        p.setPosition(this.getPosition());
        p.moveLeft();
        if (p.isValidPosition()) {
            if (cb.byPosition(p).isEmpty()) {
                list.add(new Position(p));
            }else{
                if(isEnemy(cb.byPosition(p).getPiece())){
                    list.add(new Position(p));
                }

            }
        }


        return list;
    }
}
