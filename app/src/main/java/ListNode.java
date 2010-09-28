
public class ListNode<T> {
    private T object;
    private ListNode<T> next;

    public ListNode(T object, ListNode<T> next)
    {
        this.object = object;
        this.next = next;
    }

    public T getObject()
    {
        return object;
    }

    public ListNode<T> getNext()
    {
        return next;
    }


    public void setNext(ListNode<T> next)
    {
        this.next = next;
    }
}
