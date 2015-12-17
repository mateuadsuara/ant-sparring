package parsers;

import org.junit.Test;

import java.util.Arrays;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class AntParseTest {
    @Test
    public void spawningReturnsTheId() {
        Ant ant = new AntParser().parse(
                "{:response \"ok\", :stat {:type :ant, :location [0 0], :got-food false, :team \"mateu\", :nest \"36935336\", :n 1, :id \"79246941\", :surroundings {\"n\" [], \"w\" [], \"s\" [], \"sw\" [], \"ne\" [], \"e\" [], \"nw\" [], \"se\" []}}}");
        assertThat(ant.id, is("79246941"));
    }

    @Test
    public void spawningReturnsTheLocation() {
        Ant ant = new AntParser().parse(
                "{:response \"ok\", :stat {:type :ant, :location [3 1], :got-food false, :team \"mateu\", :nest \"36935336\", :n 1, :id \"79246941\", :surroundings {\"n\" [], \"w\" [], \"s\" [], \"sw\" [], \"ne\" [], \"e\" [], \"nw\" [], \"se\" []}}}");
        assertThat(ant.location.x, is(1L));
        assertThat(ant.location.y, is(3L));
    }

    @Test
    public void spawningWithFoodAround() {
        Ant ant = new AntParser().parse(
                "{:response \"ok\", :stat {:type :ant, :location [0 0], :got-food false, :team \"mateu\", :nest \"36935336\", :n 1, :id \"79246941\", :surroundings {\"n\" [], \"w\" [{:type :food, :location [-1 0]}], \"s\" [], \"sw\" [{:type :ant}], \"ne\" [], \"e\" [{:type :ant} {:type :food, :location [1 0]}], \"nw\" [], \"se\" []}}}");
        assertThat(ant.detectedFood, is(Arrays.asList(new Location(0, 1), new Location(0, -1))));
    }

    @Test
    public void hasAnId() {
        Ant ant = new AntParser().parse(
                "{:response \"ok\", :stat {:type :ant, :location [2 1], :got-food false, :team \"mateu\", :nest \"70914831\", :n 1, :id \"28464344\", :poin" +
                        "ts 51, :surroundings {\"n\" [], \"w\" [{:type :ant, :location [1 1], :got-food false, :team \"cf\", :nest \"55670351\", :n 4, :id \"7677905" +
                        "9\", :points 12}], \"s\" [], \"sw\" [], \"ne\" [{:type :ant, :location [3 0], :got-food true, :team \"skim-uku\", :nest \"55205710\", :n 4, :" +
                        "id \"24468088\", :points 22} {:type :ant, :location [3 0], :got-food true, :team \"skim-uku\", :nest \"55205710\", :n 3, :id \"86372\", :p" +
                        "oints 40}], \"e\" [{:type :ant, :location [3 1], :got-food false, :team \"cf\", :nest \"55670351\", :n 1, :id \"68273952\", :points 48}], " +
                        "\"nw\" [{:type :ant, :location [1 0], :got-food true, :team \"makis_o\", :nest \"2478913\", :n 1, :id \"37879642\", :points 4}], \"se\" []}}" +
                        "}\n");
        assertThat(ant.id, is("28464344"));
    }

    @Test
    public void hasALocation() {
        Ant ant = new AntParser().parse(
                "{:response \"ok\", :stat {:type :ant, :location [2 1], :got-food false, :team \"mateu\", :nest \"70914831\", :n 1, :id \"28464344\", :poin" +
                        "ts 51, :surroundings {\"n\" [], \"w\" [{:type :ant, :location [1 1], :got-food false, :team \"cf\", :nest \"55670351\", :n 4, :id \"7677905" +
                        "9\", :points 12}], \"s\" [], \"sw\" [], \"ne\" [{:type :ant, :location [3 0], :got-food true, :team \"skim-uku\", :nest \"55205710\", :n 4, :" +
                        "id \"24468088\", :points 22} {:type :ant, :location [3 0], :got-food true, :team \"skim-uku\", :nest \"55205710\", :n 3, :id \"86372\", :p" +
                        "oints 40}], \"e\" [{:type :ant, :location [3 1], :got-food false, :team \"cf\", :nest \"55670351\", :n 1, :id \"68273952\", :points 48}], " +
                        "\"nw\" [{:type :ant, :location [1 0], :got-food true, :team \"makis_o\", :nest \"2478913\", :n 1, :id \"37879642\", :points 4}], \"se\" []}}" +
                        "}\n");
        assertThat(ant.location.x, is(1L));
        assertThat(ant.location.y, is(2L));
    }

    @Test
    public void doesNotHaveFood() {
        Ant ant = new AntParser().parse(
                "{:response \"ok\", :stat {:type :ant, :location [2 1], :got-food false, :team \"mateu\", :nest \"70914831\", :n 1, :id \"28464344\", :poin" +
                        "ts 51, :surroundings {\"n\" [], \"w\" [{:type :ant, :location [1 1], :got-food false, :team \"cf\", :nest \"55670351\", :n 4, :id \"7677905" +
                        "9\", :points 12}], \"s\" [], \"sw\" [], \"ne\" [{:type :ant, :location [3 0], :got-food true, :team \"skim-uku\", :nest \"55205710\", :n 4, :" +
                        "id \"24468088\", :points 22} {:type :ant, :location [3 0], :got-food true, :team \"skim-uku\", :nest \"55205710\", :n 3, :id \"86372\", :p" +
                        "oints 40}], \"e\" [{:type :ant, :location [3 1], :got-food false, :team \"cf\", :nest \"55670351\", :n 1, :id \"68273952\", :points 48}], " +
                        "\"nw\" [{:type :ant, :location [1 0], :got-food true, :team \"makis_o\", :nest \"2478913\", :n 1, :id \"37879642\", :points 4}], \"se\" []}}" +
                        "}\n");
        assertThat(ant.hasFood, is(false));
    }

    @Test
    public void hasFood() {
        Ant ant = new AntParser().parse(
                "{:response \"ok\", :stat {:type :ant, :location [2 1], :got-food true, :team \"mateu\", :nest \"70914831\", :n 1, :id \"28464344\", :poin" +
                        "ts 51, :surroundings {\"n\" [], \"w\" [{:type :ant, :location [1 1], :got-food false, :team \"cf\", :nest \"55670351\", :n 4, :id \"7677905" +
                        "9\", :points 12}], \"s\" [], \"sw\" [], \"ne\" [{:type :ant, :location [3 0], :got-food true, :team \"skim-uku\", :nest \"55205710\", :n 4, :" +
                        "id \"24468088\", :points 22} {:type :ant, :location [3 0], :got-food true, :team \"skim-uku\", :nest \"55205710\", :n 3, :id \"86372\", :p" +
                        "oints 40}], \"e\" [{:type :ant, :location [3 1], :got-food false, :team \"cf\", :nest \"55670351\", :n 1, :id \"68273952\", :points 48}], " +
                        "\"nw\" [{:type :ant, :location [1 0], :got-food true, :team \"makis_o\", :nest \"2478913\", :n 1, :id \"37879642\", :points 4}], \"se\" []}}" +
                        "}\n");
        assertThat(ant.hasFood, is(true));
    }
}
