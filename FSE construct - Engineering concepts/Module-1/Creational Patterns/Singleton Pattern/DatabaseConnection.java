public class DatabaseConnection 
{
    private static DatabaseConnection instance;
    private DatabaseConnection()
    {
        System.out.println("Database Connected");
    }
    public static DatabaseConnection getInstance()
    {
        if(instance == null)
        {
            instance = new DatabaseConnection();
        }
        return instance;
    }
}
