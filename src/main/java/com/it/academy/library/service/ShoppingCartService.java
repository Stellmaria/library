package com.it.academy.library.service;

import com.it.academy.library.exception.NotEnoughProductsInStockException;
import com.it.academy.library.model.entity.book.Book;
import com.it.academy.library.model.repository.entity.book.BookRepository;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

import java.util.Map;

@Service
@Scope(value = WebApplicationContext.SCOPE_SESSION, proxyMode = ScopedProxyMode.TARGET_CLASS)
@Transactional
@RequiredArgsConstructor
public class ShoppingCartService {
    private final BookRepository bookRepository;

    @Getter
    private final Map<Book, Long> books;

    public Map<Book, Long> addProduct(Book book) {
        if (books.containsKey(book)) {
            books.replace(book, books.get(book) + 1);
        } else {
            books.put(book, 1L);
        }
        return books;
    }

    public boolean removeProduct(Book book) {
        if (books.containsKey(book)) {
            if (books.get(book) > 1) {
                books.replace(book, books.get(book) - 1);
                return true;
            } else if (books.get(book) == 1) {
                books.remove(book);
                return true;
            }
        }
        return false;
    }

    public void checkout() throws NotEnoughProductsInStockException {
        for (Map.Entry<Book, Long> entry : books.entrySet()) {
            var book = bookRepository.findById(entry.getKey().getId());
            assert book.isPresent();

            if (book.get().getQuantity() < entry.getValue()) {
                throw new NotEnoughProductsInStockException(book.get());
            }

            entry.getKey().setQuantity(book.get().getQuantity() - entry.getValue());
        }

        bookRepository.saveAll(books.keySet());
        bookRepository.flush();
        books.clear();
    }
}
