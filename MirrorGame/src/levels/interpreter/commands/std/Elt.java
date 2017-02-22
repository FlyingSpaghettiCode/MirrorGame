/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package levels.interpreter.commands.std;

import java.util.Stack;
import levels.interpreter.CactusStackMapNode;
import levels.interpreter.Form;
import levels.interpreter.commands.Command;

/**
 * Gets a reference to the element at index arg2 of list arg1.
 * 
 * @author Edgar Lin
 */
public class Elt extends Command{

    @Override
    public void eval(Stack stack, CactusStackMapNode symMap, int args) {
        stack.push(((Form) stack.pop()).elt(((Long) stack.pop()).intValue()));
    }
    
}
