package org.prisma.processhub.bpmn.manipulation.bpmnt.operation;

import org.camunda.bpm.model.bpmn.BpmnModelInstance;
import org.camunda.bpm.model.bpmn.instance.*;
import org.camunda.bpm.model.bpmn.instance.Process;
import org.camunda.bpm.model.xml.instance.ModelElementInstance;
import org.prisma.processhub.bpmn.manipulation.bpmnt.operation.constant.BpmntExtensionAttributes;
import org.prisma.processhub.bpmn.manipulation.util.BpmnElementCreator;

public class InsertFragment extends BpmntInsertionDependentOperation {
    private String afterOfId;
    private String beforeOfId;
    private BpmnModelInstance fragmentToInsert;

    public InsertFragment(int executionOrder, String afterOfId, String beforeOfId, BpmnModelInstance fragmentToInsert) {
        this.executionOrder = executionOrder;
        this.afterOfId = afterOfId;
        this.beforeOfId = beforeOfId;
        this.fragmentToInsert = fragmentToInsert;
        this.name = "InsertFragment";
    }

    public String getAfterOfId() {
        return afterOfId;
    }

    public String getBeforeOfId() {
        return beforeOfId;
    }

    public BpmnModelInstance getFragmentToInsert() {
        return fragmentToInsert;
    }

    @Override
    public void generateExtensionElement(Process process) {
        SubProcess subProcess = generateSubProcessContainer(process);
        ModelElementInstance subProcessExt = subProcess.getExtensionElements().getElementsQuery().singleResult();

        subProcessExt.setAttributeValue(BpmntExtensionAttributes.AFTER_OF_ID, afterOfId);
        subProcessExt.setAttributeValue(BpmntExtensionAttributes.BEFORE_OF_ID, beforeOfId);
        BpmnElementCreator.convertModelToSubprocess(subProcess, fragmentToInsert);
    }
}