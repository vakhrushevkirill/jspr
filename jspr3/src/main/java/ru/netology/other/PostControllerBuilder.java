package ru.netology.other;

import ru.netology.controller.PostController;
import ru.netology.repository.PostRepository;
import ru.netology.service.PostService;

public class PostControllerBuilder {

    public PostController build(){
        final var repository = new PostRepository();
        final var service = new PostService(repository);
        return new PostController(service);
    }
}
