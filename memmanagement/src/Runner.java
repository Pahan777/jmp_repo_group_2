import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Runner {


    public static void main(String[] args) throws Exception {

        Runner runner = new Runner();
        runner.generateOOMheapSize();
        runner.generateOOMpermGen();
    }

    public void generateOOMheapSize() throws Exception {
        int arraySize = 10;
        for (int iterator = 1; iterator < 100; iterator++) {
            System.out.println("Iteration " + iterator + " Free Mem: " + Runtime.getRuntime().freeMemory());
            int i = 5;
            int[] intArr = new int[arraySize];
            do {
                intArr[i] = 0;
                i--;
            } while (i > 0);
            arraySize = arraySize * 10;
            Thread.sleep(1000);
        }
    }

    public void generateOOMpermGen() throws Exception {
        Random rnd = new Random();
        List<String> interned = new ArrayList<String>();
        for (int i = 0; i < 1000000000; i++) {
            int length = rnd.nextInt(100);
            StringBuilder builder = new StringBuilder();
            String chars = "abcdefghijklmnopqrstuvwxyz";
            for ( int j = 0; j < length; j++ ) {
                builder.append(chars.charAt(rnd.nextInt(chars.length())));
            }
            interned.add(builder.toString().intern());
        }
    }
}