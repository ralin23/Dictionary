package cs146F19.Lin.project4;

/**
 * Sample Number class for use in {@link RBTTester#genericTest()}.
 */
public class Number implements Comparable<Number> {
    private int number;

    /**
     * Creates a number class
     *
     * @param number an integer
     */
    public Number(int number) {
        this.number = number;
    }

    /**
     * Compares two numbers
     *
     * @param o number to compare against
     * @return the difference between two numbers
     */
    @Override
    public int compareTo(Number o) {
        return o.number - number;
    }

    /**
     * Outputs the data of this number to a String
     *
     * @return a String containing data to be printed out
     */
    @Override
    public String toString() {
        return "Number: " + number;
    }
}
