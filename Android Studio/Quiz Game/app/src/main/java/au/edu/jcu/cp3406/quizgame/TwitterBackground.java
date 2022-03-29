package au.edu.jcu.cp3406.quizgame;

public class TwitterBackground {

    static void run(Runnable runnable) {
        Thread thread = new Thread(runnable);
        thread.start();
    }
}

