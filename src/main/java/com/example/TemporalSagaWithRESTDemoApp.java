package com.example;

import com.example.activity.MoneyTransferActivity;
import com.example.workflow.MoneyTransferWorkflow;
import io.temporal.worker.Worker;
import io.temporal.worker.WorkerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import com.example.workflow.MoneyTransferWorkflowImpl;


@SpringBootApplication
public class TemporalSagaWithRESTDemoApp {
    public static void main(String[] args) {
        ConfigurableApplicationContext applicationContext = SpringApplication.run(TemporalSagaWithRESTDemoApp.class, args);
        WorkerFactory factory = applicationContext.getBean(WorkerFactory.class);
        MoneyTransferActivity moneyTransferActivity = applicationContext.getBean(MoneyTransferActivity.class);
        Worker worker = factory.newWorker(MoneyTransferWorkflow.QUEUE_NAME);
        worker.registerWorkflowImplementationTypes(MoneyTransferWorkflowImpl.class);
        worker.registerActivitiesImplementations(moneyTransferActivity);
        factory.start();

        /*
        WITHOUT SPRINGBOOT

        WorkflowServiceStubs service = WorkflowServiceStubs.newInstance();
        WorkflowClient client = WorkflowClient.newInstance(service);
        WorkerFactory factory = WorkerFactory.newInstance(client);
        Worker worker = factory.newWorker(MoneyTransferWorkflowImpl.QUEUE_NAME);
        worker.registerWorkflowImplementationTypes(MoneyTransferWorkflowImpl.class);
        worker.registerActivitiesImplementations(moneyTransferActivity);
        factory.start();
        */


    }
}
