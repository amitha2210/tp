package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PAYMENT;

import java.util.logging.Logger;
import java.util.stream.Stream;

import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.MarkPaidCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.FeesPaidByStudent;

/**
 * Parses input arguments and creates a new MarkPaidCommand object
 */
public class MarkPaidCommandParser implements Parser<MarkPaidCommand> {
    private static final Logger logger = LogsCenter.getLogger(MarkPaidCommandParser.class);


    /**
     * Parses the given {@code String} of arguments in the context of the MarkPaidCommand
     * and returns a MarkPaidCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public MarkPaidCommand parse(String args) throws ParseException {
        assert args != null : "Input arguments for EnrollCommand cannot be null";

        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_PAYMENT);
        if (!arePrefixesPresent(argMultimap, PREFIX_PAYMENT)) {
            logger.warning(String.format(Messages.MESSAGE_LOGGER_FOR_EXCEPTION, MarkPaidCommandParser.class));
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, MarkPaidCommand.MESSAGE_USAGE));
        }

        Index index;
        try {
            index = ParserUtil.parseIndex(argMultimap.getPreamble());
        } catch (ParseException pe) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, MarkPaidCommand.MESSAGE_USAGE), pe);
        }

        argMultimap.verifyNoDuplicatePrefixesFor(PREFIX_PAYMENT);
        FeesPaidByStudent feesPaidByStudent = ParserUtil.parseFees(argMultimap.getValue(PREFIX_PAYMENT).get());

        return new MarkPaidCommand(index, feesPaidByStudent);
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }

}

