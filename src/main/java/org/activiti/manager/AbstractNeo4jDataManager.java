package org.activiti.manager;

import org.activiti.engine.impl.cfg.ProcessEngineConfigurationImpl;
import org.activiti.engine.impl.db.Entity;
import org.activiti.engine.impl.persistence.AbstractManager;
import org.activiti.engine.impl.persistence.entity.data.DataManager;
import org.neo4j.graphdb.GraphDatabaseService;

public abstract class AbstractNeo4jDataManager<EntityImpl extends Entity>
		extends AbstractManager implements DataManager<EntityImpl> {

	public AbstractNeo4jDataManager(
			ProcessEngineConfigurationImpl processEngineConfiguration) {
		super(processEngineConfiguration);
	}

	GraphDatabaseService graphDatabaseService = Neo4jConnection.getInstance()
			.getGraphDatabaseService();

	public EntityImpl findById(String entityId) {
		throw new UnsupportedOperationException();
	}

	public void insert(EntityImpl entity) {
		throw new UnsupportedOperationException();
	}

	public EntityImpl update(EntityImpl entity) {
		throw new UnsupportedOperationException();
	}

	public void delete(String id) {
		throw new UnsupportedOperationException();
	}

	public void delete(EntityImpl entity) {
		throw new UnsupportedOperationException();
	}

}
