package org.transgalactica.management.data.materiel.bo.impl;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;
import com.mysema.query.types.path.PathInits;


/**
 * QJpaVaisseauIntergalactiqueEntity is a Querydsl query type for JpaVaisseauIntergalactiqueEntity
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QJpaVaisseauIntergalactiqueEntity extends EntityPathBase<JpaVaisseauIntergalactiqueEntity> {

    private static final long serialVersionUID = -730351845L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QJpaVaisseauIntergalactiqueEntity jpaVaisseauIntergalactiqueEntity = new QJpaVaisseauIntergalactiqueEntity("jpaVaisseauIntergalactiqueEntity");

    public final QJpaVaisseauEntity _super;

    //inherited
    public final NumberPath<Integer> autonomie;

    //inherited
    public final NumberPath<Long> capaciteDeFret;

    // inherited
    public final QJpaHangarEntity hangar;

    //inherited
    public final StringPath immatriculation;

    //inherited
    public final StringPath modele;

    public final NumberPath<Short> multiplicateurHyperdrive = createNumber("multiplicateurHyperdrive", Short.class);

    //inherited
    public final NumberPath<Short> nombreDePassagers;

    //inherited
    public final NumberPath<Long> technicalId;

    //inherited
    public final NumberPath<Integer> vitesse;

    public QJpaVaisseauIntergalactiqueEntity(String variable) {
        this(JpaVaisseauIntergalactiqueEntity.class, forVariable(variable), INITS);
    }

    public QJpaVaisseauIntergalactiqueEntity(Path<? extends JpaVaisseauIntergalactiqueEntity> path) {
        this(path.getType(), path.getMetadata(), path.getMetadata().isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QJpaVaisseauIntergalactiqueEntity(PathMetadata<?> metadata) {
        this(metadata, metadata.isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QJpaVaisseauIntergalactiqueEntity(PathMetadata<?> metadata, PathInits inits) {
        this(JpaVaisseauIntergalactiqueEntity.class, metadata, inits);
    }

    public QJpaVaisseauIntergalactiqueEntity(Class<? extends JpaVaisseauIntergalactiqueEntity> type, PathMetadata<?> metadata, PathInits inits) {
        super(type, metadata, inits);
        this._super = new QJpaVaisseauEntity(type, metadata, inits);
        this.autonomie = _super.autonomie;
        this.capaciteDeFret = _super.capaciteDeFret;
        this.hangar = _super.hangar;
        this.immatriculation = _super.immatriculation;
        this.modele = _super.modele;
        this.nombreDePassagers = _super.nombreDePassagers;
        this.technicalId = _super.technicalId;
        this.vitesse = _super.vitesse;
    }

}

