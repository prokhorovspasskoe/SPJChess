package ru.prokhorov.SPJChess.process;

import ru.prokhorov.SPJChess.events.Move;
import ru.prokhorov.SPJChess.gameobjects.Board;
import ru.prokhorov.SPJChess.gameobjects.abstracts.Figure;
import ru.prokhorov.SPJChess.gameobjects.enums.FigureColor;
import ru.prokhorov.SPJChess.gameobjects.enums.FigureName;
import ru.prokhorov.SPJChess.process.interfaces.MoveGenerator;
import java.util.ArrayList;
import java.util.List;

public class MoveGeneratorImpl implements MoveGenerator {
    @Override
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
    @Override
    public List<Move> getAllMoves(Board board){

        List<Move> moves = new ArrayList<>();
        List<Figure> figures;

        if(board.getTurn() == FigureColor.WHITE){
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
                moves.addAll(getBishopRookQueenMoves(figure, board));
            }

            if(figure.getName() == FigureName.ROOK){
                moves.addAll(getBishopRookQueenMoves(figure, board));
            }

            if(figure.getName() == FigureName.QUEEN){
                moves.addAll(getBishopRookQueenMoves(figure, board));
            }

            if(figure.getName() == FigureName.KING){
                moves.addAll(getKingMoves(figure, board));
            }
        }

