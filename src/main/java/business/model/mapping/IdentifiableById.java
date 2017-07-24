package business.model.mapping;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

/**
 * Created by alexandremasanes on 05/05/2017.
 */
public interface IdentifiableById {

    long NULL_ID = 0;

    final class Utils {
        private final static class Comparator<T extends IdentifiableById> implements java.util.Comparator<IdentifiableById> {
            @Override
            public int compare(IdentifiableById o1, IdentifiableById o2) {

                if(o1 == o2 || (o1 != null && o2 != null  && ( o1.equals(o2) || o1.getId() == o2.getId())))
                    return 0;

                if(o2 == null || (o1 != null && o1.getId() > o2.getId()))
                    return 1;

                return -1;
            }
        }

        public static <T extends IdentifiableById> List<T> toSortedList(Set<T> identifiableByIds) {
            ArrayList resultList;
            resultList = new ArrayList<IdentifiableById>(identifiableByIds);
            Collections.sort(resultList, new Comparator<T>());
            return resultList;
        }

        Utils(){
        }
    }

    long getId();




}
