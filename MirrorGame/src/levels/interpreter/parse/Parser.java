/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package levels.interpreter.parse;

import levels.interpreter.Form;
import levels.interpreter.Symbol;
import java.util.List;

/**
 * Contains a utility methods for parsing level script. 
 * @author Edgar Lin
 */
public class Parser {
    public static Object parse(StringIterator code)
        {
            code.trim();
            char first = (char) code.next();
            
            
            switch(first)
            {
                case '\"':
                    return parseString(code);
                case '(':
                    Form elements = new Form();
                    do
                    {
                        elements.add(parse(code));
                    }
                    while((char)code.next()!=')');
                    return elements;
                default:
                    String symbol = first + code.next(Math.min(//tokenizes to next space or close paren
                            code.content().indexOf(' ')&0xffff,//&0xffff cpnverts to unsigned so that error (-1) >> any real value
                            code.content().indexOf(')')&0xffff));
                    try
                    {
                        return Integer.parseInt(symbol);
                    }
                    catch(NumberFormatException e)
                    {
                        try
                        {
                            return Double.parseDouble(symbol);
                        }
                        catch(NumberFormatException f)
                        {
                            switch(symbol) // handles special tokens (null, true, false); otherwise, treats it as a name.
                            {
                                case "true":
                                    return true;
                                case "false":
                                    return false;
                                case "null":
                                    return null;
                                default:
                                    return new Symbol(symbol);
                            }
                        }
                    }
                    
            }
        }
	
        public static String parseString(StringIterator code)
        {
            String pstring = "";
            char next;
            do
            {
                pstring += code.next(Math.min( // gets the string up to the next escape or quote
                        code.content().indexOf("\\")&0xffff, // &0xffff converts to unsigned; this causes error (-1) to be >> than any value
                        code.content().indexOf("\"")&0xffff));
                next = (char) code.next();
                if(next == '\\')
                {
                    switch((char)code.next())
                    {
                        case '\"':
                            pstring += '\"';
                            break;
                        case '\\':
                            pstring += '\"';
                            break;
                    }
                }
                
            }
            while(next != '\"');
            
            return pstring;
        }
        // Removes line comments and consolidates into single string to ease parsing.
        // Leading and trailing spaces removed. Line breaks converted to spaces.
	public static String simplifyLines(List<String> ls){
                String ret = "";
		for(String i:ls)
                {
                    String a = i.trim(); // removes leading and trailing whitespace
                    a = a.substring(0, a.indexOf('#')); // removes line comments
                    if(!a.equals(""))
                        ret += " " + a;
                }
                return ret;
	}


}
