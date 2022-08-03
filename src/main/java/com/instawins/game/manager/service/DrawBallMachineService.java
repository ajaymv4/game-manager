package com.instawins.game.manager.service;

import com.instawins.game.manager.dao.GameInfo;
import com.instawins.game.manager.dao.GameRepo;
import com.instawins.game.manager.dao.GameStatusType;
import com.instawins.game.manager.dto.GameResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.apache.commons.math3.random.RandomDataGenerator;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
            Map<String, String> winners = chooseLuckyWinners(closedGame);
            gameRepo.save(closedGame);
            GameResult result = new GameResult();
            result.setGameId(closedGame.getGameId());
            result.setWinnerTokens(winners);
            results.add(result);
        });

        return results;
    }

    private Map<String, String> chooseLuckyWinners(GameInfo game) {

        List<String> tokens = game.getPlayerGameInfo().stream()
                .map(player -> player.getTokenId())
                .collect(Collectors.toList());

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
     *
     * @param max
     * @return
     */
    public int generateRandomNumber(int max) {
        if (max > 0) {
            RandomDataGenerator randomDataGenerator = new RandomDataGenerator();
            return randomDataGenerator.nextInt(1, max);
        }
        return 0;
    }
}
