package test;

import java.io.IOException;

import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import constraint.ConstraintGenVisitor;
import constraint.Constraint;
import cst_gen.CstLexer;
import cst_gen.CstParser;

public class TestConstraintGenVisitor {

	public static void main(String[] args) {

		CstLexer lexer;
		CommonTokenStream tokens;
		CstParser parser;
		ParseTree tree = null;

		try {
			lexer = new CstLexer(CharStreams.fromFileName("src\\cst_parser\\cst2parse"));
			tokens = new CommonTokenStream(lexer);

			System.out.println("Tokens: " + tokens.toString());

			parser = new CstParser(tokens);
			tree = parser.cst();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		ConstraintGenVisitor visitor = new ConstraintGenVisitor();

		System.out.println("\n\t\t-------------------------------------------------------------------\n");
		System.out.println("Stage 1, tree parsed:" + tree.toStringTree());

		// TODO = semantic analysis
		// StlSemanticListener stlListener = new StlSemanticListener();
		// walker.walk(stlListener, tree);

		// WE WANT TO USE VISITORS
		Constraint top_cst = visitor.visit(tree);

		System.out.println("Done:");
		System.out.println("" + top_cst);
	}

}
