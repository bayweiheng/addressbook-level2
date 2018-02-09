package seedu.addressbook.commands;

import org.junit.Test;
import seedu.addressbook.data.AddressBook;
import seedu.addressbook.data.person.Person;
import seedu.addressbook.data.person.ReadOnlyPerson;
import seedu.addressbook.data.person.UniquePersonList;
import seedu.addressbook.util.TestUtil;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Set;

import static org.junit.Assert.*;

public class SortCommandTest {

    @Test
    public void sortCommand_addressBookWithThreeElements_addressBookInSortedOrder() {
        SortCommand command = new SortCommand();

        Person alice = TestUtil.generateTestPerson("Alice");
        Person bob = TestUtil.generateTestPerson("bob");
        Person carol = TestUtil.generateTestPerson("Carol");

        AddressBook book = TestUtil.createAddressBook(carol, bob, alice);
        List<Person> SORTED_LIST = Arrays.asList(alice, bob, carol);

        command.setData(book, Collections.emptyList());
        command.execute();

        UniquePersonList people = book.getAllPersons();
        UniquePersonList expected = null;
        try {
            expected = new UniquePersonList(SORTED_LIST);
        } catch (Exception e) {
            System.out.println("exception");
        }

        assertTrue(people.equals(expected));
    }
}