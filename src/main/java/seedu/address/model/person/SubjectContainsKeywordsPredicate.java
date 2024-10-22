package seedu.address.model.person;

import java.util.function.Predicate;

import seedu.address.commons.util.StringUtil;
import seedu.address.commons.util.ToStringBuilder;

public class SubjectContainsKeywordsPredicate implements Predicate<Person> {
    private final String keyword;

    public SubjectContainsKeywordsPredicate(String keyword) {
        this.keyword = keyword;
    }

    @Override
    public boolean test(Person person) {
        return person.getParticipation().stream()
                .anyMatch(eachParticipation ->
                        StringUtil.areStringsMatchIgnoreCase(eachParticipation.getTutorialSubject(), this.keyword));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof SubjectContainsKeywordsPredicate)) {
            return false;
        }

        SubjectContainsKeywordsPredicate otherPredicate = (SubjectContainsKeywordsPredicate) other;
        return this.keyword.equals(otherPredicate.keyword);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).add("keyword", keyword).toString();
    }
}
