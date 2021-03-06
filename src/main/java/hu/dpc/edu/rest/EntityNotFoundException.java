package hu.dpc.edu.rest;

import javax.ws.rs.core.Response;

/**
 * Created by vrg on 2016. 11. 10..
 */
public class EntityNotFoundException extends RepositoryException {
    private final Class entityType;
    private final long id;

    public EntityNotFoundException(Class entityType, long id) {
        super(entityType.getSimpleName() + " not found with id " + id);
        this.entityType = entityType;
        this.id = id;
    }

    public Class getEntityType() {
        return entityType;
    }

    public long getId() {
        return id;
    }
}
