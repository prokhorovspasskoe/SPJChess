package ru.prokhorov.SPJChess.process;

import ru.prokhorov.SPJChess.gameobjects.Board;
import ru.prokhorov.SPJChess.gameobjects.abstracts.Figure;

import java.util.List;

public class MoveDetector {
    public Figure checkField(int field, Board board){
        for (int i = 0; i < board.getListWhite().size(); i++) {
            if(field == board.getListWhite().get(i).getPosition()) return board.getListWhite().get(i);
        }
        return null;
    }
}
