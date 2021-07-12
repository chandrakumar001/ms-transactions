package com.chandraedu.api.transactions.mapper;

import com.chandraedu.api.transactions.dto.ChildTransactionBareDTO;
import com.chandraedu.api.transactions.dto.ChildTransactionDTO;
import com.chandraedu.api.transactions.entity.ChildTransaction;
import com.chandraedu.api.transactions.entity.ParentTransaction;

import java.util.List;

import static com.chandraedu.api.transactions.constant.CommonConstant.DEFAULT_VALUE_ZERO;
import static java.util.Objects.requireNonNullElse;

public class ChildTransactionMapper {

    private ChildTransactionMapper() {
        throw new IllegalArgumentException("ChildTransactionMapper can not create object");
    }

    public static ChildTransactionDTO mapToChildTransactionDTO(List<ChildTransactionBareDTO> childTransactionBareDTOList) {

        ChildTransactionDTO childTransactionDTO = new ChildTransactionDTO();
        childTransactionDTO.setData(childTransactionBareDTOList);
        return childTransactionDTO;
    }

    public static ChildTransactionBareDTO mapToChildTransactionBareDTO(ChildTransaction childTransaction) {

        final ParentTransaction parentTransaction = childTransaction.getParentTransaction();

        ChildTransactionBareDTO childTransactionBareDTO = new ChildTransactionBareDTO();
        childTransactionBareDTO.setChildId(childTransaction.getId());
        childTransactionBareDTO.setPaidAmount(childTransaction.getPaidAmount());
        childTransactionBareDTO.setSender(requireNonNullElse(parentTransaction.getSender(), null));
        childTransactionBareDTO.setReceiver(requireNonNullElse(parentTransaction.getReceiver(), null));
        childTransactionBareDTO.setTotalAmount(requireNonNullElse(parentTransaction.getTotalAmount(), DEFAULT_VALUE_ZERO));
        return childTransactionBareDTO;
    }
}
