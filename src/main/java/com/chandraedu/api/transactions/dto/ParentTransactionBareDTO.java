package com.chandraedu.api.transactions.dto;

import lombok.Data;

@Data
public class ParentTransactionBareDTO {

    private Long parentId;
    private String sender;
    private String receiver;
    private double totalAmount;
    private double totalPaidAmount;
}
