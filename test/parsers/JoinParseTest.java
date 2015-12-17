package parsers;

import org.junit.Test;
import parsers.JoinParser;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class JoinParseTest {
    @Test
    public void returnsTheId() {
        String id = new JoinParser().parse(
                "{:response \"ok\", :stat {:type :nest, :location [0 0], :food 5, :ants 0, :team \"mateu\", :id \"36935336\"}}");
        assertThat(id, is("36935336"));
    }
}
