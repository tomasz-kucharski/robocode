
public class ProgramList extends List<Instruction> {
    public boolean jump;

    public ProgramList()
    {
        jump = true;
    }


    public Instruction getInstruction()
    {
        if (jump)
        {
            return getNext();
//            jump = false;
        }
        else
            return next();
    }

    boolean gotoInstruction(int label)
    {
        jump = true;
        Instruction temp;
        if (!setToFirst())
            return false;
        temp = getObject();
        if( temp.getLabel() == label)
            return true;
        while  ( (temp = next()) != null)
        {
            if ( temp.getLabel() == label)
                return true;
        }
        return false;
    }


    boolean returnInstruction(int line)
    {
        Instruction temp;
        if (!setToFirst())
            return false;
        temp = getObject();
        if( temp.getLine() == line)
            return true;
        while  ( (temp = next()) != null )
        {
            if ( temp.getLine() == line)
                return true;
        }
        return false;
    }
}
