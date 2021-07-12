package com.chandraedu.api.transactions.service;

import org.springframework.data.domain.Sort;

import java.util.Map;

public class SortAttribute {

    public static final String PREFIX_PLUS = "+";
    public static final String PREFIX_MINUS = "-";
    public static final String ERROR_SORTED_BY_ATTRIBUTE_IS_INVALID = "The sortedBy attribute is Invalid";

    private SortAttribute() {
        throw new IllegalArgumentException("SortAttribute can not create object");
    }

    /**
     * @param sortedBy incoming request attribute name sorting
     * @return sort object
     */
    public static Sort getSortedByName(String sortedBy) {

        final String sortedByValue = parentTransactionByValue(
                sortedBy.substring(1)
        );
        if (sortedBy.startsWith(PREFIX_PLUS)) {
            return Sort.by(sortedByValue).ascending();
        }
        if (sortedBy.startsWith(PREFIX_MINUS)) {
            return Sort.by(sortedByValue).descending();
        }
        return Sort.by("id").ascending();
    }

    /**
     * match request sort attribute and database sort attribute
     *
     * @param sortedByValue incoming request attribute name sorting
     * @return String
     */
    private static String parentTransactionByValue(String sortedByValue) {

        Map<String, String> parentTransactionMap = Map.of(
                "parentId", "id",
                "sender", "sender"
        );
        final String attribute = parentTransactionMap.get(sortedByValue);
        if (attribute == null) {
            throw new IllegalArgumentException(ERROR_SORTED_BY_ATTRIBUTE_IS_INVALID);
        }
        return attribute;
    }
}
