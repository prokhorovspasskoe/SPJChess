package ru.prokhorov.SPJChess;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import ru.prokhorov.SPJChess.events.Move;
import ru.prokhorov.SPJChess.gameobjects.*;
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

	}
}
