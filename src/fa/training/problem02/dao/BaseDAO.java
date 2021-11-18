package fa.training.problem02.dao;

import java.util.ArrayList;

public interface BaseDAO <T, K>{
   public ArrayList<T> findAll();
   public T findById(K key);
   public boolean deleteById(K key);
   public boolean save(T obj);
}
