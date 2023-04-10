import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

public class SubsekvensRegister {
    private final List<HashMap<String,Subsekvens>> hashBeholder;
    public SubsekvensRegister() {
        hashBeholder = new ArrayList<>();
    }

    public void addHashMap(HashMap<String,Subsekvens> map) {
        hashBeholder.add(map);
    }

    public HashMap<String,Subsekvens> removeHashMap() {
        return hashBeholder.remove(hashBeholder.size()-1);
    }

    public HashMap<String,Subsekvens> getHashMap() {
        return hashBeholder.get(0);
    }

    public int getSize() {
        return hashBeholder.size();
    }

    public static HashMap<String,Subsekvens> readFile(String filename) {
        File file = new File(filename);
        HashMap<String,Subsekvens> hashMap = new HashMap<>();
        try (Scanner scanner = new Scanner(file)) {
            while(scanner.hasNextLine()) {
                String line = scanner.nextLine();
                for (int i = 0; i < line.length()-2; i++) {
                    String subsekvens = line.substring(i, i+3);
                    hashMap.putIfAbsent(subsekvens, new Subsekvens(subsekvens));
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }
        return hashMap;
    }

    public static HashMap<String,Subsekvens> merge(HashMap<String,Subsekvens> map1, HashMap<String,Subsekvens> map2) {
        for (String key : map1.keySet()) {
            if (map2.containsKey(key)) {
                map2.get(key).increaseForekomster(map1.get(key).getForekomster());
            } else {
                map2.put(key, map1.get(key));
            }
        }
        return map2;
    }
}
