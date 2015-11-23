package scripts.scarPlanks.nodehandler;

public abstract class Node {

    public abstract void execute();

    public abstract boolean validate();

    public abstract int priority();


}