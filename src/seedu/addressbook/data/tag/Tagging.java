package seedu.addressbook.data.tag;

import seedu.addressbook.data.person.Person;

import java.util.ArrayList;

/**
 * Represents an adding or deleting of a tag for a specific person during a session
 * Used for displaying tags changed at the end of a session
 */

public class Tagging {

    private static ArrayList<Tagging> taggings;

    public enum CHANGE {
        ADD, DELETE
    }

    private final Person person;
    private final Tag tag;
    private final CHANGE change;

    /**
     * @param person person whose tag was changed
     * @param tag tag that was changed
     * @param change type of change
     */
    public Tagging(Person person, Tag tag, CHANGE change) {
        this.person = person;
        this.tag = tag;
        this.change = change;
    }

    /**
     * Adds tagging to class-level variable that stores all taggings
     */
    public static void addTagging(Tagging tagging) {
        taggings.add(tagging);
    }

    /**
     * @return all Taggings, representing all changes made to tags since start of session
     */
    public static ArrayList<Tagging> getTaggings() {
        return taggings;
    }

    public Person getPerson() {
        return person;
    }

    public Tag getTag() {
        return tag;
    }

    public CHANGE getChange() {
        return change;
    }
}