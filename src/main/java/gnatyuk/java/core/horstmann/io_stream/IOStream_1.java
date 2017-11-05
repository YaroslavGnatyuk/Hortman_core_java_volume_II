package gnatyuk.java.core.horstmann.io_stream;

import gnatyuk.java.core.streams.DemoClass;

import java.io.*;
import java.util.logging.Logger;

public class IOStream_1 {
    private final static Logger loger = Logger.getAnonymousLogger();
    public static void main(String[] args) {
        String fromGitlab = "solution-release10EP.4.03";
        String fromPlugin = "DSG_release_branch_10.ep4.03";

        System.out.println(fromGitlab = fromGitlab.replaceAll("\\D+",""));
        System.out.println(fromPlugin = fromPlugin.replaceAll("\\D+",""));

        if(fromGitlab.equals(fromPlugin)){
            System.out.println("It is the same version!");
        }
    }

    private static void readInBuffer() {
        String path = "/home/yaroslav/Documents/dump.zip";
        try {
            DataInputStream dataInputStream = new DataInputStream(new BufferedInputStream(new FileInputStream(path)));

            double l = dataInputStream.readDouble();
            loger.warning(l + "");

            l = dataInputStream.readDouble();
            loger.warning(l + "");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void writeByWriter() {
        String path = "/home/yaroslav/Documents/";
        String filename = "test_file.txt";
        try(OutputStreamWriter outputStreamWriter = new FileWriter(path+filename,true)){
            String textForWriting = "That is my first experience in outputWriteStream\n";
            outputStreamWriter.write(textForWriting);
            outputStreamWriter.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private static void writingBinaryData() {
        String path = "/home/yaroslav/Documents/";

        try(DataOutputStream dataOutputStream = new DataOutputStream(new BufferedOutputStream(new FileOutputStream(path+"binary_data.txt")))){
            dataOutputStream.writeInt(11);
            dataOutputStream.writeBoolean(true);
            dataOutputStream.writeChars("aaa");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void objectSerialisation() {
        String path = "/home/yaroslav/Documents/";

       /* DemoClass.Person person_0 = new DemoClass.Person(23, "Albert");
        DemoClass.Person person_1 = new DemoClass.Person(24, "Anna");
        DemoClass.Person person_2 = new DemoClass.Person(25, "Anzhelika");
        DemoClass.Person person_3 = new DemoClass.Person(26, "Aurika");

        try(ObjectOutputStream objectOutputStream = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(path + "object_serialization_1.data")))) {

            objectOutputStream.writeObject(person_0);
            objectOutputStream.writeObject(person_1);
            objectOutputStream.writeObject(person_2);
            objectOutputStream.writeObject(person_3);

            objectOutputStream.flush();
            objectOutputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }*/

        try(ObjectInputStream objectInputStream = new ObjectInputStream(new BufferedInputStream(new FileInputStream(path+"object_serialization_1.data")))){
            /*DemoClass.Person p_1 = (DemoClass.Person) objectInputStream.readObject();
            System.out.println(p_1);
            p_1 = (DemoClass.Person) objectInputStream.readObject();
            System.out.println(p_1);
            p_1 = (DemoClass.Person) objectInputStream.readObject();
            System.out.println(p_1);
            p_1 = (DemoClass.Person) objectInputStream.readObject();
            System.out.println(p_1);
            p_1 = (DemoClass.Person) objectInputStream.readObject();
            System.out.println(p_1);*/
            while(objectInputStream.available()>0){
                System.out.println("!!!");
                DemoClass.Person p_1 = (DemoClass.Person) objectInputStream.readObject();
                System.out.println(p_1);
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
