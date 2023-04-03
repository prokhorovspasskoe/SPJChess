package ru.prokhorov.SPJChess.gameobjects;

import ru.prokhorov.SPJChess.events.Move;
import ru.prokhorov.SPJChess.gameobjects.abstracts.Figure;
import ru.prokhorov.SPJChess.gameobjects.enums.FigureColor;

import java.util.ArrayList;
import java.util.List;

public class Board {
    private List<Figure> listWhite;
    private List<Figure> listBlack;
    private List<Move> formRecording;
    private List<Integer> brokenFieldsWhite;

    private List<Integer> brokenFieldsBlack;

    private List<Integer> edgeBoard;
    int remainingTime;
    FigureColor turn;

    public Board() {
        this.listWhite = new ArrayList<>();
        this.listBlack = new ArrayList<>();
        this.formRecording = new ArrayList<>();
        this.brokenFieldsWhite = new ArrayList<>();
        this.brokenFieldsBlack = new ArrayList<>();
        this.edgeBoard = new ArrayList<>();
        edgeBoard.add(0);
        edgeBoard.add(1);
        edgeBoard.add(2);
        edgeBoard.add(3);
        edgeBoard.add(4);
        edgeBoard.add(5);
        edgeBoard.add(6);
        edgeBoard.add(7);
        edgeBoard.add(8);
        edgeBoard.add(15);
        edgeBoard.add(16);
        edgeBoard.add(23);
        edgeBoard.add(24);
        edgeBoard.add(31);
        edgeBoard.add(32);
        edgeBoard.add(39);
        edgeBoard.add(40);
        edgeBoard.add(47);
        edgeBoard.add(48);
        edgeBoard.add(55);
        edgeBoard.add(56);
        edgeBoard.add(57);
        edgeBoard.add(58);
        edgeBoard.add(59);
        edgeBoard.add(60);
        edgeBoard.add(61);
        edgeBoard.add(62);
        edgeBoard.add(63);
    }

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

    public List<Integer> getBrokenFieldsWhite() {
        return brokenFieldsWhite;
    }

    public void setBrokenFieldWhite(Integer brokenFieldsWhite) {
        this.brokenFieldsWhite.add(brokenFieldsWhite);
    }

    public List<Integer> getBrokenFieldsBlack() {
        return brokenFieldsBlack;
    }

    public void setBrokenFieldBlack(Integer brokenFieldBlack) {
        this.brokenFieldsBlack.add(brokenFieldBlack);
    }

    public List<Integer> getEdgeBoard() {
        return edgeBoard;
    }
}
