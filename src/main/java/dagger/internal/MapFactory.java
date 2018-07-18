package dagger.internal;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;
import javax.inject.Provider;

public final class MapFactory<K, V> implements Factory<Map<K, V>> {
    private static final Provider<Map<Object, Object>> EMPTY = InstanceFactory.create(Collections.emptyMap());
    private final Map<K, Provider<V>> contributingMap;

    public static final class Builder<K, V> {
        private final LinkedHashMap<K, Provider<V>> map;

        private Builder(int size) {
            this.map = DaggerCollections.newLinkedHashMapWithExpectedSize(size);
        }

        public Builder<K, V> put(K key, Provider<V> providerOfValue) {
            this.map.put(Preconditions.checkNotNull(key, "key"), Preconditions.checkNotNull(providerOfValue, "provider"));
            return this;
        }

        public MapFactory<K, V> build() {
            return new MapFactory(this.map);
        }
    }

    public static <K, V> Builder<K, V> builder(int size) {
        return new Builder(size);
    }

    private MapFactory(Map<K, Provider<V>> map) {
        this.contributingMap = Collections.unmodifiableMap(map);
    }

    public Map<K, V> get() {
        Map<K, V> result = DaggerCollections.newLinkedHashMapWithExpectedSize(this.contributingMap.size());
        for (Entry<K, Provider<V>> entry : this.contributingMap.entrySet()) {
            result.put(entry.getKey(), ((Provider) entry.getValue()).get());
        }
        return Collections.unmodifiableMap(result);
    }
}
