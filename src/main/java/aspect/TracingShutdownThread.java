package aspect;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * Created by david on 2017/07/31.
 */
public class TracingShutdownThread extends Thread {
    private PlantUmlWriter umlWriter = new PlantUmlWriter();

    public TracingShutdownThread() {
        Runtime.getRuntime().addShutdownHook(this);
    }

    public void run() {
        EventStore eventStore = EventStore.getInstance();
        List<String> menuLinks = writeUmlSequences(eventStore);
        writeSiteFile(menuLinks);
    }

    private void writeSiteFile(List<String> menuLinks) {
        String fileName = "../../../../../src/site/site.xml";
        System.out.println("Writing '" + fileName + "'");
        try {
            PrintWriter writer = new PrintWriter(new FileWriter(fileName));
            BufferedReader reader = new BufferedReader(new FileReader("../../../../../src/site/site.template.xml"));
            while (true) {
                String line = reader.readLine();
                if (line == null) {
                    break;
                }
                if (line.contains("tests-uml-placeholder")) {
                    writer.println("        <menu name='Tests'>");
                    for (String menuLink : menuLinks) {
                        writer.println("            " + menuLink);
                    }
                    writer.println("        </menu>");
                } else {
                    writer.println(line);
                }
            }
            writer.close();
        } catch (Throwable t) {
            t.printStackTrace();
        }
    }

    private List<String> writeUmlSequences(EventStore eventStore) {
        List<String> menuLinks = new ArrayList<>();
        Set<String> contexts = eventStore.getContexts();
        for (String context : contexts) {
            try {
                List<JoinPointEvent> events = eventStore.getEvents(context);
                if (events.size() > 0) {
                    writeUmlSequence(context, events);
                    writeAptFile(context);
                    menuLinks.add("<item name='" + context + "' href='test-" + context + ".html'/>");
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return menuLinks;
    }

    private void writeAptFile(String context) throws IOException {
        String fileName = "../../../../../src/site/apt/test-" + context + ".apt";
        System.out.println("Writing '" + fileName + "'");
        PrintWriter writer = new PrintWriter(new FileWriter(fileName));
        writer.println(context + "\n\n[images/" + context + ".png]");
        writer.close();
    }

    private void writeUmlSequence(String context, List<JoinPointEvent> events) throws IOException {
        String fileName = "../../../../../src/site/plantuml/" + context + ".txt";
        System.out.println("Writing '" + fileName + "'");
        PrintWriter writer = new PrintWriter(new FileWriter(fileName));
        writer.println("@startuml");
        for (JoinPointEvent event : events) {
            writer.println( event.getEventType().ordinal() == EventType.CALL.ordinal() ?
                    umlWriter.writeCallSequence(event.getInfo()) :
                    umlWriter.writeReturnSequence(event.getInfo(), event.getDuration()) );
        }
        writer.println("@enduml");
        writer.close();
    }
}
