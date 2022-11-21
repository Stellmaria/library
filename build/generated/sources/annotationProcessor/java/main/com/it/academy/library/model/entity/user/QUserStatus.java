package com.it.academy.library.model.entity.user;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QUserStatus is a Querydsl query type for UserStatus
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QUserStatus extends EntityPathBase<UserStatus> {

    private static final long serialVersionUID = 1022046629L;

    public static final QUserStatus userStatus = new QUserStatus("userStatus");

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public final StringPath name = createString("name");

    public final CollectionPath<User, QUser> user = this.<User, QUser>createCollection("user", User.class, QUser.class, PathInits.DIRECT2);

    public QUserStatus(String variable) {
        super(UserStatus.class, forVariable(variable));
    }

    public QUserStatus(Path<? extends UserStatus> path) {
        super(path.getType(), path.getMetadata());
    }

    public QUserStatus(PathMetadata metadata) {
        super(UserStatus.class, metadata);
    }

}

