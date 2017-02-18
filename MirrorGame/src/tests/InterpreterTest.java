/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tests;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import levels.LevelReader;
import levels.interpreter.CactusStackMapNode;
import levels.interpreter.Interpreter;
import levels.interpreter.commands.StdLibrary;
import levels.interpreter.parse.Parser;
import levels.interpreter.parse.StringIterator;

/**
 * Provides a command line interface in order to test the script parser and 
 * interpreter
 * @author Edgar Lin
 */
public class InterpreterTest {
    public static void main(String[] args) throws IOException
    {
        LevelReader reader = new LevelReader();
        reader.initializeInterpreter();
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        CactusStackMapNode symbols = new CactusStackMapNode(new StdLibrary(null));
        while(true)
        {
            Stack stack = new Stack();
            System.out.print(">>>");
            Interpreter.interpret(Parser.parse(new StringIterator(in.readLine())), stack, symbols);
            System.out.println(stack);
        }
    }
}
