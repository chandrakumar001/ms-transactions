package com.chandraedu.api.transactions.repository;

import com.chandraedu.api.transactions.entity.ChildTransaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ChildTransactionRepository extends JpaRepository<ChildTransaction, Long> {

    List<ChildTransaction> findByParentTransactionId(Long parentId);
}
