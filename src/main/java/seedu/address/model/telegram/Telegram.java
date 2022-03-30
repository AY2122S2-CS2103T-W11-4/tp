package seedu.address.model.telegram;

import static seedu.address.commons.util.AppUtil.checkArgument;

import java.util.Optional;


/**
 * Represents a Person's address in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidHandle(String)}
 */
public class Telegram {

    public static final String MESSAGE_CONSTRAINTS = "Following telegram's restrictions,"
            + " usernames must be 5 to 32 characters long and not ending with an underscore.";

    /*
     * The first character of the address must not be a whitespace,
     * otherwise " " (a blank string) becomes a valid input.
     */
    public static final String VALIDATION_REGEX = "@\\w{5,32}(?<!_)";

    public final Optional<String> value;

    /**
     * Constructs an {@code Telegram}.
     *
     * @param telegram A valid address.
     */
    public Telegram(Optional<String> telegram) {
        if (telegram.isPresent()) {
            checkArgument(isValidHandle(telegram.get()), MESSAGE_CONSTRAINTS);
            this.value = telegram;
        } else {
            this.value = Optional.empty();
        }
    }

    /**
     * Returns true if a given string is a valid address.
     */
    public static boolean isValidHandle(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return value.orElse("--NIL--");
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Telegram // instanceof handles nulls
                && value.orElse("--NIL--").equals(((Telegram) other).value.orElse("--NIL--"))); // state check
    }

    @Override
    public int hashCode() {
        return value.orElse("--NIL--").hashCode();
    }
}
