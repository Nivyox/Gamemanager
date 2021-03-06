package com.nivyox.gamemanager.games.events;

import com.nivyox.gamemanager.games.Game;
import com.nivyox.gamemanager.games.RemoveReason;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class GamePlayerRemoveEvent extends Event {
    private final Player player;
    private RemoveReason removereason;
    private final Game game;

    public GamePlayerRemoveEvent(Game game, Player player, RemoveReason removereason) {
        this.game = game;
        this.player = player;
        this.removereason = removereason;
    }

    public Game getGame() {
        return game;
    }

    public Player getPlayer() {
        return player;
    }

    @Override
    public HandlerList getHandlers() {
        return handlers;
    }

    private static HandlerList handlers = new HandlerList();

    public static HandlerList getHandlerList() {
        return handlers;
    }

    public RemoveReason getRemovereason() {
        return removereason;
    }
}
