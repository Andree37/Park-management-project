package Logger;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

/**
 *
 * @author André Ribeiro & Daniel Afonso
 */
public final class Logger {

    private static Logger instance;
    private static final String LOGGERFILE = "logger " + LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd"));
    private PrintStream printStream;
    private boolean tickets;
    private boolean paths;
    private boolean stats;

    private Logger() {
        connect();
    }

    public boolean connect() {
        if (printStream == null) {
            try {
                printStream = new PrintStream(new FileOutputStream(LOGGERFILE), true);
            } catch (FileNotFoundException ex) {
                printStream = null;
                return false;

            }
            return true;
        }
        return true;
    }

    public static Logger getInstance() {
        if (instance == null) {
            instance = new Logger();
        }
        return instance;
    }

    public void writeToLog(String str) throws LoggerException {
        if (printStream == null) {
            throw new LoggerException("Logger Connection failed");
        }

        if (tickets == false && str.contains("Ticket")) {
            return;
        }
        if (paths == false && str.contains("Path")) {
            return;
        }
        if (stats == false && str.contains("Stat")) {
            return;
        }
        printStream.println(new Date().toString() + "  " + str);

    }

    public void setConfig(boolean tickets, boolean paths, boolean stats) {
        this.tickets = tickets;
        this.paths = paths;
        this.stats = stats;
    }

    @Override
    public String toString() {
        return "Logger{" + "printStream=" + printStream + ", tickets=" + tickets + ", paths=" + paths + ", stats=" + stats + '}';
    }
    
}
