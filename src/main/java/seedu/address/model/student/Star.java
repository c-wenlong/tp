package seedu.address.model.student;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Student's stars in the address book.
 */
public class Star implements Comparable<Star> {

    public static final String MESSAGE_CONSTRAINTS =
            "Invalid Number of Stars: \nStar should be more than 0 and less than 10.";
    public static final String MESSAGE_CONSTRAINTS_EDIT =
            "Invalid Number of Stars: \nStar should not be negative and should be less than 10.";

    public static final Star NO_STAR = new Star();

    public final Integer numOfStars; // number of stars given to a student

    /**
     * Constructs a {@code Star}.
     *
     * @param numOfStars A valid number.
     */
    public Star(Integer numOfStars) {
        requireNonNull(numOfStars);
        checkArgument(isValidStar(numOfStars), MESSAGE_CONSTRAINTS);
        this.numOfStars = numOfStars;
    }

    /**
     * Constructs a star of NO_STAR and to allow editing of stars to 0 in EditCommand.
     */
    public Star() {
        this.numOfStars = 0;
    }

    /**
     * Returns true if a given string is a valid number.
     */
    public static boolean isValidStar(Integer numOfStars) {
        return (numOfStars > 0 && numOfStars <= 10);
    }

    @Override
    public String toString() {
        return this.numOfStars.toString();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof Star)) {
            return false;
        }

        Star otherPhone = (Star) other;
        return numOfStars.equals(otherPhone.numOfStars);
    }

    @Override
    public int compareTo(Star other) {
        int otherStars = other.numOfStars;
        return this.numOfStars.compareTo(otherStars);
    }

    @Override
    public int hashCode() {
        return numOfStars.hashCode();
    }
}
