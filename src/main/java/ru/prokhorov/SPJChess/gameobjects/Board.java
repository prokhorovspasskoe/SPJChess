package ru.prokhorov.SPJChess.gameobjects;

import ru.prokhorov.SPJChess.events.Move;
import ru.prokhorov.SPJChess.gameobjects.abstracts.Figure;
import ru.prokhorov.SPJChess.gameobjects.enums.FigureColor;

import java.util.List;

public class Board {
    private List<Figure> listWhite;
    private List<Figure> listBlack;
    private List<Move> formRecording;
    int remainingTime;
    FigureColor turn;

    public List<Figure> getListWhite() {
        return listWhite;
    }

    public void setListWhite(List<Figure> listWhite) {
        this.listWhite = listWhite;
    }

    public List<Figure> getListBlack() {
        return listBlack;
    }

    public void setListBlack(List<Figure> listBlack) {
        this.listBlack = listBlack;
    }

    public int getRemainingTime() {
        return remainingTime;
    }

    public void setRemainingTime(int remainingTime) {
        this.remainingTime = remainingTime;
    }

    public FigureColor getTurn() {
        return turn;
    }

    public void setTurn(FigureColor turn) {
        this.turn = turn;
    }

    public List<Move> getFormRecording() {
        return formRecording;
    }

    public void setFormRecording(List<Move> formRecording) {
        this.formRecording = formRecording;
    }

    public Move getRecord(int index){
        return this.formRecording.get(index);
    }

    public void setRecord(Move move){
        this.formRecording.add(move);
    }
}
