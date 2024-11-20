package com.example.feed.post.application;

import com.example.feed.post.application.dto.CreatePostRequestDto;
import com.example.feed.post.application.dto.LikeRequestDto;
import com.example.feed.post.application.dto.UpdatePostRequestDto;
import com.example.feed.post.application.interfaces.LikeRepository;
import com.example.feed.post.application.interfaces.PostRepository;
import com.example.feed.post.domain.Post;
import com.example.feed.post.domain.content.Content;
import com.example.feed.post.domain.content.PostContent;
import com.example.feed.user.application.UserService;
import com.example.feed.user.domain.User;

public class PostService {

    private final UserService userService;
    private final PostRepository postRepository;
    private final LikeRepository likeRepository;

    public PostService(UserService userService, PostRepository postRepository) {
        this.userService = userService;
        this.postRepository = postRepository;
    }

    public Post createPost(CreatePostRequestDto dto) {
        User author = userService.getUser(dto.userId());
        Content content = new PostContent(dto.content());
        Post post = new Post(null, author, content, dto.status());
        return postRepository.save(post);
    }

    public Post updatePost(UpdatePostRequestDto dto) {
        Post post = getPost(dto.postId());
        User author = userService.getUser(dto.userId());

        post.updatePost(author, dto.content(), dto.status());
        return postRepository.save(post);
    }

    public void likePost(LikeRequestDto dto){
        Post post = getPost(dto.targetId());
        User user = userService.getUser(dto.userId());

        if(likeRepository.checkLike(post, user)){
            return;
        }

        post.like(user);
        postRepository.save(post);
    }

    public void unlikePost(LikeRequestDto dto){
        Post post = getPost(dto.targetId());
        User user = userService.getUser(dto.userId());

        if(!likeRepository.checkLike(post, user)){
            post.unlike();
            likeRepository.unlike(post, user);
        }
    }

    public Post getPost(Long id) {
        return postRepository
                .findById(id)
                .orElseThrow(IllegalArgumentException::new);
    }
}
