import java.io.*;
import java.nio.charset.StandardCharsets;

/**
 * 论文查重工具：基于 LCS 计算文本相似度
 * 支持命令行参数输入：原文文件 抄袭文件 输出文件
 */
public class Project {

    public static void main(String[] args) {
        // 检查参数数量
        if (args.length != 3) {
            System.err.println("Usage: java -jar main.jar <original.txt> <plagiarized.txt> <output.txt>");
            System.exit(1);
        }

        String originalPath = args[0];
        String plagiarizedPath = args[1];
        String outputPath = args[2];

        try {
            String originalText = readFile(originalPath);
            String plagiarizedText = readFile(plagiarizedPath);

            double similarity = calculateSimilarity(originalText, plagiarizedText);

            // 写入结果，保留两位小数
            try (PrintWriter writer = new PrintWriter(new OutputStreamWriter(
                    new FileOutputStream(outputPath), StandardCharsets.UTF_8))) {
                writer.printf("%.2f%n", similarity);
            }

        } catch (Exception e) {
            System.err.println("Error processing files: " + e.getMessage());
            System.exit(1);
        }
    }

    /**
     * 读取文件内容，合并所有行为一个字符串（去除每行首尾空格，但不拆分词）
     */
    private static String readFile(String filePath) throws IOException {
        StringBuilder sb = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(
                new FileInputStream(filePath), StandardCharsets.UTF_8))) {
            String line;
            while ((line = reader.readLine()) != null) {
                sb.append(line.trim());
            }
        }
        return sb.toString();
    }


    private static double calculateSimilarity(String s1, String s2) {
        return 0;
    }
}