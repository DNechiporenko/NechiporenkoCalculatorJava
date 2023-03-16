import java.util.Scanner;

public class Fraction {
    public static int gcd(int a, int b) { // НОД
        if (b == 0) {
            return a;
        }
        return gcd(b, a % b);
    }
    public static int lcm(int a, int b) { // НОК
        return a / gcd(a, b) * b;
    }
    private int numerator;
    private int denominator;

    public Fraction (int numerator,int denominator){
        if (denominator == 0){
            System.out.println("На ноль делить нельзя!");
            this.numerator = 1;
            this.denominator = 1;
            return;
        }
        if (gcd(numerator,denominator) != 1){
            System.out.println("Дробь не простая!");
            this.numerator = 1;
            this.denominator = 1;
            return;
        }
        this.numerator = numerator;
        this.denominator = denominator;
    }
    public Fraction() {
        this.numerator = 1;
        this.denominator = 1;
    }

    public static String summ (Fraction f1, Fraction f2){
        Fraction result = new Fraction();
        if (f1.denominator == 0 || f2.denominator == 0){
            System.out.println("Ошибка. Деление на 0.");
            System.exit(0);
        }
        result.denominator = lcm (f1.denominator, f2.denominator);
        result.numerator = f1.numerator*(result.denominator/ f1.denominator) + f2.numerator*(result.denominator/ f2.denominator);
        int nod = gcd(result.denominator, result.numerator);
        result.denominator = result.denominator/nod;
        result.numerator = result.numerator/nod;
        String s = "";
        s+=result.numerator;
        s+= '/';
        s+=result.denominator;
        return s;
    }
    public static String sub (Fraction f1, Fraction f2){
        if (f1.denominator == 0 || f2.denominator == 0){
            System.out.println("Ошибка. Деление на 0.");
            System.exit(0);
        }
        Fraction result = new Fraction();
        result.denominator = lcm (f1.denominator, f2.denominator);
        result.numerator = f1.numerator*(result.denominator/ f1.denominator) - f2.numerator*(result.denominator/ f2.denominator);
        int nod = gcd(result.denominator, result.numerator);
        result.denominator = result.denominator/nod;
        result.numerator = result.numerator/nod;
        String s = "";
        s+=result.numerator;
        s+= '/';
        s+=result.denominator;
        return s;
    }
    public static String mult (Fraction f1, Fraction f2){
        if (f1.denominator == 0 || f2.denominator == 0){
            System.out.println("Ошибка. Деление на 0.");
            System.exit(0);
        }
        Fraction result = new Fraction();
        result.denominator = f1.denominator*f2.denominator;
        result.numerator = f1.numerator*f2.numerator;
        int nod = gcd(result.denominator, result.numerator);
        result.denominator = result.denominator/nod;
        result.numerator = result.numerator/nod;
        String s = "";
        s+=result.numerator;
        s+= '/';
        s+=result.denominator;
        return s;
    }
    public static String div (Fraction f1, Fraction f2){
        if (f1.denominator == 0 || f2.denominator == 0){
            System.out.println("Ошибка. Деление на 0.");
            System.exit(0);
        }
        Fraction result = new Fraction();
        result.denominator = f1.denominator*f2.numerator;
        result.numerator = f1.numerator*f2.denominator;
        int nod = gcd(result.denominator, result.numerator);
        result.denominator = result.denominator/nod;
        result.numerator = result.numerator/nod;
        String s = "";
        s+=result.numerator;
        s+= '/';
        s+=result.denominator;
        return s;
    }

