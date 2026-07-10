package com.example.expense_tracker.services;

import com.example.expense_tracker.entities.Transaction;
import com.example.expense_tracker.entities.TransactionCategory;
import com.example.expense_tracker.entities.User;
import com.example.expense_tracker.repositories.TransactionCategoryRepository;
import com.example.expense_tracker.repositories.TransactionRepository;
import com.example.expense_tracker.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

@Service
public class TransactionCategoryService {

    private static final Logger logger = Logger.getLogger(TransactionCategoryService.class.getName());

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TransactionCategoryRepository transactionCategoryRepository;

    @Autowired
    private TransactionRepository transactionRepository;


    public Optional<TransactionCategory> getTransactionCategoryById(int id) {
        logger.info("Getting transaction category by id: " + id);
        return transactionCategoryRepository.findById(id);
    }

    public List<TransactionCategory> getAllTransactionCategoriesByUserId(int userId) {
        logger.info("Getting all transaction categories of user: " + userId);
        return transactionCategoryRepository.findAllByUserId(userId);
    }

    public TransactionCategory createTransactionCategory(int userId, String categoryName, String categoryColor) {
        logger.info("Creating Transaction Category for user: " + userId);

        Optional<User> userOptional = userRepository.findById(userId);
        if (userOptional.isEmpty()) return null;

        TransactionCategory category = new TransactionCategory();
        category.setUser(userOptional.get());
        category.setCategoryName(categoryName);
        category.setCategoryColor(categoryColor);

        return transactionCategoryRepository.save(category);
    }


    public TransactionCategory updateTransactionCategoryById(int categoryId, String newCategoryName, String newCategoryColor) {
        logger.info("Updating TransactionCategory ID: " + categoryId);

        Optional<TransactionCategory> categoryOptional = transactionCategoryRepository.findById(categoryId);
        if (categoryOptional.isEmpty()) return null;

        TransactionCategory category = categoryOptional.get();
        category.setCategoryName(newCategoryName);
        category.setCategoryColor(newCategoryColor);

        return transactionCategoryRepository.save(category);
    }

    public boolean deleteTransactionCategoryById(int categoryId) {
        logger.info("Deleting category with id: " + categoryId);

        Optional<TransactionCategory> categoryOptional = transactionCategoryRepository.findById(categoryId);
        if (categoryOptional.isEmpty()) return false;

        transactionCategoryRepository.delete(categoryOptional.get());
        return true;
    }
}
