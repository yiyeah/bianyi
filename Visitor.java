import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Visitor extends lab1BaseVisitor<Void>{
    static Parseredix parseredix = new Parseredix();
    static String nodenumber = "0";
    static int counter = 0;
    static int calc_value;
    static List <function> funcTable = new ArrayList<>();
    static List <varTableItem> varTable = new ArrayList<>();

    @Override
    public Void visitCompUnit(lab1Parser.CompUnitContext ctx) {
        init();
        System.out.println("declare i32 @getint()");
        System.out.println("declare void @putint(i32)");
        System.out.println("declare i32 @getch()");
        System.out.println("declare void @putch(i32)");
        return super.visitCompUnit(ctx);
    }

    @Override
    public Void visitFuncDef(lab1Parser.FuncDefContext ctx) {
        System.out.print("define dso_local ");
        visit(ctx.funcType());
        System.out.print("@"+ctx.Ident()+" () ");
        visit(ctx.block());
        return null;
    }

    @Override
    public Void visitFuncType(lab1Parser.FuncTypeContext ctx) {
        System.out.print("i32 ");
        return null;
    }

    @Override
    public Void visitBlock(lab1Parser.BlockContext ctx) {
        System.out.print("{ \n");
        for(lab1Parser.BlockItemContext blockitem: ctx.blockItem()){
            visit(blockitem);
        }   
        System.out.print("}");
        return null;
    }

    @Override
    public Void visitBlockItem(lab1Parser.BlockItemContext ctx) {
        if(ctx.decl()!=null){
            visit(ctx.decl());
        }
        else if(ctx.stmt()!=null){
            visit(ctx.stmt());
        }
        return null;
    }

    @Override
    public Void visitDecl(lab1Parser.DeclContext ctx) {
        if(ctx.constDecl()!=null){
            visit(ctx.constDecl());
        }
        else if(ctx.varDecl()!=null){
            visit(ctx.varDecl());
        }
        return null;
    }

    @Override
    public Void visitStmt(lab1Parser.StmtContext ctx) { 
        switch (ctx.children.size()) {
            case 3:{    
                //  'return' exp ';
                visit(ctx.exp());
                if(nodenumber.charAt(0)!='%'){
                    System.out.println("ret i32 "+nodenumber);
                    break;
                }
                else if(findTableByAddr(nodenumber)!=null){     //变量表中能找到
                    System.out.println("%"+ ++counter +" = load i32, i32* "+nodenumber);
                }
                System.out.println("ret i32 %"+counter);
                break;
            }
            case 4:{
                // lVal '=' exp ';'
                visit(ctx.lVal());
                String lval_addr = nodenumber;
                varTableItem tmp = findTableByAddr(lval_addr);
                if(tmp.type.equals("const")){
                    System.out.println("const lval assign error ");
                    System.exit(1);
                }

                visit(ctx.exp());
                System.out.println("store i32 "+nodenumber+", i32* "+lval_addr);
                break;
            } 
            default:{
                // exp? ';'
                if(ctx.exp()!=null)
                    visit(ctx.exp());
            }
            
        }      
        
        return null;
    }

    @Override
    public Void visitConstDecl(lab1Parser.ConstDeclContext ctx) {
        for(lab1Parser.ConstDefContext constDef: ctx.constDef()){
            visit(constDef);
        }
        return null;
    }

    @Override
    public Void visitConstDef(lab1Parser.ConstDefContext ctx) {
        visit(ctx.constInitVal());
        String ConstName = ctx.Ident().getText();

        //检查同名变量
        varTableItem tmp = findTableByName(ConstName);
        if(tmp!=null){
            System.out.println("var defined repeatedly error");
            System.exit(1);
        }
        varTableItem tableItem;
        if(nodenumber.charAt(0)!='%')
            tableItem = new varTableItem(ConstName, nodenumber, "const", nodenumber);
        else
            tableItem = new varTableItem(ConstName, ""+calc_value, "const", ""+calc_value);
        varTable.add(tableItem); 
        return null;
    }

    @Override
    public Void visitConstInitVal(lab1Parser.ConstInitValContext ctx) {
        visit(ctx.constExp());
        return null;
    }

    @Override
    public Void visitConstExp(lab1Parser.ConstExpContext ctx) {
        visit(ctx.addExp());
        return null;
    }

    @Override
    public Void visitVarDecl(lab1Parser.VarDeclContext ctx) {
        for(lab1Parser.VarDefContext varDef : ctx.varDef()){
            visit(varDef);
        }
        return null;
    }

    @Override
    public Void visitVarDef(lab1Parser.VarDefContext ctx) {
        String varName = ctx.Ident().getText();
        //检查同名变量
        varTableItem temp = findTableByName(varName);
        if(temp != null){
            System.out.println("var defined repeatedly error");
            System.exit(1);
        }

        System.out.println("%"+ ++counter+"= alloca i32");
        varTableItem tableItem = new varTableItem(varName, null, "var", ""+"%"+counter);
        varTable.add(tableItem);

        if(ctx.initVal()!= null){
            visit(ctx.initVal());
            varTableItem tmp = findTableByName(varName);
            tmp.value = nodenumber;
            System.out.println("store i32 "+nodenumber+", i32* "+tmp.address);
        }
        return null;
    }

    @Override
    public Void visitInitVal(lab1Parser.InitValContext ctx) {
        visit(ctx.exp());
        return null;
    }

    @Override
    public Void visitExp(lab1Parser.ExpContext ctx) {
        visit(ctx.addExp());
        return null;
    }

    @Override
    public Void visitLVal(lab1Parser.LValContext ctx) {
        String Lval = ctx.Ident().getText();
        varTableItem tmp = findTableByName(Lval);
        if(tmp!=null){
            nodenumber = tmp.address;
            if(tmp.value!=null&&tmp.value.charAt(0)!='%'){
                calc_value =Integer.parseInt(tmp.value);
            }
        }
        else{
            System.out.println("lval not find error");
            System.exit(1);
        }      
        return null;
    }

    @Override
    public Void visitAddExp(lab1Parser.AddExpContext ctx) {
        // TODO Auto-generated method stub
        switch(ctx.children.size()){
            case 1:{
                visit(ctx.mulExp());
                break;
            }
            case 3:{
                visit(ctx.addExp());
                String tmp1 =nodenumber;
                int v1 = calc_value;

                visit(ctx.mulExp());
                String tmp2 =nodenumber;
                int v2 = calc_value;

                if(ctx.Plus()!=null){
                    System.out.println("%" + (++counter) + " = add i32 " + tmp1 +", "+ tmp2);
                    nodenumber ="%"+counter;
                    calc_value = v1+v2;
                }
                else if(ctx.Sub()!=null){
                    System.out.println("%" + (++counter) + " = sub i32 " + tmp1 +", "+ tmp2);
                    nodenumber ="%"+counter;
                    calc_value = v1-v2;
                }
                break;
            }
        }
        
        return null;
    }

    @Override
    public Void visitMulExp(lab1Parser.MulExpContext ctx) {
        switch(ctx.children.size()){
            case 1:{
                visit(ctx.unaryExp());
                break;
            }
            case 3:{
                visit(ctx.mulExp());
                String tmp1 = nodenumber;
                int v1 = calc_value;

                visit(ctx.unaryExp());
                String tmp2 = nodenumber;
                int v2 = calc_value;

                if(ctx.Mul()!=null){
                    System.out.println("%" + (++counter) + " = mul i32 " + tmp1 +", "+ tmp2);
                    nodenumber ="%"+counter;
                    calc_value = v1*v2;
                }
                else if(ctx.Div()!=null){
                    System.out.println("%" + (++counter) + " = sdiv i32 " + tmp1 +", "+ tmp2);
                    nodenumber ="%"+counter;
                    calc_value = v1/v2;
                }
                else if(ctx.Mod()!=null){
                    System.out.println("%" + (++counter) + " = srem i32 " + tmp1 +", "+ tmp2);
                    nodenumber ="%"+counter;
                    calc_value =v1%v2;
                }
                
            }
        }
        
        return null;
    }

    @Override
    public Void visitUnaryExp(lab1Parser.UnaryExpContext ctx) {
        switch(ctx.children.size()){
            case 1:{
                visit(ctx.primaryExp());
                break;
            }
            case 2:{              
                visit(ctx.unaryExp());
                if(ctx.unaryOp().Sub()!=null){
                    System.out.println("%"+(++counter)+" = sub i32 0, "+nodenumber);
                    nodenumber = "%"+counter;
                }
                visit(ctx.unaryOp());             
                break;
            }
            default:{   //Ident '(' funcParams? ')'
                String funcName = ctx.Ident().getText();
                function func = findLibFuc(funcName);       
                if(func!=null){
                    if(ctx.funcParams()!=null){
                        visit(ctx.funcParams());
                    }
                    else 
                        nodenumber = "";

                    exeFunc(funcName, nodenumber);                    
                }
                else{
                    System.out.println("func not found error");
                    System.exit(1);
                }
            }
        }
        return null;
    }   

    @Override
    public Void visitPrimaryExp(lab1Parser.PrimaryExpContext ctx) {
        switch(ctx.children.size()){
            case 1:{
                if(ctx.lVal()!=null){
                    visit(ctx.lVal());
                    if(nodenumber.charAt(0)=='%'){//是变量而非常量
                        System.out.println("%"+ ++counter+"= load i32, i32* "+nodenumber);
                        nodenumber = "%"+counter;
                    }
                }
                if(ctx.number()!=null)
                    visit(ctx.number());
                break;
            }
            case 3:{
                visit(ctx.exp());
                break;
            }
        }
        return null;
    }

    @Override
    public Void visitUnaryOp(lab1Parser.UnaryOpContext ctx) {
        // TODO Auto-generated method stub
        return null;
    }

    
    @Override
    public Void visitFuncParams(lab1Parser.FuncParamsContext ctx) {
        String param = "";
        for(lab1Parser.ExpContext tmpExp: ctx.exp()){
            visit(tmpExp);
            param = nodenumber;
        }
        nodenumber = param;
        return null;
    }

    @Override
    public Void visitNumber(lab1Parser.NumberContext ctx) {  
        // System.out.print("i32 "+num);
        nodenumber = getNumber(ctx);
        calc_value = Integer.parseInt(getNumber(ctx));
        return null;
    }

   @Override
    public Void visitBType(lab1Parser.BTypeContext ctx) {
        // TODO Auto-generated method stub
        return null;
    }

    public static String getNumber(lab1Parser.NumberContext ctx){
        String num="";
        if(ctx.Decimal_const()!=null){
            num = ctx.Decimal_const().getText();
        }
        else if(ctx.Hexadecimal_const()!=null){
            num = parseredix.parseHex(ctx.Hexadecimal_const().getText());
        }
        else if(ctx.Octal_const()!=null){
            num = parseredix.parseOct(ctx.Octal_const().getText());
        }

        return num;
    }

    public static varTableItem findTableByName(String varName){
        for(varTableItem tmp :varTable){
            if(tmp.varName.equals(varName))
                return tmp;
        }
        return null;
    }

    public static varTableItem findTableByAddr(String addr){
        for(varTableItem tmp :varTable){
            if(tmp.address.equals(addr))
                return tmp;
        }
        return null;
    }

    public static function findLibFuc(String funcName){
        for(function tmp: funcTable){
            if(tmp.funcName.equals(funcName))
                return tmp;
        }
        return null;
    }

    public static void exeFunc(String funcName, String param){
        function func = findLibFuc(funcName); 

        if(func.retType.equals("void")){
            if(param.isEmpty()){
                System.out.println("func putint or putch no param error");
                System.exit(1);
            }
            System.out.println("call void @"+func.funcName+"("+"i32 "+param+")");
        }

        else if(func.retType.equals("int")){
            System.out.println("%"+ ++counter+" = call i32 @"+func.funcName+"("+param+")");
            nodenumber = "%"+counter;
        }
    }

    public static void init(){
        function getint = new function("getint", "int", false);
        function putint = new function("putint", "void", true);
        function getch = new function("getch", "int", false);
        function putch = new function("putch", "void", true);

        funcTable.add(getint);
        funcTable.add(putint);
        funcTable.add(getch);
        funcTable.add(putch);
    }    
}

class Parseredix{
    public String parse(String input){
        if(input.charAt(0)=='0'){
            if(input.length()==1)
                return "0";
            else{
                if(input.charAt(1)=='x'||input.charAt(1)=='X'){
                    return this.parseHex(input);
                }
                else
                    return this.parseOct(input);
            }
        }
        else
            return input;
    }
    public String parseHex(String input){
        return ""+Integer.parseInt(input.substring(2),16);
    }
    public String parseOct(String input){
        if(input.equals("0"))
            return "0";
        return ""+Integer.parseInt(input.substring(1),8);
    }
}

class varTableItem{
    String varName;
    String value;
    String type;
    String address;
    public varTableItem(String varName, String value, String type, String address) {
        this.varName = varName;
        this.value = value;
        this.type = type;
        this.address = address;
    }
}

class function{
    String funcName;
    String retType;
    boolean hasParam;
    Scanner in = new Scanner(System.in);
    public function(String funcName, String retType, boolean hasParam) {
        this.funcName = funcName;
        this.retType = retType;
        this.hasParam = hasParam;
    }
}
