package org.prisma.processhub.bpmn.manipulation.bpmnt.operation;

import org.camunda.bpm.model.bpmn.instance.FlowElement;
import org.camunda.bpm.model.xml.instance.ModelElementInstance;

public class ContributeCustomParent extends Contribute {
    private ModelElementInstance parentElement;

    public ContributeCustomParent(int executionOrder, FlowElement newElement, ModelElementInstance parentElement) {
        super(executionOrder, newElement);
        this.parentElement = parentElement;
        name = "contributeCustomParent";
    }

    public ModelElementInstance getParentElement() {
        return parentElement;
    }
}
