import java.util.HashMap;

public class Monitor {
    SubsekvensRegister subsekvensRegisteret;

    public Monitor(SubsekvensRegister subsekvensRegister) {
        subsekvensRegisteret = subsekvensRegister;
    }

    public HashMap<String, Subsekvens> removeHashMap() {
        return subsekvensRegisteret.removeHashMap();
    }

    public void addHashMap(HashMap<String, Subsekvens> map) {
        subsekvensRegisteret.addHashMap(map);
    }

    public HashMap<String, Subsekvens> getHashMap() {
        return subsekvensRegisteret.getHashMap();
    }

    public int getSize() {
        return subsekvensRegisteret.getSize();
    }

    public static HashMap<String, Subsekvens> readFile(String filename) {
        return SubsekvensRegister.readFile(filename);
    }

    public static HashMap<String, Subsekvens> merge(HashMap<String, Subsekvens> map1, HashMap<String, Subsekvens> map2) {
        return SubsekvensRegister.merge(map1, map2);
    }
}

