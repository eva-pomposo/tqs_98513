package euromillions;

import java.util.Objects;

import sets.SetOfNaturals;

import java.util.Random;

/**
 * A set of 5 numbers and 2 starts according to the Euromillions ranges.
 *
 * @author ico0
 */
public class Dip {

    static final int MAX_RANGE_STARS = 12;
    static final int MIN_RANGE_STARS = 1;
    static final int STARS_COUNT = 2;

    static final int MAX_RANGE_NUMBERS = 50;
    static final int MIN_RANGE_NUMBERS = 1;
    static final int NUMBERS_COUNT = 5;

    private SetOfNaturals numbers;
    private SetOfNaturals starts;

    public Dip() {
        numbers = new SetOfNaturals();
        starts = new SetOfNaturals();
    }

    public Dip(int[] arrayOfNumbers, int[] arrayOfStarts) {
        this();

        if (NUMBERS_COUNT == arrayOfNumbers.length && STARS_COUNT == arrayOfStarts.length) {
            for (int i : arrayOfStarts) {
                if (i < MIN_RANGE_STARS || i > MAX_RANGE_STARS  ) {
                    throw new IllegalArgumentException("stars out of range");
                }
            }

            for (int i : arrayOfNumbers) {
                if (i < MIN_RANGE_NUMBERS || i > MAX_RANGE_NUMBERS  ) {
                    throw new IllegalArgumentException("numbers out of range");
                }
            }

            numbers.add(arrayOfNumbers);
            starts.add(arrayOfStarts);
        } else {
            throw new IllegalArgumentException("wrong number of elements in numbers/stars");
        }

    }

    public SetOfNaturals getNumbersColl() {
        return numbers;
    }

    public SetOfNaturals getStarsColl() {
        return starts;
    }

    public static Dip generateRandomDip() {
        Random generator = new Random();

        Dip randomDip = new Dip();
        for (int i = 0; i < NUMBERS_COUNT; ) {
            int candidate = generator.nextInt(MAX_RANGE_NUMBERS - 1) + 1;
            if (!randomDip.getNumbersColl().contains(candidate)) {
                randomDip.getNumbersColl().add(candidate);
                i++;
            }
        }
        for (int i = 0; i < STARS_COUNT; ) {
            int candidate = generator.nextInt(MAX_RANGE_STARS - 1) + 1;
            if (!randomDip.getStarsColl().contains(candidate)) {
                randomDip.getStarsColl().add(candidate);
                i++;
            }
        }
        return randomDip;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 29 * hash + Objects.hashCode(this.numbers);
        hash = 29 * hash + Objects.hashCode(this.starts);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Dip other = (Dip) obj;
        if (!Objects.equals(this.numbers, other.numbers)) {
            return false;
        }
        return Objects.equals(this.starts, other.starts);
    }


    /**
     * prepares a string representation of the data structure, formated for
     * printing
     *
     * @return formatted string with data
     */
    public String format() {
        StringBuilder sb = new StringBuilder();
        sb.append("N[");
        for (int number : getNumbersColl()) {
            sb.append(String.format("%3d", number));
        }
        sb.append("] S[");
        for (int star : getStarsColl()) {
            sb.append(String.format("%3d", star));
        }
        sb.append("]");

        return sb.toString();
    }
}
