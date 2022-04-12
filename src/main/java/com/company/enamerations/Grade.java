package com.company.enamerations;

public enum Grade {
    BACHELOR_I(1),BACHELOR_II(2),BACHELOR_III(3),BACHELOR_IV(4), GRADUATE_BACHELOR(5),
    MASTER_I(6), MASTER_II(7), GRADUATE_MASTER(8);

    private final int id;

    Grade(int id) {
        this.id = id;
    }

    public static void main(String[] args) {
        Grade result = valueOf("BACHELOR_I");
        System.out.println(result.ordinal());
    }

    public Grade increase(){
        Grade bg = this;
        if (this == BACHELOR_I)
            bg = BACHELOR_II;
        else if (this == BACHELOR_II)
            bg = BACHELOR_III;
        else if (this == BACHELOR_III)
            bg = BACHELOR_IV;
        else if (this == BACHELOR_IV)
            bg = GRADUATE_BACHELOR;
        return bg;
    }

    @Override
    public  String toString() {
        return "grade:" + this.getClass().getName();
    }
}
