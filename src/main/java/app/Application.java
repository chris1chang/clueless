package app;

import app.index.IndexController;
import app.util.Path;
import app.util.ViewUtil;
import spark.Spark;

import java.util.Map;

/**
 * Hello world!
 *
 */
public class Application
{

    private static int views = 0;

    public static void main( String[] args )
    {
        // Initialize Spark
        Spark.port(getHerokuAssignedPort());
        Spark.staticFileLocation(Path.STATIC);
        ViewUtil.initializeFreeMarker();


        // Initialize routes
        Spark.get(Path.Web.INDEX, IndexController.serveIndexPage);




        Spark.get("/count", (req, res) -> {
            views++;
            return "<h1>Hello SparkJava World!</h1><p>Views: " + String.valueOf(views) + "</p>";
        });
    }

    // Found on https://sparktutorials.github.io/2015/08/24/spark-heroku.html
    static int getHerokuAssignedPort() {
        Map<String, String> env = System.getenv();
        if (env.containsKey("PORT")) {
            String port = env.get("PORT");
            if (port != null && !port.equals("")) {
                return Integer.parseInt(port);
            }
        }

        // DELETE: old way
        //ProcessBuilder processBuilder = new ProcessBuilder();
        //if (processBuilder.environment().get("PORT") != null) {
        //    return Integer.parseInt(processBuilder.environment().get("PORT"));
        //}
        return 4567; //return default port if heroku-port isn't set (i.e. on localhost)
    }
}
