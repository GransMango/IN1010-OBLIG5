public class Subsekvens {
    public final String subsekvens;
    private int forekomster;
    public Subsekvens(String subsekvens) {
        forekomster = 1;
        this.subsekvens = subsekvens;
    }

    public void setForekomster(int forekomster) {
        this.forekomster = forekomster;
    }

    public void increaseForekomster(int amount) {
        this.forekomster += amount;
    }

    public int getForekomster() {
        return forekomster;
    }

    @Override
    public String toString() {
        return "(" + subsekvens + "," + forekomster + ")";
    }
}
