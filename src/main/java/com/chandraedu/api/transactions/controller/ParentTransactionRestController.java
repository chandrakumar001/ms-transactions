package com.chandraedu.api.transactions.controller;

import com.chandraedu.api.transactions.dto.ParentTransactionDTO;
import com.chandraedu.api.transactions.service.ParentTransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ParentTransactionRestController {

    public static final String V1_PARENT_TRANSACTIONS_ENDPOINT = "api/v1/parent-transactions";

    public static final String PAGE_SIZE = "pageSize";
    public static final String PAGE = "page";
    public static final String SORTED_BY = "sortedBy";

    public static final String DEFAULT_VALUE_FOR_PAGE_SIZE = "2";
    public static final String DEFAULT_VALUE_FOR_PAGE = "1";
    public static final String DEFAULT_VALUE_FOR_SORTED_BY_NAME = "+parentId";

    private final ParentTransactionService parentTransactionService;

    @Autowired
    ParentTransactionRestController(ParentTransactionService parentTransactionService) {
        this.parentTransactionService = parentTransactionService;
    }

    @GetMapping(V1_PARENT_TRANSACTIONS_ENDPOINT)
    public ResponseEntity<ParentTransactionDTO> getAllParentTransaction(
            @RequestParam(name = PAGE, defaultValue = DEFAULT_VALUE_FOR_PAGE) final String page,
            @RequestParam(name = PAGE_SIZE, defaultValue = DEFAULT_VALUE_FOR_PAGE_SIZE) final String pageSize,
            @RequestParam(name = SORTED_BY, defaultValue = DEFAULT_VALUE_FOR_SORTED_BY_NAME) final String sortedBy) {

        final ParentTransactionDTO parentTransactionDTOList = parentTransactionService.getParentTransactions(
                page,
                pageSize,
                sortedBy
        );
        return new ResponseEntity<>(parentTransactionDTOList, HttpStatus.OK);
    }
}
