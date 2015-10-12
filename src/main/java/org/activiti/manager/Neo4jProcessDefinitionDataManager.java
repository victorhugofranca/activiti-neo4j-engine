package org.activiti.manager;

import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.activiti.engine.impl.Page;
import org.activiti.engine.impl.ProcessDefinitionQueryImpl;
import org.activiti.engine.impl.cfg.ProcessEngineConfigurationImpl;
import org.activiti.engine.impl.persistence.entity.ProcessDefinitionEntity;
import org.activiti.engine.impl.persistence.entity.ProcessDefinitionEntityImpl;
import org.activiti.engine.impl.persistence.entity.data.ProcessDefinitionDataManager;
import org.activiti.engine.repository.ProcessDefinition;
import org.neo4j.graphdb.DynamicLabel;
import org.neo4j.graphdb.Label;
import org.neo4j.graphdb.Node;
import org.neo4j.graphdb.ResourceIterator;
import org.neo4j.graphdb.Transaction;
import org.neo4j.graphdb.schema.IndexDefinition;
import org.neo4j.graphdb.schema.Schema;

/**
 * @author Joram Barrez
 */
public class Neo4jProcessDefinitionDataManager extends
		AbstractNeo4jDataManager<ProcessDefinitionEntity> implements
		ProcessDefinitionDataManager {

	private IndexDefinition indexDefinition;

	public Neo4jProcessDefinitionDataManager(
			ProcessEngineConfigurationImpl processEngineConfiguration) {
		super(processEngineConfiguration);
	}

	public ProcessDefinitionEntity create() {
		return new ProcessDefinitionEntityImpl();
	}

	@Override
	public void insert(ProcessDefinitionEntity entity) {
//		try (Transaction tx = graphDatabaseService.beginTx()) {
//			Schema schema = graphDatabaseService.schema();
//			indexDefinition = schema
//					.indexFor(DynamicLabel.label("ProcessDefinition"))
//					.on("key").create();
//			tx.success();
//		}
//
//		try (Transaction tx = graphDatabaseService.beginTx()) {
//			Schema schema = graphDatabaseService.schema();
//			schema.awaitIndexOnline(indexDefinition, 10, TimeUnit.SECONDS);
//		}

		try (Transaction tx = graphDatabaseService.beginTx()) {
			Node node = graphDatabaseService.createNode();
			String id = getProcessEngineConfiguration().getIdGenerator()
					.getNextId();
			entity.setId(id);
			node.setProperty("id", entity.getId());
			node.setProperty("key", entity.getKey());
			// node.setProperty("candidateStarterGroupIdExpressions",
			// entity.getCandidateStarterGroupIdExpressions());
			// node.setProperty("candidateStarterUserIdExpressions",
			// entity.getCandidateStarterUserIdExpressions());
			if (entity.getEngineVersion() != null)
				node.setProperty("engineVersion", entity.getEngineVersion());
			// node.setProperty("eventSupport", entity.getEventSupport());
			node.setProperty("hasStartFormKey", entity.getHasStartFormKey());
			if (entity.getHistoryLevel() != null)
			node.setProperty("historyLevel", entity.getHistoryLevel());
			// node.setProperty("identityLinks", entity.getIdentityLinks());
			// node.setProperty("ioSpecification", entity.getIoSpecification());
			node.setProperty("suspensionState", entity.getSuspensionState());

			tx.success();
		}
	}

	public ProcessDefinitionEntity findLatestProcessDefinitionByKey(
			String processDefinitionKey) {
		Label label = DynamicLabel.label("ProcessDefinition");
		try (Transaction tx = graphDatabaseService.beginTx()) {
			try (ResourceIterator<Node> processDefinitons = graphDatabaseService
					.findNodes(label, "key", processDefinitionKey)) {
				if (processDefinitons.hasNext()) {
					Node node = processDefinitons.next();
					ProcessDefinitionEntity processDefinitionEntity = new ProcessDefinitionEntityImpl();
					processDefinitionEntity.setId(String.valueOf(node
							.getProperty("id")));
					processDefinitionEntity.setKey(String.valueOf(node
							.getProperty("key")));
					processDefinitionEntity.setEngineVersion(String
							.valueOf(node.getProperty("engineVersion")));
					return processDefinitionEntity;
				}
			}
		}

		return null;
	}

	public ProcessDefinitionEntity findLatestProcessDefinitionByKeyAndTenantId(
			String processDefinitionKey, String tenantId) {
		throw new UnsupportedOperationException();
	}

	public void deleteProcessDefinitionsByDeploymentId(String deploymentId) {
		throw new UnsupportedOperationException();
	}

	public List<ProcessDefinition> findProcessDefinitionsByQueryCriteria(
			ProcessDefinitionQueryImpl processDefinitionQuery, Page page) {
		throw new UnsupportedOperationException();
	}

	public long findProcessDefinitionCountByQueryCriteria(
			ProcessDefinitionQueryImpl processDefinitionQuery) {
		throw new UnsupportedOperationException();
	}

	public ProcessDefinitionEntity findProcessDefinitionByDeploymentAndKey(
			String deploymentId, String processDefinitionKey) {
		throw new UnsupportedOperationException();
	}

	public ProcessDefinitionEntity findProcessDefinitionByDeploymentAndKeyAndTenantId(
			String deploymentId, String processDefinitionKey, String tenantId) {
		throw new UnsupportedOperationException();
	}

	public ProcessDefinitionEntity findProcessDefinitionByKeyAndVersion(
			String processDefinitionKey, Integer processDefinitionVersion) {
		throw new UnsupportedOperationException();
	}

	public ProcessDefinitionEntity findProcessDefinitionByKeyAndVersionAndTenantId(
			String processDefinitionKey, Integer processDefinitionVersion,
			String tenantId) {
		throw new UnsupportedOperationException();
	}

	public List<ProcessDefinition> findProcessDefinitionsByNativeQuery(
			Map<String, Object> parameterMap, int firstResult, int maxResults) {
		throw new UnsupportedOperationException();
	}

	public long findProcessDefinitionCountByNativeQuery(
			Map<String, Object> parameterMap) {
		throw new UnsupportedOperationException();
	}

	public void updateProcessDefinitionTenantIdForDeployment(
			String deploymentId, String newTenantId) {
		throw new UnsupportedOperationException();
	}

}
