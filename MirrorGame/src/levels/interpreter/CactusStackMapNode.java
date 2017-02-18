/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package levels.interpreter;

import java.util.TreeMap;

/**
 * A map that has some behaviors of a cactus stack implemented as a singly linked
 * list of maps.
 * 
 * This is a node for a singly linked list of maps, intended to be used for a stack.
 * 
 * The map has been modified so that it is also aware of the maps beneath it on
 * the stack (the parent maps). 
 * 
 * This class is used to implement lexical scoping.
 * 
 * @author Edgar Lin
 */
public class CactusStackMapNode<E,F> extends TreeMap<E,F> {
    private final CactusStackMapNode<E,F> parent;
    public CactusStackMapNode(CactusStackMapNode<E,F> parent)
    {
        super();
        this.parent = parent;
    }
    @Override
    public boolean containsKey(Object key)
    {
        return super.containsKey(key) || (parent == null ? false : parent.containsKey(key));
    }
    @Override
    public F get(Object key)
    {
        if(super.containsKey(key))
            return super.get(key);
        if(parent == null)
            return null;
        return parent.get(key);
    }
    
}
