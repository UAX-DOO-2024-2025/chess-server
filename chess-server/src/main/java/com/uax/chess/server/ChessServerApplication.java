package com.uax.chess.server;

import com.uax.chess.model.ChessGame;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.HashMap;

@SpringBootApplication
@RestController
public class ChessServerApplication {

    private int id = 1;
    public HashMap<Integer, ChessGame> partidas = new HashMap<>();

    public static void main(String[] args) {
        SpringApplication.run(ChessServerApplication.class, args);
    }

    @PostMapping("/partida")
    @ResponseBody
    public String crearPartida() {
        ChessGame partida = new ChessGame();
        partidas.put(id, partida);
        id++;
        return "Partida creada con id: " + (id - 1);
    }

    @GetMapping("/partida/{id}")
    @ResponseBody
    public ChessGame partida(@PathVariable Integer id) {
        if (!partidas.containsKey(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "La partida no existe, o ya se ha terminado.");
        }
        return partidas.get(id);
    }

	@PostMapping("/partida/{id}")
	@ResponseBody
	public ChessGame mover(@PathVariable Integer id, @RequestParam int filaOrigen, @RequestParam int columnaOrigen, @RequestParam int filaDestino, @RequestParam int columnaDestino) {
		if (!partidas.containsKey(id)) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "La partida no existe, o ya se ha terminado.");
		}
		ChessGame partida = partidas.get(id);
		partida.getTablero().moverFicha(filaOrigen, columnaOrigen, filaDestino, columnaDestino);
		return partida;
	}

}