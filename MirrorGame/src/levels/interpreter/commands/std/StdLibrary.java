/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package levels.interpreter.commands.std;

import levels.interpreter.CactusStackMapNode;
import levels.interpreter.commands.Command;

/**
 * Is a node on a cactus stack of maps. 
 * @author Edgar Lin
 */
public class StdLibrary extends CactusStackMapNode<String, Command>{

    /**
     * Adds this libraries contents, the stdlibrary functions,
     * on top of the contents of the parent's stack.
     * @param parent Parent CactusStackMap to add this library atop of.
     */
    public StdLibrary(CactusStackMapNode<String, Command> parent) {
        super(parent);
        this.put("print", new Print());
        this.put("=", new Assign());
        this.put("prog", new Prog());
        this.put("if", new If());
        this.put("?", new If());
        this.put("==", new Equals());
        this.put("!", new Not());
        this.put("+", new Add());
        this.put("-", new Minus());
        this.put("*", new Multiply());
        this.put("/", new Divide());
        this.put("%", new Mod());
        this.put("while", new While());
        this.put("<", new Less());
        this.put(">", new Greater());
        this.put("<=", new LessEqual());
        this.put(">=", new GreaterEqual());
    }
}
