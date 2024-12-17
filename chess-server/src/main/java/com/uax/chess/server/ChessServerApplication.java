package com.uax.chess.server;

import com.uax.chess.model.ChessGame;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.*;

@SpringBootApplication
@RestController
public class ChessServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(ChessServerApplication.class, args);
	}

	@GetMapping("/")
	public String hello() {
		return "Hello, Chess!";
	}

	@PostMapping("/partida")
	public ChessGame crearPartida() {
		return new ChessGame();
	}

	@GetMapping("/partida/{id}")
	public String partida(@PathVariable Integer id) {
		return "Chess Game: " + id;
	}

}
