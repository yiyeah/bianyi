import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javax.lang.model.element.Element;

class Visitor extends lab1BaseVisitor<Void>{
    static Parseredix parseredix = new Parseredix();
    static String nodenumber = "0";
    static int node_type;
    static int counter = 0;
    static int calc_value = 0;
    static boolean calc_value_flag = false;
    static List <function> funcTable = new ArrayList<>();
    static List <varTableItem> globalvarTable = new ArrayList<>();

    static boolean zext_i1 = false;
    static boolean global = true;

    static blockTreeNode root = new blockTreeNode(0, null);
    static List<blockTreeNode> blockTree = new ArrayList<>();
    static int[] blockStart ={0,};
    static blockTreeNode curBlock = root;
    
    static String loopNow="0";
    static List<blockTreeNode> breaknodeStack = new ArrayList<>();

    static varTableItem tmpArr;
    static boolean InArray = false;
    static int deep=0;
    static int[] arr_dim_data;
    static int[] visit_arr_index;
    static String arr_address;
    static int index=0;

    static List <varTableItem> changetable = new ArrayList<>();
    static function tmpFunc;
    static int defType;
    static String[] params;
    static int[] everyParamsType;
    static boolean inparams = false;
    static boolean arr_as_param =false;
    static int paramdim;
    Visitor(){
        init();
       System.out.println("declare i32 @getint()");
       System.out.println("declare void @putint(i32)");
       System.out.println("declare i32 @getch()");
       System.out.println("declare void @putch(i32)");
       System.out.println("declare void @memset(i32*  ,i32 ,i32 )");
       System.out.println("declare i32 @getarray(i32*)");
       System.out.println("declare void @putarray(i32, i32*)");
    }

    @Override
    public Void visitTreeRoot(lab1Parser.TreeRootContext ctx) {
        visit(ctx.compUnit());
        return null;
    }

    @Override
    public Void visitCompUnit(lab1Parser.CompUnitContext ctx) {
       if(ctx.decl()!=null){
            visit(ctx.decl());
       }
       else if(ctx.funcDef()!=null){
           visit(ctx.funcDef());
       }
       if(ctx.compUnit()!=null){
           visit(ctx.compUnit());
       }
       //System.out.println(blockTree.size());
        return null;
    }

    @Override
    public Void visitFuncDef(lab1Parser.FuncDefContext ctx) {
            
        String funcName = ctx.Ident().getText();
        String retType = ctx.funcType().Void()==null? "int" : "void" ;
        Boolean hasParam = ctx.funcFParams() == null? false : true;
        if(!funcName.equals("main")){
            function fuc = new function(funcName, retType, hasParam);
            tmpFunc = fuc;
            funcTable.add(fuc);
        }

        blockTree.clear();;
        root = new blockTreeNode(0, null);
        curBlock = root;
        blockTree.add(root);
        //进入一个fucdef就相当于新的root      
        
        System.out.print("define dso_local ");
        visit(ctx.funcType());      
        System.out.print("@"+funcName+" (");

        counter = 0;
        global = false;
        if(ctx.funcFParams()!=null){
            visit(ctx.funcFParams());
        }
        System.out.print(")");
        
        visit(ctx.block());
        if(funcName.equals("main")){
            blockTreeNode.globalvarTable = globalvarTable;
        }else{
            global = true;
        }
        this.printBuff();
        
        return null;
    }

    @Override
    public Void visitFuncFParams(lab1Parser.FuncFParamsContext ctx) {
        int paramCnt = ctx.funcFParam().size(),i=0;
        tmpFunc.paramCnt = paramCnt;
        tmpFunc.paramType = new int[paramCnt];
        for(lab1Parser.FuncFParamContext tmp:ctx.funcFParam()){
            visit(tmp);
            tmpFunc.paramType[i] = defType;
            if(i==0)
                System.out.print("i32");
            else
                System.out.print(",i32");
            if(defType!=0)
                System.out.print("*");
            System.out.print(" %"+ counter+" ");
            counter++;
            i++;
        }

        return null;
    }

    @Override
    public Void visitFuncFParam(lab1Parser.FuncFParamContext ctx) {
        String varName = ctx.Ident().getText();
        switch(ctx.children.size()){
            case 2 :{
                // bType Ident
                defType = 0;
                varTableItem tableItem = new varTableItem(varName, null, "var", "%"+counter);
                changetable.add(tableItem);
                curBlock.varTable.add(tableItem);
                break;
            }
            default:{
                //bType Ident ('[' ']' ( '[' exp ']' )* )?
                varTableItem tableItem = new varTableItem(varName, null, "array", "%"+counter);
                defType = ctx.exp().size()+1;
                arrayStruct arr =new arrayStruct();
                arr.isParam = true;
                arr.dim = defType;           
                int[] every_dim = new int[defType];
                every_dim[0]=100;
                int i=0;
                for(lab1Parser.ExpContext tmpExp: ctx.exp()){
                    visit(tmpExp);
                    every_dim[i+1]=calc_value;
                    i++;
                }
                arr.every_dim = every_dim;
                tableItem.array = arr;
                curBlock.varTable.add(tableItem);
                break;
            }
        }
        return null;
    }

    @Override
    public Void visitFuncType(lab1Parser.FuncTypeContext ctx) {
        if(ctx.Void()==null)
            System.out.print("i32 ");
        else
            System.out.print("void ");
        return null;
    }

