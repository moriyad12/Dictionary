

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Optional;
import java.util.Scanner;

public class RBT {
    /////// ZERO RED     ONE BLACK
    int size;
    String s="";
    Node ins(Node root, Object val) {
        size++;
        return insert(root, val);
    }

    Node rightRotate(Node root, boolean change) {
        Node upperNode = root.parent;
        Node grandNode = root.parent.parent;
        root.parent = grandNode;
        Node rightOfRoot = root.right;
        if (grandNode != null && !grandNode.is_null()) {
            if (upperNode.isLeft()) {
                grandNode.left = root;
            } else grandNode.right = root;
        }
        upperNode.parent = root;
        upperNode.left = new Node(upperNode);

        if (rightOfRoot != null && !rightOfRoot.is_null()) {
            upperNode.left = rightOfRoot;
            rightOfRoot.parent = upperNode;
        }
        root.right = upperNode;
        if (change) {
            root.col = 1;
            upperNode.col = 0;
        }
        return root;
    }

    private Node leftRotate(Node down, boolean change) {
        Node mid = down.parent;
        Node up = down.parent.parent;
        down.parent = up;
        if (mid.parent != null && !mid.parent.is_null()) {
            if (mid.isLeft()) {
                up.left = down;
            } else {
                up.right = down;
            }
        }
        Node sonOfdown = down.left;
        down.left = mid;
        mid.parent = down;
        mid.right=new Node(mid);
        if (sonOfdown!=null&&!sonOfdown.is_null()) {
            mid.right = sonOfdown;
            sonOfdown.parent = mid;
        }
        if (change) {
            down.col = 1;
            mid.col = 0;
        }
        return down;
    }

    Node fixTree(Node root, boolean bool) {
        if (bool) {
            if (root.col == 0 && root.left.col == 0) {
                Node sib = root.getSibling();
                if (sib == null || sib.is_null() || sib.col == 1) {

                    if (root.isLeft()) {

                        rightRotate(root, true);

                    } else {
                        Node n = rightRotate(root.left, false);

                        root = root.parent;

                        leftRotate(root, true);

                    }
                } else {
                    root.col = 1;
                    sib.col = 1;
                    if (root.parent.parent != null && !root.parent.parent.is_null()) {
                        root.parent.col = 0;
                    } else {
                        root.parent.updateHeight();
                    }
                }
            }

        } else if (!bool) {
            if (root.col == 0 && root.right.col == 0) {
                Node sib = root.getSibling();
                if (sib == null || sib.is_null() || sib.col == 1) {
                    if (!root.isLeft()) {
                        leftRotate(root, true);
                    } else {
                        leftRotate(root.right, false);
                        root = root.parent;
                        rightRotate(root, true);
                    }
                } else {
                    root.col = 1;
                    sib.col = 1;
                    if (root.parent.parent != null && !root.parent.parent.is_null()) {
                        root.parent.col = 0;
                    } else {
                        root.parent.updateHeight();
                    }
                }
            }
        }
        return root;
    }

