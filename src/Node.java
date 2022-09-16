public class Node <T>{
    T data;
    Node Next;
    Node Previous;

    public Node(){

    }

    public Node(T game){
        data = game;
    }

    public void setNext(Node n){
        Next = n;
    }

    public void setPrevious(Node n){
        Previous = n;
    }

    public void setData(T dat){
        data = dat;
    }

    public Node getNext(){
        return Next;
    }

    public Node getPrevious(){
        return Previous;
    }

    public T getData(){return data;}
}
