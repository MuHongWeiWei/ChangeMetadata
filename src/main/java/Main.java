import java.io.*;

public class Main {
    public String getFileText(String path) {
        StringBuilder strBuf = new StringBuilder();
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(path), "MS950"));
            while (br.ready()) {
                String brStr = br.readLine();
                if (brStr.length() > 0) {
                    int c = brStr.charAt(0);
                    if (c == 65279) {
                        brStr = brStr.substring(1);
                    }
                    strBuf.append(brStr);
                }
            }
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return strBuf.toString();
    }

    public int writeFileText(String path, String txt) {
        try {
            BufferedWriter br = new BufferedWriter(new FileWriter(path));
            br.write(txt);
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
            return -1;
        }
        return 1;
    }

    public String changeTxtWord(String path, String oldWord, String word) {
        String txt = getFileText(path);            //取得檔案內容

        System.out.println(txt);
        txt = txt.replaceAll(oldWord, word);    //換掉某個字
        writeFileText(path, txt);                //將換過的檔案內容寫回去
        return txt;
    }

    public static void main(String[] args) {
        Main lct = new Main();    //建立處理檔案內容物件

        for (int i = 0; i < 3333; i++) {
            lct.changeTxtWord("metadata/" + i, "QmbrcmUB9UoC4paDJ3NJybo2zLnaK2eQchfTcyUQqWpWDB",
                    "QmNbzKqmqGU1JNSY5TM6EpLh7fZxVt3Fh1ehxqE37CUZMj");//將David換成Ben
        }
    }
}