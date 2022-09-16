public class MyLinkedList<T> {
    Node head = null;
    Node tail = null;

    MyLinkedList(){

    }
    public Node getHead(){
        return head;
    }
    public Node getTail(){
        return tail;
    }
//    public boolean isInList(T Data){
//        Node n = searchTitle(Data);
//        if(n==null){
//            return false;
//        }
//        else return true;
//    }

//    public Node searchTitle(T Data){
//     Node current = head;
//     if(head!=null) {
//         while (current.getNext() != null) {
//            if(current.data == Data){
//               break;
//            }
//            current = current.getNext();
//         }
//     }
//     if(current.data!=Data){
//         return null;
//     }
//     else return current;
//    }

    public void add(T Data){
        Node n = new Node(Data);
        Node current = head;
        if(head==null){
            head = n;
        }else{
            while (current.getNext() != null) {
                current=current.Next;
            }
            current.setNext(n);
            n.setPrevious(current);
            tail = n;
        }
    }

    public void remove(Node n){
        Node np = n.getPrevious();
        Node nn = n.getNext();
        np.setNext(nn);
        nn.setPrevious(np);
        n.setNext(null);
        n.setPrevious(null);
    }
}
//    public Node searchTitle(String name){
//
//     Node current = gamesList.;
//     if(current!=null) {
//         while (current.getNext() != null) {
//            if(current.data.getName == name){
//               break;
//            }
//            current = current.getNext();
//         }
//     }
//     if(current.data!=Data){
//         return null;
//     }
//     else return current;
//    }