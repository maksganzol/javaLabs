import java.util.Scanner;

public class Lab4 {
    public static void main(String args[]){
        Scanner sc = new Scanner(System.in);
        String input = sc.next();

        int count = 0; //Cчетчик символов в интервале ['0', '9']
        boolean noSymbols = false;
        boolean begin = false;
        for(int i = 0; i < input.length(); i++){
            if(begin && input.charAt(i)=='9') //Если начался интервал и встречается "9", то выходим из цикла
                break;
            if(begin)  //Если начался интревал, то увеличиваем счетчик
                count++;
            if(!begin && input.charAt(i)=='0') //Если встречаем 0 и интервал не начался, то начинаем его
                begin = true;
            if(i == input.length()-1) //Если мы на последнем символе, а значит не встретили "9", то показываем, что нет символов
                noSymbols = true;
        }

        char[] symb = new char[count];
        int symbCount = 0; //Счетчик "реальной" длины массива symb
        if(!noSymbols) {
            begin = false;
            for (int i = 0; i < input.length(); i++) {
                if (begin && input.charAt(i) == '9')
                    break;
                if (begin) {
                    symb[symbCount] = input.charAt(i);
                    symbCount++;
                }
                if (!begin && input.charAt(i) == '0')
                    begin = true;
            }
        }
        String output = new String(symb);
        System.out.println(output);
    }
}
