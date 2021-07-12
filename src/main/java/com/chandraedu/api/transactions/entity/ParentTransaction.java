package com.chandraedu.api.transactions.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Entity
@Table(schema = "transactions", name = "parent_transaction")
public class ParentTransaction implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String sender;
    private String receiver;
    private double totalAmount;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "parentTransaction")
    private Set<ChildTransaction> childTransaction;
}
