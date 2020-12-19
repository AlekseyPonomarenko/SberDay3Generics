import first.CountMap;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class CountMapImpl <T> implements CountMap <T> {

    HashMap<T, Integer> mainHashmap;

    @Override
    public void add(T o) {
        // добавляет элемент в этот контейнер.

        Integer count = mainHashmap.get(o);
        if (count == null) {
            count = 0;
        };
        mainHashmap.put(o,++count);
    }

    public void add(T o, Integer count) {

        //так сделал намеренно, чтобы не дублировать метод add
        for (int i = 0; i < count; i++) {
            add(o);
        }
    }

    @Override
    public int getCount(T o) {
        //Возвращает количество добавлений данного элемента

        Integer count = mainHashmap.get(o);
        if (count == null) {
            count = 0;
        };

        return count;
    }

    @Override
    public int remove(T o) {
        //Удаляет элемент и контейнера и возвращает количество его добавлений(до удаления)
        Integer count = getCount(o);

        if (count>0){
            mainHashmap.remove(o);
        }
        return count;
    }

    @Override
    public int size() {

        //количество разных элементов
       return mainHashmap.size();
    }

    @Override
    public void addAll(CountMap <? extends T> source) {

        //Добавить все элементы из source в текущий контейнер,
        // при совпадении ключей, суммировать значения
        Map<? extends T, Integer> map = source.toMap();
        for (T key : map.keySet()) {
             add(key, map.get(key));
        }
    }

    @Override
    public Map<T, Integer> toMap() {
        //Вернуть java.util.Map. ключ - добавленный элемент,
        // значение - количество его добавлений
        return mainHashmap;
    }

    @Override
    public void toMap(Map<T, Integer> destination) {
       //Тот же самый контракт как и toMap(), только всю информацию записать в destination
        destination = toMap();
    }

    public CountMapImpl() {
        mainHashmap = new HashMap<>();
    }

    public static void main(String[] args) {

        CountMap<Integer> consumer  = new CountMapImpl<>();
        consumer.add(10);
        consumer.add(10);
        consumer.add(5);
        consumer.add(6);
        consumer.add(5);

        CountMap<Integer> producer  = new CountMapImpl<>();
        producer.add(10);
        producer.add(10);
        producer.add(5);
        producer.add(6);
        producer.add(5);
        System.out.println("до слияния: " + consumer.getCount(5));

        consumer.addAll(producer);
        System.out.println("после слияния: " + consumer.getCount(5));

        //проверка нашего ограничения CountMap <? extends T> source
        //CountMap<String> producer_error  = new CountMapImpl<>();
        //producer_error.add("10");
        //consumer.addAll(producer_error); //всё правильно  - это ошибка

    }


}
