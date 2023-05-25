package com.henry.controller;

import com.henry.model.Book;
import com.henry.resolver.QueryAndMutationResolver;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.graphql.data.method.annotation.SchemaMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class BookController {

    private  final QueryAndMutationResolver queryAndMutationResolver;

    public BookController(QueryAndMutationResolver queryAndMutationResolver) {
        this.queryAndMutationResolver = queryAndMutationResolver;
    }


    @QueryMapping
    public Book getBookById(@Argument Long id) {
        return queryAndMutationResolver.getBookById(id);
    }

    @QueryMapping
    public List<Book> getAllBooks(){
        return queryAndMutationResolver.getAllBooks();
    }

    @SchemaMapping(typeName = "Mutation", field = "createBook")
    public Book createBook(@Argument String title, @Argument Long authorId){
        return queryAndMutationResolver.createBook(title, authorId);
    }

    @SchemaMapping(typeName = "Mutation", field = "updateBook")
    public Book updateBook(@Argument Long id, @Argument String title) {
        return queryAndMutationResolver.updateBook(id,title);
    }

    @SchemaMapping(typeName = "Mutation", field = "deleteBook")
    public Boolean deleteBook(@Argument Long id) {
        return queryAndMutationResolver.deleteBook(id);
    }
}
