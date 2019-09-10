
import java.util.Map;
import java.util.HashMap;
import java.util.Scanner;
import java.io.*;

public class Kaisa{
    public static void main(String[] args){
    	Scanner scanner = new Scanner(System.in);
    	String path;
    	do{         //检查输入路径
    	    System.out.println("Add the file path (like:C:\\...\\...)");
    	    path = scanner.nextLine();
    	    if (path.matches("^[A-z]:(\\\\.+?)*")){
    	    	break;
    	    }else{
    	    	System.out.println("Wrong path!");
    	    	
    	    	System.out.println(path.matches("^[A-z]:\\(.+?\\)*"));
    	    }
    	} while(true);
    	
    	do{
        	System.out.println("lock press 1, unlock press 0,quit press exit");//选择解密or加密
      	    String Cho = scanner.nextLine();
    	
      	    if (Cho.equals("exit")){
      	    	break;
      	    }
      	    if (Cho.equals("1") || Cho.equals("0")){   //检查输入
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
      	    			try{
      	    				Read(dict,path);
      	    			}catch(Exception e){
      	    				System.out.println("Error");
      	    				break;
      	    			}
      	    		}else{       //处理偏移量大于26
      	    			int y = n % 26;
      	    			Map<String,String> dict = Lock(y,Cho);
      	    			try{
      	    				Read(dict,path);
      	    				}catch(Exception e){
      	    					System.out.println("Error");
      	    					break;
      	    				}
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
    	scanner.close();
    }
    	
    	
    public static void Write(Map<String,String> dict,char code){  //打印
    	int single = Integer.valueOf(code);
    	if (64<single && single<91){  //大写
    		System.out.print(dict.get(String.valueOf(code)));
    	}else if(96<single && single<123){//小写
    		String UP = dict.get(String.valueOf(code).toUpperCase());
    		System.out.print(UP.toLowerCase());
    	}else{   //字符
    		System.out.print(code);
    	}
    }
    
    public static void Read(Map<String,String> dict,String path) throws IOException{  //文件读取
    	path = path + "\\body.txt";
    	try(InputStream input = new FileInputStream(path)){
    		int n;
    		while ((n = input.read()) != -1){
    			Write(dict,(char)n);
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