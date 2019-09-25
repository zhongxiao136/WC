package wc;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 *
 * @author  zhongxm
 */
public class main {

    public static void main(String[] args) throws IOException {
        // TODO code application logic here
        Function function = new Function();
        System.out.println("-c 返回文件 file.c 的字符数\n" +"-w 返回文件 file.c 的词的数目  \n" +"-l 返回文件 file.c 的行数");
        System.out.println("-s   递归处理目录下符合条件的文件。\n" +"-a   返回更复杂的数据（代码行 / 空行 / 注释行）。");
        System.out.println("-x  图形化界面 \n"+"-e  即可退出程序");
        System.out.println("请输入命令：（格式为：命令 文件路径）");
        while(true){

            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            String input = br.readLine();
            String[] commond = input.split(" ");   //获得命令和文件名
            if(commond[0].equals("-x")){						//“-x”和“退出”先提前判断
                Frame frame = new Frame();				//一个图形化界面
                frame.setVisible(true);
            } else if(commond[0].equals("-e"))	{
                System.exit(0);
            } else{
                switch(commond[0]) {
                    case "-c":
                        int num1 = function.getCharNumber((commond[1]));
                        System.out.println("字符数：" + num1);
                        break;
                    case "-w":
                        int num2 = function.getWordNumber((commond[1]));
                        System.out.println("单词数：" + num2);
                        break;
                    case "-l":
                        int num3 = function.getLineNumber(commond[1]);
                        System.out.println("行数：" + num3);
                        break;
                    case "-a":
                        int[] num4 = function.extra_Function(commond[1]);
                        System.out.println("空行：" + num4[0] + " " + "\n" + "代码行：" + num4[1] + " " + "\n" + "注释行：" + num4[2]);
                        break;
                    case "-s":
                        function.scanFile(new File(commond[1]));
                        break;
                    default:
                        System.out.println("输入的命令不对呢，请再检查一次。。");
                        break;
                }
            }
        }

    }
}
