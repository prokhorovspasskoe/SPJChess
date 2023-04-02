package ru.prokhorov.SPJChess.process.interfaces;

import ru.prokhorov.SPJChess.events.Move;
import ru.prokhorov.SPJChess.gameobjects.Board;
import ru.prokhorov.SPJChess.gameobjects.abstracts.Figure;
import ru.prokhorov.SPJChess.gameobjects.enums.FigureColor;

import java.util.List;

public interface MoveGenerator {
    Figure checkField(int field, Board board);

    List<Move> getAllMoves(Board board);
}
