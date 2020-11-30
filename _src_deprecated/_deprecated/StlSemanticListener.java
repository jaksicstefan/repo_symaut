//package frontend;
package _deprecated;

import org.antlr.v4.runtime.tree.TerminalNode;

import gen.StlParserBaseListener;

/***
 * very basic listener, used to test the parser.
 * @author JaksicS
 *
 */
public class StlSemanticListener extends StlParserBaseListener{

	public void visitTerminal(TerminalNode node) {
		System.out.print(node.toString()+" ");
	}

	
}
