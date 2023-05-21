package ru.prokhorov.SPJChess.gameobjects;

import ru.prokhorov.SPJChess.gameobjects.abstracts.Figure;
import ru.prokhorov.SPJChess.gameobjects.enums.FieldName;
import ru.prokhorov.SPJChess.gameobjects.enums.FigureColor;
import ru.prokhorov.SPJChess.gameobjects.enums.FigureName;

import java.util.List;

public class Pawn extends Figure {

    public Pawn(FigureColor color, int position) {
        this.color = color;
        this.position = position;
        this.name = FigureName.PAWN;
        this.weight = 1;
        this.offset = new int[6];
        offset[0] = 7;
        offset[1] = -7;
        offset[2] = 9;
        offset[3] = -9;
        offset[4] = 8;
        offset[5] = -8;
    }


    @Override
    public FigureName getName() {
        return this.name;
    }

    @Override
    public FigureColor getColor() {
        return this.color;
    }

    @Override
    public int getWeight() {
        return this.weight;
    }

    @Override
    public int getPosition() {
        return this.position;
    }

    @Override
    public void setPosition(int position) {
        this.position = position;
    }

    @Override
    public int[] getOffset() {
        return this.offset;
    }
}
