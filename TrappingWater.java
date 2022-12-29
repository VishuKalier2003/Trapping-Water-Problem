/* Given n non-negative integers representing an elevation map where the width of each bar is 1, compute how much water it can trap after raining...
 * Eg 1: Height = [0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1]    Output = 6
 * Eg 2: Height = [4, 2, 0, 3, 2, 5]                      Output = 9
 */
import java.util.Scanner;
import java.util.Vector;
import java.util.Queue;
import java.util.LinkedList;
public class TrappingWater
{
    public int WaterCalculation(Vector<Integer> vec, Queue<Integer> q)
    {
        int iterate = 0;
        int x = vec.get(0);
        while(iterate != vec.size()-1)       // Iterating over the rooftop...
        {
            if(x <= vec.get(iterate))
                x = vec.get(iterate);       // If next is higher change the value of x...
            else
                q.add(x-vec.get(iterate));     // Adding the absolute difference to the Queue...
            iterate++;
        }
        int index = vec.indexOf(x);      // Calculating the last highest rooftop...
        if(x > vec.get(vec.size()-1))     // If the rooftop in the end is not closed...
        {
            iterate = index;
            x = vec.get(index);
            while(iterate != vec.size()-1)        // Removing the last tripping water...
            {
                if(x <= vec.get(iterate))
                    x = vec.get(iterate);
                else
                    q.add(-(x-vec.get(iterate)));     // Subtracting the summation values...
            iterate++;
            }
        }
        vec.remove(index);
        vec.remove(vec.size()-1);
        iterate = index;                // Getting the index after removing the x...
        x = vec.get(index);
        while(iterate != vec.size()-1)    // Again calculating the rooftop assuming the rooftop is open...
        {
            if(x <= vec.get(iterate))
                x = vec.get(iterate);
            else
                q.add(x-vec.get(iterate));
            iterate++;
        }
        int sum = 0;
        while(!q.isEmpty())      // We sum the differences to get the total water that can be tripped...
        {
            System.out.print(q.peek()+", ");
            sum =  sum + q.peek();
            q.remove();
        }
        System.out.println();
        return sum;              // returning the sum...
    }
    public static void main(String[] args)
    {
        Scanner sc = new Scanner(System.in);
        Queue<Integer> queue = new LinkedList<Integer>();     // Queue Data Structure...
        int x, a;
        System.out.print("Enter the size of the rooftop : ");
        x = sc.nextInt();
        Vector<Integer> vector = new Vector<Integer>(x);       // vector object defined...
        for(int i = 0; i < x; i++)
        {
            System.out.print("Enter the height of "+(i+1)+" block : ");
            a = sc.nextInt();
            vector.add(i, a);     // Adding vector at the respective index...
        }
        System.out.println("The Rooftop vector formed is : "+vector);
        TrappingWater trapping = new TrappingWater();       // object creation...
        System.out.println("The water retaining capacity is : "+trapping.WaterCalculation(vector, queue));
        sc.close();
    }
}