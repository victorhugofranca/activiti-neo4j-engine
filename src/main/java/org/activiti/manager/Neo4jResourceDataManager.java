package org.activiti.manager;

import java.util.List;

import org.activiti.engine.impl.cfg.ProcessEngineConfigurationImpl;
import org.activiti.engine.impl.persistence.entity.ResourceEntity;
import org.activiti.engine.impl.persistence.entity.ResourceEntityImpl;
import org.activiti.engine.impl.persistence.entity.data.ResourceDataManager;
import org.neo4j.graphdb.Node;
import org.neo4j.graphdb.Transaction;

/**
 * @author Joram Barrez
 */
public class Neo4jResourceDataManager extends
		AbstractNeo4jDataManager<ResourceEntity> implements ResourceDataManager {

	public Neo4jResourceDataManager(
			ProcessEngineConfigurationImpl processEngineConfiguration) {
		super(processEngineConfiguration);
	}

	public void insert(ResourceEntity entity) {
		try (Transaction tx = graphDatabaseService.beginTx()) {
			Node node = graphDatabaseService.createNode();
			String id = getProcessEngineConfiguration().getIdGenerator()
					.getNextId();
			entity.setId(id);
			node.setProperty("id", entity.getId());
			node.setProperty("deploymentId", entity.getDeploymentId());
			node.setProperty("bytes", entity.getBytes());
			node.setProperty("name", entity.getName());
			tx.success();
		}
	}

	public ResourceEntity create() {
		return new ResourceEntityImpl();
	}

	public void deleteResourcesByDeploymentId(String deploymentId) {
		throw new UnsupportedOperationException();
	}

	public ResourceEntity findResourceByDeploymentIdAndResourceName(
			String deploymentId, String resourceName) {
		throw new UnsupportedOperationException();
	}

	public List<ResourceEntity> findResourcesByDeploymentId(String deploymentId) {
		throw new UnsupportedOperationException();
	}

}
