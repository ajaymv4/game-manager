package com.instawins.game.manager;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}


	/*@Bean
	public CommandLineRunner demo(PlayerGameRepo repository, GameRepo gameRepo) {
		return (args) -> {
			List<PlayerGameInfo> playerGameInfo = repository.findByPlayerId("1");
			System.out.println(playerGameInfo);

			GameInfo game = gameRepo.findByGameId(1L);
			System.out.println();
		};
	}*/

}
