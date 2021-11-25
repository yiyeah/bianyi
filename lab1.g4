//lab1.g4
grammar lab1;
//
Hexadecimal_prefix : '0x' | '0X';
Decimal_const      : [0-9] | [1-9] [0-9]+;
Octal_const        : '0' [0-7]*;
Hexadecimal_const  : Hexadecimal_prefix [0-9a-zA-Z]+;

Plus               : '+';
Sub                : '-';
Mul                : '*';
Div                : '/';
Mod                : '%';
WHITE_SPACE: [ \r\n\t]+ -> skip;
//
compUnit : funcDef;
funcDef  : funcType ident '(' ')' block;
funcType : 'int';
ident    : 'main';
block    : '{' stmt '}';
stmt     : 'return' exp ';';
exp      : addExp;
addExp   : mulExp | addExp ( Plus | Sub) mulExp;
mulExp   : unaryExp | mulExp ( Mul | Div | Mod ) unaryExp;
unaryExp : primaryExp | unaryOp unaryExp;
primaryExp : number |'(' exp ')' ;
unaryOp    : Plus | Sub;
number   : Decimal_const |Octal_const | Hexadecimal_const;

