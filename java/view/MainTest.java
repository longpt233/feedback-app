package view;

import controler.LetterController;

import java.util.Scanner;

public class MainTest {
    private LetterController letterController;

    public static void main(String[] args) {
        System.out.println("hello");
        menu();
    }

    public void show(){
//        @FX
        // show thông tin của các trường đã thao tác
    }

    public static void menu(){
        // chọn mục để thao tác, điều hướng sẽ đi vào controller naof
        int chosen = new Scanner(System.in).nextInt();
        switch (chosen){
            case 1:
                System.out.println();
                // show ra mà hình thao tác với các đơn kiến nghị
                break;
            case 2:
                System.out.println();
                // show ra màn hình thao tác với các lịch sử đơn

            default: // show lại menu
        }
    }
}
