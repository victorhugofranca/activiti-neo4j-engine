package org.activiti.manager;

import java.util.List;
import java.util.Map;

import org.activiti.engine.impl.DeploymentQueryImpl;
import org.activiti.engine.impl.Page;
import org.activiti.engine.impl.cfg.ProcessEngineConfigurationImpl;
import org.activiti.engine.impl.persistence.entity.DeploymentEntity;
import org.activiti.engine.impl.persistence.entity.DeploymentEntityImpl;
import org.activiti.engine.impl.persistence.entity.data.DeploymentDataManager;
import org.activiti.engine.repository.Deployment;
import org.neo4j.graphdb.Node;
import org.neo4j.graphdb.Transaction;

/**
 * @author Joram Barrez
 */
public class Neo4jDeploymentDataManager extends
		AbstractNeo4jDataManager<DeploymentEntity> implements
		DeploymentDataManager {

	public Neo4jDeploymentDataManager(
			ProcessEngineConfigurationImpl processEngineConfiguration) {
		super(processEngineConfiguration);
	}

	@Override
	public void insert(DeploymentEntity entity) {
		try (Transaction tx = graphDatabaseService.beginTx()) {
			Node node = graphDatabaseService.createNode();
			String id = getProcessEngineConfiguration().getIdGenerator()
					.getNextId();
			entity.setId(id);
			node.setProperty("id", entity.getId());
			if (entity.getCategory() != null)
				node.setProperty("category", entity.getCategory());
			node.setProperty("time", entity.getDeploymentTime().getTime());

			tx.success();
		}
	}

	public DeploymentEntity create() {
		return new DeploymentEntityImpl();
	}

	public DeploymentEntity findLatestDeploymentByName(String deploymentName) {
		throw new UnsupportedOperationException();
	}

	public long findDeploymentCountByQueryCriteria(
			DeploymentQueryImpl deploymentQuery) {
		throw new UnsupportedOperationException();
	}

	public List<Deployment> findDeploymentsByQueryCriteria(
			DeploymentQueryImpl deploymentQuery, Page page) {
		throw new UnsupportedOperationException();
	}

	public List<String> getDeploymentResourceNames(String deploymentId) {
		throw new UnsupportedOperationException();
	}

	public List<Deployment> findDeploymentsByNativeQuery(
			Map<String, Object> parameterMap, int firstResult, int maxResults) {
		throw new UnsupportedOperationException();
	}

	public long findDeploymentCountByNativeQuery(
			Map<String, Object> parameterMap) {
		throw new UnsupportedOperationException();
	}

}
