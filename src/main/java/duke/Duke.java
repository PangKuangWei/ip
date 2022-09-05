package duke;

/**
 * Duke is a program that helps the user manage their tasks.
 */
public class Duke {
    private Storage storage;
    private TaskList taskList;

    enum TaskType {
        TODO,
        DEADLINE,
        EVENT
    }

    /**
     * Constructor for Duke.
     *
     * @param filePath the String that represents the relative path of the text document
     *                 to load or save from
     */
    public Duke(String filePath) {
        storage = new Storage(filePath);
        try {
            taskList = new TaskList(storage.loadData());
        } catch (DukeException e) {
            taskList = new TaskList();
        }
    }

    /**
     * Returns a response to the given input.
     *
     * @param input an input string
     * @return a string representation of the response to the given input
     */
    public String getResponse(String input) {
        String str = input.trim();
        String response;
        try {
            response = Parser.parseCommand(str, taskList);
            saveData();
        } catch (DukeException e) {
            response = e.toString();
        }
        return response;
    }

    public void saveData() throws DukeException {
        storage.saveData(taskList.getTasks());
    }
}
