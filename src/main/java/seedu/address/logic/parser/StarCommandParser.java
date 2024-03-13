package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_STAR;

import seedu.address.commons.core.index.Index;
import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.StarCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.person.Star;

/**
 * Parses input arguments and creates a new {@code RemarkCommand} object
 */
public class StarCommandParser implements Parser<StarCommand> {
    /**
     * Parses the given {@code String} of arguments in the context of the {@code StarCommand}
     * and returns a {@code StarCommand} object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public StarCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap
                = ArgumentTokenizer.tokenize(args, PREFIX_STAR);

        Index index;
        System.out.println(argMultimap.getValue(PREFIX_STAR).get());
        System.out.println(argMultimap.getPreamble());

        try {
            index = ParserUtil.parseIndex(argMultimap.getPreamble());
        } catch (IllegalValueException ive) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, StarCommand.MESSAGE_USAGE), ive);
        }

        String temp = argMultimap.getValue(PREFIX_STAR).orElse("0");
        Star star = new Star(Integer.parseInt(temp.trim()));

        return new StarCommand(index, star);
    }

}
