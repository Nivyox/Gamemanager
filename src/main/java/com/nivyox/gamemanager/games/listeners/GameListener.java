package com.nivyox.gamemanager.games.listeners;

import com.nivyox.gamemanager.GameManager;
import com.nivyox.gamemanager.games.Game;
import com.nivyox.gamemanager.games.GameState;
import com.nivyox.gamemanager.games.GameTimer;
import com.nivyox.gamemanager.games.events.*;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;

public class GameListener implements Listener {

    @EventHandler
    public void gameCreateEvent(GameCreateEvent event) {
        GameManager.addGame(event.getGame());
        event.getGame().setScoreboard(Bukkit.getScoreboardManager().getNewScoreboard());

    }

    @EventHandler
    public void gameStartEvent(GameStartEvent event) {
        Game game = event.getGame();
        game.getGamePlayers().forEach((player, gamePlayerDetails) -> gamePlayerDetails.isAlive = true);
    }

    @EventHandler(priority = EventPriority.LOW)
    public void gameStateChangedEvent(GameStateChangeEvent event) {
        GameState newGameState = event.getNewState();
        Game game = event.getGame();

        if (newGameState == GameState.PREGAME_COUNTDOWN) {
            // tp to game
        }
    }

    @EventHandler
    public void gameTimerTickEvent(GameTimerTickEvent event) {
        GameTimer timer = event.getGameTimer();
        Game game = timer.getGame();
        int time = timer.getTime();

        game.getPlayers(Game.Filter.ONLINE).forEach(player -> game.getScoreboardManager().giveScoreboard(player));

        switch (game.getGameState()) {
            case WAITING:
                break;
            case WAITING_COUNTDOWN:
                if (time % 15 == 0 || time <= 5) {
                    if (time == 0) {
                        game.setGameState(GameState.PREGAME_COUNTDOWN);
                        break;
                    } else if (time == 1) {
                        game.broadcast("You will be teleported to the map in " + time + " second!");
                    } else {
                        game.broadcast("You will be teleported to the map in " + time + " seconds!");
                    }
                }
                timer.setTime(time - 1);
                break;
            case PREGAME_COUNTDOWN:
                if (time % 15 == 0 || time <= 5) {
                    if (time == 0) {
                        game.setGameState(GameState.GAME);
                        break;
                    } else if (time == 1) {
                        game.broadcast("The game will start in " + time + " second!");
                    } else {
                        game.broadcast("The game will start in " + time + " seconds!");
                    }
                }
                timer.setTime(time - 1);
                break;
            case GAME:
                break;
            case END:
                if (timer.getEndTime() + game.getGameSettings().endgameTime == time) {
                    game.finalizeGame();
                }
                timer.setTime(time + 1);
                break;
        }
    }

    @EventHandler
    public void gamePlayerDamage(GamePlayerDamageEvent event) {

    }
}