/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package levels.parseutil;

/**
 *
 * @author Edgar Lin
 */
public class Symbol {
    private String symbol;
    public Symbol(String symbol)
    {
        this.symbol = symbol;
    }
    public String getSymbol()
    {
        return symbol;
    }
    @Override
    public String toString()
    {
        return symbol;
    }
}
