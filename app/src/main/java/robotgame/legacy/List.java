package robotgame.legacy;

public class List<T> {

    private ListNode<T> actual;
    private ListNode<T> first;
    private ListNode<T> last;

    public T getNext()
    {
        if(actual != null ) {
            ListNode<T> temp = actual;
            actual = actual.getNext();
            return temp.getObject();
        }
        else
            return null;
    }

    public boolean setToFirst()
    {
        actual = first;
        if (actual != null)
            return true;
        else
            return false;
    }

    public void add(T object) {
        add(object,false);
    }

    public void add(T object, boolean end)
    {
        if (end)
        {
            if (first == null)
            {
                ListNode<T> temp = new ListNode<T>(object, last);
                first = temp;
                last = temp;
                actual = first;
            }
            else
            {
                ListNode<T> temp = new ListNode<T>(object, null);
                last.setNext(temp);
                last = temp;
            }
        }
        else
        {
            ListNode<T> temp = new ListNode<T>(object, first);
            first = temp;
            if (last == null)
            {
                last = temp;
                actual = first;
            }
        }
    }

    public boolean remove(final Object object)
    {
        boolean objectFound = false;

        ListNode<T> temp = this.first;
        ListNode<T> prev = null;
        ListNode<T> next = null;

        while( temp != null ) {
            if (object == temp.getObject()) {
                next = temp.getNext();
                if(next == null)
                    last = null;
                if(prev == null)
                    first = next;
                else
                    prev.setNext(next);
                objectFound = true;
                break;
            }
            prev = temp;
            temp = temp.getNext();
        }
        return objectFound;
    }

    public boolean isEmpty()
    {
        if (first != null)
            return false;
        return true;
    }


    public T getObject()
    {
        return actual.getObject();
    }

    public T next()
    {
        if ( actual.getNext() != null )
        {
            actual = actual.getNext();
            return actual.getObject();
        }
        else
            return null;
    }

    public boolean removeFirst()
    {
        ListNode<T> temp;
        if (first == null)
            return false;
        temp = first.getNext();
        first = temp;
        return true;
    }
}