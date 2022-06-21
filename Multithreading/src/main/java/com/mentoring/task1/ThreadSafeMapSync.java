package com.mentoring.task1;

import java.util.AbstractMap;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class ThreadSafeMapSync<K, V> extends AbstractMap<K, V> implements Map<K, V> {

  private Map<K, V> map = new HashMap<>();
  private Set entrySet;

  @Override
  synchronized public V put(K key, V value) {
      return map.put(key, value);
  }

  @Override
  synchronized public Set<Entry<K, V>> entrySet() {
      if (entrySet == null) {
        entrySet = new HashSet(map.entrySet());
      }
      return entrySet;
  }
}
