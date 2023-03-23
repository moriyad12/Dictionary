public class NODERB {
    NODERB left,right,parent;
    int col=1;// 0=>red,1=>black
    Object val;
    int freq, height;

    NODERB() {
        freq = 0;
        height = 0;
        col=1;
        left = null;
        right = null;
        parent=null;
    }
    NODERB(NODERB par){
        freq = 0;
        height = 0;
        col=1;//----1
        left = null;
        right = null;
        parent=par;
    }
    NODERB(Object v,int col,NODERB parent) {
        val = v;
        freq = height = 1;
        this.col=col;
        left = new NODERB(this);
        right = new NODERB(this);
        this.parent=parent;
    }
    NODERB getSibling(){
        if(parent!=null){
            if(isLeft())return parent.right;
            else return parent.left;
        }
        return null;
    }
    boolean isLeft(){
        if(parent!=null){
            return parent.left==this;
        }
        return false;
    }

    boolean is_null() {
        return (freq == 0 && height == 0&&col==1);
    }
    void updateHeight() {
        height = 1 + Math.max(left.height, right.height);
    }
    int comp(Object val) {
        if (val instanceof String) {
            return ((String) val).compareTo((String) this.val);
        } else if (val instanceof Integer) {
            if ((int) val > (int) this.val) {
                return 1;
            } else if ((int) val == (int) this.val) {
                return 0;
            } else {
                return -1;
            }
        } else {
            return 0;
        }
    }
}
