package ru.prokhorov.SPJChess.gameobjects;

import ru.prokhorov.SPJChess.gameobjects.abstracts.Figure;
import ru.prokhorov.SPJChess.gameobjects.enums.FigureColor;
import ru.prokhorov.SPJChess.gameobjects.enums.FigureName;

public class Rook extends Figure {
    FigureName name;
    FigureColor color;
    int weight;
    int position;
    int [] offset;

    public Rook(FigureColor color, int position) {
        this.color = color;
        this.position = position;
        this.name = FigureName.ROOK;
        this.weight = 5;
        this.offset = new int [4];
        offset[0] = 8;
        offset[1] = -8;
        offset[2] = 1;
        offset[3] = -1;
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
