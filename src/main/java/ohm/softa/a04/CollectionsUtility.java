package ohm.softa.a04;

import java.util.Comparator;

public abstract class CollectionsUtility {
    private CollectionsUtility() {}
    
    
    // extrem hochperformante O(n^3) oder so Lösung
    public static <T> SimpleList<T> bubblesort(SimpleList<T> list, Comparator<T> comparator) {
        SimpleListImpl<Object> sortedList = new SimpleListImpl<>();
        
    }
}
