package ru.prokhorov.SPJChess.gameobjects.abstracts;

import ru.prokhorov.SPJChess.gameobjects.enums.FieldName;
import ru.prokhorov.SPJChess.gameobjects.enums.FigureColor;
import ru.prokhorov.SPJChess.gameobjects.enums.FigureName;

import java.util.List;

public abstract class Figure {

    public abstract FigureName getName();

    public abstract FigureColor getColor();

    public abstract int getWeight();

    public abstract int getPosition();

    public abstract void setPosition(int position);

    public abstract int[] getOffset();
}
