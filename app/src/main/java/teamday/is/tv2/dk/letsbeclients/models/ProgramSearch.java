package teamday.is.tv2.dk.letsbeclients.models;

import java.util.List;

/**
 * Created by migo on 24/02/17.
 */
public class ProgramSearch {
    private List<Program> programs;

    public List<Program> getPrograms() {
        return programs;
    }

    public void setPrograms(List<Program> programs) {
        this.programs = programs;
    }

    @Override
    public String toString() {
        return "ProgramSearch{" +
                "programs=" + programs +
                '}';
    }
}
