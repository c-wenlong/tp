package seedu.address.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Person's phone number in the address book.
 */
public class Star {

    public static final String MESSAGE_CONSTRAINTS =
            "Stars should only contain numbers.";
    public static final String VALIDATION_REGEX = "\\d{1,}";

    private Integer value;

    /**
     * Constructs a {@code Star}.
     *
     * @param star A valid number.
     */
    public Star(Integer star) {
        requireNonNull(star);
        checkArgument(isValidStar(star), MESSAGE_CONSTRAINTS);
        value = star;
    }

    /**
     * Returns true if a given string is a valid number.
     */
    public static boolean isValidStar(Integer test) {
        return test.toString().matches(VALIDATION_REGEX);
    }

    /**
     * Increments star number.
     * @param starCount number of stars we add to the person.
     */
    public void add(int starCount) {
        this.value+=starCount;
    }

    public Integer getValue() {
        return this.value;
    }

    @Override
    public String toString() {
        return value.toString();
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
        return value.equals(otherPhone.value);
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }
}