package executor;

public class TestExecutor {
    public static void main(String[] args) {
        User user = new User("Sergey", "bazhukovsi@yandex.ru");
        User user1 = new User("Petr", "petr@yandex.ru");
        User user2 = new User("Ivan", "ivan@yandex.ru");
        EmailNotification executor = new EmailNotification();
        executor.emailTo(user);
        executor.emailTo(user1);
        executor.emailTo(user2);
        executor.close();
    }
}
