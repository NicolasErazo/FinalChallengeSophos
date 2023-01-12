package com.FinalChallenge.FinalChallenge.service;

import com.FinalChallenge.FinalChallenge.entity.AccountType;
import com.FinalChallenge.FinalChallenge.entity.Products;
import com.FinalChallenge.FinalChallenge.entity.Status;
import com.FinalChallenge.FinalChallenge.entity.Transactions;
import com.FinalChallenge.FinalChallenge.repository.ProductsRepository;
import com.FinalChallenge.FinalChallenge.repository.TransactionsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TransactionsServicesImplementation implements TransactionsServices {
    @Autowired
    TransactionsRepository transactionsRepository;
    @Autowired
    ProductsRepository productsRepository;

    @Override
    public Boolean addMovementToProduct(Transactions transaction, int id) throws ChangeSetPersister.NotFoundException {

        Optional<Products> product = productsRepository.findById(id);

        if (product.isEmpty()) {
            throw new ChangeSetPersister.NotFoundException();
        }
        if (!product.get().getStatus().equals(Status.CANCELED)) {
            Products products = product.get();
            transaction.setProduct_id(id);
            transaction.setBalance(product.get().getBalance());
            transaction.setAvailableBalance(product.get().getAvailableBalance());

            if (transaction.getTypeOfMovement().equals("debit")) {
                long operation = product.get().getBalance() - transaction.getValue();

                if (product.get().getAccountType().equals(AccountType.SAVINGS) && operation >= 0) {
                    if ((products.getAvailableBalance() - (products.getBalance() * 0.004)) >= transaction.getValue()) {
                        products.setBalance(operation);
                        long gmf = (long) (transaction.getValue() * 0.004);
                        if (!products.isGMF()) {
                            if (products.getAvailableBalance() == 0) {
                                products.setAvailableBalance(product.get().getBalance() - gmf);
                            } else {
                                products.setAvailableBalance(products.getAvailableBalance() - transaction.getValue() - gmf);
                            }

                        } else {
                            products.setAvailableBalance(products.getAvailableBalance() - transaction.getValue());
                        }
                    } else {
                        return false;
                    }
                } else if (product.get().getAccountType().equals(AccountType.CHECKING) && operation >= -3000000) {
                    products.setBalance(operation);
                    long gmf = (long) (transaction.getValue() * 0.004);
                    if (!products.isGMF()) {
                        if (products.getAvailableBalance() == 0) {
                            products.setAvailableBalance(product.get().getBalance() - gmf);
                        } else {
                            products.setAvailableBalance(products.getAvailableBalance() - transaction.getValue() - gmf);
                        }

                    } else {
                        products.setAvailableBalance(products.getAvailableBalance() - transaction.getValue());
                    }

                } else {
                    return false;
                }

            } else if (!product.get().getStatus().equals(Status.INACTIVE)) {
                products.setBalance(transaction.getValue() + product.get().getBalance());
                products.setAvailableBalance(products.getAvailableBalance() + transaction.getValue());
            } else {
                return false;
            }

            productsRepository.save(products);
            transactionsRepository.save(transaction);
            return true;
        }
        return false;
    }

}

