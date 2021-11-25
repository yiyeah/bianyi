class Visitor extends lab1BaseVisitor<Void>{
    static Parseredix parseredix = new Parseredix();
    String nodenumber = "0";
    int counter = 0;
    @Override
    public Void visitCompUnit(lab1Parser.CompUnitContext ctx) {
        return super.visitCompUnit(ctx);
    }

    @Override
    public Void visitFuncDef(lab1Parser.FuncDefContext ctx) {
        System.out.print("define dso_local ");
        visit(ctx.funcType());
        visit(ctx.ident());
        System.out.print("() ");
        visit(ctx.block());
        return null;
    }

    @Override
    public Void visitFuncType(lab1Parser.FuncTypeContext ctx) {
        System.out.print("i32 ");
        return null;
    }

    @Override
    public Void visitIdent(lab1Parser.IdentContext ctx) {
        System.out.print("@main ");
        return null;
    }

    @Override
    public Void visitBlock(lab1Parser.BlockContext ctx) {
        System.out.print("{ \n");
        visit(ctx.stmt());
        System.out.print("}");
        return null;
    }

    @Override
    public Void visitStmt(lab1Parser.StmtContext ctx) {       
        visit(ctx.exp());
        System.out.println("ret i32 %"+counter);
        return null;
    }
    
    @Override
    public Void visitExp(lab1Parser.ExpContext ctx) {
        // TODO Auto-generated method stub
        visit(ctx.addExp());
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
                String tmp1 =this.nodenumber;

                visit(ctx.mulExp());
                String tmp2 =this.nodenumber;

                if(ctx.Plus()!=null){
                    System.out.println("%" + (++counter) + " = add i32 " + tmp1 +", "+ tmp2);
                    this.nodenumber ="%"+this.counter;
                }
                else if(ctx.Sub()!=null){
                    System.out.println("%" + (++counter) + " = sub i32 " + tmp1 +", "+ tmp2);
                    this.nodenumber ="%"+this.counter;
                }
                break;
            }
        }
        
        return null;
    }

    @Override
    public Void visitMulExp(lab1Parser.MulExpContext ctx) {
        // TODO Auto-generated method stub
        switch(ctx.children.size()){
            case 1:{
                visit(ctx.unaryExp());
                break;
            }
            case 3:{
                visit(ctx.mulExp());
                String tmp1 = this.nodenumber;

                visit(ctx.unaryExp());
                String tmp2 = this.nodenumber;

                if(ctx.Mul()!=null){
                    System.out.println("%" + (++counter) + " = mul i32 " + tmp1 +", "+ tmp2);
                    this.nodenumber ="%"+this.counter;
                }
                else if(ctx.Div()!=null){
                    System.out.println("%" + (++counter) + " = sdiv i32 " + tmp1 +", "+ tmp2);
                    this.nodenumber ="%"+this.counter;
                }
                else if(ctx.Mod()!=null){
                    System.out.println("%" + (++counter) + " = srem i32 " + tmp1 +", "+ tmp2);
                    this.nodenumber ="%"+this.counter;
                }
                
            }
        }
        
        return null;
    }

    @Override
    public Void visitUnaryExp(lab1Parser.UnaryExpContext ctx) {
        // TODO Auto-generated method stub
        switch(ctx.children.size()){
            case 1:{
                visit(ctx.primaryExp());
                break;
            }
            case 2:{              
                visit(ctx.unaryExp());
                if(ctx.unaryOp().Sub()!=null){
                    System.out.println("%"+(++this.counter)+" = sub i32 0, "+this.nodenumber);
                    this.nodenumber = "%"+this.counter;
                }
                visit(ctx.unaryOp());             
                break;
            }
        }
        return null;
    }   

    @Override
    public Void visitPrimaryExp(lab1Parser.PrimaryExpContext ctx) {
        // TODO Auto-generated method stub
        switch(ctx.children.size()){
            case 1:{
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
    public Void visitNumber(lab1Parser.NumberContext ctx) {  
        // System.out.print("i32 "+num);
        nodenumber = getNumber(ctx);
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