    private Node insert(Node root, Object ins_val) {
        if (root.is_null()) {
            if (root.parent == null || root.parent.is_null()) {
                return new Node(ins_val, 1, new Node());
            } else {
                return new Node(ins_val, 0, root.parent);
            }
        }
        if (root.val.equals(ins_val)) {
            root.freq++;
            size--;
            return root;
        }
        if (root.comp(ins_val) < 0) {
            Node lft = insert(root.left, ins_val);
            if (lft == root.parent) {
                return lft;
            }
            root.left = lft;
            root = fixTree(root, true);
        } else {
            Node rig = insert(root.right, ins_val);
            if (rig == root.parent) {
                return rig;
            }
            root.right = rig;
            root = fixTree(root, false);
        }
        return root;
    }
    Node search (Node root, Object ins_val){
            if (root == null ||root.is_null()) {
                return null;
            }
            if (root.comp(ins_val) == 0) {
                return root;
            }
            if (root.comp(ins_val) < 0) {
                return search(root.left, ins_val);
            } else {
                return search(root.right, ins_val);
            }
        }
        boolean Search(Node root, Object ins_val){
        if(this.search( root, ins_val)==null) return false;
        else return true;
        }
    Node getmino(Node root) {//GET minimum value
        if(root.is_null())return null;
        Node s = root.left;
        Node last = root;
        while (!(s.is_null())) {
            last = s;
            s = s.left;
        }
        return last;
    }
    Node HelpDelete(Node node){
        Node uncle = node.parent.getSibling();
        Node sebling = node.getSibling();
        if(node.parent == null || node.parent.is_null()) return node ;//1
        else if(node.parent.col==0 && sebling.col==1 &&(sebling.is_null()||(sebling.left.col == 1 && sebling.right.col == 1))){//4
            node.parent.col = 1;
            sebling.col = 0;

        }else if(node.isLeft() && sebling.col==1 && (!sebling.is_null()&&sebling.right.col==0)){//6
            int temp = node.parent.col;
            node.parent.col = sebling.col;
            sebling.col = temp;
            sebling.right.col = 1;
            leftRotate(sebling,false);
        }
        else if(!node.isLeft() && sebling.col==1 && (!sebling.is_null()&&sebling.left.col==0)){//6 part2
            int temp = node.parent.col;
            node.parent.col = sebling.col;
            sebling.col = temp;
            sebling.left.col = 1;
            rightRotate(sebling,false);
        }
        else if(node.isLeft() && node.parent.col ==1 && sebling.col == 0 && (sebling.is_null()||(sebling.left.col == 1 && sebling.right.col == 1))){//2
            int temp = node.parent.col;
            node.parent.col = sebling.col;
            sebling.col = temp;
            sebling.left.col = 1;
            leftRotate(sebling,false);
            HelpDelete(node);
        } else if(!node.isLeft() && node.parent.col ==1 && sebling.col == 0 && (sebling.is_null()||(sebling.left.col == 1 && sebling.right.col == 1))){//2 part2
            int temp = node.parent.col;
            node.parent.col = sebling.col;
            sebling.col = temp;
            sebling.right.col = 1;
            rightRotate(sebling,false);
            HelpDelete(node);
        }else if( node.parent.col ==1 && sebling.col == 1 && (sebling.is_null()||(sebling.left.col == 1 && sebling.right.col == 1))){//3
            sebling.col = 0;
            HelpDelete(node.parent);
        }else if(node.isLeft() && node.parent.col == 1 && sebling.col == 1 &&(sebling.is_null()|| (sebling.left.col == 0 && sebling.right.col == 1))){//5
            sebling.left.col = 1;
            sebling.col = 0;
            rightRotate(sebling.left,false);

        }else if(!node.isLeft() && node.parent.col == 1 && sebling.col == 1 &&(sebling.is_null()|| (sebling.right.col == 0 && sebling.left.col == 1))){//5
            sebling.right.col = 1;
            sebling.col = 0;
            rightRotate(sebling.right,false);

        }
        while(node.parent !=null && !node.parent.is_null()){
            node = node.parent;
        }
        return node;
    }
    Node delete(Node root, Object val){
        Node node = search(root, val);
        if(node == null) return root ;
        else if((node.left.is_null()||node.left == null)&&(!node.right.is_null()&&node.right != null)) {
            if (node.right.col == 0) {
                node.right.col = 1;
                if (node.parent.left == node) {
                    node.parent.left = node.left;
                    node.left.parent = node.parent;
                } else if (node.parent.right == node) {

                    node.parent.right = node.right;
                    node.right.parent = node.parent;
                }
                node = new Node();
            }
        }
        else if((node.right.is_null()||node.right == null)&&(!node.left.is_null()&&node.left != null)) {
            if (node.left.col == 0) {
                node.left.col = 1;
                if (node.parent.left == node) {
                    node.parent.left = node.left;
                    node.left.parent = node.parent;
                } else if (node.parent.right == node) {

                    node.parent.right = node.left;
                    node.right.parent = node.parent;
                }
                node = new Node();
            }
        }
        else {

            Node node2 =  getmino(node.right);
            if(node2 == null || node2.is_null()){
                node2 = node;
            }
            node.val = node2.val;
            if(node2.col == 0) {
                if(node2.parent.left == node2) node2.parent.left = new Node();
               else if (node2.parent.right == node2) node2.parent.right = new Node();
               node2 = new Node();
               // node2.val =0;
               // node2.height=0;
               // node2.col=1;
            }
            else{
               Node n  =  HelpDelete(node2);
               root = n;
                if(node2.parent.left == node2) node2.parent.left = new Node();
                else if (node2.parent.right == node2) node2.parent.right = new Node();
                node2 = new Node();
            }
        }
        size--;
        return  root;
    }
    int getHeight(Node root){
        int hL=0;
        int hR=0;
        if(root.right!=null&&root.right.is_null()==false){
            hR=getHeight(root.right);
        }
        if(root.left!=null&&root.left.is_null()==false){
            hL=getHeight(root.left);
        }
        return 1+Math.max(hL,hR);
    }
    public String printInorder(Node NODERB)
    {   if(NODERB!=null){
        if (NODERB.val!=null) {


            /* first recur on left child */
            printInorder(NODERB.left);

            /* then print the data of NODERB */
            //   System.out.print(NODERB.val + " ");
            s = s+NODERB.val.toString()+" ";

            /* now recur on right child */
            printInorder(NODERB.right);
        }}
        return s;
    }
    Node Patchins(Node node, String dir)
    {
        try {
            File myObj = new File(dir);
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                node =  insert(node, data);
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        return node;
    }
    Node Patchdel(Node node2,String dir){
        try {
            File myObj = new File(dir);
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                node2 = delete(node2, data);

            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        return node2;
    }
    void printTreeHelper(Node root, int space )
    {
        int i;
        if(root != null|| root.is_null())
        {
            space = space + 10;
            printTreeHelper(root.right, space);
            System.out.printf("\n");
            for ( i = 10; i < space; i++)
            {
                System.out.printf(" ");
            }
            System.out.printf("%d (%d)", root.val,root.col);
            System.out.printf("\n");
            printTreeHelper(root.left, space);
        }
    }

    // Wrappers over above recursive functions

}
