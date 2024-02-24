import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

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
        boolean[] recursion = new boolean[v];
        System.out.println(detectCycleDirected(graph, vis, recursion, 0));
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

    // Cycle detection Directed graph
    static boolean detectCycleDirected(ArrayList<Edge>[] graph,boolean[] vis, boolean[] recursion,int curr){

        vis[curr]=true;
        recursion[curr]=true;
        for(int i = 0;i<graph[0].size();i++){
            Edge e = graph[0].get(i);
            if(recursion[e.dest]) return true;
            if(!vis[e.dest]){
                if(detectCycleDirected(graph, vis, recursion, e.dest)) return true;
            }
        }
        recursion[curr] = false;
        return false;
    }


    //Topological sort : Used only on directed acyclic graph
    static void topologicalSortUtil(ArrayList<Edge>[] graph, boolean[] vis, Stack<Integer> stack,int curr){
        vis[curr]=true;
        for(int i = 0;i<graph[curr].size();i++){
            Edge e = graph[curr].get(i);
            if(!vis[e.dest]){
                topologicalSortUtil(graph, vis, stack, e.dest);
            }
        }   
        stack.push(curr);
    }
    static void topologicalSort(ArrayList<Edge>[] graph){
        boolean vis[] = new boolean[graph.length];
        Stack<Integer> stack = new Stack<>();
        for(int i = 0;i<graph.length;i++){
            if(vis[i]==false){
                topologicalSortUtil(graph, vis, stack, i);
            }
        }

        while(!stack.isEmpty()){
            System.out.println(stack.pop());
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

