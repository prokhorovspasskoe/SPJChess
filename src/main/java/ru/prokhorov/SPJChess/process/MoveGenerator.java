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

    //Ходы без учёта шаха
    public List<Move> getAllMoves(Board board, FigureColor color){

        Figure figure;
        List<Move> moves = new ArrayList<>();
        Move move = new Move();

        if(color == FigureColor.WHITE){
            for (int i = 0; i < board.getListWhite().size(); i++) {
                figure = board.getListWhite().get(i);
                if(figure.getName() == FigureName.PAWN){
                   moves.addAll(getPawnMoves(FigureColor.WHITE, figure, board));
                }
            }
        }

        return moves;
    }

    // Получить ходы пешками
    private List<Move> getPawnMoves(FigureColor color, Figure figure, Board board) {

        List<Move> moves = new ArrayList<>();

        int offset_8 = 8;

        // Из начальной позиции белыми
        if(figure.getColor() == FigureColor.WHITE && figure.getPosition() >=9 && figure.getPosition() <= 15){
            // на одну клетку
            if(checkField(figure.getPosition() + offset_8, board) == null){
                Move move = new Move(figure, figure.getPosition() + offset_8);
                moves.add(move);
            }

            // На две клетки
            if(checkField(figure.getPosition() + (offset_8 * 2), board) == null){
                Move move = new Move(figure, figure.getPosition() + offset_8);
                moves.add(move);
            }

            // На соседней клетке по диагонали вражеская фигура
            if(checkField(figure.getPosition() + (offset_8 - 1), board) != null &&
                    checkField(figure.getPosition() + (offset_8 - 1), board).getColor() != figure.getColor()){
                Move move = new Move(figure, figure.getPosition() + offset_8 - 1);
                moves.add(move);

            }else if(checkField(figure.getPosition() + (offset_8 + 1), board) != null &&
                    checkField(figure.getPosition() + (offset_8 + 1), board).getColor() != figure.getColor()){
                Move move = new Move(figure, figure.getPosition() + offset_8 + 1);
                moves.add(move);
            }

            // Взятие на проходе

        }
        return moves;
    }
}
