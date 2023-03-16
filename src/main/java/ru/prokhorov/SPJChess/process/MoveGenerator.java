package ru.prokhorov.SPJChess.process;

import ru.prokhorov.SPJChess.events.Move;
import ru.prokhorov.SPJChess.gameobjects.Board;
import ru.prokhorov.SPJChess.gameobjects.abstracts.Figure;
import ru.prokhorov.SPJChess.gameobjects.enums.FigureColor;
import ru.prokhorov.SPJChess.gameobjects.enums.FigureName;

import java.util.ArrayList;
import java.util.List;

public class MoveGenerator {
    public Figure checkField(int field, Board board){
        for (int i = 0; i < board.getListWhite().size(); i++) {
            if(field == board.getListWhite().get(i).getPosition()) return board.getListWhite().get(i);
        }
        for (int i = 0; i < board.getListBlack().size(); i++) {
            if(field == board.getListBlack().get(i).getPosition()) return board.getListBlack().get(i);
        }
        return null;
    }

    public List<Move> getAllMoves(Board board, FigureColor color){

        Figure figure;
        List<Move> moves = new ArrayList<>();
        Move move = new Move();

        if(color == FigureColor.WHITE){
            for (int i = 0; i < board.getListWhite().size(); i++) {
                figure = board.getListWhite().get(i);
                if(figure.getName() == FigureName.PAWN){
                   moves.addAll(getPawnMoves(FigureColor.WHITE));
                }
            }
        }

        return moves;
    }

    private List<Move> getPawnMoves(FigureColor color) {
        List<Move> moves = new ArrayList<>();
        return moves;
    }
}
