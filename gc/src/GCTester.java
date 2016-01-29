import java.util.ArrayList;
import java.util.Random;

public class GCTester {

    /*
    * -Xms6m -Xmx18m -XX:NewSize2m -XX:MaxNewSize2m -XX:PermSize=20 -XX:MaxPermSize=30 -XX:+UseSerialGC
    *
    * -Xms9m -Xmx18m -XX:NewSize3m -XX:MaxNewSize3m -XX:PermSize=40 -XX:MaxPermSize=40 -XX:+UseParallelOldGC
    *
    * -Xms6m -Xmx18m -XX:NewSize2m -XX:MaxNewSize2m -XX:PermSize=20 -XX:MaxPermSize=30 -XX:+UseConcMarkSweepGC
    *
    * -Xms4m -Xmx16m -XX:NewSize2m -XX:MaxNewSize2m -XX:PermSize=12 -XX:MaxPermSize=18 -XX:+UseG1GC
    */


    public static void main(String[] args) throws InterruptedException {
        load();
    }

    protected static void load() throws InterruptedException {
        int arraySize = 10;
        Random random = new Random(1);
        for (int iterator = 1; iterator < 1000; iterator++) {
            System.out.println("Iteration " + iterator + " Free Mem: " + Runtime.getRuntime().freeMemory());
            ArrayList<String> list = new ArrayList<String>(arraySize);
            int i = random.nextInt(10);
            do {
                list.add(String.valueOf(random.nextInt(1000) * 10000));
                i--;
            } while (i > 0);
            arraySize = arraySize * 10;
            long milis = random.nextInt(10) * 100L;
            Thread.sleep(milis);
        }
    }
}