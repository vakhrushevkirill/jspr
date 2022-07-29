package ru.netology.repository;

import ru.netology.model.Post;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.CopyOnWriteArrayList;

// Stub
public class PostRepository {

  private List<Post> postList;
  private int countPosts;

  public PostRepository(){
    postList = new CopyOnWriteArrayList<>();
    countPosts = 0;
  }

  public List<Post> all() {
    return postList;
  }

  public Optional<Post> getById(long id) {
    return Optional.ofNullable(postList.get((int) id - 1));
  }

  public Post save(Post post) {
    if (post.getId() == 0 || post.getId() > countPosts){
      countPosts++;
      post.setId(countPosts);
      postList.add(post);
    } else {
      postList.get((int) post.getId() - 1).setContent(post.getContent());
    }
    return post;
  }

  public void removeById(long id) {
    try {
      postList.remove((int) id - 1);
      countPosts--;
    } catch (Exception exception){
      exception.printStackTrace();
    }
  }
}
