package com.chandraedu.api.transactions.mapper;

import com.chandraedu.api.transactions.dto.ParentTransactionBareDTO;
import com.chandraedu.api.transactions.dto.ParentTransactionDTO;
import com.chandraedu.api.transactions.entity.ParentTransaction;

import java.util.List;

public class ParentTransactionMapper {

    private ParentTransactionMapper() {
        throw new IllegalArgumentException("ParentTransactionMapper can not create object");
    }

    public static ParentTransactionDTO getParentTransactionDTO(List<ParentTransactionBareDTO> parentTransactionBareDTOList) {

        ParentTransactionDTO parentTransactionDTO = new ParentTransactionDTO();
        parentTransactionDTO.setData(parentTransactionBareDTOList);
        return parentTransactionDTO;
    }

    public static ParentTransactionBareDTO mapToParentTransactionBareDTO(
            final ParentTransaction parentTransaction,
            final double totalPaidAmount) {

        ParentTransactionBareDTO parentTransactionBareDTO = new ParentTransactionBareDTO();
        parentTransactionBareDTO.setParentId(parentTransaction.getId());
        parentTransactionBareDTO.setReceiver(parentTransaction.getReceiver());
        parentTransactionBareDTO.setTotalAmount(parentTransaction.getTotalAmount());
        parentTransactionBareDTO.setSender(parentTransaction.getSender());
        parentTransactionBareDTO.setTotalPaidAmount(totalPaidAmount);
        return parentTransactionBareDTO;
    }

}
