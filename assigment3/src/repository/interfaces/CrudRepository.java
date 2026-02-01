package repository.interfaces;

public interface CrudRepository<T> {
    void create(T t);
    T getById(int id);
    void update(T t);
    void delete(int id);
}
