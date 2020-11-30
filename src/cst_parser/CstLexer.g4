lexer grammar CstLexer ;

@header{
	package cst_gen;
}


// Rules prefixed with fragment can be called only from other lexer rules;
// they are not tokens in their own right.

// Separators

LPAREN
	: '(' ;

RPAREN
	: ')' ;


// Boolean operators
NotOperator
	: 'not' ;

OrOperator
	: 'or' ;

AndOperator
	: 'and' ;

EqualOperator
	: '==' ;

NotEqualOperator
	: '!==' ;

GreaterOrEqualOperator
	: '>=' ;

LesserOrEqualOperator
	: '<=' ;

GreaterOperator
	: '>' ;

LesserOperator
	: '<' ;

// Literals
BooleanLiteral
	: (TRUE | FALSE) ;

TRUE
	: ('true' | 'TRUE');

FALSE
	: ('false' | 'FALSE');

// Integer Literals

IntegerLiteral
	: DecimalNumeral
	| HexNumeral
	| BinaryNumeral ;

fragment DecimalNumeral
	: '0'
	| NonZeroDigit (Digits? | Underscores Digits) ;

fragment Digits
	: Digit (DigitsAndUnderscores? Digit)? ;

fragment Digit
	: '0'
	| NonZeroDigit ;

fragment NonZeroDigit
	: [1-9] ;

fragment DigitsAndUnderscores
	: DigitOrUnderscore+ ;

fragment DigitOrUnderscore
	: Digit
	| '_' ;

fragment Underscores
	: '_'+ ;

fragment HexNumeral
	: '0' [xX] HexDigits ;

fragment HexDigits
	: HexDigit (HexDigitsAndUnderscores? HexDigit)? ;

fragment HexDigit
	: [0-9a-fA-F] ;

fragment HexDigitsAndUnderscores
	: HexDigitOrUnderscore+ ;

fragment HexDigitOrUnderscore
	: HexDigit
	| '_' ;

fragment BinaryNumeral
	: '0' [bB] BinaryDigits ;

fragment BinaryDigits
	: BinaryDigit (BinaryDigitsAndUnderscores? BinaryDigit)? ;

fragment BinaryDigit
	: [01] ;

fragment BinaryDigitsAndUnderscores
	: BinaryDigitOrUnderscore+ ;

// long bytes = 0b11010010_01101001_10010100_10010010;

fragment BinaryDigitOrUnderscore
	: BinaryDigit
	| '_' ;

// Identifier (must appear after all keywords in the grammar)

Identifier
	: ((IdentifierStart)(IdentifierPart)*) ;

fragment IdentifierStart
	: (LetterOrUnderscore | '$') ;

fragment IdentifierPart
	: ((IdentifierStart)|(Digit)) ;

fragment LetterOrUnderscore
	: (Letter | '_') ;

fragment Letter
	: [A-Za-z] ;

// Whitespace and comments
//
LINE_TERMINATOR
	: [\n] -> skip ;

WHITESPACE
	: [ \t\r\u000C]+ -> skip ;

COMMENT
	: '/*' .*? '*/' -> skip ;

LINE_COMMENT
	: '//' ~[\r\n]* -> skip ;

