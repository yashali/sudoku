public class BillPugh {
    private BillPugh() {

    }

    public static BillPugh getInstance() {
        return InstanceBuilder.INSTANCE;
    }

    public static void main(String args[]) {
        BillPugh i1 = BillPugh.getInstance();
        BillPugh i2 = BillPugh.getInstance();

        System.out.println(i1);
        System.out.println(i2);

    }

    private static class InstanceBuilder {
        public static final BillPugh INSTANCE = new BillPugh();
        // here static nested class is a must
    }
}