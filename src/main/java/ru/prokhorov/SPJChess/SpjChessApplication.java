package ru.prokhorov.SPJChess;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import ru.prokhorov.SPJChess.events.Move;
import ru.prokhorov.SPJChess.gameobjects.Board;
import ru.prokhorov.SPJChess.gameobjects.Pawn;
import ru.prokhorov.SPJChess.gameobjects.abstracts.Figure;
import ru.prokhorov.SPJChess.gameobjects.enums.FigureColor;
import ru.prokhorov.SPJChess.gameobjects.enums.FigureName;
import ru.prokhorov.SPJChess.process.MoveGeneratorImpl;
import ru.prokhorov.SPJChess.process.interfaces.MoveGenerator;
import java.util.List;

@SpringBootApplication
public class SpjChessApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpjChessApplication.class, args);

		Board board = new Board();
		Figure pawn = new Pawn(FigureColor.WHITE, 33);
		Figure pawn2 = new Pawn(FigureColor.BLACK, 42);
//		Move move = new Move(FigureName.PAWN, FigureColor.BLACK, 48, 32);
//		board.setRecord(move);
		board.getListWhite().add(pawn);
		board.getListBlack().add(pawn2);
		board.setTurn(FigureColor.WHITE);
		MoveGenerator moveGenerator = new MoveGeneratorImpl();
		List<Move> moveList = moveGenerator.getAllMoves(board);
		System.out.println(moveList);
		System.out.println(moveList.get(0).getStartPosition());
		System.out.println(moveList.get(0).getTargetPosition());
		System.out.println(moveList.get(1).getStartPosition());
		System.out.println(moveList.get(1).getTargetPosition());
	}
}
