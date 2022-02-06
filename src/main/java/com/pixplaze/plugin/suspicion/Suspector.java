package com.pixplaze.plugin.suspicion;

import org.bukkit.GameMode;
import org.bukkit.inventory.ItemStack;
import java.io.Serializable;

/**
 * Класс представляющий подозреваемого игрока.
 * Содержится в сессии подозрения {@link SuspectSession}
 * Поле username - имя подозреваемого игрока.
 * Поле inventoryItems - предметы инвентаря подозреваемого игрока.
 * Поле lastGameMode - режим игры, в котором находился игрок на моменте, когда его заподозрили.
 * Поле info - дополнительная информация о подозрении. {@link Suspector.SuspicionInfo}
 * @see org.bukkit.GameMode
 * @see org.bukkit.inventory.ItemStack
 */
public class Suspector implements Serializable {

    public final String username;
    public final ItemStack[] inventoryItems;
    public final GameMode lastGameMode;
    public final SuspicionInfo info;

    public Suspector(String username, ItemStack[] inventoryItems, GameMode lastGameMode) {
        this.username = username;
        this.inventoryItems = inventoryItems;
        this.lastGameMode = lastGameMode;
        this.info = new SuspicionInfo(System.currentTimeMillis() / 1000L, null);
    }

    /**
     * Класс представляющий информацию о подозрении игрока.
     * Содержится внутри класса Suspector {@link Suspector}.
     * Поле timestamp отвечает за время, когда игрок был заподозрен.
     * Поле reason отвечает за то, в чём игрок был заподозрен и как.
     */
    protected static class SuspicionInfo implements Serializable {

        public final long timestamp;
        public final String reason;

        public SuspicionInfo(long timestamp, String reason) {
            this.timestamp = timestamp;
            this.reason = reason;
        }
    }
}
