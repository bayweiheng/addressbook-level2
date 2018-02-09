package seedu.addressbook.commands;

/**
 * Clears the address book.
 */
public class SortCommand extends Command {

    public static final String COMMAND_WORD = "sort";
    public static final String MESSAGE_USAGE = "Sorts address book by name. Persons with the same name" +
            "will have their relative order preserved. \n"
            + "Example: " + COMMAND_WORD;

    public static final String MESSAGE_SUCCESS = "All persons in address book sorted!";

    public SortCommand() {}


    @Override
    public CommandResult execute() {
        addressBook.sortAllPersonsByName();
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
