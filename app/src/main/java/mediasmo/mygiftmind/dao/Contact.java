package mediasmo.mygiftmind.dao;

/**
 * Created by Jan on 28.02.2015.
 */
public class Contact {
    /**
     * int id
     */
    private int id;
    /**
     * String name
     */
    private String name;

    /**
     * constructor
     *
     * @param id int
     * @param name String
     */
    public Contact(int id, String name) {
        this.id = id;
        this.name = name;
    }
    /**
     * constructor
     *
     * @param name String
     */
    public Contact(String name) {
        this.name = name;
    }

    /**
     * getId
     *
     * @return int
     */
    public int getId() {
        return this.id;
    }
    /**
     * setId
     *
     * @param id int
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * getName
     *
     * @return String
     */
    public String getName() {
        return this.name;
    }
    /**
     * setName
     *
     * @param name String
     */
    public void setName(String name) {
        this.name = name;
    }
}
