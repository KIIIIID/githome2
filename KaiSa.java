
import java.util.Map;
import java.util.HashMap;
import java.util.Scanner;

public class KaiSa {
    public static void main(String[] args){
    	Scanner scanner = new Scanner(System.in);
    	do{
    	System.out.println("lock press 1, unlock press 0");//选择解密or加密
    	String Cho = scanner.nextLine();
    	
    	if (Cho.equals("1") || Cho.equals("0")){   //检查输入
    		System.out.println("Input code:");
        	String code = scanner.nextLine();//输入原码
        	System.out.println("Input number");
        	String N = scanner.nextLine();//输入偏移量
        	if (N.equals("")){
        		N = "3";
        	}
        	try{
        		int n = Integer.valueOf(N);
        		if (n<=0){   //检测偏移量
            		System.out.println("Wrong Number!");
            	}else if(n>0 && n<=26){
            		Map<String,String> dict = Lock(n,Cho);
                	Write(dict,code);
            	}else{       //处理偏移量大于26
            		int y = n % 26;
            		Map<String,String> dict = Lock(y,Cho);
                	Write(dict,code);
            	}
            	System.out.println("");
        	}catch(NumberFormatException n){
        		System.out.println("Please input a number");
        		continue;
        	}
    	}else{
    		System.out.println("Wrong Choice!");
    	}
    	} while (true);
    }
    	
    public static void Write(Map<String,String> dict,String code){
    	for (int i=0;i<code.length();i++){
    		int single = Integer.valueOf(code.charAt(i));
    		if (64<single && single<91){  //大写
    			System.out.print(dict.get(String.valueOf(code.charAt(i))));
    		}else if(96<single && single<123){//小写
    			String UP = dict.get(String.valueOf(code.charAt(i)).toUpperCase());
    			System.out.print(UP.toLowerCase());
    		}else{   //字符
    			System.out.print(code.charAt(i));
    		}
    	}
    }
    
    public static Map<String,String> Lock(int n,String Switch){  //生成解密字典
    	Map<String,String> lock = new HashMap<>(); 
    	String Word = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    	String NewWord = Word.substring(n,26) + Word.substring(0,n);
    	for (int i=0;i<26;i++){
    		String word = String.valueOf(Word.charAt(i));
    		String newword = String.valueOf(NewWord.charAt(i));
    		switch(Switch){
    		case "1":
        		lock.put(word, newword);
    		case "0":
        		lock.put(newword, word);
    		}
    	}
    	return lock;
    }
}
