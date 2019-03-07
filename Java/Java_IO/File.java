import java.io.*;
import java.util.Scanner;

public class Demo {




    public static String minMaxLength(File[] file) {
        long min = file[0].length();
        long max = file[0].length();
        String file1 = file[0].getName();
        String file2 = file[0].getName();

        for (File f : file) {
            if (f.length() < min) {
                min = f.length();
                file1 = f.getName();
            }
            if (f.length() > max) {
                max = f.length();
                file2 = f.getName();
            }
        }
        return file1 + " " + min + " " + file2 + " " + max;
    }

    public static void printAllFiles(File file) {
        if (file == null) {
            System.out.println("this is null");
        }

        if (file.isDirectory()) {
            System.out.println(file.getName() + " " + "is a directory");
            for (File f : file.listFiles()) {
                printAllFiles(f);
            }
        } else {
            System.out.println(file.getName() + " " + "is a file");
        }
    }

    public static void split(File file) throws Exception {
        int size = 128;
        byte[] bytes = new byte[(int)file.length()];
        int number = bytes.length % size == 0 ? bytes.length / size : bytes.length / size + 1;
        FileInputStream fis = new FileInputStream(file);

        try {
            fis.read(bytes);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (fis != null) {
                try {
                    fis.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }

        for (int i = 0; i < number; i++) {
            File f = new File(file.getParent(), file.getName() + "-" + i);

            /**
             * 实现autocloseable接口
             */
            try (FileOutputStream fos = new FileOutputStream(f)) {
                if (i != number - 1) {
                    fos.write(bytes, size * i, size);
                } else {
                    fos.write(bytes, size * i,bytes.length - size * i);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static void encodeFile(File souFile) {
        try (FileReader fr = new FileReader(souFile)) {
            char[] content = new char[(int) souFile.length()];
            fr.read(content);
            System.out.println(new String(content));
            for (int i = 0; i < content.length; i++) {
                switch (content[i]) {
                    case 'W':
                        content[i] = 'D';
                        break;
                    default:
                        content[i] = 'A';
                        break;
                }
            }
            System.out.println(new String(content));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void buRead(File file) {
        try (
                FileWriter fis = new FileWriter(file);
                PrintWriter pw = new PrintWriter(fis);
        ) {
            pw.println("shuai");
        } catch (Exception e) {
            e.printStackTrace();
        }


    }






}