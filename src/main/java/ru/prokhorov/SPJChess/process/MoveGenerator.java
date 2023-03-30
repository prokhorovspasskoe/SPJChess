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

        if(color == FigureColor.WHITE){
            for (int i = 0; i < board.getListWhite().size(); i++) {
                figure = board.getListWhite().get(i);
                if(figure.getName() == FigureName.PAWN){
                   moves.addAll(getPawnMoves(figure, board));
                }
            }
        }

        return moves;
    }

    // Получить ходы пешками
    private List<Move> getPawnMoves(Figure figure, Board board) {

        List<Move> moves = new ArrayList<>();
        WriterGame writerGame = new WriterGame();
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
            writerGame.setMove(move);
            moves.add(move);
        }

        if(figure.getPosition() >= start && figure.getPosition() <= end){
            if(checkField(figure.getPosition() + (offset_8 * 2), board) == null){
                move = new Move(figure.getName(), figure.getColor(), figure.getPosition(),
                        figure.getPosition() + offset_8);
                writerGame.setMove(move);
                moves.add(move);
            }
        }

        // На соседней клетке по диагонали вражеская фигура
        if(checkField(figure.getPosition() + offset_7, board) != null &&
                checkField(figure.getPosition() + offset_7, board).getColor() != figure.getColor() &&
                edgePawn(offset_7, figure.getPosition())){
            move = new Move(figure.getName(), figure.getColor(), figure.getPosition(),
                    figure.getPosition() + offset_7);
            writerGame.setMove(move);
            moves.add(move);
        }

        if(checkField(figure.getPosition() + offset_9, board) != null &&
                checkField(figure.getPosition() + offset_9, board).getColor() != figure.getColor() &&
                edgePawn(offset_9, figure.getPosition())){
            move = new Move(figure.getName(), figure.getColor(), figure.getPosition(),
                    figure.getPosition() + offset_9);
            writerGame.setMove(move);
            moves.add(move);
        }

        if(figure.getPosition() >= beginDoPosition && figure.getPosition() <= endDoPosition &&
                checkField(figure.getPosition() + offset_7, board) != null){

            takingOnThePass(figure, moves, writerGame, offset_7, interference, startOpp, endOpp, startTargetPos, endTargetPos);
        }

        if(figure.getPosition() >= beginDoPosition && figure.getPosition() <= endDoPosition &&
                checkField(figure.getPosition() + offset_9, board) != null){

           takingOnThePass(figure, moves, writerGame, offset_9, interference, startOpp, endOpp, startTargetPos, endTargetPos);
        }

        return moves;
    }

    private void takingOnThePass(Figure figure, List<Move> moves, WriterGame writerGame, int offset, int interference,
                                 int startOpp, int endOpp, int startTargetPos, int endTargetPos) {
        Move move;
        int len = writerGame.getFormRecording().size();

        if(writerGame.getMove(len).getFigureName() == FigureName.PAWN &&
                writerGame.getMove(len).getStartPosition() >= startOpp &&
                writerGame.getMove(len).getStartPosition() != figure.getPosition() + interference &&
                writerGame.getMove(len).getStartPosition() <= endOpp &&
                writerGame.getMove(len).getTargetPosition() >= startTargetPos &&
                writerGame.getMove(len).getTargetPosition() <= endTargetPos){
            move = new Move(figure.getName(), figure.getColor(), figure.getPosition(),
                    figure.getPosition() + offset);
            writerGame.setMove(move);
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
