import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;

public class Oblig5Del1 {
    public static void main(String[] args) {
        SubsekvensRegister subsekvensRegisterLiten = new SubsekvensRegister();
        SubsekvensRegister subsekvensRegisterLike = new SubsekvensRegister();
        Monitor monitorLike = new Monitor(subsekvensRegisterLike);
        Monitor monitorLiten = new Monitor(subsekvensRegisterLiten);
        readFile(monitorLike, "ext filer/testdatalike/TestDataLike/");
        readFile(monitorLiten, "ext filer/testdataliten/TestDataLiten/");
        mergeAll(monitorLike);
        mergeAll(monitorLiten);
    }

    public static void readFile(Monitor monitor, String path) {
        File file = new File(path + "metadata.csv");
        ArrayList<Thread> threads = new ArrayList<>();
        try (Scanner scanner = new Scanner(file)) {
            while(scanner.hasNextLine()) {
                String[] line = scanner.nextLine().split(",");
                threads.add(new LeseTrad((path + line[0]), monitor));
            }
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }
        for (Thread thread : threads) {
            thread.start();
        }
        for (Thread thread : threads) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

    }

    public static void mergeAll(Monitor monitor) {
        while (monitor.getSize() > 1) {
            HashMap<String,Subsekvens> map1 = monitor.removeHashMap();
            HashMap<String,Subsekvens> map2 = monitor.removeHashMap();
            HashMap<String,Subsekvens> mergedMap = Monitor.merge(map1, map2);
            monitor.addHashMap(mergedMap);
            System.out.println(monitor.getSize());
        }
        int storsteForekomst = 0;
        Subsekvens storsteSubsekvens = null;
        for (String key : monitor.getHashMap().keySet()) {
            Subsekvens current = monitor.getHashMap().get(key);
            if (current.getForekomster() > storsteForekomst) {
                storsteSubsekvens = current;
                storsteForekomst = current.getForekomster();
            }
        }
        System.out.println(storsteSubsekvens);
    }
}
