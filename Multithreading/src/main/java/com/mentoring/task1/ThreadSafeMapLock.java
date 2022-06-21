package com.mentoring.task1;

import java.util.AbstractMap;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.locks.ReentrantLock;

public class ThreadSafeMapLock<K, V> extends AbstractMap<K, V> implements Map<K, V> {

  private  Map<K, V> map = new HashMap<>();
  private Set entrySet;
  private final ReentrantLock lock = new ReentrantLock();

  @Override
  public V put(K key, V value) {
    lock.lock();
    try {
      return map.put(key, value);
    } finally {
      lock.unlock();
    }
  }

  @Override
  public Set<Entry<K, V>> entrySet() {
    lock.lock();
    try {
      if (entrySet == null) {
        entrySet = new HashSet(map.entrySet());
      }
      return entrySet;
    } finally {
      lock.unlock();
    }
  }
}
