package com.instawins.game.manager.service;

import com.instawins.game.manager.dao.GameInfo;
import com.instawins.game.manager.dao.GameRepo;
import com.instawins.game.manager.dao.GameStatusType;
import com.instawins.game.manager.dto.GameResult;
import org.apache.commons.rng.UniformRandomProvider;
import org.apache.commons.rng.simple.RandomSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
//import org.apache.commons.math3.random.RandomDataGenerator;

import java.util.*;
import java.util.stream.Collectors;

@Component
public class DrawBallMachineService {

    public static final String FIRST_POS = "1";
    public static final String SECOND_POS = "2";
    public static final String THIRD_POS = "3";
    @Autowired
    private GameRepo gameRepo;

    public List<GameResult> selectWinners() {
        List<GameInfo> closedGames = gameRepo.findByGameStatus(GameStatusType.CLOSED.toString());
        List<GameResult> results = new ArrayList<>();
        closedGames.forEach(closedGame -> {
            Map<String, String> winners = chooseThreeLuckyWinners(closedGame);
            gameRepo.save(closedGame);
            GameResult result = new GameResult();
            result.setGameId(closedGame.getGameId());
            result.setWinnerTokens(winners);
            results.add(result);
        });

        return results;
    }

    private Map<String, String> chooseThreeLuckyWinners(GameInfo game) {

        //Get all the tokens from the pot
        List<String> tokens = game.getPlayerGameInfo().stream()
                .map(player -> player.getTokenId())
                .collect(Collectors.toList());

        //Shuffle it one more time before choosing winner.
        Collections.shuffle(tokens);

        //Map to store results
        Map<String, String> results = new HashMap<>();

        int max = tokens.size();
        for (int i = 1; i <= 3; i++) {
            int pos = generateRandomNumber(max - i);
            results.put(Integer.toString(i), tokens.get(pos));
            tokens.remove(pos);
        }

        game.setFirstPos(results.get(FIRST_POS));
        game.setSecondPos(results.get(SECOND_POS));
        game.setThirdPos(results.get(THIRD_POS));

        return results;
    }

    /**
     * Returns a random number for a max value passed
     * <p>
     * INFO: more secured and latest apache RNG (https://commons.apache.org/proper/commons-rng/userguide/rng.html)
     * <p>
     * int n = rng.nextInt(); // Integer.MIN_VALUE <= n <= Integer.MAX_VALUE.
     * int m = rng.nextInt(max); // 0 <= m < max.
     *
     * @param seed
     * @return
     */
    public int generateRandomNumber(int seed) {
        int randomNumber = 0;
        UniformRandomProvider rng = RandomSource.MT.create(RandomSource.MWC_256);
        if (seed > 0) {
            while (randomNumber <= 0) {
                randomNumber = rng.nextInt(seed + 1);
            }
        }
        return randomNumber;
    }
}
