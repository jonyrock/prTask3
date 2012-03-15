import java.util.LinkedList;
import java.util.List;

public class LinkedSortedList {

    List<String> list = new LinkedList<String>();

    public void add(String s) {

        if (isEmpty()) {
            list.add(s);
            return;
        }

        int len1 = s.length();
        int lenList = list.size();

        int pos = 0;
        for (String ls : list) {
            if (len1 < ls.length()) {
                list.add(pos, s);
                return;
            }
        }

        list.add(lenList, s);

    }

    public String pop() {

        String s = list.get(0);
        if (list.size() > 0) {
            list.remove(0);
        }

        return s;

    }

    public boolean isEmpty() {
        return list.isEmpty();
    }

}
