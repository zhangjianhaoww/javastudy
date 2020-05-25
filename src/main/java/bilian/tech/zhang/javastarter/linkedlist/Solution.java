package bilian.tech.zhang.javastarter.linkedlist;

public class Solution {

    private static class ListNode{
        int val;
        ListNode next;

        ListNode(int x){
            val = x;
        }

        ListNode(int[] arr){
            if (arr == null || arr.length == 0){
                throw new IllegalArgumentException("arr can not be empty");
            }

            this.val = arr[0];
            ListNode cur = this;
            for (int i = 1; i < arr.length; i++){
                cur.next = new ListNode(arr[i]);
                cur = cur.next;
            }
        }

        @Override
        public String toString() {
            StringBuilder res = new StringBuilder();
            ListNode cur = this;
            while (cur != null)
            {
                res.append(cur.val + " -> ");
                cur = cur.next;
            }

            res.append("null");
            
            return res.toString();
        }
    }

    /**
     * 使用循环删除，在没有虚拟头节点的情况下，需要考虑两种情况：
     *  1 删除头节点
     *      头节点没有上一节点
     *  2 删除中间节点
     *      需要知道头节点的上一个节点，进行删除
     *
     * @param head
     * @param val
     * @return
     */
    public static ListNode removeElements0(ListNode head, int val) {

        while (head != null && head.val == val){
            ListNode delNode = head;
            head = head.next;
            delNode.next = null;
        }

        if (head == null){
            return null;
        }

        ListNode prev = head;
        while (prev.next != null){

            if(prev.next.val == val){
                ListNode delNode = prev.next;
                prev.next = delNode.next;
                delNode.next = null;
            }else{
                prev = prev.next;
            }
        }

        return head;
    }

    /**
     * 通过添加虚拟头节点来统一删除链表的头节点和中间节点
     *
     * @param head
     * @param val
     * @return
     */
    public static ListNode removeElements1(ListNode head, int val) {

        ListNode dummyHead = new ListNode(-1);
        dummyHead.next = head;

        ListNode prev = dummyHead;
        while (prev.next != null){

            if(prev.next.val == val){
                ListNode delNode = prev.next;
                prev.next = delNode.next;
                delNode.next = null;
            }else{
                prev = prev.next;
            }
        }

        return dummyHead.next;
    }


    /**
     * 递归方式
     *
     * @param head
     * @param val
     * @return
     */
    public static ListNode removeElements3(ListNode head, int val) {

       if (head == null){
           return null;
       }

       ListNode res = removeElements3(head.next, val);

       if (head.val == val){
           return res;
       }
       head.next = res;
       return head;
    }



    public ListNode removeElements2(ListNode head, int val){

        if (head == null){
            return null;
        }
        // 6
        if (head.next == null){
            if (head.val == val){
                head = null;
            }
            return head;

        }

        if (head.val == val){
            return removeElements2(head.next, val);
        }
        head.next = removeElements2(head.next, val);
        return head;

    }



    public static void main(String[] args) {
        int[] nums = {5,5,1, 2, 3, 4, 5, 5, 2, 1, 5};
        ListNode node = new ListNode(nums);
        System.out.println(node);   // 5 -> 5 -> 1 -> 2 -> 3 -> 4 -> 5 -> 5 -> 2 -> 1 -> 5 -> null

        System.out.println(removeElements3(node, 5));  // 1 -> 2 -> 3 -> 4 -> 2 -> 1 -> null

        //引用传递，原链表结构会更改
        System.out.println(node);  // 5 -> null

    }
}
