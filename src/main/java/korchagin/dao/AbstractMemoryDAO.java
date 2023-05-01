package korchagin.dao;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public abstract class AbstractMemoryDAO<I, E extends IdentityInterface<I>> implements DAO<I, E> {

    private final Map<I, List<Object>> inMemoryStorage;

    protected AbstractMemoryDAO() {
        this.inMemoryStorage = new HashMap<>();
    }

    protected void putInMemoryInternal(I identity, Object... params) {
        if (inMemoryStorage.containsKey(identity)) {
            throw new IllegalThreadStateException("Duplicate key - " + identity);
        }

        this.inMemoryStorage.put(identity, List.of(params));
    }

    protected Optional<Object[]> getFromMemory(I identity) {
        if (!this.inMemoryStorage.containsKey(identity)) {
            return Optional.empty();
        }

        return Optional.of(this.inMemoryStorage.get(identity).toArray());
    }
}
