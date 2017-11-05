import java.io.EOFException;
import java.io.IOException;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;

/**
 * Created by yaroslav on 22.03.16.
 */
public class JavaCore {
    public static void main(String[] args) {
        Map<Integer, String> map = new TreeMap<Integer, String>();
        for (int i = 0; i < 100; i++) {
            map.put(i,"my namber is "+ i);
        }

        Iterator i = map.entrySet().iterator();


    }

    public static void decrement(Integer i){
        i--;
    }

    public static void changeObj(Person sc){
        sc.setAddress("USA");
        sc.setAge(44);
        sc.setName("John");
    }
}

class Person {
    int age;
    String name;
    String address;
    static int count = 0;

    public Person(int age, String name, String address) {
        count++;
        this.age = age;
        this.name = name;
        this.address = address;
    }

    public Person() {
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    protected String getName() throws IOException{
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "Person{" +
                "age=" + age +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                '}';
    }


    public static void getCount()throws IOException{
        System.out.println(count);
        throw new EOFException();
    }
}

class ChildPerson extends Person{

    public ChildPerson(int age, String name, String address) {
        super(age, name, address);
    }

    @Override
    public String getName() throws EOFException{
        return "something else";
    }


}