package dbal.specification;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;

public class GenreSpecification {

        private GenreSpecification() {
            throw new IllegalStateException("Utility class");
        }

        public static Specifiable getByGenre(String name) {
            if(name == null)
                throw new IllegalArgumentException();

            return new AbstractSpecification() {
                @Override
                public Criterion toCriterion() {
                    return Restrictions.eq("name", name.toLowerCase());
                }
            };
        }

}
