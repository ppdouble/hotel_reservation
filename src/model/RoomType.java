package model;

public enum RoomType {
    //SINGLE, DOUBLE;

    SINGLE(1), DOUBLE(2);

    private Integer FLAG;

    private RoomType (Integer FLAG) {
        this.FLAG = FLAG;
    }

    public Integer getFLAG() {
        return FLAG;
    }


}