    /*public static String FindBracket(){
        for (int i = 0; i <= s.length();i++){
            (1/3 + 2 +(3 +1*(7-1)))+(1-4-3-2)*6
        }
    }*/
    public static String ToDoSums (String s){
        Fraction f1 = new Fraction();
        Fraction f2 = new Fraction();
        f1.denominator = 0;
        f1.numerator = 0;
        f2.denominator = 0;
        f2.numerator = 0;
        int endS = -1;
        boolean flag = false;
        for (int i = 0; i < s.length();i++){
            if (s.charAt(i) == '/'){
                flag = true;
            }
            if ('0'<=s.charAt(i) &&  s.charAt(i)<='9'){
                if (endS == -1){
                    endS = i;
                }
                if (flag){
                    f1.denominator = f1.denominator*10 + (s.charAt(i)-'0');
                }
                else {
                    f1.numerator = f1.numerator*10 + (s.charAt(i)-'0');
                }
                if (i > 1){
                    if (s.charAt(i-1) == '-' && (s.charAt(i-2) == '+' || s.charAt(i-2) == '-' || s.charAt(i-2) == '*' || s.charAt(i-2) == ':')){
                        f1.numerator *= -1;
                        endS-=1;
                    }
                }else {
                    if (i == 1) {
                        if (s.charAt(i - 1) == '-') {
                            f1.numerator *= -1;
                            endS-=1;
                        }
                    }
                }
                if (i>1 && s.charAt(i-1) == '-' && s.charAt(i-2) == '/'){
                    f1.denominator *= -1;
                }
            }
            if (s.charAt(i) == '+' || (s.charAt(i) == '-' && i > 0 && (s.charAt(i-1) >= '0' && s.charAt(i-1) <= '9'))){
                f1.denominator = 0;
                f1.numerator = 0;
                flag = false;
                endS = -1;
            }
            if (s.charAt(i) == '*'){
                String result = "";
                int minus = 1;
                int j = i+1;
                if (s.charAt(j) == '-'){
                    j += 1;
                    minus = -1;
                }
                while ('0'<=s.charAt(j) && s.charAt(j)<='9'){
                    f2.numerator = f2.numerator*10 + (s.charAt(j)-'0');
                    j+=1;
                }
                j += 1;
                if (s.charAt(j) == '-'){
                    j += 1;
                    minus *= -1;
                }
                while ('0'<=s.charAt(j) && s.charAt(j)<='9'){
                    f2.denominator = f2.denominator*10 + (s.charAt(j)-'0');
                    j+=1;
                    if (j == s.length()){
                        break;
                    }
                }
                f2.numerator *= minus;
                /*System.out.println(f1.numerator);
                System.out.println(f1.denominator);
                System.out.println(f2.numerator);
                System.out.println(f2.denominator);*/
                result = mult(f1, f2);
                //System.out.println(result);
                String newS = s.substring(0, endS);
                newS += result;
                if (j < s.length()){
                    newS += s.substring(j);
                }
                //System.out.println(newS);
                s = newS;
                return ToDoSums(newS);
            }
            if (s.charAt(i) == ':'){
                String result = "";
                int minus = 1;
                int j = i+1;
                if (s.charAt(j) == '-'){
                    j += 1;
                    minus = -1;
                }
                while ('0'<=s.charAt(j) && s.charAt(j)<='9'){
                    f2.numerator = f2.numerator*10 + (s.charAt(j)-'0');
                    j+=1;
                }
                j+=1;
                if (s.charAt(j) == '-'){
                    j += 1;
                    minus *= -1;
                }
                while ('0'<=s.charAt(j) && s.charAt(j)<='9'){
                    f2.denominator = f2.denominator*10 + (s.charAt(j)-'0');
                    j+=1;
                    if (j == s.length()){
                        break;
                    }
                }
                f2.numerator *= minus;
                result = div(f1, f2);
                String newS = s.substring(0, endS);
                newS += result;
                if (j < s.length()){
                    newS += s.substring(j);
                }
                //System.out.println(newS);
                s = newS;
                return ToDoSums(newS);
            }
        }
        f1.denominator = 0;
        f1.numerator = 0;
        flag = false;
        for (int i = 0; i < s.length();i++){
            if (s.charAt(i) == '/'){
                flag = true;
            }
            if ('0'<=s.charAt(i) &&  s.charAt(i)<='9'){
                if (flag){
                    f1.denominator = f1.denominator*10 + (s.charAt(i)-'0');
                }
                else {
                    f1.numerator = f1.numerator*10 + (s.charAt(i)-'0');
                }
            }
            if (i > 1){
                if (s.charAt(i-1) == '-' && (s.charAt(i-2) == '+' || s.charAt(i-2) == '-' || s.charAt(i-2) == '*' || s.charAt(i-2) == ':')){
                    f1.numerator *= -1;
                }
            }else {
                if (i == 1) {
                    if (s.charAt(i - 1) == '-') {
                        f1.numerator *= -1;
                    }
                }
            }
            if (i>1 && s.charAt(i-1) == '-' && s.charAt(i-2) == '/'){
                f1.denominator *= -1;
            }
            if (s.charAt(i) == '+'){
                String result = "";
                int minus = 1;
                int j = i+1;
                if (s.charAt(j) == '-'){
                    j += 1;
                    minus = -1;
                }
                while ('0'<=s.charAt(j) && s.charAt(j)<='9'){
                    f2.numerator = f2.numerator*10 + (s.charAt(j)-'0');
                    j+=1;
                }
                j+=1;
                if (s.charAt(j) == '-'){
                    j += 1;
                    minus *= -1;
                }
                while ('0'<=s.charAt(j) && s.charAt(j)<='9'){
                    f2.denominator = f2.denominator*10 + (s.charAt(j)-'0');
                    j+=1;
                    if (j == s.length()){
                        break;
                    }
                }
                f2.numerator *= minus;
                result = summ(f1, f2);
                String newS = result;
                if (j < s.length()){
                    newS += s.substring(j);
                }
                //System.out.println(newS);
                return ToDoSums(newS);
            }
            if (s.charAt(i) == '-' && i > 0 && (s.charAt(i-1) >= '0' && s.charAt(i-1) <= '9')){
                int minus = 1;
                String result = "";
                int j = i+1;
                if (s.charAt(j) == '-'){
                    j += 1;
                    minus = -1;
                }
                while ('0'<=s.charAt(j) && s.charAt(j)<='9'){
                    f2.numerator = f2.numerator*10 + (s.charAt(j)-'0');
                    j+=1;
                }
                j+=1;
                if (s.charAt(j) == '-'){
                    j += 1;
                    minus *= -1;
                }
                while ('0'<=s.charAt(j) && s.charAt(j)<='9'){
                    f2.denominator = f2.denominator*10 + (s.charAt(j)-'0');
                    j+=1;
                    if (j == s.length()){
                        break;
                    }
                }
                f2.numerator *= minus;
                /*System.out.println(f1.numerator);
                System.out.println(f1.denominator);
                System.out.println(f2.numerator);
                System.out.println(f2.denominator);*/
                result = sub(f1, f2);
                String newS = result;
                if (j < s.length()){
                    newS += s.substring(j);
                }
                //System.out.println(newS);
                return ToDoSums(newS);
            }
        }
        return s;
    }

