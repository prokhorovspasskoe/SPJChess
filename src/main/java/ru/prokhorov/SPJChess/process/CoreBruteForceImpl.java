package ru.prokhorov.SPJChess.process;

import ru.prokhorov.SPJChess.events.Move;
import ru.prokhorov.SPJChess.gameobjects.Board;
import ru.prokhorov.SPJChess.gameobjects.Pawn;
import ru.prokhorov.SPJChess.gameobjects.abstracts.Figure;
import ru.prokhorov.SPJChess.gameobjects.enums.FigureColor;
import ru.prokhorov.SPJChess.process.interfaces.CoreBruteForce;
import ru.prokhorov.SPJChess.process.interfaces.MoveGenerator;

import java.util.*;

public class CoreBruteForceImpl implements CoreBruteForce {

    private Board bufferBoard;
    private MoveGenerator moveGenerator;
    private List<Double> evaluations;
    private Map<Board, Double> position;
    @Override
    public Board makeMove(Board board) {
        moveGenerator = new MoveGeneratorImpl();
        List<Move> moves = moveGenerator.getAllMoves(board);
        bufferBoard = board;
        position = new HashMap<>();

        return bruteForce(bufferBoard, moves);
    }

    @Override
    public boolean checkDetector(Board board){
        FigureColor turn;
        List<Move> movesList;
        bufferBoard = board;

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
        Figure fg;
        int index = 0;
        double evaluation = 0.0;
        Board returnBoard = new Board();
        Set<Board> boards = new HashSet<>();
        Figure figure;
        // Получить ход
        for (Move move: moves) {
            // Получить фигуру которая должна ходить
            for (Iterator<Figure> iterator = bruteForceBoard.getListWhite().listIterator(); iterator.hasNext();) {
                index++;
                // Если фигура найдена
                figure = iterator.next();

                if(figure.getPosition() == move.getStartPosition()){
                    // Сохранить её в буфер
                    fg = figure;
                    // Удалить фигуру из списка (устранить ошибку ConcurrentModificationException)
                    bruteForceBoard.getListWhite().remove(figure);
                    // Переписать её позицию на позицию хода
                    figure.setPosition(move.getTargetPosition());
                    // Поместить в список
                    bruteForceBoard.getListWhite().add(index, figure);
                    // Оценить позицию
                    evaluation = staticEvaluation(bruteForceBoard);
                    // Сохранить оценку и позицию
                    position.put(bruteForceBoard, evaluation);
                    // Удалить из списка
                    bruteForceBoard.getListWhite().remove(figure);
                    // Полместить сохранённую в буфере фигуру обратно в список
                    bruteForceBoard.getListWhite().add(index, fg);
                }
            }
        }
        // Сравнить ходы между собой и выбрать лучший
        boards = position.keySet();
        ArrayList<Double> eval = new ArrayList<>(position.values());
        int indexPos = List.of().indexOf(Collections.max(eval));
        evaluation = Collections.max(eval);
        ArrayList<Board> boardArrayList = new ArrayList<>(boards);
        returnBoard = boardArrayList.get(indexPos);
        returnBoard.setEvaluation(evaluation);

        return returnBoard;
    }

    private double staticEvaluation(Board bruteForceBoard){
        // Подсчёт материала
        int whiteMaterial = 0;
        int blackMaterial = 0;
        int material;
        double brokenFieldWhite;
        double brokenFieldBlack;
        double brokenFieldWeight;

        List<Figure> blackFiguresList;
        List<Figure> whiteFiguresList;

        blackFiguresList = bruteForceBoard.getListBlack();
        whiteFiguresList = bruteForceBoard.getListWhite();

        for (Figure figure: whiteFiguresList) {
            whiteMaterial += figure.getWeight();
        }

        for (Figure figure: blackFiguresList){
            blackMaterial += figure.getWeight();
        }

        material = whiteMaterial - blackMaterial;

        // Поля под боем
        brokenFieldWhite = bruteForceBoard.getBrokenFieldsWhite().size() * 0.01;
        brokenFieldBlack = bruteForceBoard.getBrokenFieldsBlack().size() * 0.01;

        brokenFieldWeight = brokenFieldWhite - brokenFieldBlack;

        // Безопасность короля

        // Открытые линии

        // Центр

        // Слабые пешки и поля

        return material + brokenFieldWeight;
    }
}
