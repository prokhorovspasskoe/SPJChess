package ru.prokhorov.SPJChess;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import ru.prokhorov.SPJChess.events.Move;
import ru.prokhorov.SPJChess.gameobjects.enums.FieldName;
import ru.prokhorov.SPJChess.others.TestPosition;
import ru.prokhorov.SPJChess.process.MoveGeneratorImpl;

import java.util.List;
import java.util.Objects;

@SpringBootApplication
public class SpjChessApplication {
	public static void main(String[] args) {
		int h = 0;
		SpringApplication.run(SpjChessApplication.class, args);
		TestPosition testPosition = new TestPosition();
		MoveGeneratorImpl moveGenerator = new MoveGeneratorImpl();
		List<Move> moveList = moveGenerator.getAllMoves(testPosition.startPosition());
		for (int i = 0; i <moveList.size() ; i++) {
			if(Objects.equals(moveList.get(i).getCastling(), "")){
				System.out.println(moveList.get(i).getFigureName() + " " + (FieldName.values()[moveList.get(i).getStartPosition()] + "-" + FieldName.values()[(moveList.get(i).getTargetPosition())]));
			}else{
				System.out.println(moveList.get(i).getCastling());
			}
			h++;
		}
		System.out.println(h);
		System.exit(0);
	}
}
