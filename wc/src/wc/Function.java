package wc;

import java.io.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author zhongxm
 */
public class Function {

    static int getCharNumber(String fileName) throws IOException {
        int count = 0;
        File file = new File(fileName);
        String line = null;
        if(file.exists()){
            BufferedReader br=new BufferedReader(new FileReader(file));
            while ((line = br.readLine()) != null){
                Pattern p = Pattern.compile("\\s*|\t|\r|\n");
                Matcher m = p.matcher(line);
                line = m.replaceAll("");
                count += line.length();//按行统计字符
            }
        }
        else{
            System.out.println("文件不存在，请重新输入：");
        }
        return count;

    }
    static int getWordNumber(String fileName) throws IOException{
        int count = 0;
        File file = new File(fileName);
        String[] word = null;
        if(file.exists()){
            BufferedReader br=new BufferedReader(new FileReader(file));
            String line=null;
            StringBuilder sbr=new StringBuilder();
            while((line=br.readLine())!=null){
                sbr.append(line);
                String string=sbr.toString();
                string=string.replaceAll("\\W", " ");//用空格替换非词字符
                word=string.split("\\s+");//按空格将字符串拆分后放入字符串数组中
                count=word.length;//数组的长度就是词的数目
            }
            br.close();
        }
        else{
            System.out.println("文件不存在，请重新输入：");
        }
        return count;

    }
    static int getLineNumber(String fileName) throws IOException{
        int count = 0;
        File file = new File(fileName);
        if(file.exists()){
            BufferedReader br=new BufferedReader(new FileReader(file));
            while(br.readLine()!=null){
                count++;
            }
        }
        else{
            System.out.println("文件不存在，请重新输入：");
        }
        return count;
    }
    static int[] extra_Function(String fileName) throws IOException{
        int null_Line = 0;
        int code_Line = 0;
        int comment_Line = 0;
        int line = 0;
        String str = null;
        File file = new File(fileName);
        if(file.exists()){
            BufferedReader br = new BufferedReader(new FileReader(file));
            Pattern null_Pattern = Pattern.compile("^(\\s*)\\r\\n");// 构造空行的模式匹配（既行的开头和结尾间只有空白字符\s)
            Pattern comment = Pattern.compile("((//)|(/\\*+)|((^\\s)*\\*)|((^\\s)*\\*+/))+",Pattern.MULTILINE + Pattern.DOTALL);//注释
            while((str = br.readLine())!=null){
                line ++;
                if(comment.matcher(str).find()){
                    comment_Line ++ ;
                }
                else if(null_Pattern.matcher(str).find()){
                    null_Line ++;
                }
            }
            code_Line=line-comment_Line-null_Line;
        }
        else{
            System.out.println("文件不存在，请重新输入：");
        }
        int[] count={null_Line,code_Line,comment_Line};
        return count;
    }
    static void scanFile(File file) throws IOException{
        if(file.exists()){
            if(!file.isDirectory()){
                System.out.println("非法目录，请重新输入：");
            }
            else{
                File[] files = file.listFiles(new fileFilter());
                for (File file1 : files) {
                    if (file1.isDirectory()) {
                        scanFile(file1);
                    } else {
                        String path = file1.getAbsolutePath();
                        int num1 = getCharNumber(path);
                        int num2 = getWordNumber(path);
                        int num3 = getLineNumber(path);
                        int[] num4 = extra_Function(path);
                        int[] num5 = {num1,num2,num3,num4[0],num4[1],num4[2]};
                        System.out.println(path);
                        System.out.println("字符数："+num1+"\n单词数："+num2+"\n行数："+num3+"\n空行："+num4[0]+" "+"代码行："+num4[1]+" "+"注释行："+num4[2]);
                    }
                }
            }
        }
    }
    public static class fileFilter implements FileFilter {

        @Override
        public boolean accept(File pathname) {
            String name = pathname.getName();
            if (name.endsWith(".txt") || name.endsWith(".java") || name.endsWith(".c") || name.endsWith(".cpp") || pathname.isDirectory()) {
                return true;
            } else {
                return false;
            }
        }
    }
}
