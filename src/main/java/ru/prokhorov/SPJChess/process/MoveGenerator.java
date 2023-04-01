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
    public List<Move> getAllMoves(Board board, FigureColor turn){

        List<Move> moves = new ArrayList<>();
        List<Figure> figures;

        if(turn == FigureColor.WHITE){
            figures = board.getListWhite();
        }else{
            figures = board.getListBlack();
        }

        for (Figure figure : figures) {
            if (figure.getName() == FigureName.PAWN) {
                moves.addAll(getPawnMoves(figure, board));
            }

            if(figure.getName() == FigureName.KNIGHT){
                moves.addAll(getKnightMoves(figure, board));
            }

            if(figure.getName() == FigureName.BISHOP){
                moves.addAll(getBishopMoves(figure, board));
            }
        }

        return moves;
    }

    private List<Move> getBishopMoves(Figure figure, Board board) {
        List<Move> bishopMovesList = new ArrayList<>();

        int loop = figure.getOffset().length;
        int toField = 0;

        for (int i = 0; i < loop; i++) {
            for (int j = 0; j < figure.getOffset()[i]; j++) {

                if(j == 0) toField = figure.getPosition() + figure.getOffset()[i];
                if(j == 1) toField = figure.getPosition() + (figure.getOffset()[i] * 2);
                if(j > 1) toField = figure.getPosition() + (figure.getOffset()[i] * j);

                if(checkField(toField, board) != null &&
                        checkField(toField, board).getColor() == figure.getColor()) break;

                if(checkField(toField, board) != null &&
                        checkField(toField, board).getColor() != figure.getColor()){
                    Move move = new Move(figure.getName(), figure.getColor(),
                            figure.getPosition(), figure.getPosition() + toField);
                    bishopMovesList.add(move);
                    break;
                }

                if(checkField(figure.getPosition() + figure.getOffset()[i], board) == null){
                    Move move = new Move(figure.getName(), figure.getColor(),
                            figure.getPosition(), figure.getPosition() + toField);
                    bishopMovesList.add(move);
                }
            }
        }

        return bishopMovesList;
    }

    private List<Move> getKnightMoves(Figure figure, Board board) {
        List<Move> knightMovesList = new ArrayList<>();
        int loop = figure.getOffset().length;
        Move move;

        for (int i = 0; i < loop; i++) {
            if(figure.getPosition() + figure.getOffset()[i] >= 0 &&
                    figure.getPosition() + figure.getOffset()[i] <= 63 &&
                    (checkField(figure.getPosition() + figure.getOffset()[i], board).getColor() != figure.getColor() ||
                            checkField(figure.getPosition() + figure.getOffset()[i], board) == null)){
                move = new Move(figure.getName(), figure.getColor(), figure.getPosition(),
                        figure.getPosition() + figure.getOffset()[i]);
                knightMovesList.add(move);
            }
        }
        return knightMovesList;
    }

    // Получить ходы пешками
    private List<Move> getPawnMoves(Figure figure, Board board) {

        List<Move> moves = new ArrayList<>();
        int offset_8 = 0;
        int offset_7 = 0;
        int offset_9 = 0;
        int start = 0;
        int end = 0;
        int beginDoPosition = 0;
        int endDoPosition = 0;
        int interference = 0;
        int startOpp = 0;
        int endOpp = 0;
        int startTargetPos = 0;
        int endTargetPos = 0;

        // Из начальной позиции белыми
        if(figure.getColor() == FigureColor.WHITE){
            offset_8 = figure.getOffset()[4];
            offset_7 = figure.getOffset()[0];
            offset_9 = figure.getOffset()[2];
            start = 8;
            end = 15;
            beginDoPosition = 32;
            endDoPosition = 39;
            startOpp = 48;
            endOpp = 55;
            startTargetPos = 32;
            endTargetPos = 39;
            interference = 16;
        }

        if(figure.getColor() == FigureColor.BLACK){
            offset_8 = figure.getOffset()[5];
            offset_7 = figure.getOffset()[1];
            offset_9 = figure.getOffset()[3];
            start = 48;
            end = 55;
            beginDoPosition = 24;
            endDoPosition = 31;
            startOpp = 8;
            endOpp = 15;
            startTargetPos = 24;
            endTargetPos = 31;
            interference = -16;
        }

        Move move;

        if(checkField(figure.getPosition() + offset_8, board) == null){
            move = new Move(figure.getName(), figure.getColor(), figure.getPosition(),
                    figure.getPosition() + offset_8);
            moves.add(move);
        }

        if(figure.getPosition() >= start && figure.getPosition() <= end){
            if(checkField(figure.getPosition() + (offset_8 * 2), board) == null){
                move = new Move(figure.getName(), figure.getColor(), figure.getPosition(),
                        figure.getPosition() + offset_8);
                moves.add(move);
            }
        }

        // На соседней клетке по диагонали вражеская фигура
        if(checkField(figure.getPosition() + offset_7, board) != null &&
                checkField(figure.getPosition() + offset_7, board).getColor() != figure.getColor() &&
                edgePawn(offset_7, figure.getPosition())){
            move = new Move(figure.getName(), figure.getColor(), figure.getPosition(),
                    figure.getPosition() + offset_7);
            moves.add(move);
        }

        if(checkField(figure.getPosition() + offset_9, board) != null &&
                checkField(figure.getPosition() + offset_9, board).getColor() != figure.getColor() &&
                edgePawn(offset_9, figure.getPosition())){
            move = new Move(figure.getName(), figure.getColor(), figure.getPosition(),
                    figure.getPosition() + offset_9);
            moves.add(move);
        }

        if(figure.getPosition() >= beginDoPosition && figure.getPosition() <= endDoPosition &&
                checkField(figure.getPosition() + offset_7, board) != null){

            takingOnThePass(figure, moves, board, offset_7, interference, startOpp, endOpp, startTargetPos, endTargetPos);
        }

        if(figure.getPosition() >= beginDoPosition && figure.getPosition() <= endDoPosition &&
                checkField(figure.getPosition() + offset_9, board) != null){

           takingOnThePass(figure, moves, board, offset_9, interference, startOpp, endOpp, startTargetPos, endTargetPos);
        }

        return moves;
    }

    private void takingOnThePass(Figure figure, List<Move> moves, Board board, int offset, int interference,
                                 int startOpp, int endOpp, int startTargetPos, int endTargetPos) {
        Move move;
        int len = board.getFormRecording().size();

        if(board.getRecord(len).getFigureName() == FigureName.PAWN &&
                board.getRecord(len).getStartPosition() >= startOpp &&
                board.getRecord(len).getStartPosition() != figure.getPosition() + interference &&
                board.getRecord(len).getStartPosition() <= endOpp &&
                board.getRecord(len).getTargetPosition() >= startTargetPos &&
                board.getRecord(len).getTargetPosition() <= endTargetPos){
            move = new Move(figure.getName(), figure.getColor(), figure.getPosition(),
                    figure.getPosition() + offset);
            moves.add(move);
        }
    }

    private boolean edgePawn(int offset, int position){
        if((position == 8 || position == 16 || position == 24 || position == 34 ||
        position == 40 || position == 48 || position == 56) && offset == 7) return false;
        if((position == 15 || position == 23 || position == 31 || position == 39 ||
                position == 47 || position == 55) && offset == 9) return false;
        return true;
    }
}
