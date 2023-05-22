package ru.prokhorov.SPJChess.others;

import ru.prokhorov.SPJChess.events.Move;
import ru.prokhorov.SPJChess.gameobjects.*;
import ru.prokhorov.SPJChess.gameobjects.abstracts.Figure;
import ru.prokhorov.SPJChess.gameobjects.enums.FigureColor;

import java.util.List;

public class TestPosition {
    public Board startPosition(){
        Board board = new Board();
        board.setTurn(FigureColor.WHITE);

        board.setBrokenFieldBlack(40);
        board.setBrokenFieldBlack(41);
        board.setBrokenFieldBlack(42);
        board.setBrokenFieldBlack(43);
        board.setBrokenFieldBlack(44);
        board.setBrokenFieldBlack(45);
        board.setBrokenFieldBlack(46);
        board.setBrokenFieldBlack(47);
        board.setBrokenFieldWhite(16);
        board.setBrokenFieldWhite(17);
        board.setBrokenFieldWhite(18);
        board.setBrokenFieldWhite(19);
        board.setBrokenFieldWhite(20);
        board.setBrokenFieldWhite(21);
        board.setBrokenFieldWhite(22);
        board.setBrokenFieldWhite(23);

        board.setBrokenFieldBlack(24);
        board.setBrokenFieldBlack(25);
        board.setBrokenFieldBlack(26);
        board.setBrokenFieldBlack(27);
        board.setBrokenFieldBlack(28);
        board.setBrokenFieldBlack(29);
        board.setBrokenFieldBlack(30);
        board.setBrokenFieldBlack(31);


        board.getListWhite().add(new King(FigureColor.WHITE, 4));
        board.getListWhite().add(new Queen(FigureColor.WHITE, 3));
        board.getListWhite().add(new Bishop(FigureColor.WHITE, 2));
        board.getListWhite().add(new Bishop(FigureColor.WHITE, 5));
        board.getListWhite().add(new Knight(FigureColor.WHITE, 1));
        board.getListWhite().add(new Knight(FigureColor.WHITE, 6));
        board.getListWhite().add(new Rook(FigureColor.WHITE, 0));
        board.getListWhite().add(new Rook(FigureColor.WHITE, 7));
        board.getListWhite().add(new Pawn(FigureColor.WHITE, 8));
        board.getListWhite().add(new Pawn(FigureColor.WHITE, 9));
        board.getListWhite().add(new Pawn(FigureColor.WHITE, 10));
        board.getListWhite().add(new Pawn(FigureColor.WHITE, 11));
        board.getListWhite().add(new Pawn(FigureColor.WHITE, 12));
        board.getListWhite().add(new Pawn(FigureColor.WHITE, 13));
        board.getListWhite().add(new Pawn(FigureColor.WHITE, 14));
        board.getListWhite().add(new Pawn(FigureColor.WHITE, 15));

        board.getListBlack().add(new King(FigureColor.BLACK, 60));
        board.getListBlack().add(new Queen(FigureColor.BLACK, 59));
        board.getListBlack().add(new Bishop(FigureColor.BLACK, 58));
        board.getListBlack().add(new Bishop(FigureColor.BLACK, 61));
        board.getListBlack().add(new Knight(FigureColor.BLACK, 57));
        board.getListBlack().add(new Knight(FigureColor.BLACK, 62));
        board.getListBlack().add(new Rook(FigureColor.BLACK, 56));
        board.getListBlack().add(new Rook(FigureColor.BLACK, 63));
        board.getListBlack().add(new Pawn(FigureColor.BLACK, 48));
        board.getListBlack().add(new Pawn(FigureColor.BLACK, 49));
        board.getListBlack().add(new Pawn(FigureColor.BLACK, 50));
        board.getListBlack().add(new Pawn(FigureColor.BLACK, 51));
        board.getListBlack().add(new Pawn(FigureColor.BLACK, 52));
        board.getListBlack().add(new Pawn(FigureColor.BLACK, 53));
        board.getListBlack().add(new Pawn(FigureColor.BLACK, 54));
        board.getListBlack().add(new Pawn(FigureColor.BLACK, 55));

        return board;
    }

    public Board addFigureToPosition(Board board, Figure figure, FigureColor turn){

        if(figure.getColor() == FigureColor.WHITE){
            board.getListWhite().add(figure);
        }else{
            board.getListBlack().add(figure);
        }

        board.setTurn(turn);

        return board;
    }

    public Board removeFigureFromPosition(Board board, Figure figure){

        if(figure.getColor() == FigureColor.WHITE){
            board.getListWhite().remove(figure);
        }else{
            board.getListBlack().remove(figure);
        }

        return board;
    }

    public Board createNewPosition(Board board, List<Figure> figureList, FigureColor turn){

        board.getListWhite().clear();
        board.getListBlack().clear();

        if(figureList.size() > 0){
            for (Figure figure: figureList) {
                board = addFigureToPosition(board, figure, turn);
            }
        }
        return board;
    }
}
