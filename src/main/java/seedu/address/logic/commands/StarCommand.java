package seedu.address.logic.commands;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_STAR;

import java.util.List;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.Messages;
import seedu.address.model.Model;
import seedu.address.model.person.Person;
import seedu.address.model.person.Star;

/**
 * Adds a star to a student in the address book.
 * @code star 1 s/3
 */
public class StarCommand extends Command {

    public static final String COMMAND_WORD = "star";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Edits the remark of the person identified "
            + "by the index number used in the last person listing. "
            + "Existing remark will be overwritten by the input.\n"
            + "Parameters: INDEX (must be a positive integer) "
            + PREFIX_STAR + "[REMARK]\n"
            + "Example: " + COMMAND_WORD + " 1 "
            + PREFIX_STAR + "3";

    public static final String MESSAGE_ARGUMENTS = "Index: %1$d";
    public static final String MESSAGE_ADD_STAR_SUCCESS = "Added stars to Person: %1$d";
    public static final String MESSAGE_ADD_STAR_FAILURE = "Unable to add stars to Person: %1$d";

    private final Index index; // Index to give star
    private final Star star; // Number of stars given

    /**
     * @param index of the person in the filtered person list to give the star.
     * @param star number of stars given to student.
     */
    public StarCommand(Index index, Star star) {
        requireAllNonNull(index, star);
        this.index = index;
        this.star = star;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException { // executes the starring
        List<Person> lastShownList = model.getFilteredPersonList(); // get the list of persons

        if (index.getZeroBased() >= lastShownList.size()) { // if index out of range
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }

        Person personToEdit = lastShownList.get(index.getZeroBased()); // get person indexed
        Star star = personToEdit.getStar();
        star.add(star.getValue()); // update star
        Person editedPerson = new Person(
                personToEdit.getName(), personToEdit.getPhone(), personToEdit.getEmail(),
                personToEdit.getAddress(), star, personToEdit.getTags());

        model.setPerson(personToEdit, editedPerson);
        model.updateFilteredPersonList(Model.PREDICATE_SHOW_ALL_PERSONS);

        return new CommandResult(generateSuccessMessage(editedPerson));
    }

    /**
     * Generates a command execution success message based on whether
     * the star(s) is added to or removed from
     * {@code personToEdit}.
     */
    private String generateSuccessMessage(Person personToEdit) {
        String message = !(star.getValue() > 0) ? MESSAGE_ADD_STAR_SUCCESS : MESSAGE_ADD_STAR_FAILURE;
        return String.format(message, personToEdit);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof StarCommand)) {
            return false;
        }

        StarCommand e = (StarCommand) other;
        return index.equals(e.index);
    }
}
