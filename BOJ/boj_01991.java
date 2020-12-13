// boj 1991 트리순회
// 트리

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class boj_01991 {

    private static node[] tree = new node[27];
    private static final int none = '.' - 'A';

    private static class node {
        public int left;
        public int right;

        public node(int left, int right) {
            this.left = left;
            this.right = right;
        }
    }


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());

        while (n-- > 0) {
            st = new StringTokenizer(br.readLine());
            int now = st.nextToken().charAt(0) - 'A';
            int left = st.nextToken().charAt(0) - 'A';
            int right = st.nextToken().charAt(0) - 'A';

            tree[now] = new node(left, right);
        }

        preorder(0);
        System.out.println();
        inorder(0);
        System.out.println();
        postorder(0);
    }

    private static void preorder(int now) {
        System.out.print((char) (now + 'A'));
        if (tree[now].left != none) preorder(tree[now].left);
        if (tree[now].right != none) preorder(tree[now].right);
    }
    private static void inorder(int now) {
        if (tree[now].left != none) inorder(tree[now].left);
        System.out.print((char) (now + 'A'));
        if (tree[now].right != none) inorder(tree[now].right);
    }
    private static void postorder(int now) {
        if (tree[now].left != none) postorder(tree[now].left);
        if (tree[now].right != none) postorder(tree[now].right);
        System.out.print((char) (now + 'A'));
    }
}
