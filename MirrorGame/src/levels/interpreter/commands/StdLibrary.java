/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package levels.interpreter.commands;

import levels.interpreter.CactusStackMapNode;

/**
 *
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
    }
}
