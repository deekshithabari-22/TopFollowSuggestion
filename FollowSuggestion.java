import java.util.Scanner;
import java.util.Map;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;

class Graph{

    static Scanner sc=new Scanner(System.in);

    public void run(){

        Map<String,List<String>> graph=createGraph();
        mutualCount(graph);

    }

    private static void mutualCount(Map<String,List<String>> hm){

        System.out.println("Enter the username to get suggestions for that particular account :");
        String user=sc.next();
        if(!hm.containsKey(user)){
            System.out.println("User doesn't exist");
            return;
        }
        Map<String,Integer> count=new HashMap<>();
        for(int i=0;i<hm.get(user).size();i++){
            String cur=hm.get(user).get(i);
            for(int j=0;j<hm.get(cur).size();j++){
                String connect=hm.get(cur).get(j);
                if(!connect.equals(user) && !hm.get(user).contains(connect)){
                    count.put(connect,count.getOrDefault(connect,0)+1);
                }
            }
        }

        List<String> ans=new ArrayList<>();
        int max=0;

        for(String i : count.keySet()){
            if(count.get(i)>max){
                max=count.get(i);
                ans.clear();
                ans.add(i);
            }
            else if(count.get(i)==max){
                ans.add(i);
            }
        }
        if(ans.isEmpty()){
            System.out.println("No Suggestions");
            return;
        }
        System.out.println("The Top most Suggestion :");
        System.out.println(ans+" : "+max+" mutual followers");
    }

    private static Map<String,List<String>> createGraph(){

        Map<String,List<String>> hm=new HashMap<>();
        System.out.println("Enter number of edges(connections)");
        int n=sc.nextInt();
        System.out.println("Enter the space separated edges(A username must not contain any spaces in between)");
        for(int i=0;i<n;i++){
            String a=sc.next();
            String b=sc.next();
            hm.putIfAbsent(a,new ArrayList<>());
            hm.putIfAbsent(b,new ArrayList<>());
            if(!a.equals(b)) hm.get(a).add(b);
        }
        return hm;
    }
}
public class FollowSuggestion {

    public static void main(String[] args) {
        
        Graph g=new Graph();
        g.run();

    }

}
