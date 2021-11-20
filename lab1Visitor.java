class Visitor extends lab1BaseVisitor<Void>{
    Parseredix parseredix = new Parseredix();

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
        System.out.print("ret ");
        visit(ctx.number());
        System.out.print("\n");
        return null;
    }

    @Override
    public Void visitNumber(lab1Parser.NumberContext ctx) {
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
        System.out.print("i32 "+num);
        return null;
    }
}

class Parseredix{
    Parseredix(){
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
