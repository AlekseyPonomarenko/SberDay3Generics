package first;

public class MyClass1 implements Comparable{
    int i;

    public MyClass1(int i) {
        this.i = i;
    }

    @Override
    public String toString() {
        return "i=" + i;
    }

    @Override
    public boolean equals(Object obj) {

        if (obj == null) {
            return false;
        }

       if (obj instanceof MyClass1 || obj instanceof MyClass2){
           return ((MyClass1)obj).i== i;
       }

       return false;


    }

    @Override
    public int compareTo(Object obj) {

        if (obj instanceof MyClass1 || obj instanceof MyClass2){
            return i - ((MyClass1)obj).i;
        }

        return 0;
    }
}
