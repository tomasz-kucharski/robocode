package robot.logic;

import robot.Instruction;
import robot.Order;

import java.util.HashMap;
import java.util.Map;

/**
 * @author tomekk
 * @since 2010-10-24, 23:16:03
 */
public class InstructionFactory {

    public Instruction createInstruction(Order order) {
        InstructionCreator instructionCreator = factory.get(order);
        if (instructionCreator != null) {
            return instructionCreator.createInstruction();
        } else {
            return null;
        }
    }

    private Map<Order,InstructionCreator> factory = new HashMap<Order, InstructionCreator>();
    {
        factory.put(Order.RAND, new InstructionCreator(){
            @Override
            public Instruction createInstruction() {
                return new InstructionRAND();
            }
        });
        factory.put(Order.MEMBACK, new InstructionCreator(){
            @Override
            public Instruction createInstruction() {
                return new InstructionMEMBACK();
            }
        });
        factory.put(Order.MEMFRONT, new InstructionCreator(){
            @Override
            public Instruction createInstruction() {
                return new InstructionMEMFRONT();
            }
        });
        factory.put(Order.MEMRIGHT, new InstructionCreator(){
            @Override
            public Instruction createInstruction() {
                return new InstructionMEMRIGHT();
            }
        });
        factory.put(Order.MEMLEFT, new InstructionCreator(){
            @Override
            public Instruction createInstruction() {
                return new InstructionMEMLEFT();
            }
        });
        factory.put(Order.JUMP, new InstructionCreator(){
            @Override
            public Instruction createInstruction() {
                return new InstructionJUMP();
            }
        });
        factory.put(Order.JEQUAL, new InstructionCreator(){
            @Override
            public Instruction createInstruction() {
                return new InstructionJUMPIFEQUAL();
            }
        });
        factory.put(Order.JNEQUAL, new InstructionCreator(){
            @Override
            public Instruction createInstruction() {
                return new InstructionJUMPIFNOTEQUAL();
            }
        });
        factory.put(Order.JGT, new InstructionCreator(){
            @Override
            public Instruction createInstruction() {
                return new InstructionJUMPIFGT();
            }
        });
        factory.put(Order.JLT, new InstructionCreator(){
            @Override
            public Instruction createInstruction() {
                return new InstructionJUMPIFLT();
            }
        });
        factory.put(Order.INVOKE, new InstructionCreator(){
            @Override
            public Instruction createInstruction() {
                return new InstructionINVOKE();
            }
        });
        factory.put(Order.INC, new InstructionCreator(){
            @Override
            public Instruction createInstruction() {
                return new InstructionINC();
            }
        });
        factory.put(Order.DEC, new InstructionCreator(){
            @Override
            public Instruction createInstruction() {
                return new InstructionDEC();
            }
        });
        factory.put(Order.RETURN, new InstructionCreator(){
            @Override
            public Instruction createInstruction() {
                return new InstructionRETURN();
            }
        });
        factory.put(Order.STORE, new InstructionCreator(){
            @Override
            public Instruction createInstruction() {
                return new InstructionSTORE();
            }
        });
        factory.put(Order.LOAD, new InstructionCreator(){
            @Override
            public Instruction createInstruction() {
                return new InstructionLOAD();
            }
        });








    }

    private interface InstructionCreator {
        public Instruction createInstruction();
    }
}
