package com.it.academy.library.model.entity.order;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QOrderType is a Querydsl query type for OrderType
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QOrderType extends EntityPathBase<OrderType> {

    private static final long serialVersionUID = -373464883L;

    public static final QOrderType orderType = new QOrderType("orderType");

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public final StringPath name = createString("name");

    public final CollectionPath<Order, QOrder> order = this.<Order, QOrder>createCollection("order", Order.class, QOrder.class, PathInits.DIRECT2);

    public QOrderType(String variable) {
        super(OrderType.class, forVariable(variable));
    }

    public QOrderType(Path<? extends OrderType> path) {
        super(path.getType(), path.getMetadata());
    }

    public QOrderType(PathMetadata metadata) {
        super(OrderType.class, metadata);
    }

}

