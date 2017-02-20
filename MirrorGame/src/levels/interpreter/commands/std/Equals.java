/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package levels.interpreter.commands.std;

import java.util.Objects;
import java.util.Stack;
import levels.interpreter.CactusStackMapNode;
import levels.interpreter.commands.Command;

/**
 * Returns true if the two arguments are equal according to java.util.Objects.equals(a,b)
 * false otherwise.
 * @author Edgar Lin
 */
public class Equals extends Command {

    @Override
    public void eval(Stack stack, CactusStackMapNode symMap, int args) {
        stack.push(Objects.equals(stack.pop(), stack.pop()));
    }
    
}
