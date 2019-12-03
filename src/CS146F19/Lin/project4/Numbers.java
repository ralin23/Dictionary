package CS146F19.Lin.project4;

public class Numbers implements Comparable<Numbers> {
    private int number;

    public Numbers(int number) {
        this.number = number;
    }

    @Override
    public int compareTo(Numbers o) {
        return o.number - number;
    }

    @Override
    public String toString() {
        return "Number: " + number + System.lineSeparator();
    }
}
