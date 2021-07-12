package com.chandraedu.api.transactions.service;

import com.chandraedu.api.transactions.dto.ParentTransactionBareDTO;
import com.chandraedu.api.transactions.dto.ParentTransactionDTO;
import com.chandraedu.api.transactions.entity.ChildTransaction;
import com.chandraedu.api.transactions.entity.ParentTransaction;
import com.chandraedu.api.transactions.exception.FieldValidationException;
import com.chandraedu.api.transactions.exception.NoRecordFoundException;
import com.chandraedu.api.transactions.repository.ParentTransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;

import static com.chandraedu.api.transactions.constant.CommonConstant.DEFAULT_VALUE_ZERO;
import static com.chandraedu.api.transactions.mapper.ParentTransactionMapper.getParentTransactionDTO;
import static com.chandraedu.api.transactions.mapper.ParentTransactionMapper.mapToParentTransactionBareDTO;
import static java.util.stream.Collectors.toList;

@Service
@Transactional
public class DefaultParentTransactionService implements ParentTransactionService {

    public static final int MINUS_ONE_PAGE = 1;
    public static final String ERROR_INVALID_PAGE = "The page is invalid";
    private final ParentTransactionRepository parentTransactionRepository;

    @Autowired
    public DefaultParentTransactionService(final ParentTransactionRepository parentTransactionRepository) {
        this.parentTransactionRepository = parentTransactionRepository;
    }


    /**
     * @param page     this will be page number
     * @param pageSize this will be page size, per request how many record want to see it.
     * @param sortedBy this will be sort, per request sorted by parentId.
     * @return ParentTransactionDTO
     * @Throws NoRecordFoundException, if database their is no record then throw the Exception
     * @Throws FieldValidationException, if given page number is exceed then throw the Exception
     */
    @Override
    public ParentTransactionDTO getParentTransactions(
            final String page,
            final String pageSize,
            final String sortedBy) {

        final int requestedPage = Integer.parseInt(page);
        final int size = Integer.parseInt(pageSize);

        final Pageable pageable = getPageable(requestedPage, size, sortedBy);
        final Page<ParentTransaction> parentTransactionPage = parentTransactionRepository.findAll(
                pageable
        );
        validateInvalidPageRequest(requestedPage, parentTransactionPage.getTotalPages())
                .ifPresent(FieldValidationException::fieldValidationException);

        final List<ParentTransaction> parentTransactionList = parentTransactionPage.getContent();
        if (CollectionUtils.isEmpty(parentTransactionList)) {
            throw new NoRecordFoundException();
        }

        final List<ParentTransactionBareDTO> parentTransactionDTOList = getParentTransactionDTOList(
                parentTransactionList
        );
        return getParentTransactionDTO(parentTransactionDTOList);
    }

    /**
     *
     * @param requestedPage this will be page number
     * @param pageSize      this will be pageSize, per request how many record want to see it.
     * @param sortedBy      this will be sort, per request sorted by parentId.
     * @return Pageable object
     */
    private Pageable getPageable(int requestedPage,
                                 int pageSize,
                                 final String sortedBy) {

        final Sort sortedByName = SortAttribute.getSortedByName(sortedBy);
        return PageRequest.of(
                requestedPage - MINUS_ONE_PAGE,
                pageSize,
                sortedByName
        );
    }

    /**
     * validate validateInvalidPageRequest
     *
     * @param requestedPage, incoming request page number
     * @param totalPage,     database total page number
     * @return string as errorMessage
     */
    private Optional<String> validateInvalidPageRequest(int requestedPage,
                                                        int totalPage) {

        if (requestedPage <= totalPage) {
            return Optional.empty();
        }
        return Optional.of(ERROR_INVALID_PAGE);
    }

    /**
     * Mapping database object to dto object
     *
     * @param parentTransactionList database object
     * @return ParentTransactionBareDTO list object
     */
    private static List<ParentTransactionBareDTO> getParentTransactionDTOList(List<ParentTransaction> parentTransactionList) {

        return parentTransactionList.stream()
                .filter(Objects::nonNull)
                .map(DefaultParentTransactionService::getParentTransactionBareDTO)
                .collect(toList());
    }

    /**
     * Mapping database object to dto object
     *
     * @param parentTransaction database object
     * @return ParentTransactionBareDTO object
     */
    private static ParentTransactionBareDTO getParentTransactionBareDTO(ParentTransaction parentTransaction) {

        final double totalPaidAmount = calculateTotalPaidAmount(
                parentTransaction.getChildTransaction()
        );
        return mapToParentTransactionBareDTO(parentTransaction, totalPaidAmount);
    }

    /**
     * calculateTotalPaidAmount for ChildTransaction
     *
     * @param childTransaction database object
     * @return calculate all child sum
     */
    private static double calculateTotalPaidAmount(Set<ChildTransaction> childTransaction) {

        if (CollectionUtils.isEmpty(childTransaction)) {
            return DEFAULT_VALUE_ZERO;
        }
        return childTransaction
                .stream()
                .filter(Objects::nonNull)
                .mapToDouble(ChildTransaction::getPaidAmount)
                .sum();
    }
}
