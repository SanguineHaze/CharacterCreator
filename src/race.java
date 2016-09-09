import java.io.IOException;
import java.util.ArrayList;

public class race {


    ArrayList<String> raceList = new ArrayList<String>(); //don't declare vectors with a size to avoid declaring too big if the file changes

     race() throws IOException {
        String targetFile = "C:/users/dylanc/Desktop/test.txt"; //path to the file on local environment.

        try {
            ReadFromFile file = new ReadFromFile(targetFile);
            raceList = file.OpenFile();

            raceList.sort(null); // Fun fact about vectors, they can do sorts for you, uncommenting this will put stuff in alphabetical order.

            for(String out: raceList){
                System.out.println(out);
            }
        }
        
        catch (IOException e) {
            //Default error message
            System.out.println(e.getMessage());

            //Let's make our own error
            //System.out.println("ERROR: Dude, where's my file?! Better check that spelling!");
        } //End catch

    }//End race()

    public static void main(String[] args) {
        try {
            race thisRace = new race();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
    }// End main()

}// End class
