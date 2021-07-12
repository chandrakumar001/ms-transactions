package com.chandraedu.api.transactions.repository;

import com.chandraedu.api.transactions.entity.ParentTransaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ParentTransactionRepository extends
        JpaRepository<ParentTransaction, Long>,
        PagingAndSortingRepository<ParentTransaction, Long> {
}
