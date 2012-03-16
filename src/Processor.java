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
        
        if(!right.isEmpty()){
            if(Character.isUpperCase(right.charAt(0))){
                throw new RuntimeException("Wrong expression");
            }            
            if(right.length() > 2){
                throw new RuntimeException("Wrong expression");
            }
        }

    }

    public boolean contain(String word) {

        LinkedSortedList evalSet = new LinkedSortedList();

        evalSet.add("S");

        while (!evalSet.isEmpty()) {

            String f = evalSet.pop();

            String subNTerm = getNTerm(f);


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


    @Override
    public String toString() {

        StringBuilder builder = new StringBuilder();

        builder.append("digraph NFA {\n");
        builder.append("    node [shape = doublecircle]; S ⏚;\n");
        builder.append("    node [shape = circle];\n");
        Set<String> keys = pr.keySet();

        for (String key : keys) {

            List<String> list = pr.get(key);

            for (String l : list) {

                if (l.isEmpty()) {
                    builder.append("    " + key + " -> ⏚ [ label = \"ε\" ];\n");
                    continue;
                }

                String nTerm = getNTerm(l);

                if (nTerm.isEmpty()) {
                    builder.append("    " + key + " -> ⏚ [ label = \"" + l + "\" ];\n");
                    continue;
                }

                builder.append("    " + key + " -> " + nTerm
                        + " [ label = \"" + l.replace(nTerm, "") + "\" ];\n");
                
            }

        }
        
        builder.append("}");

        return builder.toString();

    }


    private String getNTerm(String f) {

        for (int i = 0; i < f.length(); i++) {
            if (Character.isUpperCase(f.charAt(i))) {
                return f.charAt(i) + "";
            }
        }
        return "";

    }
}
