package dbal.specification;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;

public class BookSpecification {

        private BookSpecification() {
            throw new IllegalStateException("Utility class");
        }

        public static Specifiable getByGenre(int genre_id) {
            return new AbstractSpecification() {
                @Override
                public Criterion toCriterion() {
                    return Restrictions.eq("genre_id", genre_id);
                }
            };
        }

}
