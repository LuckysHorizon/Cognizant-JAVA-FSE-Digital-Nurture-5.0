import java.util.List;
public class ProxyInternet implements Internet {
    private Internet internet = new RealInternet();

    private List<String> blockedSites = 
            List.of(
                "facebook.com",
                "instagram.com",
                "reddit.com"
            );

    @Override
    public void connectTo(String website)
    {
        if(blockedSites.contains(website.toLowerCase()))
        {
            System.out.println("Access Denied !");
        }
        else
        {
            internet.connectTo(website);
        }
    }
}
