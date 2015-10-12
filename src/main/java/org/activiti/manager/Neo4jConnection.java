package org.activiti.manager;

import org.neo4j.graphdb.GraphDatabaseService;
import org.neo4j.graphdb.factory.GraphDatabaseFactory;

public class Neo4jConnection {

	private static Neo4jConnection instance;

	private GraphDatabaseService graphDatabaseService;

	private Neo4jConnection() {
		this.graphDatabaseService = new GraphDatabaseFactory()
				.newEmbeddedDatabase("target/graphDB");

		registerShutdownHook(graphDatabaseService);
	}

	public static Neo4jConnection getInstance() {
		if (instance == null)
			instance = new Neo4jConnection();

		return instance;
	}

	private void registerShutdownHook(final GraphDatabaseService graphDb) {
		Runtime.getRuntime().addShutdownHook(new Thread() {
			@Override
			public void run() {
				graphDb.shutdown();
			}
		});
	}

	public GraphDatabaseService getGraphDatabaseService() {
		return graphDatabaseService;
	}

}
