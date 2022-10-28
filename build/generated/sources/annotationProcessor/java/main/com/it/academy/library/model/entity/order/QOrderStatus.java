package com.it.academy.library.model.entity.order;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QOrderStatus is a Querydsl query type for OrderStatus
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QOrderStatus extends EntityPathBase<OrderStatus> {

    private static final long serialVersionUID = 1843824837L;

    public static final QOrderStatus orderStatus = new QOrderStatus("orderStatus");

    public final com.it.academy.library.model.entity.QAbstractAuditingEntity _super = new com.it.academy.library.model.entity.QAbstractAuditingEntity(this);

    //inherited
    public final DateTimePath<java.time.Instant> createdAt = _super.createdAt;

    //inherited
    public final StringPath createdBy = _super.createdBy;

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    //inherited
    public final DateTimePath<java.time.Instant> modifiedAt = _super.modifiedAt;

    //inherited
    public final StringPath modifiedBy = _super.modifiedBy;

    public final StringPath name = createString("name");

    public final CollectionPath<Order, QOrder> order = this.<Order, QOrder>createCollection("order", Order.class, QOrder.class, PathInits.DIRECT2);

    public QOrderStatus(String variable) {
        super(OrderStatus.class, forVariable(variable));
    }

    public QOrderStatus(Path<? extends OrderStatus> path) {
        super(path.getType(), path.getMetadata());
    }

    public QOrderStatus(PathMetadata metadata) {
        super(OrderStatus.class, metadata);
    }

}

