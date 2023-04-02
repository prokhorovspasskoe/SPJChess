package ru.prokhorov.SPJChess.process.interfaces;

import ru.prokhorov.SPJChess.events.Move;
import ru.prokhorov.SPJChess.gameobjects.Board;
import ru.prokhorov.SPJChess.gameobjects.enums.FigureColor;

public interface CoreBruteForce {
    Board makeMove(Board board);
}
