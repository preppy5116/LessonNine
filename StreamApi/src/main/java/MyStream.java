import java.util.*;
import java.util.function.Function;
import java.util.function.Predicate;
public class MyStream<T> {
    public Collection<? extends T> collection;

    private MyStream(Collection<? extends T> collection) {
        this.collection = new ArrayList<>(collection);
    }

    public static <T> MyStream<T> of(Collection<? extends T> collection) {
        return new MyStream<>(collection);
    }

    public MyStream<T> filter(Predicate<? super T> predicate) {
        List<T> list = new ArrayList<T>();
        for (T element : collection)
            if (predicate.test(element))
                list.add(element);
        return MyStream.of(list);
    }

    public <K, V> Map<K, V> toMap(Function<? super T, ? extends K> funcKey, Function<? super T, ? extends V> funcValue) {
        Map<K, V> map = new HashMap<>();
        for (T element : collection)
            map.put(funcKey.apply(element), funcValue.apply(element));
        return map;
    }

    public <R> MyStream<R> transform(Function<? super T, ? extends R> function) {
        List<R> list = new ArrayList<>();
        for (T element : collection)
            list.add(function.apply(element));
        return MyStream.of(list);
    }
}

