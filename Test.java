import java.util.Scanner;

public class Test {
    char char_now;
    char char_last;
    String token_now;
    String line_now;
    int len;
    int i = 0;

    static String   output[] = {"Ident","Number","If","Else","While","Break","Continue","Return","Assign","Semicolon",
            "LPar","RPar","LBrace","RBrace","Plus","Mult","Div","Lt","Gt","Eq"};

    void clearToken(){
        this.token_now = "";
    }
    void catToken(){
        this.token_now+=this.char_now;
    }
    void retract(){
        if(!this.token_now.isEmpty()) {
            this.char_now = this.char_last;
            this.i--;
        }
        return;
    }

    boolean isSpace_tab_newline(char c){
        if(c==' ' || c=='\n' || c=='\t')
            return true;
        return false;
    }
    boolean isletter(char c){
        if(Character.isLetter(c))
            return true;
        return false;
    }
    boolean isdigit(char c){
        if(Character.isDigit(c))
            return true;
        return false;
    }
    boolean is_(char c){
        if(c=='_')
            return true;
        return false;
    }
    boolean isSemi(char c){
        if(c==';')
            return true;
        return false;
    }
    boolean isEqual(char c){
        if(c=='=')
            return true;
        return false;
    }
    boolean isPlus(char c){
        if(c=='+')
            return true;
        return false;
    }
    boolean isMinus(char c){
        if(c=='-')
            return true;
        return false;
    }
    boolean isStar(char c){
        if(c=='*')
            return true;
        return false;
    }
    boolean isDivi(char c){
        if(c=='/')
            return true;
        return false;
    }
    boolean isLpar(char c){
        if(c=='(')
            return true;
        return false;
    }
    boolean isRpar(char c){
        if(c==')')
            return true;
        return false;
    }
    boolean isLBrace(char c){
        if(c=='{')
            return true;
        return false;
    }
    boolean isRBrace(char c){
        if(c=='}')
            return true;
        return false;
    }
    boolean isLt(char c){
        if(c=='<')
            return true;
        return false;
    }
    boolean isRt(char c){
        if(c=='>')
            return true;
        return false;
    }
    boolean getchar(){
        if(this.i >= this.len)
            return false;
        else {
            this.char_last = this.char_now;
            this.char_now = this.line_now.charAt(i);
        }
        this.i++;
        return true;
    }

    void getToken(){
        this.i = 0;
        this.len = this.line_now.length();

        for(i=0;i<this.len;){
            this.clearToken();

            if(!this.getchar())
                return;
            this.catToken();

            if(this.isSpace_tab_newline(this.char_now)){
                while(this.isSpace_tab_newline(this.char_now)){                                     //空字符,制表,换行
                    if(!this.getchar())
                        return;
                }
                this.retract();
                continue;
            }


            if(this.isletter(this.char_now)||this.is_(this.char_now)){                                                   //字母
                if(!this.getchar()){
                    this.printToken(0);
                    return;
                }

                while(this.isletter(this.char_now)||this.is_(this.char_now)||this.isdigit(this.char_now)){
                    this.catToken();
                    if(!this.getchar()){
                        this.printToken(0);
                        return;
                    }
                }
                this.retract();
                this.printToken(0);
            }

            else if(this.isdigit(this.char_now)){                                               //数字
                if(!this.getchar()){
                    this.printToken(1);
                    return;
                }
                while(this.isdigit(this.char_now)){
                    this.catToken();
                    if(!this.getchar()){
                        this.printToken(1);
                        return;
                    }
                }
                this.retract();
                this.printToken(1);
            }

            else if(this.isEqual(this.char_now)){                                               //等号
                if(!this.getchar()){
                    this.printToken(8);
                    return;
                }
                if(this.isEqual(this.char_now)){
                    this.catToken();
                    this.printToken(19);
                }

                else{
                    this.retract();
                    this.printToken(8);
                }
            }
            else switch (this.char_now){
                    case ';':
                        this.printToken(9);
                        break;
                    case '(':
                        this.printToken(10);
                        break;
                    case ')':
                        this.printToken(11);
                        break;
                    case '{':
                        this.printToken(12);
                        break;
                    case '}':
                        this.printToken(13);
                        break;
                    case '+':
                        this.printToken(14);
                        break;
                    case '*':
                        this.printToken(15);
                        break;
                    case '/':
                        this.printToken(16);
                        break;
                    case '<':
                        this.printToken(17);
                        break;
                    case '>':
                        this.printToken(18);
                        break;
                    default:
                        System.out.println("Err");
                        System.exit(0);
            }
        }
    }

    void printToken(int type){
        if(type == 0){
            if(this.token_now.equals("if"))type = 2;
            if(this.token_now.equals("else"))type = 3;
            if(this.token_now.equals("while"))type = 4;
            if(this.token_now.equals("break"))type = 5;
            if(this.token_now.equals("continue"))type = 6;
            if(this.token_now.equals("return"))type = 7;
        }
        if(type == 0 || type ==1)
            System.out.println(output[type]+"("+this.token_now+")");
        else
            System.out.println(output[type]);
    }

    public static void main(String[] args) {
	// write your code here
        Scanner sc = new Scanner(System.in);
        Test test = new Test();
        while(sc.hasNext()){
            test.line_now = sc.nextLine();
            test.getToken();
        }
        sc.close();
    }
}
