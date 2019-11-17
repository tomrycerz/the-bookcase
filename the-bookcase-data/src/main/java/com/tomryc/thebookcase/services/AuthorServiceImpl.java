package com.tomryc.thebookcase.services;

import com.tomryc.thebookcase.commands.AuthorCommand;
import com.tomryc.thebookcase.converters.AuthorCommandToAuthor;
import com.tomryc.thebookcase.converters.AuthorToAuthorCommand;
import com.tomryc.thebookcase.model.Author;
import com.tomryc.thebookcase.repositories.AuthorRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Slf4j
@Service
public class AuthorServiceImpl implements AuthorService {

    private final AuthorRepository authorRepository;
    private final AuthorToAuthorCommand authorToAuthorCommand;
    private final AuthorCommandToAuthor authorCommandToAuthor;

    public AuthorServiceImpl(AuthorRepository authorRepository, AuthorToAuthorCommand authorToAuthorCommand, AuthorCommandToAuthor authorCommandToAuthor) {
        this.authorRepository = authorRepository;
        this.authorToAuthorCommand = authorToAuthorCommand;
        this.authorCommandToAuthor = authorCommandToAuthor;
    }

    @Override
    public Set<Author> getAuthors() {
        Set<Author> authorSet =new HashSet<>();
        authorRepository.findAll().iterator().forEachRemaining(authorSet::add);
        return authorSet;
    }

    @Override
    public Author findById(Long l) {

        Optional<Author> authorOptional = authorRepository.findById(l);

        if(!authorOptional.isPresent()){
        // TODO: 17/11/2019 add NotFoundException(RunTime)exception
            //throw new Exception();
        }
        return authorOptional.get();
    }

    @Override
    @Transactional
    public AuthorCommand findCommandById(Long l) {
        return authorToAuthorCommand.convert(findById(l));
    }

    @Override
    @Transactional
    public AuthorCommand saveAuthorCommand(AuthorCommand command) {
        Author detachedAuthor = authorCommandToAuthor.convert(command);

        Author savedAuthor = authorRepository.save(detachedAuthor);
        log.debug("Saved AuthorId: " + savedAuthor.getId());
        return authorToAuthorCommand.convert(savedAuthor);

    }

    @Override
    public void deleteById(Long idToDelete) {
        authorRepository.deleteById(idToDelete);
    }
}
