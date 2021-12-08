//lab1.g4
grammar lab1;
//
Hexadecimal_prefix : '0x' | '0X';
Decimal_const      : [0-9] | [1-9] [0-9]+;
Octal_const        : '0' [0-7]*;
Hexadecimal_const  : Hexadecimal_prefix [0-9a-zA-Z]+;
Ident              : ('_' | [a-zA-Z]) | ('_' | [a-zA-Z]) ([0-9] | ('_' | [a-zA-Z])) +;

Plus               : '+';
Sub                : '-';
Mul                : '*';
Div                : '/';
Mod                : '%';
Not                : '!';
Equal              : '==';
NEqual             : '!=';
Lt                 : '<';
Le                 : '<=';
Gt                 : '>';
Ge                 : '>=';


WHITE_SPACE: [ \r\n\t]+ -> skip;

//语法分析
//
compUnit : decl* funcDef;
funcDef  : funcType Ident '(' ')' block;
funcType : 'int';
block    : '{' blockItem* '}';
blockItem: decl | stmt;
decl     : constDecl | varDecl;
//const
constDecl: 'const' bType constDef (',' constDef)* ';';
bType    : 'int';
constDef : Ident '=' constInitVal;
constInitVal : constExp;
constExp : addExp;
//var
varDecl  : bType varDef (',' varDef)* ';';
varDef   : Ident | Ident '=' initVal;
initVal  : exp;

stmt     :  lVal '=' exp ';'  | exp? ';' | 'return' exp ';'
            | 'if' '(' cond ')' stmt ('else' stmt)? | block;
lVal     : Ident;
exp      : addExp;
addExp   : mulExp | addExp ( Plus | Sub) mulExp;
mulExp   : unaryExp | mulExp ( Mul | Div | Mod ) unaryExp;
unaryExp : primaryExp | unaryOp unaryExp | Ident '(' funcParams? ')';
primaryExp : number | lVal |'(' exp ')' ;
unaryOp    : Plus | Sub | Not;
number   : Decimal_const |Octal_const | Hexadecimal_const;
//func
funcParams: exp (',' exp)*;
//cond
cond     : lOrExp;
lOrExp   : lAndExp| lOrExp '||' lAndExp;
lAndExp  : eqExp | lAndExp '&&' eqExp ;
eqExp    : relExp | eqExp (Equal | NEqual) relExp;
relExp   : addExp | relExp (Lt | Le | Gt | Ge) addExp;

