public class Solution {

    class ListNode {
        private ListNode next;
        private int val;

        public ListNode(ListNode next, int val){
            this.next = next;
            this.val = val;
        }
    }

    public ListNode removeElement(ListNode head, int val){

        if(head==null){
            return null;
        }

        head.next = removeElement(head.next, val);
        return head.val == val? head.next : head;
    }
}
