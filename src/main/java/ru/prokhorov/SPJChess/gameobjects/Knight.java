package ru.prokhorov.SPJChess.gameobjects;

import ru.prokhorov.SPJChess.gameobjects.abstracts.Figure;
import ru.prokhorov.SPJChess.gameobjects.enums.FigureColor;
import ru.prokhorov.SPJChess.gameobjects.enums.FigureName;

import java.util.List;

public class Knight extends Figure {

    public Knight(FigureColor color, int position) {
        this.color = color;
        this.position = position;
        this.name = FigureName.KNIGHT;
        this.weight = 3;
        this.offset = new int [8];
        offset[0] = 6;
        offset[1] = -6;
        offset[2] = 10;
        offset[3] = -10;
        offset[4] = 15;
        offset[5] = -15;
        offset[6] = 17;
        offset[7] = -17;
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
