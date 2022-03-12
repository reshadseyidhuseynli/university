package enamerations;

public enum BachelorGrade {
    BACHELOR_I(1),BACHELOR_II(2),BACHELOR_III(3),BACHELOR_IV(4), GRADUATE_BACHELOR(0);

    private final int year;

    BachelorGrade(int year) {
        this.year = year;
    }

    public BachelorGrade increase(){
        BachelorGrade bg = this;
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
        return "grade:" + year;
    }
}
