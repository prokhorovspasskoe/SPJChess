package ru.prokhorov.SPJChess.events;

import ru.prokhorov.SPJChess.gameobjects.abstracts.Figure;

public class Move {
    Figure figure;
    int targetField;

    public Move(){

    }

    public Move(Figure figure, int targetField) {
        this.figure = figure;
        this.targetField = targetField;
    }

    public Figure getFigure() {
        return figure;
    }

    public void setFigure(Figure figure) {
        this.figure = figure;
    }

    public int getTargetField() {
        return targetField;
    }

    public void setTargetField(int targetField) {
        this.targetField = targetField;
    }
}
