package ru.prokhorov.SPJChess.gameobjects;

import ru.prokhorov.SPJChess.gameobjects.abstracts.Figure;
import ru.prokhorov.SPJChess.gameobjects.enums.FigureColor;
import ru.prokhorov.SPJChess.gameobjects.enums.FigureName;

public class Bishop extends Figure {

    FigureName name;
    FigureColor color;
    int weight;
    int position;
    int [] offset;

    public Bishop(FigureColor color, int position) {
        this.color = color;
        this.position = position;
        this.name = FigureName.BISHOP;
        this.weight = 3;
        this.offset = new int [4];
        offset[0] = 7;
        offset[1] = -7;
        offset[2] = 9;
        offset[3] = -9;
    }

    @Override
    public int[] getOffset() {
        return offset;
    }

    @Override
    public FigureName getName() {
        return name;
    }

    @Override
    public FigureColor getColor() {
        return color;
    }

    @Override
    public int getWeight() {
        return weight;
    }

    @Override
    public int getPosition() {
        return position;
    }
    @Override
    public void setPosition(int position) {
        this.position = position;
    }
}