    @Override
    public Void visitBlock(lab1Parser.BlockContext ctx) {
        if(ctx.getParent().children.size()== 5 || ctx.getParent().children.size()== 6){
            //funcDef  : funcType Ident '(' funcFParams?')' block  
            curBlock.saveBuf("{ \n",false);     
            for(varTableItem changeTmp: changetable){
                curBlock.saveBuf("%"+ (++counter) +"= alloca i32", true);
                String changeNum = changeTmp.address;
                curBlock.saveBuf("store i32 "+ changeNum +", i32* %"+ (counter), true);
                changeTmp.address = "%"+ counter;
            }
            changetable.clear();

            for(lab1Parser.BlockItemContext blockitem: ctx.blockItem()){
                visit(blockitem);
            }   
            if(need_ret(curBlock))
                curBlock.saveBuf("ret void", true);
            curBlock.saveBuf("}",true);
        }
        else if(ctx.getParent().getParent().children.size() == 5 || ctx.getParent().getParent().children.size() == 7){
            //'if' '(' cond ')' stmt ('else' stmt)?  
            //While ( cond ) stmt
            for(lab1Parser.BlockItemContext blockitem: ctx.blockItem()){
                visit(blockitem);
            }
        }
        else{
            //create new block     
            blockTreeNode save_curblock = curBlock;
            blockTreeNode tmp = new blockTreeNode(counter, curBlock);
            curBlock.otherBlocks.add(tmp);
            //blockTree.add(tmp);
            curBlock = tmp;       
            for(lab1Parser.BlockItemContext blockitem: ctx.blockItem()){
                visit(blockitem);
            }
            String innerblockbuf = curBlock.buf;
            curBlock = save_curblock;
            curBlock.buf+= innerblockbuf;
        }
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
            case 1:{
                // block
                if(ctx.block()!=null){
                    visit(ctx.block());
                }
                    break;
            }
            case 2:{
                // exp? ';' || contine ';' || break ';' ||Return ';'
                if(ctx.Break()!=null){
                    breaknodeStack.add(curBlock);
                    return null;
                }
                else if(ctx.Continue()!=null){
                    curBlock.saveBuf("br label "+ loopNow, true);
                }
                else if(ctx.exp()!=null){
                    visit(ctx.exp());
                }
                else if(ctx.Return()!=null){
                    curBlock.saveBuf("ret void",true);
                }
                break;
            }
            case 3:{    
                //  'return' exp ';
                visit(ctx.exp());
                if(nodenumber.charAt(0)!='%'){
                    curBlock.saveBuf("ret i32 "+nodenumber, true);
                    break;
                }
                else if(curBlock.findTableByAddr(nodenumber)!=null){     //变量表中能找到
                    curBlock.saveBuf("%"+ ++counter +" = load i32, i32* "+nodenumber, true);
                }
                curBlock.saveBuf("ret i32 %"+counter, true);
                break;
            }
            case 4:{
                // lVal '=' exp ';'
                visit(ctx.lVal());
                String lval_addr = nodenumber;
                varTableItem tmp =curBlock.findTableByAddr(lval_addr);
                if(tmp!=null&&tmp.type.equals("const")){
                        //array tmp is null
                    System.out.println("const lval assign error ");
                    System.exit(1);
                }

                visit(ctx.exp());
                if(nodenumber.equals("%void")){
                    System.out.println("void assign error");
                    System.exit(1);
                }
                curBlock.saveBuf("store i32 "+nodenumber+", i32* "+lval_addr, true);
                break;
            } 
            case 5:{
                // If '(' cond ')' stmt || While ( cond ) stmt
                if(ctx.If()!=null){        
                    visit(ctx.cond());

                    blockTreeNode save_curnode = curBlock;

                    String iftrue = ++counter+":";
                    curBlock.saveBuf("br i1 "+nodenumber+",label %"+ counter+", label ", false);

                    blockTreeNode truenode = new blockTreeNode(counter, curBlock);
                    blockTree.add(truenode);
                    curBlock.jmpTrueBlock = truenode;
                    curBlock = truenode;
                    curBlock.saveBuf(iftrue, true);

                    lab1Parser.StmtContext tmp = ctx.stmt().get(0);         
                    visit(tmp);
                    if(need_br(curBlock))
                        curBlock.saveBuf("br label %"+ (counter+1),true);

                    curBlock = save_curnode;
                    
                    String dest = ++counter + ":";
                    curBlock.saveBuf("%"+counter, true);
                    
                    blockTreeNode destnode = new blockTreeNode(counter, curBlock);
                    blockTree.add(destnode);
                    curBlock.destBlock= destnode;
                    curBlock = destnode;
                    curBlock.saveBuf(dest, true);
                }
                else if(ctx.While()!=null){
                    curBlock.saveBuf("br label %"+ ++counter, true);
                    curBlock.saveBuf(counter+":", true);
                    String loopstart ="%"+counter;
                    String save_loopNow="0";
                    if(!loopNow.equals("0")){
                        save_loopNow = loopNow;
                    }
                    loopNow = loopstart;

                    visit(ctx.cond());

                    blockTreeNode save_curnode = curBlock;
                    String loop = ++counter+":";
                    curBlock.saveBuf("br i1 "+nodenumber+",label %"+ counter+", label ", false);

                    blockTreeNode loopnode = new blockTreeNode(counter, curBlock);
                    blockTree.add(loopnode);
                
                    curBlock.loopBlock= loopnode;
                    curBlock = loopnode;
                    curBlock.saveBuf(loop, true);

                    lab1Parser.StmtContext tmp = ctx.stmt().get(0);         
                    visit(tmp);
                    if(need_br(curBlock))
                        curBlock.saveBuf("br label "+ loopstart,true);
                    curBlock = save_curnode;
                 
                    String dest = ++counter+":";
                    curBlock.saveBuf("%"+counter, true);

                    for(blockTreeNode breaknode: breaknodeStack){
                        breaknode.saveBuf("br label %"+counter, true);
                    }
                    breaknodeStack.clear();;

                    blockTreeNode destnode = new blockTreeNode(counter, curBlock);
                    blockTree.add(destnode);
                    curBlock.destBlock= destnode;
                    curBlock = destnode;
                    curBlock.saveBuf(dest, true);
                    if(!save_loopNow.equals("0"))
                        loopNow = save_loopNow;
                }
                break; 
            }
            case 7:{
                // 'if' '(' cond ')' stmt 'else' stmt
                visit(ctx.cond());
                blockTreeNode save_curnode = curBlock;
                curBlock.saveBuf("br i1 "+nodenumber+",label %"+ ++counter+", label ", false);

                //true
                String iftrue = counter+":";            
                blockTreeNode truenode = new blockTreeNode(counter, curBlock);
                blockTree.add(truenode);
                curBlock.jmpTrueBlock = truenode;
                curBlock = truenode;
                curBlock.saveBuf(iftrue, true);

                lab1Parser.StmtContext tmp = ctx.stmt().get(0);         
                visit(tmp);
                
                curBlock = save_curnode;
                
                //false
                String iffalse = ++counter+":";
                curBlock.saveBuf("%"+counter, true);


                blockTreeNode falsenode = new blockTreeNode(counter, curBlock);
                blockTree.add(falsenode);
                curBlock.jumFalseBlock = falsenode;
                curBlock = falsenode;
                curBlock.saveBuf(iffalse, true);

                tmp = ctx.stmt().get(1);         
                visit(tmp);
                if(curBlock!=root && curBlock.parBlock.destBlock!=null&&curBlock.parBlock.destBlock.equals(curBlock) &&need_br(curBlock)){
                    curBlock.saveBuf("br label %"+(counter+1) ,true);
                }

                curBlock = save_curnode;

                String dest = ++counter+":";
                //存在隐患，应遍历从该节点出发的叶节点
                if(curBlock.jmpTrueBlock.jmpTrueBlock==null && need_br(curBlock.jmpTrueBlock))
                    curBlock.jmpTrueBlock.saveBuf("br label %"+counter,true);
                if(curBlock.jmpTrueBlock.destBlock!=null && need_br(curBlock.jmpTrueBlock.destBlock))
                    curBlock.jmpTrueBlock.destBlock.saveBuf("br label %"+counter,true);
                if(curBlock.jumFalseBlock.jmpTrueBlock==null && need_br(curBlock.jumFalseBlock))
                    curBlock.jumFalseBlock.saveBuf("br label %"+counter,true);

                blockTreeNode destnode = new blockTreeNode(counter, curBlock);
                blockTree.add(destnode);
                curBlock.destBlock= destnode;
                curBlock = destnode;
                curBlock.saveBuf(dest, true);

                break;
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
        String ConstName = ctx.Ident().getText();
        if(ctx.children.size()>3){
            //数组def
            varTableItem tmp =curBlock.checkTableByName(ConstName);
            if(tmp!=null){
                System.out.println("var defined repeatedly error");
                System.exit(1);
            }
            
            varTableItem tableItem;
            
            arrayStruct arr =new arrayStruct();
            int i=0,len=1;
            int dim = ctx.constExp().size();
            arr.every_dim = new int[dim];
            for(lab1Parser.ConstExpContext dimDef:ctx.constExp()){
                visit(dimDef);
                arr.every_dim[i] = calc_value;
                len*=calc_value;
                i++;
            }

            if(!global)
                tableItem = new varTableItem(ConstName, "0", "array", "%"+ ++counter+"");
            else
                tableItem = new varTableItem(ConstName, "0", "array", "@"+ConstName);

            arr.dim = dim;
            arr.len = len;
            arr.data = new String[len];
            for(int j=0;j<len;j++){
                arr.data[j]="0";
            }
            tableItem.array=arr;
            tmpArr = tableItem;        
            if(!global){
                curBlock.varTable.add(tableItem);
                curBlock.saveBuf("%"+ counter+ " = alloca ["+len+ " x i32]", true);
                arr_address = "%"+counter;
                curBlock.saveBuf("%"+ ++counter +"= getelementptr ["+len+ " x i32], ["+ len +" x i32]* "+ arr_address +", i32 0, i32 0", true);
                curBlock.saveBuf("call void @memset(i32* %"+counter+", i32 0, i32 "+4*len+")", true);             
            //初始时就分配好0的初值;
            }
            else{
                globalvarTable.add(tableItem);
                arr_address = "@"+ConstName;
                System.out.print("@"+ConstName+" = dso_local constant ["+len+" x i32]");
            }

            arr_dim_data = arr.every_dim;
            visit_arr_index =new int[dim];
            InArray = true;
            visit(ctx.constInitVal()); 
            InArray = false;
            deep = 0;
            index = 0;
            return null;
        }

            visit(ctx.constInitVal());           
        if(!global){
            //检查同名变量
            varTableItem tmp =curBlock.checkTableByName(ConstName);
            if(tmp!=null){
                System.out.println("var defined repeatedly error");
                System.exit(1);
            }
            varTableItem tableItem;
            if(nodenumber.charAt(0)!='%')
                //@ 全局
                tableItem = new varTableItem(ConstName, nodenumber, "const", nodenumber);
            else
                tableItem = new varTableItem(ConstName, ""+calc_value, "const", ""+calc_value);
            curBlock.varTable.add(tableItem); 
        }
        else{
            //检查同名变量
            varTableItem tmp =checkGlobalByName(ConstName);
            if(tmp!=null){
                System.out.println("global defined repeatedly error");
                System.exit(1);
            }
            varTableItem tableItem;
                tableItem = new varTableItem(ConstName, ""+calc_value, "const", ""+calc_value);
                globalvarTable.add(tableItem);            
        }
        return null;
    }

    @Override
    public Void visitConstInitVal(lab1Parser.ConstInitValContext ctx) {
        if(ctx.constExp()!=null){
            if(!InArray)
                visit(ctx.constExp());
            else{
                visit(ctx.constExp());
                int offset = getOffset(arr_dim_data, visit_arr_index);
                int len = tmpArr.array.len;
                if(global){
                    if(index == 0){
                        System.out.print("i32 "+calc_value);
                        index++;
                    }
                    else
                        System.out.print(",i32 "+calc_value);
                }
                else{
                    curBlock.saveBuf("%"+ ++counter +"= getelementptr ["+len+ " x i32], ["+ len +" x i32]* "+ arr_address +", i32 0, i32 "+ offset, true);
                    curBlock.saveBuf("store i32 "+calc_value+", i32* %"+counter, true);
                }        
                tmpArr.array.data[offset] = ""+calc_value;
            }
        }       
        else{
            //'{' ( constInitVal (',' constInitVal)* )? '}'
            deep++;       
            varTableItem curVarTableItem = tmpArr;
            
            if(deep==1&&ctx.constInitVal().size()==0){
                // { } 初始化为0;
                if(global)
                    System.out.println(" zeroinitializer ");            
                return null;
            }           
            else if(ctx.constInitVal().size() > arr_dim_data[deep-1]){
                System.out.println("array constInit dim error");
                System.exit(1);
            }
            else{
                int initValue,i=0;
                if(global&&deep==1)System.out.print(" [");
                for(lab1Parser.ConstInitValContext tmpInit: ctx.constInitVal()){
                    visit_arr_index[deep-1]=i;
                    visit(tmpInit);
                    i++;
                }
                int zeronum = getZeroNum(i, arr_dim_data, deep-1);
                if(global){
                    for(i=0;i<zeronum;i++){
                        if(index!=0)
                            System.out.print(",i32 0");
                        else{
                            System.out.print("i32 0");
                            index++;
                        }
                    }
                }
                if(global&&deep==1){
                    System.out.println(" ]");

                } 
            }
            deep--;
        }
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
        if(ctx.children.size()>3){
            //数组def
            varTableItem tmp =curBlock.checkTableByName(varName);
            if(tmp!=null){
                System.out.println("var defined repeatedly error");
                System.exit(1);
            }

            varTableItem tableItem;
            
            arrayStruct arr =new arrayStruct();
            int i=0,len=1;
            int dim = ctx.constExp().size();
            arr.every_dim = new int[dim];

            for(lab1Parser.ConstExpContext dimDef:ctx.constExp()){
                visit(dimDef);
                arr.every_dim[i] = calc_value;
                len*=calc_value;
                i++;
            }

            if(!global)
                tableItem = new varTableItem(varName, "0", "array", "%"+ ++counter+"");
            else
                tableItem = new varTableItem(varName, "0", "array", "@"+ varName+"");

            arr.dim = dim;
            arr.len = len;
            arr.data = new String[len];
            for(int j=0;j<len;j++){
                arr.data[j]="0";
            }
            tableItem.array=arr;
            tmpArr = tableItem;

            if(!global){
                curBlock.varTable.add(tableItem);
                curBlock.saveBuf("%"+ counter+ " = alloca ["+len+ " x i32]", true);
                arr_address = "%"+counter;
                curBlock.saveBuf("%"+ ++counter +"= getelementptr ["+len+ " x i32], ["+ len +" x i32]* "+ arr_address +", i32 0, i32 0", true);
                curBlock.saveBuf("call void @memset(i32* %"+counter+", i32 0, i32 "+4*len+")", true);
            //初始时就分配好0的初值;
            }
            else{
                globalvarTable.add(tableItem);
                arr_address = "@"+varName;
                System.out.print("@"+varName+" = dso_local global ["+len+" x i32]");
            }

            arr_dim_data = arr.every_dim;
            visit_arr_index =new int[dim];

            InArray = true;
            if(ctx.initVal()==null){
                if(global)
                    System.out.println(" zeroinitializer");
                InArray = false;
                return null;
            }
            else{
                visit(ctx.initVal()); 
            }
            InArray = false;
            deep = 0;
            index = 0;
            return null;
        }

        //检查同名变量
        if(!global){
            varTableItem temp = curBlock.checkTableByName(varName);
            if(temp != null){
                System.out.println("var defined repeatedly error");
                System.exit(1);
            }

            curBlock.saveBuf("%"+ ++counter+"= alloca i32", true);
            varTableItem tableItem = new varTableItem(varName, null, "var", "%"+counter);
            curBlock.varTable.add(tableItem);

            if(ctx.initVal()!= null){
                visit(ctx.initVal());
                varTableItem tmp =curBlock.findTableByName(varName);
                tmp.value = nodenumber;
                curBlock.saveBuf("store i32 "+nodenumber+", i32* "+tmp.address, true);
            }
        }
        else{
            varTableItem temp = checkGlobalByName(varName);
            if(temp != null){
                System.out.println("gloabal defined repeatedly error");
                System.exit(1);
            }
            if(ctx.initVal()!= null){
                visit(ctx.initVal());
                    System.out.println("@"+varName+"= dso_local global i32 "+calc_value);
                    varTableItem tableItem = new varTableItem(varName, ""+calc_value, "var", "@"+varName);
                    globalvarTable.add(tableItem); 
            }
            else{
                System.out.println("@"+varName+"= dso_local global i32 "+"0");
                varTableItem tableItem = new varTableItem(varName, "0", "var", "@"+varName);
                globalvarTable.add(tableItem);  
            }
            
        }
        return null;
    }

    @Override
    public Void visitInitVal(lab1Parser.InitValContext ctx) {
        if(ctx.exp()!=null){
            if(!InArray){
               visit(ctx.exp());
               if(nodenumber.equals("%void")){
                    System.out.println("void assign error");
                    System.exit(1);
                } 
            }
            else{
                visit(ctx.exp());
                if(nodenumber.equals("%void")){
                    System.out.println("void assign error");
                    System.exit(1);
                }
                int offset = getOffset(arr_dim_data, visit_arr_index);
                int len = tmpArr.array.len;
                if(global){
                    if(index == 0){
                        System.out.print("i32 "+calc_value);
                        index++;
                    }
                    else{
                        System.out.print(",i32 "+calc_value);
                        index++;
                    }
                }
                else{
                    curBlock.saveBuf("%"+ ++counter +"= getelementptr ["+len+ " x i32], ["+ len +" x i32]* "+ arr_address +", i32 0, i32 "+ offset, true);
                    curBlock.saveBuf("store i32 "+nodenumber+", i32* %"+counter, true);
                }       
                tmpArr.array.data[offset] = nodenumber;
            }
        }
        else{
            //'{' ( InitVal (',' InitVal)* )? '}'
            deep++;       
            varTableItem curVarTableItem = tmpArr;
            if(deep==1&&ctx.initVal().size()==0){
                // { } 初始化为0;
                if(global)
                    System.out.println(" zeroinitializer ");
                return null;
            }           
            else if(ctx.initVal().size() > arr_dim_data[deep-1]){
                System.out.println("array varInit dim error");
                System.exit(1);
            }
            else{
                int initValue,i=0;
                if(global&&deep==1)System.out.print(" [");
                for(lab1Parser.InitValContext tmpInit: ctx.initVal()){
                    visit_arr_index[deep-1]=i;
                    visit(tmpInit);
                    i++;
                } 
                int zeronum = getZeroNum(i, arr_dim_data, deep-1);
                if(global){
                    for(i=0;i<zeronum;i++){
                        if(index!=0){
                            System.out.print(",i32 0");
                        }
                        else{
                            System.out.print("i32 0"); 
                        }
                        index++;
                    }
                }
                if(global&&deep==1){
                    System.out.println(" ]");
                } 
            }
            deep--;
        }
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
        varTableItem tmp;
        if(!global){
            tmp = curBlock.findTableByName(Lval);
            if(tmp==null){
                tmp = findGlobalByName(Lval);
            }
        }
        else{
            tmp = findGlobalByName(Lval);
        }
        
        if(tmp==null){
            System.out.println("lval not find error");
            System.exit(1);  
        }

        if(ctx.children.size()>1){
            //a[][][] array

            int dim = ctx.exp().size();
            int[]  arr_dim_data;
            Boolean flag=false;
            String[] visit_arr_index;
            
            if(dim!=tmp.array.dim){
                if(inparams&&dim==tmp.array.dim-1){
                    flag = true;
                }
                else{
                    System.out.println("array dim error");
                    System.exit(1); 
                }       
            }
            if(flag)
                visit_arr_index = new String[dim+1];
            else
                visit_arr_index = new String[dim];

            arr_dim_data = tmp.array.every_dim;
            int len = tmp.array.len;

            int i=0;
            for(lab1Parser.ExpContext tmpExp: ctx.exp()){
                visit(tmpExp);
                visit_arr_index[i] = nodenumber;//bug
                i++;
            }
            if(flag){
                visit_arr_index[i]="0";
                if(inparams){
                    arr_as_param = true;
                }
            }
            String offset = getVarOffset(arr_dim_data, visit_arr_index);
            
            if(!tmp.array.isParam)
                curBlock.saveBuf("%"+ ++counter +"= getelementptr ["+len+ " x i32], ["+ len +" x i32]* "+ tmp.address+", i32 0, i32 "+ offset, true);
            else
                curBlock.saveBuf("%"+ ++counter +"= getelementptr i32, i32* "+ tmp.address+", i32 "+ offset, true);
            nodenumber = "%"+counter;
            node_type = 1;

            if(!tmp.array.isParam && offset.charAt(0)!='%'){
                int off = Integer.parseInt(offset);
                if(tmp.array.data[off].charAt(0)!='%')
                    calc_value =Integer.parseInt(tmp.array.data[off]);
            }
 
        }
        else{    
            if(global&&!tmp.type.equals("const")){
                System.out.println("global should be const error");
                System.exit(1);
            }
            nodenumber = tmp.address;
            node_type = 1;
            if(tmp.type.equals("array")){
                // arr
                if(tmp.array.dim!=tmpFunc.paramType[paramdim]){
                    System.out.println("array dim error");
                    System.exit(1); 
                }
                if(!tmp.array.isParam){
                    if(nodenumber.charAt(0)=='%'){
                        nodenumber=nodenumber.substring(1);
                        nodenumber="%"+(Integer.parseInt(nodenumber)+1);
                    }
                    else if(nodenumber.charAt(0)=='@'){
                        int len = tmp.array.len;
                        curBlock.saveBuf("%"+ ++counter +"= getelementptr ["+len+ " x i32], ["+ len +" x i32]* "+ nodenumber+", i32 0, i32 0", true);
                        nodenumber ="%"+counter;
                    }
                    
                    node_type = 1;
                }
                if(inparams){
                    arr_as_param = true;
                }

            }
            if(tmp.value!=null&&tmp.value.charAt(0)!='%'){
                calc_value =Integer.parseInt(tmp.value);           
            }
            
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

                if(global&&((tmp1.charAt(0)=='%'||tmp2.charAt(0)=='%')&&tmp1.charAt(1)!='0'&&tmp2.charAt(1)!='0')){
                    System.out.println("global should be const error");
                    System.exit(1);
                }
                if(ctx.Plus()!=null){
                    if(!global)
                        curBlock.saveBuf("%" + (++counter) + " = add i32 " + tmp1 +", "+ tmp2, true);              
                    nodenumber ="%"+counter;
                    node_type = 0;
                    calc_value = v1+v2;
                }
                else if(ctx.Sub()!=null){
                    if(!global)
                        curBlock.saveBuf("%" + (++counter) + " = sub i32 " + tmp1 +", "+ tmp2, true);
                    nodenumber ="%"+counter;
                    node_type = 0;
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

                if(global&&(tmp1.charAt(0)=='%'||tmp2.charAt(0)=='%')){
                    System.out.println("global should be const error");
                    System.exit(1);
                }

                if(ctx.Mul()!=null){
                    if(!global)
                        curBlock.saveBuf("%" + (++counter) + " = mul i32 " + tmp1 +", "+ tmp2, true);
                    nodenumber ="%"+counter;
                    calc_value = v1*v2;
                    node_type = 0;
                }
                else if(ctx.Div()!=null){
                    if(!global)
                        curBlock.saveBuf("%" + (++counter) + " = sdiv i32 " + tmp1 +", "+ tmp2, true);
                    nodenumber ="%"+counter;
                    if(v2!=0)
                        calc_value = v1/v2;
                    node_type = 0;
                }
                else if(ctx.Mod()!=null){
                    if(!global)
                        curBlock.saveBuf("%" + (++counter) + " = srem i32 " + tmp1 +", "+ tmp2, true);
                    nodenumber ="%"+counter;
                    if(v2!=0)
                        calc_value =v1%v2;
                    node_type = 0;
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
                //unaryOp unaryExp              
                visit(ctx.unaryExp());
                if(ctx.unaryOp().Sub()!=null){
                    int v =calc_value;
                    if(!global)
                        curBlock.saveBuf("%"+(++counter)+" = sub i32 0, "+nodenumber, true);
                    nodenumber = "%"+counter;
                    calc_value = 0-v;
                    node_type = 0;
                }
                else if(ctx.unaryOp().Not()!=null){
                    curBlock.saveBuf("%"+(++counter)+" = icmp eq i32 "+nodenumber +", 0", true);
                    nodenumber = "%"+counter;
                    curBlock.saveBuf("%"+(++counter)+" = zext i1 "+nodenumber +" to i32", true);
                    nodenumber = "%"+counter;
                    node_type = 0;
                    zext_i1 =true;
                }

                visit(ctx.unaryOp());             
                break;
            }
            default:{   //Ident '(' funcRParams? ')'
                String funcName = ctx.Ident().getText();
                function func = findLibFuc(funcName);
                String[] save_params = params;
                int[] save_everyParamsType = everyParamsType;
                function save_tmpFunc = tmpFunc;
                //保存防止嵌套调用      
                if(func!=null){              
                    tmpFunc = func;
                    if(ctx.funcRParams()!=null){
                        visit(ctx.funcRParams());
                    }
                    else{
                        if(func.hasParam){
                            System.out.println("func no param");
                            System.exit(1);
                        }
                        params = null;
                    }
                    exeFunc(funcName, params);                    
                }
                else{
                    System.out.println("func not found error");
                    System.exit(1);
                }
                tmpFunc = save_tmpFunc;
                params = save_params;
                everyParamsType = save_everyParamsType;
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
    public Void visitPrimaryExp(lab1Parser.PrimaryExpContext ctx) {
        switch(ctx.children.size()){
            case 1:{
                if(ctx.lVal()!=null){
                    visit(ctx.lVal());
                    if(!global&&(!(inparams&&arr_as_param))&&(nodenumber.charAt(0)=='%'||nodenumber.charAt(0)=='@')){//是变量而非常量
                        curBlock.saveBuf("%"+ ++counter+"= load i32, i32* "+nodenumber, true);
                        nodenumber = "%"+counter;
                        node_type = 0;
                    }//根据paramtype!!!
                }
                else if(ctx.number()!=null)
                    visit(ctx.number());
                break;
            }
            case 3:{
                visit(ctx.exp());
                break;
            }
        }
        arr_as_param = false;
        return null;
    }

    @Override
    public Void visitFuncRParams(lab1Parser.FuncRParamsContext ctx) {  
        int param_cnt = ctx.exp().size(),i=0;
        String[] lparams= new String[param_cnt];
        int[] leveryParamsType = new int[param_cnt];

        if(param_cnt!=tmpFunc.paramCnt){
            System.out.println("param num error");
            System.exit(1);
        }
        inparams = true;
        for(lab1Parser.ExpContext tmpExp: ctx.exp()){
            paramdim = i;
            visit(tmpExp);
            lparams[i]= nodenumber;
            leveryParamsType[i]= node_type;
            i++;
        }
        inparams = false;
        params = lparams;
        everyParamsType = leveryParamsType;
        
        return null;
    }

    @Override
    public Void visitNumber(lab1Parser.NumberContext ctx) {  
        // System.out.print("i32 "+num);
        nodenumber = getNumber(ctx);
        calc_value = Integer.parseInt(getNumber(ctx));
        node_type = 0;
        return null;
    }

    @Override
    public Void visitCond(lab1Parser.CondContext ctx) {
        visit(ctx.lOrExp());
        return null;
    }

    @Override
    public Void visitLOrExp(lab1Parser.LOrExpContext ctx) {
        switch(ctx.children.size()){
            case 1:{
                //lAndExp
                visit(ctx.lAndExp());
                break;
            }
            case 3:{
                // lOrExp '||' lAndExp
                visit(ctx.lOrExp());
                String tmp1 = nodenumber;

                visit(ctx.lAndExp());
                String tmp2 = nodenumber;

                curBlock.saveBuf("%"+ ++counter+" = or i1 " +tmp1+", "+tmp2, true);
                nodenumber ="%"+counter;
                node_type = -1;
                break;
            }
        }
        return null;
    }

    @Override
    public Void visitLAndExp(lab1Parser.LAndExpContext ctx) {
        switch(ctx.children.size()){
            case 1:{
                // eqExp
                visit(ctx.eqExp());
                break;
            }
            case 4:{
                // lAndExp & & eqExp
                visit(ctx.lAndExp());
                String tmp1 = nodenumber;

                visit(ctx.eqExp());
                String tmp2 = nodenumber;
                curBlock.saveBuf("%"+ ++counter+" = and i1 " +tmp1+", "+tmp2, true);
                nodenumber ="%"+counter;
                node_type = -1;
                break;
            }
        }
        return null;
    }

    @Override
    public Void visitEqExp(lab1Parser.EqExpContext ctx) {
        switch(ctx.children.size()){
            case 1:{
                // relExp 
                visit(ctx.relExp());
                break;
            }
            case 3:{
                // eqExp ('==' | '!=') relExp;
                visit(ctx.eqExp());
                String tmp1 = nodenumber;

                visit(ctx.relExp());
                String tmp2 = nodenumber;

                if(ctx.NEqual() != null){
                    curBlock.saveBuf("%"+ ++counter +" = icmp ne i32 "+tmp1+", "+tmp2, true);
                }
                else if(ctx.Equal() != null){
                    curBlock.saveBuf("%"+ ++counter +" = icmp eq i32 "+tmp1+", "+tmp2, true);            
                }
                nodenumber ="%"+counter;
                node_type = -1;
                break;
            }
        }
        return null;
    }

    @Override
    public Void visitRelExp(lab1Parser.RelExpContext ctx) {
        switch(ctx.children.size()){
            case 1:{
                //  addExp
                visit(ctx.addExp());
                if(zext_i1){
                    curBlock.saveBuf("%"+ ++counter +" = icmp ne i32 "+nodenumber+", 0", true);
                    nodenumber ="%"+counter;
                    node_type = -1;
                    zext_i1 = false;
                }
                else{
                    if(ctx.getParent().children.size()== 1&&(ctx.getParent().getParent().children.size()== 1||ctx.getParent().getParent().children.size()==4)){
                        curBlock.saveBuf("%"+ ++counter +" = icmp ne i32 "+nodenumber+", 0", true);
                        nodenumber ="%"+counter;
                        node_type = -1;
                    }
                }

                break;
            }
            case 3:{
                // relExp ('<' | '>' | '<=' | '>=') addExp
                visit(ctx.relExp());
                String tmp1 = nodenumber;

                visit(ctx.addExp());
                String tmp2 = nodenumber;

                if(ctx.Lt()!=null){
                    curBlock.saveBuf("%"+ ++counter +" = icmp slt i32 "+tmp1+", "+tmp2, true);
                }
                else if(ctx.Le()!=null){
                    curBlock.saveBuf("%"+ ++counter +" = icmp sle i32 "+tmp1+", "+tmp2, true);
                }
                else if(ctx.Gt()!=null){
                    curBlock.saveBuf("%"+ ++counter +" = icmp sgt i32 "+tmp1+", "+tmp2, true);
                }
                else if(ctx.Ge()!=null){
                    curBlock.saveBuf("%"+ ++counter +" = icmp sge i32 "+tmp1+", "+tmp2 , true);
                }
                nodenumber ="%"+counter;
                node_type = -1;
                break;
            }
        }
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

    // public static varTableItem findTableByName(String varName){
    //     for(varTableItem tmp :varTable){
    //         if(tmp.varName.equals(varName))
    //             return tmp;
    //     }
    //     return null;
    // }

    // public static varTableItem findTableByAddr(String addr){
    //     for(varTableItem tmp :varTable){
    //         if(tmp.address.equals(addr))
    //             return tmp;
    //     }
    //     return null;
    // }

    public static function findLibFuc(String funcName){
        for(function tmp: funcTable){
            if(tmp.funcName.equals(funcName))
                return tmp;
        }
        return null;
    }

    public static void exeFunc(String funcName, String[] params){
        function func = tmpFunc; 
        int param_cnt = tmpFunc.paramCnt;
        boolean isInt = func.retType.equals("int");
        if(param_cnt == 0){
            if(!isInt){
                curBlock.saveBuf("call void @"+func.funcName+"()", true);
            }
    
            else{
                curBlock.saveBuf("%"+ ++counter+" = call i32 @"+func.funcName+"()", true);   
                nodenumber = "%"+counter;
                node_type = 0;
            }
        }
        else{
            if(!isInt){
                //void
                curBlock.saveBuf("call void @"+func.funcName+"(", false);
                for(int i=0;i<param_cnt;i++){
                    curBlock.saveBuf(" i32", false);
                    if(everyParamsType[i]!=0)
                        curBlock.saveBuf("*", false);
                    curBlock.saveBuf(" "+params[i], false);
                    if(i!=param_cnt-1)
                        curBlock.saveBuf(", ", false);
                }
                curBlock.saveBuf(")", true);
                nodenumber = "%void";
            }

            else{
                curBlock.saveBuf("%"+ ++counter+" = call i32 @"+func.funcName+"(", false);
                for(int i=0;i<param_cnt;i++){
                    curBlock.saveBuf(" i32", false);
                    if(everyParamsType[i]!=0)
                        curBlock.saveBuf("*", false);
                    curBlock.saveBuf(" "+params[i], false);
                    if(i!=param_cnt-1)
                        curBlock.saveBuf(", ", false);
                }
                curBlock.saveBuf(")", true);
                nodenumber = "%"+counter;
                node_type = 0;
            }
        }
        
    }

    public static void init(){
        function getint = new function("getint", "int", false);
        int int_param[] = {0};
        function putint = new function("putint", "void", true, 1, int_param);
        function getch = new function("getch", "int", false);
        function putch = new function("putch", "void", true, 1, int_param);
        int getarray_param[] = {1};
        function getarray = new function("getarray", "int", true, 1, getarray_param);
        int putarray_param[] = {0,1};
        function putarray = new function("putarray", "void", true, 2, putarray_param);

        funcTable.add(getint);
        funcTable.add(putint);
        funcTable.add(getch);
        funcTable.add(putch);
        funcTable.add(getarray);
        funcTable.add(putarray);

        blockTree.add(root);
    }
    
    public void printBuff(){
        for(blockTreeNode tmp:blockTree){
            System.out.print(tmp.buf);
        }
    }

    public static boolean need_br(blockTreeNode b){
        if(breaknodeStack.contains(b))
            return false;
        String buf = b.buf;
        int len = buf.length();
        if(len == 0){
            return true;
        }
        else if(buf.contains("br")||buf.contains("ret")){
            return false;
        }
        else{
            return true;
        }
    }

    public static boolean need_ret(blockTreeNode b){
        String buf = b.buf;
        int len = buf.length();
        if(len == 0){
            return true;
        }
        else if(buf.contains("ret")){
            return false;
        }
        else{
            return true;
        }
    }

    public varTableItem checkGlobalByName(String varName){
        for(varTableItem tmp :globalvarTable){
            if(tmp.varName.equals(varName))
                return tmp;      
        }
        return null;
    }

    public varTableItem findGlobalByName(String varName){
        for(varTableItem tmp :globalvarTable){
            if(tmp.varName.equals(varName))
                return tmp;      
        }
        return null;
    }

    public static int getOffset(int[] arr_dim_data, int[] visit_arr_index){
        int len = arr_dim_data.length;
        int offset = 0,tmp=1;
        for(int i =0; i<len; i++){
            for(int j=i+1;j<len;j++){
                tmp*=arr_dim_data[j];
            }
            offset+=visit_arr_index[i]*tmp;
            tmp=1;
        }
        return offset;
    }

    public static String getVarOffset(int[] arr_dim_data, String[] visit_arr_index){
        boolean constflag = true;
        int len = arr_dim_data.length,k=0;
        int[] visit_arr_int = new int[len];
        for(String tmp: visit_arr_index){
            if(tmp.charAt(0)=='%'){
                constflag = false;
                break;
            }
            visit_arr_int[k] =Integer.parseInt(tmp);
            k++;
        }
        if(constflag) {
            int ret = getOffset(arr_dim_data,visit_arr_int);
            return ret+"";
        }
        else {
            curBlock.saveBuf("%"+ ++counter+"= add i32 1 , 0",true);
            String temp ="%"+counter;
            String tmp0 ="%"+counter;
            curBlock.saveBuf("%"+ ++counter+"= add i32 0 , 0",true);
            String ofset ="%"+counter;
            for(int i =0; i<len; i++){
                for(int j=i+1;j<len;j++){
                    curBlock.saveBuf("%"+ ++counter+"= mul i32 "+temp+", "+arr_dim_data[j],true);
                    temp ="%"+counter;
                }
                curBlock.saveBuf("%"+ ++counter+"= mul i32 "+temp+", "+visit_arr_index[i],true);
                curBlock.saveBuf("%"+ ++counter+"= add i32 %"+(counter-1)+", "+ofset,true);
                ofset ="%"+counter;
                temp = tmp0;
            }
            return "%"+counter;
        }
    }

    public static int getZeroNum(int i, int[] arr_dim_data, int dim){
        if(dim>arr_dim_data.length || i>= arr_dim_data[dim])
            return 0;
        else{
            int j = arr_dim_data[dim] - i;
            ++dim;
            for(;dim<arr_dim_data.length;dim++){
                j*=arr_dim_data[dim];
            }
            return j;
        }
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
    arrayStruct array;
    public varTableItem(String varName, String value, String type, String address) {
        this.varName = varName;
        this.value = value;
        this.type = type;
        this.address = address;
    }
    @Override
    public String toString() {
        return "varTableItem [address=" + address + ", type=" + type + ", value=" + value + ", varName=" + varName
                + "]";
    }
}

class arrayStruct{
    int[] every_dim;
    String[] data;
    int dim;
    int len;
    boolean isParam = false;
}

class function{
    String funcName;
    String retType;
    boolean hasParam;
    int paramCnt;
    int [] paramType;
    public function(String funcName, String retType, boolean hasParam) {
        this.funcName = funcName;
        this.retType = retType;
        this.hasParam = hasParam;
    }
    public function(String funcName, String retType, boolean hasParam, int paramcnt, int[] paramtype) {
        this.funcName = funcName;
        this.retType = retType;
        this.hasParam = hasParam;
        this.paramCnt = paramcnt;
        this.paramType = paramtype;
    }
}

class blockTreeNode{
    int counter_start;
    int counter_end;
    blockTreeNode jmpTrueBlock;
    blockTreeNode jumFalseBlock;
    blockTreeNode loopBlock;
    blockTreeNode destBlock;
    blockTreeNode parBlock;
    List<blockTreeNode> otherBlocks = new ArrayList<>();
    List <varTableItem> varTable = new ArrayList<>();
    String buf="";
    static List <varTableItem> globalvarTable= new ArrayList<>();

    blockTreeNode(int counter_start, blockTreeNode parBlock){
        this.counter_start = counter_start;
        this.parBlock = parBlock;
    }
    public void saveBuf(String str, boolean n){
        if(n)
            this.buf+=str+'\n';
            //System.out.println(str);
        else
            this.buf+=str;
            //System.out.print(str);
    }
    public void printBuf(String str, boolean n){
        if(n)
            System.out.println(str);
        else
            System.out.print(str);
    }
    public varTableItem findTableByName(String varName){
        blockTreeNode block =this;
        while(block!=null){
            for(varTableItem tmp : block.varTable){
                if(tmp.varName.equals(varName))
                    return tmp;
            }
            block = block.parBlock;
        }

        for(varTableItem tmp :globalvarTable){
            if(tmp.varName.equals(varName))
                return tmp;      
        }

        return null;
    }

    public varTableItem findTableByAddr(String addr){
        blockTreeNode block =this;
        while(block!=null){
            for(varTableItem tmp :block.varTable){
                if(tmp.address.equals(addr))
                    return tmp;
            }
            block = block.parBlock;

            for(varTableItem tmp :globalvarTable){
                if(tmp.address.equals(addr))
                    return tmp;      
            }
        }

        return null;
    }

    public varTableItem checkTableByName(String varName){
        for(varTableItem tmp :varTable){
            if(tmp.varName.equals(varName))
                return tmp;      
        }
        return null;
    }
}
