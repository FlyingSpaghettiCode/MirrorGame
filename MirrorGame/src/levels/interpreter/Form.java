/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package levels.interpreter;

import java.util.ArrayList;
import java.util.Stack;
import levels.interpreter.commands.Command;
import levels.interpreter.commands.std.*;

/**
 * A list used to store script commands and lists. 
 * @author Edgar Lin
 */
public class Form extends ArrayList {
    public Settable elt(int index)
    {
        return new Element(index, this);
    }

    private class Element extends Command implements Settable {
        
        private final int index;
        private final Form list;
        
        public Element(int index, Form list)
        {
            this.index = index;
            this.list = list;
        }

        @Override
        public void set(Object value) {
            list.set(index, value);
        }
        
        @Override
        public void eval(Stack stack, CactusStackMapNode symMap, int args)
        {
            stack.push(list.get(index));
        }
        
    }
}
