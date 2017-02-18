/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package levels.parseutil.commands;

import levels.parseutil.CactusStackMapNode;

/**
 *
 * @author Edgar Lin
 */
public class StdLibrary {
    /**
     * Loads the contents of this library onto the symbol table, table
     * @param table 
     */
    public static void load(CactusStackMapNode table)
    {
        table.put("print", new Print());
        table.put("=", new Assign());
    }
}
