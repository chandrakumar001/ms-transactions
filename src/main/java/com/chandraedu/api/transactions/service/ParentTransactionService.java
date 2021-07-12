package com.chandraedu.api.transactions.service;

import com.chandraedu.api.transactions.dto.ParentTransactionDTO;

public interface ParentTransactionService {

    /**
     * @param page this will be page number
     * @param pageSize this will be page size, per request how many record want to see it.
     * @param sortedBy this will be sort, per request sorted by parentId.
     * @return ParentTransactionDTO
     * @Throws NoRecordFoundException, if database their is no record then throw the Exception
     * @Throws FieldValidationException, if given page number is exceed then throw the Exception
     */
    ParentTransactionDTO getParentTransactions(
            final String page,
            final String pageSize,
            final String sortedBy
    );
}
