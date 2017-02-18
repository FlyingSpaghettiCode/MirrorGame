/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package levels.parseutil.commands;

import java.util.Stack;

/**
 * Takes one argument from stack; prints it to standard output; no return.
 * @author Edgar Lin
 */
public class Print extends Command {

    @Override
    public void eval(Stack stack, int args) {
        if(args > 1)
            throw new Error("Arguments expected: 1; Arguments given:" + args);
        System.out.println(stack.pop());
    }
    
}
