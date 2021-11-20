// Main.java
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;



public class Main {
    public static void main(String[] args) {
        Prepare p =new Prepare();
        String input = "" ;
        File infile = new File(args[0]);
        try{
            Scanner sc = new Scanner(infile);
            while(sc.hasNextLine()){
                input +=p.clearnote(sc.nextLine());
            }
            sc.close();
        }catch(FileNotFoundException e){
            e.printStackTrace();
        }

            CharStream inputStream = CharStreams.fromString(input); // 获取输入流
            lab1Lexer lexer = new lab1Lexer(inputStream);
            //lexer.removeErrorListeners();
            //lexer.addErrorListener(MyErrorListener.INSTANCE);
            
            CommonTokenStream tokenStream = new CommonTokenStream(lexer); // 词法分析获取 token 流
            lab1Parser parser = new lab1Parser(tokenStream);
            //parser.removeErrorListeners();
            //parser.addErrorListener(MyErrorListener.INSTANCE);                  
            ParseTree tree = parser.compUnit(); // 获取语法树的根节点
            System.out.println(tree.toStringTree(parser));
            
            Visitor visitor = new Visitor();
            visitor.visit(tree);
            
        // File outfile = new File("out.txt");
        // try {
        //     PrintWriter output = new PrintWriter(outfile);
        //     output.print(tree.toStringTree(parser));
        //     output.close();
        // } catch(Exception e){
        //     e.printStackTrace();
        // }
        
    }

}
class Prepare {
    private static boolean flag = false;
    public static String clearnote(String inputline){
        int index=0,len = inputline.length();
        char tmp;
        String returnline="";
            for(;index< len;index++){
                tmp = inputline.charAt(index);
                if(!flag){
                    if(tmp =='/' && index+1<len && inputline.charAt(index+1)=='/'){ ;
                        break;
                    }
                    if(tmp =='/' && index+1<len && inputline.charAt(index+1)=='*'){
                        flag = true;
                        index++;
                        continue;
                    }
                    else returnline+=tmp;
                }
                else if(flag){
                    if(tmp =='*' && index+1<len && inputline.charAt(index+1)=='/'){
                        flag =false;
                        index++;
                        continue;
                    }
                }
            }
        return returnline;
    }
}
