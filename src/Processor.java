import java.util.HashMap;
import java.util.Map;

public class Processor {
    
    Map<String, String> pr = new HashMap<String, String>();
    
    public Processor(String[] expressions){
        
        for(String s: expressions){
            
            String[] rule = s.replace(" ","").split(":");
            if(rule.length == 2){
                pr.put(rule[0], rule[1]);
            } else {
                pr.put(rule[0], "");
            }
            
        }
        
    }
    
}
