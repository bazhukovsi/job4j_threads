package ru.job4j.cashe;

import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.assertj.core.api.Assertions.*;

class CacheTest {

    @Test
    void whenAdd() {
        Cache cache = new Cache();
        Base base1 = new Base(1, 1);
        Base base2 = new Base(2, 2);
        assertThat(cache.add(base1)).isTrue();
        assertThat(cache.add(base1)).isFalse();
        assertThat(cache.add(base2)).isTrue();
        assertThat(cache.getMemory()).isEqualTo(Map.of(1, base1, 2, base2));
    }

    @Test
    void whenUpdate() {
        Cache cache = new Cache();
        Base base1 = new Base(1, 1);
        Base base2 = new Base(2, 2);
        Base base3 = new Base(1, 1);
        cache.add(base1);
        cache.add(base2);
        assertThat(cache.update(base3)).isTrue();
        assertThat(cache.update(new Base(3, 3))).isFalse();
        assertThat(cache.getMemory().get(base1.getId()).getVersion()).isEqualTo(2);
    }

    @Test
    void whenDelete() {
        Cache cache = new Cache();
        Base base1 = new Base(1, 1);
        Base base2 = new Base(2, 2);
        cache.add(base1);
        cache.add(base2);
        cache.delete(base1);
        assertThat(cache.update(new Base(1, 1))).isFalse();
    }

    @Test
    void whenUpdateThenException() {
        Cache cache = new Cache();
        Base base1 = new Base(1, 100);
        cache.add(base1);
        assertThatThrownBy(() -> cache.update(new Base(1, 10)))
                .isInstanceOf(OptimisticException.class);
    }
}