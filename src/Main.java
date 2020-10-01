public class Main {
    public static void main(String[] args) {
        if (args.length <= 0) {
            System.out.println("invalid argument");
            System.out.println("Example: java Main user1");
            return;
        }
        new MessageFrame(args[0]);
    }
}
