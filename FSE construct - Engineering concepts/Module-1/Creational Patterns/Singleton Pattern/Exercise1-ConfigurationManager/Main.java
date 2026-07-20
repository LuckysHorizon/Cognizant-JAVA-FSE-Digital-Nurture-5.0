public class Main {

    public static void main(String[] args) {
        ConfigurationManager config1 = ConfigurationManager.getInstance();
        ConfigurationManager config2 = ConfigurationManager.getInstance();

        System.out.println("Same instance: " + (config1 == config2));

        System.out.println("App Name: " + config1.get("app.name"));
        System.out.println("DB Host: " + config1.get("db.host"));

        config1.set("db.host", "192.168.1.100");
        System.out.println("Updated DB Host (via config2): " + config2.get("db.host"));

        System.out.println("All settings: " + config1.getAllSettings());
    }
}
