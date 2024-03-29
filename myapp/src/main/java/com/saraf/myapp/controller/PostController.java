package com.saraf.myapp.controller;

import com.saraf.myapp.entity.Post;
import com.saraf.myapp.service.PostService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/post")
public class PostController {
    private PostService postService;

    @PostMapping("/create")
    public ResponseEntity<Post> createPost(@Valid @RequestBody Post post){
        postService.savePost(post);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Post> findPostById(@Valid @PathVariable Long id){
        return new ResponseEntity<>(postService.getPost(id) ,HttpStatus.OK);

    }


    // retrieving all the posts
    @GetMapping("/all")
    public ResponseEntity<List<Post>> findPosts(){

        return new ResponseEntity<>(postService.getPosts(), HttpStatus.OK);
    }

    // updating the resource
    @PutMapping("/{id}")
    public ResponseEntity <Post> updatePost(@PathVariable long id, @RequestBody Post post){
        postService.updatePost(id, post);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity < Post> deletePost(@PathVariable long id){
        postService.deletePost(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/all")
    public ResponseEntity<Post> deleteAllPosts(){
        postService.deleteAllPosts();
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
