package com.userfront.service;

import com.userfront.dao.PrimaryTransactionDao;
import com.userfront.dao.SavingsTransactionDao;
import com.userfront.domain.PrimaryTransaction;
import com.userfront.domain.SavingsTransaction;
import com.userfront.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransactionServiceImpl implements TransactionService {

    private UserService userService;
    private PrimaryTransactionDao primaryTransactionDao;
    private SavingsTransactionDao savingsTransactionDao;

    public TransactionServiceImpl() { }

    @Autowired
    public TransactionServiceImpl(UserService userService,
                                  PrimaryTransactionDao primaryTransactionDao,
                                  SavingsTransactionDao savingsTransactionDao) {
        this.userService = userService;
        this.primaryTransactionDao = primaryTransactionDao;
        this.savingsTransactionDao = savingsTransactionDao;
    }

    @Override
    public List<PrimaryTransaction> findPrimaryTransactionList(String username) {
        User user = userService.findByUsername(username);
        List<PrimaryTransaction> primaryTransactionList = user.getPrimaryAccount().getPrimaryTransactionList();

        return primaryTransactionList;
    }

    @Override
    public List<SavingsTransaction> findSavingsTransactionList(String username) {
        User user = userService.findByUsername(username);
        List<SavingsTransaction> savingsTransactionList = user.getSavingsAccount().getSavingsTransactionList();

        return savingsTransactionList;
    }

    @Override
    public void savePrimaryDepositTransaction(PrimaryTransaction primaryTransaction) {
        primaryTransactionDao.save(primaryTransaction);
    }

    @Override
    public void saveSavingsDepositTransaction(SavingsTransaction savingsTransaction) {
        savingsTransactionDao.save(savingsTransaction);
    }


}
