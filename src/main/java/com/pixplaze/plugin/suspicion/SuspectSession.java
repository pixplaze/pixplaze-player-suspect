package com.pixplaze.plugin.suspicion;

import com.google.common.base.Charsets;
import com.google.common.io.Files;
import com.google.gson.reflect.TypeToken;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import com.pixplaze.exceptions.NoSuchSuspectorException;
import com.pixplaze.serialization.SuspectorDeserializer;
import com.pixplaze.serialization.SuspectorSerializer;
import com.pixplaze.serialization.SuspicionInfoDeserializer;
import com.pixplaze.serialization.SuspicionInfoSerializer;
import org.bukkit.GameMode;
import org.bukkit.Server;
import org.bukkit.entity.Player;

import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.logging.Logger;

import com.pixplaze.plugin.PixplazePlayerSuspect;

import static com.pixplaze.util.Collections.pop;
import static com.pixplaze.util.Common.notnull;
import static com.pixplaze.util.Common.nullable;


public class SuspectSession implements SuspicionExecutor {

    protected static final Logger logger;
    protected static final Server server;
    protected static final File file;
    protected static final Gson gson;

    static {
        logger = PixplazePlayerSuspect.getInstance().logger;
        server = PixplazePlayerSuspect.getInstance().getServer();
        file = new File(PixplazePlayerSuspect.getInstance().getDataFolder().getAbsolutePath() + "/suspectors.json");
        gson = new GsonBuilder()
                .setPrettyPrinting()
                .registerTypeAdapter(Suspector.class, new SuspectorSerializer())
                .registerTypeAdapter(Suspector.class, new SuspectorDeserializer())
                .registerTypeAdapter(Suspector.SuspicionInfo.class, new SuspicionInfoSerializer())
                .registerTypeAdapter(Suspector.SuspicionInfo.class, new SuspicionInfoDeserializer())
                .create();
    }

    private final Set<Suspector> suspectors;
    private final Suspector.SuspicionInfo info;

    public SuspectSession(String reason) {
        var timestamp = System.currentTimeMillis() / 1000L;
        this.info = new Suspector.SuspicionInfo(reason, timestamp);
        suspectors = loadSuspectors();
    }

    public SuspectSession() {
        this(null);
    }

    @Override
    public void suspectPlayer(Player player) {
        var inventory = player.getInventory();
        var itemStack = inventory.getContents();

        var suspector = new Suspector(
            player.getName(), player.getGameMode(),
            Arrays.copyOf(itemStack, itemStack.length),
            this.info
        );

        this.suspectors.add(suspector);

        player.setGameMode(GameMode.ADVENTURE);
        inventory.clear();
    }

    @Override
    public void desuspectPlayer(Player player) {
        var desired = new Suspector(player.getName());
        try {
            desired = pop(desired, suspectors);
            player.setGameMode(desired.lastGameMode);
            notnull(desired.inventoryItems, s -> player.getInventory().setContents(s));
        } catch (NoSuchElementException e) {
            throw new NoSuchSuspectorException();
        }
    }

    @Override
    public void saveSuspectors() {
        try {
            Set<Suspector> savedSuspectors = new HashSet<>(suspectors);
            var json = gson.toJson(savedSuspectors);
            Files.asCharSink(file, Charsets.UTF_8).write(json);
            logger.info(json);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Set<Suspector> loadSuspectors() {
        var suspectorType = new TypeToken<Set<Suspector>>(){}.getType();
        var suspectors = new HashSet<Suspector>();
        try {
            if (!file.createNewFile()) { // If file was exist, load from file, else skip.
                var json = Files.asCharSource(file, Charsets.UTF_8).read();
                suspectors = gson.fromJson(json, suspectorType);
                // Если в json ничего не было записано, создать новый HashSet<>, иначе - загрузить из json
                suspectors = nullable(suspectors, s -> s, new HashSet<>());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return suspectors;
    }
}
