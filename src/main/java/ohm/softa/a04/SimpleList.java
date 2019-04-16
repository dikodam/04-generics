package ohm.softa.a04;

import java.util.function.Function;

public interface SimpleList<T> extends Iterable<T> {
    /**
     * Add a given object to the back of the list.
     */
    void add(T o);
    
    /**
     * @return current size of the list
     */
    int size();
    
    T get(int index);
    
    void addDefault(Class<T> klass);
    
    /**
     * Get a new SimpleList instance with all items of this list which match the given filter
     *
     * @param filter SimpleFilter instance
     * @return new SimpleList instance
     */
    @SuppressWarnings("unchecked")
    default SimpleList<T> filter(SimpleFilter<T> filter) {
        try {
            SimpleList<T> result = this.getClass().newInstance();
            for (T o : this) {
                if (filter.include(o)) {
                    result.add(o);
                }
            }
            return result;
        } catch (InstantiationException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }
    
    @SuppressWarnings("unchecked")
    default <R> SimpleList<R> map(Function<T, R> transform) {
        try {
            SimpleList<R> result = this.getClass().newInstance();
            for (T item : this) {
                result.add(transform.apply(item));
            }
            return result;
        } catch (InstantiationException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }
}
