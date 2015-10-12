package org.activiti;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.repository.Deployment;

public class Main {
  
  public static void main(String[] args) {
    GraphProcessEngineConfiguration config = new GraphProcessEngineConfiguration();
    ProcessEngine processEngine = config.buildProcessEngine();
    
    RepositoryService repositoryService = processEngine.getRepositoryService();
		// RuntimeService runtimeService = processEngine.getRuntimeService();
		// TaskService taskService = processEngine.getTaskService();
		// HistoryService historyService = processEngine.getHistoryService();
    
    Deployment deployment = repositoryService.createDeployment().addClasspathResource("oneTaskProcess.bpmn20.xml").deploy();
    System.out.println("Process deployed! Deployment id is " + deployment.getId());
    
//    ProcessInstance processInstance = runtimeService.startProcessInstanceByKey("oneTaskProcess");
//    List<Task> tasks = taskService.createTaskQuery().processInstanceId(processInstance.getId()).list();
//    System.out.println("Got " + tasks.size() + " tasks!");
    
//    taskService.complete(tasks.get(0).getId());
		// System.out.println("Number of process instances = " +
		// historyService.createHistoricProcessInstanceQuery().count());
		// System.out.println("Number of active process instances = " +
		// historyService.createHistoricProcessInstanceQuery().finished().count());
		// System.out.println("Number of finished process instances = " +
		// historyService.createHistoricProcessInstanceQuery().unfinished().count());
    
  }

}
