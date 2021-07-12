package com.chandraedu.api.transactions.dto;

import lombok.Data;

@Data
public class ChildTransactionBareDTO {

    private Long childId;
    private String sender;
    private String receiver;
    private double totalAmount;
    private double paidAmount;
}
