package test.merchant.galaxy;

import org.junit.Test;

public class InputTest {

    @Test
    public void readLineSpitTest(){
        String regex = " ";
        String line1 = "glob is I";
        String arr1[] = line1.split(regex);
        printlnArr(arr1);

        String line2="glob glob Silver is 34 Credits";
        String arr2[] = line2.split(regex);
        printlnArr(arr2);

        String line3="how much is pish tegj glob glob ?";
        String arr3[] = line3.split(regex);
        printlnArr(arr3);

        String line4="how much wood could a woodchuck chuck if a woodchuck could chuck= wood ?";
        String arr4[] = line4.split(regex);
        printlnArr(arr4);
    }

    private void printlnArr(String[] arr){
        for(String ele : arr){
            System.out.print(ele+":");
        }
        System.out.println();
    }
}
