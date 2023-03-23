

import java.util.Optional;

public class RBT {
    /////// ZERO RED     ONE BLACK
    int size;
    String s="";
    NODERB ins(NODERB root, Object val) {
        size++;
        return insert(root, val);
    }

    NODERB rightRotate(NODERB root, boolean change) {
        NODERB upperNODERB = root.parent;
        NODERB grandNODERB = root.parent.parent;
        root.parent = grandNODERB;
        NODERB rightOfRoot = root.right;
        if (grandNODERB != null && !grandNODERB.is_null()) {
            if (upperNODERB.isLeft()) {
                grandNODERB.left = root;
            } else grandNODERB.right = root;
        }
        upperNODERB.parent = root;
        upperNODERB.left = new NODERB(upperNODERB);

        if (rightOfRoot != null && !rightOfRoot.is_null()) {
            upperNODERB.left = rightOfRoot;
            rightOfRoot.parent = upperNODERB;
        }
        root.right = upperNODERB;
        if (change) {
            root.col = 1;
            upperNODERB.col = 0;
        }
        return root;
    }

    private NODERB leftRotate(NODERB down, boolean change) {
        NODERB mid = down.parent;
        NODERB up = down.parent.parent;
        down.parent = up;
        if (mid.parent != null && !mid.parent.is_null()) {
            if (mid.isLeft()) {
                up.left = down;
            } else {
                up.right = down;
            }
        }
        NODERB sonOfdown = down.left;
        down.left = mid;
        mid.parent = down;
        mid.right=new NODERB(mid);
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

    NODERB fixTree(NODERB root, boolean bool) {
        if (bool) {
            if (root.col == 0 && root.left.col == 0) {
                NODERB sib = root.getSibling();
                if (sib == null || sib.is_null() || sib.col == 1) {

                    if (root.isLeft()) {

                        rightRotate(root, true);

                    } else {
                        NODERB n = rightRotate(root.left, false);

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
                NODERB sib = root.getSibling();
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

    private NODERB insert(NODERB root, Object ins_val) {
        if (root.is_null()) {
            if (root.parent == null || root.parent.is_null()) {
                return new NODERB(ins_val, 1, new NODERB());
            } else {
                return new NODERB(ins_val, 0, root.parent);
            }
        }
        if (root.val.equals(ins_val)) {
            root.freq++;
            size--;
            return root;
        }
        if (root.comp(ins_val) < 0) {
            NODERB lft = insert(root.left, ins_val);
            if (lft == root.parent) {
                return lft;
            }
            root.left = lft;
            root = fixTree(root, true);
        } else {
            NODERB rig = insert(root.right, ins_val);
            if (rig == root.parent) {
                return rig;
            }
            root.right = rig;
            root = fixTree(root, false);
        }
        return root;
    }
    NODERB search (NODERB root, Object ins_val){
        if (root.is_null()) {
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
    boolean Search(NODERB root, Object ins_val){
        if(this.search( root, ins_val)==null) return false;
        else return true;
    }
    NODERB getmino(NODERB root) {//GET minimum value
        if(root.is_null())return null;
        NODERB s = root.left;
        NODERB last = root;
        while (!(s.is_null())) {
            last = s;
            s = s.left;
        }
        return last;
    }
    NODERB HelpDelete(NODERB NODERB){
        NODERB uncle = NODERB.parent.getSibling();
        NODERB sebling = NODERB.getSibling();
        if(NODERB.parent == null || NODERB.parent.is_null()) return NODERB ;//1
        else if(NODERB.parent.col==0 && sebling.col==1 &&(sebling.is_null()||(sebling.left.col == 1 && sebling.right.col == 1))){//4
            NODERB.parent.col = 1;
            sebling.col = 0;

        }else if(NODERB.isLeft() && sebling.col==1 && (!sebling.is_null()&&sebling.right.col==0)){//6
            int temp = NODERB.parent.col;
            NODERB.parent.col = sebling.col;
            sebling.col = temp;
            sebling.right.col = 1;
            leftRotate(sebling,false);
        }
        else if(!NODERB.isLeft() && sebling.col==1 && (!sebling.is_null()&&sebling.left.col==0)){//6 part2
            int temp = NODERB.parent.col;
            NODERB.parent.col = sebling.col;
            sebling.col = temp;
            sebling.left.col = 1;
            rightRotate(sebling,false);
        }
        else if(NODERB.isLeft() && NODERB.parent.col ==1 && sebling.col == 0 && (sebling.is_null()||(sebling.left.col == 1 && sebling.right.col == 1))){//2
            int temp = NODERB.parent.col;
            NODERB.parent.col = sebling.col;
            sebling.col = temp;
            sebling.left.col = 1;
            leftRotate(sebling,false);
            HelpDelete(NODERB);
        } else if(!NODERB.isLeft() && NODERB.parent.col ==1 && sebling.col == 0 && (sebling.is_null()||(sebling.left.col == 1 && sebling.right.col == 1))){//2 part2
            int temp = NODERB.parent.col;
            NODERB.parent.col = sebling.col;
            sebling.col = temp;
            sebling.right.col = 1;
            rightRotate(sebling,false);
            HelpDelete(NODERB);
        }else if( NODERB.parent.col ==1 && sebling.col == 1 && (sebling.is_null()||(sebling.left.col == 1 && sebling.right.col == 1))){//3
            sebling.col = 0;
            HelpDelete(NODERB.parent);
        }else if(NODERB.isLeft() && NODERB.parent.col == 1 && sebling.col == 1 &&(sebling.is_null()|| (sebling.left.col == 0 && sebling.right.col == 1))){//5
            sebling.left.col = 1;
            sebling.col = 0;
            rightRotate(sebling.left,false);

        }else if(!NODERB.isLeft() && NODERB.parent.col == 1 && sebling.col == 1 &&(sebling.is_null()|| (sebling.right.col == 0 && sebling.left.col == 1))){//5
            sebling.right.col = 1;
            sebling.col = 0;
            rightRotate(sebling.right,false);

        }
        while(NODERB.parent !=null && !NODERB.parent.is_null()){
            NODERB = NODERB.parent;
        }
        return NODERB;
    }
    NODERB delete(NODERB root, Object val){
        NODERB NODERB = search(root, val);
        if(NODERB == null) return root ;
        else if((NODERB.left.is_null()||NODERB.left == null)&&(!NODERB.right.is_null()&&NODERB.right != null)) {
            if (NODERB.right.col == 0) {
                NODERB.right.col = 1;
                if (NODERB.parent.left == NODERB) {
                    NODERB.parent.left = NODERB.left;
                    NODERB.left.parent = NODERB.parent;
                } else if (NODERB.parent.right == NODERB) {

                    NODERB.parent.right = NODERB.right;
                    NODERB.right.parent = NODERB.parent;
                }
                NODERB = new NODERB();
            }
        }
        else if((NODERB.right.is_null()||NODERB.right == null)&&(!NODERB.left.is_null()&&NODERB.left != null)) {
            if (NODERB.left.col == 0) {
                NODERB.left.col = 1;
                if (NODERB.parent.left == NODERB) {
                    NODERB.parent.left = NODERB.left;
                    NODERB.left.parent = NODERB.parent;
                } else if (NODERB.parent.right == NODERB) {

                    NODERB.parent.right = NODERB.left;
                    NODERB.right.parent = NODERB.parent;
                }
                NODERB = new NODERB();
            }
        }
        else {

            NODERB NODERB2 =  getmino(NODERB.right);
            if(NODERB2 == null || NODERB2.is_null()){
                NODERB2 = NODERB;
            }
            NODERB.val = NODERB2.val;
            if(NODERB2.col == 0) {
                if(NODERB2.parent.left == NODERB2) NODERB2.parent.left = new NODERB();
                else if (NODERB2.parent.right == NODERB2) NODERB2.parent.right = new NODERB();
                NODERB2 = new NODERB();
                // NODERB2.val =0;
                // NODERB2.height=0;
                // NODERB2.col=1;
            }
            else{
                NODERB n  =  HelpDelete(NODERB2);
                root = n;
                if(NODERB2.parent.left == NODERB2) NODERB2.parent.left = new NODERB();
                else if (NODERB2.parent.right == NODERB2) NODERB2.parent.right = new NODERB();
                NODERB2 = new NODERB();
            }
        }
        size--;
        return  root;
    }
    int getHeight(NODERB root){
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
    public String printInorder(NODERB NODERBRB)
    {   if(NODERBRB!=null){
        if (NODERBRB.val!=null) {


            /* first recur on left child */
            printInorder(NODERBRB.left);

            /* then print the data of NODERBRB */
            //   System.out.print(NODERBRB.val + " ");
            s = s+NODERBRB.val.toString()+" ";

            /* now recur on right child */
            printInorder(NODERBRB.right);
        }}
        return s;
    }
    void printTreeHelper(NODERB root, int space )
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
