package com.liquidpig.framework;

import java.util.ArrayList;
import java.util.List;

/**
 * A pool for storing mass objects.
 */
public class Pool<T>
{
  /**
   * Factory class for creating objects for use with pool.
   */
  public interface PoolObjectFactory<T>
  {
    /**
     * Creates an object of the factory's type.
     * @return the created object.
     */
    public T createObject();
  }

  private final List<T> freeObjects;
  private final PoolObjectFactory<T> factory;
  private final int maxSize;

  /**
   * Creates a pool with a factory and size for the pool.
   * @param factory is the factory to create the pool's objects with.
   * @param maxSize is the maximum number of objects to keep in the pool.
   */
  public Pool(PoolObjectFactory<T> factory, int maxSize)
  {
    this.factory = factory;
    this.maxSize = maxSize;
    this.freeObjects = new ArrayList<T>(maxSize);
  }

  /**
   * Gets a new object from the pool.
   * @return a new object.
   */
  public T newObject()
  {
    if (freeObjects.size() == 0) return factory.createObject();
    else return freeObjects.remove(freeObjects.size() - 1);
  }

  /**
   * Release the given object back to the pool.
   * @param object is the no longer needed object.
   */
  public void free(T object)
  {
    if (freeObjects.size() < maxSize) freeObjects.add(object);
  }
}
