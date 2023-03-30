package ru.prokhorov.SPJChess.events;

import ru.prokhorov.SPJChess.gameobjects.abstracts.Figure;
import ru.prokhorov.SPJChess.gameobjects.enums.FigureColor;
import ru.prokhorov.SPJChess.gameobjects.enums.FigureName;

public class Move {
   private FigureName figureName;
   private FigureColor figureColor;
   private int startPosition;
   private int targetPosition;

    public Move(FigureName figureName, FigureColor figureColor, int startPosition, int targetPosition) {
        this.figureName = figureName;
        this.figureColor = figureColor;
        this.startPosition = startPosition;
        this.targetPosition = targetPosition;
    }

    public FigureName getFigureName() {
        return figureName;
    }

    public void setFigureName(FigureName figureName) {
        this.figureName = figureName;
    }

    public FigureColor getFigureColor() {
        return figureColor;
    }

    public void setFigureColor(FigureColor figureColor) {
        this.figureColor = figureColor;
    }

    public int getStartPosition() {
        return startPosition;
    }

    public void setStartPosition(int startPosition) {
        this.startPosition = startPosition;
    }

    public int getTargetPosition() {
        return targetPosition;
    }

    public void setTargetPosition(int targetPosition) {
        this.targetPosition = targetPosition;
    }
}
