package org.example;

import bilian.tech.zhang.javastarter.array.Array;
import bilian.tech.zhang.javastarter.queue.ArrayQueue;
import bilian.tech.zhang.javastarter.queue.LoopQueue;
import bilian.tech.zhang.javastarter.queue.Queue;
import bilian.tech.zhang.javastarter.stack.ArrayStack;
import bilian.tech.zhang.javastarter.stack.LinkedListStack;
import bilian.tech.zhang.javastarter.stack.Stack;

import java.util.Random;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {

//        Array<Integer> array = new Array<>(3);
//
//        array.addLast(23);
//        array.addLast(25);
//        array.addLast(28);
//        array.addLast(29);
//        System.out.println(array.toString());
//
//        array.add(1, 100);
//        System.out.println(array.toString());
//
//        array.addFirst(-1);
//        System.out.println(array.toString());
//
//        array.remove(3);
//        array.removeFirst();
//        array.removeFirst();
//        System.out.println(array.toString());
//
//        array.removeElement(29);
//        System.out.println(array.toString());
//
//
//
//        Array<Student> studentArray = new Array<>();
//        studentArray.addLast(new Student("a", 100));
//        studentArray.addLast(new Student("b", 50));
//        studentArray.addLast(new Student("c", 70));
//        System.out.println(studentArray.toString());




//        ArrayStack<Integer> stack = new ArrayStack<>();
//        for (int i = 0; i < 5; i++){
//            stack.push(i);
//        }
//        System.out.println(stack);
//
//        stack.pop();
//        System.out.println(stack);
//
//        System.out.println(stack.getCapacity());
//
//
//        ArrayQueue<Integer> queue = new ArrayQueue<>();
//        for (int i = 0; i < 5; i ++){
//            queue.enqueue(i);
//            System.out.println(queue);
//        }
//
//        queue.dequeue();
//        System.out.println(queue);
//
//        System.out.println(queue.getCapacity());
//


//        int opCount = 200000;
//        double time1 = testQueue(new ArrayQueue<Integer>(), opCount);
//        double time2 = testQueue(new LoopQueue<Integer>(), opCount);
//        System.out.println("ArrayQueue, time: " + time1);
//        System.out.println("LoopQueue, time: " + time2);



//        int opCount = 2000000;
//        double time3 = testStack(new ArrayStack<Integer>(), opCount);
//        double time4 = testStack(new LinkedListStack<Integer>(), opCount);
//        System.out.println("ArrayStack, time: " + time3);
//        System.out.println("LinkedListStack, time: " + time4);

        int[] nums = {1,2,3,4,5,6,7,8};
        System.out.println(sum(nums));
    }

    private static double testQueue(Queue<Integer> queue, int opCount){
        long startTime = System.nanoTime();

        Random random = new Random();
        for (int i = 0; i < opCount; i++){
            queue.enqueue(random.nextInt(Integer.MAX_VALUE));
        }

        for (int i = 0; i < opCount; i++){
            queue.dequeue();
        }

        long endTime = System.nanoTime();

        return (endTime - startTime) / 1000000000.0;
    }


    private static double testStack(Stack<Integer> stack, int opCount){
        long startTime = System.nanoTime();

        Random random = new Random();
        for (int i = 0; i < opCount; i++){
            stack.push(random.nextInt(Integer.MAX_VALUE));
        }

        for (int i = 0; i < opCount; i++){
            stack.pop();
        }

        long endTime = System.nanoTime();

        return (endTime - startTime) / 1000000000.0;
    }


    private static int sum(int[] arr, int l){

        if (l == arr.length){
            return 0;
        }

        return arr[l] + sum(arr, l + 1);
    }

    private static int sum(int[] arr){
        return sum(arr, 0);
    }
}

class Student{
    private String name;
    private int score;

    public Student(String name, int score){
        this.name = name;
        this.score = score;

    }

    @Override
    public String toString() {
        return String.format("Student(name: %s, score: %d", name, score);

    }


}