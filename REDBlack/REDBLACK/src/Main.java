public class Main {
    public static void main(String[] args) {

        RBT tree = new RBT();
        Node root=new Node();
        root = tree.ins(root, 10);
        root = tree.ins(root, 100);
        root = tree.ins(root, 50);
        root = tree.ins(root, 30);
        root = tree.ins(root, 40);
      //  root = tree.ins(root, 1000);
//        root.right.col=1;
//        root.right.right.right.col=1;
//        root.right.right.left.col=1;
//        root.right.col=1;
 //     root =  tree.delete(root,-10);


       // root =  tree.delete(root,15);

        tree.printTreeHelper(root, 0);

    }
}