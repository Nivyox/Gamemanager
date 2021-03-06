package com.nivyox.gamemanager.games.events;

import com.nivyox.gamemanager.games.Game;
import com.nivyox.gamemanager.games.GameState;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;


public class GameStateChangeEvent extends Event {
    private final GameState newState;
    private final GameState oldState;
    private final Game game;

    public GameStateChangeEvent(Game game, GameState oldState, GameState newState) {
        this.game = game;
        this.oldState = oldState;
        this.newState = newState;
    }

    @Override
    public HandlerList getHandlers() {
        return handlers;
    }

    private static HandlerList handlers = new HandlerList();

    public static HandlerList getHandlerList() {
        return handlers;
    }

    public Game getGame() {
        return game;
    }

    public GameState getNewState() {
        return newState;
    }

    public GameState getOldState() {
        return oldState;
    }
}
