package week5;

import java.io.*;
import java.util.*;

public class P007 {
    // 파이썬의 tree = {} 와 동일한 역할을 하는 Map
    // key: 루트 노드 이름, value: [왼쪽 자식, 오른쪽 자식] 배열
    static Map<String, String[]> tree = new HashMap<>();
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine().trim());

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            String root = st.nextToken();
            String left = st.nextToken();
            String right = st.nextToken();
            tree.put(root, new String[]{left, right});
        }

        preorder("A");
        sb.append("\n");
        inorder("A");
        sb.append("\n");
        postorder("A");

        System.out.println(sb.toString());
    }

    // 전위 순회 (Root -> Left -> Right)
    static void preorder(String root) {
        if (root.equals(".")) return;
        sb.append(root);
        preorder(tree.get(root)[0]);
        preorder(tree.get(root)[1]);
    }

    // 중위 순회 (Left -> Root -> Right)
    static void inorder(String root) {
        if (root.equals(".")) return;
        inorder(tree.get(root)[0]);
        sb.append(root);
        inorder(tree.get(root)[1]);
    }

    // 후위 순회 (Left -> Right -> Root)
    static void postorder(String root) {
        if (root.equals(".")) return;
        postorder(tree.get(root)[0]);
        postorder(tree.get(root)[1]);
        sb.append(root);
    }
}