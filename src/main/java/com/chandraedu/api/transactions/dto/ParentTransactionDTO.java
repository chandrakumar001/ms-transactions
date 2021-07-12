package com.chandraedu.api.transactions.dto;

import lombok.Data;

import java.util.List;

@Data
public class ParentTransactionDTO {
    
    private List<ParentTransactionBareDTO> data;
}
