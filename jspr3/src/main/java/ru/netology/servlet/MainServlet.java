package ru.netology.servlet;

import ru.netology.controller.PostController;
import ru.netology.other.PostControllerBuilder;
import ru.netology.repository.PostRepository;
import ru.netology.service.PostService;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class MainServlet extends HttpServlet {
  private PostController controller;

  final String API_POSTS = "/api/posts";
  final String METH_GET = "GET";
  final String METH_POST = "POST";
  final String METH_DEL = "DELETE";


  @Override
  public void init() {
    controller = new PostControllerBuilder().build();
  }

  public Long getIdFromPath(String path){
    return Long.parseLong(path.substring(path.lastIndexOf("/")).replace("/", ""));
  }

  @Override
  protected void service(HttpServletRequest req, HttpServletResponse resp) {
    // если деплоились в root context, то достаточно этого
    try {
      final var path = req.getRequestURI();
      final var method = req.getMethod();
      // primitive routing
      if (method.equals(METH_GET) && path.equals(API_POSTS)) {
        controller.all(resp);
        return;
      }
      if (method.equals(METH_GET) && path.matches(API_POSTS + "/\\d+")) {
        // easy way
        final var id = getIdFromPath(path);
        controller.getById(id, resp);
        return;
      }
      if (method.equals(METH_POST) && path.equals(API_POSTS)) {
        controller.save(req.getReader(), resp);
        return;
      }
      if (method.equals(METH_DEL) && path.matches(API_POSTS + "/\\d+")) {
        // easy way
        final var id = getIdFromPath(path);
        controller.removeById(id, resp);
        return;
      }
      resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
    } catch (Exception e) {
      e.printStackTrace();
      resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
    }
  }
}

