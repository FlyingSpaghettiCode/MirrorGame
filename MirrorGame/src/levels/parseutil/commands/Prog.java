package levels.parseutil.commands;

import java.util.Stack;

/**
 * Executes the given commands in order. Returns the return value of the last
 * one.
 * @author Edgar Lin
 */
public class Prog extends Command{

    @Override
    public void eval(Stack stack, int args) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    @Override
    public boolean isEager(int index)
    {
        return false;
    }
    
}
