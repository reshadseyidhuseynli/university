package enamerations;

public enum MasterGrade {
    MASTER_I(1),MASTER_II(2),GRADUATE_MASTER(0);

    private final int year;

    MasterGrade(int year) {
        this.year = year;
    }

    public MasterGrade increase(){
        MasterGrade mg = this;
        if (this == MASTER_I)
            mg = MASTER_II;
        else if (this == MASTER_II)
            mg = GRADUATE_MASTER;
        return mg;
    }

    @Override
    public  String toString() {
        return "grade:" + year;
    }
}
