package ru.prokhorov.SPJChess;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import ru.prokhorov.SPJChess.events.Move;
import ru.prokhorov.SPJChess.gameobjects.Board;
import ru.prokhorov.SPJChess.gameobjects.Knight;
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
		Figure knight = new Knight(FigureColor.WHITE, 19);
		board.getListWhite().add(knight);
		//board.setTurn(FigureColor.BLACK);
		board.setTurn(FigureColor.WHITE);
		MoveGenerator moveGenerator = new MoveGeneratorImpl();
		List<Move> moveList = moveGenerator.getAllMoves(board);
		System.out.println(moveList);

		for (Move move: moveList) {
			System.out.println(move.getStartPosition());
			System.out.println(move.getTargetPosition());
		}
	}
}
