parser grammar StlParser ;

@header {
	package gen;
}

options {
	tokenVocab = StlLexer ;
}

stlfile
	: stlSpecification EOF ;

stlSpecification
	: ( declaration )* ( assertion )+ ;

assertion 
	: Assertion identifier COLON expression SEMICOLON ;

declaration 
	: variableDeclaration                                         #declVariable ;

variableDeclaration
	: Constant? ioType? domainType identifier assignment? SEMICOLON ;

assignment
	: EQUAL literal 				#AsgnLiteral
	| EQUAL expression 				#AsgnExpr ;

domainType
	: DomainTypeReal
	| DomainTypeBool
	| DomainTypeInt ;

ioType
	: Input
	| Output
	| Internal ;

interval
	: (LPAREN | LBRACK) intervalTime COLON intervalTime (RPAREN | RBRACK) ;

intervalTime
	: IntegerLiteral      #intervalTimeLiteral ;

 

// -- O -- O -- O -- O -- O -- O -- O -- O -- O -- O -- O -- O -- O  expression

expression
	: primary                                                #ExprPrimary
	| NotOperator expression              					 #ExprNotUnaryExpr
	| NotOperator identifier             					 #ExprNotUnaryId
	| idComp												 #ExprIdComp

	| expression equalityCmpOp expression  				     #ExprEqualExpr
	| (RiseOperator | FallOperator) expression             	 #ExprRiseFall

	| expression AndOperator expression                      #ExprAnd
	| expression OrOperator expression                       #ExprOr
	| expression XorOperator expression                      #ExprXor
	| expression ImpliesOperator expression                  #ExprImplies
	| expression IffOperator expression                      #ExprIff

	| expression UntilOperator (interval)? expression        #ExprUntil
	| expression SinceOperator (interval)? expression        #ExprSince
	| EventuallyOperator (interval)? expression              #ExprEventually
	| OnceOperator (interval)? expression                	 #ExprOnceExpr
	| AlwaysOperator (interval)? expression              	 #ExprAlwaysExpr
	| HistoricallyOperator (interval)? expression        	 #ExprHistoricallyExpr
	| PreviousOperator expression            			 	 #ExprPreviousExpr
	| NextOperator expression            				 	 #ExprNextExpr
	| OracleOperator IntegerLiteral expression				 #ExprOracleExpr;

idComp
	: identifier ( comparisonOp | equalityCmpOp) identifier 		    #IdCompId
	| identifier ( comparisonOp | equalityCmpOp) IntegerLiteral		    #IdCompInt
	| IntegerLiteral ( comparisonOp | equalityCmpOp) identifier		    #IntCompId;

equalityCmpOp
	: EqualOperator 									 #EqOp
	| NotEqualOperator 									 #NeqOp ;

comparisonOp
	: LesserOrEqualOperator 							 #CmpOpLs
	| GreaterOrEqualOperator 							 #CmpOpGte
	| LesserOperator 									 #CmpOpLse
	| GreaterOperator									 #CmpOpGt ;

primary
	: LPAREN expression RPAREN                               #PrimaryParenthesis
	| booleanLiteral										 #PrimaryLiteralBool
	| identifier                                             #PrimaryId ;

booleanLiteral
	: LPAREN BooleanLiteral RPAREN						 	 #LiteralBoolParen
	| BooleanLiteral										 #LiteralBool ;

integerLiteral
	: LPAREN BooleanLiteral RPAREN							 #LiteralIntParen
	| IntegerLiteral										 #LiteralInt ;

literal
	: booleanLiteral										 #LiteralBoolean
	| integerLiteral										 #LiteralInteger ;

identifier
	: Identifier											 #Id ;

