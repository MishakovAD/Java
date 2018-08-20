package Pagination;

import java.util.Map;
import java.util.TreeMap;

public class Pagination {
	/**
     * Method create a set of pages for pagination
     *
     * @param count         common count of elements
     * @param pageNumber    Number of page we build pagination for
     * @param pageSize      Count of elements for each page
     * @param delta         Count of pages we want get before and after current page
     * @return              Set of Page
     */
    public static Map<Integer, Boolean> getPagination(int count, int pageNumber, int pageSize, int delta) {
 
        /* Prepare result map op Pages */
        Map<Integer, Boolean> result = new TreeMap<>();
 
        /* Get quantity of full and not full pages */
        int pn = count % pageSize > 0 ? Math.floorDiv(count, pageSize) + 1 : Math.floorDiv(count, pageSize);
 
        /* Run from 1 to page quantity */
        for (int i = 1; i <= pn ; i ++ ) {
 
                /* If i in current page environs of delta then add it to page collection.
                 * Additionally if i is current page, then we mark it to highlight in
                 * interface */
                if ( Math.abs( i - pageNumber) <= delta ) {
                    result.put(i, i == pageNumber);
                }
        }
 
        return result;
    }

}
