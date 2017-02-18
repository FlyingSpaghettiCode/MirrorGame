/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package levels.interpreter;

import java.util.Stack;
import levels.Level;
import levels.interpreter.commands.Command;
import levels.interpreter.commands.std.Variable;

/**
 * Holds utility methods to interpret and execute parsed abstract symbol trees.
 * @author Edgar Lin
 */
public class Interpreter {
        public static void interpret(Object ast, Stack stack, CactusStackMapNode symMap)
        {
            if(ast instanceof Symbol)
            {
                if(symMap.containsKey(((Symbol) ast).getSymbol()))
                    stack.push(symMap.get(((Symbol)ast).getSymbol()));
                else
                {
                    Variable var = new Variable(null);
                    symMap.put(((Symbol) ast).getSymbol(), var);
                    stack.push(var);
                }
            }
            else if(ast instanceof Form)
            {
                interpret(((Form) ast).get(0), stack, symMap);
                Command command = (Command) stack.pop();
                for(int i = ((Form) ast).size() - 1; i > 0 ; i--) // puts the contents of the form in the stack so that the first element (excluding the command) is on top.
                {
                    if(command.isEager(i))
                        interpret(((Form) ast).get(i), stack, symMap);
                    else
                        stack.push(((Form) ast).get(i));
                }
                command.eval(stack, symMap, ((Form) ast).size()-1);
            }
            else
                stack.push(ast);
        }

}
