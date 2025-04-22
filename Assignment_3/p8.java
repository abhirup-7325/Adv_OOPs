package Assignment_3;

class NoticeBoard {
    private String notice = "Initial Notice";
    private final Object lock = new Object();
    private int readerCount = 0;

    // Reader method
    public void readNotice(String readerName) {
        synchronized (lock) {
            readerCount++;
        }

        System.out.println(readerName + " is reading: \"" + notice + "\"");

        try {
            Thread.sleep(1000);  // Simulate reading time
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        synchronized (lock) {
            readerCount--;
            if (readerCount == 0) {
                lock.notifyAll();  // Notify writers if no readers left
            }
        }
    }

    // Writer method
    public void writeNotice(String writerName, String newNotice) {
        synchronized (lock) {
            while (readerCount > 0) {  // Wait if readers are present
                try {
                    System.out.println(writerName + " is waiting for readers to finish...");
                    lock.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            System.out.println(writerName + " is writing a new notice...");
            notice = newNotice;

            try {
                Thread.sleep(2000);  // Simulate writing time
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.println(writerName + " updated the notice!");
        }
    }
}

class ReaderThread extends Thread {
    private final NoticeBoard board;
    private final String name;

    ReaderThread(NoticeBoard board, String name) {
        this.board = board;
        this.name = name;
    }

    public void run() {
        board.readNotice(name);
    }
}

class WriterThread extends Thread {
    private final NoticeBoard board;
    private final String notice;
    private final String name;

    WriterThread(NoticeBoard board, String name, String notice) {
        this.board = board;
        this.name = name;
        this.notice = notice;
    }

    public void run() {
        board.writeNotice(name, notice);
    }
}

public class p8 {
    public static void main(String[] args) {
        NoticeBoard board = new NoticeBoard();

        Thread reader1 = new ReaderThread(board, "Reader-1");
        Thread reader2 = new ReaderThread(board, "Reader-2");
        Thread reader3 = new ReaderThread(board, "Reader-3");

        Thread writer1 = new WriterThread(board, "Writer-1", "Meeting at 3 PM");
        Thread writer2 = new WriterThread(board, "Writer-2", "Holiday Declared!");

        reader1.start();
        reader2.start();
        writer1.start();  // Writer will wait for readers
        reader3.start();
        writer2.start();

        try {
            reader1.join(); reader2.join(); reader3.join();
            writer1.join(); writer2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("All reading and writing operations completed.");
    }
}
