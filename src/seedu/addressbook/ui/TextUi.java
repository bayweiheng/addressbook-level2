package seedu.addressbook.ui;

import static seedu.addressbook.common.Messages.MESSAGE_GOODBYE;
import static seedu.addressbook.common.Messages.MESSAGE_INIT_FAILED;
import static seedu.addressbook.common.Messages.MESSAGE_PROGRAM_LAUNCH_ARGS_USAGE;
import static seedu.addressbook.common.Messages.MESSAGE_USING_STORAGE_FILE;
import static seedu.addressbook.common.Messages.MESSAGE_WELCOME;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

import seedu.addressbook.commands.CommandResult;
import seedu.addressbook.data.person.ReadOnlyPerson;

/**
 * Text UI of the application.
 */
public class TextUi {


    private final Scanner in;
    private final PrintStream out;

    public TextUi() {
        this(System.in, System.out);
    }

    public TextUi(InputStream in, PrintStream out) {
        this.in = new Scanner(in);
        this.out = out;
    }

    /**
     * Prompts for the command and reads the text entered by the user.
     * Ignores empty, pure whitespace, and comment lines.
     * Echos the command back to the user.
     * @return command (full line) entered by the user
     */
    public String getUserCommand() {
        showToUser(Formatter.formatRequestUserInputMessage());
        String fullInputLine = in.nextLine();

        // silently consume all ignored lines
        while (Formatter.shouldIgnore(fullInputLine)) {
            fullInputLine = in.nextLine();
        }

        showToUser(Formatter.formatEchoUserInputLine(fullInputLine));
        return fullInputLine;
    }


    public void showWelcomeMessage(String version, String storageFilePath) {
        showToUser(Formatter.formatWelcomeMessage(version, storageFilePath));
    }

    public void showGoodbyeMessage() {
        showToUser(Formatter.formatGoodbyeMessage());
    }


    public void showInitFailedMessage() {
        showToUser(Formatter.formatInitFailedMessage());
    }

    /** Shows message(s) to the user */
    public void showToUser(String message) {
        out.print(message);
    }

    /**
     * Shows the result of a command execution to the user. Includes additional formatting to demarcate different
     * command execution segments.
     */
    public void showResultToUser(CommandResult result) {
        final Optional<List<? extends ReadOnlyPerson>> resultPersons = result.getRelevantPersons();
        if (resultPersons.isPresent()) {
            showPersonListView(resultPersons.get());
        }
        showToUser(Formatter.formatFeedbackToUser(result.feedbackToUser));
    }

    /**
     * Shows a list of persons to the user, formatted as an indexed list.
     * Private contact details are hidden.
     */
    private void showPersonListView(List<? extends ReadOnlyPerson> persons) {
        final List<String> personsToShow = new ArrayList<>();
        for (ReadOnlyPerson person : persons) {
            personsToShow.add(person.getAsTextHidePrivate());
        }
        showToUserAsIndexedList(personsToShow);
    }

    /** Shows a list of strings to the user, formatted as an indexed list. */
    private void showToUserAsIndexedList(List<String> list) {
        showToUser(Formatter.formatIndexedListForViewing(list));
    }

}
