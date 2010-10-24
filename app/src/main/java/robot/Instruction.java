package robot;

import robot.logic.InstructionExecutionException;

public abstract class Instruction {

    private int label;
    private int line;
    private Order order;
    private InstructionOperator operation;
    private int value1;
    private int value2;

    public Instruction() {
    }

    public int getLabel() {
        return label;
    }

    public void setLabel(int label) {
        this.label = label;
    }

    public int getLine() {
        return line;
    }

    public void setLine(int line) {
        this.line = line;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public InstructionOperator getOperation() {
        return operation;
    }

    public void setOperation(InstructionOperator operation) {
        this.operation = operation;
    }

    public int getValue1() {
        return value1;
    }

    public void setValue1(int value1) {
        this.value1 = value1;
    }

    public int getValue2() {
        return value2;
    }

    public void setValue2(int value2) {
        this.value2 = value2;
    }

    public abstract void process(RobotProcessor processor) throws InstructionExecutionException;

    @Override
    public String toString() {
        return "Instruction{" +
                "label=" + label +
                ", line=" + (line +1) +
                ", rozkaz=" + order +
                ", operation=" + operation +
                ", value1=" + value1 +
                ", value2=" + value2 +
                '}';
    }
}