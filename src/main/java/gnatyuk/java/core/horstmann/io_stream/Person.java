package gnatyuk.java.core.horstmann.io_stream;

import java.io.*;

public class Person implements Serializable {
    private int id;
    public static final Person person_1 = new Person(0);
    public static final Person person_2 = new Person(1);

    private Person(int id) {
        this.id = id;
    }

    protected Object readResolve() {
        if (id == 0) return Person.person_1;
        if (id == 1) return Person.person_1;
        return null;
    }

    public static void main(String[] args) {
        File temp = new File("D:\\temp.data");
        try (ObjectOutputStream objectOutputStream = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(temp)));
        ) {
            objectOutputStream.writeObject(Person.person_1);
            objectOutputStream.close();

            ObjectInputStream objectInputStream = new ObjectInputStream(new BufferedInputStream(new FileInputStream(temp)));
            Person person = (Person) objectInputStream.readObject();
            if (person == Person.person_1) {
                System.out.println("OK!");
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
