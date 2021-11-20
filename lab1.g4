//lab1.g4
grammar lab1;
//
Hexadecimal_prefix : '0x' | '0X';
Nonzero_digit      : '1' | '2' | '3' | '4' | '5' | '6' | '7' | '8' | '9';
Octal_digit        : '0' | '1' | '2' | '3' | '4' | '5' | '6' | '7';
Digit              : '0' | Nonzero_digit;

Hexadecimal_digit  : '0' | '1' | '2' | '3' | '4' | '5' | '6' | '7' | '8' | '9' | 'a' | 'b' | 'c' | 'd' | 'e' | 'f' | 'A' | 'B' | 'C' | 'D' | 'E' | 'F';
Decimal_const      : Digit | Nonzero_digit Decimal_const;
Octal_const        : '0' Octal_digit*;
Hexadecimal_const  : Hexadecimal_prefix Hexadecimal_digit+;
WHITE_SPACE: [ \r\n\t]+ -> skip;
//
compUnit : funcDef;
funcDef  : funcType ident '(' ')' block;
funcType : 'int';
ident    : 'main';
block    : '{' stmt '}';
stmt     : 'return' number ';';
number   : Decimal_const |Octal_const | Hexadecimal_const;

