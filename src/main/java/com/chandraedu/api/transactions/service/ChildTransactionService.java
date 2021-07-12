package com.chandraedu.api.transactions.service;

import com.chandraedu.api.transactions.dto.ChildTransactionDTO;

public interface ChildTransactionService {

    /**
     * @param parentId load corresponding child  Transaction
     * @return childTransactionDTO data
     * @Throws NoRecordFoundException, if given parentId is not found then throw the Exception
     */
    ChildTransactionDTO getChildTransactions( final String parentId);
}
