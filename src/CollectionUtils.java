import first.MyClass1;
import first.MyClass2;

import java.util.*;

public class CollectionUtils {

    public static<T> void addAll(List<? extends T> source, List<? super T> destination){
        destination.addAll(source);
    }

    public static <T>  List newArrayList() {
        return new ArrayList<T>();
    }

    public static <T> int indexOf(List<? extends T> source, T o) {
        int index = source.indexOf(o);
        if (index == -1){
            for (T t : source) {
                if (t.equals(o)){
                    return source.indexOf(t);
                }
            }
        };
        return index;
    }

    public static <T> List limit(List<? extends T> source, int size) {
        //смысл limit не понятен, пусть будет  - получить новый список с определенным размером

        List<T> newArray = newArrayList();
        int newSize = 0;
        for (T e : source) {
            if (newArray.size() >= size) {
                break;
            }
            add(newArray, e);
        }
        return newArray;
    }

    public static <T> void add(List <? super T> source, T o) {
        source.add(o);
    }

    public static <T> void removeAll(List<? super T> removeFrom, List<? extends T> c2) {

        List<? super T> listTmp = newArrayList();

        for (T e: c2){
            int index = indexOf(removeFrom, e);
            if (index>-1){
                removeFrom.remove(index);
            };
        }
    }
    //true если первый лист содержит все элементы второго
    public static <T> boolean containsAll(List<? super T> c1, List<? extends T> c2) {

        for (T e : c2) {
            int index = indexOf(c1, e);
            if (index == -1) {
                return false;
            }
            ;
        }

        return true;
    }

    //true если первый лист содержит хотя-бы 1 второго
    public static <T> boolean containsAny(List<? super T> c1, List<? extends T> c2) {
        for (T e : c2) {
            int index = indexOf(c1, e);
            if (index > -1) {
                return true;
            }
            ;
        }

        return false;
    }

    //Возвращает лист, содержащий элементы из входного листа в диапазоне от min до max.
    // Элементы сравнивать через Comparable.
    // Прмер range(Arrays.asList(8,1,3,5,6, 4), 3, 6) вернет {3,4,5,6}
    public static <T extends Comparable> List range(List<? extends T> list, T min, T max) {

        List<T> newList = newArrayList();
        Collections.sort(list);

        for (T e: list){
            if (e.compareTo(min)>0 &&e.compareTo(max)<0){
                add(newList, e);
            }
        }
        return newList;
    }


    //Возвращает лист, содержащий элементы из входного листа в диапазоне от min до max.
    // Элементы сравнивать через Comparable.
    // Прмер range(Arrays.asList(8,1,3,5,6, 4), 3, 6) вернет {3,4,5,6}
    public static <T>List range(List<? extends T> list, T min, T max, Comparator comparator) {

        List<T> newList = newArrayList();
        Collections.sort(list, comparator);



        for (T e: list){
            if (comparator.compare(e,min)>0 && comparator.compare(e, max)<0){
                add(newList, e);
            }
        }
        return newList;
    }


    public static <T> void showList(List<T> list, String nameOfList){
        System.out.print(nameOfList);
        System.out.print("{");
        for (T e: list) {
            System.out.print(" " + e + " ");
        }
        System.out.println("}");

    }


    public static void main(String[] args) {

        List<MyClass1> arrayList1 = newArrayList();
        arrayList1.add(new MyClass1(1));
        arrayList1.add(new MyClass1(2));
        arrayList1.add(new MyClass1(3));
        arrayList1.add(new MyClass1(4));
        arrayList1.add(new MyClass1(5));
        arrayList1.add(new MyClass1(0));
        arrayList1.add(new MyClass1(150));
        arrayList1.add(new MyClass1(50));

        showList(arrayList1, "arrayList1");

        List<MyClass2> arrayList2 = newArrayList();
        arrayList2.add(new MyClass2(1));
        arrayList2.add(new MyClass2(2));
        arrayList2.add(new MyClass2(3));

        showList(arrayList2, "arrayList2");

        System.out.print("limit 2: ");
        showList(limit(arrayList1,2), "limitArray");


        System.out.print("containsAll: ");
        System.out.print(containsAll(arrayList1, arrayList2) + "\n");

        System.out.println("removeAll:");
        removeAll(arrayList1, arrayList2);
        showList(arrayList1, "arrayList1");

        System.out.print("containsAll: ");
        System.out.print(containsAll(arrayList1, arrayList2) + "\n");

        System.out.print("range 0 100: ");
        showList(range(arrayList1, new MyClass1(0), new MyClass1(100)), "new arrayList1");

        Comparator<? extends Comparable> comparator = new Comparator<Comparable>() {
            @Override
            public int compare(Comparable o1, Comparable o2) {
                return o1.compareTo(o2);
            }
        };
        System.out.print("range with comparator 0 100: ");
        showList(range(arrayList1, new MyClass1(0), new MyClass1(100), comparator), "new arrayList1");

    }
}
