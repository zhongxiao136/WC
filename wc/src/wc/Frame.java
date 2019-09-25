package wc;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.*;

import javax.swing.*;

public class Frame extends JFrame{
    private static final long serialVersionUID = 1L;

    Toolkit kit = Toolkit.getDefaultToolkit();
    Dimension screenSize = kit.getScreenSize();
    private static  JTextArea textArea = new JTextArea();
    private JPanel chooseBar = new JPanel();
    private JButton	count_c	= new JButton("字符数");
    private JButton	count_w = new JButton("词数");
    private JButton	count_l	= new JButton("行数");
    private JButton	count_a = new JButton("扩展");
    private JButton	count_z	= new JButton("汇总");
    private JButton	count_exit	= new JButton("退出");
    private Font buttonFont = new Font("宋体",Font.PLAIN,15);

    int charNum = 0;
    int wordNum = 0;
    int lineNum = 0;
    int blankLineNum = 0;
    int codeLineNum = 0;
    int annotationLineNum = 0;
    int btn = 0;

    Frame(){
        this.setTitle("WC测试程序");
        //设置窗口的位置大小
        this.setSize(650, 420);
        this.setLocation(330, 200);
        initEventListeners();
        chooseBar.setLayout(new GridLayout(6,1));
        add(chooseBar,BorderLayout.WEST);
        initChooseBar();
        initTextArea();
    }

    private void initChooseBar(){												//右边选项栏设置
        chooseBar.add(count_c);
        chooseBar.add(count_w);
        chooseBar.add(count_l);
        chooseBar.add(count_a);
        chooseBar.add(count_z);
        chooseBar.add(count_exit);
        count_c.setFont(buttonFont);
        count_w.setFont(buttonFont);
        count_l.setFont(buttonFont);
        count_a.setFont(buttonFont);
        count_z.setFont(buttonFont);
        count_exit.setFont(buttonFont);
    }

    private void initTextArea(){
        textArea.setFont(new Font("宋体", Font.PLAIN, 15));
        textArea.setMargin(new Insets(3,10,3,10));
        textArea.setLineWrap(true);
        textArea.setDragEnabled(true);
        JScrollPane panel = new JScrollPane(textArea,
                ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,
                ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        getContentPane().add(panel, BorderLayout.CENTER);
    }

    private void initEventListeners(){              //初始化监听器
        count_c.addActionListener(this::countC);
        count_w.addActionListener(this::countW);
        count_l.addActionListener(this::countL);
        count_a.addActionListener(this::countA);
        count_z.addActionListener(this::countZ);
        count_exit.addActionListener(this::countExit);
    }

    private void countC(ActionEvent event){
        btn = 1;
        CountStart();
    }
    private void countW(ActionEvent event){
        btn = 2;
        CountStart();
    }
    private void countL(ActionEvent event){
        btn = 3;
        CountStart();
    }
    private void countZ(ActionEvent event){
        btn = 4;
        CountStart();
    }

    private void countA(ActionEvent event){
        btn = 5;
        CountStart();
    }

    private void countExit(ActionEvent event){
        System.exit(0);
    }

    public void CountStart(){
        String filePath = null;
        JFileChooser choose = new JFileChooser();
        choose.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
        choose.showDialog(new JLabel(), "选择要统计的文件");
        File file = choose.getSelectedFile();
        //存在文件绝对路径
        filePath = file.getAbsolutePath();
        try{
            String encoding = "GBK";
            InputStreamReader readFile = new InputStreamReader(new FileInputStream(file),encoding);
            switch(btn){
                case 1:
                    charNum = Function.getCharNumber(filePath);
                    textArea.append(file+":\n"+"字符数：" + charNum + "\n\n");
                    break;
                case 2:
                    wordNum = Function.getWordNumber(filePath);
                    textArea.append(file+":\n词数：" + wordNum+"\n\n");
                    break;
                case 3:
                    lineNum = Function.getLineNumber(filePath);
                    textArea.append(file+":\n行数：" + lineNum+"\n\n");
                    break;
                case 4:
                    int[] resultList = null;
                    resultList = Function.extra_Function(filePath);
                    blankLineNum = resultList[0];
                    codeLineNum = resultList[1];
                    annotationLineNum = resultList[2];
                    textArea.append(file+":\n空行数：" + blankLineNum + "\n代码行数："
                            + codeLineNum + "\n注释行数" + annotationLineNum+"\n\n");
                    break;
                case 5:
                    int[] resultList1 = null;
                    charNum = Function.getCharNumber(filePath);
                    wordNum = Function.getWordNumber(filePath);
                    lineNum = Function.getLineNumber(filePath);
                    resultList1 = Function.extra_Function(filePath);
                    blankLineNum = resultList1[0];
                    codeLineNum = resultList1[1];
                    annotationLineNum = resultList1[2];
                    textArea.append(file+":\n"+"字符数：" + charNum + "\n词数："
                            + wordNum + "\n行数：" + lineNum + "\n空行数：" + blankLineNum + "\n代码行数："
                            + codeLineNum + "\n注释行数" + annotationLineNum+"\n");
                    break;
            }
        }catch(IOException e){
            System.out.println("文件路径错误或者文件不支持喔~");
            e.printStackTrace();
        }
    }
}


