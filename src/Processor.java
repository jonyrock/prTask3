import java.util.*;

public class Processor {

    public Map<String, List<String>> pr = new HashMap<String, List<String>>();


    public Processor(String[] expressions) {

        for (String s : expressions) {

            String[] rule = s.replace(" ", "").split(":");
            addToMap(rule);

        }

    }

    public void addToMap(String[] rule) {

        String left = rule[0];
        String right = rule.length > 1 ? rule[1] : "";

        List<String> mapList = pr.get(left);

        if (mapList != null) {
            mapList.add(right);
        } else {
            List<String> list = new ArrayList<String>();
            list.add(right);
            pr.put(left, list);
        }

    }

    public boolean contain(String word) {

        LinkedSortedList  evalSet = new LinkedSortedList();
                
        evalSet.add("S");

        while (!evalSet.isEmpty()) {

            String f = evalSet.pop();            

            String subNTerm = "";

            for (int i = 0; i < f.length(); i++) {
                if (Character.isUpperCase(f.charAt(i))) {
                    subNTerm = f.charAt(i) + "";
                    break;
                }
            }

            if (!subNTerm.isEmpty()) {

                List<String> list = pr.get(subNTerm);
                for (String s : list) {
                    String newT = f.replace(subNTerm, s);
                    if (newT.isEmpty()) {
                        if (word.isEmpty()) {
                            return true;
                        }
                    } else {
                        evalSet.add(newT);
                    }
                }

            } else {

                if (f.equals(word)) {
                    return true;
                }

                if (f.length() > word.length()) {
                    return false;
                }

            }


        }

        return false;

    }


    private class LengthComparator implements Comparator<String> {

        public int compare(String s, String s1) {
            
            if(s.length() == s1.length()){
                return 0;
            }
            return s.length() > s1.length() ? 1 : -1;

        }


    }


}
