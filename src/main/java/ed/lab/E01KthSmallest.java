package ed.lab;

public class E01KthSmallest {
    private int count;
    private int answer;
    public int kthSmallest(TreeNode<Integer> root, int k) {
        count = 0;
        answer = 0;
        inOrder(root, k);
        return answer;
    }
    private void inOrder(TreeNode<Integer> node, int k) {
        if (node == null) {
            return;
        }
        inOrder(node.left, k);
        count++;
        if (count == k) {
            answer = node.value;
            return;
        }

        inOrder(node.right, k);
    }

}