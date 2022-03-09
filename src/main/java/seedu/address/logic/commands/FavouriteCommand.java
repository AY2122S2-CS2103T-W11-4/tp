package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.*;
import seedu.address.model.tag.Tag;

import java.util.List;
import java.util.Set;

/**
 * Changes the favourite status of an existing person in the address book.
 */
public class FavouriteCommand extends Command {

    public static final String COMMAND_WORD = "fav";

    private final Index targetIndex;

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Changes the favourite status of a person identified by the index number used in the displayed person list.\n"
            + "Parameters: INDEX (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " 1";

    public static final String MESSAGE_FAVOURITE_PERSON_SUCCESS = "Added to Favourites Person: %1$s";

    public FavouriteCommand(Index targetIndex) {
        this.targetIndex = targetIndex;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Person> lastShownList = model.getFilteredPersonList();

        if (targetIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }

        // change this xddd
        Person personToFavourite = lastShownList.get(targetIndex.getZeroBased());
        Person favouritedPerson = createFavouritedPerson(personToFavourite);
        model.setPerson(personToFavourite, favouritedPerson);
        return new CommandResult(String.format(MESSAGE_FAVOURITE_PERSON_SUCCESS, favouritedPerson));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof DeleteCommand // instanceof handles nulls
                && targetIndex.equals(((FavouriteCommand) other).targetIndex)); // state check
    }

    private static Person createFavouritedPerson(Person personToFavourite) {
        assert personToFavourite != null;

        Name updatedName = personToFavourite.getName();
        Phone updatedPhone = personToFavourite.getPhone();
        Email updatedEmail = personToFavourite.getEmail();

        Address updatedAddress = personToFavourite.getAddress();
        Favourite updatedFavourite = new Favourite(true); // edit command does not allow editing favourite status
        Set<Tag> updatedTags = personToFavourite.getTags();


        return new Person(updatedName, updatedPhone, updatedEmail, updatedAddress, updatedFavourite, updatedTags);
    }
}