        return moves;
    }

    private List<Move> getKingMoves(Figure figure, Board board) {
        List<Move> kingMovesList = new ArrayList<>();
        int loop = figure.getOffset().length;
        int toField = 0;
        List<Integer> brokenFields;

        if(figure.getColor() == FigureColor.WHITE) {
            brokenFields = board.getBrokenFieldsBlack();
        }else{
            brokenFields = board.getBrokenFieldsWhite();
        }


        for (int i = 0; i < loop; i++){
            toField = figure.getPosition() + figure.getOffset()[i];

            if(toField < 0 || toField > 63) continue;

            boolean broken = false;

            // Ход на свободную клетку не под боем.
            if(checkField(toField, board) == null){

                for (Integer brokenField: brokenFields) {
                    if(toField == brokenField){
                        broken = true;
                        break;
                    }
                }

                if(!broken){
                    Move move = new Move(figure.getName(), figure.getColor(), figure.getPosition(), toField);
                    kingMovesList.add(move);
                    if(figure.getColor() == FigureColor.WHITE){
                        board.setBrokenFieldWhite(toField);
                    }else{
                        board.setBrokenFieldBlack(toField);
                    }
                }
            }

            if(checkField(toField, board) != null &&
                    checkField(toField, board).getColor() == figure.getColor()){
                if(figure.getColor() == FigureColor.WHITE){
                    board.setBrokenFieldWhite(toField);
                }else{
                    board.setBrokenFieldBlack(toField);
                }
                break;
            }

            // Убить фигуру противника не под боем.
            if(checkField(toField, board) != null &&
                    checkField(toField, board).getColor() != figure.getColor()){

                for (Integer brokenField: brokenFields) {
                    if(toField == brokenField){
                       broken = true;
                       break;
                    }
                }

                if(!broken){
                    Move move = new Move(figure.getName(), figure.getColor(), figure.getPosition(), toField);
                    kingMovesList.add(move);
                    if(figure.getColor() == FigureColor.WHITE){
                        board.setBrokenFieldWhite(toField);
                    }else{
                        board.setBrokenFieldBlack(toField);
                    }
                }
            }
        }

        return kingMovesList;
    }

    private List<Move> getBishopRookQueenMoves(Figure figure, Board board) {
        List<Move> movesList = new ArrayList<>();

        int loop = figure.getOffset().length;
        int toField = 0;
        int iter = 0;
        boolean gep;
        boolean generated = false;

        for (int i = 0; i < loop; i++) {

            gep = false;

            while(true){

                iter += figure.getOffset()[i];
                toField = figure.getPosition() + iter;

                if(toField < 0 || toField > 63){
                    iter = 0;
                    break;
                }

                for (int j = 0; j < board.getEdgeBoard().size(); j++) {

                    if(figure.getOffset()[i] == 8 || figure.getOffset()[i] == -8) break;

                    if(figure.getOffset()[i] == 1 || figure.getOffset()[i] == -1){
                        if(iter < 7 - figure.getPosition()) break;
                    }

                    if(board.getEdgeBoard().get(j) == toField){
                        iter = 0;
                        gep = true;
                        break;
                    }
                }

                if(figure.getOffset()[i] == 1 &&
                        (figure.getPosition() == 7 ||
                        figure.getPosition() == 15 ||
                        figure.getPosition() == 23 ||
                        figure.getPosition() == 31 ||
                        figure.getPosition() == 39 ||
                        figure.getPosition() == 47 ||
                        figure.getPosition() == 55) && toField > 0) {
                    iter = 0;
                    break;
                }

                if(figure.getOffset()[i] == 7  || figure.getOffset()[i] == -9){
                    if(figure.getPosition() == 0 || figure.getPosition() == 8 || figure.getPosition() == 16 ||
                            figure.getPosition() == 24 || figure.getPosition() == 32 || figure.getPosition() == 40 ||
                            figure.getPosition() == 48 || figure.getPosition() == 56){
                        iter = 0;
                        break;
                    }
                }

                if(figure.getOffset()[i] == - 7 || figure.getOffset()[i] == 9){
                    if(figure.getPosition() == 7 || figure.getPosition() == 15 || figure.getPosition() == 23 ||
                            figure.getPosition() == 31 || figure.getPosition() == 39 || figure.getPosition() == 47 ||
                            figure.getPosition() == 55 || figure.getPosition() == 63){
                        iter = 0;
                        break;
                    }
                }

                if(checkField(toField, board) == null){
                    Move move = new Move(figure.getName(), figure.getColor(), figure.getPosition(), toField);
                    if(figure.getColor() == FigureColor.WHITE){
                        board.setBrokenFieldWhite(toField);
                    }else{
                        board.setBrokenFieldBlack(toField);
                    }
                    movesList.add(move);
                }

                if(checkField(toField, board) != null &&
                        checkField(toField, board).getColor() == figure.getColor()){
                    if(figure.getColor() == FigureColor.WHITE){
                        board.setBrokenFieldWhite(toField);
                    }else{
                        board.setBrokenFieldBlack(toField);
                    }
                    iter = 0;
                    break;
                }

                if(checkField(toField, board) != null &&
                        checkField(toField, board).getColor() != figure.getColor() &&
                        checkField(toField, board).getName() == FigureName.KING){
                    Move move = new Move(figure.getName(), figure.getColor(), figure.getPosition(), toField);
                    move.setCheck(true);
                    movesList.add(move);
                    iter = 0;
                    break;
                }

                if(checkField(toField, board) != null &&
                        checkField(toField, board).getColor() != figure.getColor()){
                    Move move = new Move(figure.getName(), figure.getColor(), figure.getPosition(), toField);
                    movesList.add(move);
                    iter = 0;
                    break;
                }

               if(gep){
                   break;
               }
            }
        }

        return movesList;
    }

    private List<Move> getKnightMoves(Figure figure, Board board) {
        List<Move> knightMovesList = new ArrayList<>();
        int loop = figure.getOffset().length;
        int toField = 0;
        Move move;

        for (int i = 0; i < loop; i++) {
            toField = figure.getPosition() + figure.getOffset()[i];

            if (toField < 0 || toField > 63) continue;

            if(figure.getOffset()[i] == 6){
                switch (figure.getPosition()) {
                    case 0, 1, 8, 9, 16, 17, 24, 25, 32, 33, 40, 41, 48, 49, 56, 57 -> {
                        continue;
                    }
                }
            }

            if(figure.getOffset()[i] == - 6){
                switch (figure.getPosition()) {
                    case 6, 7, 14, 15, 22, 23, 30, 31, 38, 39, 46, 47, 54, 55, 62, 63 -> {
                        continue;
                    }
                }
            }

            if(figure.getOffset()[i] == 10){
                switch (figure.getPosition()) {
                    case 6, 7, 14, 15, 22, 23, 30, 31, 38, 39, 46, 47, 54, 55 -> {
                        continue;
                    }
                }
            }

            if(figure.getOffset()[i] == -10){
                switch (figure.getPosition()) {
                    case 0, 1, 8, 9, 16, 17, 24, 25, 32, 33, 40, 41, 48, 49, 56, 57 -> {
                        continue;
                    }
                }
            }

            if (checkField(toField, board) == null) {
                if (figure.getColor() == FigureColor.WHITE) {
                    board.setBrokenFieldWhite(toField);
                } else {
                    board.setBrokenFieldBlack(toField);
                }
                move = new Move(figure.getName(), figure.getColor(), figure.getPosition(), toField);
                knightMovesList.add(move);
            }

            if ((checkField(toField, board) != null &&
                    checkField(toField, board).getColor() != figure.getColor() &&
                    checkField(toField, board).getName() == FigureName.KING)) {
                move = new Move(figure.getName(), figure.getColor(), figure.getPosition(), toField);
                move.setCheck(true);
                knightMovesList.add(move);
            }

            if(checkField(toField, board) != null &&
                    checkField(toField, board).getColor() != figure.getColor() ){
                move = new Move(figure.getName(), figure.getColor(), figure.getPosition(), toField);
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
            if(checkField(figure.getPosition() + (offset_8 * 2), board) == null &&
                    checkField(figure.getPosition() + offset_8, board) == null){
                move = new Move(figure.getName(), figure.getColor(), figure.getPosition(),
                        figure.getPosition() + (offset_8 * 2));
                moves.add(move);
            }
        }

        // На соседней клетке по диагонали вражеская фигура
        if(checkField(figure.getPosition() + offset_7, board) != null &&
                checkField(figure.getPosition() + offset_7, board).getColor() != figure.getColor() &&
                edgePawn(offset_7, figure.getPosition())){
            move = new Move(figure.getName(), figure.getColor(), figure.getPosition(),
                    figure.getPosition() + offset_7);
            if(checkField(figure.getPosition() + offset_7, board).getName() == FigureName.KING) move.setCheck(true);
            moves.add(move);
        }

        if(checkField(figure.getPosition() + offset_7, board) != null &&
                checkField(figure.getPosition() + offset_7, board).getColor() == figure.getColor() &&
                edgePawn(offset_7, figure.getPosition())){
            if(figure.getColor() == FigureColor.WHITE){
                board.setBrokenFieldWhite(figure.getPosition() + offset_7);
            }else{
                board.setBrokenFieldBlack(figure.getPosition() + offset_7);
            }
        }

        if(checkField(figure.getPosition() + offset_9, board) != null &&
                checkField(figure.getPosition() + offset_9, board).getColor() != figure.getColor() &&
                edgePawn(offset_9, figure.getPosition())){
            move = new Move(figure.getName(), figure.getColor(), figure.getPosition(),
                    figure.getPosition() + offset_9);
            if(checkField(figure.getPosition() + offset_9, board).getName() == FigureName.KING) move.setCheck(true);
            moves.add(move);
        }

        if(checkField(figure.getPosition() + offset_9, board) != null &&
                checkField(figure.getPosition() + offset_9, board).getColor() == figure.getColor() &&
                edgePawn(offset_9, figure.getPosition())){
            if(figure.getColor() == FigureColor.WHITE){
                board.setBrokenFieldWhite(figure.getPosition() + offset_9);
            }else{
                board.setBrokenFieldBlack(figure.getPosition() + offset_9);
            }
        }

        if(figure.getPosition() > beginDoPosition && figure.getPosition() <= endDoPosition &&
                checkField(figure.getPosition() + offset_7, board) == null && edgePawn(offset_7, figure.getPosition())){
            takingOnThePass(figure, moves, board, offset_7, interference, startOpp, endOpp, startTargetPos, endTargetPos);
        }

        if(figure.getPosition() >= beginDoPosition && figure.getPosition() < endDoPosition &&
                checkField(figure.getPosition() + offset_9, board) == null &&
                edgePawn(offset_9, figure.getPosition())){
            takingOnThePass(figure, moves, board, offset_9, interference, startOpp, endOpp, startTargetPos, endTargetPos);
        }

        return moves;
    }

    private void takingOnThePass(Figure figure, List<Move> moves, Board board, int offset, int interference,
                                 int startOpp, int endOpp, int startTargetPos, int endTargetPos) {
        if(board.getFormRecording().size() > 0){
            Move move;
            int len = board.getFormRecording().size() - 1;

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
    }

    private boolean edgePawn(int offset, int position){
        if((position == 8 || position == 16 || position == 24 || position == 34 ||
        position == 40 || position == 48 || position == 56) && offset == 7) return false;
        if((position == 15 || position == 23 || position == 31 || position == 39 ||
                position == 47 || position == 55) && offset == 9) return false;
        return true;
    }
}
