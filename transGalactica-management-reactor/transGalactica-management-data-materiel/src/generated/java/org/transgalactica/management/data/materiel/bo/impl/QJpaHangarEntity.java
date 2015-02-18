package org.transgalactica.management.data.materiel.bo.impl;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;
import com.mysema.query.types.path.PathInits;


/**
 * QJpaHangarEntity is a Querydsl query type for JpaHangarEntity
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QJpaHangarEntity extends EntityPathBase<JpaHangarEntity> {

    private static final long serialVersionUID = 1009651751L;

    public static final QJpaHangarEntity jpaHangarEntity = new QJpaHangarEntity("jpaHangarEntity");

    public final StringPath localisation = createString("localisation");

    public final NumberPath<Integer> nombreEmplacements = createNumber("nombreEmplacements", Integer.class);

    public final NumberPath<Long> numero = createNumber("numero", Long.class);

    public final SetPath<JpaVaisseauEntity, QJpaVaisseauEntity> vaisseaux = this.<JpaVaisseauEntity, QJpaVaisseauEntity>createSet("vaisseaux", JpaVaisseauEntity.class, QJpaVaisseauEntity.class, PathInits.DIRECT2);

    public QJpaHangarEntity(String variable) {
        super(JpaHangarEntity.class, forVariable(variable));
    }

    public QJpaHangarEntity(Path<? extends JpaHangarEntity> path) {
        super(path.getType(), path.getMetadata());
    }

    public QJpaHangarEntity(PathMetadata<?> metadata) {
        super(JpaHangarEntity.class, metadata);
    }

}

