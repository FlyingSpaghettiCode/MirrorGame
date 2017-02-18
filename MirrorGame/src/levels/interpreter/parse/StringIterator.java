/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package levels.interpreter.parse;

import java.util.Iterator;

/**
 * Keeps track of one's position in a string; 
 * @author Edgar Lin
 */
public class StringIterator implements Iterator {

    private String string;
    private int index;
    
    public StringIterator(String string)
    {
        this.string = string;
        this.index = 0;
    }
    
    @Override
    public boolean hasNext() {
        return index < string.length();
    }

    @Override
    public Object next() {
        return string.charAt(index++);
    }
    public String next(int index){
        String ret = string.substring(this.index, this.index + index);
        this.index += index;
        return ret;
    }
    public String content(){
        return string.substring(index);
    }
    public char peek() {
        return string.charAt(0);
    }
    
    public void trim(){
        string = string.substring(index).trim();
        index = 0;
    }
    
}
