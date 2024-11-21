package com.example.feed.post.repository.post_queue;

import com.example.feed.post.repository.entity.QLikeEntity;
import com.example.feed.post.repository.entity.QPostEntity;
import com.example.feed.post.repository.entity.QUserPostQueueEntity;
import com.example.feed.post.ui.dto.GetPostContentResponseDto;
import com.example.feed.user.repository.entity.QUserEntity;
import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@RequiredArgsConstructor
@Repository
public class UserPostQueueQueryRepositoryImpl implements UserPostQueueQueryRepository {

    private final JPAQueryFactory jpaQueryFactory;
    private final QPostEntity post = QPostEntity.postEntity;
    private final QUserEntity user = QUserEntity.userEntity;
    private final QLikeEntity like = QLikeEntity.likeEntity;
    private final QUserPostQueueEntity userPostQueue = QUserPostQueueEntity.userPostQueueEntity;

    @Override
    public List<GetPostContentResponseDto> getPostList(Long userId, Long lastContentId) {
        return jpaQueryFactory
                .select(
                        Projections.fields(
                                GetPostContentResponseDto.class,
                                post.id.as("id"),
                                post.content.as("content"),
                                user.id.as("userId"),
                                user.name.as("userName"),
                                user.profileImage.as("userProfileImage"),
                                post.regDt.as("regDt"),
                                post.modDt.as("modDt"),
                                post.commentCount.as("commentCount"),
                                post.likeCount.as("likeCount"),
                                like.isNotNull().as("isLiked")
                        )
                )
                .from(userPostQueue)
                .join(post).on(userPostQueue.postId.eq(post.id))
                .join(user).on(userPostQueue.userId.eq(user.id))
                .leftJoin(like).on(hasLike(userId))
                .where(
                        userPostQueue.userId.eq(userId),
                        hasLastData(lastContentId)
                )
                .orderBy(userPostQueue.postId.desc())
                .limit(20)
                .fetch();
    }

    private BooleanExpression hasLastData(Long lastContentId) {
        if(lastContentId == null) {
            return null;
        }
        return post.id.lt(lastContentId);
    }

    private BooleanExpression hasLike(Long userId) {
        if(userId == null) {
            return null;
        }
        return post.id
                .eq(like.id.targetId)
                .and(like.id.targetType.eq("POST"))
                .and(like.id.userId.eq(userId));
    }
}
