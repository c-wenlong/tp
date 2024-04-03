package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import java.util.Arrays;

import seedu.address.logic.commands.FindCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.student.predicates.BoltWithinBoundsPredicate;
import seedu.address.model.student.predicates.MajorContainsSubStringPredicate;
import seedu.address.model.student.predicates.NameContainsKeywordsPredicate;
import seedu.address.model.student.predicates.StarWithinBoundsPredicate;
import seedu.address.model.student.predicates.TagContainsSubStringPredicate;

/**
 * Parses input arguments and creates a new FindCommand object
 */
public class FindCommandParser implements Parser<FindCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the FindCommand
     * and returns a FindCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public FindCommand parse(String args) throws ParseException {
        String[] parts = args.trim().split("\\s+");
        String field = parts[0].trim();
        String argsToMatch = args.trim().substring(field.length()).trim();

        switch (field) {
        case "name":
            return findNameParser(argsToMatch);

        case "major":
            return findMajorParser(argsToMatch);

        case "star":
            return findStarParser(argsToMatch);

        case "bolt":
            return findBoltParser(argsToMatch);

        case "tag":
            return findTagParser(argsToMatch);

        default:
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
        }
    }

    /**
     * Parses the given {@code String} of arguments in the context of the FindCommand (by name)
     * and returns a FindCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
    */
    public FindCommand findNameParser(String args) throws ParseException {
        if (args.isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    FindCommand.getSpecificMessageUsage("name")));
        }
        String[] nameKeywords = args.split("\\s+");
        return new FindCommand(new NameContainsKeywordsPredicate(Arrays.asList(nameKeywords)));
    }

    /**
     * Parses the given {@code String} of arguments in the context of the FindCommand (by major)
     * and returns a FindCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    public FindCommand findMajorParser(String args) throws ParseException {
        if (args.isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    FindCommand.getSpecificMessageUsage("major")));
        }
        return new FindCommand(new MajorContainsSubStringPredicate(args));
    }

    /**
     * Parses the given {@code String} of arguments in the context of the FindCommand (by star)
     * and returns a FindCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    public FindCommand findStarParser(String args) throws ParseException {
        try {
            String[] starArgs = args.split(" ", 2);
            String sOperator = starArgs[0];
            Integer sOperand = Integer.parseInt(starArgs[1].trim());

            if (isInvalidOperator(sOperator) || sOperand < 0) {
                throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                        FindCommand.getSpecificMessageUsage("star")));
            }

            return new FindCommand(new StarWithinBoundsPredicate(sOperator, sOperand));
        } catch (IndexOutOfBoundsException | NumberFormatException e) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    FindCommand.getSpecificMessageUsage("star")));
        }
    }

    /**
     * Parses the given {@code String} of arguments in the context of the FindCommand (by bolt)
     * and returns a FindCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    public FindCommand findBoltParser(String args) throws ParseException {
        try {
            String[] boltArgs = args.split(" ", 2);
            String bOperator = boltArgs[0];
            Integer bOperand = Integer.parseInt(boltArgs[1].trim());

            if (isInvalidOperator(bOperator) || bOperand < 0) {
                throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                        FindCommand.getSpecificMessageUsage("bolt")));
            }

            return new FindCommand(new BoltWithinBoundsPredicate(bOperator, bOperand));
        } catch (IndexOutOfBoundsException | NumberFormatException e) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    FindCommand.getSpecificMessageUsage("bolt")));
        }
    }

    /**
     * Parses the given {@code String} of arguments in the context of the FindCommand (by tag)
     * and returns a FindCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    public FindCommand findTagParser(String args) throws ParseException {
        if (args.isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    FindCommand.getSpecificMessageUsage("tag")));
        }
        return new FindCommand(new TagContainsSubStringPredicate(args));
    }

    /**
     * Checks if the operator is valid.
     *
     * @param operator
     * @return
     */
    public boolean isInvalidOperator(String operator) {
        return !operator.equals("<")
                && !operator.equals(">")
                && !operator.equals("<=")
                && !operator.equals(">=")
                && !operator.equals("=");
    }

}
