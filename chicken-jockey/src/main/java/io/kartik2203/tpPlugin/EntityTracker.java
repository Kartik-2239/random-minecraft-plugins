package io.kartik2203.tpPlugin;

import org.bukkit.entity.Entity;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

public class EntityTracker {

    private final Set<UUID> processedEntities = new HashSet<>();

    public boolean isProcessed(Entity entity) {
        return processedEntities.contains(entity.getUniqueId());
    }

    public void markProcessed(Entity entity) {
        processedEntities.add(entity.getUniqueId());
    }
}
