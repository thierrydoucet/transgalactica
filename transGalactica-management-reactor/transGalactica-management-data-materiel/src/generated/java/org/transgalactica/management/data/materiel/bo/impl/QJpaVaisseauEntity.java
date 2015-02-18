package org.transgalactica.management.data.materiel.bo.impl;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;
import com.mysema.query.types.path.PathInits;


/**
 * QJpaVaisseauEntity is a Querydsl query type for JpaVaisseauEntity
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QJpaVaisseauEntity extends EntityPathBase<JpaVaisseauEntity> {

    private static final long serialVersionUID = 1006316127L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QJpaVaisseauEntity jpaVaisseauEntity = new QJpaVaisseauEntity("jpaVaisseauEntity");

    public final NumberPath<Integer> autonomie = createNumber("autonomie", Integer.class);

    public final NumberPath<Long> capaciteDeFret = createNumber("capaciteDeFret", Long.class);

    public final QJpaHangarEntity hangar;

    public final StringPath immatriculation = createString("immatriculation");

    public final StringPath modele = createString("modele");

    public final NumberPath<Short> nombreDePassagers = createNumber("nombreDePassagers", Short.class);

    public final NumberPath<Long> technicalId = createNumber("technicalId", Long.class);

    public final NumberPath<Integer> vitesse = createNumber("vitesse", Integer.class);

    public QJpaVaisseauEntity(String variable) {
        this(JpaVaisseauEntity.class, forVariable(variable), INITS);
    }

    public QJpaVaisseauEntity(Path<? extends JpaVaisseauEntity> path) {
        this(path.getType(), path.getMetadata(), path.getMetadata().isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QJpaVaisseauEntity(PathMetadata<?> metadata) {
        this(metadata, metadata.isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QJpaVaisseauEntity(PathMetadata<?> metadata, PathInits inits) {
        this(JpaVaisseauEntity.class, metadata, inits);
    }

    public QJpaVaisseauEntity(Class<? extends JpaVaisseauEntity> type, PathMetadata<?> metadata, PathInits inits) {
        super(type, metadata, inits);
        this.hangar = inits.isInitialized("hangar") ? new QJpaHangarEntity(forProperty("hangar")) : null;
    }

}

