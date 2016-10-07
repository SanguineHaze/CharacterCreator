import java.io.IOException;
import java.util.Scanner;
import java.util.ArrayList;

import java.io.File;


public class ReadFromFile {
	
    private String path;
    
    
    public ReadFromFile(String filePath) {
        path = filePath;
    }

    public ArrayList<String> OpenFile() throws IOException {
        ArrayList<String> output = new ArrayList<String>();
        File fr = new File(path);
        Scanner textReader = new Scanner(fr);

        while(textReader.hasNext()){
            output.add(textReader.nextLine());
        }
        textReader.close();
        return output;
    }

}

//Example String Split for reference use:
/*import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class Main {

    public static void main(String[] args) {        
        try {
            File f = new File("people.ser");
            Scanner sc = new Scanner(f);

            List<Person> people = new ArrayList<Person>();

            while(sc.hasNextLine()){
                String line = sc.nextLine();
                String[] details = line.split(":");
                String gender = details[0];
                String name = details[1];
                int age = Integer.parseInt(details[2]);
                Person p = new Person(gender, name, age);
                people.add(p);
            }

            for(Person p: people){
                System.out.println(p.toString());
            }

        } catch (FileNotFoundException e) {         
            e.printStackTrace();
        }
    }
}

class Person{

    private String gender;
    private String name;
    private int age;

    public Person(String gender, String name, int age){
        this.gender = gender;
        this.setName(name);
        this.age = age;
    }

    *//**
     * @return the gender
     *//*
public String getGender() {
    return gender;
}

*//**
 * @param gender the gender to set
 *//*
public void setGender(String gender) {
    this.gender = gender;
}

*//**
 * @param name the name to set
 *//*
public void setName(String name) {
    this.name = name;
}

*//**
 * @return the name
 *//*
public String getName() {
    return name;
}

*//**
 * @return the age
 *//*
public int getAge() {
    return age;
}

*//**
 * @param age the age to set
 *//*
public void setAge(int age) {
    this.age = age;
}

public String toString(){
    return this.gender + " " + this.name + " " + this.age;
}


}*/