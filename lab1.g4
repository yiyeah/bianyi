//lab1.g4
grammar lab1;
//
Plus               : '+';
Sub                : '-';
Mul                : '*';
Div                : '/';
Mod                : '%';
Not                : '!';
Logic_And          : '&';
Equal              : '==';
NEqual             : '!=';
Lt                 : '<';
Le                 : '<=';
Gt                 : '>';
Ge                 : '>=';
If                 : 'if';
While              : 'while';
Break              : 'break';
Continue           : 'continue';
Return             : 'return';
Void               : 'void';

Hexadecimal_prefix : '0x' | '0X';
Decimal_const      : [0-9] | [1-9] [0-9]+;
Octal_const        : '0' [0-7]*;
Hexadecimal_const  : Hexadecimal_prefix [0-9a-zA-Z]+;
Ident              : ('_' | [a-zA-Z]) | ('_' | [a-zA-Z]) ([0-9] | ('_' | [a-zA-Z])) +;

WHITE_SPACE: [ \r\n\t]+ -> skip;

//语法分析
treeRoot : compUnit;
compUnit :  (funcDef | decl) (compUnit)?;
funcDef  : funcType Ident '(' (funcFParams)? ')' block;
funcType : 'int' | Void;
block    : '{' blockItem* '}';
blockItem: decl | stmt;
decl     : constDecl | varDecl;
//const
constDecl: 'const' bType constDef (',' constDef)* ';';
bType    : 'int';
constDef : Ident ('[' constExp ']')* '=' constInitVal;
constInitVal : constExp 
             | '{' ( constInitVal (',' constInitVal)* )? '}';
constExp : addExp;
//var
varDecl  : bType varDef (',' varDef)* ';';
varDef   : Ident ('[' constExp ']')* 
         | Ident ('[' constExp ']')* '=' initVal;
initVal  : exp | '{' ( initVal ( ',' initVal)* )? '}';

stmt     :  lVal '=' exp ';'  | exp? ';' | 'return' exp ';'
            | If '(' cond ')' stmt ('else' stmt)? | block 
            | While '(' cond ')' stmt | Continue ';' | Break ';' | Return (exp)? ';';
lVal     : Ident ('[' exp ']')*;
exp      : addExp;
addExp   : mulExp | addExp ( Plus | Sub) mulExp;
mulExp   : unaryExp | mulExp ( Mul | Div | Mod ) unaryExp;
unaryExp : primaryExp | unaryOp unaryExp | Ident '(' funcRParams? ')';
primaryExp : number | lVal |'(' exp ')' ;
unaryOp    : Plus | Sub | Not;
number   : Decimal_const |Octal_const | Hexadecimal_const;
//func
funcRParams: exp (',' exp)*;
funcFParams: funcFParam (',' funcFParam)*;
funcFParam : bType Ident ('[' ']' ( '[' exp ']' )* )?;
//cond
cond     : lOrExp;
lOrExp   : lAndExp| lOrExp '||' lAndExp;
lAndExp  : eqExp | lAndExp Logic_And Logic_And eqExp ;
eqExp    : relExp | eqExp (Equal | NEqual) relExp;
relExp   : addExp | relExp (Lt | Le | Gt | Ge) addExp;

