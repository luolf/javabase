package thread.test;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: luolifeng
 * Date: 2018-11-05
 * Time: 16:03
 */
public class Goods {
    private String id;
    private String producerName;

    public Goods(String id, String producerName) {
        this.id = id;
        this.producerName = producerName;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getProducerName() {
        return producerName;
    }

    public void setProducerName(String producerName) {
        this.producerName = producerName;
    }

    @Override
    public String toString() {
        return "商品编号="+this.getId();
    }
}
