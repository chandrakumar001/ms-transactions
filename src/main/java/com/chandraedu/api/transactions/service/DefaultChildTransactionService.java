package com.chandraedu.api.transactions.service;

import com.chandraedu.api.transactions.dto.ChildTransactionBareDTO;
import com.chandraedu.api.transactions.dto.ChildTransactionDTO;
import com.chandraedu.api.transactions.entity.ChildTransaction;
import com.chandraedu.api.transactions.exception.NoRecordFoundException;
import com.chandraedu.api.transactions.mapper.ChildTransactionMapper;
import com.chandraedu.api.transactions.repository.ChildTransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.transaction.Transactional;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;

import static com.chandraedu.api.transactions.mapper.ChildTransactionMapper.mapToChildTransactionDTO;
import static java.util.stream.Collectors.toList;

@Service
@Transactional
public class DefaultChildTransactionService implements ChildTransactionService {

    private final ChildTransactionRepository childTransactionRepository;

    @Autowired
    public DefaultChildTransactionService(final ChildTransactionRepository childTransactionRepository) {
        this.childTransactionRepository = childTransactionRepository;
    }

    /**
     * @param parentId load corresponding child  Transaction
     * @return childTransactionDTO data
     * @Throws NoRecordFoundException, if given parentId is not found then throw the Exception
     */
    @Override
    public ChildTransactionDTO getChildTransactions(final String parentId) {

        //TODO validate incoming request

        final Long parentIdLong = Long.parseLong(parentId);
        final List<ChildTransaction> childTransactionList = childTransactionRepository.findByParentTransactionId(
                parentIdLong
        );
        if (CollectionUtils.isEmpty(childTransactionList)) {
            throw new NoRecordFoundException();
        }

        final List<ChildTransactionBareDTO> childTransactionBareDTOList = getChildTransactionDTOList(
                childTransactionList
        );
        return mapToChildTransactionDTO(childTransactionBareDTOList);
    }

    /**
     * Mapping database object to dto object
     *
     * @param childTransactions map database entity to DTO entity
     * @return ChildTransactionBareDTO list
     */
    private static List<ChildTransactionBareDTO> getChildTransactionDTOList(List<ChildTransaction> childTransactions) {

        return childTransactions.stream()
                .filter(Objects::nonNull)
                .sorted(Comparator.comparingLong(ChildTransaction::getId))
                .map(ChildTransactionMapper::mapToChildTransactionBareDTO)
                .collect(toList());
    }
}
