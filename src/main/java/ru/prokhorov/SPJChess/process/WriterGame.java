package ru.prokhorov.SPJChess.process;

import ru.prokhorov.SPJChess.events.Move;

import java.util.List;

public class WriterGame {
    private List<Move> formRecording;

    public List<Move> getFormRecording() {
        return formRecording;
    }

    public void setFormRecording(List<Move> formRecording) {
        this.formRecording = formRecording;
    }

    public Move getMove(int index){
        return formRecording.get(index);
    }

    public void setMove(Move move){
        this.formRecording.add(move);
    }
}
