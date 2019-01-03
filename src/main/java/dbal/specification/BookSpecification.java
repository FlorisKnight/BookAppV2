package dbal.specification;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;

public class BookSpecification {

        private BookSpecification() {
            throw new IllegalStateException("Utility class");
        }

        public static Specifiable getById(int book_id) {
            return new AbstractSpecification() {
                @Override
                public Criterion toCriterion() {
                    return Restrictions.eq("id", book_id);
                }
            };
        }

}
