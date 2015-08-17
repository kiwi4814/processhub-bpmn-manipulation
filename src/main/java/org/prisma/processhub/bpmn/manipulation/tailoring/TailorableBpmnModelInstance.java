package org.prisma.processhub.bpmn.manipulation.tailoring;

import org.camunda.bpm.model.bpmn.BpmnModelInstance;
import org.camunda.bpm.model.bpmn.instance.*;
import org.camunda.bpm.model.bpmn.instance.Process;
import org.camunda.bpm.model.xml.instance.ModelElementInstance;
import org.prisma.processhub.bpmn.manipulation.bpmnt.BpmntModelInstance;
import org.prisma.processhub.bpmn.manipulation.exception.ElementNotFoundException;

import java.util.Collection;

//import java.util.List;

// Interface that extends the Camunda API with tailoring operations
public interface TailorableBpmnModelInstance extends BpmnModelInstance {

    // Useful operations that extend BpmnModelInstance features
    boolean contains (FlowElement element);

    String setUniqueId(FlowElement element);
    void generateUniqueIds();

    void connectAllPreviousToSucceedingNodes(FlowNode previous, FlowNode succeeding);
    void connectAllPreviousToSucceedingNodes(FlowNode node);


    // Low-level operations
    /* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */

    // Extend
    BpmntModelInstance extend();

    // Contribute
    <T extends FlowElement, E extends ModelElementInstance> T contribute(E parentElement, T element);
    <T extends FlowElement> T contribute(T element);

    // Suppress
    <T extends FlowElement> void suppress (T element);
    <T extends FlowElement> void suppress (Collection<T> elements);
    void suppress (String elementId);

    // Modify
    <T extends FlowElement> void modify(T element, String property, String value);
    void modify(String elementId, String property, String value);


    // High-level operations
    /* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */

    // Rename
    void rename(String elementId, String newName);
    void rename(FlowElement element, String newName);

    // Delete
    void delete(FlowNode node);
    void delete(String nodeId);

    void delete(FlowNode startingNode, FlowNode endingNode);
    void delete(String startingNodeId, String endingNodeId);

    // Replace
    void replace(FlowNode targetNode, FlowNode replacingNode);
    void replace(String targetNodeId, FlowNode FlowNode);

    void replace(FlowNode targetNode, BpmnModelInstance replacingFragment);
    void replace(String targetNodeId, BpmnModelInstance replacingFragment);

    void replace(FlowNode startingNode, FlowNode endingNode, FlowNode replacingNode);
    void replace(String startingNodeId, String endingNodeId, FlowNode replacingNode);

    void replace(FlowNode startingNode, FlowNode endingNode, BpmnModelInstance replacingFragment);
    void replace(String startingNodeId, String endingNodeId, BpmnModelInstance replacingFragment);

    // Move
    void move(FlowNode targetNode, FlowNode newPositionAfterOf, FlowNode newPositionBeforeOf);
    void move(String targetNodeId, String newPositionAfterOfId, String newPositionBeforeOfId);

    void move(FlowNode targetStartingNode, FlowNode targetEndingNode, FlowNode newPositionAfterOf, FlowNode newPositionBeforeOf);
    void move(String targetStartingNodeId, String targetEndingNodeId, String newPositionAfterOfId, String newPositionBeforeOfId);

    // Parallelize
    void parallelize(FlowNode targetStartingNode, FlowNode targetEndingNode) throws Exception;
    void parallelize(String targetStartingNodeId, String targetEndingNodeId) throws Exception;

    // Split
    void split(Task targetTask, BpmnModelInstance newSubProcessModel);

    // Insert
    void insert(FlowNode afterOf, FlowNode beforeOf, FlowNode flowNodeToInsert);
    void insert(FlowNode afterOf, FlowNode beforeOf, Process fragmentToInsert);
    void insert(FlowNode afterOf, FlowNode beforeOf, BpmnModelInstance fragmentToInsert);

    // Conditional Insert
    // TODO: find out what is the purpose of "inLoop"
    void conditionalInsert(FlowNode afterOf, FlowNode beforeOf, FlowNode flowNodeToInsert, String condition, boolean inLoop);
    void conditionalInsert(FlowNode afterOf, FlowNode beforeOf, Process fragmentToInsert,  String condition, boolean inLoop);
    void conditionalInsert(FlowNode afterOf, FlowNode beforeOf, BpmnModelInstance fragmentToInsert,  String condition, boolean inLoop);

}
