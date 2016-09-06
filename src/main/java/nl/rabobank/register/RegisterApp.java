package nl.rabobank.register;

import nl.rabobank.register.server.WebServer;
import nl.rabobank.register.server.WebServerConfig;

public class RegisterApp
{
    public static void main(String... anArgs) throws Exception
    {
        new RegisterApp().start();
    }

    private WebServer server;

    public RegisterApp()
    {
        server = new WebServer(
                WebServerConfig.Factory.newDevelopmentConfig("happy", 8000, "localhost"));
    }

    public void start() throws Exception
    {
        server.start();
        server.join();
    }
}
