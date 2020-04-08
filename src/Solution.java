/**
 * 二叉树重建 前序、中序、后序遍历
 */
import java.util.Arrays;
import java.util.GregorianCalendar;

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode(int x) { val = x; }
}
public class Solution {
    public static void main(String[] arg){
        int[] pre = new int[]{1,2,4,7,3,5,6,8};
        int[] in = new int[]{4,7,2,1,5,3,8,6};
        System.out.println("前序遍历为：");
        preorder(reConstructBinaryTree(pre,in));
        System.out.println();
        System.out.println("中序遍历为：");
        inorder(reConstructBinaryTree(pre,in));
        System.out.println();
        System.out.println("后序遍历为：");
        afterorder(reConstructBinaryTree(pre,in));
    }
    private static TreeNode reConstructBinaryTree(int[] pre, int[] in) {
        if (pre.length == 0 || in.length == 0) {
            return null;
        }
        TreeNode root = new TreeNode(pre[0]);
        // 在中序中找到前序的根
        for (int i = 0; i < in.length; i++) {
            if (in[i] == pre[0]) {
                // 左子树，注意 copyOfRange 函数，左闭右开 [from,to)
                root.left = reConstructBinaryTree(Arrays.copyOfRange(pre, 1, i + 1), Arrays.copyOfRange(in, 0, i));
                // 右子树，注意 copyOfRange 函数，左闭右开
                root.right = reConstructBinaryTree(Arrays.copyOfRange(pre, i + 1, pre.length), Arrays.copyOfRange(in, i + 1, in.length));
                break;
            }
        }
        return root;
    }

    // 前序遍历
    static void preorder(TreeNode root) {
        if (root != null) {
            System.out.print(root.val+" ");
            preorder(root.left);
            preorder(root.right);
        }
    }

    // 中序遍历
    static void inorder(TreeNode root){
        if(root!=null) {
            inorder(root.left);
            System.out.print(root.val+" ");
            inorder(root.right);
        }
    }

    // 后序遍历
    static void afterorder (TreeNode root){
        if(root!=null) {
            inorder(root.left);
            inorder(root.right);
            System.out.print(root.val+" ");
        }
    }

}

