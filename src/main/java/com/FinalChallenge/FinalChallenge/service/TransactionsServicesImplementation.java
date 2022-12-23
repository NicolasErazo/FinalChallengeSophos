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
            if (product.isPresent()) {
                Products products = product.get();
                transaction.setProduct_id(id);
                transaction.setBalance(product.get().getBalance());
                transaction.setAvailableBalance(product.get().getAvailableBalance());

                if (transaction.getTypeOfMovement().equals("credit")) {
                    long operation = product.get().getBalance() - transaction.getValue();

                    if (product.get().getAccountType().equals(AccountType.savings_account) && operation >= 0) {
                        products.setBalance(operation);
                    } else {

                        if (product.get().getAccountType().equals(AccountType.current_account) && operation >= -3000000) {
                            products.setBalance(operation);
                        } else {
                            return false;
                        }
                    }

                } else {
                    products.setBalance(transaction.getValue() + product.get().getBalance());
                }

                productsRepository.save(products);
                transactionsRepository.save(transaction);
                return true;
            }
        }
        return false;
    }

}

