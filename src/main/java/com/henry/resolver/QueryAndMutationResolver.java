package com.henry.resolver;

import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import com.henry.model.Author;
import com.henry.model.Book;
import com.henry.repository.AuthorRepository;
import com.henry.repository.BookRepository;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class QueryAndMutationResolver implements GraphQLQueryResolver, GraphQLMutationResolver {

    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;


    public QueryAndMutationResolver(BookRepository bookRepository, AuthorRepository authorRepository) {
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
    }

    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    public Book getBookById(Long id) {
        return bookRepository.findById(id).orElse(null);
    }

    public Iterable<Author> getAllAuthors() {
        return authorRepository.findAll();
    }

    public Author getAuthorById(Long id) {
        return authorRepository.findById(id).orElseGet(null);
    }

    public Author createAuthor(String firstName, String lastName ) {
        Author author = new Author();
        author.setFirstName(firstName);
        author.setLastName(lastName);
        return authorRepository.saveAndFlush(author);
    }

    public Author updateAuthor(Long id, String firstName, String lastName) {
        Author author = authorRepository.findById(id).orElse(null);
        if (author != null) {
            author.setFirstName(firstName);
            author.setLastName(lastName);
            return authorRepository.saveAndFlush(author);
        }
        return null;
    }

    public Boolean deleteAuthor(Long id) {
        authorRepository.deleteById(id);
        return true;
    }

    public Book createBook(String title, Long author_id) {

        Author author = authorRepository.findById(author_id).
                orElseGet(null);

        Book book = new Book();
        book.setTitle(title);
        book.setAuthor(author);
        return bookRepository.saveAndFlush(book);
    }

    public Book updateBook(Long id, String title) {
        Book book = bookRepository.findById(id).orElse(null);
        if (book != null) {
            book.setTitle(title);
            return bookRepository.saveAndFlush(book);
        }
        return null;
    }

    public Boolean deleteBook(Long id) {
        bookRepository.deleteById(id);
        return true;
    }
}

