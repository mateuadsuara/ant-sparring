package parsers;

import us.bpsm.edn.parser.Parseable;
import us.bpsm.edn.parser.Parser;
import us.bpsm.edn.parser.Parsers;

import java.util.Map;

import static us.bpsm.edn.Keyword.newKeyword;
import static us.bpsm.edn.parser.Parsers.defaultConfiguration;

public class JoinParser {
    public String parse(String response) {
        Parseable parseable = Parsers.newParseable(response);
        Parser parser = Parsers.newParser(defaultConfiguration());
        Map<?, ?> map = (Map<?, ?>) parser.nextValue(parseable);
        Map<?, ?> stat = (Map<?, ?>) map.get(newKeyword("stat"));
        return (String) stat.get(newKeyword("id"));
    }
}
