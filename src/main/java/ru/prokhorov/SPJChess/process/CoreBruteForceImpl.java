package ru.prokhorov.SPJChess.process;

import ru.prokhorov.SPJChess.events.Move;
import ru.prokhorov.SPJChess.gameobjects.Board;
import ru.prokhorov.SPJChess.gameobjects.enums.FigureColor;
import ru.prokhorov.SPJChess.process.interfaces.CoreBruteForce;
import ru.prokhorov.SPJChess.process.interfaces.MoveGenerator;

import java.util.ArrayList;
import java.util.List;

public class CoreBruteForceImpl implements CoreBruteForce {

    private Board bufferBoard;
    private List<Move> moves;
    @Override
    public Board makeMove(Board board) {

        //(Заметка) Удалить битые поле фигуры перед её ходом и записать новые битые поля после хода.

        Board newBoard;
        List<Move> preventionCheck = new ArrayList<>();
        MoveGenerator moveGenerator = new MoveGeneratorImpl();

        if(checkDetector(board)) {
            moves = moveGenerator.getAllMoves(bufferBoard);

            for (Move move : moves) {
                if (!move.isCheck()) preventionCheck.add(move);
            }

            newBoard = bruteForce(bufferBoard, preventionCheck);

        }else{
            newBoard = bruteForce(bufferBoard, moves);
        }
        return newBoard;
    }

    private boolean checkDetector(Board board){
        FigureColor turn;
        List<Move> movesList;
        bufferBoard = board;
        MoveGenerator moveGenerator = new MoveGeneratorImpl();

        if(board.getTurn() == FigureColor.WHITE){
            turn = FigureColor.BLACK;
        }else{
            turn = FigureColor.WHITE;
        }

        bufferBoard.setTurn(turn);
        movesList = moveGenerator.getAllMoves(bufferBoard);

        for (Move move : movesList) {
            if (move.isCheck()){
                if(bufferBoard.getTurn() == FigureColor.WHITE){
                    bufferBoard.setTurn(FigureColor.BLACK);
                }else{
                    bufferBoard.setTurn(FigureColor.WHITE);
                }
                return true;
            }
        }

        if(bufferBoard.getTurn() == FigureColor.WHITE){
            bufferBoard.setTurn(FigureColor.BLACK);
        }else{
            bufferBoard.setTurn(FigureColor.WHITE);
        }

        return false;
    }

    private Board bruteForce(Board bruteForceBoard, List<Move> moves){
        // Обновить списки фигур
        return null;
    }
}
