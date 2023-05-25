package com.henry.controller;

import com.henry.model.Author;
import com.henry.resolver.QueryAndMutationResolver;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.graphql.data.method.annotation.SchemaMapping;
import org.springframework.stereotype.Controller;

@Controller
public class AuthorController {
    private  final QueryAndMutationResolver queryAndMutationResolver;

    public AuthorController(QueryAndMutationResolver queryAndMutationResolver) {
        this.queryAndMutationResolver = queryAndMutationResolver;
    }

    @QueryMapping
    public Author getAuthorById(@Argument Long id) {
        return queryAndMutationResolver.getAuthorById(id);
    }

    @QueryMapping
    public Iterable<Author> getAllAuthors(){
        return queryAndMutationResolver.getAllAuthors();
    }

    @SchemaMapping(typeName = "Mutation", field = "createAuthor")
    public Author createAuthor(@Argument String firstName, @Argument String lastName){
        return queryAndMutationResolver.createAuthor(firstName, lastName);
    }

    @SchemaMapping(typeName = "Mutation", field = "updateAuthor")
    public Author updateAuthor(@Argument Long id, @Argument String firstName, @Argument String lastName) {
        return queryAndMutationResolver.updateAuthor(id,firstName, lastName);
    }

    @SchemaMapping(typeName = "Mutation", field = "deleteAuthor")
    public Boolean deleteAuthor(@Argument Long id) {
        return queryAndMutationResolver.deleteAuthor(id);
    }
}
