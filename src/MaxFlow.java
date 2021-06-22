import java.io.File;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.Scanner;

/**
 *
 * @author keshan_2019369_w1761302
 */

public class MaxFlow {

    static int V; // Numb of vertices
    private static int datax [][];


    public static void main (String[] args)

            throws java.lang.Exception {

        Stopwatch stopwatch = new Stopwatch();
        getNum();
        //  create graph
        int graph[][] = datax;

        MaxFlow m = new MaxFlow();

        System.out.println("The maximum possible flow is "
                + m.fordFulkerson(graph, 0, V - 1));
        System.out.println("code Executed Time =" + stopwatch.elapsedTime());
    }



    boolean bfs(int rGraph[][], int s, int t, int parent[]) {
        // Create a visited array and mark all vertices as not visited
        boolean visited[] = new boolean[V];
        for (int i = 0; i < V; ++i)
            visited[i] = false;


        LinkedList<Integer> queue = new LinkedList<Integer>();
        queue.add(s);
        visited[s] = true;
        parent[s] = -1;

        //  BFS Loop
        while (queue.size() != 0) {
            int u = queue.poll();

            for (int v = 0; v < V; v++) {
                if (visited[v] == false
                        && rGraph[u][v] > 0) {

                    if (v == t) {
                        parent[v] = u;
                        return true;
                    }
                    queue.add(v);
                    parent[v] = u;
                    visited[v] = true;
                }
            }
        }


        // return false
        return false;
    }

    // Returns tne maximum flow
    // graph
    int fordFulkerson(int graph[][], int s, int t) {
        int u, v;

        int rGraph[][] = new int[V][V];

        for (u = 0; u < V; u++)
            for (v = 0; v < V; v++)
                rGraph[u][v] = graph[u][v];

        // This array is filled by BFS and to store path
        int parent[] = new int[V];

        int max_flow = 0; // There was no flow


        while (bfs(rGraph, s, t, parent)) {

            // find the maximum flow
            int path_flow = Integer.MAX_VALUE;
            for (v = t; v != s; v = parent[v]) {
                u = parent[v];
                path_flow
                        = Math.min(path_flow, rGraph[u][v]);
            }

            for (v = t; v != s; v = parent[v]) {
                u = parent[v];
                rGraph[u][v] -= path_flow;
                rGraph[v][u] += path_flow;
            }

            // Add  flow
            max_flow += path_flow;
        }

        // Return flow
        return max_flow;


    }

    public static void getNum() throws FileNotFoundException {
        File filee = new File("./k/ladder_2.txt");
        Scanner scan = new Scanner(filee);
        String[] capacityArray = scan.nextLine().split(" ");
        V = Integer.parseInt(capacityArray[0]);
        datax = new int[V][V];

        while (scan.hasNextLine()) {
            String[] numbersArray = scan.nextLine().split(" ");
            int u = Integer.parseInt(numbersArray[0]);
            int v = Integer.parseInt(numbersArray[1]);
            int capacity = Integer.parseInt(numbersArray[2]);
            datax[u][v] = capacity;
        }
        scan.close();
        for (int i = 0; i < V; i++) {
            for (int j = 0; j < V; j++) {
                System.out.print(datax[i][j] + "");
            }
            System.out.println();
        }

        System.out.println();


    }
}


