package org.activiti;

import org.activiti.engine.impl.cfg.ProcessEngineConfigurationImpl;
import org.activiti.engine.impl.interceptor.CommandInterceptor;
import org.activiti.engine.impl.persistence.StrongUuidGenerator;
import org.activiti.manager.Neo4jDeploymentDataManager;
import org.activiti.manager.Neo4jJobDataManager;
import org.activiti.manager.Neo4jProcessDefinitionDataManager;
import org.activiti.manager.Neo4jResourceDataManager;
import org.activiti.transaction.NoopTransactionContextFactory;

public class GraphProcessEngineConfiguration extends
		ProcessEngineConfigurationImpl {

	public GraphProcessEngineConfiguration() {
		this.usingRelationalDatabase = false;
		this.idGenerator = new StrongUuidGenerator();
	}

	@Override
	protected CommandInterceptor createTransactionInterceptor() {
		return null;
	}

	@Override
	protected void initTransactionContextFactory() {
		if (transactionContextFactory == null) {
			transactionContextFactory = new NoopTransactionContextFactory();
		}
	}

	@Override
	protected void initDataManagers() {
		this.deploymentDataManager = new Neo4jDeploymentDataManager(this);
		this.resourceDataManager = new Neo4jResourceDataManager(this);
		this.processDefinitionDataManager = new Neo4jProcessDefinitionDataManager(
				this);
		this.jobDataManager = new Neo4jJobDataManager(this);
		this.resourceDataManager = new Neo4jResourceDataManager(this);
	}

}
