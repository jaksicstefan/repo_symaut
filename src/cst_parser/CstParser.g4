parser grammar CstParser ;

@header {
	package cst_gen;
}

options {
	tokenVocab = CstLexer ;
}


// -- O -- O -- O -- O -- O -- O -- O -- O -- O -- O -- O -- O -- O  expression
cst:
	expression EOF											 #CstExpr; //

expression
	: primary                                                #ExprPrimary
	| NotOperator expression              					 #ExprNotUnaryExpr  //
	| NotOperator identifier             					 #ExprNotUnaryId	//

	| identifier ( comparisonOp | equalityCmpOp) identifier 		    #IdCompId   //
	| identifier ( comparisonOp | equalityCmpOp) integerLiteral		    #IdCompInt0 //
	| integerLiteral ( comparisonOp | equalityCmpOp) identifier		    #IdCompInt1 //
	| integerLiteral ( comparisonOp | equalityCmpOp) integerLiteral	    #IdCompInt2 //

	| expression equalityCmpOp expression  				     #ExprEqualExpr //
	| expression AndOperator expression                      #ExprAnd //
	| expression OrOperator expression                       #ExprOr; //

equalityCmpOp
	: EqualOperator 									 #EqOp   //
	| NotEqualOperator 									 #NeqOp ;//

comparisonOp
	: LesserOrEqualOperator 							 #CmpOpLs  //
	| GreaterOrEqualOperator 							 #CmpOpGte //
	| LesserOperator 									 #CmpOpLse //
	| GreaterOperator									 #CmpOpGt ;//

primary
	: LPAREN expression RPAREN                               #PrimaryParenthesis //THIS IS NOT COVERED IN VISITOR
	| booleanLiteral										 #PrimaryLiteralBool //
	| identifier                                             #PrimaryId ; //

booleanLiteral
	: LPAREN BooleanLiteral RPAREN						 	 #LiteralBoolParen//
	| BooleanLiteral										 #LiteralBool ;//

integerLiteral
	: LPAREN IntegerLiteral RPAREN							 #LiteralIntParen //
	| IntegerLiteral										 #LiteralInt ; //

identifier
	: Identifier											 #Id ; //

