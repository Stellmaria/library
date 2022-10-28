package com.it.academy.library.model.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QAbstractAuditingEntity is a Querydsl query type for AbstractAuditingEntity
 */
@Generated("com.querydsl.codegen.DefaultSupertypeSerializer")
public class QAbstractAuditingEntity extends EntityPathBase<AbstractAuditingEntity<? extends java.io.Serializable>> {

    private static final long serialVersionUID = 818269767L;

    public static final QAbstractAuditingEntity abstractAuditingEntity = new QAbstractAuditingEntity("abstractAuditingEntity");

    public final DateTimePath<java.time.Instant> createdAt = createDateTime("createdAt", java.time.Instant.class);

    public final StringPath createdBy = createString("createdBy");

    public final DateTimePath<java.time.Instant> modifiedAt = createDateTime("modifiedAt", java.time.Instant.class);

    public final StringPath modifiedBy = createString("modifiedBy");

    @SuppressWarnings({"all", "rawtypes", "unchecked"})
    public QAbstractAuditingEntity(String variable) {
        super((Class) AbstractAuditingEntity.class, forVariable(variable));
    }

    @SuppressWarnings({"all", "rawtypes", "unchecked"})
    public QAbstractAuditingEntity(Path<? extends AbstractAuditingEntity> path) {
        super((Class) path.getType(), path.getMetadata());
    }

    @SuppressWarnings({"all", "rawtypes", "unchecked"})
    public QAbstractAuditingEntity(PathMetadata metadata) {
        super((Class) AbstractAuditingEntity.class, metadata);
    }

}

