package com.FinalChallenge.FinalChallenge.service;

import com.FinalChallenge.FinalChallenge.entity.AccountType;
import com.FinalChallenge.FinalChallenge.entity.Products;
import com.FinalChallenge.FinalChallenge.entity.Status;
import com.FinalChallenge.FinalChallenge.entity.Transactions;
import com.FinalChallenge.FinalChallenge.repository.ProductsRepository;
import com.FinalChallenge.FinalChallenge.repository.TransactionsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TransactionsServicesImplementation implements TransactionsServices {
    @Autowired
    TransactionsRepository transactionsRepository;
    @Autowired
    ProductsRepository productsRepository;

    @Override
    public Boolean addMovementToProduct(Transactions transaction, int id) {

        Optional<Products> product = productsRepository.findById(id);

        if (!product.get().getStatus().equals(Status.canceled)) {
            Products products = product.get();
            transaction.setProduct_id(id);
            transaction.setBalance(product.get().getBalance());
            transaction.setAvailableBalance(product.get().getAvailableBalance());

            if (transaction.getTypeOfMovement().equals("debit")) {
                long operation = product.get().getBalance() - transaction.getValue();

                if (product.get().getAccountType().equals(AccountType.savings) && operation >= 0) {
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
                } else {

                    if (product.get().getAccountType().equals(AccountType.checking) && operation >= -3000000) {
                        if (true) { //condition availableBalance
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

                    } else {
                        return false;
                    }
                }

            } else if (!product.get().getStatus().equals(Status.inactive)) {
                products.setBalance(transaction.getValue() + product.get().getBalance());
                products.setAvailableBalance(products.getAvailableBalance() + transaction.getValue());
            }

            productsRepository.save(products);
            transactionsRepository.save(transaction);
            return true;
        }
        return false;
    }

}

