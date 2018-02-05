package seedu.addressbook.ui;

import java.util.List;

import static seedu.addressbook.common.Messages.*;

public class Formatter {
    /**
     * A decorative prefix added to the beginning of lines printed by AddressBook
     */
    public static final String LINE_PREFIX = "|| ";
    /**
     * A platform independent line separator.
     */
    public static final String LS = System.lineSeparator();
    public static final String DIVIDER = "===================================================";
    /**
     * Format of indexed list item
     */
    public static final String MESSAGE_INDEXED_LIST_ITEM = "\t%1$d. %2$s";
    /**
     * Offset required to convert between 1-indexing and 0-indexing.
     */
    public static final int DISPLAYED_INDEX_OFFSET = 1;
    /**
     * Format of a comment input line. Comment lines are silently consumed when reading user input.
     */
    public static final String COMMENT_LINE_FORMAT_REGEX = "#.*";

    public Formatter() {
    }

    /**
     * Returns true if the user input line should be ignored.
     * Input should be ignored if it is parsed as a comment, is only whitespace, or is empty.
     *
     * @param rawInputLine full raw user input line.
     * @return true if the entire user input line should be ignored.
     */
    public static boolean shouldIgnore(String rawInputLine) {
        return rawInputLine.trim().isEmpty() || isCommentLine(rawInputLine);
    }

    /**
     * Returns true if the user input line is a comment line.
     *
     * @param rawInputLine full raw user input line.
     * @return true if input line is a comment.
     */
    public static boolean isCommentLine(String rawInputLine) {
        return rawInputLine.trim().matches(COMMENT_LINE_FORMAT_REGEX);
    }

    /** Formats one or more messages, adding line breaks and prefixes */
    public static String formatMultipleMessages(String... message) {
        StringBuilder sb = new StringBuilder();
        for (String m : message) {
            sb.append(LINE_PREFIX + m.replace("\n", LS + LINE_PREFIX) + LS);
        }
        return sb.toString();
    }

    public static String formatRequestUserInputMessage() {
        return LINE_PREFIX + "Enter command: ";
    }

    public static String formatEchoUserInputLine(String userInputLine) {
        return formatMultipleMessages("[Command entered:" + userInputLine + "]");
    }

    public static String formatWelcomeMessage(String version, String storageFilePath) {
        String storageFileInfo = String.format(MESSAGE_USING_STORAGE_FILE, storageFilePath);
        return formatMultipleMessages(
                Formatter.DIVIDER,
                Formatter.DIVIDER,
                MESSAGE_WELCOME,
                version,
                MESSAGE_PROGRAM_LAUNCH_ARGS_USAGE,
                storageFileInfo,
                Formatter.DIVIDER);
    }

    public static String formatGoodbyeMessage() {
        return formatMultipleMessages(MESSAGE_GOODBYE, Formatter.DIVIDER, Formatter.DIVIDER);
    }

    public static String formatInitFailedMessage() {
        return formatMultipleMessages(MESSAGE_INIT_FAILED, Formatter.DIVIDER, Formatter.DIVIDER);
    }

    public static String formatFeedbackToUser(String feedback) {
        return formatMultipleMessages(feedback, DIVIDER);
    }

    /**
     * Formats a list of strings as a viewable indexed list.
     */
    public static String formatIndexedListForViewing(List<String> listItems) {
        final StringBuilder formatted = new StringBuilder();
        int displayIndex = 0 + DISPLAYED_INDEX_OFFSET;
        for (String listItem : listItems) {
            formatted.append(formatIndexedListItem(displayIndex, listItem)).append("\n");
            displayIndex++;
        }
        return formatMultipleMessages(formatted.toString());
    }

    /**
     * Formats a string as a viewable indexed list item.
     *
     * @param visibleIndex visible index for this listing
     */
    public static String formatIndexedListItem(int visibleIndex, String listItem) {
        return String.format(MESSAGE_INDEXED_LIST_ITEM, visibleIndex, listItem);
    }
}