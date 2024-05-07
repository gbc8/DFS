import java.io.File;
import java.io.IOException;
import java.util.*;

public class DFS {
    
    public static List<Integer> dist = new ArrayList<>();
    public static Map<String, Integer> StringToInteger = new HashMap<>();
    public static Map<Integer, String> IntegerToString = new HashMap<>();
    
    public static void dfs(int origin, List<List<Integer>> grafo, String path){
        if(grafo.get(origin).isEmpty()){
            System.out.println(path);
        }
        dist.set(origin, 1);
        for(int v : grafo.get(origin)){
            if(dist.get(v) == -1){
                dfs(v, grafo, path + '\t' + IntegerToString.get(v));
            }
        }
    }

    public static void main(String[] args){
        File file = new File("Mapper");
        Scanner sc = null;
        List<List<Integer>> list = new ArrayList<>(200);
        int id = 0;
        
        
        for(int i = 0; i < 200; ++i){
            list.add(new ArrayList<>());
        }
        
        try {
            sc = new Scanner(file);
            
            while (sc.hasNextLine()) {
                String line = sc.nextLine();
                List<String> ms = Arrays.asList(line.split(" "));
                System.out.println(ms.toString());

                for(int i = 0; i < ms.size(); ++i){
                    if(StringToInteger.get(ms.get(i)) == null){
                        StringToInteger.put(ms.get(i), id);
                        IntegerToString.put(id, ms.get(i));
                        id++;
                    }
                }

                for(int i = 1; i < ms.size(); ++i){
                    list.get(StringToInteger.get(ms.get(0))).add(StringToInteger.get(ms.get(i)));
                }
            }

            for(int i = 0; i < 200; i++){
                dist.add(-1);
            }

            int node = 0;

            dfs(node, list, IntegerToString.get(node));
            
        } catch (IOException e) {  
            System.out.println("Error: " + e.getMessage());
        }
    }
}