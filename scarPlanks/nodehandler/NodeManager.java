package scripts.scarPlanks.nodehandler;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class NodeManager implements Comparator<Node> {

    private List<Node> list;

    public NodeManager() {
        list = new ArrayList<>();
    }

    public void addTasks(Node... nodes) {
        for (Node node: nodes) {
            if (!list.contains(node)) {
                list.add(node);
            }
        }
    }

    public void removeTask(Node node) {
        if (list.contains(node)) {
            list.remove(node);
        }
    }

    public void clearTasks() {
        list.clear();
    }

    public int size() {
        return list.size();
    }

    /*
    Return the highest priority valid task.
     */
    public Node getValidTask() {
        if (list.size() > 0) {
            Collections.sort(list, this);
            return list.get(0);
        }
        return null;
    }

    /*
    Overridden compare method from the Comparator interface used by the
    Collections sort method to determine what task is at the head of the priority.
    Added to the standard compare method is a check to see if each
    task validates or not before comparing them.
    If one task does not validate and the other does, the task that validates
    assumes higher priority.
    Refer to the javadoc for the Comparator interface for an explanation of the
    return values from this method.
     */
    @Override
    public int compare(Node o1, Node o2) {
        boolean o1Valid = o1.validate(), o2Valid = o2.validate();
        if (!o1Valid && o2Valid) return 1;
        else if (o1Valid && !o2Valid) return -1;
        return o2.priority() - o1.priority();
    }

}