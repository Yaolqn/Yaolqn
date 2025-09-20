import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ProjectTest {

    @Test
    void test1() {
        String s1 = "今天天气好";
        String s2 = "今天天气好";
        double sim = Project.calculateSimilarity(s1, s2);
        assertEquals(1.00, sim, 0.001);
    }

    @Test
    void test2() {
        String s1 = "abc";
        String s2 = "xyz";
        double sim = Project.calculateSimilarity(s1, s2);
        assertEquals(0.00, sim, 0.001);
    }

    @Test
    void test3() {
        String original = "今天是星期天，天气晴，今天晚上我要去看电影。";
        String plagiarized = "今天是周天，天气晴朗，我晚上要去看电影。";
        double sim = Project.calculateSimilarity(original, plagiarized);
        assertTrue(sim > 0.8 && sim < 0.9); // 预期在 0.8~0.9 之间
    }

    @Test
    void test4() {
        String s1 = "";
        String s2 = "hello";
        double sim = Project.calculateSimilarity(s1, s2);
        assertEquals(0.00, sim, 0.001);
    }

    @Test
    void test5() {
        String s1 = "";
        String s2 = "";
        double sim = Project.calculateSimilarity(s1, s2);
        assertEquals(0.00, sim, 0.001);
    }

    @Test
    void test6() {
        String s1 = "a";
        String s2 = "a";
        double sim = Project.calculateSimilarity(s1, s2);
        assertEquals(1.00, sim, 0.001);
    }

    @Test
    void test7() {
        String s1 = "Deep learning is a powerful method in machine learning.";
        String s2 = "Deep learning is a very powerful method in modern machine learning.";
        double sim = Project.calculateSimilarity(s1.toLowerCase(), s2.toLowerCase());
        assertTrue(sim > 0.85);
    }

    @Test
    void test8() {
        String s1 = "a";
        String s2 = "b";
        double sim = Project.calculateSimilarity(s1, s2);
        assertEquals(0.00, sim, 0.001);
    }

    @Test
    void test9() {
        String s1 = "abcdef";
        String s2 = "bcde";
        double sim = Project.calculateSimilarity(s1, s2);
        assertEquals(0.80, sim, 0.001);
    }

    @Test
    void test10() {
        String s1 = "aaaaaa";
        String s2 = "aaabbb";
        double sim = Project.calculateSimilarity(s1, s2);
        assertEquals(0.50, sim, 0.001);
    }
}