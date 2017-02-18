/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package levels.interpreter;

import java.util.TreeMap;

/**
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
