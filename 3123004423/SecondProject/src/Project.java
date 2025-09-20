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
            // 读取文件内容
            String originalText = readFile(originalPath);
            String plagiarizedText = readFile(plagiarizedPath);

            // 关键计算操作
            double similarity = calculateSimilarity(originalText, plagiarizedText);

            // 写入结果，保留两位小数
            try (PrintWriter writer = new PrintWriter(new OutputStreamWriter(
                    new FileOutputStream(outputPath), StandardCharsets.UTF_8))) {
                writer.printf("%.2f%n", similarity);
            }

        } catch (Exception e) {
            // 读取文件失败退出
            System.err.println("Error processing files: " + e.getMessage());
            System.exit(1);
        }
    }

    /**
     * 读取文件内容，合并所有行为一个字符串（去除每行首尾空格，但不拆分词）
     */
    public static String readFile(String filePath) throws IOException {
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


    /**
     * 使用动态规划计算 LCS，并返回基于 (2*LCS)/(len1+len2) 的相似度
     */
    public static double calculateSimilarity(String s1, String s2) {
        int m = s1.length();
        int n = s2.length();

        if (m == 0 || n == 0) return 0.0;

        int[][] dp = new int[m + 1][n + 1];

        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (s1.charAt(i - 1) == s2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }

        int lcsLength = dp[m][n];
        return (2.0 * lcsLength) / (m + n);
    }
}