    public static String ToDoBr (String s){
        int FirstBraket = -1;
        int LastBraket = -1;
        for (int i = 0; i < s.length(); i++){
            if (s.charAt(i) == '(') FirstBraket = i;
            if (s.charAt(i) == ')'){
                LastBraket = i;
                break;
            }
        }
        if (FirstBraket != -1){
            String newS = s.substring(0, FirstBraket);
            newS += ToDoSums(s.substring(FirstBraket + 1, LastBraket));
            if (LastBraket < s.length() - 1) newS += s.substring(LastBraket + 1, s.length());
            s = ToDoBr(newS);
        }
        return ToDoSums(s);
    }




    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String s = in.nextLine();
        //String s = "(-1/3 * 3/4 - 1/2)*2/1)";
        s = s.replaceAll("\s", "");
        if (s == "quit" || s == "Quit"){
            System.out.println("Завершение работы");
            System.exit(0);
        }
        int OpenBraket = 0;
        int CloseBraket = 0;
        for (int i = 0; i < s.length() - 1; i++){
            if ((s.charAt(i) == '+' || s.charAt(i) == '*' || s.charAt(i) == '-' || s.charAt(i) == ':') && (s.charAt(i + 1) == '+' || s.charAt(i + 1) == '*' || s.charAt(i + 1) == '-' || s.charAt(i + 1) == ':')){
                System.out.println("Ошибка. Некорректное выражение!");
                System.exit(0);
            }
        }
        for (int i = 0; i < s.length(); i++){
            if (s.charAt(i) == '(') OpenBraket++;
            if (s.charAt(i) == ')') CloseBraket++;
        }
        if (OpenBraket != CloseBraket){
            System.out.println("Ошибка. Некорректное выражение!");
            System.exit(0);
        }
        s = ToDoBr(s);
        int minus = 1;
        for (int i = 0; i < s.length(); i++){
            if (s.charAt(i) == '-'){
                minus *= -1;
            }
        }
        s = s.replaceAll("-", "");
        if (minus == -1){
            s = "-" + s;
        }
        System.out.println(s);
    }
}