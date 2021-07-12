package com.chandraedu.api.transactions.controller;

import com.chandraedu.api.transactions.dto.ChildTransactionDTO;
import com.chandraedu.api.transactions.service.ChildTransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ChildTransactionRestController {

    public static final String V1_CHILD_TRANSACTIONS_ENDPOINT = "api/v1/child-transactions";
    private final ChildTransactionService childTransactionService;

    @Autowired
    ChildTransactionRestController(ChildTransactionService childTransactionService) {
        this.childTransactionService = childTransactionService;
    }

    @GetMapping(V1_CHILD_TRANSACTIONS_ENDPOINT)
    public ResponseEntity<ChildTransactionDTO> getAllChildTransactions(
            @RequestParam(name = "parentId") final String parentId) {

        final ChildTransactionDTO childTransactionDTOList = childTransactionService.getChildTransactions(
                parentId
        );
        return new ResponseEntity<>(childTransactionDTOList, HttpStatus.OK);
    }
}
