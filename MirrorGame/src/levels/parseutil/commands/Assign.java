/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package levels.parseutil.commands;

import java.util.Stack;

/**
 * When executed, assigns the value in the second argument to the settable
 * thing in the first argument. It returns that value to the stack. 
 * @author Edgar Lin
 */
public class Assign extends Command{

    @Override
    public void eval(Stack stack, int args) {
        ((Settable) stack.pop()).set(stack.peek());
    }
    
}
