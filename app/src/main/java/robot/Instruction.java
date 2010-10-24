package robot;

public class Instruction {

    private 	int label;
    private int line;
    private int rozkaz;
    private int operation;
    private int value1;
    private int value2;

    public Instruction() {
    }

    public Instruction(int line, int label, int rozkaz, int operation, int value1, int value2)
    {
        this.line = line;
        this.label = label;
        this.rozkaz = rozkaz;
        this.operation = operation;
        this.value1 = value1;
        this.value2 = value2;
    }
    public Instruction(int line, int label, int rozkaz, int operation,int value1)
    {
        this.line = line;
        this.label = label;
        this.rozkaz = rozkaz;
        this.operation = operation;
        this.value1 = value1;
        value2 = -1;
    }

    public Instruction(int line, int label, int rozkaz, int value1)
    {
        this.line = line;
        this.label = label;
        this.rozkaz = rozkaz;
        operation = -1;
        this.value1 = value1;
        value2 = -1;
    }

    public Instruction(int line, int label, int rozkaz)
    {
        this.line = line;
        this.label = label;
        this.rozkaz = rozkaz;
        operation = -1;
        value1 = -1;
        value2 = -1;
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

    public int getRozkaz() {
        return rozkaz;
    }

    public void setRozkaz(int rozkaz) {
        this.rozkaz = rozkaz;
    }

    public int getOperation() {
        return operation;
    }

    public void setOperation(int operation) {
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

    @Override
    public String toString() {
        return "Instruction{" +
                "label=" + label +
                ", line=" + (line +1) +
                ", rozkaz=" + rozkaz +
                ", operation=" + operation +
                ", value1=" + value1 +
                ", value2=" + value2 +
                '}';
    }
}