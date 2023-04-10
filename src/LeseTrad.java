public class LeseTrad extends Thread{
    private String filename;
    private Monitor monitor;
    public LeseTrad(String filename, Monitor monitor) {
        this.filename = filename;
        this.monitor = monitor;
    }

    public void run() {
        monitor.addHashMap(Monitor.readFile(filename));
    }
}
