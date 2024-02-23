import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

class Graphs{
    public static void main(String[] args) {
        System.out.println("hello");

        ArrayList<Edge>[] graph = new ArrayList[4];

        createGraph(graph);

        
        int v = 4;
        boolean vis[] = new boolean[v];
        // for(int i = 0;i<v;i++){
        //     if(!vis[i]) bfs(graph,vis,i);
        // }

        // bfs(graph,vis,v);
        dfs(graph, 0, vis);
    }

    public static void createGraph(ArrayList<Edge>[] graph){
        for(int i = 0;i<graph.length;i++){
            graph[i] = new ArrayList<Edge>();
        }
        graph[0].add(new Edge(0,2,2));

        graph[1].add(new Edge(1,2,10));
        graph[1].add(new Edge(1,3,0));

        graph[2].add(new Edge(2,0,2));
        graph[2].add(new Edge(2,1,10));
        graph[2].add(new Edge(2,3,-1));

        graph[3].add(new Edge(3,1,0));
        graph[3].add(new Edge(3,2,-1));

    }

// Breadth first search
    static void bfs(ArrayList<Edge>[] graph,boolean[] vis,int v){
        Queue<Integer> queue = new LinkedList<>();
        
        queue.add(0);
        while(!queue.isEmpty()){
            int curr = queue.remove();
            if(vis[curr]==true) continue;
            System.out.println(curr);
            vis[curr]=true;


            for(int i=0;i<graph[curr].size();i++){
                Edge e = graph[curr].get(i);
                queue.add(e.dest);
            }
        }
        

    }

// Depth First Search
    static void dfs(ArrayList<Edge> graph[], int curr,boolean vis[]){
        System.out.println(curr);
        vis[curr] = true;

        for(int i = 0;i<graph[curr].size();i++){
            Edge e = graph[curr].get(i);
            if(!vis[e.dest]){
                dfs(graph, e.dest, vis);
            }
        }
    }

    static class Edge{
        int src;
        int dest;
        int weight;
        Edge(int src, int dest, int weight){
            this.src = src;
            this.dest = dest;
            this.weight = weight;
        }
    }
}

