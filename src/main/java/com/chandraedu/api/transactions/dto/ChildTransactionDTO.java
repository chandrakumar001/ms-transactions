package com.chandraedu.api.transactions.dto;

import lombok.Data;

import java.util.List;

@Data
public class ChildTransactionDTO {
    
    private List<ChildTransactionBareDTO> data;
}
