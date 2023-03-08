package ru.prokhorov.SPJChess.gameobjects;

import ru.prokhorov.SPJChess.gameobjects.abstracts.Figure;
import ru.prokhorov.SPJChess.gameobjects.enums.FigureColor;
import ru.prokhorov.SPJChess.gameobjects.enums.FigureName;

public class Queen extends Figure {

    FigureName name;
    FigureColor color;
    int weight;
    int position;
    int [] offset;

    public Queen(FigureColor color, int position) {
        this.color = color;
        this.position = position;
        this.name = FigureName.QUEEN;
        this.weight = 9;
        this.offset = new int [8];
        offset[0] = 8;
        offset[1] = -8;
        offset[2] = 1;
        offset[3] = -1;
        offset[4] = 7;
        offset[5] = -7;
        offset[6] = 9;
        offset[7] = -9;
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
